package com.hungteen.pvz.common.entity.effect;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * used to display the effect of radiationn of origin block.
 */
public class OriginEffectEntity extends AbstractEffectEntity {

	private static final DataParameter<Float> LENGTH = EntityDataManager.defineId(OriginEffectEntity.class, DataSerializers.FLOAT);
	private static final DataParameter<Integer> COLOR = EntityDataManager.defineId(OriginEffectEntity.class, DataSerializers.INT);

	public OriginEffectEntity(EntityType<?> type, World world) {
		super(type, world);
		this.maxEffectTick = 100;
	}

	public static void create(World world, BlockPos pos, int color){
		OriginEffectEntity effectEntity = EntityRegister.ORIGIN_EFFECT.get().create(world);
		final boolean exist = EntityUtil.hasNearBy(world, pos, 5, e -> e instanceof OriginEffectEntity);
		if(! exist) {// avoid overlapped.
			EntityUtil.onEntitySpawn(world, effectEntity, pos);
			effectEntity.setColor(color);
		}
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(LENGTH, 1F);
		this.entityData.define(COLOR, 255);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("effect_color")){
			this.setColor(compound.getInt("effect_color"));
		}
		if(compound.contains("effect_length")){
			this.setLength(compound.getFloat("effect_length"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("effect_color", this.getColor());
		compound.putFloat("effect_length", this.getLength());
	}

	public void setColor(int color){
		this.entityData.set(COLOR, color);
	}

	public int getColor(){
		return this.entityData.get(COLOR);
	}

	public void setLength(float l){
		this.entityData.set(LENGTH, l);
	}

	public float getLength(){
		return this.entityData.get(LENGTH);
	}
}
