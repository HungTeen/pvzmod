package com.hungteen.pvz.entity.plant.arma;

import java.util.Optional;

import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.itembullet.CabbageEntity;
import com.hungteen.pvz.entity.bullet.itembullet.CabbageEntity.CabbageTypes;
import com.hungteen.pvz.entity.plant.base.PlantPultEntity;
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
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getPultRange(), 11, 10));
	}

	@Override
	public void pultBullet() {
		LivingEntity target = this.getAttackTarget();
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
		CabbageEntity cabbage = new CabbageEntity(world, this);
        cabbage.setPosition(this.getPosX(), this.getPosY() + 1.7f, this.getPosZ());
        cabbage.shootPultBullet(target);
        EntityUtil.playSound(this, SoundRegister.PLANT_THROW.get());
        this.world.addEntity(cabbage);
        return Optional.of(cabbage);
	}
	
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 3.75F + 0.25F * lvl;
		return 9;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8F, 1F);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.CABBAGE_PULT;
	}

}
