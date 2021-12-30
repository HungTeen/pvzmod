package com.hungteen.pvz.common.entity.plant.explosion;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ExplodeONutEntity extends WallNutEntity {

	public ExplodeONutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(! this.level.isClientSide) {
			this.explode(this);
		} else {
			for(int i = 0; i < 5; ++ i) {
		        this.level.addParticle(ParticleRegister.RED_BOMB.get(), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
			}
		}
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.explode(this);
		EntityUtil.spawnParticle(this, 0);
		final float range = 25F;
		EntityUtil.getRandomLivingInRange(level, this, EntityUtil.getEntityAABB(this, range, range), this.getExtraAttackChance()).forEach(target -> {
			this.explode(target);
			EntityUtil.spawnParticle(target, 0);
		});
	}
	
	/**
	 * explode at specific entity.
	 */
	public void explode(Entity entity) {
		final float range = 4F;
		EntityUtil.getWholeTargetableEntities(this, EntityUtil.getEntityAABB(entity, range, range)).forEach(target -> {
			target.hurt(PVZDamageSource.explode(this), this.getAttackDamage());
		});
		EntityUtil.playSound(this, SoundRegister.CHERRY_BOMB.get());
	}
	
	public int getExtraAttackChance() {
		return 1;
//		return this.getThreeStage(1, 2, 3);
	}
	
	public float getAttackDamage(){
		return 150F;
//		return this.getAverageProgress(150F, 650F);
	}
	
	@Override
	public float getSuperLife() {
		return 0;
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.EXPLODE_O_NUT;
	}

}
