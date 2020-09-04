package com.hungteen.pvz.entity.plant.flame;

import com.hungteen.pvz.entity.bullet.PeaEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class TorchWoodEntity extends PVZPlantEntity{

	private static final DataParameter<Boolean> SUPER_FLAME = EntityDataManager.createKey(TorchWoodEntity.class, DataSerializers.BOOLEAN);
	
	public TorchWoodEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(SUPER_FLAME, false);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(world.isRemote) {
			if(this.IsSuperFlame()) {
				this.world.addParticle(ParticleRegister.BLUE_FLAME.get(), this.getPosX(), this.getPosY()+1.5, this.getPosZ(), 0, 0, 0);
			}else {
				this.world.addParticle(ParticleRegister.YELLOW_FLAME.get(), this.getPosX(), this.getPosY()+1.5, this.getPosZ(), 0, 0, 0);
			}
		}
		if(this.isPlantInSuperMode()) {
			fireballRain();
		}
	}
	
	private void fireballRain() {
		double dx = (this.getRNG().nextFloat()-0.5f)*6;
		double dz = (this.getRNG().nextFloat()-0.5f)*6;
		double dy = 10;
		PeaEntity pea = new PeaEntity(EntityRegister.PEA.get(), world, this, PeaEntity.Type.NORMAL, PeaEntity.State.FIRE);
		pea.setPosition(this.getPosX()+dx, this.getPosY()+dy, this.getPosZ()+dz);
		pea.setMotion(0, -0.4, 0);
		world.addEntity(pea);
	}
	
	@Override
	public void startSuperMode() {
		super.startSuperMode();
		this.setSuperFlame(true);
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.95f, 1.95f, false);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.TORCH_WOOD;
	}

	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 30;
		if(lvl<=13) return 50;
		if(lvl<=20) return 80;
		return 30;
	}
	
	public boolean IsSuperFlame() {
		return dataManager.get(SUPER_FLAME);
	}
	
	public void setSuperFlame(boolean is) {
		dataManager.set(SUPER_FLAME, is);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setSuperFlame(compound.getBoolean("is_super_fire"));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("is_super_fire", this.IsSuperFlame());
	}

}
