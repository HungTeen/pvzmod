package com.hungteen.pvzmod.entities.plants.explosion;

import java.util.Random;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.bullets.EntityPotato;
import com.hungteen.pvzmod.entities.plants.base.EntityNearPlantBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityPotatoMine extends EntityNearPlantBase{

	private static final DataParameter<Integer> MINE_STATE = EntityDataManager.createKey(EntityPotatoMine.class, DataSerializers.VARINT);
	private boolean sign_state;
	
	public EntityPotatoMine(World worldIn) {
		super(worldIn);
		sign_state=false;
		this.range=1;
		this.setSize(0.6f,0.4f);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(MINE_STATE, MineState.PRE.ordinal());
	}
	
	@Override
	public void onNormalPlantUpdate() {
		if(this.getAttackTime()%this.getSignChangeTime()==0) {//红灯闪烁
			this.sign_state=!this.sign_state;
		}
		if(this.isPlantInSuperMode()) {//优先看是否放大招
			if(this.getMineState()==MineState.PRE) {//准备中则直接钻出地面
				this.outDirt();
			}
			performShoot();
		}
		this.setAttackTime(this.getAttackTime()+1);
		if(this.getAttackTime()>=this.getReadyTime()&&this.getMineState()==MineState.PRE) {//满足钻出地表的条件
			this.outDirt();
		}
		super.onNormalPlantUpdate();//再爆炸
	}
	
	@Override
	protected void performAttack() {
		if(this.getMineState()==MineState.READY) {//准备状态才能炸
			if(!this.world.isRemote) {
				AxisAlignedBB aabb=new AxisAlignedBB(this.posX-range, this.posY-range, this.posZ-range, this.posX+range, this.posY+range, this.posZ+range);
				for(EntityLivingBase target:EntityUtil.getEntityAttackableTarget(this, aabb)){
					target.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
				}
				this.playSound(SoundsHandler.POTATOMINE_EXPLODE, 1f, 1f);
			}
			for(int i=1;i<=5;i++) {
				Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX, this.posY, this.posZ, (rand.nextFloat()-0.5)/4,0.4d,(rand.nextFloat()-0.5)/4);
			    Main.proxy.spawnParticle(PVZParticleType.POTATO_BOMB, this.posX, this.posY, this.posZ, 0, 0, 0);
			    Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX, this.posY, this.posZ, (rand.nextFloat()-0.5)/4,0.4d,(rand.nextFloat()-0.5)/4);
			}
			this.setDead();
		}
	}
	
	private void outDirt()
	{
		if(!this.world.isRemote) {
			this.setMineState(MineState.READY);
		}
		for(int i=1;i<=5;i++) {
			Random rand=new Random();
	  	    Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX+0.5d, this.posY, this.posZ+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
		    Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX+0.5d, this.posY, this.posZ-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
		    Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX-0.5d, this.posY, this.posZ+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
		    Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX-0.5d, this.posY, this.posZ-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
		}
	}

	private void performShoot()
	{
		int lvl=this.getPlantLvl();
		if(lvl>=1) this.shootPotato(0.4f);
		if(lvl>=7) this.shootPotato(0.7f);
		if(lvl>=14) this.shootPotato(1.1f);
	}
	
	private void shootPotato(float r)
	{
		if(!this.world.isRemote) {
		    EntityPotato potato=new EntityPotato(this.world,this);
		    potato.setPosition(this.posX, this.posY+1, this.posZ);
		    float dx=(this.getRNG().nextFloat()-0.5f)*r;
		    float dy=1;
		    float dz=(this.getRNG().nextFloat()-0.5f)*r;
		    potato.shoot(dx, dy, dz);
		    this.world.spawnEntity(potato);
		}
	}
	
	protected int getSignChangeTime()
	{
		if(this.getMineState()==MineState.PRE) {
			return 20;
		}
		else {
			return 10;
		}
	}
	
	private int getReadyTime()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 240-20*now;
		}
		return 240;
	}
    
    @Override
	public float getAttackDamage() {
		int lvl=this.getPlantLvl();
		if(lvl>=1&&lvl<=6) return 150;
		else if(lvl>=7&&lvl<=13) return 180;
		else if(lvl<=20) return 225;
		return 150;
	}
    
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("mine_state", this.getMineState().ordinal());
        compound.setBoolean("sign_state", this.sign_state);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compund)
    {
        super.readEntityFromNBT(compund);
        this.setMineState(MineState.values()[compund.getInteger("mine_state")]);
        this.sign_state=compund.getBoolean("sign_state");
    }
    
    public void setMineState(MineState state)
    {
    	this.dataManager.set(MINE_STATE, state.ordinal());
    }
    
    public MineState getMineState()
    {
    	return MineState.values()[this.dataManager.get(MINE_STATE)];
    }
    
    public boolean getSignState()
    {
    	return this.sign_state;
    }
    
	@Override
	public Plants getPlantEnumName() {
		return Plants.POTATO_MINE;
	}

	public enum MineState{
		PRE,
		READY
	}
}
