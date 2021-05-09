package com.hungteen.pvz.entity.plant.enforce;

import java.util.List;

import com.hungteen.pvz.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.roof.BungeeZombieEntity;
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
	
	private void tickLeaf() {
		float range = this.getProtectRange();
		List<Entity> list = level.getEntitiesOfClass(Entity.class, EntityUtil.getEntityAABB(this, range, 3), (target) -> {
			if(target instanceof PultBulletEntity) return EntityUtil.checkCanEntityAttack(this, target);
			if(target instanceof BungeeZombieEntity) return EntityUtil.checkCanEntityAttack(this, target);
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
		list.forEach((target) -> {
			if(target instanceof BungeeZombieEntity) {
				((BungeeZombieEntity) target).pushBack();
			} else if(target instanceof PultBulletEntity){
				((PultBulletEntity) target).pushBack();
			}
		});
	}
	
	public float getProtectRange() {
		if(this.isPlantInStage(1)) return 2.5F;
		if(this.isPlantInStage(2)) return 3.5F;
		return 5F;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.7F, 1.2F);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.UMBRELLA_LEAF;
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
	public int getSuperTimeLength() {
		return 0;
	}
	
}
