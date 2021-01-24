package com.hungteen.pvz.entity.plant.flame;

import com.hungteen.pvz.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.interfaces.ILightPlant;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class TorchWoodEntity extends PVZPlantEntity implements ILightPlant {

	private static final DataParameter<Boolean> SUPER_FLAME = EntityDataManager.createKey(TorchWoodEntity.class, DataSerializers.BOOLEAN);
	
	public TorchWoodEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCharmed = false;
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
		if(! world.isRemote) {
			if(this.ticksExisted % 40 == 0) {
				int range = 15;
				this.giveLightToPlayers(range);
			}
		}
		if(this.isPlantInSuperMode()) {
			fireballRain();
		}
	}
	
	private void giveLightToPlayers(float range) {
		world.getEntitiesWithinAABB(PlayerEntity.class, EntityUtil.getEntityAABB(this, range, range), (player) -> {
			return ! EntityUtil.checkCanEntityAttack(this, player);
		}).forEach((player) -> {
			player.addPotionEffect(getLightEyeEffect());
		});
	}
	
	private void fireballRain() {
		double dx = (this.getRNG().nextFloat() - 0.5f) * 6;
		double dz = (this.getRNG().nextFloat() - 0.5f) * 6;
		double dy = 10;
		PeaEntity pea = new PeaEntity(EntityRegister.PEA.get(), world, this, PeaEntity.Type.NORMAL, PeaEntity.State.FIRE);
		pea.setPosition(this.getPosX() + dx, this.getPosY() + dy, this.getPosZ() + dz);
		pea.setMotion(0, - 0.4, 0);
		world.addEntity(pea);
	}
	
	@Override
	public EffectInstance getLightEyeEffect() {
		return new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), 100, 0, false, true);
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
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
		if(this.isPlantInStage(1)) return 30;
		if(this.isPlantInStage(2)) return 50;
		return 80;
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
		if(compound.contains("is_super_fire")) {
			this.setSuperFlame(compound.getBoolean("is_super_fire"));
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("is_super_fire", this.IsSuperFlame());
	}

}
