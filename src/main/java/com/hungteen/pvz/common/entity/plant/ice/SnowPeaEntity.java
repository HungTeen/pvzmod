package com.hungteen.pvz.common.entity.plant.ice;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity.State;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.common.potion.EffectRegister;

import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.COLD_LEVEL, this.getColdLvl()),
				Pair.of(PAZAlmanacs.COLD_TIME, this.getColdTick())
		));
	}

	/**
	 * cold effect maxLevel.
	 */
	public int getColdLvl() {
		return 5;
	}
	
	/**
	 * cold effect duration.
	 */
	public int getColdTick() {
		return 80;
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
	public IPlantType getPlantType() {
		return PVZPlants.SNOW_PEA;
	}
	
}
