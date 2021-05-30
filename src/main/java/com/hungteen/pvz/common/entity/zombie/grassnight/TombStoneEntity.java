package com.hungteen.pvz.common.entity.zombie.grassnight;

import java.util.Random;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.base.UnderGroundZombieEntity;
import com.hungteen.pvz.common.entity.zombie.other.NobleZombieEntity;
import com.hungteen.pvz.common.item.card.ImitaterCardItem;
import com.hungteen.pvz.common.item.card.PlantCardItem;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.others.WeightList;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TombStoneEntity extends UnderGroundZombieEntity {

	public static final Zombies[] GROUND_ZOMBIES = new Zombies[] {Zombies.NORMAL_ZOMBIE, Zombies.CONEHEAD_ZOMBIE, Zombies.BUCKETHEAD_ZOMBIE};
	private int currentSummonCD;
	private int minSummonCD = 360;
	private int maxSummonCD = 1200;
	
	public TombStoneEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.currentSummonCD = this.getRandom().nextInt(this.maxSummonCD - this.minSummonCD + 1) + this.minSummonCD; 
		this.canBeButter = false;
		this.canBeCold = false;
		this.canBeCharm = false;
		this.canBeMini = false;
		this.canBeFrozen = false;
		this.canBeStealByBungee = false;
		this.canCollideWithZombie = false;
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new NearestAttackableTargetGoal<NobleZombieEntity>(this, NobleZombieEntity.class, true));
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(0);
	}
	
	@Override
	public void aiStep() {
		super.aiStep();
		if(! this.level.isClientSide && this.getAttackTime() >= 0) {
			if(this.canSummonZombie()) {
				this.setAttackTime(this.getAttackTime() + 1);
				if(this.getAttackTime() >= this.currentSummonCD) {
					this.summonZombie();
					this.setAttackTime(0);
					this.currentSummonCD = this.getRandom().nextInt(this.maxSummonCD - this.minSummonCD + 1) + this.minSummonCD;
				}
			}
		}
		if(! this.level.isClientSide) {
			BlockPos pos = this.blockPosition();
			this.setPos(pos.getX() + 0.5, this.getY(), pos.getZ() + 0.5);
		}
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		return target instanceof TombStoneEntity;
	}
	
	public void summonZombie() {
		int pos = this.getRandom().nextInt(GROUND_ZOMBIES.length);
		PVZZombieEntity zombie = ZombieUtil.getZombieEntity(level, GROUND_ZOMBIES[pos]);
		if(zombie != null) {
			if(zombie instanceof UnderGroundZombieEntity) {
				((UnderGroundZombieEntity) zombie).setRiseType(true);
			}
			EntityUtil.onMobEntitySpawn(level, zombie, blockPosition());
		}
	}
	
	protected boolean canSummonZombie() {
		return this.getTarget() != null;
	}
	
	public static boolean canTombSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
			SpawnReason reason, BlockPos pos, Random rand) {
		return (worldIn instanceof ServerWorld && ! ((ServerWorld) worldIn).isDay()) ? canZombieSpawn(zombieType, worldIn, reason, pos, rand) : false;
	}
	
	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if(! level.isClientSide) {
			ItemStack stack = player.getItemInHand(hand);
			if(this.getPassengers().isEmpty() && stack.getItem() instanceof PlantCardItem) {
				PlantCardItem cardItem = (PlantCardItem) stack.getItem();
				if(cardItem.plantType == Plants.GRAVE_BUSTER) {
					PlantCardItem.checkSunAndSummonPlant(player, stack, cardItem, blockPosition(), (plantEntity) -> {
						plantEntity.setTarget(this);
					});
				} else if(cardItem instanceof ImitaterCardItem && ((ImitaterCardItem) cardItem).isPlantTypeEqual(stack, Plants.GRAVE_BUSTER)) {
					ImitaterCardItem.checkSunAndSummonImitater(player, stack, cardItem, blockPosition(), (imitater) -> {
						imitater.setTarget(this);
					});
				}
				return ActionResultType.CONSUME;
			}
		}
		return super.interactAt(player, vec3d, hand);
	}
	
	@Override
	protected WeightList<DropType> getDropSpecialList() {
		return super.getDropSpecialList().setLeftItem(DropType.COPPER);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.6f);
	}
	
	@Override
	public float getLife() {
		return 70;
	}

	@Override
	public double getPassengersRidingOffset() {
		return 0;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TOMB_STONE;
	}

}
