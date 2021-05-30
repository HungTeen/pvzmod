package com.hungteen.pvz.common.entity.zombie.base;

import com.hungteen.pvz.common.entity.PVZMultiPartEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.utils.interfaces.IMultiPartZombie;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class DefenceZombieEntity extends PVZZombieEntity implements IMultiPartZombie{

	protected PVZHealthPartEntity part;
	
	public DefenceZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setDefenceLife(this.getPartLife());
		resetParts();
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
			if(! this.part.shouldContinuePersisting()) {
				this.level.addFreshEntity(this.part);
			}
			float j = 2 * 3.14159f * this.yRot / 360;
			float dis = this.getPartOffset();
			Vector3d pos = this.position();
			this.part.yRotO = this.yRot;
			this.part.xRotO = this.xRot;
			this.part.moveTo(pos.x() - Math.sin(j) * dis, pos.y() + this.getPartHeightOffset(), pos.z() + Math.cos(j) * dis, this.yRot, this.xRot);
			this.part.setOwner(this);
		}
	}
	
	@Override
	public void onZombieBeMini() {
		super.onZombieBeMini();
		this.setDefenceLife(this.getPartLife() * 0.6F);
	}
	
	public abstract float getPartLife();
	
	public SoundEvent getPartHurtSound() {
		return null;
	}
	
	public SoundEvent getPartDeathSound() {
		return null;
	}
	
	protected float getPartHeightOffset() {
		if(this.isMiniZombie()) return 0.1F;
		return 0.2f;
	}
	
	public PVZMultiPartEntity[] getMultiParts() {
		return new PVZMultiPartEntity[] {this.part};
	}
	
	@Override
	public boolean canPartsBeRemoved() {
		return this.getDefenceLife() == 0;
	}
	
	public float getPartOffset() {
		if(this.isMiniZombie()) return 0.3F;
		return 0.55f;
	}
	
	@Override
	public void tick() {
		super.tick();
		updateParts();
	}

}
