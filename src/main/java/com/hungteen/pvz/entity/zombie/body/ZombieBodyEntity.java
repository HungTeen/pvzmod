package com.hungteen.pvz.entity.zombie.body;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ZombieBodyEntity extends Entity {

	@SuppressWarnings("unused")
	private PVZZombieEntity bodyOwner;
	
	public ZombieBodyEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
		super(p_i48580_1_, p_i48580_2_);
	}

	@Override
	protected void defineSynchedData() {
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
	}

	public void setOwner(PVZZombieEntity zombie) {
		this.bodyOwner = zombie;
	}
	
	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
