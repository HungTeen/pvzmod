package com.hungteen.pvz.common.entity.plant.flame;

import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.interfaces.ILightPlant;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.utils.EntityUtil;
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
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class TorchWoodEntity extends PVZPlantEntity implements ILightPlant {

	private static final DataParameter<Boolean> SUPER_FLAME = EntityDataManager.defineId(TorchWoodEntity.class, DataSerializers.BOOLEAN);
	
	public TorchWoodEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCharm = false;
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(SUPER_FLAME, false);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(level.isClientSide) {
			if(this.IsSuperFlame()) {
				this.level.addParticle(ParticleRegister.BLUE_FLAME.get(), this.getX(), this.getY()+1.5, this.getZ(), 0, 0, 0);
			}else {
				this.level.addParticle(ParticleRegister.YELLOW_FLAME.get(), this.getX(), this.getY()+1.5, this.getZ(), 0, 0, 0);
			}
		}
		if(! level.isClientSide) {
			if(this.tickCount % 40 == 0) {
				int range = 15;
				this.giveLightToPlayers(range);
			}
		}
		if(this.isPlantInSuperMode()) {
			fireballRain();
		}
	}
	
	private void giveLightToPlayers(float range) {
		level.getEntitiesOfClass(PlayerEntity.class, EntityUtil.getEntityAABB(this, range, range), (player) -> {
			return ! EntityUtil.canTargetEntity(this, player);
		}).forEach((player) -> {
			player.addEffect(getLightEyeEffect());
		});
	}
	
	private void fireballRain() {
		double dx = (this.getRandom().nextFloat() - 0.5f) * 6;
		double dz = (this.getRandom().nextFloat() - 0.5f) * 6;
		double dy = 10;
		PeaEntity pea = new PeaEntity(level, this, PeaEntity.Type.NORMAL, PeaEntity.State.FIRE);
		pea.setPos(this.getX() + dx, this.getY() + dy, this.getZ() + dz);
		pea.setDeltaMovement(0, - 0.4, 0);
		level.addFreshEntity(pea);
	}
	
	@Override
	public EffectInstance getLightEyeEffect() {
		return new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), 100, 0, false, false);
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.setSuperFlame(true);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.95f, 1.95f, false);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.TORCH_WOOD;
	}

	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 30;
		if(this.isPlantInStage(2)) return 50;
		return 80;
	}
	
	public boolean IsSuperFlame() {
		return entityData.get(SUPER_FLAME);
	}
	
	public void setSuperFlame(boolean is) {
		entityData.set(SUPER_FLAME, is);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("is_super_fire")) {
			this.setSuperFlame(compound.getBoolean("is_super_fire"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("is_super_fire", this.IsSuperFlame());
	}

}
