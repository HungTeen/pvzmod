package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.entity.drop.CoinEntity;
import com.hungteen.pvz.entity.drop.CoinEntity.CoinType;
import com.hungteen.pvz.entity.drop.JewelEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.base.UnderGroundZombieEntity;
import com.hungteen.pvz.entity.zombie.other.NobleZombieEntity;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class TombStoneEntity extends UnderGroundZombieEntity{

	public static final Zombies[] GROUND_ZOMBIES = new Zombies[] {Zombies.NORMAL_ZOMBIE, Zombies.FLAG_ZOMBIE, Zombies.CONEHEAD_ZOMBIE, Zombies.BUCKETHEAD_ZOMBIE};
	private int currentSummonCD;
	private int minSummonCD = 360;
	private int maxSummonCD = 1200;
	
	public TombStoneEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void onZombieInitialSpawn() {
		super.onZombieInitialSpawn();
		this.currentSummonCD = this.getRNG().nextInt(this.maxSummonCD - this.minSummonCD + 1) + this.minSummonCD; 
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new NearestAttackableTargetGoal<NobleZombieEntity>(this, NobleZombieEntity.class, true));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(!this.world.isRemote && this.getAttackTime() >= 0) {
			if(this.canSummonZombie()) {
				this.setAttackTime(this.getAttackTime() + 1);
				if(this.getAttackTime() >= this.currentSummonCD) {
					this.summonZombie();
					this.setAttackTime(0);
					this.currentSummonCD = this.getRNG().nextInt(this.maxSummonCD - this.minSummonCD + 1) + this.minSummonCD;
				}
			}
		}
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		return target instanceof TombStoneEntity;
	}
	
	protected void summonZombie() {
		int pos = this.getRNG().nextInt(GROUND_ZOMBIES.length);
		PVZZombieEntity zombie = ZombieUtil.getZombieEntity(world, GROUND_ZOMBIES[pos]);
		if(zombie != null) {
			if(zombie instanceof UnderGroundZombieEntity) {
				((UnderGroundZombieEntity) zombie).setRiseType(true);
			}
			EntityUtil.onMobEntitySpawn(world, zombie, getPosition());
		}
	}
	
	protected boolean canSummonZombie() {
		return this.getAttackTarget() != null;
	}
	
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		if(! world.isRemote) {
			ItemStack stack = player.getHeldItem(hand);
			if(this.getPassengers().isEmpty() && stack.getItem() instanceof PlantCardItem) {
				PlantCardItem cardItem = (PlantCardItem) stack.getItem();
				if(cardItem.plantType == Plants.GRAVE_BUSTER) {
					PlantCardItem.checkSunAndSummonPlant(player, stack, cardItem, getPosition(), (plantEntity) -> {
						plantEntity.setAttackTarget(this);
					});
				}
			}
		}
		return super.processInteract(player, hand);
	}
	
	@Override
	protected void dropCoinOrSpecial() {
		int num = this.getRNG().nextInt(1000);
		if(num == 0) {
			JewelEntity jewel = EntityRegister.JEWEL.get().create(world);
			EntityUtil.onMobEntitySpawn(world, jewel, getPosition());
			return ;
		}
		if(num == 1){
			ItemEntity chocolate = new ItemEntity(world, getPosX(), getPosY(), getPosZ(), new ItemStack(ItemRegister.CHOCOLATE.get()));
			EntityUtil.playSound(chocolate, SoundRegister.JEWEL_DROP.get());
			world.addEntity(chocolate);
			return ;
		}
		if(num < 1000) {
			int amount = CoinType.COPPER.money;
			if(num < 12) amount = CoinType.GOLD.money;
			else if(num < 112) amount = CoinType.SILVER.money;
			CoinEntity coin = EntityRegister.COIN.get().create(world);
			coin.setAmount(amount);
			EntityUtil.onMobEntitySpawn(world, coin, getPosition());
			return ;
		}
	}
	
	@Override
	protected Type getSpawnType() {
		return Type.NORMAL;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8f, 1.6f);
	}
	
	@Override
	public float getLife() {
		return 70;
	}

	@Override
	public double getMountedYOffset() {
		return 0;
	}
	
	@Override
	public boolean canBeButter() {
		return false;
	}
	
	@Override
	public boolean canBeCold() {
		return false;
	}
	
	@Override
	public boolean canBeFrozen() {
		return false;
	}
	
	@Override
	public boolean canBeInvis() {
		return false;
	}
	
	@Override
	public boolean canBeSmall() {
		return false;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TOMB_STONE;
	}

}
