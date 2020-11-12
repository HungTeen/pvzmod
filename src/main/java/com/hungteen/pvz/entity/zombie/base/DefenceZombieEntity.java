package com.hungteen.pvz.entity.zombie.base;

import com.hungteen.pvz.entity.PVZMultiPartEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZZombiePartEntity;
import com.hungteen.pvz.utils.interfaces.IMultiPartZombie;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class DefenceZombieEntity extends PVZZombieEntity implements IMultiPartZombie{

	protected PVZZombiePartEntity part;
	
	public DefenceZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
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
		return this.part == null;
	}
	
	public float getPartOffset() {
		return 0.5f;
	}
	
	@Override
	public void tick() {
		super.tick();
		updateParts();
	}
	
}
