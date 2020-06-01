package com.hungteen.pvzmod.entities.bullets;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityThorn extends PVZThrowable{

	public EntityThorn(World worldIn) {
		super(worldIn);
	}
	
	public EntityThorn(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
		this.setSize(0.2f, 0.2f);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.ticksExisted%10==0) {
			EntityLivingBase target=null;
			if(this.getThrower() instanceof EntityPlantBase) {
				target=((EntityLiving) this.getThrower()).getAttackTarget();
			}
			if(target!=null) {
				double dx=target.posX-this.posX;
				double dy=target.posY-this.posY;
				double dz=target.posZ-this.posZ;
				double dis=MathHelper.sqrt(dx*dx+dy*dy+dz*dz);
				double mx=dx/dis;
				double my=dy/dis;
				double mz=dz/dis;
				this.motionX=mx*this.getSpeed();
				this.motionY=my*this.getSpeed();
				this.motionZ=mz*this.getSpeed();
			}
			else {
				this.motionX=0;
				this.motionY=0.05;
				this.motionZ=0;
			}
		}
		if(this.ticksExisted>=1000) {
			this.setDead();
		}
		if(this.getThrower()!=null) {
			if(this.getThrower().isDead) {
				this.setDead();
			}
		}
	}
	
	private float getSpeed()
	{
		if(this.getThrower() instanceof EntityPlantBase) {
			EntityPlantBase plant=(EntityPlantBase) this.getThrower();
			int lvl=plant.getPlantLvl();
			if(lvl<=6) return 0.15f;
			else if(lvl<=13) return 0.25f;
			else return 0.35f;
		}
		return 0.05f;
	}
	
	public void shoot()
	{
		this.motionY=0.05;
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0;
	}

	private float getAttackDamage()
	{
		if(this.getThrower() instanceof EntityPlantBase) {
		    return ((EntityPlantBase) this.getThrower()).getAttackDamage();
		}
		return 2f;
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		Entity target=result.entityHit;
        if (checkCanAttack(target))
        {
        	target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.getThrower()), this.getAttackDamage());//damage
        }
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            if(!this.checkLive(result)) this.setDead();
        }
	}

}
