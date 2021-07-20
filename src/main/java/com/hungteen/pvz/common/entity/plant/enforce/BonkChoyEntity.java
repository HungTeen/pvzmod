package com.hungteen.pvz.common.entity.plant.enforce;

import java.util.EnumSet;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

public class BonkChoyEntity extends PVZPlantEntity {

	public BonkChoyEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new BonkChoyAttackGoal(this));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, false, 3, 2));
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			if(this.isPlantInSuperMode() && this.getSuperTime() % 5 == 0) {
				float range = 3F;
				EntityUtil.playSound(this, SoundRegister.SWING.get());
				EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
					target.hurt(PVZDamageSource.normal(this), this.getAttackDamage() * 5);
					for(int i = 0; i < 2; ++ i) {
						EntityUtil.spawnParticle(target, 7);
					}
				});
			}
		}
	}
	
	public void attackTarget(LivingEntity target) {
		EntityUtil.playSound(this, SoundRegister.SWING.get());
		target.hurt(PVZDamageSource.normal(this), this.getAttackDamage());
	}
	
	@Override
	public boolean canPlantTarget(Entity entity) {
		if(entity instanceof BalloonZombieEntity) return true;
		return super.canPlantTarget(entity);
	}

	public int getAttackCD() {
		return 10;
	}
	
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 2;
			return 1.75F + now * 0.25F;
		}
		return 4F;
	}
	
	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 80;
		if(this.isPlantInStage(2)) return 100;
		return 120;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.BONK_CHOY;
	}
	
	private final class BonkChoyAttackGoal extends Goal {
		
		private final BonkChoyEntity attacker;
		
		public BonkChoyAttackGoal(BonkChoyEntity attacker) {
			this.attacker = attacker;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
		
		@Override
		public boolean canUse() {
			LivingEntity living = this.attacker.getTarget();
			if (! EntityUtil.isEntityValid(living)) return false;
			return this.attacker.canSee(living) && EntityUtil.getAttackRange(attacker, living, 3F) >= EntityUtil.getNearestDistance(this.attacker, living);
		}
		
		@Override
		public boolean canContinueToUse() {
			LivingEntity living = this.attacker.getTarget();
			if (! EntityUtil.isEntityValid(living)) return false;
			return this.attacker.canSee(living) && EntityUtil.getAttackRange(attacker, living, 3F) >= EntityUtil.getNearestDistance(this.attacker, living);
		}
		
		@Override
		public void stop() {
			this.attacker.setTarget(null);
			this.attacker.setAttackTime(0);
		}
		
		@Override
		public void tick() {
			LivingEntity target = this.attacker.getTarget();
			this.attacker.getLookControl().setLookAt(target, 30F, 30F);
			if(this.attacker.getAttackTime() == 0) {
				if(this.attacker.getAttackDamage() >= EntityUtil.getCurrentHealth(target)) {
					this.attacker.setAttackTime(1);
				} else {
					this.attacker.setAttackTime(- 1);
				}
			} else if(this.attacker.getAttackTime() > 0) {
				this.attacker.setAttackTime(this.attacker.getAttackTime() + 1);
				if(this.attacker.getAttackTime() == this.attacker.getAttackCD() * 4 / 5) {
				    this.attacker.attackTarget(target);
				} else if(this.attacker.getAttackTime() >= this.attacker.getAttackCD()) {
					this.attacker.setAttackTime(0);
				}
			} else {
				this.attacker.setAttackTime(this.attacker.getAttackTime() - 1);
				if(- this.attacker.getAttackTime() == this.attacker.getAttackCD() * 4 / 5) {
				    this.attacker.attackTarget(target);
				} else if(- this.attacker.getAttackTime() >= this.attacker.getAttackCD()) {
					this.attacker.setAttackTime(0);
				}
			}
		}
		
	}

}
