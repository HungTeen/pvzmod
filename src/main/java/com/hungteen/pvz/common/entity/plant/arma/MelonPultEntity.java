package com.hungteen.pvz.common.entity.plant.arma;

import java.util.Optional;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.MelonEntity;
import com.hungteen.pvz.common.entity.bullet.MelonEntity.MelonStates;
import com.hungteen.pvz.common.entity.bullet.MelonEntity.MelonTypes;
import com.hungteen.pvz.common.entity.plant.base.PlantPultEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class MelonPultEntity extends PlantPultEntity {

	public MelonPultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getPultRange(), 11, 10));
	}
	
	@Override
	public void pultBullet() {
		LivingEntity target = this.getTarget();
		this.pultMelon(target);
	}
	
	@Override
	protected void doSuperAttackToTarget(LivingEntity target) {
		this.pultMelon(target).ifPresent((melon) -> {
			melon.setMelonType(MelonTypes.POWER);
		});
	}
	
	private Optional<MelonEntity> pultMelon(LivingEntity target){
		if(target == null) return Optional.empty();
		MelonEntity melon = new MelonEntity(level, this);
        melon.setPos(this.getX(), this.getY() + 1.5f, this.getZ());
        melon.shootPultBullet(target);
        melon.setMelonState(this.getThrowMelonState());
        EntityUtil.playSound(this, SoundRegister.PLANT_THROW.get());
        this.level.addFreshEntity(melon);
        return Optional.of(melon);
	}
	
	protected MelonStates getThrowMelonState() {
		return MelonStates.NORMAL;
	}
	
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 7.5F + 0.5F * lvl;
		return 18;
	}
	
	public float getSplashDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 2.75F + 0.25F * lvl;
		return 8;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9F, 1F);
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return Plants.WINTER_MELON;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.MELON_PULT;
	}

}
