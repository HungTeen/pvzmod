package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class PultBulletEntity extends AbstractBulletEntity {

	protected int targetChance = 5;
	protected LivingEntity target = null;
	
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
		if(! this.world.isRemote && this.ticksExisted % this.targetChance == 0 && EntityUtil.isEntityValid(target)) {
			Vec3d speed = this.getMotion();
			double g = this.getGravityVelocity();
			double t1 = speed.y / g;
			double height = speed.y * speed.y / 2 / g;
			double downHeight = this.getPosY() + height - target.getPosY() - target.getHeight();
			double t2 = Math.sqrt(2 * downHeight / g);
			double dx = target.getPosX() + target.getMotion().getX() * (t1 + t2) - this.getPosX();
	    	double dz = target.getPosZ() + target.getMotion().getZ() * (t1 + t2) - this.getPosZ();
	    	double dxz = MathHelper.sqrt(dx * dx + dz * dz);
	    	double vxz = dxz / (t1 + t2);
	    	this.setMotion(vxz * dx / dxz, speed.y, vxz * dz / dxz);
		}
	}

	/**
     * Pult shoot
     */
    public void shootPultBullet(LivingEntity target) {
    	if(target == null) {
    		System.out.println("Warn: No target at all .");
    		return ;
    	}
    	this.target = target;
    	double h = 10;//max throw height
    	double g = this.getGravityVelocity();
    	double t1 = MathHelper.sqrt(2 * h / g);//go up time
    	double t2 = MathHelper.sqrt(2 * (this.getPosY() + h - target.getPosY() - target.getHeight()) / g);//go down time
    	double dx = target.getPosX() + target.getMotion().getX() * (t1 + t2) - this.getPosX();
    	double dz = target.getPosZ() + target.getMotion().getZ() * (t1 + t2) - this.getPosZ();
    	double dxz = MathHelper.sqrt(dx * dx + dz * dz);
    	double vxz = dxz / (t1 + t2);
    	double vy = g * t1;
    	this.setMotion(vxz * dx / dxz, vy, vxz * dz / dxz);
    }
    
	@Override
	protected float getGravityVelocity() {
		return 0.1F;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("target_entity_id")) {
			this.target = (LivingEntity) world.getEntityByID(compound.getInt("target_entity_id"));
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		if(this.target != null) {
			compound.putInt("target_entity_id", this.target.getEntityId());
		}
	}

}
