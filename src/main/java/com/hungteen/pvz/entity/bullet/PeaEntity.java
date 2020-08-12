package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.interfaces.IIcePlant;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class PeaEntity extends PVZThrowableEntity{

	private static final DataParameter<Integer> PEA_STATE =EntityDataManager.createKey(PeaEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> PEA_TYPE =EntityDataManager.createKey(PeaEntity.class, DataSerializers.VARINT);
//	private EntityTorchWood torchWood=null;
	
	public PeaEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public PeaEntity(EntityType<? extends ThrowableEntity> type, World worldIn, LivingEntity shooter,Type peaType,State peaState) {
		super(type, worldIn, shooter);
		this.setPeaState(peaState);
        this.setPeaType(peaType);
        this.recalculateSize();
	}
	
	@Override
	protected void registerData() {
		dataManager.register(PEA_STATE, State.NORMAL.ordinal());
		dataManager.register(PEA_TYPE, Type.NORMAL.ordinal());
	}

	public void shootPea(double dx,double dz,double speed)
	{
		double dv=Math.sqrt(dx*dx+dz*dz);
		double vx=dx/dv*speed;
		double vz=dz/dv*speed;
		this.setMotion(vx, this.getMotion().y, vz);
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
        if (result.getType()==RayTraceResult.Type.ENTITY)
        {
        	Entity target = ((EntityRayTraceResult)result).getEntity();
        	if(checkCanAttack(target)) {
        		target.hurtResistantTime=0;
        		if(this.getPeaState()==State.NORMAL) {
        		    //System.out.println(this.getThrower());
                    target.attackEntityFrom(PVZDamageSource.causeAppeaseDamage(this, this.getThrower()), this.getAttackDamage());//damage
        	    }
        		else if(this.getPeaState()==State.ICE) {
        			target.attackEntityFrom(PVZDamageSource.causeIceDamage(this, this.getThrower()), this.getAttackDamage());
        			LivingEntity owner = this.getThrower();
        			if(owner instanceof IIcePlant&&target instanceof LivingEntity) {
        				((LivingEntity)target).addPotionEffect(((IIcePlant) owner).getColdEffect());
        				((LivingEntity)target).addPotionEffect(((IIcePlant) owner).getFrozenEffect());
        			}
        		}
        	}
        	
//        	else if(this.getPeaState()==State.SNOW) {
//        		if(target instanceof EntityZombieBase) {
//        			if(!this.world.isRemote&&!((EntityZombieBase) target).getIsCold()) {
//						this.playSound(SoundsHandler.FROZEN_PEA, 4, 1);
//        			}
//        		}
//        		target.attackEntityFrom(PVZDamageSource.causeSnowDamage(this, this.getThrower()), this.getAttackDamage());//damage
//        	}
//        	else if(this.getPeaState()==State.FIRE||this.getPeaState()==State.BLUE_FIRE) {
//        		if(!this.world.isRemote) {
//					this.playSound(SoundsHandler.FIRE_PEA, 4, 1);
//        		}
//        		target.attackEntityFrom(PVZDamageSource.causeFireDamage(this, this.getThrower()), this.getAttackDamage());//damage
//        	}
//        	else if(this.getPeaState()==State.ELECTRICITY) {
//        		mob.attackEntityFrom(PVZDamageSource.causeElectricityDamage(this, this.getThrower()), this.getAttackDamage());//damage
//        	}
        }
//        if(!this.world.isRemote&&target instanceof EntityTorchWood) {//火炬树桩和豌豆的互动
//        	EntityTorchWood tmp=(EntityTorchWood) target;
//        	if(this.torchWood==null||!this.torchWood.isEntityEqual(tmp)) {//是否为之前那个火炬树桩[防止一个火炬树桩互动两次]
//        		this.torchWood=tmp;
//        	    if(this.torchWood.isPlantInSuperMode()) {//蓝火
//        		    if(this.getPeaState()==State.SNOW) {//冰豌豆变火豌豆
//        			    this.setPeaState(State.FIRE);
//        		    }
//        		    else if(this.getPeaState().ordinal()<State.BLUE_FIRE.ordinal()) {//豌豆、火豌豆变蓝焰豌豆
//        			    this.setPeaState(State.BLUE_FIRE);
//        		    }
//        	    }
//        	    else {//黄火
//        		    if(this.getPeaState()==State.SNOW) {
//        			    this.setPeaState(State.NORMAL);
//        		    }
//        		    else if(this.getPeaState()==State.NORMAL) {
//        			    this.setPeaState(State.FIRE);
//        		    }
//        	    }
//        	}
//        }
        if (!this.world.isRemote)
        {
//        	if(result.entityHit instanceof Entity) {
//        		System.out.println(result.entityHit);
//        	}
            this.world.setEntityState(this, (byte)3);
            if(!this.checkLive(result)) this.remove();
        }
	}
	
	private float getAttackDamage()
	{
		float damage=0;
		
		//伤害先看主人
		if(this.getThrower() instanceof PVZPlantEntity) {
			damage=((PVZPlantEntity) this.getThrower()).getAttackDamage();
		}
//		else if(this.shooter instanceof EntityZombieBase) {//植物僵尸的攻击，后期要增加！
//			damage=3;
//		}
//		else if(this.shooter instanceof EntityPlayer) {
//			damage=4;
//		}
		
		//然后大小判断
		if(this.getPeaType()==Type.BIG) {
			damage+=20f;
		}
		else if(this.getPeaType()==Type.HUGE) {
			damage+=75f;
		}
		
		//然后火焰翻倍
		if(this.getPeaState()==State.FIRE) {
			damage*=2;
		}
		else if(this.getPeaState()==State.BLUE_FIRE) {
			damage*=3;
		}
		return damage;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		if(this.getPeaType()==Type.NORMAL) return new EntitySize(0.2f,0.2f,false);
        if(this.getPeaType()==Type.BIG) return new EntitySize(0.4f,0.4f,false);
        if(this.getPeaType()==Type.HUGE) return new EntitySize(0.6f,0.6f,false);
		return new EntitySize(0.2f,0.2f,false);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("peaState", this.getPeaState().ordinal());
        compound.putInt("peaType", this.getPeaType().ordinal());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setPeaState(State.values()[compound.getInt("peaState")]);
        this.setPeaType(Type.values()[compound.getInt("peaType")]);
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.002f;
	}
	
	public State getPeaState()
	{
		return State.values()[dataManager.get(PEA_STATE)];
	}
	
	public void setPeaState(State state)
	{
		dataManager.set(PEA_STATE, state.ordinal());
	}
	
	public Type getPeaType()
	{
		return Type.values()[dataManager.get(PEA_TYPE)];
	}
	
	public void setPeaType(Type type)
	{
		dataManager.set(PEA_TYPE, type.ordinal());
	}
	
	@Override
	public ItemStack getItem() {
		if(this.getPeaState()==State.NORMAL) return new ItemStack(ItemRegister.PEA.get());
		if(this.getPeaState()==State.ICE) return new ItemStack(ItemRegister.SNOW_PEA.get());
		if(this.getPeaState()==State.FIRE) return new ItemStack(ItemRegister.FLAME_PEA.get());
		return new ItemStack(ItemRegister.PEA.get());
	}

	public enum Type{
		NORMAL,//一般
		BIG,//大豌豆
		HUGE,//巨大豌豆
	}
	
	public enum State{
		ICE,//冰豌豆
		NORMAL,//一般豌豆
		FIRE,//火豌豆
		BLUE_FIRE,//蓝火豌豆
		ELECTRICITY,//电豌豆
	}

	
}
