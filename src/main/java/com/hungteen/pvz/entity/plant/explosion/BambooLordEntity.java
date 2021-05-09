package com.hungteen.pvz.entity.plant.explosion;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.entity.bullet.itembullet.FireCrackerEntity;
import com.hungteen.pvz.entity.misc.FireCrackersEntity;
import com.hungteen.pvz.entity.plant.base.PlantCloserEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BambooLordEntity extends PlantCloserEntity {

	public static final int UP_CD = 20;
	public static final float UP_SPEED = 2F;
	public BambooLordEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
				if(this.getAttackTime() <= 0) {
					this.explode();
				}
			}
		}
	}
	
	protected void explode() {
		float range = 40;
		List<LivingEntity> list = new ArrayList<>();
		EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
			if(this.getSensing().canSee(target)) {
				list.add(target);
			}
		});
		if(! list.isEmpty()) {
			for(int i = 0; i < this.getSplitCount(); ++ i) {
				int pos = this.getRandom().nextInt(list.size());
				FireCrackerEntity entity = new FireCrackerEntity(level, this);
				entity.setPos(this.getX(), this.getY(), this.getZ());
				entity.shoot(list.get(pos));
				level.addFreshEntity(entity);
			}
		}
		for(int i = 0; i < 3; ++ i) {
			EntityUtil.spawnParticle(this, 5);
		}
		this.remove();
	}
	
	@Override
	public void performAttack(LivingEntity target1) {
		if(this.getAttackTime() != 0) return ;
		this.setAttackTime(UP_CD);
		for(int i = 0; i < 3; ++ i) {
			EntityUtil.spawnParticle(this, 5);
		}
		float range = 1.5F;
		EntityUtil.getAttackEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
			target.hurt(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
			target.setDeltaMovement(target.getDeltaMovement().add(0, UP_SPEED, 0));
		});
		EntityUtil.playSound(this, SoundRegister.POTATO_MINE.get());
		this.setDeltaMovement(this.getDeltaMovement().add(0, UP_SPEED, 0));
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		for(int i = 0; i < this.getCrackersNum(); ++ i) {
			this.generateCrackers();
		}
	}
	
	private void generateCrackers() {
		float rotate = this.getRandom().nextFloat() * 2 * 3.14159F;
		final int len = 10;
		double dx = Math.sin(rotate);
		double dz = Math.cos(rotate);
		for(int i = 0; i < len; ++ i) {
			BlockPos pos = this.blockPosition().offset(dx * i * 2, 2, dz * i * 2);
			FireCrackersEntity entity = new FireCrackersEntity(level, this);
			entity.setPos(pos.getX(), pos.getY(), pos.getZ());
			entity.setFuse(10 + 6 * i);
			level.addFreshEntity(entity);
		}
	}
	
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			return 57F + 3 * lvl;
		}
		return 120F;
	}
	
	public int getSplitCount() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 5;
			return now + 2;
		}
		return 5;
	}
	
	public int getCrackersNum() {
		if(this.isPlantInStage(1)) return 1;
		if(this.isPlantInStage(2)) return 2;
		return 3;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.6F, 1F);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.BAMBOO_LORD;
	}

}
