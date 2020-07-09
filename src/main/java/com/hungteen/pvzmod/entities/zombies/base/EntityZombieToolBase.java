package com.hungteen.pvzmod.entities.zombies.base;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase.Type;
import com.hungteen.pvzmod.entities.zombies.special.EntityDuckyTube;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityZombieToolBase extends EntityZombieBase{

	private static final DataParameter<Integer> LIVE_TIME = EntityDataManager.createKey(EntityZombieToolBase.class, DataSerializers.VARINT);
	private final int MAX_LIVE_TIME = 5;
	
	public EntityZombieToolBase(World worldIn) {
		super(worldIn);
	}

	@Override
	protected Type getType() {
		return Type.NORMAL;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(LIVE_TIME, 0);
	}
	
	@Override
	protected void initEntityAI()
    {
//        this.tasks.addTask(5, new EntityAILookIdle(this));
        initAITargetTask();
    }
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.world.isRemote) {
			if(this.getPassengers().isEmpty()) {
			    this.setLiveTime(this.getLiveTime()+1);
			}else {
				this.setLiveTime(0);
			}
			if(this.getLiveTime()>=MAX_LIVE_TIME) {
			    this.setDead();
			}
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compund) {
		super.readEntityFromNBT(compund);
		this.setLiveTime(compund.getInteger("water_live_time"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("water_live_time", this.getLiveTime());
	}
	
	public int getLiveTime()
	{
		return dataManager.get(LIVE_TIME);
	}
	
	public void setLiveTime(int time)
	{
		dataManager.set(LIVE_TIME, time);
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return source != DamageSource.OUT_OF_WORLD;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TOOL;
	}
}
