package com.hungteen.pvz.entity.zombie.base;

import com.hungteen.pvz.entity.PVZMultiPartEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.utils.interfaces.IMultiPartZombie;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class DefenceZombieEntity extends PVZZombieEntity implements IMultiPartZombie{

	private static final DataParameter<Float> DEFENCE_LIFE = EntityDataManager.createKey(DefenceZombieEntity.class, DataSerializers.FLOAT);
	protected PVZHealthPartEntity part;
	
	public DefenceZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		resetParts();
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(DEFENCE_LIFE, 0f);
	}

	@Override
	public void removeParts() {
		if(this.part != null) {
			this.part.remove();
			this.part = null;
		}
	}
	
	@Override
	public void updateParts() {
		if(this.part != null) {
			if(!this.part.shouldContinuePersisting()) {
				this.world.addEntity(this.part);
			}
			float j = 2 * 3.14159f * this.rotationYaw / 360;
			float dis = this.getPartOffset();
			Vec3d pos = this.getPositionVec();
			this.part.prevRotationYaw = this.rotationYaw;
			this.part.prevRotationPitch = this.rotationPitch;
			this.part.setLocationAndAngles(pos.getX() - Math.sin(j) * dis, pos.getY() + 0.3f, pos.getZ() + Math.cos(j) * dis, this.rotationYaw, this.rotationPitch);
			this.part.setOwner(this);
		}
	}
	
	public PVZMultiPartEntity[] getParts() {
		return new PVZMultiPartEntity[] {this.part};
	}
	
	@Override
	public boolean canPartsBeRemoved() {
		return this.getDefenceLife() == 0;
	}
	
	public float getPartOffset() {
		return 0.55f;
	}
	
	@Override
	public void tick() {
		super.tick();
		updateParts();
	}
	
	public float getDefenceLife() {
		return dataManager.get(DEFENCE_LIFE);
	}
	
	public void setDefenceLife(float life) {
		dataManager.set(DEFENCE_LIFE, life);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setDefenceLife(compound.getFloat("defence_life"));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putFloat("defence_life", this.getDefenceLife());
	}
	
}
