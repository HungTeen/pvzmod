package com.hungteen.pvz.entity.plant.magic;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.interfaces.IShroomPlant;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.misc.damage.PVZDamageType;
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
			return 0.58f + 0.02f * lvl;
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
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.6f, 1.6f);
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
