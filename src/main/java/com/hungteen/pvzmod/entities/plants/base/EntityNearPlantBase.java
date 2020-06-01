package com.hungteen.pvzmod.entities.plants.base;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.damage.PVZDamageType;
import com.hungteen.pvzmod.entities.ai.target.PVZAIPlantGlobalTarget;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityNearPlantBase extends EntityPlantBase{

	protected float range;
	
	public EntityNearPlantBase(World worldIn) {
		super(worldIn);
		range=1;
		initPlantAI();
	}
	
	protected void initPlantAI()
	{
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new PVZAIPlantGlobalTarget(this,range, range));
	}

	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(this.getAttackTarget()!=null) {
			this.performAttack();
		}
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		if(source instanceof PVZDamageSource) {
			if(((PVZDamageSource) source).getPVZDamageType()==PVZDamageType.EAT) return true;
			else if(((PVZDamageSource) source).getPVZDamageType()==PVZDamageType.DEAD) return true;
		}
		return super.isEntityInvulnerable(source);
	}
	
	/**
	 * 两个端的开始攻击
	 */
	protected abstract void performAttack();
	
	@Override
	public int getSuperTimeLength() {
		return 2;
	}
	
	@Override
    protected void initEntityAI()
    {
    }
}
