package com.hungteen.pvzmod.entities.npcs;

import java.util.Random;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.entities.ai.EntityAIFaceCustomer;
import com.hungteen.pvzmod.entities.ai.EntityAITradeWithPlayer;
import com.hungteen.pvzmod.util.StringUtil;
import com.hungteen.pvzmod.util.enums.Enums;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityTrader extends EntityCreature implements INpc, IMerchant {
	private static final DataParameter<String> TRADE_STATUSES = EntityDataManager.<String>createKey(EntityTrader.class, DataSerializers.STRING);
	private static final DataParameter<Integer> ADDITIONAL_TRADES = EntityDataManager.<Integer>createKey(EntityTrader.class, DataSerializers.VARINT);

	private MerchantRecipeList trades;
	private int maxTradesCount = 0;

	private String currentTradesInfo = "";
	private int additionalTrades = 0;
	private EntityPlayer latestCustomer;

	public EntityTrader(World world, float entityWidth, float entityHeight) {
		super(world);

		setSize(entityWidth, entityHeight);
		((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityMob.class, 8.0f, 0.8f, 1.0f));
		this.tasks.addTask(1, new EntityAITradeWithPlayer(this));
		this.tasks.addTask(1, new EntityAIFaceCustomer(this));
		this.tasks.addTask(2, new EntityAIMoveIndoors(this));
		this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
		this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(5, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0f, 1.0f));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6));
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		dataManager.register(TRADE_STATUSES, "");
		dataManager.register(ADDITIONAL_TRADES, 0);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	protected abstract double getBaseMaxHealth();

	protected abstract double getBaseMovementSpeed();

	protected boolean isFixedTradesList() {
		return false;
	}

	@Override
	public void setCustomer(@Nullable EntityPlayer player) {
		latestCustomer = player;
	}

	@Nullable
	@Override
	public EntityPlayer getCustomer() {
		return latestCustomer;
	}

	public boolean isTrading() {
		return latestCustomer != null;
	}

	@Override
	public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
		return type == EnumCreatureType.AMBIENT;
	}

	@Override
	public boolean getCanSpawnHere() {
		if (world.getWorldType() == WorldType.FLAT)
			return false;

		return checkSpawnChance() && checkWorldSpawnConditions() && isValidLightLevel() && canSpawnOnBlock(world.getBlockState(getPosition().down()));
	}

	protected boolean canSpawnOnBlock(IBlockState block) {
		return block.canEntitySpawn(this); //&& WorldUtil.isNaturalDimensionBlock(world, getPosition(), block);
	}

	protected boolean checkWorldSpawnConditions() {
		if (isOverworldNPC() && world.provider.getDimension() != 0) {
			EntityRegistry.removeSpawn(getClass(), EnumCreatureType.AMBIENT, world.getBiome(getPosition()));

			return false;
		}

		return true;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		compound.setString("TradeStatuses", currentTradesInfo);
		compound.setInteger("AdditionalTrades", additionalTrades);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		currentTradesInfo = compound.hasKey("TradeStatuses") ? compound.getString("TradeStatuses") : "";
		additionalTrades = compound.hasKey("AdditionalTrades") ? compound.getInteger("AdditionalTrades") : 0;

		if (!world.isRemote) {
			dataManager.set(TRADE_STATUSES, currentTradesInfo);
			dataManager.set(ADDITIONAL_TRADES, additionalTrades);
			deserializeTradeStatuses(currentTradesInfo);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote) {
			String currentInfo = dataManager.get(TRADE_STATUSES);

			if (!currentInfo.equals(currentTradesInfo)) {
				currentTradesInfo = currentInfo;
				int extraTrades = dataManager.get(ADDITIONAL_TRADES);

				if (extraTrades != additionalTrades) {
					additionalTrades = extraTrades;

					generateTrades();
				}

				deserializeTradeStatuses(currentInfo);
			}
		}
	}

	protected boolean isOverworldNPC() {
		return false;
	}

	protected int getSpawnChanceFactor() {
		return 10;
	}

	private boolean checkSpawnChance() {
		return getSpawnChanceFactor() <= 1 || rand.nextInt(getSpawnChanceFactor()) == 0;
	}

	protected boolean isValidLightLevel() {
		if (world.provider.getDimension() != 0)
			return true;

		BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

		if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32)) {
			return true;
		}
		else {
			int i = this.world.getLightFromNeighbors(blockpos);

			if (this.world.isThundering()) {
				int j = this.world.getSkylightSubtracted();
				this.world.setSkylightSubtracted(10);
				i = this.world.getLightFromNeighbors(blockpos);
				this.world.setSkylightSubtracted(j);
			}

			return i > this.rand.nextInt(8);
		}
	}

	protected abstract Enums.Guis getTraderGui();

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			heldStack.interactWithEntity(player, this, hand);

			return true;
		}

		if (isEntityAlive() && !player.isSneaking()) {
			if (!world.isRemote) {
				getRecipes(player);
				setCustomer(player);
				player.openGui(Main.instance, getTraderGui().guiId, world, getEntityId(), 0, 0);
			}

			return true;
		}

		return super.processInteract(player, hand);
	}

	@Nullable
	@Override
	public MerchantRecipeList getRecipes(EntityPlayer player) {
		if (trades == null)
			generateTrades();

		return trades;
	}

	private void generateTrades() {
		Random rand = new Random(1 + getUniqueID().getMostSignificantBits() + getUniqueID().getLeastSignificantBits());
		NonNullList<EntityTraderRecipe> allTrades = NonNullList.<EntityTraderRecipe>create();
		trades = new MerchantRecipeList();

		getTradesList(allTrades);

		maxTradesCount = allTrades.size();

		if (!isFixedTradesList()) {
			int newTradesSize = Math.max(rand.nextInt(allTrades.size()), 1);

			if (additionalTrades > 0 && newTradesSize < allTrades.size())
				newTradesSize = Math.min(newTradesSize + additionalTrades, allTrades.size());

			for (int i = 0; i < newTradesSize; i++) {
				int pick = rand.nextInt(allTrades.size());

				trades.add(allTrades.get(pick));
				allTrades.remove(pick);
			}
		}
		else {
			trades.addAll(allTrades);
		}
	}

	protected abstract void getTradesList(final NonNullList<EntityTraderRecipe> newTradesList);

	@SideOnly(Side.CLIENT)
	@Override
	public void setRecipes(@Nullable MerchantRecipeList recipeList) {}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
		if (getLootTable() != null) {
			LootTable lootTable = world.getLootTableManager().getLootTableFromLocation(getLootTable());

			LootContext.Builder lootBuilder = (new LootContext.Builder((WorldServer)world)).withLootedEntity(this).withDamageSource(source);

			if (wasRecentlyHit && attackingPlayer != null)
				lootBuilder.withPlayer(attackingPlayer).withLuck(attackingPlayer.getLuck() + lootingModifier);

			for (ItemStack stack : lootTable.generateLootForPools(rand, lootBuilder.build())) {
				entityDropItem(stack, 0);
			}

			dropEquipment(wasRecentlyHit, lootingModifier);
		}
		else {
			super.dropLoot(wasRecentlyHit, lootingModifier, source);
		}
	}

	@Override
	public void useRecipe(MerchantRecipe recipe) {
		if (isFixedTradesList() || trades.size() >= maxTradesCount)
			return;

		recipe.incrementToolUses();

		if (!world.isRemote) {
			if (recipe.getToolUses() >= recipe.getMaxTradeUses()) {
				boolean tradesComplete = true;

				for (MerchantRecipe trade : trades) {
					if (!trade.isRecipeDisabled()) {
						tradesComplete = false;

						break;
					}
				}

				if (tradesComplete)
					addNewTrade();
			}

			currentTradesInfo = serializeTradeStatuses();

			dataManager.set(TRADE_STATUSES, currentTradesInfo);
			dataManager.set(ADDITIONAL_TRADES, additionalTrades);
		}
	}

	@Override
	public void verifySellingItem(ItemStack stack) {}

	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public BlockPos getPos() {
		return new BlockPos(this);
	}

	private void addNewTrade() {
		additionalTrades++;

		generateTrades();
	}

	private String serializeTradeStatuses() {
		if (trades == null)
			return "";

		StringBuilder builder = new StringBuilder();

		for (MerchantRecipe trade : trades) {
			builder.append("|");
			builder.append(trade.getToolUses());
		}

		return builder.toString().substring(1);
	}

	private void deserializeTradeStatuses(String string) {
		if (!currentTradesInfo.equals(string))
			currentTradesInfo = string;

		if (trades == null)
			generateTrades();

		int tradeIndex = 0;

		if (!trades.isEmpty() && !currentTradesInfo.isEmpty()) {
			for (String s : currentTradesInfo.split("\\|")) {
				MerchantRecipe trade = trades.get(tradeIndex);

				for (int i = 0; i < StringUtil.toInteger(s) - trade.getToolUses(); i++) {
					trade.incrementToolUses();
				}

				tradeIndex++;
			}
		}
	}
}
