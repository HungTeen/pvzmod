package com.hungteen.pvz.common.entity.zombie.pool;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import com.hungteen.pvz.utils.interfaces.IHasMetal;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class PogoZombieEntity extends PVZZombieEntity implements IHasMetal {

	private static final DataParameter<Boolean> HAS_POGO = EntityDataManager.defineId(PogoZombieEntity.class, DataSerializers.BOOLEAN);
	private static final int JUMP_CD = 10;
	
	public PogoZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
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
			this.setAttackTime((this.getAttackTime() + 1) % JUMP_CD); 
			if(this.hasPogo() && (this.isOnGround() || this.isInWaterOrBubble()) && this.getAttackTime() % JUMP_CD == 0) {
				double motionY = 0.85D + MathUtil.getRandomFloat(getRandom()) * 0.1D;
				this.setDeltaMovement(this.getDeltaMovement().x(), motionY, this.getDeltaMovement().z());
				EntityUtil.playSound(this, SoundRegister.POGO.get());
			}
		}
	}
	
	@Override
	public boolean canBreakPlantBlock() {
		return super.canBreakPlantBlock() && ! this.hasMetal();
	}
	
	@Override
	public boolean canBeAttractedBy(ICanAttract defender) {
		if(defender instanceof PVZPlantEntity) {
			final IPlantType plant = ((PVZPlantEntity) defender).getPlantType();
			return plant == PVZPlants.TALL_NUT;
		}
		return true;
	}
	
	@Override
	public void attractBy(ICanAttract defender) {
		super.attractBy(defender);
		if(this.hasPogo()) {
		    this.setPogo(false);
		    EntityUtil.playSound(this, SoundRegister.HAMMER_BONK.get());
		}
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
	public boolean canBeButtered() {
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
    public ZombieType getZombieType() {
	    return PoolZombies.POGO_ZOMBIE;
    }

}
