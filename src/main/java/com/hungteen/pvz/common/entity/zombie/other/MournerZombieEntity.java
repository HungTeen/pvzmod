package com.hungteen.pvz.common.entity.zombie.other;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MournerZombieEntity extends PVZZombieEntity{

	private static final DataParameter<Boolean> RIGHT_SHAKE = EntityDataManager.defineId(MournerZombieEntity.class, DataSerializers.BOOLEAN);
	public static final int SHAKE_CD = 10;
	
	public MournerZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setRightShake(this.getRandom().nextInt(2) == 0 ? true : false);
		this.maxDeathTime = 0;
		this.canLostHand = false;
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(RIGHT_SHAKE, true);
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_SLOW);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.VERY_LOW);
	}
	
	@Override
	public float getLife() {
		return 48;
	}
	
	@Override
	public boolean doHurtTarget(Entity entityIn) {
		this.setAttackTime(SHAKE_CD);
		float scale = 3;
		entityIn.setDeltaMovement(0, Math.sqrt(this.getRandom().nextFloat()) * scale, 0);
		return super.doHurtTarget(entityIn);
	}
	
	@Override
	protected PVZDamageSource getZombieAttackDamageSource() {
		return PVZDamageSource.causeNormalDamage(this, this);
	}

	@Override
	public void tick() {
		super.tick();
		if(this.getAttackTime() > 0) {
			this.setAttackTime(this.getAttackTime() - 1);
		}
	}
	
	@Override
	protected void onZombieRemove() {
		if(!level.isClientSide) {
			TombStoneEntity tomb = EntityRegister.TOMB_STONE.get().create(level);
			EntityUtil.onEntitySpawn(level, tomb, blockPosition());
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("is_right_shake")) {
			this.setRightShake(compound.getBoolean("is_right_shake"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("is_right_shake", this.isRightShake());
	}
	
	public boolean isRightShake() {
		return this.entityData.get(RIGHT_SHAKE);
	}
	
	public void setRightShake(boolean is) {
		this.entityData.set(RIGHT_SHAKE, is);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.MOURNER_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.MOURNER_ZOMBIE;
	}

}
