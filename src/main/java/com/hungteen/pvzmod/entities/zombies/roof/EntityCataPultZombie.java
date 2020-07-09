package com.hungteen.pvzmod.entities.zombies.roof;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.ai.attack.PVZAIZombiePultAttack;
import com.hungteen.pvzmod.entities.bullets.EntityBall;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityCataPultZombie extends EntityZombieBase implements IRangedAttackMob{

	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(EntityCataPultZombie.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> IS_ATTACKED = EntityDataManager.createKey(EntityCataPultZombie.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_OUT = EntityDataManager.createKey(EntityCataPultZombie.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> CAN_ATTACK_NOW = EntityDataManager.createKey(EntityCataPultZombie.class, DataSerializers.BOOLEAN);
	private float LENTH=0.1f;
	
	public EntityCataPultZombie(World worldIn) {
		super(worldIn);
		this.setSize(1.5f, 2f);
	}

	@Override
	protected void setSmallSize() {
		this.setSize(0.6f, 0.5f);
	}
	
	@Override
	protected Type getType() {
		return Type.NORMAL;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(ATTACK_TIME, 0);
		dataManager.register(IS_ATTACKED, false);
		dataManager.register(IS_OUT, false);
		dataManager.register(CAN_ATTACK_NOW, false);
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
//        this.tasks.addTask(2, new EntityAIZombieEat(this, 1.0, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
	    this.tasks.addTask(10, new PVZAIZombiePultAttack(this, 50.0F));
	    initAITargetTask();
	}
	
	@Override
	public float getLife() {
		return 90;
	}
	
	protected boolean dealAttackDamage(float damage,Entity target)
	{
		if(target instanceof EntityLivingBase) {
		    return target.attackEntityFrom(PVZDamageSource.causeDeadDamage(this, this), ((EntityLivingBase) target).getMaxHealth());
		}
		return false;
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.BIT_SLOW);
    }
	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("zombie_attack_time", this.getAttackTime());
        compound.setBoolean("isAttacked", this.getIsAttacked());
        compound.setBoolean("isOut", this.getIsOut());
        compound.setBoolean("canAttackNow", this.getCanAttackNow());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compund)
    {
        super.readEntityFromNBT(compund);
        this.setAttackTime(compund.getInteger("zombie_attack_time"));
        this.setIsAttacked(compund.getBoolean("isAttacked"));
        this.setIsOut(compund.getBoolean("isOut"));
        this.setCanAttackNow(compund.getBoolean("canAttackNow"));
    }
    
    @Override
	public void onNormalZombieUpdate(){
		super.onNormalZombieUpdate();
		if(this.getAttackTarget()!=null) {
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
		}
		else {
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.BIT_SLOW);
		}
		//System.out.println(this.getAttackTime()+" "+this.getCanAttackNow());
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
        EntityBall cabbage = new EntityBall(world,this);
        cabbage.setPosition(this.posX-deltaX,this.posY+1.7f,this.posZ-deltaZ);
        //System.out.println(target.posX+" "+target.posY+" "+target.posZ);
        cabbage.shoot(target.posX-cabbage.posX, target.posY-cabbage.posY+0.5, target.posZ-cabbage.posZ);
        this.playSound(SoundsHandler.BALL_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(cabbage);
    }
    
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
		this.setIsAttacked(true);
		this.setAttackTarget(target);
    }
    
    public int getPultSpeed()
    {
    	return 50;
    }
    
    public int getAttackTime()
	{
	    return dataManager.get(ATTACK_TIME);
	}
	    
	public void setAttackTime(int cd)
	{
	    dataManager.set(ATTACK_TIME, cd);
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

	@Override
	public void setSwingingArms(boolean swingingArms) {
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.CATAPULT_ZOMBIE;
	}
}
