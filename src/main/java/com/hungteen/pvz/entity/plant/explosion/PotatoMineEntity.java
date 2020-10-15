package com.hungteen.pvz.entity.plant.explosion;

import java.util.Random;

import com.hungteen.pvz.entity.bullet.PotatoEntity;
import com.hungteen.pvz.entity.plant.base.PlantCloserEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class PotatoMineEntity extends PlantCloserEntity{

	private static final DataParameter<Boolean> MINE_READY = EntityDataManager.createKey(PotatoMineEntity.class, DataSerializers.BOOLEAN);
	public boolean sign_red = true;
	private final float bombRange = 1.5f;
	
	public PotatoMineEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MINE_READY, false);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(this.getAttackTime() % this.getSignChangeTime() == 0) {
			this.sign_red = !this.sign_red;
		}
		this.setAttackTime(this.getAttackTime() + 1);
		if(this.getAttackTime() >= this.getReadyTime() && !this.isMineReady()) {
			this.outDirt();
		}
		if(this.isPlantInSuperMode() && !this.isMineReady()) {
			this.outDirt();
		}
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		shootPotatos();
	}
	
	/**
	 * shoot some potato to the sky
	 */
	protected void shootPotatos() {
		int lvl = this.getPlantLvl();
		int min = lvl<=13?1:2;
		int max = lvl<=6?2:3;
		int num = this.getRNG().nextInt(max-min)+min;
		for(int i = 1;i <= num;i++) {
			PotatoEntity potato = new PotatoEntity(EntityRegister.POTATO.get(), world, this);
			potato.setPosition(this.getPosX(), this.getPosY() + 1, this.getPosZ());
		    float dx = (this.getRNG().nextFloat() - 0.5f) * i / 3;
		    float dy = 1;
		    float dz = (this.getRNG().nextFloat() - 0.5f) * i / 3;
		    potato.shoot(dx, dy, dz);
		    this.world.addEntity(potato);
		}
	}
	
	@Override
	public boolean performAttack() {
		if(!this.isMineReady()) {//not ready
			return false;
		}
		if(!this.world.isRemote) {
			AxisAlignedBB aabb= EntityUtil.getEntityAABB(this, bombRange,bombRange);
			for(LivingEntity target:EntityUtil.getEntityTargetableEntity(this, aabb)) {
				target.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
			}
			this.playSound(SoundRegister.POTATO_MINE.get(), 1f, 1f);
		}
		return true;
	}
	
	@Override
	public void spawnParticle() {
		if(!this.isMineReady()) {
			return ;
		}
		for(int i=1;i<=5;i++) {
//			System.out.println("111");
			this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX(), this.getPosY(), this.getPosZ(), (rand.nextFloat()-0.5)/4,0.4d,(rand.nextFloat()-0.5)/4);
		    this.world.addParticle(ParticleRegister.YELLOW_BOMB.get(), this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
		    this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX(), this.getPosY(), this.getPosZ(), (rand.nextFloat()-0.5)/4,0.4d,(rand.nextFloat()-0.5)/4);
		}
	}
	
	/**
	 * potato mine get ready now
	 */
	protected void outDirt(){
		this.setMineReady(true);
		if(world.isRemote) {
			for(int i=0;i<10;i++) {
			    Random rand=this.getRNG();
			    this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()+0.5d, this.getPosY(), this.getPosZ()+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
			    this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()+0.5d, this.getPosY(), this.getPosZ()-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
			    this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()-0.5d, this.getPosY(), this.getPosZ()+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
			    this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()-0.5d, this.getPosY(), this.getPosZ()-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
		    }
		}else {
			this.playSound(SoundRegister.DIRT_RISE.get(), 1f, 1f);
		}
	}
	
	@Override
	public boolean isPlantImmuneTo(DamageSource source) {
		if(this.isMineReady()) {
			return super.isPlantImmuneTo(source) || PVZDamageSource.isEnforceDamage(source);
		}
		return super.isPlantImmuneTo(source);
	}
	
	/**
	 * potato mine prepare time
	 */
	public int getReadyTime(){
		int lvl = this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 240-now*20;
		}
		return 240;
	}
	
	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 140+now*20;
		}
		return 140;
	}
	
	protected int getSignChangeTime(){
		if(this.isMineReady()) return 10;
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
		this.setMineReady(compound.getBoolean("mine_ready"));
		this.sign_red=compound.getBoolean("sign_red");
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("mine_ready", this.isMineReady());
		compound.putBoolean("sign_red", this.sign_red);
	}
	
	public void setMineReady(boolean is){
    	this.dataManager.set(MINE_READY, is);
    }
    
    public boolean isMineReady(){
    	return this.dataManager.get(MINE_READY);
    }
    
	@Override
	public Plants getPlantEnumName() {
		return Plants.POTATO_MINE;
	}

}
