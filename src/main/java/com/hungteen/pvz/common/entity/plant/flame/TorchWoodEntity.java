package com.hungteen.pvz.common.entity.plant.flame;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.particle.ParticleRegister;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;

import java.util.List;

public class TorchWoodEntity extends PVZPlantEntity {

	private static final DataParameter<Integer> FLAME_TYPE = EntityDataManager.defineId(TorchWoodEntity.class, DataSerializers.INT);
	
	public TorchWoodEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(FLAME_TYPE, FlameTypes.YELLOW.ordinal());
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			this.heatPeas();
		}else {
			IParticleData particle = ParticleRegister.YELLOW_FLAME.get();
			if(this.getFlameType() == FlameTypes.BLUE) {
				particle = ParticleRegister.BLUE_FLAME.get();
			}
			WorldUtil.spawnRandomSpeedParticle(this.level, particle, this.position().add(0, 1.5F, 0), 0.1F);
		}
	}
	
	/**
	 * {@link #normalPlantTick()}
	 */
	public void heatPeas() {
		final float range = this.getHeatRange();
		level.getEntitiesOfClass(PeaEntity.class, EntityUtil.getEntityAABB(this, range, range)).forEach(pea -> {
			pea.heatBy(this);
		});
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.setFlameType(FlameTypes.BLUE);
	}
	
	@Override
	public boolean canStartSuperMode() {
		return super.canStartSuperMode() && this.getFlameType() == FlameTypes.YELLOW;
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.add(Pair.of(PAZAlmanacs.HEAT_PEA_RANGE, this.getHeatRange()));
	}

	/**
	 * {@link #heatPeas()}
	 */
	public float getHeatRange() {
		return this.getSkillValue(SkillTypes.HEAT_PEA_RANGE);
	}

	@Override
	protected float getLife() {
		return this.getSkillValue(SkillTypes.WOOD_MORE_LIFE);
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.95f, 1.95f, false);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 0;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("flame_type")) {
			this.setFlameType(FlameTypes.values()[compound.getInt("flame_type")]);
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("flame_type", this.getFlameType().ordinal());
	}
	
	public FlameTypes getFlameType() {
		return FlameTypes.values()[entityData.get(FLAME_TYPE)];
	}
	
	public void setFlameType(FlameTypes type) {
		entityData.set(FLAME_TYPE, type.ordinal());
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.TORCH_WOOD;
	}
	
	public enum FlameTypes{
		YELLOW,
		BLUE,
		PURPLE
	}

}
