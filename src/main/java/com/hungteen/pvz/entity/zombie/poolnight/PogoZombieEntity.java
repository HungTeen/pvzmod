package com.hungteen.pvz.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.MetalTypes;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class PogoZombieEntity extends PVZZombieEntity implements IHasMetal {

	private static final DataParameter<Boolean> HAS_POGO = EntityDataManager.defineId(PogoZombieEntity.class, DataSerializers.BOOLEAN);
	private final int JUMP_CD = 30;
	
	public PogoZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPogo(true);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_POGO, true);
	}

	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {
			if(this.hasPogo() && this.tickCount % this.JUMP_CD == 0) {
				double motionY = 0.85D;
				this.setDeltaMovement(this.getDeltaMovement().x(), motionY, this.getDeltaMovement().z());
				EntityUtil.playSound(this, SoundRegister.POGO.get());
			}
		}
	}
	
	@Override
	public boolean checkCanZombieBreakBlock() {
		if(super.checkCanZombieBreakBlock()) {
			return ! this.hasMetal();
		}
		return false;
	}
	
	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if(source.getMsgId() == DamageSource.FALL.msgId) return true;
		return super.isInvulnerableTo(source);
	}
	
	@Override
	public boolean hasMetal() {
		return this.hasPogo();
	}

	@Override
	public void decreaseMetal() {
		this.setPogo(false);
	}

	@Override
	public void increaseMetal() {
		this.setPogo(true);
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.POGO;
	}
	
	@Override
	public boolean canBeFrozen() {
		return ! this.hasPogo();
	}
	
	@Override
	public boolean canBeButter() {
		return ! this.hasPogo();
	}
	
	@Override
	public float getLife() {
		return 48;
	}

	public boolean hasPogo() {
		return this.entityData.get(HAS_POGO);
	}
	
	public void setPogo(boolean has) {
		this.entityData.set(HAS_POGO, has);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("has_pogo")) {
			this.setPogo(compound.getBoolean("has_pogo"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("has_pogo", this.hasPogo());
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.POGO_ZOMBIE;
	}

}
