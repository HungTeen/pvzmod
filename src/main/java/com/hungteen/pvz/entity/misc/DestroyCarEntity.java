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
		if(! world.isRemote) {
			if(this.ticksExisted >= 100) {
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
    	if(this.getPosY() + height - target.getPosY() - target.getHeight() >= 0) {//random pult
    		t2 = MathHelper.sqrt(2 * (this.getPosY() + height - target.getPosY() - target.getHeight()) / g);//go down time
    	}
    	double dx = target.getPosX() + target.getMotion().getX() * (t1 + t2) - this.getPosX();
    	double dz = target.getPosZ() + target.getMotion().getZ() * (t1 + t2) - this.getPosZ();
    	double dxz = MathHelper.sqrt(dx * dx + dz * dz);
    	double vxz = dxz / (t1 + t2);
    	double vy = g * t1;
    	this.setMotion(vxz * dx / dxz, vy, vxz * dz / dxz);
    }
	
	private void tickCollision() {
		if(! world.isRemote && this.ticksExisted % 10 == 0) {
			EntityUtil.getAttackEntities(this, this.getBoundingBox().grow(0.5F)).forEach((target) -> {
				if(target instanceof PVZPlantEntity) {
					target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.getOwner()), EntityUtil.getCurrentMaxHealth((PVZPlantEntity) target) * 2);
				}
			});
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(2F, 2F);
	}

}
