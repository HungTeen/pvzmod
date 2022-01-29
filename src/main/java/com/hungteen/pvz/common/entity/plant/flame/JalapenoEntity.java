package com.hungteen.pvz.common.entity.plant.flame;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity.ElementTypes;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.entity.zombie.zombotany.JalapenoZombieEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.WorldUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JalapenoEntity extends PlantBomberEntity{

	public JalapenoEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb(boolean server) {
		final float range = this.getExplodeRange();
		if(server) {
			//deal damage.
			fireMob(this, range, 1F);
		    fireMob(this, 1F, range);
		    //clear ice balls.
		    ElementBallEntity.killElementBalls(this, 40, ElementTypes.ICE);
			EntityUtil.playSound(this, SoundRegister.JALAPENO.get());
		}
		clearSnowAndSpawnFlame(this, (int) range);
	}
	
	/**
	 * jalapeno fire mobs.
	 * {@link #startBomb(boolean)}
	 */
	public static void fireMob(LivingEntity entity, float dx, float dz) {
		final AxisAlignedBB aabb = new AxisAlignedBB(entity.position().add(dx, 1, dz), entity.position().add(- dx, - 1, - dz));
		for(Entity target : EntityUtil.getWholeTargetableEntities(entity, aabb)) {
			float damage = 0;
			if(entity instanceof JalapenoEntity) {
				damage = ((JalapenoEntity) entity).getExplodeDamage();
			} else if(entity instanceof JalapenoZombieEntity) {
				if(target instanceof LivingEntity) {
					damage = EntityUtil.getMaxHealthDamage((LivingEntity) target, 2);
				} else {
					damage = 100F;
				}
			}
			target.hurt(PVZEntityDamageSource.causeFlameDamage(entity, entity).setExplosion(), damage);
		}
		PVZPlantEntity.clearLadders(entity, aabb);
	}
	
	/**
	 * spawn flame particle and clear snow.
	 * {@link #startBomb(boolean)}
	 */
	public static void clearSnowAndSpawnFlame(LivingEntity entity, int range) {
		final boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(entity.level, entity);
		//handle cross .
		for(int i = - range; i <= range; ++ i) {
			spawnFlame(entity, i, 0);
			spawnFlame(entity, 0, i);
			if(flag) {
				clearSnow(entity, i, 0);
				clearSnow(entity, 0, i);
			}
		}
		//clear range snow.
		if(flag) {
			for(int i = - range / 2; i <= range / 2; ++ i) {
			    for(int j = - range / 2; j <= range / 2; ++ j) {
				    clearSnow(entity, i, j);
			    }
			}
		}
	}
	
	/**
	 * spawn flame particle.
	 */
	private static void spawnFlame(LivingEntity entity, int dx, int dz) {
		if(entity.level.isClientSide) {
			for(int i = 0; i < 20; ++ i) {
				WorldUtil.spawnRandomSpeedParticle(entity.level, ParticleTypes.FLAME, entity.position().add(dx, 0, dz), 0.1F);
			}
		}
	}
	
	/**
	 * clear snow around.
	 */
	private static void clearSnow(LivingEntity entity, int dx, int dz) {
		if(! entity.level.isClientSide) {
		    final BlockPos pos = entity.blockPosition().offset(dx, 0, dz);
		    if(entity.level.getBlockState(pos).getBlock() == Blocks.SNOW || entity.level.getBlockState(pos).getBlock() == Blocks.SNOW_BLOCK) {
			    entity.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		    }
		}
	}

	@Override
	public float getExplodeRange() {
		return 20;
	}

	@Override
	public float getExplodeDamage() {
		return this.getSkillValue(SkillTypes.NORMAL_BOMB_DAMAGE);
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.7f, 1.5f);
	}

	@Override
	public int getReadyTime() {
		return 20;
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.JALAPENO;
	}

}
