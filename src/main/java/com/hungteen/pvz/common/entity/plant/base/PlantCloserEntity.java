package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Closer Plants will be attack after several ticks focusing on its target.
 * use ATTACK_TIME to perform scale animation.
 */
public abstract class PlantCloserEntity extends PVZPlantEntity{
	
	public static final int ATTACK_ANIM_CD = 10;
	protected float closeWidth = 1.5F;
	protected float closeHeight = 1.5F;
	
	public PlantCloserEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, this.getCloseWidth(), this.getCloseHeight()));
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! this.level.isClientSide && this.canCheckDistance()) {
			if(EntityUtil.isEntityValid(this.getTarget())) {//target is close enough
				this.getLookControl().setLookAt(this.getTarget(), 30f, 30f);
				final int time = this.getAttackTime();
				if(time >= ATTACK_ANIM_CD) {
					this.performAttack(this.getTarget());
				}
				this.setAttackTime(time + 1);
			} else {
				this.setAttackTime(0);
			}
		}
	}
	
	/**
	 * whether plants can check distance and perform attack.
	 * {@link #normalPlantTick()}
	 */
	public boolean canCheckDistance() {
		return true;
	}
	
	/**
	 * perform attack when target is close.
	 * {@link #normalPlantTick()}
	 */
	public abstract void performAttack(LivingEntity target);
	
	@Override
	public boolean isPlantImmuneTo(DamageSource source) {
		if(this.canBeImmuneToEnforce()) {
			return super.isPlantImmuneTo(source) || PVZDamageSource.isEnforceDamage(source);
		}
		return super.isPlantImmuneTo(source);
	}
	
	protected boolean canBeImmuneToEnforce() {
		return true;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	/**
	 * horizontal distance needed when attack.
	 * {@link #registerGoals()}
	 */
	public float getCloseWidth() {
		return 1;
	}

	/**
	 * vertical distance needed when attack.
	 * {@link #registerGoals()}
	 */
	public float getCloseHeight() {
		return 1;
	}
	
}
