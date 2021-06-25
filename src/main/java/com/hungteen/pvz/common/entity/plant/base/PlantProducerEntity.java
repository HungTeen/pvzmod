package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.common.entity.ai.goal.ProducerGenGoal;
import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IProducer;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public abstract class PlantProducerEntity extends PVZPlantEntity implements IProducer{

	private static final DataParameter<Boolean> IS_GEN_TIME = EntityDataManager.defineId(PlantProducerEntity.class, DataSerializers.BOOLEAN);
	public int genCD;
	
	public PlantProducerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCharm = false;
		this.genCD = this.getGenCD();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(IS_GEN_TIME, false);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new ProducerGenGoal(this));
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}

	/**
	 * sun produce plant gen sun
	 * such as sunflower or sunshroom
	 */
	protected void genSun(int num){
		SunEntity sun = EntityRegister.SUN.get().create(this.level);
		sun.setAmount(num);
		EntityUtil.onMobEntityRandomPosSpawn(level, sun, blockPosition(), 2);
		EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("is_gen_time")) {
			this.setIsGenTime(compound.getBoolean("is_gen_time"));
		}
		if(compound.contains("gen_cd")) {
			this.genCD = compound.getInt("gen_cd");
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("is_gen_time", this.getIsGenTime());
		compound.putInt("gen_cd", this.genCD);
	}
	
	public boolean getIsGenTime()
	{
		return entityData.get(IS_GEN_TIME);
	}
	
	public void setIsGenTime(boolean is)
	{
		this.entityData.set(IS_GEN_TIME, is);
	}
	
}
