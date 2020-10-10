package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.entity.PVZMultiPartEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZZombiePartEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IMultiPartEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NewspaperZombieEntity extends PVZZombieEntity implements IMultiPartEntity{

	private PVZZombiePartEntity part;
	private boolean isZombieAngry = false;
	
	public NewspaperZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		resetParts();
	}

	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZZombiePartEntity(this, 1f, 1.5f) {
			@Override
			public boolean attackEntityFrom(DamageSource source, float damage) {
				if(source instanceof PVZDamageSource) {
					((PVZDamageSource) source).setDefended(true);
				}
				return super.attackEntityFrom(source, damage);
			}
		};
		this.part.setOwner(this);
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
	
	public float getPartOffset() {
		return 0.5f;
	}
	
	@Override
	public void tick() {
		super.tick();
		updateParts();
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(!this.world.isRemote) {
			if(!this.isZombieAngry && this.shouldAngry()) {
				this.updateAngry(true);
		    }else if(this.isZombieAngry && !this.shouldAngry()) {
				this.updateAngry(false);
			}
		}
	}
	
	protected void updateAngry(boolean is) {
		this.isZombieAngry = is;
		if(is) {
			this.playSound(SoundRegister.ANGRY.get(), 1f, 1f);
		}
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.LITTLE_FAST : ZombieUtil.LITTLE_SLOW);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(is ? ZombieUtil.LOW : ZombieUtil.LITTLE_LOW);
	}
	
	public boolean shouldAngry() {
		return this.getHealth() <= this.getMaxHealth() / 2;
	}
	
	@Override
	public float getLife() {
		return 36;
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("is_zombie_angry", this.isZombieAngry);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.isZombieAngry = compound.getBoolean("is_zombie_angry");
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NEWSPAPER_ZOMBIE;
	}

}
