package com.hungteen.pvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.MelonEntity;
import com.hungteen.pvz.common.entity.bullet.MelonEntity.MelonStates;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantPultEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

public class MelonPultEntity extends PlantPultEntity {

	public MelonPultEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	protected PultBulletEntity createBullet() {
		final MelonEntity melon = new MelonEntity(level, this);
		melon.setMelonState(this.getThrowMelonState());
		return melon;
	}
	
	protected MelonStates getThrowMelonState() {
		return MelonStates.NORMAL;
	}
	
	public float getAttackDamage() {
		return this.getSkillValue(SkillTypes.MORE_MELON_DAMAGE);
	}
	
	@Override
	public float getSuperDamage() {
		return this.getAttackDamage() + 15;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9F, 1F);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.MELON_PULT;
	}

}
