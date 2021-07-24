package com.hungteen.pvz.common.entity.plant.flame;

import com.hungteen.pvz.api.interfaces.ILightEffect;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class TorchWoodEntity extends PVZPlantEntity implements ILightEffect {

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
			if(this.tickCount % 40 == 0) {
				this.giveLightToPlayers(10);
			}
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
	
	private void giveLightToPlayers(float range) {
		level.getEntitiesOfClass(PlayerEntity.class, EntityUtil.getEntityAABB(this, range, range), (player) -> {
			return ! EntityUtil.canTargetEntity(this, player);
		}).forEach((player) -> {
			player.addEffect(getLightEyeEffect());
		});
	}
	
	@Override
	public EffectInstance getLightEyeEffect() {
		return new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), 100, 0, false, false);
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
	
	/**
	 * {@link #heatPeas()}
	 */
	public float getHeatRange() {
		return this.isPlantInStage(1) ? 1.5F : this.isPlantInStage(2) ? 2 : 2.5F;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.95f, 1.95f, false);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
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
	public Plants getPlantEnumName() {
		return Plants.TORCH_WOOD;
	}
	
	public static enum FlameTypes{
		YELLOW,
		BLUE,
		PURPLE
	}

}
