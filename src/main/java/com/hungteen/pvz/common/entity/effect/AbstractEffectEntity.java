package com.hungteen.pvz.common.entity.effect;

import com.hungteen.pvz.common.entity.PVZEntityBase;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractEffectEntity extends PVZEntityBase {

	private static final DataParameter<Integer> EXIST_TICK = EntityDataManager.defineId(AbstractEffectEntity.class,
			DataSerializers.INT);
	protected int maxEffectTick;
	
	public AbstractEffectEntity(EntityType<?> type, World world) {
		super(type, world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(EXIST_TICK, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! this.level.isClientSide) {
			this.setExistTick(this.getExistTick() + 1);
			if(this.getExistTick() > this.maxEffectTick) {
				this.remove();
			}
		}
	}
	
	/**
	 * Checks if the entity is in range to render.
	 */
	@OnlyIn(Dist.CLIENT)
	public boolean shouldRenderAtSqrDistance(double distance) {
		double d0 = this.getBoundingBox().getSize() * 4.0D;
		if (Double.isNaN(d0)) {
			d0 = 4.0D;
		}
		d0 = d0 * 256.0D;
		return distance < d0 * d0;
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("effect_exist_tick", this.getExistTick());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("effect_exist_tick")) {
			this.setExistTick(compound.getInt("effect_exist_tick"));
		}
	}

	public int getExistTick() {
		return this.entityData.get(EXIST_TICK);
	}

	public void setExistTick(int tick) {
		this.entityData.set(EXIST_TICK, tick);
	}

}
