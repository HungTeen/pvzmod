package com.hungteen.pvz.entity.bullet;

import javax.annotation.Nullable;

import com.hungteen.pvz.entity.misc.AbstractOwnerEntity;
import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IGroupEntity;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AbstractBulletEntity extends AbstractOwnerEntity implements IGroupEntity {

	protected IntOpenHashSet hitEntities;
	protected float airSlowDown = 0.99F;
	protected float attackDamage = 0F;
	
	public AbstractBulletEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}

	public AbstractBulletEntity(EntityType<?> type, World worldIn, LivingEntity livingEntityIn) {
		super(type, worldIn, livingEntityIn);
		this.attackDamage = this.getAttackDamage();
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
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			float f = MathHelper.sqrt(x * x + z * z);
			this.yRot = (float) (MathHelper.atan2(x, z) * (double) (180F / (float) Math.PI));
			this.xRot = (float) (MathHelper.atan2(y, (double) f) * (double) (180F / (float) Math.PI));
			this.yRotO = this.yRot;
			this.xRotO = this.xRot;
			this.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot,
					this.xRot);
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void tick() {
		super.tick();
		if (! level.isClientSide && this.tickCount >= this.getMaxLiveTick()) {
			this.remove();
		}
		if(this.tickCount <= 10) {
			this.refreshDimensions();
		}
		//on hit
		if(! level.isClientSide) {
			Vector3d start = this.position();
		    Vector3d end = start.add(this.getDeltaMovement());
		    RayTraceResult result = this.level.clip(new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
		    if(result.getType() != RayTraceResult.Type.MISS) {// hit something
		    	end = result.getLocation();
		    }
		    EntityRayTraceResult entityRay = this.rayTraceEntities(start, end);
		    if(entityRay != null) {
			    result = entityRay;
		    }
		    if(result != null && result.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, result)) {//on hit 
			    this.onImpact(result);
		    }
		}
		//move
		Vector3d vec3d = this.getDeltaMovement();
		double d0 = this.getX() + vec3d.x;
		double d1 = this.getY() + vec3d.y;
		double d2 = this.getZ() + vec3d.z;
		float f = MathHelper.sqrt(getHorizontalDistanceSqr(vec3d));
		this.yRot = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (double) (180F / (float) Math.PI));
		for (this.xRot = (float) (MathHelper.atan2(vec3d.y, (double) f)
				* (double) (180F / (float) Math.PI)); this.xRot
						- this.xRotO < -180.0F; this.xRotO -= 360.0F) {
			;
		}
		while (this.xRot - this.xRotO >= 180.0F) {
			this.xRotO += 360.0F;
		}
		while (this.yRot - this.yRotO < -180.0F) {
			this.yRotO -= 360.0F;
		}
		while (this.yRot - this.yRotO >= 180.0F) {
			this.yRotO += 360.0F;
		}
		this.xRot = MathHelper.lerp(0.2F, this.xRotO, this.xRot);
		this.yRot = MathHelper.lerp(0.2F, this.yRotO, this.yRot);
		float f1;
		if (this.isInWater()) {
			for (int i = 0; i < 4; ++i) {
				this.level.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D,
						d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
			}
			f1 = 0.8F;
		} else {
			f1 = this.airSlowDown;
		}
		this.setDeltaMovement(vec3d.scale((double) f1));
		if (!this.isNoGravity()) {
			Vector3d vec3d1 = this.getDeltaMovement();
			this.setDeltaMovement(vec3d1.x, vec3d1.y - (double) this.getGravityVelocity(), vec3d1.z);
		}
		this.setPos(d0, d1, d2);
	}

	protected void addHitEntity(Entity entity) {
		this.hitEntities.addAll(EntityUtil.getOwnerAndPartsID(entity));
	}
	
	protected boolean checkCanAttack(Entity target){
		return EntityUtil.checkCanEntityAttack(this, target);
	}
	
	protected boolean shouldHit(Entity target) {
		return EntityUtil.checkCanEntityAttack(this, target);
	}
	
	/**
	 * shoot bullet such as pea or spore
	 */
	public void shootPea(double dx, double dy, double dz, double speed) {
		double down = this.getShootPeaAngle();
		double dxz = Math.sqrt(dx * dx + dz * dz);
		dy = MathHelper.clamp(dy, - dxz / down, dxz / down);
		double dis = Math.sqrt(dx * dx + dy * dy + dz * dz);
		double vx = dx / dis * speed;
		double vy = dy / dis * speed;
		double vz = dz / dis * speed;
		this.setDeltaMovement(vx, vy, vz);
	}
	
	public void shootPeaAnti(double dx, double dy, double dz, double speed) {
		double down = this.getShootPeaAngle();
		double dxz = Math.sqrt(dx * dx + dz * dz);
		dy = MathHelper.clamp(dy, -dxz / down, dxz / down);
		double dis = Math.sqrt(dx * dx + dy * dy + dz * dz);
		double vx = dx / dis * speed;
		double vy = dy / dis * speed;
		double vz = dz / dis * speed;
		this.setDeltaMovement(- vx, - vy, - vz);
	}
	
	public void shootToTarget(LivingEntity target, double speed) {
		this.setDeltaMovement(target.position().add(0, target.getEyeHeight(), 0).subtract(this.position()).normalize().scale(speed));
	}
	
	/**
	 * get how much angle can shoot by thrower
	 */
	protected double getShootPeaAngle() {
		if (this.getThrower() instanceof PlantShooterEntity) {
			return ((PlantShooterEntity) this.getThrower()).getMaxShootAngle();
		}
		return 10;
	}
	
	/**
	 * Gets the EntityRayTraceResult representing the entity hit
	 */
	@Nullable
	protected EntityRayTraceResult rayTraceEntities(Vector3d startVec, Vector3d endVec) {
		return ProjectileHelper.getEntityHitResult(this.level, this, startVec, endVec, 
				this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), (entity) -> {
			return entity.isPickable() && shouldHit(entity)
							&& (this.hitEntities == null|| !this.hitEntities.contains(entity.getId()));
		});
	}

	/**
	 * Gets the amount of gravity to apply to the thrown entity with each tick.
	 */
	protected float getGravityVelocity() {
		return 0.03F;
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected abstract void onImpact(RayTraceResult result);

	/**
	 * get the first initial damage for thrower.
	 */
	protected abstract float getAttackDamage();
	
	@Nullable
	public LivingEntity getThrower() {
		return this.getOwner();
	}
	
	protected abstract int getMaxLiveTick(); 
	
	protected boolean checkLive(RayTraceResult result) {
		if (result.getType() == RayTraceResult.Type.ENTITY) {// attack entity
			if (EntityUtil.checkCanEntityAttack(getThrower(), ((EntityRayTraceResult) result).getEntity())) {
				return false;
			}
			return true;
		} else if (result.getType() == RayTraceResult.Type.BLOCK) {
			Block block = level.getBlockState(((BlockRayTraceResult) result).getBlockPos()).getBlock();
			if (block instanceof BushBlock) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("bullet_attack_damage")) {
			this.attackDamage = compound.getFloat("bullet_attack_damage");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putFloat("bullet_attack_damage", this.attackDamage);
	}

	@Override
	public boolean isAttackable() {
		return false;
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
