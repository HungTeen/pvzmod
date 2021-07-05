package com.hungteen.pvz.common.entity.plant.ice;

import java.util.Optional;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity.State;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SnowPeaEntity extends PeaShooterEntity implements IIceEffect{

	public SnowPeaEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public Optional<EffectInstance> getColdEffect() {
		return Optional.ofNullable(new EffectInstance(EffectRegister.COLD_EFFECT.get(), this.getColdTick(), this.getColdLvl(), false, false));
	}
	
	@Override
	public Optional<EffectInstance> getFrozenEffect() {
		return Optional.empty();
	}
	
	/**
	 * cold effect level.
	 */
	public int getColdLvl() {
		return MathUtil.getProgressByDif(4, 1, this.getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 5, 9);
	}
	
	/**
	 * cold effect duration.
	 */
	public int getColdTick() {
		return MathUtil.getProgressAverage(this.getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 80, 160);
	}
	
	@Override
	protected SoundEvent getShootSound() {
		return SoundRegister.SNOW_SHOOT.get();
	}
	
	@Override
	protected State getShootState() {
		return State.ICE;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SNOW_PEA;
	}
	
}
