package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.ai.PVZAIGargantuarAttack;
import com.hungteen.pvzmod.entities.ai.target.PVZAIZombieGlobalTarget;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeRock;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeWeed;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityGargantuar extends EntityZombieBase{

	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(EntityGargantuar.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> THROW_TIME = EntityDataManager.createKey(EntityGargantuar.class, DataSerializers.VARINT);
	private Entity rider=null;
	
	public EntityGargantuar(World worldIn) {
		super(worldIn);
		setSize(2f, 5f);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(ATTACK_TIME, 0);
		dataManager.register(THROW_TIME, 0);
	}
	
	@Override
	protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(10, new PVZAIGargantuarAttack(this, 1.0,false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 2.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        initAITargetTask();
    }
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		int rand=this.getRNG().nextInt(3);
		if(rand==0) {
		    this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Item.getItemFromBlock(BlockRegister.POLE)));
		}else if(rand==1) {
			this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Item.getItemFromBlock(BlockRegister.ZOMBIE_DOLL)));
		}else if(rand==2) {
			this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Item.getItemFromBlock(BlockRegister.WARNING_SIGN)));
		}
		if(!this.world.isRemote) {
			EntityImp imp=new EntityImp(world);
			imp.setLocationAndAngles(posX, posY+2, posZ, rotationYaw,rotationPitch);
			world.spawnEntity(imp);
			imp.startRiding(this);
		}
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(EntityUtil.ZOMBIE_GIANT_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.VERY_SLOW_SPEED);
	}
	
	protected boolean dealAttackDamage(float damage,Entity target)
	{
		if(target instanceof EntityPlayer) {
			return target.attackEntityFrom(PVZDamageSource.causeDeadDamage(this, this), damage);
		}
		return target.attackEntityFrom(PVZDamageSource.causeDeadDamage(this, this), damage);
	}
	
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.world.isRemote&&this.getThrowTime()>0) {//正在扔
			this.setThrowTime(this.getThrowTime()+1);
			if(this.getThrowTime()>=this.getThrowTick()/2&&this.rider!=null) {
				this.rider.dismountRidingEntity();
				if(this.getAttackTarget()!=null) {
					Entity target=this.getAttackTarget();
					double dis=Math.sqrt((target.posX-rider.posX)*(target.posX-rider.posX)+(target.posZ-rider.posZ)*(target.posZ-rider.posZ));
					rider.motionX=(target.posX-rider.posX)/dis*2;
					rider.motionY=0.6f;
					rider.motionZ=(target.posZ-rider.posZ)/dis*2;
					//System.out.println(dis);
				}else {
					rider.motionX=(this.getRNG().nextFloat()-0.5f);
					rider.motionY=0.6f;
					rider.motionZ=(this.getRNG().nextFloat()-0.5f);
				}
				this.world.playSound(null, posX, posY, posZ, SoundsHandler.IMP, SoundCategory.VOICE, 1f,1f);
				this.rider=null;
			}
			if(this.getThrowTime()==this.getThrowTick()) {
				this.setThrowTime(0);
			}
			return ;
		}
		if(!this.world.isRemote&&this.getHealth()<=this.getMaxHealth()/2&&this.getThrowTime()==0&&!this.getPassengers().isEmpty()) {//扔
			//System.out.println(this.getRidingEntity());
			this.rider=this.getPassengers().get(0);
			this.setThrowTime(1);
			return ;
		}
		if(!this.world.isRemote) {
			if(this.getAttackTarget()!=null) {//有目标
				EntityLivingBase target=this.getAttackTarget();
				if(this.getAttackTime()<this.getAttackTick()) {//但是还在蓄力
				    if(this.getDistanceSq(target)<=(this.width/2+target.width/2+1)*(this.width/2+target.width/2+1)) {//如果满足攻击条件，则继续蓄力
					    this.setAttackTime(this.getAttackTime()+1);
				    }
				    else {//否则归零
				    	this.setAttackTime(0);
				    }
				}else {//蓄力完毕
					this.setAttackTime(0);
					target.attackEntityFrom(PVZDamageSource.causeDeadDamage(this, this), target.getHealth());
					this.world.playSound(null, posX, posY, posZ, SoundsHandler.GROUND_SHAKE, SoundCategory.VOICE, 1f,1f);
				}
			}else {
				this.setAttackTime(0);
			}
		}
	}
	
	@Override
	public void updatePassenger(Entity passenger)
    {
        if (this.isPassenger(passenger))
        {
        	float len=1.2f;
        	float j=2*3.14159f*this.rotationYaw/360;
            passenger.setPosition(this.posX+Math.sin(j)*len, this.posY + this.getMountedYOffset() + passenger.getYOffset()+0.7, this.posZ-Math.cos(j)*len);
        }
    }
	
	@Override
	public int getAttackTick() {
		return 50;
	}
	
	public int getThrowTick()
	{
		return 50;
	}
	
	@Override
	public boolean shouldDismountInWater(Entity rider) {
		return false;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compund) {
		super.readEntityFromNBT(compund);
		this.setAttackTime(compund.getInteger("zombie_attack_time"));
		this.setThrowTime(compund.getInteger("zombie_throw_time"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("zombie_attack_time", this.getAttackTime());
		compound.setInteger("zombie_throw_time", this.getThrowTime());
	}
	
	public int getAttackTime()
	{
	    return dataManager.get(ATTACK_TIME);
	}
	    
	public void setAttackTime(int cd)
	{
	    dataManager.set(ATTACK_TIME, cd);
	}
	
	public int getThrowTime()
	{
	    return dataManager.get(THROW_TIME);
	}
	    
	public void setThrowTime(int cd)
	{
	    dataManager.set(THROW_TIME, cd);
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsHandler.GAR_AMBIENT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsHandler.GAR_DIE;
	}
}
