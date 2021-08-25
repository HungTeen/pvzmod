package com.hungteen.pvz.common.entity.plant.assist;

import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.entity.zombie.pool.BalloonZombieEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BloverEntity extends PlantBomberEntity {

	public BloverEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb(boolean server) {
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide && this.getAttackTime() == 5) {
			this.blow();
		}
	}
	
	public void blow() {
		if(! this.level.isClientSide) {
			final float len = 30;
			//deal damage.
			EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, len, len)).forEach(target -> {
				if(EntityUtil.isEntityInSky(target)) {
					target.hurt(PVZDamageSource.normal(this).setMustHurt(), this.getAttackDamage());
					final Vector3d speed = target.getDeltaMovement();
					final double lvl = this.getForceLevel() * 2.5F;
					final Vector3d delta = MathUtil.getHorizontalNormalizedVec(this.position(), target.position()).scale(lvl);
					target.setDeltaMovement(speed.x + delta.x, speed.y, speed.z + delta.z);
				}
			});
			//dispel fog.
			level.getEntitiesOfClass(PlayerEntity.class, EntityUtil.getEntityAABB(this, len, len), player -> ! EntityUtil.canTargetEntity(this, player)).forEach((player) -> {
				PlayerUtil.addPlayerStats(player, Resources.NO_FOG_TICK, 2400 * this.getForceLevel());
			});
		}
	}
	
	@Override
	public boolean canPlantTarget(Entity entity) {
		if(entity instanceof BalloonZombieEntity) {
			return true;
		}
		return super.canPlantTarget(entity);
	}
	
	@Override
	protected SoundEvent getSpawnSound() {
		return SoundRegister.BLOVER.get();
	}
	
	public float getAttackDamage(){
		return PlantUtil.getPlantAverageProgress(this, 10, 50);
	}
	
	public int getForceLevel() {
		return this.isPlantInStage(1) ? 1 : this.isPlantInStage(2) ? 2 : 3;
	}
	
	@Override
	public int getReadyTime() {
		return 40;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5F, 1.5F);
	}

	@Override
	public PlantType getPlantType() {
		return PVZPlants.BLOVER;
	}

}
