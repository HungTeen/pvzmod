package com.hungteen.pvz.common.entity.plant.toxic;

import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.SporeEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.ShootTypes;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class PuffShroomEntity extends PlantShooterEntity {

	protected static final double SHOOT_OFFSET = 0.1D;
	
	public PuffShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void shootBullet() {
		if(this.isPlantInSuperMode()) {
			this.performShoot(SHOOT_OFFSET, 0, -0.2F, this.getExistTick() % 5 == 0, ShootTypes.FORWARD);
		} else {
			this.performShoot(SHOOT_OFFSET, 0, -0.2F, this.getAttackTime() == 1, ShootTypes.FORWARD);
		}
	}
	
	@Override
	protected AbstractBulletEntity createBullet() {
		return new SporeEntity(this.level, this);
	}
	
	@Override
	protected SoundEvent getShootSound() {
		return SoundRegister.PUFF.get();
	}

	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(first) {
			int cnt = 1;
			final int range = 20;
			for(PuffShroomEntity shroom : level.getEntities(EntityRegister.PUFF_SHROOM.get(), EntityUtil.getEntityAABB(this, range, range), shroom -> {
				return ! EntityUtil.canTargetEntity(this, shroom);
			})) {
				if(shroom.canStartSuperMode()) {
				    shroom.startSuperMode(false);
				    ++ cnt;
				    if(cnt >= this.getMaxSuperCnt()) {
				    	break;
				    }
				}
			}
			PlayerEntity player = EntityUtil.getEntityOwner(level, this);
			if(player != null && player instanceof ServerPlayerEntity) {
				EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this, cnt);
			}
		}
	}
	
	public int getMaxSuperCnt() {
		return this.isPlantInStage(1) ? 10 : this.isPlantInStage(2) ? 15 : 25;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5f, 0.5f);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	@Override
	public float getPlantHealth() {
		return MathUtil.getProgressAverage(this.getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 30, 50);
	}
	
	@Override
	public float getShootRange() {
		return 10;
	}
	
	@Override
	public float getShootHeight() {
		return 2;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.PUFF_SHROOM;
	}

}
