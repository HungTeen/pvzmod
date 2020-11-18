package com.hungteen.pvz.entity.plant.light;

import com.hungteen.pvz.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.entity.plant.interfaces.IShroomPlant;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class SunShroomEntity extends PlantProducerEntity implements IShroomPlant{

	protected static final int GROW_TICK = 1800;
	private static final DataParameter<Integer> GROW_ANIM = EntityDataManager.createKey(SunShroomEntity.class, DataSerializers.VARINT);
	public final int growAnimTo = 10;
	
	public SunShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(GROW_ANIM, 0);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(this.getGrowAnim() > 0 && this.getGrowAnim() < this.growAnimTo) {
			this.setGrowAnim(this.getGrowAnim() + 1);
		}
		if(!this.hasGrowUp() && this.getLiveTick() > GROW_TICK) {
			this.grow();
		}
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(!this.hasGrowUp()) {
		    this.grow();
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return this.hasGrowUp() ? EntitySize.flexible(0.6f, 0.8f) : EntitySize.flexible(0.4f, 0.4f);
	}
	
	/**
	 * sun shroom grow up progress
	 */
	private void grow() {
		this.setGrowAnim(1);
		if(!world.isRemote) {
			EntityUtil.playSound(this, SoundRegister.PLANT_GROW.get());
		}
	}
	
	@Override
	public void genSomething() {
		this.genSun(this.getCurrentSunAmount());
	}

	@Override
	public void genSuper() {
		for(int i = 0;i < 4;i ++) {
			this.genSun(this.getSunAmount());
		}
	}
	
	/**
	 * check weather the shroom has grow up
	 */
	public boolean hasGrowUp() {
		return this.getGrowAnim() > 0;
	}
	
	/**
	 * get current sun gen num;
	 */
	private int getCurrentSunAmount() {
		return this.hasGrowUp() ? this.getSunAmount() : 15;
	}
	
	/**
	 * get sun amount when grow up
	 */
	public int getSunAmount(){
		if(this.isPlantInStage(1)) return 25;
		if(this.isPlantInStage(2)) return 35;
		return 50;
	}

	@Override
	public int getGenCD() {
		return 600;
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("grow_anim", this.getGrowAnim());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setGrowAnim(compound.getInt("grow_anim"));
	}

	public void setGrowAnim(int tick) {
		this.dataManager.set(GROW_ANIM, tick);
	}
	
	public int getGrowAnim() {
		return this.dataManager.get(GROW_ANIM);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SUN_SHROOM;
	}

}
