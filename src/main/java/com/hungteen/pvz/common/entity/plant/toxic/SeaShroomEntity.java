package com.hungteen.pvz.common.entity.plant.toxic;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.itembullet.SporeEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SeaShroomEntity extends PlantShooterEntity {

	protected final double LENTH = 0.15d;
	
	public SeaShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new SwimGoal(this));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getShootRange(), 2, 0));
	}
	
	@Override
	public void shootBullet() {
		LivingEntity target=this.getTarget();
		if(target==null) {
			//System.out.println("no target at all!");
			return ;
		}
		double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        double y = this.getY()+this.getDimensions(getPose()).height*0.7f;
        double dis =MathHelper.sqrt(dx * dx + dz * dz);
        double tmp=this.LENTH / dis;
        double deltaX = tmp * dx;
        double deltaZ = tmp * dz;
        SporeEntity spore = new SporeEntity(this.level, this);
        spore.setPos(this.getX() + deltaX, y, this.getZ() + deltaZ);
        spore.shootPea(dx, target.getY() + target.getBbHeight() - y, dz, this.getBulletSpeed());      
        EntityUtil.playSound(this, SoundRegister.PUFF.get());
        this.level.addFreshEntity(spore);
	}

	@Override
	public float getShootRange() {
		return 10;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5f, 0.8f);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.SEA_SHROOM;
	}

	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 100;
		if(this.isPlantInStage(2)) return 120;
		return 140;
	}

}
