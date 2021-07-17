package com.hungteen.pvz.common.entity.plant.explosion;

import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;

public class CherryBombEntity extends PlantBomberEntity{

	public CherryBombEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb(boolean server) {
		if(server) {
			int deathCnt = 0;
			final float range = 4.5F;
			for(Entity target : EntityUtil.getWholeTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range))) {
				target.hurt(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
				if(! EntityUtil.isEntityValid(target)) {
					++ deathCnt;
				}
			}
			EntityUtil.playSound(this, SoundRegister.CHERRY_BOMB.get());
			//trigger advancement.
			PlayerEntity owner = EntityUtil.getEntityOwner(level, this);
			if(owner != null && owner instanceof ServerPlayerEntity) {
				EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) owner, this, deathCnt);
			}
		} else {
			for(int i = 0; i < 5; ++ i) {
		        this.level.addParticle(ParticleRegister.RED_BOMB.get(), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
			}
		}
	}
	
	/**
	 * explosion damage.
	 * {@link #startBomb()}
	 */
	public float getAttackDamage(){
		return MathUtil.getProgressAverage(this.getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 150, 650);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.9f, 1f, false);
	}
	
	@Override
	public int getReadyTime() {
		return 30;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.CHERRY_BOMB;
	}

}
