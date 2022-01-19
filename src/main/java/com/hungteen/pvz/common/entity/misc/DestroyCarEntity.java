package com.hungteen.pvz.common.entity.misc;

import com.hungteen.pvz.common.entity.AbstractOwnerEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.roof.Edgar090505Entity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DestroyCarEntity extends AbstractOwnerEntity {

	protected final float height = 20;
	
	public DestroyCarEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}
	
	public DestroyCarEntity(World worldIn, Edgar090505Entity boss) {
		super(EntityRegister.DESTROY_CAR.get(), worldIn, boss);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! level.isClientSide) {
			if(this.tickCount >= 100) {
				this.remove();
			}
		}
		this.tickMove();
		this.tickCollision();
	}
	
	/**
     * Pult shoot
     */
    public void shootPultBullet(LivingEntity target) {
    	if(target == null) {
    		System.out.println("Warn: No target at all .");
    		return ;
    	}
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
    	this.setDeltaMovement(vxz * dx / dxz, vy + MathUtil.getRandomFloat(this.random) / 10, vxz * dz / dxz);
    }
	
	private void tickCollision() {
		if(! level.isClientSide && this.tickCount % 10 == 0) {
			EntityUtil.getTargetableEntities(this.getOwnerOrSelf(), this.getBoundingBox().inflate(1F)).forEach((target) -> {
				if(target instanceof PVZPlantEntity) {
					target.hurt(PVZDamageSource.causeDeadlyDamage(this, this.getOwner()), EntityUtil.getCurrentMaxHealth((PVZPlantEntity) target) * 2);
				} else {
					target.hurt(PVZDamageSource.normal(this, this.getOwner()), 5F);
				}
			});
		}
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(2F, 2F);
	}

}
