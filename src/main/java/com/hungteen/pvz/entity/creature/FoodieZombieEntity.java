package com.hungteen.pvz.entity.creature;

import java.util.Random;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.ai.WaterTemptGoal;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.entity.zombie.poolday.SnorkelZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.SoundRegister;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
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
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class FoodieZombieEntity extends AnimalEntity {

	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(ItemRegister.FAKE_BRAIN.get(),
			ItemRegister.REAL_BRAIN.get());
	private static final DataParameter<Integer> GEN_TICK = EntityDataManager.createKey(FoodieZombieEntity.class,
			DataSerializers.VARINT);
	protected int lvl;
	protected static final int MAX_LVL = 10;

	public FoodieZombieEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new MoveHelperController(this);
		this.setPathPriority(PathNodeType.WATER, 0.0F);
		this.recalculateSize();
		this.lvl = 1;
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(GEN_TICK, -1);
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
	public void livingTick() {
		super.livingTick();
//		if(this.ticksExisted%50==0) {
//			System.out.println(this.getGenTick());
//		}
		if (!world.isRemote && this.getGenTick() >= 0) {
			this.setGenTick(this.getGenTick() - 1);
			if (this.getGenTick() == 0) {
				this.produceSun();
			}
		}
	}

	protected void produceSun() {
		SunEntity sun = EntityRegister.SUN.get().create(world);
		sun.setPosition(this.getPosX(), this.getPosY() + 1, this.getPosZ());
		sun.setAmount(this.getSunAmount());
		this.world.addEntity(sun);
	}

	protected int getSunAmount() {
		return 20 + this.lvl * 5;
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		if (this.isBreedingItem(itemstack)) {
			if (!this.world.isRemote && this.getGrowingAge() == 0 && this.getGenTick() == -1 && this.canBreed()) {
				this.consumeItemFromStack(player, itemstack);
				this.playSound(SoundRegister.SLURP.get(), 1f, 1f);
				this.setInLove(player);
				this.setGenTick(this.getGenCD());// start gen tick
				player.swing(hand, true);
				return true;
			}

			if (this.isChild()) {
				this.consumeItemFromStack(player, itemstack);
				this.ageUp((int) ((float) (-this.getGrowingAge() / 20) * 0.1F), true);
				return true;
			} else {
				if (itemstack.getItem() == ItemRegister.REAL_BRAIN.get() && this.lvl <= MAX_LVL) {
					this.lvl++;
				}
			}
		}
		return super.processInteract(player, hand);
	}

	private int getGenCD() {
		return 600 - this.lvl * 20;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		if (this.isChild()) {
			return EntitySize.flexible(0.3f, 0.3f);
		}
		return EntitySize.flexible(0.7f, 0.5f);
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return EntityRegister.FOODIE_ZOMBIE.get().create(world);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}

	public boolean isNotColliding(IWorldReader worldIn) {
		return worldIn.checkNoEntityCollision(this);
	}

	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}

	protected void onDeathUpdate() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove();

			for (int i = 0; i < 20; ++i) {
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				this.world.addParticle(ParticleTypes.POOF, this.getPosXRandom(1.0D), this.getPosYRandom(),
						this.getPosZRandom(1.0D), d0, d1, d2);
			}

			this.doDeathSpawn();
		}
	};

	private void doDeathSpawn() {
		if (!world.isRemote) {
			SnorkelZombieEntity snorkel = EntityRegister.SNORKEL_ZOMBIE.get().create(world);
			snorkel.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
			world.addEntity(snorkel);
		}
	}

	public boolean isPushedByWater() {
		return false;
	}

	public boolean canBeLeashedTo(PlayerEntity player) {
		return true;
	}

	public int getGenTick() {
		return this.dataManager.get(GEN_TICK);
	}

	public void setGenTick(int tick) {
		this.dataManager.set(GEN_TICK, tick);
	}
	
	@Override
	protected ResourceLocation getLootTable() {
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
				this.zombie.setMotion(this.zombie.getMotion().add(0.0D, 0.005D, 0.0D));
			}

			if (this.action == MovementController.Action.MOVE_TO && !this.zombie.getNavigator().noPath()) {
				double d0 = this.posX - this.zombie.getPosX();
				double d1 = this.posY - this.zombie.getPosY();
				double d2 = this.posZ - this.zombie.getPosZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d3 < (double) 2.5000003E-7F) {
					this.mob.setMoveForward(0.0F);
				} else {
					float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.zombie.rotationYaw = this.limitAngle(this.zombie.rotationYaw, f, 10.0F);
					this.zombie.renderYawOffset = this.zombie.rotationYaw;
					this.zombie.rotationYawHead = this.zombie.rotationYaw;
					float f1 = (float) (this.speed
							* this.zombie.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					if (this.zombie.isInWater()) {
						this.zombie.setAIMoveSpeed(f1 * 0.02F);
						float f2 = -((float) (MathHelper.atan2(d1, (double) MathHelper.sqrt(d0 * d0 + d2 * d2))
								* (double) (180F / (float) Math.PI)));
						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
						this.zombie.rotationPitch = this.limitAngle(this.zombie.rotationPitch, f2, 5.0F);
						float f3 = MathHelper.cos(this.zombie.rotationPitch * ((float) Math.PI / 180F));
						float f4 = MathHelper.sin(this.zombie.rotationPitch * ((float) Math.PI / 180F));
						this.zombie.moveForward = f3 * f1;
						this.zombie.moveVertical = -f4 * f1;
					} else {
						this.zombie.setAIMoveSpeed(f1 * 0.1F);
					}

				}
			} else {
				this.zombie.setAIMoveSpeed(0.0F);
				this.zombie.setMoveStrafing(0.0F);
				this.zombie.setMoveVertical(0.0F);
				this.zombie.setMoveForward(0.0F);
			}
		}
	}

	public static boolean canSpawn(EntityType<? extends MobEntity> type, IWorld worldIn, SpawnReason reason,BlockPos pos, Random randomIn) {
		return worldIn.getBlockState(pos).getBlock() == Blocks.WATER && worldIn.getBlockState(pos.up()).getBlock() == Blocks.WATER;
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.lvl = compound.getInt("zombie_lvl");
		this.setGenTick(compound.getInt("gen_tick"));
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("zombie_lvl", this.lvl);
		compound.putInt("gen_tick", this.getGenTick());
	}

}
