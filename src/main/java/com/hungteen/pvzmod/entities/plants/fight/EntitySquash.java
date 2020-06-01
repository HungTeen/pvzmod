package com.hungteen.pvzmod.entities.plants.fight;

import java.util.List;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.damage.PVZDamageType;
import com.hungteen.pvzmod.entities.ai.target.PVZAIPlantGlobalTarget;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntitySquash extends EntityPlantBase{

	private static final DataParameter<Integer> EXTRA_CHANCE =EntityDataManager.createKey(EntitySquash.class, DataSerializers.VARINT);
	private final float range=3;
	
	public EntitySquash(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(EXTRA_CHANCE, 0);
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(10, new PVZAIPlantGlobalTarget(this,  2, range));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(3);
	}
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(this.isPlantInSuperMode()) {
			this.setExtraChance(this.getSuperChance());
		}
		EntityLivingBase target=this.getAttackTarget();
		if(target!=null) {
		    if(this.getDistanceSq(target)>EntityUtil.getAttackRange(this, target, range)) {
		    	this.setAttackTarget(null);
		    }
		}
		if(!this.world.isRemote) {
			if(this.getAttackTime()==1) {//跳起来了
				if(EntityUtil.isOnGround(this)) {//着陆
					this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.GROUND_SHAKE, SoundCategory.BLOCKS, 4f, 1f);
					this.dealDamage();
					if(this.getExtraChance()>0) {//有免死机会就免死
						this.setExtraChance(this.getExtraChance()-1);
					}
					else {//否则顺天命
						if(this.getRNG().nextInt(100)>=this.getDeathChance()) {
						    this.setDead();
					    }
					}
					this.setAttackTime(0);
				}
			}else {//在地面
				if(target!=null&&EntityUtil.checkCanEntityAttack(this, target)) {//有目标
					this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.SQUASH_HMM, SoundCategory.VOICE, 4f, 1f);
					this.smash(target);
				}
			}
		}
	}
	
	protected void smash(Entity target)
	{
		int tick=10;
		double x=target.posX+target.motionX*tick;
		double y=target.posY+target.motionY*tick;
		double z=target.posZ+target.motionZ*tick;
		this.setPosition(x, y+target.getEyeHeight()+3, z);
		this.motionY=-0.15;
		this.setAttackTime(1);
	}
	
	protected void dealDamage()
	{
		this.setAttackTime(0);
		AxisAlignedBB aabb=new AxisAlignedBB(this.posX-0.5, this.posY, this.posZ-0.5, this.posX+0.5, this.posY+1, this.posZ+0.5);
		for(Entity entity : EntityUtil.getEntityAttackableTarget(this, aabb)) {
			entity.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this), this.getAttackDamage());
		}
	}
	
	public int getExtraChance()
	{
		return this.dataManager.get(EXTRA_CHANCE);
	}
	
	public void setExtraChance(int chance)
	{
		this.dataManager.set(EXTRA_CHANCE, chance);
	}
	
	protected int getSuperChance()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 2;
		else if(lvl<=13) return 3;
		else if(lvl<=20) return 4;
		return 2;
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		if(source instanceof PVZDamageSource) {
			if(((PVZDamageSource) source).getPVZDamageType()==PVZDamageType.EAT){
				return true;
			}
			if(((PVZDamageSource) source).getPVZDamageType()==PVZDamageType.DEAD){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public float getAttackDamage() {
		int lvl=this.getPlantLvl();
		if(lvl<=18) {
			int now=(lvl-1)/2;
			return 10*now+120;
		}
		else if(lvl<=20) {
			return 225;
		}
		return 130;
	}
	
	protected int getDeathChance()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 10+now*10-1;
		}
		return 9;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 2;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setExtraChance(compound.getInteger("extra_chance"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("extra_zombie", this.getExtraChance());
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SQUASH;
	}
}
