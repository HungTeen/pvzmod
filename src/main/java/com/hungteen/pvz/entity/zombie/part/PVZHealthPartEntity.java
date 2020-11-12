package com.hungteen.pvz.entity.zombie.part;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class PVZHealthPartEntity extends PVZZombiePartEntity{

	private static final DataParameter<Float> HEALTH = EntityDataManager.createKey(PVZHealthPartEntity.class, DataSerializers.FLOAT);
	
	public PVZHealthPartEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}
	
	public PVZHealthPartEntity(PVZZombieEntity owner, float sizeX, float sizeY, float life) {
		super(owner, sizeX, sizeY);
		this.setHealth(life);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if(!world.isRemote) {
			if(this.getHealth() >= damage) {
				this.setHealth(this.getHealth() - damage);
				return true;
			} else if(this.getHealth() > 0){
				damage -= this.getHealth();
				this.setHealth(0);
			}
		}
		return super.attackEntityFrom(source, damage);
	}
	
	@Override
	public boolean shouldNotExist() {
		return super.shouldNotExist() || this.getHealth() == 0;
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(HEALTH, 0f);
	}
	
	@Override
	protected void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setHealth(compound.getFloat("part_health"));
	}
	
	@Override
	protected void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.getFloat("part_health");
	}
	
	public void setHealth(float life) {
		this.dataManager.set(HEALTH, life);
	}
	
	public float getHealth() {
		return this.dataManager.get(HEALTH);
	}

}
