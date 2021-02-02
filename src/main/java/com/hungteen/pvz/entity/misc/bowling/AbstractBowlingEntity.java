package com.hungteen.pvz.entity.misc.bowling;

import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.misc.AbstractOwnerEntity;
import com.hungteen.pvz.register.SoundRegister;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractBowlingEntity extends AbstractOwnerEntity {

	protected IntOpenHashSet hitEntities;
	private static final DataParameter<Integer> FACING = EntityDataManager.createKey(AbstractBowlingEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> DIRECTION = EntityDataManager.createKey(AbstractBowlingEntity.class, DataSerializers.VARINT);
	private int bowlingTick = 0;
	private int wallTick = 0;
	private boolean playSpawnSound = false;
	protected int hitCount = 0;
	
	public AbstractBowlingEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
		this.entityCollisionReduction = 1F;
	}
	
	public AbstractBowlingEntity(EntityType<?> type, World worldIn, PlayerEntity livingEntityIn) {
		this(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		this.dataManager.register(FACING, BowlingFacings.MID.ordinal());
		this.dataManager.register(DIRECTION, Direction.NORTH.ordinal());
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	public void tick() {
		super.tick();
		if (! world.isRemote) {
			if(this.ticksExisted <= 10 && ! this.playSpawnSound) {
				EntityUtil.playSound(this, SoundRegister.BOWLING_SPAWN.get());
				this.playSpawnSound = true;
			}
			if(this.ticksExisted >= this.getMaxLiveTick()) {
				this.remove();
			}
		}
		this.prevRotationYaw = this.rotationYaw;
		this.rotationYaw = this.getDirection().getHorizontalAngle() + this.getBowlingFacing().offset;
		double angle = this.rotationYaw * Math.PI / 180;
		double dx = - Math.sin(angle);
		double dz = Math.cos(angle);
		double speed = this.getBowlingSpeed();
		this.setMotion(dx * speed, this.getMotion().getY(), dz * speed);
		this.tickRayTrace();
		this.tickMove();
		this.tickCollision();
		if(! this.world.isRemote) {
			if(this.bowlingTick > 0) -- this.bowlingTick;
			if(this.wallTick > 0) -- this.wallTick;
			if(this.bowlingTick == 0 && this.collidedHorizontally) {// collide with wall
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
		if(this.world.isRemote) return ;
		if(this.bowlingTick > 0) return ;
		List<Entity> list = this.world.getEntitiesWithinAABB(Entity.class, this.getBoundingBox(), (target) -> {
			return EntityUtil.checkCanEntityAttack(this, target);
		});
		if(! list.isEmpty()) {
			this.dealDamageTo(list.get(0));
			this.changeDiretion();
		}
	}
	
	private void tickRayTrace() {
		double rayLen = 3D;
		double angle = (this.getDirection().getHorizontalAngle() + this.getBowlingFacing().offset) * Math.PI / 180;
		double dx = Math.sin(angle);
		double dz = - Math.cos(angle);
		Vec3d start = this.getPositionVec();
		Vec3d end = start.add(new Vec3d(dx * rayLen, 0, dz * rayLen));
		RayTraceResult result = this.world.rayTraceBlocks(new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
		if(result.getType() != RayTraceResult.Type.MISS) {// hit something
			end = result.getHitVec();
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
			this.setBowlingFacing(this.rand.nextInt(2) == 0 ? BowlingFacings.LEFT :BowlingFacings.RIGHT);
		} else if(this.getBowlingFacing() == BowlingFacings.LEFT){
			this.setBowlingFacing(BowlingFacings.RIGHT);
		} else if(this.getBowlingFacing() == BowlingFacings.RIGHT){
			this.setBowlingFacing(BowlingFacings.LEFT);
		}
		this.bowlingTick = 10;
	}
	
	protected abstract void dealDamageTo(Entity entity);
	
	public void shoot(PlayerEntity player) {
		Direction direction = player.getHorizontalFacing();
		this.setDirection(direction);
		this.rotationYaw = direction.getHorizontalAngle();
	}
	
	public double getBowlingSpeed() {
		return 0.3D;
	}
	
	protected void addHitEntity(Entity entity) {
		this.hitEntities.addAll(EntityUtil.getOwnerAndPartsID(entity));
	}
	
	protected boolean shouldHit(Entity target) {
		return EntityUtil.checkCanEntityAttack(this.getOwner(), target);
	}
	
	/**
	 * Gets the EntityRayTraceResult representing the entity hit
	 */
	@Nullable
	protected EntityRayTraceResult rayTraceEntities(Vec3d startVec, Vec3d endVec) {
		return ProjectileHelper.rayTraceEntities(this.world, this, startVec, endVec, 
				this.getBoundingBox().expand(this.getMotion()).grow(1.0D), (entity) -> {
			return entity.canBeCollidedWith() && shouldHit(entity)
							&& (this.hitEntities == null|| !this.hitEntities.contains(entity.getEntityId()));
		});
	}

	protected void onImpact(RayTraceResult result) {
		
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.95F, 1F);
	}

	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.BowlingLiveTick.get();
	}
	
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("bowling_facings", this.getBowlingFacing().ordinal());
		compound.putInt("bowling_directions", this.getDirection().ordinal());
		compound.putInt("bowling_tick", this.bowlingTick);
		compound.putInt("bowling_hit_count", this.hitCount);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
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
		return Direction.values()[this.dataManager.get(DIRECTION)];
	}
	
	public void setDirection(Direction drt) {
		this.dataManager.set(DIRECTION, drt.ordinal());
	}
	
	public BowlingFacings getBowlingFacing() {
		return BowlingFacings.values()[this.dataManager.get(FACING)];
	}
	
	public void setBowlingFacing(BowlingFacings facing) {
		this.dataManager.set(FACING, facing.ordinal());
	}

	/**
	 * Checks if the entity is in range to render.
	 */
	@OnlyIn(Dist.CLIENT)
	public boolean isInRangeToRenderDist(double distance) {
		double d0 = this.getBoundingBox().getAverageEdgeLength() * 4.0D;
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
	public void setVelocity(double x, double y, double z) {
		this.setMotion(x, y, z);
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
	public boolean canBeAttackedWithItem() {
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
