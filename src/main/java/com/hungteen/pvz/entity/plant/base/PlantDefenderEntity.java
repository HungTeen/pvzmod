package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.DefenderAttractGoal;
import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IDefender;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class PlantDefenderEntity extends PVZPlantEntity implements IDefender{

	private static final DataParameter<Float> DEFENCE_LIFE = EntityDataManager.createKey(PlantDefenderEntity.class, DataSerializers.FLOAT);
	
	public PlantDefenderEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(DEFENCE_LIFE, 0f);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new DefenderAttractGoal(this, 20));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, getAttractRange(), 3f) {
			@Override
			protected boolean checkPlant(Entity entity) {
				return entity instanceof MobEntity;
			}
		});
	}

	@Override
	public void startSuperMode() {
		super.startSuperMode();
		this.setDefenceLife(this.getSuperLife());
	}
	
	@Override
	public void attract() {
		float range = getAttractRange();
		for(LivingEntity target:EntityUtil.getEntityAttackableTarget(this, EntityUtil.getEntityAABB(this, range, range))) {
			if(target instanceof MobEntity) {
				if(!(((MobEntity) target).getAttackTarget() instanceof PlantDefenderEntity)) {
					((MobEntity) target).setAttackTarget(this);
				}
			}
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(!world.isRemote) {
			if(this.getDefenceLife()>amount) {
				this.setDefenceLife(this.getDefenceLife()-amount);
				amount=0;
			}else {
				amount-=this.getDefenceLife();
				this.setDefenceLife(0f);
			}
		}
		return super.attackEntityFrom(source, amount);
	}
	
	protected float getAttractRange() {
		return 3;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	public float getDefenceLife() {
		return dataManager.get(DEFENCE_LIFE);
	}
	
	public void setDefenceLife(float life) {
		dataManager.set(DEFENCE_LIFE, life);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setDefenceLife(compound.getFloat("defence_life"));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putFloat("defence_life", this.getDefenceLife());
	}
	
}
