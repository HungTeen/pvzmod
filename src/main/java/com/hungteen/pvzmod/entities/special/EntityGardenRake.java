package com.hungteen.pvzmod.entities.special;

import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.ai.target.PVZAIPlantGlobalTarget;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGardenRake extends EntityCreature{

	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(EntityGardenRake.class, DataSerializers.VARINT);
	private static final DataParameter<String> OWNER_NAME = EntityDataManager.createKey(EntityGardenRake.class, DataSerializers.STRING);
	
	public EntityGardenRake(World worldIn) {
		super(worldIn);
		setEntityInvulnerable(true);
		this.setSize(0.4f, 0.1f);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(ATTACK_TIME, 0);
		dataManager.register(OWNER_NAME, "");
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.world.isRemote) return ;
		EntityLivingBase target=this.getAttackTarget();
		if(target!=null) {
			if(target instanceof EntityZombieBase) {
				target.setPosition(posX, posY, posZ);
				this.setAttackTime(this.getAttackTime()+1);
				if(this.getAttackTime()==10) {
			        this.setDead();
			        target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this,this), 200);
		        }
			}
			else this.setAttackTime(0);
		}
		else this.setAttackTime(0);
		
	}
	
	@Override
	protected void collideWithNearbyEntities() {
		
	}
	
	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		this.targetTasks.addTask(10, new PVZAIPlantGlobalTarget(this, 2, 2));
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(2.0D);
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setAttackTime(compound.getInteger("attack_time"));
		if(!this.getOwnerName().equals(""))
        {
            compound.setString("ownerName", this.getOwnerName());
        }
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("attack_time", this.getAttackTime());
		 if (compound.hasKey("ownerName")){
	            this.setOwnerName(compound.getString("ownerName"));
	        }
	        else {
	        	this.setOwnerName("");
	        }
	}
	
	@Nullable
    public String getOwnerName()
    {
        return dataManager.get(OWNER_NAME);
    }

    public void setOwnerName(String name)
    {
        this.dataManager.set(OWNER_NAME, name);
    }
    
	public int getAttackTime()
	{
		return dataManager.get(ATTACK_TIME);
	}
	
	public void setAttackTime(int time)
	{
		dataManager.set(ATTACK_TIME, time);
	}
}
