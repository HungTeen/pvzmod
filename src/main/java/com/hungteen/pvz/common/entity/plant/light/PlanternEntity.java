package com.hungteen.pvz.common.entity.plant.light;

import com.hungteen.pvz.api.interfaces.ILightEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Optional;

public class PlanternEntity extends PVZPlantEntity implements ILightEffect {

	private static final int EFFECT_CD = 50;
	
	public PlanternEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			if(this.getExistTick() % EFFECT_CD == 0) {
				this.giveLightToPlayers();
			}
		}
	}
	
	/**
	 * {@link #normalPlantTick()}
	 */
	private void giveLightToPlayers() {
		final float range = 30;
		EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range, range)).stream()
		.filter(e -> e instanceof PlayerEntity).forEach(entity -> {
			entity.addEffect(this.getLightEyeEffect());
		});
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		final float range = 30;
		EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range, range)).forEach(entity -> {
			entity.addEffect(this.createEffect(1, this.getSuperLightEyeTime()));
		});
		this.getSpawnSound().ifPresent(s -> EntityUtil.playSound(this, s));
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.75f, 1.7f);
	}

	@Override
	public int getSuperTimeLength() {
		return 20;
	}

	@Override
	public EffectInstance getLightEyeEffect() {
		return createEffect(0, this.getLightEyeTime());
	}
	
	private EffectInstance createEffect(int lvl, int time) {
		return new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), time, lvl, false, false);
	}
	
	public int getLightEyeTime() {
		return Math.min(4000, this.getPAZLevel() * 200);
	}
	
	public int getSuperLightEyeTime() {
		return this.isPlantInStage(1) ? 3600 : this.isPlantInStage(2) ? 7200 : 10800; 
	}
	
	@Override
	public Optional<SoundEvent> getSpawnSound() {
		return Optional.ofNullable(SoundRegister.PLANTERN.get());
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.PLANTERN;
	}
	
}
