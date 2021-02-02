package com.hungteen.pvz.entity.plant.spear;

import java.util.HashSet;

import com.hungteen.pvz.entity.ai.target.PVZGlobalTargetGoal;
import com.hungteen.pvz.entity.bullet.ThornEntity;
import com.hungteen.pvz.entity.bullet.ThornEntity.ThornStates;
import com.hungteen.pvz.entity.bullet.ThornEntity.ThornTypes;
import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class CatTailEntity extends PlantShooterEntity {

	public HashSet<ThornEntity> thorns = new HashSet<>();
	private int powerCount = 0;
	private int powerTick = 0;
	private final int POWER_CD = 200;
	
	public CatTailEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZGlobalTargetGoal(this, true, getShootRange(), getShootRange()));
		this.goalSelector.addGoal(2, new SwimGoal(this));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		if(! world.isRemote) {
			if(this.powerTick > 0) -- this.powerTick;
			HashSet<ThornEntity> tmp = new HashSet<>();
			thorns.forEach((thorn)->{
				if(! thorn.removed && thorn.isInControl()) {
					thorn.setThornTarget(this.getAttackTarget());
					tmp.add(thorn);
				}
			});
			this.thorns.clear();
			this.thorns = tmp;
		}
	}
	
	@Override
	protected boolean canPlantTarget(LivingEntity entity) {
		if(entity instanceof BalloonZombieEntity) return true;
		return super.canPlantTarget(entity);
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.powerCount += this.getPowerThornCount();
	}
	
	@Override
	public int getShootCD() {
		return getNormalAttackCD();
	}
	
	@Override
	public boolean checkY(Entity target) {
		return true;
	}
	
	@Override
	public void shootBullet() {
		LivingEntity target = this.getAttackTarget();
		if(target == null) return ;
		ThornEntity thorn = new ThornEntity(world, this);
		thorn.setThornType(this.getThornShootType());
		thorn.setThornState(thorn.getThornType() == ThornTypes.AUTO ? ThornStates.POWER : ThornStates.NORMAL);
		thorn.setExtraHitCount(this.getExtraAttackCount());
		thorn.setPosition(getPosX(), getPosY() + this.getHeight() + 0.2F, getPosZ());
		this.thorns.add(thorn);
		EntityUtil.playSound(this, SoundRegister.PLANT_THROW.get());
		this.world.addEntity(thorn);
	}
	
	@Override
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 2;
			return 2.25F + now * 0.25F;
		}
		return 4.5F;
	}
	
	public int getExtraAttackCount() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 4;
			return now + 1;
		}
		return 5;
	}
	
	private ThornTypes getThornShootType() {
		if(this.powerCount > 0 && this.powerTick == 0) {
			this.powerTick = this.POWER_CD;
			-- this.powerCount;
			return ThornTypes.AUTO;
		}
		return ThornTypes.GUILD;
	}
	
	public int getPowerThornCount() {
		if(this.isPlantInStage(1)) return 1;
		if(this.isPlantInStage(2)) return 2;
		return 3;
	}
	
	@Override
	protected boolean canAttackNow() {
		return this.getAttackTime() > 0 && this.getAttackTime() % getAnimCD() == 0;
	}
	
	public int getAnimCD() {
		return 8;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(this.getAnimCD() * 2);
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8F, 1.4F);
	}
	
	@Override
	public float getShootRange() {
		return 50;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.CAT_TAIL;
	}

	@Override
	public int getSuperTimeLength() {
		return 40;
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("power_thorn_count")){
			this.powerCount = compound.getInt("power_thorn_count");
		}
		if(compound.contains("power_shoot_tick")){
			this.powerTick = compound.getInt("power_shoot_tick");
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("power_thorn_count", this.powerCount);
		compound.putInt("power_shoot_tick", this.powerTick);
	}
	
}
