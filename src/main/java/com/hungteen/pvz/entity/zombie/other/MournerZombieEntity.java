package com.hungteen.pvz.entity.zombie.other;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class MournerZombieEntity extends PVZZombieEntity{

	private static final DataParameter<Boolean> RIGHT_SHAKE = EntityDataManager.createKey(MournerZombieEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(MournerZombieEntity.class, DataSerializers.VARINT);
	public static final int SHAKE_CD = 10;
	
	public MournerZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(RIGHT_SHAKE, true);
		this.dataManager.register(ATTACK_TIME, 0);
	}

	@Override
	public float getLife() {
		return 48;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		this.setAttackTime(SHAKE_CD);
		float scale = 3;
		entityIn.setMotion(0, Math.sqrt(this.getRNG().nextFloat()) * scale, 0);
		return super.attackEntityAsMob(entityIn);
	}

	@Override
	public void tick() {
		super.tick();
		if(this.getAttackTime() > 0) {
			this.setAttackTime(this.getAttackTime() - 1);
		}
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setAttackTime(compound.getInt("zombie_attack_time"));
		this.setRightShake(compound.getBoolean("is_right_shake"));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("zombie_attack_time", this.getAttackTime());
		compound.putBoolean("is_right_shake", this.isRightShake());
	}
	
	public boolean isRightShake() {
		return this.dataManager.get(RIGHT_SHAKE);
	}
	
	public void setRightShake(boolean is) {
		this.dataManager.set(RIGHT_SHAKE, is);
	}
	
	public int getAttackTime(){
    	return dataManager.get(ATTACK_TIME);
    }
    
    public void setAttackTime(int cd){
    	dataManager.set(ATTACK_TIME, cd);
    }
    
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.MOURNER_ZOMBIE;
	}

}
