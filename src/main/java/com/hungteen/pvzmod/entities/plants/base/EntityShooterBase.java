package com.hungteen.pvzmod.entities.plants.base;

import com.hungteen.pvzmod.entities.ai.attack.PVZAIShooterAttack;
import com.hungteen.pvzmod.entities.ai.target.PVZAIShooterTarget;
import com.hungteen.pvzmod.util.interfaces.IShooter;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityShooterBase extends EntityPlantBase implements IShooter{
	
	public EntityShooterBase(World worldIn) {
		super(worldIn);
	}
	
	@Override
    protected void initEntityAI()
    {
		this.tasks.addTask(0, new PVZAIShooterAttack(this)); //3
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 2F)); //2
        this.targetTasks.addTask(0, new PVZAIShooterTarget(this,2,40)); //1
    }
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
    }
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(!this.world.isRemote) {
		    if(this.getAttackTime()>0) {
			    this.shootBullet();
			    this.setAttackTime(this.getAttackTime()-1);
			}
		}
	}
	
	@Override
	public float getAttackDamage()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=13) return 1.5f+lvl*0.5f;
		else if(lvl<=20) return (lvl-13)+8f;
		return 2f;
	}

	public float getBulletSpeed()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 1.2f;
		else if(lvl<=13) return 1.6f;
		else if(lvl<=20) return 2.2f;
		return 1.2f;
	}
	
	public int getShootSpeed()
	{
		if(this.isPlantInSuperMode()) {
			return 1;
		}
		return 30;
	}
	
	public static boolean checkY(double dy)
	{
		return dy>=-1&&dy<=1.5;
	}
}
