package com.hungteen.pvz.entity.misc;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.roof.ZomBossEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;

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
	
	public DestroyCarEntity(World worldIn, ZomBossEntity boss) {
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
    	this.setDeltaMovement(vxz * dx / dxz, vy, vxz * dz / dxz);
    }
	
	private void tickCollision() {
		if(! level.isClientSide && this.tickCount % 10 == 0) {
			EntityUtil.getAttackEntities(this, this.getBoundingBox().inflate(0.5F)).forEach((target) -> {
				if(target instanceof PVZPlantEntity) {
					target.hurt(PVZDamageSource.causeNormalDamage(this, this.getOwner()), EntityUtil.getCurrentMaxHealth((PVZPlantEntity) target) * 2);
				}
			});
		}
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(2F, 2F);
	}

}
