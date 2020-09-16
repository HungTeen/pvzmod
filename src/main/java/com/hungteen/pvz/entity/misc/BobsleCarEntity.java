package com.hungteen.pvz.entity.misc;

import javax.annotation.Nullable;

import com.hungteen.pvz.register.ItemRegister;

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
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class BobsleCarEntity extends Entity {

	private static final DataParameter<Integer> TIME_SINCE_HIT = EntityDataManager.createKey(BobsleCarEntity.class,
			DataSerializers.VARINT);
	private static final DataParameter<Float> DAMAGE_TAKEN = EntityDataManager.createKey(BobsleCarEntity.class,
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
		recalculateSize();
	}
	
	protected void registerData() {
		this.dataManager.register(TIME_SINCE_HIT, 0);
		this.dataManager.register(DAMAGE_TAKEN, 0.0F);
	}

	public void tick() {
		super.tick();
		this.status = this.getCarStatus();
		if(this.status==Status.IN_WATER) {
			this.removePassengers();
			this.remove();
		}
//		if(this.ticksExisted%20==0) {
//			System.out.println(this.rotationYaw);
//		}
		if (this.getTimeSinceHit() > 0) {
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0.0F) {
			this.setDamageTaken(this.getDamageTaken() - 1.0F);
		}
		
		this.tickLerp();
		if (this.canPassengerSteer()) {
			this.updateMotion();
			if (this.world.isRemote) {
				this.updateControls();
			}

			this.move(MoverType.SELF, this.getMotion());
		} else {
			this.setMotion(Vec3d.ZERO);
		}

		this.doBlockCollisions();
		
		if(!world.isRemote) {//check collide or passenger 
//			System.out.println(this.world.getEntitiesInAABBexcluding(this,this.getBoundingBox().grow((double) 0.2F, (double) -0.01F, (double) 0.2F),EntityPredicates.pushableBy(this)).size());
			for(Entity entity:this.world.getEntitiesInAABBexcluding(this,this.getBoundingBox().grow((double) 0.2F, (double) -0.01F, (double) 0.2F),EntityPredicates.pushableBy(this))) {
				if(!entity.isPassenger()) {
//					System.out.println("has entity");
				    if(checkCanRideOn(entity)) {
				    	entity.startRiding(this);
				    }else {
				    	this.applyEntityCollision(entity);
				    }
				}
			}
		}
	}

    private boolean checkCanRideOn(Entity entity) {
//    	System.out.println(entity.getWidth()+" "+this.getWidth());
        return !(this.getControllingPassenger() instanceof PlayerEntity)&&this.getPassengers().size()<MAX_PASSENGER_SIZE&&!entity.isPassenger()
        		&&entity.getWidth()<this.getWidth()&&entity instanceof LivingEntity&&!(entity instanceof WaterMobEntity) && !(entity instanceof PlayerEntity);
    }

	@Override
	public boolean processInitialInteract(PlayerEntity player, Hand hand) {
//		System.out.println("click!");
		if (player.isSecondaryUseActive()) {
			return false;
		}
		return !this.world.isRemote ? player.startRiding(this) : false;
	}
	
	@OnlyIn(Dist.CLIENT)
	protected void updateControls() {
		Minecraft mc = Minecraft.getInstance();
		if(this.isRidingPlayer(mc.player)) {
			float f = 0;
//			boolean left = mc.gameSettings.keyBindLeft.isPressed();
//			boolean right = mc.gameSettings.keyBindRight.isPressed();
//			boolean forward = mc.gameSettings.keyBindForward.isPressed();
//			boolean back = mc.gameSettings.keyBindBack.isPressed();
			boolean left = mc.gameSettings.keyBindLeft.isKeyDown();
			boolean right = mc.gameSettings.keyBindRight.isKeyDown();
			boolean forward = mc.gameSettings.keyBindForward.isKeyDown();
			boolean back = mc.gameSettings.keyBindBack.isKeyDown();
			if(left) {
				this.deltaRotation-=MAX_ROTATION;
			}
			if(right) {
	            this.deltaRotation+=MAX_ROTATION;
	        }
			if(!right&&!left) {
				this.deltaRotation=0;
			}
			this.rotationYaw += this.deltaRotation;
			if(right!=left&&!forward&&!back) {
				f+=MAX_MOVE_SPEED/10f;
			}
			if(forward) {
				f+=MAX_MOVE_SPEED;
		    }
			if(back) {
				f-=MAX_MOVE_SPEED/3;
			}
			this.setMotion(this.getMotion().add((double)(MathHelper.sin(-this.rotationYaw * ((float)Math.PI / 180F)) * f), 0.0D, (double)(MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F)) * f)));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	private boolean isRidingPlayer(PlayerEntity player) {
		return player.getRidingEntity()!=null&&player.getRidingEntity()==this;
	}

//	public void applyEntityCollision(Entity entityIn) {
//		if (entityIn instanceof BobsleCarEntity) {
//			if (entityIn.getBoundingBox().minY < this.getBoundingBox().maxY) {
//				super.applyEntityCollision(entityIn);
//			}
//		} else if (entityIn.getBoundingBox().minY <= this.getBoundingBox().minY) {
//			super.applyEntityCollision(entityIn);
//		}
//	}

	@SuppressWarnings("deprecation")
	public boolean canBeCollidedWith() {
		return !this.removed;
	}

	@Nullable
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	protected boolean canFitPassenger(Entity passenger) {
		return this.getPassengers().size() < MAX_PASSENGER_SIZE;
	}

	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return false;
	}

	@Override
	public boolean canBeRiddenInWater() {
		return false;
	}

	@Nullable
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		return entityIn.getBoundingBox();
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox() {
		return this.getBoundingBox();
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public double getMountedYOffset() {
		return 0.2D;
	}

	@SuppressWarnings("deprecation")
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else if (!this.world.isRemote && !this.removed) {
			if (source instanceof IndirectEntityDamageSource && source.getTrueSource() != null
					&& this.isPassenger(source.getTrueSource())) {
				return false;
			} else {
				this.setTimeSinceHit(10);
				this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
				this.markVelocityChanged();
				boolean flag = source.getTrueSource() instanceof PlayerEntity
						&& ((PlayerEntity) source.getTrueSource()).abilities.isCreativeMode;
				if (flag || this.getDamageTaken() > 40.0F) {
					if (!flag && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
	                  this.entityDropItem(ItemRegister.BOBSLE_CAR.get());
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
	public void performHurtAnimation() {
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}

	/**
	 * Sets a target for the client to interpolate towards over the next few ticks
	 */
	@OnlyIn(Dist.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch,
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
	public Direction getAdjustedHorizontalFacing() {
		return this.getHorizontalFacing().rotateY();
	}


	private void tickLerp() {
		if (this.canPassengerSteer()) {
			this.lerpSteps = 0;
			this.setPacketCoordinates(this.getPosX(), this.getPosY(), this.getPosZ());
		}

		if (this.lerpSteps > 0) {
			double d0 = this.getPosX() + (this.lerpX - this.getPosX()) / (double) this.lerpSteps;
			double d1 = this.getPosY() + (this.lerpY - this.getPosY()) / (double) this.lerpSteps;
			double d2 = this.getPosZ() + (this.lerpZ - this.getPosZ()) / (double) this.lerpSteps;
			double d3 = MathHelper.wrapDegrees(this.lerpYaw - (double) this.rotationYaw);
			this.rotationYaw = (float) ((double) this.rotationYaw + d3 / (double) this.lerpSteps);
			this.rotationPitch = (float) ((double) this.rotationPitch
					+ (this.lerpPitch - (double) this.rotationPitch) / (double) this.lerpSteps);
			--this.lerpSteps;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		}
	}


	private Status getCarStatus() {
		if (this.isInWater()) {
			return BobsleCarEntity.Status.IN_WATER;
		} else {
			float f = this.getCarGlide();
			if(f==SNOW_SMOOTH) {
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
		if(this.world.getBlockState(new BlockPos(this)).getBlock()==Blocks.SNOW||this.world.getBlockState(new BlockPos(this).down()).getBlock()==Blocks.SNOW_BLOCK) {
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
		
		try (BlockPos.PooledMutable blockpos$pooledmutable = BlockPos.PooledMutable.retain()) {
			for (int l1 = i; l1 < j; ++l1) {
				for (int i2 = i1; i2 < j1; ++i2) {
					int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
					if (j2 != 2) {
						for (int k2 = k; k2 < l; ++k2) {
							if (j2 <= 0 || k2 != k && k2 != l - 1) {
								blockpos$pooledmutable.setPos(l1, k2, i2);
								BlockState blockstate = this.world.getBlockState(blockpos$pooledmutable);
								if (!(blockstate.getBlock() instanceof LilyPadBlock) && VoxelShapes.compare(
										blockstate.getCollisionShape(this.world, blockpos$pooledmutable)
												.withOffset((double) l1, (double) k2, (double) i2),
										voxelshape, IBooleanFunction.AND)) {
									f += blockstate.getSlipperiness(this.world, blockpos$pooledmutable, this);
									++k1;
								}
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
		} else if(this.status == Status.ON_SNOW) {
			this.momentum = this.boatGlide;
			if (this.getControllingPassenger() instanceof PlayerEntity) {
				this.boatGlide /= 2.0F;
			}
		}

		Vec3d vec3d = this.getMotion();
		this.setMotion(vec3d.x * (double) this.momentum, vec3d.y + d1, vec3d.z * (double) this.momentum);
		this.deltaRotation *= this.momentum;
	}

	@SuppressWarnings("deprecation")
	public void updatePassenger(Entity passenger) {
		if (this.isPassenger(passenger)) {
			float f = 0.0F;
			float f1 = (float) ((this.removed ? (double) 0.01F : this.getMountedYOffset()) + passenger.getYOffset());
			if (this.getPassengers().size() > 1) {
				int i = this.getPassengers().indexOf(passenger);
				f = 0.2F - 0.7f*i;
				if (passenger instanceof AnimalEntity) {
					f = (float) ((double) f + 0.2D);
				}
			}

			Vec3d vec3d = (new Vec3d((double) f, 0.0D, 0.0D))
					.rotateYaw(-this.rotationYaw * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
			passenger.setPosition(this.getPosX() + vec3d.x, this.getPosY() + (double) f1, this.getPosZ() + vec3d.z);
			passenger.rotationYaw += this.deltaRotation;
			passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
			this.applyYawToEntity(passenger);
			if (passenger instanceof AnimalEntity && this.getPassengers().size() > 1) {
				int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
				passenger.setRenderYawOffset(((AnimalEntity) passenger).renderYawOffset + (float) j);
				passenger.setRotationYawHead(passenger.getRotationYawHead() + (float) j);
			}

		}
	}

	/**
	 * Applies this boat's yaw to the given entity. Used to update the orientation
	 * of its passenger.
	 */
	protected void applyYawToEntity(Entity entityToUpdate) {
		entityToUpdate.setRenderYawOffset(this.rotationYaw);
		float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
		float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
		entityToUpdate.prevRotationYaw += f1 - f;
		entityToUpdate.rotationYaw += f1 - f;
		entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
	}

	/**
	 * Applies this entity's orientation (pitch/yaw) to another entity. Used to
	 * update passenger orientation.
	 */
	@OnlyIn(Dist.CLIENT)
	public void applyOrientationToEntity(Entity entityToUpdate) {
		this.applyYawToEntity(entityToUpdate);
	}

	protected void writeAdditional(CompoundNBT compound) {
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readAdditional(CompoundNBT compound) {
	}

	@SuppressWarnings("deprecation")
	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		if (!this.isPassenger()) {
			if (onGroundIn) {
				if (this.fallDistance > 3.0F) {
					if (this.status != Status.ON_LAND && this.status!=Status.ON_SNOW) {
						this.fallDistance = 0.0F;
						return;
					}

					this.onLivingFall(this.fallDistance, 1.0F);
					if (!this.world.isRemote && !this.removed) {
						this.remove();
						if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
							this.entityDropItem(ItemRegister.BOBSLE_CAR.get());
						}
					}
				}
				this.fallDistance = 0.0F;
			} else if (!this.world.getFluidState((new BlockPos(this)).down()).isTagged(FluidTags.WATER) && y < 0.0D) {
				this.fallDistance = (float) ((double) this.fallDistance - y);
			}

		}
	}

	/**
	 * Sets the damage taken from the last hit.
	 */
	public void setDamageTaken(float damageTaken) {
		this.dataManager.set(DAMAGE_TAKEN, damageTaken);
	}

	/**
	 * Gets the damage taken from the last hit.
	 */
	public float getDamageTaken() {
		return this.dataManager.get(DAMAGE_TAKEN);
	}

	/**
	 * Sets the time to count down from since the last time entity was hit.
	 */
	public void setTimeSinceHit(int timeSinceHit) {
		this.dataManager.set(TIME_SINCE_HIT, timeSinceHit);
	}

	/**
	 * Gets the time since the last hit.
	 */
	public int getTimeSinceHit() {
		return this.dataManager.get(TIME_SINCE_HIT);
	}

	// Forge: Fix MC-119811 by instantly completing lerp on board
	@Override
	protected void addPassenger(Entity passenger) {
		super.addPassenger(passenger);
		if (this.canPassengerSteer() && this.lerpSteps > 0) {
			this.lerpSteps = 0;
			this.setPositionAndRotation(this.lerpX, this.lerpY, this.lerpZ, (float) this.lerpYaw,
					(float) this.lerpPitch);
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(1.25f, 1.4f);
	}
	
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static enum Status {
		IN_WATER, ON_SNOW, ON_LAND, IN_AIR;
	}

}
