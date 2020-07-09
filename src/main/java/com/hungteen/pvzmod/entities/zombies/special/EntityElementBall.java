package com.hungteen.pvzmod.entities.zombies.special;

import java.util.Random;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.ai.EntityAIZombieEat;
import com.hungteen.pvzmod.entities.plants.flame.EntityJalapeno;
import com.hungteen.pvzmod.entities.plants.ice.EntityIceShroom;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityElementBall extends EntityZombieBase{

	private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityElementBall.class, DataSerializers.VARINT);
	private final float speed=0.5f;
	
	public EntityElementBall(World worldIn) {
		this(worldIn,new Random().nextInt(2)+1);
	}

	public EntityElementBall(World worldIn,int type) {
		super(worldIn);
		if(type-1<0) {
			System.out.println("element_ball error type!");
			this.setBallState(State.values()[this.getRNG().nextInt(State.values().length)]);
		}else {
		    this.setBallState(State.values()[type-1]);
		}
		this.setSize(4f, 4f);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(STATE, 0);
	}
	
	@Override
	protected void initEntityAI()
    {
		this.tasks.addTask(2, new EntityAIZombieEat(this, 1.0, false));
        initAITargetTask();
    }
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.world.isRemote) {
			if(this.getAttackTarget()!=null) {
				EntityLivingBase target=this.getAttackTarget();
				double dx=target.posX-this.posX;
				double dy=target.posY-this.posY;
				double dz=target.posZ-this.posZ;
				double dis=Math.sqrt(dx*dx+dy*dy+dz*dz);
				this.motionX=dx/dis*this.speed;
				this.motionY=dy/dis*this.speed;
				this.motionZ=dz/dis*this.speed;
			}
			if(this.ticksExisted==600) {
				this.setDead();
			}
		}
	}
	
	public void onUpdate()
    {
        this.noClip = true;
        super.onUpdate();
        this.noClip = false;
    }
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		doExplosion();
		return true;
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		if(source instanceof PVZDamageSource) {
			PVZDamageSource pvzSource = (PVZDamageSource) source;
			Entity owner = pvzSource.getTrueSource();
			if(this.getBallState()==State.FLAME&&owner instanceof EntityIceShroom) {
				this.setDead();
			}
			else if(this.getBallState()==State.ICE&&owner instanceof EntityJalapeno) {
				this.setDead();
			}
		}
		return super.isEntityInvulnerable(source);
	}
	
	private void doExplosion()
	{
//		System.out.println(this.getBallState());
		for(int i=1;i<=100;i++) {
			if(this.getBallState()==State.FLAME) {
//				System.out.println("fire");
				Main.proxy.spawnParticle(PVZParticleType.FLAME, posX, posY, posZ, (this.getRNG().nextFloat()-0.5),(this.getRNG().nextFloat()-0.5),(this.getRNG().nextFloat()-0.5));
			}
			else if(this.getBallState()==State.ICE) {
				Main.proxy.spawnParticle(PVZParticleType.SNOW, posX, posY, posZ, (this.getRNG().nextFloat()-0.5),(this.getRNG().nextFloat()-0.5),(this.getRNG().nextFloat()-0.5));
			}
		}
		if(!this.world.isRemote) {
			AxisAlignedBB aabb=new AxisAlignedBB(posX+3f, posY+3f, posZ+3f, posX-3f, posY-3f, posZ-3f);
			for(Entity target:EntityUtil.getEntityAttackableTarget(this, aabb)) {
				target.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this), ((EntityLivingBase) target).getMaxHealth());
			}
			this.playSound(SoundsHandler.CHERRY_BOMB, 1f, 1f);
		}
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return null;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}
	
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("ball_state", this.getBallState().ordinal());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compund) {
		super.readEntityFromNBT(compund);
		this.setBallState(State.values()[compund.getInteger("ball_state")]);
	}
	
	public void setBallState(State state)
	{
		dataManager.set(STATE, state.ordinal());
	}
	
	public State getBallState()
	{
		return State.values()[dataManager.get(STATE)];
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TOOL;
	}

	@Override
	public float getLife() {
		return 10;
	}
	
	public enum State{
		ICE,
		FLAME,
	}
}
