package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.ai.target.PVZAIZombieGlobalTarget;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.special.EntityElementBall;
import com.hungteen.pvzmod.entities.zombies.special.EntityNormalDefence;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityZomBoss extends EntityZombieBase implements IEntityMultiPart{

	public EntityNormalDefence body1 = new EntityNormalDefence(this, "body1", 3, 3);
	public EntityNormalDefence body2 = new EntityNormalDefence(this, "body2", 3, 3);
	public EntityNormalDefence body3 = new EntityNormalDefence(this, "body3", 3, 3);
//	public EntityNormalDefence body4 = new EntityNormalDefence(this, "body4", 3, 3);
//	public EntityNormalDefence body5 = new EntityNormalDefence(this, "body5", 3, 3);
	
	private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityZomBoss.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(EntityZomBoss.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> NORMAL_TIME = EntityDataManager.createKey(EntityZomBoss.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> THROW_TIME = EntityDataManager.createKey(EntityZomBoss.class, DataSerializers.VARINT);
	
	public EntityZomBoss(World worldIn) {
		super(worldIn);
		this.setSize(5f, 18f);
	}
	
	@Override
	protected Type getType() {
		return EntityZombieBase.Type.NORMAL;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(STATE, State.NORMAL.ordinal());
		dataManager.register(ATTACK_TIME, 0);
		dataManager.register(NORMAL_TIME, 0);
		dataManager.register(THROW_TIME, 0);
	}
	
	@Override
	public float getLife() {
		return 3000;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.SLOW);
	}
	
	@Override
	protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
//        this.tasks.addTask(5, new EntityAILookIdle(this));
        initAITargetTask();
    }
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		updateBody();
		if(!this.world.isRemote) {
			if(this.getAttackTarget()!=null) {//有目标了
				EntityLivingBase target=this.getAttackTarget();
				this.getLookHelper().setLookPositionWithEntity(target, 30f, 30f);//盯着目标看
				if(this.getAttackTime()>0) {//正在发射元素球
					this.setAttackTime(this.getAttackTime()+1);
					if(this.getAttackTime()>=this.getAttackTick()) {//发射
						//System.out.println(this.getBossState());
						EntityElementBall ball=new EntityElementBall(world,this.getBossState().ordinal());
						ball.setPosition(posX, posY+this.getEyeHeight()+1, posZ);
						this.world.spawnEntity(ball);
						this.setAttackTime(0);
						this.setBossState(State.NORMAL);
					}
				}
				else if(this.getThrowTime()>0) {//丢车子
					
				}else {
					this.setNormalTime(this.getNormalTime()+1);
					if(this.getNormalTime()>=this.getAttackCD()) {//攻击CD达到随机攻击
						int chance=this.getRNG().nextInt(100);
						this.setAttackTime(1);
						int type=this.getRNG().nextInt(2);
						//System.out.println(type);
						this.setBossState(State.values()[type+1]);
						//System.out.println(this.getBossState());
						this.setNormalTime(0);
					}
				}
			}
		}
	}
	
	private void updateBody()
	{
		this.body1.onUpdate();
		this.body1.setPositionAndRotation(posX, posY+4, posZ, rotationYaw, rotationPitch);
		this.body2.onUpdate();
		this.body2.setPositionAndRotation(posX, posY+8, posZ, rotationYaw, rotationPitch);
		this.body3.onUpdate();
		this.body3.setPositionAndRotation(posX, posY+12, posZ, rotationYaw, rotationPitch);
//		this.body4.onUpdate();
//		this.body4.setPositionAndRotation(posX, posY+16, posZ, rotationYaw, rotationPitch);
//		this.body5.onUpdate();
//		this.body5.setPositionAndRotation(posX, posY+20, posZ, rotationYaw, rotationPitch);
	}
	
	public int getAttackCD()
	{
		return 400;
	}
	
	@Override
	public int getAttackTick()
	{
		return 50;
	}
	
	public int getThrowTick()
	{
		return 50;
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	public boolean getCanBeInvis() {
		return false;
	}
	
	@Override
	public boolean getCanBeSmall() {
		return false;
	}
	
	@Override
	public boolean getCanBeButter() {
		return false;
	}
	
	@Override
	public boolean getCanBeFrozen() {
		return false;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compund) {
		super.readEntityFromNBT(compund);
		this.setAttackTime(compund.getInteger("zombie_attack_time"));
		this.setThrowTime(compund.getInteger("zombie_throw_time"));
		this.setNormalTime(compund.getInteger("zombie_normal_time"));
		this.setBossState(State.values()[compund.getInteger("zombie_boss_state")]);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("zombie_attack_time", this.getAttackTime());
		compound.setInteger("zombie_throw_time", this.getThrowTime());
		compound.setInteger("zombie_normal_time", this.getNormalTime());
		compound.setInteger("zombie_boss_state", this.getBossState().ordinal());
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
	
	public int getNormalTime()
	{
	    return dataManager.get(NORMAL_TIME);
	}
	    
	public void setNormalTime(int cd)
	{
	    dataManager.set(NORMAL_TIME, cd);
	}
	
	public State getBossState()
	{
	    return State.values()[dataManager.get(STATE)];
	}
	    
	public void setBossState(State state)
	{
	    dataManager.set(STATE, state.ordinal());
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsHandler.GAR_DIE;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundsHandler.METAL_HURT;
	}

	@Override
	public Entity[] getParts() {
		return new Entity[] {this.body1,this.body2,this.body3};
	}
	
	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public boolean attackEntityFromPart(MultiPartEntityPart dragonPart, DamageSource source, float damage) {
		return this.attackEntityFrom(source,damage);
	}
	
	public enum State{
		NORMAL,
		ICE,
		FLAME,
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.ZOMBOSS;
	}
}
