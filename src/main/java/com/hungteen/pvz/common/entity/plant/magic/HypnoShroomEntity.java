package com.hungteen.pvz.common.entity.plant.magic;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.GargantuarEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.misc.damage.PVZDamageType;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class HypnoShroomEntity extends PVZPlantEntity {

	public HypnoShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(! level.isClientSide && ! this.isPlantSleeping()) {
			if(cause instanceof PVZDamageSource && ((PVZDamageSource) cause).getPVZDamageType() == PVZDamageType.EAT) {
				if(this.isPlantInSuperMode()) {
					if(cause.getEntity() != null) {
						cause.getEntity().remove();
						GargantuarEntity gar = EntityRegister.GARGANTUAR.get().create(level);
						EntityUtil.onEntitySpawn(level, gar, cause.getEntity().blockPosition());
						gar.setHealth(gar.getMaxHealth() * this.getSummonHealth());
						gar.onCharmedBy(this);
					}
				} else {
					if(cause.getEntity() instanceof PVZZombieEntity) {
					    PVZZombieEntity zombie = (PVZZombieEntity) cause.getEntity();
					    zombie.onCharmedBy(this);
					    EntityUtil.playSound(this, SoundRegister.CHARM.get());
					    float life = zombie.getHealth();
					    float max = zombie.getMaxHealth();
					    float std = max * this.getHealHealth();
					    float need = MathHelper.clamp(std - life, 0, max);
					    zombie.heal(need);
				    }
				}
			}
		}
	}
	
	@Override
	public float getPlantHealth() {
		return 30;
	}
	
	public float getHealHealth() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) {
			return 0.12f + 0.08f * lvl;
		}
		return 1f;
	}
	
	/**
	 * the current health of gargangtuar when summoning 
	 */
	public float getSummonHealth() {
		if(this.isPlantInStage(1)) return 0.5f;
		if(this.isPlantInStage(2)) return 0.75f;
		return 1f;
	}
	
	@Override
	public boolean isPlantImmuneTo(DamageSource source) {
		return false;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.7f, 1.9f);
	}
	
	/**
	 * HypnoShoorm has no super mode now.
	 */
	@Override
	public int getSuperTimeLength() {
		return 2400;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.HYPNO_SHROOM;
	}

}
