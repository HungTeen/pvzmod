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

	private static final DataParameter<Boolean> HAS_POGO = EntityDataManager.createKey(PogoZombieEntity.class, DataSerializers.BOOLEAN);
	private final int JUMP_CD = 30;
	
	public PogoZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPogo(true);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(HAS_POGO, true);
	}

	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! world.isRemote) {
			if(this.hasPogo() && this.ticksExisted % this.JUMP_CD == 0) {
				double motionY = 0.85D;
				this.setMotion(this.getMotion().getX(), motionY, this.getMotion().getZ());
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
		if(source.getDamageType() == DamageSource.FALL.damageType) return true;
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
		return this.dataManager.get(HAS_POGO);
	}
	
	public void setPogo(boolean has) {
		this.dataManager.set(HAS_POGO, has);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("has_pogo")) {
			this.setPogo(compound.getBoolean("has_pogo"));
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("has_pogo", this.hasPogo());
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.POGO_ZOMBIE;
	}

}
