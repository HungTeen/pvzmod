package com.hungteen.pvz.common.entity.misc.bowling;

import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.entity.AbstractOwnerEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Direction;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractBowlingEntity extends AbstractOwnerEntity {

	protected IntOpenHashSet hitEntities;
	private static final DataParameter<Integer> FACING = EntityDataManager.defineId(AbstractBowlingEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> DIRECTION = EntityDataManager.defineId(AbstractBowlingEntity.class, DataSerializers.INT);
	private int bowlingTick = 0;
	private int wallTick = 0;
	private boolean playSpawnSound = false;
	protected int hitCount = 0;
	
	public AbstractBowlingEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
		this.pushthrough = 1F;
	}
	
	public AbstractBowlingEntity(EntityType<?> type, World worldIn, PlayerEntity livingEntityIn) {
		this(type, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		this.entityData.define(FACING, BowlingFacings.MID.ordinal());
		this.entityData.define(DIRECTION, Direction.NORTH.ordinal());
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	public void tick() {
		super.tick();
		if (! level.isClientSide) {
			if(this.tickCount <= 10 && ! this.playSpawnSound) {
				EntityUtil.playSound(this, SoundRegister.BOWLING.get());
				this.playSpawnSound = true;
			}
			if(this.tickCount >= this.getMaxLiveTick()) {
				this.remove();
			}
		}
		this.yRotO = this.yRot;
		this.yRot = this.getDirection().toYRot() + this.getBowlingFacing().offset;
		double angle = this.yRot * Math.PI / 180;
		double dx = - Math.sin(angle);
		double dz = Math.cos(angle);
		double speed = this.getBowlingSpeed();
		this.setDeltaMovement(dx * speed, this.getDeltaMovement().y(), dz * speed);
		this.tickRayTrace();
		this.tickMove();
		this.tickCollision();
		if(! this.level.isClientSide) {
			if(this.bowlingTick > 0) -- this.bowlingTick;
			if(this.wallTick > 0) -- this.wallTick;
			if(this.bowlingTick == 0 && this.horizontalCollision) {// collide with wall
				if(this.wallTick > 0) {
					this.remove();
				} else {
					this.wallTick = 15;
					this.changeDiretion();
				}
			}
		}
	}
	
	protected void tickCollision() {
		if(this.level.isClientSide) return ;
		if(this.bowlingTick > 0) return ;
		List<Entity> list = this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox(), (target) -> {
			return EntityUtil.canTargetEntity(this.getOwnerOrSelf(), target);
		});
		if(! list.isEmpty()) {
			this.dealDamageTo(list.get(0));
			this.changeDiretion();
		}
	}
	
	private void tickRayTrace() {
		double rayLen = 3D;
		double angle = (this.getDirection().toYRot() + this.getBowlingFacing().offset) * Math.PI / 180;
		double dx = Math.sin(angle);
		double dz = - Math.cos(angle);
		Vector3d start = this.position();
		Vector3d end = start.add(new Vector3d(dx * rayLen, 0, dz * rayLen));
		RayTraceResult result = this.level.clip(new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
		if(result.getType() != RayTraceResult.Type.MISS) {// hit something
			end = result.getLocation();
		}
		EntityRayTraceResult entityRay = this.rayTraceEntities(start, end);
		if(entityRay != null) {
			result = entityRay;
		}
		if(result != null && result.getType() != RayTraceResult.Type.MISS && ! net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, result)) { 
			this.onImpact(result);
		}
	}
	
	protected void changeDiretion() {
		if(this.getBowlingFacing() == BowlingFacings.MID) {
			this.setBowlingFacing(this.random.nextInt(2) == 0 ? BowlingFacings.LEFT :BowlingFacings.RIGHT);
		} else if(this.getBowlingFacing() == BowlingFacings.LEFT){
			this.setBowlingFacing(BowlingFacings.RIGHT);
		} else if(this.getBowlingFacing() == BowlingFacings.RIGHT){
			this.setBowlingFacing(BowlingFacings.LEFT);
		}
		this.bowlingTick = 10;
	}
	
	protected abstract void dealDamageTo(Entity entity);
	
	public void shoot(PlayerEntity player) {
		Direction direction = player.getDirection();
		this.setDirection(direction);
		this.yRot = direction.toYRot();
	}
	
	public double getBowlingSpeed() {
		return 0.3D;
	}
	
	protected void addHitEntity(Entity entity) {
		this.hitEntities.addAll(EntityUtil.getOwnerAndPartsID(entity));
	}
	
	protected boolean shouldHit(Entity target) {
		return EntityUtil.canTargetEntity(this.getOwnerOrSelf(), target);
	}
	
	/**
	 * Gets the EntityRayTraceResult representing the entity hit
	 */
	@Nullable
	protected EntityRayTraceResult rayTraceEntities(Vector3d startVec, Vector3d endVec) {
		return ProjectileHelper.getEntityHitResult(this.level, this, startVec, endVec, 
				this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), (entity) -> {
			return entity.isPickable() && shouldHit(entity)
							&& (this.hitEntities == null|| ! this.hitEntities.contains(entity.getId()));
		});
	}

	protected void onImpact(RayTraceResult result) {
		
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.95F, 1F);
	}

	protected int getMaxLiveTick() {
		return 0;//return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.BowlingLiveTick.get();
	}
	
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("bowling_facings", this.getBowlingFacing().ordinal());
		compound.putInt("bowling_directions", this.getDirection().ordinal());
		compound.putInt("bowling_tick", this.bowlingTick);
		compound.putInt("bowling_hit_count", this.hitCount);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("bowling_facings")) {
			this.setBowlingFacing(BowlingFacings.values()[compound.getInt("bowling_facings")]);
		}
		if(compound.contains("bowling_directions")) {
			this.setDirection(Direction.values()[compound.getInt("bowling_directions")]);
		}
		if(compound.contains("bowling_tick")) {
			this.bowlingTick = compound.getInt("bowling_tick");
		}
		if(compound.contains("bowling_hit_count")) {
			this.hitCount = compound.getInt("bowling_hit_count");
		}
	}
	
	public Direction getDirection() {
		return Direction.values()[this.entityData.get(DIRECTION)];
	}
	
	public void setDirection(Direction drt) {
		this.entityData.set(DIRECTION, drt.ordinal());
	}
	
	public BowlingFacings getBowlingFacing() {
		return BowlingFacings.values()[this.entityData.get(FACING)];
	}
	
	public void setBowlingFacing(BowlingFacings facing) {
		this.entityData.set(FACING, facing.ordinal());
	}

	/**
	 * Checks if the entity is in range to render.
	 */
	@OnlyIn(Dist.CLIENT)
	public boolean shouldRenderAtSqrDistance(double distance) {
		double d0 = this.getBoundingBox().getSize() * 4.0D;
		if (Double.isNaN(d0)) {
			d0 = 4.0D;
		}

		d0 = d0 * 64.0D;
		return distance < d0 * d0;
	}

	/**
	 * Updates the entity motion clientside, called by packets from the server
	 */
	@OnlyIn(Dist.CLIENT)
	public void lerpMotion(double x, double y, double z) {
		this.setDeltaMovement(x, y, z);
//		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
//			float f = MathHelper.sqrt(x * x + z * z);
//			this.rotationYaw = (float) (MathHelper.atan2(x, z) * (double) (180F / (float) Math.PI));
//			this.rotationPitch = (float) (MathHelper.atan2(y, (double) f) * (double) (180F / (float) Math.PI));
//			this.prevRotationYaw = this.rotationYaw;
//			this.prevRotationPitch = this.rotationPitch;
//			this.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw,
//					this.rotationPitch);
//		}
	}

	@Override
	public boolean isAttackable() {
		return false;
	}

	public static enum BowlingFacings {
		LEFT(- 45),
		MID(0), 
		RIGHT(45),
		BOMB(0);
		public final float offset;
		
		private BowlingFacings(float offset) {
			this.offset = offset;
		}
		
	}

}
