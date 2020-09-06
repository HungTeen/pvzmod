package com.hungteen.pvz.entity.zombie;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class PVZZombiePartEntity extends Entity{

	public final PVZZombieEntity zombie;
	public final String name;
	private final EntitySize size;
	
	public PVZZombiePartEntity(PVZZombieEntity zombie, World worldIn, String name, float width, float height) {
		super(zombie.getType(), worldIn);
		this.zombie = zombie;
		this.name = name;
		this.size = EntitySize.flexible(width, height);
		this.recalculateSize();
	}

	@Override
	protected void registerData() {
		
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return this.zombie.attackEntityFrom(source, amount);
	}
	
	@Override
	public boolean isEntityEqual(Entity entityIn) {
		return this==entityIn||this.zombie==entityIn;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return this.size;
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		throw new UnsupportedOperationException();
	}

}
