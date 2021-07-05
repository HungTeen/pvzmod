package com.hungteen.pvz.common.entity.plant.arma;

import java.util.Optional;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.itembullet.CabbageEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.CabbageEntity.CabbageTypes;
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

public class CabbagePultEntity extends PlantPultEntity {

	public CabbagePultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, false, 5, getPultRange(), 11, 10));
	}

	@Override
	public void pultBullet() {
		LivingEntity target = this.getTarget();
		this.pultCabbage(target);
	}
	
	@Override
	protected void doSuperAttackToTarget(LivingEntity target) {
		this.pultCabbage(target).ifPresent((cabbage) -> {
			cabbage.setCabbageType(CabbageTypes.POWER);
		});
	}
	
	private Optional<CabbageEntity> pultCabbage(LivingEntity target){
		if(target == null) return Optional.empty();
		CabbageEntity cabbage = new CabbageEntity(level, this);
        cabbage.setPos(this.getX(), this.getY() + 1.7f, this.getZ());
        cabbage.shootPultBullet(target);
        EntityUtil.playSound(this, SoundRegister.PLANT_THROW.get());
        this.level.addFreshEntity(cabbage);
        return Optional.of(cabbage);
	}
	
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 3.75F + 0.25F * lvl;
		return 9;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 1F);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.CABBAGE_PULT;
	}

}
