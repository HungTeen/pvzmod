package com.hungteen.pvzmod.entities.bullets;

import com.hungteen.pvzmod.entities.drops.EntityCoin;
import com.hungteen.pvzmod.entities.drops.EntityPlantFood;
import com.hungteen.pvzmod.entities.drops.EntitySun;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.flame.EntityTorchWood;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.special.EntityDuckyTube;
import com.hungteen.pvzmod.util.ConfigurationUtil;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public abstract class PVZThrowable extends EntityThrowable implements IThrowableEntity{

	protected EntityLivingBase shooter;
	
	public PVZThrowable(World worldIn) {
		super(worldIn);
	}
	
	public PVZThrowable(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
        this.shooter=throwerIn;
    }
    
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.ticksExisted>=200) this.setDead(); //10s必死
//		EntityTameable
	}
	
	/**
	 * 能否攻击这个目标
	 */
	protected boolean checkCanAttack(Entity target)
	{
		
		if(ConfigurationUtil.MainConfig.damageSettings.canHurtFriendByAccident) {
			if(!target.isEntityEqual(getThrower())) {
				return true;
			}
		}
		return EntityUtil.checkCanEntityAttack(getThrower(), target);
	}
	
	/**
	 * 检测是否能继续前进
	 */
    protected boolean checkLive(RayTraceResult result)
    {
    	Entity target=result.entityHit;
    	if(result.typeOfHit==RayTraceResult.Type.ENTITY) {//打到实体
        	if(!ConfigurationUtil.MainConfig.damageSettings.canBulletPassEntity){//不能穿过实体必死
        		return false;
        	}
        	if(EntityUtil.checkCanEntityAttack(getThrower(), target)){
        		return false;
        	}
        	return true;
//        	if(target instanceof EntityCoin||target instanceof EntitySun||target instanceof EntityPlantFood) {//可穿过掉落物
//        		return true;
//        	}
        }
    	return false;
    }
	
    /**
     * 抛物线的shoot
     */
    public void shoot(double dx,double dy,double dz){
    	Entity target=null;
    	if(this.getThrower() !=null) {
    		target=((EntityLiving) this.getThrower()).getAttackTarget();
    	}
    	else {
    		System.out.println("thrower error!");
    		return ;
    	}
    	if(target==null) {
    		return ;
    	}
    	double h=this.getThrowHeight(dy);
    	double g=this.getGravityVelocity();
    	double t1=MathHelper.sqrt(2*h/g);
    	double t2=MathHelper.sqrt(2*(h-dy)/g);
    	dx+=target.motionX*(t1+t2);
    	dz+=target.motionZ*(t1+t2);
    	double dxz=MathHelper.sqrt(dx*dx+dz*dz);
    	double vxz=dxz/(t1+t2);
    	double vy=g*t1;
    	this.motionX=vxz*dx/dxz;
    	this.motionY=vy;
    	this.motionZ=vxz*dz/dxz;
    }
    
    @Override
    public void setThrower(Entity entity)
    {
        this.shooter = (EntityLivingBase) entity;
    }
   
    @Override
    public EntityLivingBase getThrower()
    {
        return this.shooter;
    }
    
    protected double getThrowHeight(double y)
    {
    	return Math.floor(y)/10*10+10;
    }
}
