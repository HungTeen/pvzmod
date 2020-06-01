package com.hungteen.pvzmod.entities.zombies.special;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.zombies.EntityGargantuar;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityJackOutBoxZombie extends EntityZombieBase{

	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(EntityGargantuar.class, DataSerializers.VARINT);
	
	public EntityJackOutBoxZombie(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void initEntityAI() {
	}
	
	@Override
	protected Type getType() {
		return Type.NORMAL;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(ATTACK_TIME, 0);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
	}
	
//	@Override
//	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
//		if(!this.world.isRemote) {
//			this.world.playSound(null, posX, posY, posZ, soundIn, category, volume, pitch);
//		}
//		return super.onInitialSpawn(difficulty, livingdata);
//	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.getAttackTime()>=this.getAttackCD()) {
		    if(!this.world.isRemote) {
			    performExp();
		        this.setDead();
		        this.setAttackTime(0);
		    }
		    this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE,posX,posY,posZ,0,0,0);
	    }
		if(!this.world.isRemote) {
		   	this.setAttackTime(this.getAttackTime()+1);
		}
	}
	
	private void performExp()
	{
		float len=getExpLen();
		AxisAlignedBB aabb=new AxisAlignedBB(posX-len, posY-len, posZ-len, posX+len, posY+len, posZ+len);
		for(Entity entity:EntityUtil.getEntityAttackableTarget(this, aabb)) {
				entity.attackEntityFrom(PVZDamageSource.causeMotalDamage(this, this),((EntityLivingBase) entity).getHealth()*2);
		}
		this.world.playSound(null, posX,posY,posZ, SoundsHandler.CAR_EXPLOSION, SoundCategory.AMBIENT, 1f,1f);
	}
	
	private float getExpLen()
	{
		return 3f;
	}
	
	private int getAttackCD()
	{
		return 20;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compund) {
		super.readEntityFromNBT(compund);
		this.setAttackTime(compund.getInteger("zombie_attack_time"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("zombie_attack_time", this.getAttackTime());
	}
	
	public int getAttackTime()
	{
	    return dataManager.get(ATTACK_TIME);
	}
	    
	public void setAttackTime(int cd)
	{
	    dataManager.set(ATTACK_TIME, cd);
	}
	
}
