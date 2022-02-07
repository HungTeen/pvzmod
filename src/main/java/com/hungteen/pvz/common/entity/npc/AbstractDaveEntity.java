package com.hungteen.pvz.common.entity.npc;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IHasGroup;
import com.hungteen.pvz.api.raid.IAmountComponent;
import com.hungteen.pvz.common.datapack.TransactionTypeLoader;
import com.hungteen.pvz.common.impl.challenge.amount.ConstantAmount;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.utils.others.WeightList;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractDaveEntity extends CreatureEntity implements IHasGroup {

	private static final DataParameter<CompoundNBT> GOODS = EntityDataManager.defineId(AbstractDaveEntity.class, DataSerializers.COMPOUND_TAG);
	private static final DataParameter<Integer> EXIST_TICK = EntityDataManager.defineId(AbstractDaveEntity.class, DataSerializers.INT);
	protected ResourceLocation transactionResource = null;
	private final Set<GoodType> set = new HashSet<>();
	@Nullable
	private PlayerEntity customer;

	public AbstractDaveEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.refreshDimensions();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(GOODS, new CompoundNBT());
		this.entityData.define(EXIST_TICK, 0);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(3, new LookAtCustomerGoal(this));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.25D) {
			
			@Override
			public boolean canUse() {
				if(this.mob instanceof AbstractDaveEntity) {
					return super.canUse() && ((AbstractDaveEntity) this.mob).getCustomer() == null;
				}
				return super.canUse();
			}
		});
	}

	@Override
	public void tick() {
		super.tick();
		if(! this.level.isClientSide){
			this.setExistTick(this.getExistTick() + 1);
			//TODO bug?
			if(this.getExistTick() % 24000 == 10){
				this.refreshTransactions();
			}
		}
	}

	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if (player.getItemInHand(hand).isEmpty()) {
			if (!level.isClientSide && player instanceof ServerPlayerEntity && ! this.hasCustomer()) {
				this.openContainer((ServerPlayerEntity) player);
				this.setCustomer(player);
			}
			return ActionResultType.SUCCESS;
		}
		return super.interactAt(player, vec3d, hand);
	}

	/**
	 * refresh good list each day.
	 */
	public void refreshTransactions(){
		this.clearTransaction();
		if(this.transactionResource != null){
			final TransactionType type = TransactionTypeLoader.getTransactionByRes(this.transactionResource);
			
			if(type != null){
				final WeightList<GoodType> list = new WeightList<>();
				final int count = type.getEachGoodCount();

				int id = 0;
				//add must choose good.
				for (GoodType goodType : type.goodTypes) {
					if(goodType.mustChoose){
						this.addGoodToTransactions(id ++, goodType);
					} else{
						list.addItem(goodType, goodType.weight);
					}
				}
				//add other goods randomly and unique.
				this.set.clear();
				for(int i = 0; i < count; ++ i){
					final GoodType goodType = list.getRandomItem(this.getRandom()).get();
					if(! this.set.contains(goodType)){
						this.addGoodToTransactions(id ++, goodType);
					}
				}
				this.set.clear();

				this.addExtraTransactions(id);
				
				return;
			}
		}
		PVZMod.LOGGER.error("Error : NPC Shopper has no transaction !");
	}

	/**
	 * get origin goods in shopper's nbt.
	 * {@link  com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer#getValidGoods(PlayerEntity)}
	 */
	public List<GoodType> getGoodList(){
		final List<GoodType> goods = new ArrayList<>();
		this.getGoods().getAllKeys().forEach(key -> {
			final CompoundNBT tmp = this.getGoods().getCompound(key);
			final GoodType goodType = new GoodType(tmp);
			goods.add(goodType);
		});
		return goods;
	}

	protected void addGoodToTransactions(int id, GoodType goodType){
		goodType.setPos(id);
		final CompoundNBT nbt = this.getGoods().copy();
		nbt.put("good_" + id, goodType.saveToNBT());
		this.setGoods(nbt);
	}

	public void sellGoodForTransactions(GoodType goodType){
		goodType.shrink();
		final CompoundNBT nbt = this.getGoods().copy();
		nbt.put("good_" + goodType.getPos(), goodType.saveToNBT());
		this.setGoods(nbt);
	}

	public void clearTransaction(){
		this.setGoods(new CompoundNBT());
	}

	protected void addExtraTransactions(int id){

	}

	protected abstract void openContainer(ServerPlayerEntity player);
	
	@Override
	public int getAmbientSoundInterval() {
		return 200;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9f, 2.6f);
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundRegister.DAVE_HURT.get();
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundRegister.DAVE_SCREAM.get();
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.CRAZY_SAY.get();
	}

	public void setCustomer(@Nullable PlayerEntity player) {
		this.customer = player;
	}

	@Nullable
	public PlayerEntity getCustomer() {
		return this.customer;
	}

	public boolean hasCustomer() {
		return this.customer != null;
	}

	@Nullable
	public Entity changeDimension(ServerWorld level, net.minecraftforge.common.util.ITeleporter teleporter) {
		this.resetCustomer();
		return super.changeDimension(level, teleporter);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}
	
	protected void resetCustomer() {
		this.setCustomer((PlayerEntity) null);
	}

	public void die(DamageSource cause) {
		super.die(cause);
		this.resetCustomer();
	}

	public boolean canBeLeashed(PlayerEntity player) {
		return false;
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compoundNBT) {
		super.readAdditionalSaveData(compoundNBT);
		if(compoundNBT.contains("goods_nbt")){
			this.setGoods(compoundNBT.getCompound("goods_nbt"));
		}
		if(compoundNBT.contains("exist_tick")){
			this.setExistTick(compoundNBT.getInt("exist_tick"));
		}
		if(compoundNBT.contains("transaction_res")){
			this.transactionResource = new ResourceLocation(compoundNBT.getString("transaction_res"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compoundNBT) {
		super.addAdditionalSaveData(compoundNBT);
		compoundNBT.put("goods_nbt", this.getGoods());
		compoundNBT.putInt("exist_tick", this.getExistTick());
		compoundNBT.putString("transaction_res", this.transactionResource.toString());
	}

	public void setGoods(CompoundNBT nbt){
		this.entityData.set(GOODS, nbt);
	}

	public CompoundNBT getGoods() {
		return this.entityData.get(GOODS);
	}

	public void setExistTick(int tick){
		this.entityData.set(EXIST_TICK, tick);
	}

	public int getExistTick(){
		return this.entityData.get(EXIST_TICK);
	}

	public static class LookAtCustomerGoal extends LookAtGoal {
		private final AbstractDaveEntity dave;

		public LookAtCustomerGoal(AbstractDaveEntity dave) {
			super(dave, PlayerEntity.class, 8.0F);
			this.dave = dave;
		}

		public boolean canUse() {
			if (this.dave.hasCustomer()) {
				this.lookAt = this.dave.getCustomer();
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public PVZGroupType getEntityGroupType() {
		return PVZGroupType.NEUTRALS;
	}

	public static class TransactionType{
		private final List<GoodType> goodTypes = new ArrayList<>();
		private IAmountComponent goodCount = new ConstantAmount();
		private final ResourceLocation resourceLocation;

		public TransactionType(ResourceLocation resourceLocation){
			this.resourceLocation = resourceLocation;
		}

		public void addGood(GoodType goodType){
			if(! this.goodTypes.contains(goodType)){
				this.goodTypes.add(goodType);
			}
		}

		public void setGoodCount(IAmountComponent amountComponent){
			this.goodCount = amountComponent;
		}

		public int getEachGoodCount(){
			return this.goodCount.getSpawnAmount();
		}
	}

	public static class GoodType{
		
		private final GoodTypes type;
		private final ItemStack good;
		private final int goodPrice;
		private final int weight;
		private int transactionLimit;
		private final boolean mustChoose;
		private int pos;

		public GoodType(GoodTypes type, ItemStack good, int goodPrice, int weight, int transactionLimit, boolean mustChoose){
			this.type = type;
			this.good = good;
			this.goodPrice = goodPrice;
			this.weight = weight;
			this.transactionLimit = transactionLimit;
			this.mustChoose = mustChoose;
		}

		public GoodType(CompoundNBT nbt){
			this.type = GoodTypes.values()[nbt.getInt("good_type")];
			this.good = ItemStack.of(nbt.getCompound("good"));
			this.goodPrice = nbt.getInt("good_price");
			this.weight = nbt.getInt("weight");
			this.transactionLimit = nbt.getInt("limit");
			this.mustChoose = nbt.getBoolean("must");
			this.pos = nbt.getInt("pos");
		}

		public CompoundNBT saveToNBT(){
			final CompoundNBT nbt = new CompoundNBT();
			nbt.putInt("good_type", this.type.ordinal());
			nbt.put("good", this.good.save(new CompoundNBT()));
			nbt.putInt("good_price", this.goodPrice);
			nbt.putInt("weight", this.weight);
			nbt.putInt("limit", this.transactionLimit);
			nbt.putBoolean("must", this.mustChoose);
			nbt.putInt("pos", this.pos);
			return nbt;
		}

		public void setPos(int pos){
			this.pos = pos;
		}

		public void shrink(){
			this.transactionLimit = Math.max(0, this.transactionLimit - 1);
		}

		public int getPos() {
			return pos;
		}

		public GoodTypes getType() {
			return type;
		}

		public int getTransactionLimit() {
			return transactionLimit;
		}

		public boolean isMustChoose() {
			return mustChoose;
		}

		public int getGoodPrice() {
			return goodPrice;
		}

		public int getPriority(){
			return this.getType().getPriority();
		}

		public ITextComponent getGoodDescription(){
			return this.type.getGoodDescription(this.good);
		}

		public ItemStack getGood() {
			return good;
		}
	}

	public enum GoodTypes{
		ITEM("item", 0, 10),
		ENERGY_2("energy", 2, 100),
		ENERGY_3("energy", 3, 100),
		ENERGY_4("energy", 4, 100),
		ENERGY_5("energy", 5, 100),
		ENERGY_6("energy", 6, 100),
		ENERGY_7("energy", 7, 100),
		ENERGY_8("energy", 8, 100),
		ENERGY_9("energy", 9, 100),
		ENERGY_10("energy", 10, 100),
		SLOT_2("slot", 2, 90),
		SLOT_3("slot", 3, 90),
		SLOT_4("slot", 4, 90),
		SLOT_5("slot", 5, 90),
		SLOT_6("slot", 6, 90),
		SLOT_7("slot", 7, 90),
		SLOT_8("slot", 8, 90),
		SLOT_9("slot", 9, 90),
		SLOT_10("slot", 10, 90),
		MONEY_1("money", 0, 95),
		MONEY_10("money", 0, 95),
		CHALLENGE("item", 0, 5),
		SLOT_MACHINE("item", 0, 6);

		private final String tag;
		private final int lvl;
		private final int priority;

		private GoodTypes(String tag, int lvl, int priority){
			this.tag = tag;
			this.lvl = lvl;
			this.priority = priority;
		}

		public boolean isValid(PlayerEntity player){
			if(isEnergy()){
				return PlayerUtil.getResource(player, Resources.MAX_ENERGY_NUM) == lvl - 1;
			}
			if(isSlot()){
				return PlayerUtil.getResource(player, Resources.SLOT_NUM) == lvl - 1;
			}
			return true;
		}

		public ITextComponent getGoodDescription(ItemStack stack){
			if(isEnergy()){
				return new TranslationTextComponent("gui.pvz.shop.more_energy");
			}
			if(isSlot()){
				return new TranslationTextComponent("gui.pvz.shop.more_slot");
			}
			if(isMoney()){
				return new TranslationTextComponent("gui.pvz.shop.money");
			}
			return stack.getItem().getDescription();
		}

		public int getPriority() {
			return priority;
		}

		public boolean isEnergy(){
			return this.tag.equals("energy");
		}

		public boolean isSlot(){
			return this.tag.equals("slot");
		}

		public boolean isMoney(){
			return this.tag.equals("money");
		}

		public boolean isItem(){
			return this.tag.equals("item");
		}

	}
	
}