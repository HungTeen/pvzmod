package com.hungteen.pvz.common.entity.plant.light;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.interfaces.ILightEffect;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.effect.OriginEffectEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import com.hungteen.pvz.utils.EffectUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Optional;

public class PlanternEntity extends PVZPlantEntity implements ILightEffect {

	private static final int EFFECT_CD = 100;
	
	public PlanternEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			if(this.getExistTick() % EFFECT_CD == 10) {
				this.giveLightToPlayers();
			}
		}
	}

	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if(hand == Hand.MAIN_HAND && player.getItemInHand(hand).isEmpty() && this.isInSuperState()){
			if(! this.level.isClientSide){
				OriginEffectEntity.create(this.level, this.blockPosition().above(), Colors.YELLOW);
                
				this.displayAllRaider();

				EntityUtil.playSound(this, this.getSpawnSound().get());
			}
			return ActionResultType.SUCCESS;
		}
		return super.interactAt(player, vec3d, hand);
	}

	/**
	 * {@link #normalPlantTick()}
	 */
	private void giveLightToPlayers() {
		final float range = this.getEffectRange();
		EntityUtil.getFriendlyLivings(this, EntityUtil.getEntityAABB(this, range, range)).forEach(entity -> {
			entity.addEffect(this.getLightEyeEffect());
			final int nightVisionTime = this.getNightVisionTime();
			if(nightVisionTime > 0){
				entity.addEffect(EffectUtil.viewEffect(Effects.NIGHT_VISION, nightVisionTime, 0));
			}
		});
	}

	private void displayAllRaider(){
		if(this.level instanceof ServerWorld){
			ChallengeManager.getChallengeNearBy((ServerWorld) this.level, this.blockPosition()).ifPresent(challenge -> {
				challenge.getRaiders().forEach(raider -> {
					if(raider instanceof LivingEntity) {
						((LivingEntity) raider).addEffect(EffectUtil.viewEffect(Effects.GLOWING, 200, 0));
					} else {
						raider.setGlowing(true);
					}
				});
			});
		}
	}

	public float getEffectRange(){
		return this.getSkillValue(SkillTypes.MORE_LIGHT_RANGE);
	}

	public int getNightVisionTime(){
		return (int) this.getSkillValue(SkillTypes.NIGHT_VISION);
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.75f, 1.7f);
	}

	@Override
	public EffectInstance getLightEyeEffect() {
		return EffectUtil.viewEffect(EffectRegister.LIGHT_EYE_EFFECT.get(), this.getLightEyeTime(), 0);
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.add(Pair.of(PAZAlmanacs.EFFECT_TIME, this.getLightEyeTime()));
	}

	public int getLightEyeTime() {
		return 1800;
	}

	@Override
	public int getSuperTimeLength() {
		return 20;
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
