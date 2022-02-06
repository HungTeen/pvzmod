package com.hungteen.pvz.common.entity.plant.enforce;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.interfaces.ICanPushBack;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

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
		final float range = this.getWorkRange();
		List<Entity> list = level.getEntitiesOfClass(Entity.class, EntityUtil.getEntityAABB(this, range, range).inflate(0.25), target -> {
			return EntityUtil.canTargetEntity(this, target);
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
			if(target instanceof ICanPushBack){
				((ICanPushBack) target).pushBack();
			}
		});
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.add(Pair.of(PAZAlmanacs.WORK_RANGE, this.getWorkRange()));
	}

	public float getWorkRange(){
		return 4.5F;
	}
	
	@Override
	public boolean canPAZTarget(Entity target) {
		return target instanceof ICanPushBack;
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
	public IPlantType getPlantType() {
		return PVZPlants.UMBRELLA_LEAF;
	}
	
}
