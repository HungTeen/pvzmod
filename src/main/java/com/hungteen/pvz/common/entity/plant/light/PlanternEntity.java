package com.hungteen.pvz.common.entity.plant.light;

import com.hungteen.pvz.api.interfaces.ILightEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
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
			if(this.getExistTick() % EFFECT_CD == 3) {
				this.giveLightToPlayers();
			}
		}
	}
	
	/**
	 * {@link #normalPlantTick()}
	 */
	private void giveLightToPlayers() {
		final float range = this.getEffectRange();
		EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range, range)).forEach(entity -> {
			entity.addEffect(this.createEffect(0, this.getLightEyeTime()));
		});
	}

	public float getEffectRange(){
		return this.getSkillValue(SkillTypes.MORE_LIGHT_RANGE);
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.75f, 1.7f);
	}

	@Override
	public EffectInstance getLightEyeEffect() {
		return createEffect(0, this.getLightEyeTime());
	}
	
	private EffectInstance createEffect(int lvl, int time) {
		return new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), time, lvl, false, false);
	}
	
	public int getLightEyeTime() {
		return 2000;
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
