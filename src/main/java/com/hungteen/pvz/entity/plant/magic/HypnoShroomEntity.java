package com.hungteen.pvz.entity.plant.magic;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.interfaces.IShroomPlant;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.misc.damage.PVZDamageType;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class HypnoShroomEntity extends PVZPlantEntity implements IShroomPlant{

	public HypnoShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if(!world.isRemote && !this.isPlantSleeping()) {
			if(cause instanceof PVZDamageSource && ((PVZDamageSource) cause).getPVZDamageType() == PVZDamageType.EAT) {
				if(this.isPlantInSuperMode()) {
					
				}else {
					if(cause.getTrueSource() instanceof PVZZombieEntity) {
					    PVZZombieEntity zombie = (PVZZombieEntity) cause.getTrueSource();
					    zombie.onCharmed();
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
	
	public float getHealHealth() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 4;
			return 0.6f + 0.1f * now;
		}
		return 0.6f;
	}
	
	/**
	 * the current health of gargangtuar when summoning 
	 */
	public float getSummonHealth() {
		int lvl = this.getPlantLvl();
		if(lvl <= 6) {
			return 0.5f;
		}else if(lvl <= 13) {
			return 0.75f;
		}else if(lvl <= 20) {
			return 1f;
		}
		return 0.5f;
	}
	
	/**
	 * HypnoShoorm has no super mode now.
	 */
	@Override
	public int getSuperTimeLength() {
		return 0;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.HYPNO_SHROOM;
	}

}
