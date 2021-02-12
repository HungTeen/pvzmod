package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.attack.PultAttackGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IPult;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public abstract class PlantPultEntity extends PVZPlantEntity implements IPult {

	protected boolean isSuperOut = false;
	
	public PlantPultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PultAttackGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(this.getPultRange());
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! world.isRemote && this.getAttackTime() > 0) {
			this.setAttackTime(this.getAttackTime() - 1);
			if(this.getAttackTime() == this.getPultAnimTime() / 2) {
				if(this.isPlantInSuperMode() && ! this.isSuperOut) {
					this.isSuperOut = true;
					this.superAttack();
				} else {
					this.pultBullet();
				}
			}
		}
	}
	
	protected void superAttack() {
		float range = this.getSuperRange();
		EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
			this.doSuperAttackToTarget(target);
		});
	}
	
	protected abstract void doSuperAttackToTarget(LivingEntity target);
	
	@Override
	public boolean shouldPult() {
		return this.canPlantNormalUpdate();
	}
	
	@Override
	protected boolean canPlantTarget(LivingEntity entity) {
		if(entity instanceof BalloonZombieEntity) return true;
		return this.checkY(entity) && super.canPlantTarget(entity);
	}
	
	protected boolean checkY(LivingEntity target) {
		return this.getPosY() + 10 >= target.getPosY() + target.getHeight();
	}
	
	public float getSuperRange() {
		if(this.isPlantInStage(1)) return 10;
		if(this.isPlantInStage(2)) return 15;
		return 20;
	}
	
	@Override
	public float getPultRange() {
		return 40;
	}
	
	@Override
	public void startPultAttack() {
		this.setAttackTime(this.getPultAnimTime());
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.isSuperOut = false;
		this.startPultAttack();
	}
	
	public int getPultAnimTime() {
		return 20;
	}
	
	@Override
	public int getPultCD() {
		return 60;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 60;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("is_plant_super_out")) {
			this.isSuperOut = compound.getBoolean("is_plant_super_out");
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("is_plant_super_out", this.isSuperOut);
	}

}
