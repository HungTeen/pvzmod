package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.ProducerGenGoal;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
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

	private static final DataParameter<Boolean> IS_GEN_TIME = EntityDataManager.createKey(PlantProducerEntity.class, DataSerializers.BOOLEAN);
	public int genCD;
	
	public PlantProducerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCharmed = false;
		this.genCD = this.getGenCD();
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(IS_GEN_TIME, false);
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
		SunEntity sun = EntityRegister.SUN.get().create(this.world);
		sun.setAmount(num);
		EntityUtil.onMobEntityRandomPosSpawn(world, sun, getPosition(), 3);
		EntityUtil.playSound(this, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setIsGenTime(compound.getBoolean("is_gen_time"));
		this.genCD=compound.getInt("gen_cd");
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("is_gen_time", this.getIsGenTime());
		compound.putInt("gen_cd", this.genCD);
	}
	
	public boolean getIsGenTime()
	{
		return dataManager.get(IS_GEN_TIME);
	}
	
	public void setIsGenTime(boolean is)
	{
		this.dataManager.set(IS_GEN_TIME, is);
	}
	
}
