package com.hungteen.pvz.common.entity.plant.enforce;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZRandomTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class SquashEntity extends PVZPlantEntity{

	private static final int TARGET_RANGE = 3;
	protected int extraChance = 0;
	
	public SquashEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZRandomTargetGoal(this, true, TARGET_RANGE, TARGET_RANGE));
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(!level.isClientSide) {
			if(EntityUtil.isEntityValid(this.getTarget())) {
				this.getLookControl().setLookAt(this.getTarget(), 30f, 30f);
			}
			if(this.getAttackTime() > 0) {
				if(this.isOnGround()) {
					this.dealDamage();
					//check death.
					if(this.extraChance > 0) {
						-- this.extraChance;
					}else {
						if(this.getRandom().nextInt(100) < this.getDeathChance()) {
							this.remove();
						}
					}
				}
			} else {
				if(this.getTarget() != null) {
					EntityUtil.playSound(this, SoundRegister.SQUASH_HMM.get());
					this.jumpToTarget(this.getTarget());
				}
			}
		}
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		if(EntityUtil.canTargetEntity(this, target)) {
			return false;
		}
		return super.shouldCollideWithEntity(target);
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(!level.isClientSide) {
		    this.extraChance += this.getSuperBonusChance();
		}
	}
	
	/**
	 * {@link #normalPlantTick()}
	 */
	protected void dealDamage(){
		this.setAttackTime(0);
		this.canCollideWithPlant = true;
		this.isImmuneToWeak = false;
		EntityUtil.playSound(this, SoundRegister.GROUND_SHAKE.get());
		final float range = 1F;
		for(LivingEntity entity : EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range))) {
			entity.hurt(PVZDamageSource.causeNormalDamage(this, this), this.getAttackDamage());
		}
	}
	
	/**
	 * jump to the top of the target.
	 * {@link #normalPlantTick()}
	 */
	private void jumpToTarget(LivingEntity target) {
		final int tick = 10;
		this.canCollideWithPlant = false;
		this.isImmuneToWeak = true;
		final Vector3d pos = target.position().add(target.getDeltaMovement().scale(tick * 0.8D));
		this.setPos(pos.x(), pos.y() + target.getBbHeight() + 1, pos.z());
		this.setDeltaMovement(this.getDeltaMovement().x(), 0, this.getDeltaMovement().z());
		this.setAttackTime(1);
	}
	
	@Override
	protected boolean shouldLockXZ() {
		return this.isOnGround();
	}
	
	/**
	 * extra smash times
	 */
	protected int getSuperBonusChance(){
		return this.isPlantInStage(1) ? 3 : this.isPlantInStage(2) ? 4 : 5;
	}
	
	public float getAttackDamage(){
		return PlantUtil.getPlantAverageProgress(this, 125, 425);
	}
	
	/**
	 * die chance for each smash
	 */
	public int getDeathChance(){
		return PlantUtil.getPlantAverageProgress(this, 90, 40);
	}
	
	@Override
	public boolean isPlantImmuneTo(DamageSource source) {
		return super.isPlantImmuneTo(source) || PVZDamageSource.isEnforceDamage(source) || source == DamageSource.FALL;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9f, 1.5f);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("extra_chance")) {
			this.extraChance = compound.getInt("extra_chance");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("extra_chance", this.extraChance);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SQUASH;
	}

}
