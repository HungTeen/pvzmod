package com.hungteen.pvz.entity.bullet;

import java.util.Optional;

import com.hungteen.pvz.utils.EntityUtil;

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

public abstract class PultBulletEntity extends AbstractBulletEntity {

	protected int targetChance = 5;
	protected Optional<LivingEntity> lockTarget = Optional.empty();
	protected Optional<BlockPos> lockPos = Optional.empty();
	protected float height = 12;
	protected boolean isPushBack = false;
	
	public PultBulletEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
		this.airSlowDown = 1F;
	}
	
	public PultBulletEntity(EntityType<?> type, World worldIn, LivingEntity shooter) {
		super(type, worldIn, shooter);
		this.airSlowDown = 1F;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! this.level.isClientSide && ! this.isPushBack && this.tickCount % this.targetChance == 0) {
			if(this.lockTarget.isPresent() && EntityUtil.isEntityValid(lockTarget.get())) {
				LivingEntity target = this.lockTarget.get();
				Vector3d speed = this.getDeltaMovement();
			    double g = this.getGravityVelocity();
			    double t1 = speed.y / g;
			    double height = speed.y * speed.y / 2 / g;
			    double downHeight = this.getY() + height - target.getY() - target.getBbHeight();
			    if(downHeight < 0) return ;
			    double t2 = Math.sqrt(2 * downHeight / g);
			    double dx = target.getX() + target.getDeltaMovement().x() * (t1 + t2) - this.getX();
	    	    double dz = target.getZ() + target.getDeltaMovement().z() * (t1 + t2) - this.getZ();
	    	    double dxz = MathHelper.sqrt(dx * dx + dz * dz);
	    	    double vxz = dxz / (t1 + t2);
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
			if (checkCanAttack(target)) {
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
	
	public void pushBack() {
		this.isPushBack = true;
		this.setDeltaMovement((this.random.nextFloat() - 0.5F) * 2, this.random.nextFloat() * 2, (this.random.nextFloat() - 0.5F) * 2);
	}
	
	protected abstract void dealDamage(Entity target);
	
	/**
     * Pult shoot
     */
    public void shootPultBullet(LivingEntity target) {
    	if(target == null) {
    		System.out.println("Warn: No target at all .");
    		return ;
    	}
    	this.lockTarget = Optional.ofNullable(target);
    	double g = this.getGravityVelocity();
    	double t1 = MathHelper.sqrt(2 * height / g);//go up time
    	double t2 = 0;
    	if(this.getY() + height - target.getY() - target.getBbHeight() >= 0) {//random pult
    		t2 = MathHelper.sqrt(2 * (this.getY() + height - target.getY() - target.getBbHeight()) / g);//go down time
    	}
    	double dx = target.getX() + target.getDeltaMovement().x() * (t1 + t2) - this.getX();
    	double dz = target.getZ() + target.getDeltaMovement().z() * (t1 + t2) - this.getZ();
    	double dxz = MathHelper.sqrt(dx * dx + dz * dz);
    	double vxz = dxz / (t1 + t2);
    	double vy = g * t1;
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
    		System.out.println("Warn: No pos at all .");
    		return ;
    	}
    	this.lockPos = Optional.ofNullable(pos);
    	double g = this.getGravityVelocity();
    	double t1 = MathHelper.sqrt(2 * height / g);//go up time
    	double t2 = 0;
    	if(this.getY() + height - pos.getY() - 1 >= 0) {//random pult
    		t2 = MathHelper.sqrt(2 * (this.getY() + height - pos.getY() - 1) / g);//go down time
    	}
    	double dx = pos.getX() - this.getX();
    	double dz = pos.getZ() - this.getZ();
    	double dxz = MathHelper.sqrt(dx * dx + dz * dz);
    	double vxz = dxz / (t1 + t2);
    	double vy = g * t1;
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
