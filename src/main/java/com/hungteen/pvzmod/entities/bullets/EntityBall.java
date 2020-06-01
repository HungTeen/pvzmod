package com.hungteen.pvzmod.entities.bullets;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBall extends PVZThrowable{
	
	public EntityBall(World worldIn) {
		super(worldIn);
	}
	
	public EntityBall(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.motionX/=0.99f;
		this.motionY/=0.99f;
		this.motionZ/=0.99f;
	}
    
	@Override
	protected void onImpact(RayTraceResult result) {
		Entity target=result.entityHit;
        if (checkCanAttack(target))
        {
        	this.performAttack(target);
        }
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            if(!this.checkLive(result)) {
            	this.setDead();
            }
        }
	}

	protected void performAttack(Entity target)
	{
		target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.getThrower()), this.getAttackDamage());//damage
	}

	
	private float getAttackDamage()
	{
		return 4f;
	}
	
    @Override
	protected float getGravityVelocity()
    {
        return 0.1F;
    }
}
