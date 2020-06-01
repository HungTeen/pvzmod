package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.entities.ai.EntityAIZombieEat;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntitySnorkelZombie extends EntityZombieBase{

	public EntitySnorkelZombie(World worldIn) {
		super(worldIn);
		this.setSize(0.9f, 2.1f);
	}

	@Override
	protected void initEntityAI()
    {
        this.tasks.addTask(2, new EntityAIZombieEat(this, 1.0, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        initAITargetTask();
    }
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35f);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.SLOW_WALK);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.world.isRemote&&this.getAttackTarget()!=null) {
			Entity target=this.getAttackTarget();
			if(this.isInWater()) {
				double dx=target.posX-this.posX;
				double dy=target.posY-this.posY;
				double dz=target.posZ-this.posZ;
				double dis=MathHelper.sqrt(dx*dx+dy*dy+dz*dz);
				this.motionX=dx*getWaterSpeed()/dis;
				this.motionY=dy*getWaterSpeed()/dis;
				this.motionZ=dz*getWaterSpeed()/dis;
				this.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);
			}
		}
	}
	
	protected double getWaterSpeed()
	{
		return this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue()/2;
	}
	
	@Override
	protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateSwimmer(this, worldIn);
    }
	
	@Override
	public boolean hasNoGravity() {
		if(this.isInWater()) return true;
		return super.hasNoGravity();
	}
	
	@Override
	public boolean canBreatheUnderwater()
    {
        return true;
    }
	
	public boolean getCanSpawnHere()
    {
        return this.posY > 35.0D && this.posY < (double)this.world.getSeaLevel() && super.getCanSpawnHere();
    }
	
	@Override
	protected float getWaterSlowDown() {
		return 0.9f;
	}
	
	@Override
	public boolean isPushedByWater() {
		return false;
	}
}

