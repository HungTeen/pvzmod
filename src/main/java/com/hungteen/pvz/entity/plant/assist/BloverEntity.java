package com.hungteen.pvz.entity.plant.assist;

import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
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
		if(! world.isRemote && this.getAttackTime() == 5) {
			this.blow();
		}
	}
	
	private void blow() {
		if(! this.world.isRemote) {
			float len = 30;
			AxisAlignedBB aabb = EntityUtil.getEntityAABB(this, len, len);
			EntityUtil.getEntityTargetableEntity(this, aabb).forEach((target) -> {
				if(EntityUtil.isEntityInSky(target)) {
					target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this), this.getAttackDamage());
					Vec3d speed = target.getMotion();
					double dx = target.getPosX() - this.getPosX();
					double dz = target.getPosZ() - this.getPosZ();
					double tot = MathHelper.sqrt(dx * dx + dz * dz);
					double lvl = this.getForceLevel() * 2.5F;
					double speedX = dx / tot * lvl;
					double speedZ = dz / tot * lvl;
					target.setMotion(speed.x + speedX, speed.y, speed.z + speedZ);
				}
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
	
	public float getForceLevel() {
		if(this.isPlantInStage(1)) return 1;
		if(this.isPlantInStage(2)) return 2;
		return 3;
	}
	
	@Override
	public int getReadyTime() {
		return 50;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.5F, 1.5F);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.BLOVER;
	}

}
