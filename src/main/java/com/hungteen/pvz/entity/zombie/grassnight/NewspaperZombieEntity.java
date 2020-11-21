package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class NewspaperZombieEntity extends DefenceZombieEntity {

	private static final DataParameter<Boolean> IS_ANGRY = EntityDataManager.createKey(NewspaperZombieEntity.class, DataSerializers.BOOLEAN);
	
	public NewspaperZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(IS_ANGRY, false);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.updateAngry(false);
	}
	
	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZHealthPartEntity(this, 1f, 1f);
		this.part.setOwner(this);
	}
	
	@Override
	protected float getPartHeightOffset() {
		return 0.7f;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(!world.isRemote) {
			if(this.canPartsBeRemoved() && !this.isAngry()) {
				EntityUtil.playSound(this, SoundRegister.ANGRY.get());
				this.updateAngry(true);
			}
		}
	}
	
	protected void updateAngry(boolean is) {
		this.setAngry(is);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.LITTLE_FAST : ZombieUtil.LITTLE_SLOW);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(is ? ZombieUtil.LOW : ZombieUtil.LITTLE_LOW);
	}
	
	@Override
	public SoundEvent getPartDeathSound() {
		return SoundRegister.PAPER_GONE.get();
	}
	
	@Override
	public float getLife() {
		return 22;
	}
	
	@Override
	public float getPartLife() {
		return 10;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setAngry(compound.getBoolean("is_zombie_angry"));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("is_zombie_angry", this.isAngry());
	}
	
	public void setAngry(boolean is) {
		this.dataManager.set(IS_ANGRY, is);
	}
	
	public boolean isAngry() {
		return this.dataManager.get(IS_ANGRY);
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NEWSPAPER_ZOMBIE;
	}

}
