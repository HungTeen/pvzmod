package com.hungteen.pvz.entity.plant.light;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.interfaces.ILightPlant;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class PlanternEntity extends PVZPlantEntity implements ILightPlant {

	public PlanternEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! world.isRemote) {
			EntityUtil.playSound(this, SoundRegister.PLANTERN.get());
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! world.isRemote) {
			if(this.ticksExisted % 40 == 0) {
				int range = this.isPlantInSuperMode() ? 40 : 30;
				this.giveLightToPlayers(range);
			}
		}
	}
	
	private void giveLightToPlayers(float range) {
		world.getEntitiesWithinAABB(PlayerEntity.class, EntityUtil.getEntityAABB(this, range, range), (player) -> {
			return ! EntityUtil.checkCanEntityAttack(this, player);
		}).forEach((player) -> {
			player.addPotionEffect(getLightEyeEffect());
		});
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.PLANTERN;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.75f, 1.7f);
	}

	@Override
	public int getSuperTimeLength() {
		return 50;
	}

	@Override
	public EffectInstance getLightEyeEffect() {
		if(this.isPlantInSuperMode()) return new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), this.getLightEyeTime() * 2, this.getSuperLightLvl(), false, true);
		return new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), this.getLightEyeTime(), this.getLightEyeLvl(), false, false);
	}
	
	public int getLightEyeLvl() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 5;
			return now;
		}
		return 3;
	}
	
	public int getSuperLightLvl() {
		if(this.isPlantInStage(1)) return 1;
		if(this.isPlantInStage(2)) return 2;
		return 3;
	}
	
	public int getLightEyeTime() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) return lvl * 200;
		return 4000;
	}
	
}
