package com.hungteen.pvz.entity.plant.assist;

import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BloverEntity extends PlantBomberEntity {

	public BloverEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb() {
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide && this.getAttackTime() == 5) {
			this.blow();
		}
	}
	
	private void blow() {
		if(! this.level.isClientSide) {
			float len = 30;
			AxisAlignedBB aabb = EntityUtil.getEntityAABB(this, len, len);
			EntityUtil.getEntityTargetableEntity(this, aabb).forEach((target) -> {
				if(EntityUtil.isEntityInSky(target)) {
					target.hurt(PVZDamageSource.causeNormalDamage(this, this), this.getAttackDamage());
					Vector3d speed = target.getDeltaMovement();
					double dx = target.getX() - this.getX();
					double dz = target.getZ() - this.getZ();
					double tot = MathHelper.sqrt(dx * dx + dz * dz);
					double lvl = this.getForceLevel() * 2.5F;
					double speedX = dx / tot * lvl;
					double speedZ = dz / tot * lvl;
					target.setDeltaMovement(speed.x + speedX, speed.y, speed.z + speedZ);
				}
			});
			level.getEntitiesOfClass(PlayerEntity.class, EntityUtil.getEntityAABB(this, len, len), (player) -> {
				return ! EntityUtil.checkCanEntityAttack(this, player);
			}).forEach((player) -> {
				PlayerUtil.addPlayerStats(player, Resources.NO_FOG_TICK, 2400 * this.getForceLevel());
			});
		}
	}
	
	@Override
	protected boolean canPlantTarget(LivingEntity entity) {
		if(entity instanceof BalloonZombieEntity) return true;
		return super.canPlantTarget(entity);
	}
	
	@Override
	protected SoundEvent getSpawnSound() {
		return SoundRegister.BLOVER.get();
	}
	
	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 28.5F + 1.5F * lvl;
		return 60;
	}
	
	public int getForceLevel() {
		if(this.isPlantInStage(1)) return 1;
		if(this.isPlantInStage(2)) return 2;
		return 3;
	}
	
	@Override
	public int getReadyTime() {
		return 50;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5F, 1.5F);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.BLOVER;
	}

}
