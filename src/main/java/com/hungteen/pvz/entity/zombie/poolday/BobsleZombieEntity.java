package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.misc.BobsleCarEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BobsleZombieEntity extends PVZZombieEntity{

	private static final DataParameter<Boolean> IS_LEADER = EntityDataManager.createKey(BobsleZombieEntity.class, DataSerializers.BOOLEAN);
	
	public BobsleZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(IS_LEADER, true);
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(this.isLeader()) {
			BobsleCarEntity car = EntityRegister.BOBSLE_CAR.get().create(world);
			car.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
			world.addEntity(car);
			this.startRiding(car);
			for(int i=0;i<3;i++) {
				BobsleZombieEntity zombie = EntityRegister.BOBSLE_ZOMBIE.get().create(world);
				zombie.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
				zombie.setLeader(false);
				zombie.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
				world.addEntity(zombie);
				zombie.startRiding(car);
			}
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(!world.isRemote) {
			if(EntityUtil.isOnSnow(this)) {
				this.addPotionEffect(new EffectInstance(Effects.SPEED, 10, 0, false, false));
			}
		}
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setLeader(compound.getBoolean("is_leader"));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("is_leader", this.isLeader());
	}
	
	public boolean isLeader() {
		return this.dataManager.get(IS_LEADER);
	}
	
	public void setLeader(boolean is) {
		this.dataManager.set(IS_LEADER, is);
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BOBSLE_ZOMBIE;
	}

}
