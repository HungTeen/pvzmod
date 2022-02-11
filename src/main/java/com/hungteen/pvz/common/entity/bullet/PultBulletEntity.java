package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.interfaces.ICanPushBack;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Optional;

public abstract class PultBulletEntity extends AbstractBulletEntity implements ICanPushBack {

	protected int targetChance = 5;
	protected Optional<LivingEntity> lockTarget = Optional.empty();
	protected Optional<BlockPos> lockPos = Optional.empty();
	protected float height = 12;
	protected boolean isPushBack = false;
	
	public PultBulletEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
		this.setNoGravity(false);
		this.airSlowDown = 1F;
	}
	
	public PultBulletEntity(EntityType<?> type, World worldIn, LivingEntity shooter) {
		super(type, worldIn, shooter);
		this.setNoGravity(false);
		this.airSlowDown = 1F;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! this.level.isClientSide && ! this.isPushBack && this.tickCount % this.targetChance == 0) {
			if(this.lockTarget.isPresent() && EntityUtil.isEntityValid(lockTarget.get())) {
				final LivingEntity target = this.lockTarget.get();
				final Vector3d speed = this.getDeltaMovement();
			    final double g = this.getGravityVelocity();
			    final double t1 = speed.y / g;
			    final double height = speed.y * speed.y / 2 / g;
			    final double downHeight = this.getY() + height - target.getY() - target.getBbHeight();
			    if(downHeight < 0){
					return ;
				}
			    final double t2 = Math.sqrt(2 * downHeight / g);
			    final double dx = target.getX() + target.getDeltaMovement().x() * (t1 + t2) - this.getX();
			    final double dz = target.getZ() + target.getDeltaMovement().z() * (t1 + t2) - this.getZ();
			    final double dxz = MathHelper.sqrt(dx * dx + dz * dz);
			    final double vxz = dxz / (t1 + t2);
	    	    if(dxz == 0) {
	    	    	this.setDeltaMovement(0, speed.y, 0);
	    	    } else {
	    		    this.setDeltaMovement(vxz * dx / dxz, speed.y, vxz * dz / dxz);
	    	    }
			}
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (this.shouldHit(target)) {
				target.invulnerableTime = 0;
				this.dealDamage(target); // attack 
				flag = true;
			}
		}
		this.level.broadcastEntityEvent(this, (byte) 3);
		if (flag) {
			this.remove();
		} else if(! this.checkLive(result)) {
			this.onHitBlock();
			this.remove();
		}
	}
	
	protected void onHitBlock() {
	}
	
	@Override
	protected int getMaxLiveTick() {
		return 200;
	}

	@Override
	public void pushBack() {
		this.isPushBack = true;
		this.setDeltaMovement(MathUtil.getRandomFloat(random), this.random.nextFloat() * 2, MathUtil.getRandomFloat(random));
	}
	
	protected abstract void dealDamage(Entity target);
	
	/**
     * Pult shoot
     */
    public void shootPultBullet(LivingEntity target) {
    	if(target == null) {
    		PVZMod.LOGGER.warn("No pult target at all !");
    		return ;
    	}
    	this.lockTarget = Optional.ofNullable(target);
    	final double g = this.getGravityVelocity();
    	final double t1 = MathHelper.sqrt(2 * height / g);//go up time
    	double t2 = 0;
    	if(this.getY() + height - target.getY() - target.getBbHeight() >= 0) {//random pult
    		t2 = MathHelper.sqrt(2 * (this.getY() + height - target.getY() - target.getBbHeight()) / g);//go down time
    	}
    	final double dx = target.getX() + target.getDeltaMovement().x() * (t1 + t2) - this.getX();
    	final double dz = target.getZ() + target.getDeltaMovement().z() * (t1 + t2) - this.getZ();
    	final double dxz = MathHelper.sqrt(dx * dx + dz * dz);
    	final double vxz = dxz / (t1 + t2);
    	final double vy = g * t1;
    	if(dxz == 0) {
    		this.setDeltaMovement(0, vy, 0);
    	} else {
    		this.setDeltaMovement(vxz * dx / dxz, vy, vxz * dz / dxz);
    	}
    }
    
    /**
     * Pult shoot
     */
    public void shootPultBullet(BlockPos pos) {
    	if(pos == null) {
    		PVZMod.LOGGER.warn("No pult target at all !");
    		return ;
    	}
    	this.lockPos = Optional.ofNullable(pos);
    	final double g = this.getGravityVelocity();
    	final double t1 = MathHelper.sqrt(2 * height / g);//go up time
    	double t2 = 0;
    	if(this.getY() + height - pos.getY() - 1 >= 0) {//random pult
    		t2 = MathHelper.sqrt(2 * (this.getY() + height - pos.getY() - 1) / g);//go down time
    	}
    	final double dx = pos.getX() - this.getX();
    	final double dz = pos.getZ() - this.getZ();
    	final double dxz = MathHelper.sqrt(dx * dx + dz * dz);
    	final double vxz = dxz / (t1 + t2);
    	final double vy = g * t1;
    	if(dxz == 0) {
    		this.setDeltaMovement(0, vy, 0);
    	} else {
    		this.setDeltaMovement(vxz * dx / dxz, vy, vxz * dz / dxz);
    	}
    }
    
	@Override
	protected float getGravityVelocity() {
		return 0.1F;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("target_entity_id")) {
			this.lockTarget = Optional.ofNullable((LivingEntity) level.getEntity(compound.getInt("target_entity_id")));
		}
		if(compound.contains("is_target_push_back")) {
			this.isPushBack = compound.getBoolean("is_target_push_back");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		if(this.lockTarget.isPresent()) {
			compound.putInt("target_entity_id", this.lockTarget.get().getId());
		}
		compound.putBoolean("is_target_push_back", this.isPushBack);
	}

}
