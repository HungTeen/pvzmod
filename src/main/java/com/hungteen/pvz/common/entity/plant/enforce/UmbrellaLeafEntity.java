package com.hungteen.pvz.common.entity.plant.enforce;

import java.util.List;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class UmbrellaLeafEntity extends PVZPlantEntity{

	public static final int ANIM_TICK = 10;
	private int inc = 0;
	
	public UmbrellaLeafEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			this.tickLeaf();
			if(this.getAttackTime() == 0 && inc > 0) {
				EntityUtil.playSound(this, SoundRegister.DRAG.get());
			}
			this.setAttackTime(MathHelper.clamp(this.getAttackTime() + inc, 0, ANIM_TICK));
		}
	}
	
	/**
	 * {@link #normalPlantTick()}
	 */
	public void tickLeaf() {
		final float range = 4.5F;
		List<Entity> list = level.getEntitiesOfClass(Entity.class, EntityUtil.getEntityAABB(this, range, 3), target -> {
			if(target instanceof PultBulletEntity || target instanceof BungeeZombieEntity) {
				return EntityUtil.canTargetEntity(this, target);
			}
			return false;
		});
		if(list.isEmpty()) {
			if(this.inc != -1 && this.getAttackTime() != ANIM_TICK) {
			} else {
				this.inc = - 1;
			}
			return ;
		}
		this.inc = 1;
		list.forEach(target -> {
			if(target instanceof BungeeZombieEntity) {
				((BungeeZombieEntity) target).pushBack();
			} else if(target instanceof PultBulletEntity){
				((PultBulletEntity) target).pushBack();
			}
		});
	}
	
	@Override
	public boolean canPlantTarget(Entity target) {
		if(target instanceof BungeeZombieEntity) {
			return true;
		}
		return super.canPlantTarget(target);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.7F, 1.2F);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 0;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("anim_inc_num")) {
			this.inc = compound.getInt("anim_inc_num");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("anim_inc_num", this.inc);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.UMBRELLA_LEAF;
	}
	
}
