package com.hungteen.pvz.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.base.PlantCloserEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class PotatoMineEntity extends PlantCloserEntity{

	private static final DataParameter<Boolean> MINE_READY = EntityDataManager.createKey(PotatoMineEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SIGN_STATE = EntityDataManager.createKey(PotatoMineEntity.class, DataSerializers.BOOLEAN);
	private final float bombRange=1.5f;
	
	public PotatoMineEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MINE_READY, false);
		this.dataManager.register(SIGN_STATE, false);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(this.getAttackTime()%this.getSignChangeTime()==0) {
			this.setSignState(!this.getSignState());
		}
		this.setAttackTime(this.getAttackTime()+1);
		if(this.getAttackTime()>=this.getReadyTime()&&!this.getIsMineReady()) {
			this.outDirt();
		}
	}
	
	@Override
	public void performAttack() {
		if(!this.getIsMineReady()) return ;//not ready
		if(!this.world.isRemote) {
			AxisAlignedBB aabb= EntityUtil.getEntityAABB(this, bombRange,bombRange);
			for(LivingEntity target:EntityUtil.getEntityAttackableTarget(this, aabb)) {
				target.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
			}
//			this.playSound(SoundsHandler.POTATOMINE_EXPLODE, 1f, 1f);
		}
	}
	
	@Override
	public void spawnParticle() {
		for(int i=1;i<=5;i++) {
//			System.out.println("111");
//			Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX, this.posY, this.posZ, (rand.nextFloat()-0.5)/4,0.4d,(rand.nextFloat()-0.5)/4);
		    this.world.addParticle(ParticleRegister.YELLOW_BOMB.get(), this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
//		    Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX, this.posY, this.posZ, (rand.nextFloat()-0.5)/4,0.4d,(rand.nextFloat()-0.5)/4);
		}
	}
	
	/**
	 * potato mine get ready now
	 */
	protected void outDirt()
	{
		this.setIsMineReady(true);
		for(int i=0;i<5;i++) {
//			Random rand=new Random();
//	  	    Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX+0.5d, this.posY, this.posZ+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
//		    Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX+0.5d, this.posY, this.posZ-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
//		    Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX-0.5d, this.posY, this.posZ+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
//		    Main.proxy.spawnParticle(PVZParticleType.DIRT_BURSTOUT, this.posX-0.5d, this.posY, this.posZ-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
		}
	}
	
	/**
	 * potato mine prepare time
	 */
	protected int getReadyTime()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 240-now*20;
		}
		return 240;
	}
	
	@Override
	public float getAttackDamage() {
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 140+now*20;
		}
		return 140;
	}
	
	protected int getSignChangeTime()
	{
		if(this.getIsMineReady()) return 10;
		return 20;
	}
	
	@Override
	public float getCloseWidth() {
		return 1f;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.6f, 0.4f, false);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setIsMineReady(compound.getBoolean("is_mine_ready"));
		this.setSignState(compound.getBoolean("sign_state"));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("is_mine_ready", this.getIsMineReady());
		compound.putBoolean("sign_state", this.getSignState());
	}
	
	public void setIsMineReady(boolean is)
    {
    	this.dataManager.set(MINE_READY, is);
    }
    
    public boolean getIsMineReady()
    {
    	return this.dataManager.get(MINE_READY);
    }
    
    public void setSignState(boolean state)
    {
    	this.dataManager.set(SIGN_STATE, state);
    }
    
    public boolean getSignState()
    {
    	return this.dataManager.get(SIGN_STATE);
    }
    
	@Override
	public Plants getPlantEnumName() {
		return Plants.POTATO_MINE;
	}

}
