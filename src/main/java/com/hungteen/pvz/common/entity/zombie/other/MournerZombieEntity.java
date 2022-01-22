package com.hungteen.pvz.common.entity.zombie.other;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.TombStoneEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.CustomZombies;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MournerZombieEntity extends PVZZombieEntity{

	private static final DataParameter<Boolean> RIGHT_SHAKE = EntityDataManager.defineId(MournerZombieEntity.class, DataSerializers.BOOLEAN);
	public static final int SHAKE_CD = 10;
	
	public MournerZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setRightShake(this.getRandom().nextInt(2) == 0 ? true : false);
		this.canLostHand = false;
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(RIGHT_SHAKE, true);
	}

	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_SLOW);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.VERY_LOW);
	}
	
	@Override
	public float getLife() {
		return 48;
	}

	@Override
	protected int getDeathTime() {
		return 1;
	}

	@Override
	public boolean doHurtTarget(Entity entityIn) {
		this.setAttackTime(SHAKE_CD);
		final float scale = 3;
		if(! entityIn.isOnGround() && entityIn instanceof LivingEntity) {
			entityIn.hurt(PVZDamageSource.normal(this), EntityUtil.getMaxHealthDamage((LivingEntity) entityIn, 0.2F));
		}
		entityIn.setDeltaMovement(0, Math.sqrt(this.getRandom().nextFloat()) * scale, 0);
		return super.doHurtTarget(entityIn);
	}
	
	@Override
	protected PVZDamageSource getZombieAttackDamageSource() {
		return PVZDamageSource.normal(this);
	}

	@Override
	public void tick() {
		super.tick();
		if(this.getAttackTime() > 0) {
			this.setAttackTime(this.getAttackTime() - 1);
		}
	}
	
	@Override
	protected void onRemoveWhenDeath() {
		if(!level.isClientSide) {
			TombStoneEntity tomb = EntityRegister.TOMB_STONE.get().create(level);
			ZombieUtil.onZombieSpawn(this, tomb, blockPosition());
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
	public ZombieType getZombieType() {
		return CustomZombies.MOURNER_ZOMBIE;
	}

}
