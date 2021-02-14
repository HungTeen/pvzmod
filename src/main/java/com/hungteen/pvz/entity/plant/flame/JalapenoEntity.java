package com.hungteen.pvz.entity.plant.flame;

import com.hungteen.pvz.entity.misc.ElementBallEntity;
import com.hungteen.pvz.entity.misc.ElementBallEntity.ElementTypes;
import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.entity.zombie.zombotany.JalapenoZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JalapenoEntity extends PlantBomberEntity{

	public JalapenoEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb() {
		int range = this.getFireRange();
		for(int i = - range; i <= range; ++ i) {
			clearSnowAndSpawnFlame(this, i, 0);
			clearSnowAndSpawnFlame(this, 0, i);
		}
		if(! world.isRemote) {
			EntityUtil.playSound(this, SoundRegister.JALAPENO.get());
		}
		fireMob(this, range, 0.5f);
		fireMob(this, 0.5f, range);
		if(! this.isCharmed()) {
			killIceBall(this);
		}
	}
	
	/**
	 * kill zomboss iceball
	 */
	public static void killIceBall(LivingEntity entity) {
		if(! entity.world.isRemote) {
			float range = 10;
			if(entity instanceof JalapenoEntity) range = ((JalapenoEntity) entity).getFireRange() + 10F;
			else if(entity instanceof JalapenoZombieEntity) range = ((JalapenoZombieEntity) entity).getFireRange();
			entity.world.getEntitiesWithinAABB(ElementBallEntity.class, EntityUtil.getEntityAABB(entity, range, range), (target) -> {
				return target.getElementBallType() == ElementTypes.ICE;
			}).forEach((target) -> {
				target.onKilledByPlants(entity);
			});
		}
	}
	
	public static void fireMob(LivingEntity entity, float dx, float dz) {
		BlockPos pos = entity.getPosition();
		double x = pos.getX() + 0.5f;
		double y = pos.getY() + 0.5f;
		double z = pos.getZ() + 0.5f;
		AxisAlignedBB aabb = new AxisAlignedBB(x + dx, y + 1, z + dz, x - dx, y - 1, z - dz);
		for(LivingEntity target : EntityUtil.getEntityTargetableEntity(entity, aabb)) {
			float damage = 0;
			if(entity instanceof JalapenoEntity) damage = ((JalapenoEntity) entity).getAttackDamage();
			else if(entity instanceof JalapenoZombieEntity) damage = EntityUtil.getCurrentMaxHealth(target) * 2;
			target.attackEntityFrom(PVZDamageSource.causeFireDamage(entity, entity), damage);
		}
	}
	
	/**
	 * clear snow
	 */
	public static void clearSnowAndSpawnFlame(LivingEntity entity, int dx, int dz) {
		BlockPos pos = entity.getPosition().add(dx, 0, dz);
		if(entity.world.getBlockState(pos).getBlock() == Blocks.SNOW || entity.world.getBlockState(pos).getBlock()==Blocks.SNOW_BLOCK) {
			entity.world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		if(entity.world.isRemote) {
			for(int i = 0; i < 30; ++ i) {
				entity.world.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 
						(entity.getRNG().nextFloat() - 0.5) / 10, entity.getRNG().nextFloat() / 8, (entity.getRNG().nextFloat() - 0.5) / 10);
			}
		}
	}

	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl <= 19) {
			return 139.5f + 0.5f * lvl;
		}
		return 150;
	}
	
	public int getFireRange() {
		if(this.isPlantInStage(1)) return 10;
		if(this.isPlantInStage(2)) return 15;
		return 20;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.7f, 1.5f);
	}

	@Override
	public int getReadyTime() {
		return 30;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.JALAPENO;
	}

}
