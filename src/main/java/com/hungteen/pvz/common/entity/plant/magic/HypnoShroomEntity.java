package com.hungteen.pvz.common.entity.plant.magic;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity.Type;
import com.hungteen.pvz.common.entity.zombie.roof.GargantuarEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.misc.damage.PVZDamageType;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class HypnoShroomEntity extends PVZPlantEntity {

	public HypnoShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(! level.isClientSide && ! this.canPlantNormalUpdate()) {
			if(cause instanceof PVZDamageSource && ((PVZDamageSource) cause).getPVZDamageType() == PVZDamageType.EAT) {
				if(this.isPlantInSuperMode()) {
					if(cause.getEntity() != null) {
						cause.getEntity().remove();
						GargantuarEntity gar = EntityRegister.GARGANTUAR.get().create(level);
						EntityUtil.onEntitySpawn(level, gar, cause.getEntity().blockPosition());
						this.perfromCharmTo(gar);
						gar.setZombieType(Type.NORMAL);
						gar.setHealth(gar.getMaxHealth() * this.getSummonHealth());
						gar.setCharmed(! this.isCharmed());
					}
				} else {
					if(cause.getEntity() instanceof PVZZombieEntity) {
					    PVZZombieEntity zombie = (PVZZombieEntity) cause.getEntity();
					    if(zombie.getZombieLevel() <= this.getPlantLvl()) {
					        zombie.onCharmedBy(this);
					        zombie.healZombie(EntityUtil.getCurrentMaxHealth(zombie) * this.getHealHealth());
					        this.perfromCharmTo(zombie);
					    }
				    }
				}
			}
		}
	}
	
	protected void perfromCharmTo(PVZZombieEntity zombie) {
		EntityUtil.playSound(this, SoundRegister.CHARM.get());
		zombie.updateZombieLevel(Math.min(ZombieUtil.MAX_ZOMBIE_LEVEL, this.getPlantLvl()));
	}
	
	@Override
	public float getPlantHealth() {
		return 30;
	}
	
	public float getHealHealth() {
		return PlantUtil.getPlantAverageProgress(this, 0.2F, 1F);
	}
	
	/**
	 * the current health of gargangtuar when summoning.
	 */
	public float getSummonHealth() {
		return this.isPlantInStage(1) ? 0.5F : this.isPlantInStage(2) ? 0.75F : 1F;
	}
	
	@Override
	public boolean isPlantImmuneTo(DamageSource source) {
		return false;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.7f, 1.9f);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 2400;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.HYPNO_SHROOM;
	}

}
