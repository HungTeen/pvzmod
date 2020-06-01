package com.hungteen.pvzmod.entities.plants.base;

import com.hungteen.pvzmod.entities.ai.attack.PVZAIPultAttack;
import com.hungteen.pvzmod.entities.ai.target.PVZAIPlantGlobalTarget;
import com.hungteen.pvzmod.entities.bullets.EntityCabbage;
import com.hungteen.pvzmod.entities.bullets.EntityPult;
import com.hungteen.pvzmod.entities.plants.common.EntityCabbagePult;
import com.hungteen.pvzmod.util.interfaces.IPult;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class EntityPultBase extends EntityPlantBase implements IPult{

	private static final DataParameter<Boolean> IS_ATTACKED = EntityDataManager.createKey(EntityCabbagePult.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_OUT = EntityDataManager.createKey(EntityCabbagePult.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> CAN_ATTACK_NOW = EntityDataManager.createKey(EntityCabbagePult.class, DataSerializers.BOOLEAN);
	private final double LENTH=0.5f;
	
	public EntityPultBase(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(IS_ATTACKED, false);
		dataManager.register(IS_OUT, false);
		dataManager.register(CAN_ATTACK_NOW, false);
	}
	
	@Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new PVZAIPultAttack(this));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 1.5F));
        this.targetTasks.addTask(0, new PVZAIPlantGlobalTarget(this, 50, 50));
    }

	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
    }
	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("isAttacked", this.getIsAttacked());
        compound.setBoolean("isOut", this.getIsOut());
        compound.setBoolean("canAttackNow", this.getCanAttackNow());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compund)
    {
        super.readEntityFromNBT(compund);
        this.setIsAttacked(compund.getBoolean("isAttacked"));
        this.setIsOut(compund.getBoolean("isOut"));
        this.setCanAttackNow(compund.getBoolean("canAttackNow"));
    }
    
    @Override
	public void onNormalPlantUpdate(){
		super.onNormalPlantUpdate();
		//System.out.println(this.getAttackTime()+" "+this.getCanAttackNow());
//		if(this.ticksExisted%40==0) {
//			System.out.println(this.getAttackTarget());
//		}
		if(this.getIsAttacked()) {//开始攻击
			this.setAttackTime(0);
			this.setCanAttackNow(true);
			this.setIsAttacked(false); //已经开始攻击了
		}
		if(this.getCanAttackNow()) {
			if(this.getAttackTime()>=20) {
				this.setCanAttackNow(false);
				this.setAttackTime(0);
				this.setIsOut(false);
				if(!this.world.isRemote) {
				    this.changeBullet();
				}
			}
			else if(this.getAttackTime()==8) {
				this.setIsOut(true);
				if(!this.world.isRemote) {
					this.throwBullet();
				}
			}
		}
		this.setAttackTime(this.getAttackTime()+1);
	}
    
    protected void changeBullet()
    {
    }
    
    private void throwBullet()
    {
    	if(this.getAttackTarget()==null) return ;
		EntityLivingBase target=this.getAttackTarget();
		double dx = target.posX - this.posX;
        double dz = target.posZ - this.posZ;
        double dis =MathHelper.sqrt(dx * dx + dz * dz);
        double tmp=this.LENTH/dis;
        double deltaX=tmp*dx;
        double deltaZ=tmp*dz;
        EntityPult cabbage = this.getBullet();
        cabbage.setPosition(this.posX-deltaX,this.posY+1.7f,this.posZ-deltaZ);
        //System.out.println(target.posX+" "+target.posY+" "+target.posZ);
        cabbage.shoot(target.posX-cabbage.posX, target.posY-cabbage.posY+0.5, target.posZ-cabbage.posZ);
        this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(cabbage);
    }

    protected abstract EntityPult getBullet();
    
    @Override
    public void startPultAttack()
    {
		this.setIsAttacked(true);
    }
    
    public void setIsAttacked(boolean isAttacked)
	{
		dataManager.set(IS_ATTACKED, isAttacked);
	}
	
	public boolean getIsAttacked()
	{
		return dataManager.get(IS_ATTACKED);
	}
	
	public void setIsOut(boolean isOut)
	{
		dataManager.set(IS_OUT, isOut);
	}
	
	public boolean getIsOut()
	{
		return dataManager.get(IS_OUT);
	}
	
	public void setCanAttackNow(boolean canAttackNow)
	{
		dataManager.set(CAN_ATTACK_NOW, canAttackNow);
	}
	
	public boolean getCanAttackNow()
	{
		return dataManager.get(CAN_ATTACK_NOW);
	}

	public int getRange()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 15;
		else if(lvl<=13) return 20;
		else if(lvl<=20) return 30;
		return 15;
	}
	
	public int getPultSpeed()
	{
//		int lvl=this.getPlantLvl();
//		if(lvl<=20) {
//			int now=(lvl-1)/4;
//			return 60-5*now;
//		}
		return 60;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 100;
	}
}
