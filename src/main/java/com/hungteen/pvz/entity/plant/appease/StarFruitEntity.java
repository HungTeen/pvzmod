package com.hungteen.pvz.entity.plant.appease;

import com.hungteen.pvz.entity.ai.attack.ShooterAttackGoal;
import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.StarEntity;
import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.command.arguments.EntityAnchorArgument.Type;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class StarFruitEntity extends PlantShooterEntity {

	public static final float PER_ANGLE = 360F / 5;
	public int lightTick = 0;
	
	public StarFruitEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new ShooterAttackGoal(this));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getShootRange(), 2, 0));
	}

	@Override
	public void normalPlantTick() {
		if(level.isClientSide) {
			if(this.lightTick > 0) {
				-- this.lightTick;
			}
			if(this.getAttackTime() > 0) {
			    this.lightTick = 8;
			}
		}
		super.normalPlantTick();
	}
	
	@Override
	protected boolean canAttackNow() {
		return this.getAttackTime() == 2;
	}
	
	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 100;
		if(this.isPlantInStage(2)) return 150;
		if(this.isPlantInStage(3)) return 200;
		return 200;
	}

	@Override
	public void shootBullet() {
		float now = this.yRot + 180F;
		for(int i = 0; i < 5; ++ i) {
			this.shootStarByAngle(now);
			now += PER_ANGLE;
		}
		EntityUtil.playSound(this, SoundRegister.SNOW_SHOOT.get());
	}
	
	private void shootStarByAngle(float angle) {
		angle *= 3.14159F / 180F;
		double vx = - MathHelper.sin(angle);
		double vz = MathHelper.cos(angle);
		StarEntity.StarTypes type = this.isPlantInSuperMode() ? StarEntity.StarTypes.HUGE : StarEntity.StarTypes.NORMAL;
		StarEntity pea = new StarEntity(level, this, type, StarEntity.StarStates.YELLOW);
		pea.setPos(getX(), getY() + 0.2F, getZ());
		pea.setDeltaMovement(vx, 0, vz);
		level.addFreshEntity(pea);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9F, 0.5F);
	}
	
	@Override
	public double getMaxShootAngle() {
		return 40;
	}

	@Override
	public void startShootAttack() {
		this.updateFacing();
		this.setAttackTime(2);
	}
	
	private void updateFacing() {
		float now = this.getRandom().nextFloat() * 3.14159F * 2F;
		double dx = Math.sin(now);
		double dz = Math.cos(now);
		double dy = 0;
		this.lookAt(Type.FEET, this.position().add(dx, dy, dz));
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.STAR_FRUIT;
	}
	
}
