package com.hungteen.pvz.common.entity.zombie.base;

import com.hungteen.pvz.common.entity.PVZMultiPartEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IMultiPartZombie;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

/**
 * use outer defence life as extra life.
 */
public abstract class DefenceZombieEntity extends PVZZombieEntity implements IMultiPartZombie{

	protected PVZHealthPartEntity part;
	public boolean hitDefence = false;
	
	public DefenceZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		resetParts();
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! this.level.isClientSide) {
			updateParts();
		}
	}
	
	@Override
	public void removeParts() {
		if(EntityUtil.isEntityValid(this.part)) {
			this.part.remove();
			this.part = null;
		}
	}
	
	@Override
	public void updateParts() {
		if(this.canPartsExist()) {
			if(! EntityUtil.isEntityValid(part)) {
				this.resetParts();
			}
			if(! this.part.isAddedToWorld()) {
				this.level.addFreshEntity(this.part);
			}
			float j = 2 * 3.14159f * this.yRot / 360;
			float dis = this.getPartWidthOffset();
			Vector3d pos = this.position();
			this.part.yRotO = this.yRot;
			this.part.xRotO = this.xRot;
			this.part.moveTo(pos.x() - Math.sin(j) * dis, pos.y() + this.getPartHeightOffset(), pos.z() + Math.cos(j) * dis, this.yRot, this.xRot);
			this.part.setOwner(this);
		} else {
			this.removeParts();
		}
	}
	
	@Override
	public void onZombieBeMini() {
		super.onZombieBeMini();
		if(EntityUtil.isEntityValid(this.part)) {
			this.part.onOwnerBeMini(this);
		}
		this.setOuterDefenceLife(this.getOuterLife() * 0.6F);
	}
	
	@Override
	public PVZMultiPartEntity[] getMultiParts() {
		return new PVZMultiPartEntity[] {this.part};
	}
	
	@Override
	public boolean canPartsExist() {
		return this.getOuterDefenceLife() > 0;
	}

	@Override
	public void onOuterDefenceBroken() {
		super.onOuterDefenceBroken();
		if(! this.level.isClientSide){
			EntityUtil.playSound(this, this.getPartDeathSound());
		}
		this.hitDefence = false;
	}

	@Override
	public void onOuterDefenceHurt() {
		super.onOuterDefenceHurt();
		if(! this.level.isClientSide){
			EntityUtil.playSound(this, this.getPartHurtSound());
		}
		this.hitDefence = false;
	}

	@Override
	public boolean canOuterDefend(DamageSource source) {
		return super.canOuterDefend(source) && this.hitDefence;
	}

	protected float getPartHeightOffset() {
		if(this.isMiniZombie()) return 0.1F;
		return 0.2f;
	}
	
	public float getPartWidthOffset() {
		if(this.isMiniZombie()) return 0.3F;
		return 0.55f;
	}
	
	public SoundEvent getPartHurtSound() {
		return null;
	}
	
	public SoundEvent getPartDeathSound() {
		return null;
	}

}
