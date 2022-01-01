package com.hungteen.pvz.common.entity.misc;

import javax.annotation.Nullable;

import com.hungteen.pvz.common.item.ItemRegister;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class BobsleCarEntity extends Entity {

	private static final DataParameter<Integer> TIME_SINCE_HIT = EntityDataManager.defineId(BobsleCarEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Float> DAMAGE_TAKEN = EntityDataManager.defineId(BobsleCarEntity.class,
			DataSerializers.FLOAT);
	private static final int MAX_PASSENGER_SIZE = 4;
	private static final float SNOW_SMOOTH = 0.991f;
	private static final float MAX_MOVE_SPEED = 0.042f;
	private static final float MAX_ROTATION = 1f;
	private Status status;
	private float momentum;
	private float deltaRotation;
	private int lerpSteps;
	private double lerpX;
	private double lerpY;
	private double lerpZ;
	private double lerpYaw;
	private double lerpPitch;
	private float boatGlide;

	public BobsleCarEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		refreshDimensions();
	}

	protected void defineSynchedData() {
		this.entityData.define(TIME_SINCE_HIT, 0);
		this.entityData.define(DAMAGE_TAKEN, 0.0F);
	}

	public void tick() {
		super.tick();
		this.status = this.getCarStatus();
		if (this.status == Status.IN_WATER) {
			this.ejectPassengers();
			this.remove();
		}
		if (this.getTimeSinceHit() > 0) {
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0.0F) {
			this.setDamageTaken(this.getDamageTaken() - 1.0F);
		}

		this.tickLerp();
		if (this.isControlledByLocalInstance()) {
			this.updateMotion();
			if (this.level.isClientSide) {
				this.updateControls();
			}

			this.move(MoverType.SELF, this.getDeltaMovement());
		} else {
			this.setDeltaMovement(Vector3d.ZERO);
		}

		this.checkInsideBlocks();

		if (!level.isClientSide) {// check collide or passenger
			for (Entity entity : this.level.getEntities(this,
					this.getBoundingBox().inflate((double) 0.2F, (double) -0.01F, (double) 0.2F),
					EntityPredicates.pushableBy(this))) {
				if (!entity.isPassenger()) {
					if (checkCanRideOn(entity)) {
						entity.startRiding(this);
					} else {
						this.push(entity);
					}
				}
			}
		}
	}

	private boolean checkCanRideOn(Entity entity) {
		return !(this.getControllingPassenger() instanceof PlayerEntity)
				&& this.getPassengers().size() < MAX_PASSENGER_SIZE && !entity.isPassenger()
				&& entity.getBbWidth() < this.getBbWidth() && entity instanceof LivingEntity
				&& !(entity instanceof WaterMobEntity) && !(entity instanceof PlayerEntity);
	}

	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if (player.isSecondaryUseActive()) {
			return ActionResultType.FAIL;
		}
		if(! this.level.isClientSide) {
			player.startRiding(this);
			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.FAIL;
		}
	}

	@OnlyIn(Dist.CLIENT)
	protected void updateControls() {
		Minecraft mc = Minecraft.getInstance();
		if (this.isRidingPlayer(mc.player)) {
			float f = 0;
			boolean left = mc.options.keyLeft.isDown();
			boolean right = mc.options.keyRight.isDown();
			boolean forward = mc.options.keyUp.isDown();
			boolean back = mc.options.keyDown.isDown();
			if (left) {
				this.deltaRotation -= MAX_ROTATION;
			}
			if (right) {
				this.deltaRotation += MAX_ROTATION;
			}
			if (!right && !left) {
				this.deltaRotation = 0;
			}
			this.yRot += this.deltaRotation;
			if (right != left && !forward && !back) {
				f += MAX_MOVE_SPEED / 10f;
			}
			if (forward) {
				f += MAX_MOVE_SPEED;
			}
			if (back) {
				f -= MAX_MOVE_SPEED / 3;
			}
			this.setDeltaMovement(
					this.getDeltaMovement().add((double) (MathHelper.sin(-this.yRot * ((float) Math.PI / 180F)) * f),
							0.0D, (double) (MathHelper.cos(this.yRot * ((float) Math.PI / 180F)) * f)));
		}
	}

	@OnlyIn(Dist.CLIENT)
	private boolean isRidingPlayer(PlayerEntity player) {
		return player.getVehicle() != null && player.getVehicle() == this;
	}

	@SuppressWarnings("deprecation")
	public boolean isPickable() {
		return !this.removed;
	}

	@Nullable
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	}

	@Override
	public boolean isPushable() {
		return true;
	}

	@Override
	protected boolean canAddPassenger(Entity passenger) {
		return this.getPassengers().size() < MAX_PASSENGER_SIZE;
	}

	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return false;
	}

	@Override
	public boolean rideableUnderWater() {
		return false;
	}

	@Nullable
	public AxisAlignedBB func_70114_g(Entity entityIn) {
		return entityIn.getBoundingBox();
	}

	@Nullable
	public AxisAlignedBB func_70046_E() {
		return this.getBoundingBox();
	}

	protected boolean isMovementNoisy() {
		return false;
	}

	public double getPassengersRidingOffset() {
		return 0.2D;
	}

	@SuppressWarnings("deprecation")
	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else if (!this.level.isClientSide && !this.removed) {
			if (source instanceof IndirectEntityDamageSource && source.getEntity() != null
					&& this.hasPassenger(source.getEntity())) {
				return false;
			} else {
				this.setTimeSinceHit(10);
				this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
				this.markHurt();
				boolean flag = source.getEntity() instanceof PlayerEntity
						&& ((PlayerEntity) source.getEntity()).abilities.instabuild;
				if (flag || this.getDamageTaken() > 40.0F) {
					if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
						this.spawnAtLocation(ItemRegister.BOBSLE_CAR.get());
					}

					this.remove();
				}

				return true;
			}
		} else {
			return true;
		}
	}

	/**
	 * Setups the entity to do the hurt animation. Only used by packets in
	 * multiplayer.
	 */
	@OnlyIn(Dist.CLIENT)
	public void animateHurt() {
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}

	/**
	 * Sets a target for the client to interpolate towards over the next few ticks
	 */
	@OnlyIn(Dist.CLIENT)
	public void lerpTo(double x, double y, double z, float yaw, float pitch,
			int posRotationIncrements, boolean teleport) {
		this.lerpX = x;
		this.lerpY = y;
		this.lerpZ = z;
		this.lerpYaw = (double) yaw;
		this.lerpPitch = (double) pitch;
		this.lerpSteps = 10;
	}

	/**
	 * Gets the horizontal facing direction of this Entity, adjusted to take
	 * specially-treated entity types into account.
	 */
	public Direction getMotionDirection() {
		return this.getDirection().getClockWise();
	}

	private void tickLerp() {
		if (this.isControlledByLocalInstance()) {
			this.lerpSteps = 0;
			this.setPacketCoordinates(this.getX(), this.getY(), this.getZ());
		}

		if (this.lerpSteps > 0) {
			double d0 = this.getX() + (this.lerpX - this.getX()) / (double) this.lerpSteps;
			double d1 = this.getY() + (this.lerpY - this.getY()) / (double) this.lerpSteps;
			double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double) this.lerpSteps;
			double d3 = MathHelper.wrapDegrees(this.lerpYaw - (double) this.yRot);
			this.yRot = (float) ((double) this.yRot + d3 / (double) this.lerpSteps);
			this.xRot = (float) ((double) this.xRot
					+ (this.lerpPitch - (double) this.xRot) / (double) this.lerpSteps);
			--this.lerpSteps;
			this.setPos(d0, d1, d2);
			this.setRot(this.yRot, this.xRot);
		}
	}

	private Status getCarStatus() {
		if (this.isInWater()) {
			return BobsleCarEntity.Status.IN_WATER;
		} else {
			float f = this.getCarGlide();
			if (f == SNOW_SMOOTH) {
				this.boatGlide = f;
				return Status.ON_SNOW;
			}
			if (f > 0.0F) {
				this.boatGlide = f;
				return Status.ON_LAND;
			} else {
				return Status.IN_AIR;
			}
		}
	}

	/**
	 * Decides how much the boat should be gliding on the land (based on any
	 * slippery blocks)
	 */
	public float getCarGlide() {
		if (this.level.getBlockState(this.blockPosition()).getBlock() == Blocks.SNOW
				|| this.level.getBlockState(this.blockPosition().below()).getBlock() == Blocks.SNOW_BLOCK) {
			return SNOW_SMOOTH;
		}
		AxisAlignedBB axisalignedbb = this.getBoundingBox();
		AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY - 0.001D,
				axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
		int i = MathHelper.floor(axisalignedbb1.minX) - 1;
		int j = MathHelper.ceil(axisalignedbb1.maxX) + 1;
		int k = MathHelper.floor(axisalignedbb1.minY) - 1;
		int l = MathHelper.ceil(axisalignedbb1.maxY) + 1;
		int i1 = MathHelper.floor(axisalignedbb1.minZ) - 1;
		int j1 = MathHelper.ceil(axisalignedbb1.maxZ) + 1;
		VoxelShape voxelshape = VoxelShapes.create(axisalignedbb1);
		float f = 0.0F;
		int k1 = 0;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

	      for(int l1 = i; l1 < j; ++l1) {
	         for(int i2 = i1; i2 < j1; ++i2) {
	            int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
	            if (j2 != 2) {
	               for(int k2 = k; k2 < l; ++k2) {
	                  if (j2 <= 0 || k2 != k && k2 != l - 1) {
	                     blockpos$mutable.set(l1, k2, i2);
	                     BlockState blockstate = this.level.getBlockState(blockpos$mutable);
	                     if (!(blockstate.getBlock() instanceof LilyPadBlock) && VoxelShapes.joinIsNotEmpty(blockstate.getCollisionShape(this.level, blockpos$mutable).move((double)l1, (double)k2, (double)i2), voxelshape, IBooleanFunction.AND)) {
	                        f += blockstate.getSlipperiness(this.level, blockpos$mutable, this);
	                        ++k1;
	                     }
	                  }
	               }
	            }
	         }
	      }

		return f / (float) k1;
	}

	/**
	 * Update the boat's speed, based on momentum.
	 */
	private void updateMotion() {
		double d1 = -0.04;
		this.momentum = 0.05F;
		if (this.status == Status.IN_AIR) {
			this.momentum = 0.9F;
		} else if (this.status == Status.ON_LAND) {
			this.momentum = this.boatGlide;
			if (this.getControllingPassenger() instanceof PlayerEntity) {
				this.boatGlide /= 2.0F;
			}
		} else if (this.status == Status.ON_SNOW) {
			this.momentum = this.boatGlide;
			if (this.getControllingPassenger() instanceof PlayerEntity) {
				this.boatGlide /= 2.0F;
			}
		}

		Vector3d vec3d = this.getDeltaMovement();
		this.setDeltaMovement(vec3d.x * (double) this.momentum, vec3d.y + d1, vec3d.z * (double) this.momentum);
		this.deltaRotation *= this.momentum;
	}

	@SuppressWarnings("deprecation")
	public void positionRider(Entity passenger) {
		if (this.hasPassenger(passenger)) {
			float f = 0.0F;
			float f1 = (float) ((this.removed ? (double) 0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());
			if (this.getPassengers().size() > 1) {
				int i = this.getPassengers().indexOf(passenger);
				f = 0.2F - 0.7f * i;
				if (passenger instanceof AnimalEntity) {
					f = (float) ((double) f + 0.2D);
				}
			}

			Vector3d vec3d = (new Vector3d((double) f, 0.0D, 0.0D))
					.yRot(-this.yRot * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
			passenger.setPos(this.getX() + vec3d.x, this.getY() + (double) f1, this.getZ() + vec3d.z);
			passenger.yRot += this.deltaRotation;
			passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
			this.applyYawToEntity(passenger);
			if (passenger instanceof AnimalEntity && this.getPassengers().size() > 1) {
				int j = passenger.getId() % 2 == 0 ? 90 : 270;
				passenger.setYBodyRot(((AnimalEntity) passenger).yBodyRot + (float) j);
				passenger.setYHeadRot(passenger.getYHeadRot() + (float) j);
			}

		}
	}

	/**
	 * Applies this boat's yaw to the given entity. Used to update the orientation
	 * of its passenger.
	 */
	protected void applyYawToEntity(Entity entityToUpdate) {
		entityToUpdate.setYBodyRot(this.yRot);
		float f = MathHelper.wrapDegrees(entityToUpdate.yRot - this.yRot);
		float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
		entityToUpdate.yRotO += f1 - f;
		entityToUpdate.yRot += f1 - f;
		entityToUpdate.setYHeadRot(entityToUpdate.yRot);
	}

	/**
	 * Applies this entity's orientation (pitch/yaw) to another entity. Used to
	 * update passenger orientation.
	 */
	@OnlyIn(Dist.CLIENT)
	public void onPassengerTurned(Entity entityToUpdate) {
		this.applyYawToEntity(entityToUpdate);
	}

	protected void addAdditionalSaveData(CompoundNBT compound) {
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readAdditionalSaveData(CompoundNBT compound) {
	}

	@SuppressWarnings("deprecation")
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		if (!this.isPassenger()) {
			if (onGroundIn) {
				if (this.fallDistance > 3.0F) {
					if (this.status != Status.ON_LAND && this.status != Status.ON_SNOW) {
						this.fallDistance = 0.0F;
						return;
					}

					this.causeFallDamage(this.fallDistance, 1.0F);
					if (!this.level.isClientSide && !this.removed) {
						this.remove();
						if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
							this.spawnAtLocation(ItemRegister.BOBSLE_CAR.get());
						}
					}
				}
				this.fallDistance = 0.0F;
			} else if (!this.level.getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0.0D) {
				this.fallDistance = (float) ((double) this.fallDistance - y);
			}

		}
	}

	/**
	 * Sets the damage taken from the last hit.
	 */
	public void setDamageTaken(float damageTaken) {
		this.entityData.set(DAMAGE_TAKEN, damageTaken);
	}

	/**
	 * Gets the damage taken from the last hit.
	 */
	public float getDamageTaken() {
		return this.entityData.get(DAMAGE_TAKEN);
	}

	/**
	 * Sets the time to count down from since the last time entity was hit.
	 */
	public void setTimeSinceHit(int timeSinceHit) {
		this.entityData.set(TIME_SINCE_HIT, timeSinceHit);
	}

	/**
	 * Gets the time since the last hit.
	 */
	public int getTimeSinceHit() {
		return this.entityData.get(TIME_SINCE_HIT);
	}

	// Forge: Fix MC-119811 by instantly completing lerp on board
	@Override
	protected void addPassenger(Entity passenger) {
		super.addPassenger(passenger);
		if (this.isControlledByLocalInstance() && this.lerpSteps > 0) {
			this.lerpSteps = 0;
			this.absMoveTo(this.lerpX, this.lerpY, this.lerpZ, (float) this.lerpYaw,
					(float) this.lerpPitch);
		}
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(1.25f, 1.4f);
	}

	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static enum Status {
		IN_WATER, ON_SNOW, ON_LAND, IN_AIR;
	}

}
