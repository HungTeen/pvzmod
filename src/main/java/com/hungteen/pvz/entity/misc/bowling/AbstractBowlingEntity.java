package com.hungteen.pvz.entity.misc.bowling;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AbstractBowlingEntity extends Entity {

	protected LivingEntity owner;
	private UUID ownerId;
	protected IntOpenHashSet hitEntities;
	private static final DataParameter<Integer> FACING = EntityDataManager.createKey(AbstractBowlingEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> DIRECTION = EntityDataManager.createKey(AbstractBowlingEntity.class, DataSerializers.VARINT);
	private int bowlingTick = 0;
	private boolean playSpawnSound = false;
	protected int hitCount = 0;
	
	public AbstractBowlingEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public AbstractBowlingEntity(EntityType<?> type, World worldIn, PlayerEntity livingEntityIn) {
		this(type, worldIn);
		this.owner = livingEntityIn;
		this.ownerId = livingEntityIn.getUniqueID();
		this.entityCollisionReduction = 1F;
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
		if(this.ticksExisted <= 10) {
			this.recalculateSize();
		}
		double angle = (this.getDirection().getHorizontalAngle() + this.getBowlingFacing().offset) * Math.PI / 180;
		double dx = - Math.sin(angle);
		double dz = Math.cos(angle);
		double speed = this.getBowlingSpeed();
		this.setMotion(dx * speed, this.getMotion().getY(), dz * speed);
		this.tickRayTrace();
		this.tickMove();
		this.tickCollision();
		if(! this.world.isRemote) {
			if(this.bowlingTick > 0) -- this.bowlingTick;
			if(this.bowlingTick == 0 && this.collidedHorizontally) {
				this.changeDiretion();
			}
		}
	}
	
	protected void tickCollision() {
		if(this.world.isRemote) return ;
		if(this.bowlingTick > 0) return ;
		List<Entity> list = this.world.getEntitiesWithinAABB(Entity.class, this.getBoundingBox(), (target) -> {
			return EntityUtil.checkCanEntityAttack(this.getThrower(), target);
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
//		if(this.ticksExisted % 50 == 0) System.out.println(start + "," + end);
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
	
	private void tickMove() {
		Vec3d vec3d = this.getMotion();
		double d0 = this.getPosX() + vec3d.x;
		double d1 = this.getPosY() + vec3d.y;
		double d2 = this.getPosZ() + vec3d.z;
		float f = MathHelper.sqrt(horizontalMag(vec3d));
		this.rotationYaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (double) (180F / (float) Math.PI));
		for (this.rotationPitch = (float) (MathHelper.atan2(vec3d.y, (double) f)
				* (double) (180F / (float) Math.PI)); this.rotationPitch
						- this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
			;
		}
		while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
			this.prevRotationPitch += 360.0F;
		}
		while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}
		while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}
		this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
		this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
		float f1;
		if (this.isInWater()) {
			for (int i = 0; i < 4; ++i) {
				this.world.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D,
						d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
			}
			f1 = 0.8F;
		} else {
			f1 = 1F;
		}
		this.setMotion(vec3d.scale((double) f1));
		if (! this.hasNoGravity()) {
			Vec3d vec3d1 = this.getMotion();
			this.setMotion(vec3d1.x, vec3d1.y - (double) this.getGravityVelocity(), vec3d1.z);
		}
		this.move(MoverType.SELF, this.getMotion());
	}

	protected abstract void dealDamageTo(Entity entity);
	
	public void shoot(PlayerEntity player) {
		Direction direction = player.getHorizontalFacing();
		this.setDirection(direction);
	}
	
	public double getBowlingSpeed() {
		return 0.3D;
	}
	
	protected void addHitEntity(Entity entity) {
		this.hitEntities.addAll(EntityUtil.getOwnerAndPartsID(entity));
	}
	
	protected boolean shouldHit(Entity target) {
		return EntityUtil.checkCanEntityAttack(getThrower(), target);
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

	protected float getGravityVelocity() {
		return 0.05F;
	}

	protected void onImpact(RayTraceResult result) {
		
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.95F, 1F);
	}

	protected int getMaxLiveTick() {
		return 400;
	}
	
	public void setThrower(PlayerEntity player) {
		this.owner = player;
	}
	
	public void writeAdditional(CompoundNBT compound) {
		if (this.ownerId != null) {
			compound.put("owner", NBTUtil.writeUniqueId(this.ownerId));
		}
		compound.putInt("bowling_facings", this.getBowlingFacing().ordinal());
		compound.putInt("bowling_directions", this.getDirection().ordinal());
		compound.putInt("bowling_tick", this.bowlingTick);
		compound.putInt("bowling_hit_count", this.hitCount);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditional(CompoundNBT compound) {
		this.owner = null;
		if (compound.contains("owner", 10)) {
			this.ownerId = NBTUtil.readUniqueId(compound.getCompound("owner"));
		}
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

	@SuppressWarnings("deprecation")
	@Nullable
	public LivingEntity getThrower() {
		if ((this.owner == null || this.owner.removed) && this.ownerId != null && this.world instanceof ServerWorld) {
			Entity entity = ((ServerWorld) this.world).getEntityByUuid(this.ownerId);
			if (entity instanceof LivingEntity) {
				this.owner = (LivingEntity) entity;
			} else {
				this.owner = null;
			}
		}
		return this.owner;
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
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt(x * x + z * z);
			this.rotationYaw = (float) (MathHelper.atan2(x, z) * (double) (180F / (float) Math.PI));
			this.rotationPitch = (float) (MathHelper.atan2(y, (double) f) * (double) (180F / (float) Math.PI));
			this.prevRotationYaw = this.rotationYaw;
			this.prevRotationPitch = this.rotationPitch;
			this.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw,
					this.rotationPitch);
		}
	}

	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}

	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
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
