package com.hungteen.pvz.common.entity.creature;

import com.hungteen.pvz.common.entity.ai.goal.WaterTemptGoal;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.common.entity.zombie.pool.SnorkelZombieEntity;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.misc.PVZLoot;
import com.hungteen.pvz.common.entity.EntityRegister;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.BreatheAirGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FoodieZombieEntity extends AnimalEntity {

	private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(ItemRegister.FAKE_BRAIN.get(),
			ItemRegister.REAL_BRAIN.get());
	private static final DataParameter<Integer> GEN_TICK = EntityDataManager.defineId(FoodieZombieEntity.class,
			DataSerializers.INT);
	protected int lvl;
	protected static final int MAX_LVL = 10;

	public FoodieZombieEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveControl = new MoveHelperController(this);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.refreshDimensions();
		this.lvl = 1;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(GEN_TICK, -1);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new BreatheAirGoal(this));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new WaterTemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (! level.isClientSide && this.getGenTick() >= 0) {
			this.setGenTick(this.getGenTick() - 1);
			if (this.getGenTick() == 0) {
				this.produceSun();
			}
		}
	}

	protected void produceSun() {
		SunEntity sun = EntityRegister.SUN.get().create(level);
		sun.setPos(this.getX(), this.getY() + 1, this.getZ());
		sun.setAmount(this.getSunAmount());
		this.level.addFreshEntity(sun);
	}

	protected int getSunAmount() {
		return 20 + this.lvl * 5;
	}

	 
	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (this.isFood(itemstack)) {
			if (! this.level.isClientSide && this.getAge() == 0 && this.getGenTick() == - 1 && this.canFallInLove()) {
				this.usePlayerItem(player, itemstack);
				this.playSound(SoundRegister.SLURP.get(), 1f, 1f);
				this.setInLove(player);
				this.setGenTick(this.getGenCD());// start gen tick
				player.swing(hand, true);
				return ActionResultType.CONSUME;
			}

			if (this.isBaby()) {
				this.usePlayerItem(player, itemstack);
				this.ageUp((int) ((float) (-this.getAge() / 20) * 0.1F), true);
				return ActionResultType.CONSUME;
			} else {
				if (itemstack.getItem() == ItemRegister.REAL_BRAIN.get() && this.lvl <= MAX_LVL) {
					++ this.lvl;
					return ActionResultType.CONSUME;
				}
			}
		}
	    return ActionResultType.FAIL;
	}

	private int getGenCD() {
		return 600 - this.lvl * 20;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if (this.isBaby()) {
			return EntitySize.scalable(0.3f, 0.3f);
		}
		return EntitySize.scalable(0.7f, 0.5f);
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity ageable) {
		return EntityRegister.FOODIE_ZOMBIE.get().create(level);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public CreatureAttribute getMobType() {
		return CreatureAttribute.WATER;
	}

	public boolean checkSpawnObstruction(IWorldReader worldIn) {
		return worldIn.isUnobstructed(this);
	}

	@Override
	protected PathNavigator createNavigation(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}

	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove();

			for (int i = 0; i < 20; ++i) {
				double d0 = this.random.nextGaussian() * 0.02D;
				double d1 = this.random.nextGaussian() * 0.02D;
				double d2 = this.random.nextGaussian() * 0.02D;
				this.level.addParticle(ParticleTypes.POOF, this.getRandomX(1.0D), this.getRandomY(),
						this.getRandomZ(1.0D), d0, d1, d2);
			}

			this.doDeathSpawn();
		}
	};

	private void doDeathSpawn() {
		if (!level.isClientSide) {
			SnorkelZombieEntity snorkel = EntityRegister.SNORKEL_ZOMBIE.get().create(level);
			snorkel.setPos(this.getX(), this.getY(), this.getZ());
			level.addFreshEntity(snorkel);
		}
	}

	public boolean isPushedByFluid() {
		return false;
	}

	public boolean canBeLeashed(PlayerEntity player) {
		return true;
	}

	public int getGenTick() {
		return this.entityData.get(GEN_TICK);
	}

	public void setGenTick(int tick) {
		this.entityData.set(GEN_TICK, tick);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.FOODIE_ZOMBIE;
	}

	static class MoveHelperController extends MovementController {
		private final FoodieZombieEntity zombie;

		public MoveHelperController(FoodieZombieEntity zombie) {
			super(zombie);
			this.zombie = zombie;
		}

		public void tick() {
			if (this.zombie.isInWater()) {
				this.zombie.setDeltaMovement(this.zombie.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MovementController.Action.MOVE_TO && !this.zombie.getNavigation().isDone()) {
				double d0 = this.wantedX - this.zombie.getX();
				double d1 = this.wantedY - this.zombie.getY();
				double d2 = this.wantedZ - this.zombie.getZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d3 < (double) 2.5000003E-7F) {
					this.mob.setZza(0.0F);
				} else {
					float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.zombie.yRot = this.rotlerp(this.zombie.yRot, f, 10.0F);
					this.zombie.yBodyRot = this.zombie.yRot;
					this.zombie.yHeadRot = this.zombie.yRot;
					float f1 = (float) (this.speedModifier
							* this.zombie.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
					if (this.zombie.isInWater()) {
						this.zombie.setSpeed(f1 * 0.02F);
						float f2 = -((float) (MathHelper.atan2(d1, (double) MathHelper.sqrt(d0 * d0 + d2 * d2))
								* (double) (180F / (float) Math.PI)));
						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
						this.zombie.xRot = this.rotlerp(this.zombie.xRot, f2, 5.0F);
						float f3 = MathHelper.cos(this.zombie.xRot * ((float) Math.PI / 180F));
						float f4 = MathHelper.sin(this.zombie.xRot * ((float) Math.PI / 180F));
						this.zombie.zza = f3 * f1;
						this.zombie.yya = -f4 * f1;
					} else {
						this.zombie.setSpeed(f1 * 0.1F);
					}

				}
			} else {
				this.zombie.setSpeed(0.0F);
				this.zombie.setXxa(0.0F);
				this.zombie.setYya(0.0F);
				this.zombie.setZza(0.0F);
			}
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("zombie_lvl")) {
			this.lvl = compound.getInt("zombie_lvl");
		}
		if(compound.contains("gen_tick")) {
			this.setGenTick(compound.getInt("gen_tick"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("zombie_lvl", this.lvl);
		compound.putInt("gen_tick", this.getGenTick());
	}

}
