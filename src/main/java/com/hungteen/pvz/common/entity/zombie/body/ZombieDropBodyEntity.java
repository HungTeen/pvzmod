package com.hungteen.pvz.common.entity.zombie.body;

import java.util.Optional;

import com.hungteen.pvz.common.entity.PVZEntityBase;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ZombieDropBodyEntity extends PVZEntityBase {

	private static final DataParameter<Integer> ZOMBIE_TYPE = EntityDataManager.defineId(ZombieDropBodyEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> BODY_TYPE = EntityDataManager.defineId(ZombieDropBodyEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> BODY_STATE = EntityDataManager.defineId(ZombieDropBodyEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> ANIM_TIME = EntityDataManager.defineId(ZombieDropBodyEntity.class,
			DataSerializers.INT);
	public static final int MAX_EXIST_TICK = 60;
	private static final int HAS_HAND_DEFENCE = 0;
	public final int HEAD_ROT;

	public ZombieDropBodyEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
		super(p_i48580_1_, p_i48580_2_);
		HEAD_ROT = this.random.nextInt(60) - 30;
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(ZOMBIE_TYPE, Zombies.NORMAL_ZOMBIE.ordinal());
		this.entityData.define(BODY_TYPE, BodyType.HAND.ordinal());
		this.entityData.define(BODY_STATE, 0);
		this.entityData.define(ANIM_TIME, 0);
	}

	@Override
	public void tick() {
		super.tick();
		this.tickMove();
		if (!this.level.isClientSide) {
			if (this.getAnimTime() >= MAX_EXIST_TICK) {
				this.remove();
			} else {
				this.setAnimTime(this.getAnimTime() + 1);
			}
			
		}
		if(this.onGround) {
			this.setDeltaMovement(this.getDeltaMovement().scale(0.3D));
		}
	}

	/**
	 * common drop styles.
	 */
	public void droppedByOwner(PVZZombieEntity zombie, DamageSource source, BodyType type) {
		this.updateInfo(zombie, type);
		switch(type) {
		case HAND:{
			float j = 2 * 3.14159f * this.yRot / 360;
			final float dis = 0.6F;
			this.setPos(zombie.position().x - Math.sin(j) * dis, zombie.position().y + zombie.getEyeHeight(), zombie.position().z + Math.cos(j) * dis);
			break;
		}
		case HEAD:{
			this.hitUp(zombie, source, 0.3D);
			break;
		}
		case BODY:{
			this.setPos(zombie.position().x, zombie.position().y, zombie.position().z);
			this.setDeltaMovement(zombie.getDeltaMovement());
			break;
		}
		default:
			break;
		}
	}
	
	/**
	 * special drop style, such as zomboni, gargantuar.
	 */
	public void specialDropBody(PVZZombieEntity zombie, DamageSource source, BodyType type) {
		this.updateInfo(zombie, type);
		switch (zombie.getZombieEnumName()) {
		case ZOMBONI:{
			this.hitUp(zombie, source, 0.5D, 0.5D, 0.5D);
			break;
		}
		default:{
			break;
		}
		}
	}
	
	private void hitUp(PVZZombieEntity zombie, DamageSource source, double speed) {
		this.hitUp(zombie, source, speed, speed, speed);
	}
	
	private void hitUp(PVZZombieEntity zombie, DamageSource source, double speed, double speedH, double speedV) {
		this.setPos(zombie.position().x, zombie.position().y + zombie.getEyeHeight(), zombie.position().z);
		double speedX = (this.random.nextDouble() - 0.5D) * speedH;
		double speedZ = (this.random.nextDouble() - 0.5D) * speedH;
		double speedY = this.random.nextDouble() * speedV;
		Optional.ofNullable(source.getSourcePosition()).ifPresent(vec -> {
			Vector3d v = this.position().subtract(vec);
			this.setDeltaMovement(v.normalize().multiply(speed, speed, speed).add(speedX, speedY, speedZ));
		});
	}
	
	private void updateInfo(PVZZombieEntity zombie, BodyType type) {
		this.setZombieType(zombie.getZombieEnumName());
		this.setBodyType(type);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void lerpMotion(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
		this.setDeltaMovement(p_70016_1_, p_70016_3_, p_70016_5_);
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			float f = MathHelper.sqrt(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
			this.xRot = (float) (MathHelper.atan2(p_70016_3_, (double) f) * (double) (180F / (float) Math.PI));
			this.yRot = (float) (MathHelper.atan2(p_70016_1_, p_70016_5_) * (double) (180F / (float) Math.PI));
			this.xRotO = this.xRot;
			this.yRotO = this.yRot;
			this.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
		}
	}
	
	@Override
	public EntitySize getDimensions(Pose p_213305_1_) {
		return EntitySize.scalable(0.5F, 0.5F);
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT nbt) {
		if (nbt.contains("body_anim_tick")) {
			this.setAnimTime(nbt.getInt("body_anim_tick"));
		}
		if (nbt.contains("body_zombie_type")) {
			this.setZombieType(Zombies.values()[nbt.getInt("body_zombie_type")]);
		}
		if (nbt.contains("body_part_state")) {
			this.setBodyState(nbt.getInt("body_part_state"));
		}
		if (nbt.contains("body_part_type")) {
			this.setBodyType(BodyType.values()[nbt.getInt("body_part_type")]);
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT nbt) {
		nbt.putInt("body_anim_tick", this.getAnimTime());
		nbt.putInt("body_zombie_type", this.getZombieType().ordinal());
		nbt.putInt("body_part_state", this.getBodyState());
		nbt.putInt("body_part_type", this.getBodyType().ordinal());
	}
	
	/* getter setter */

	public Zombies getZombieType() {
		return Zombies.values()[entityData.get(ZOMBIE_TYPE)];
	}

	public void setZombieType(Zombies type) {
		entityData.set(ZOMBIE_TYPE, type.ordinal());
	}

	public BodyType getBodyType() {
		return BodyType.values()[entityData.get(BODY_TYPE)];
	}

	public void setBodyType(BodyType type) {
		entityData.set(BODY_TYPE, type.ordinal());
	}
	
	public int getBodyState() {
		return entityData.get(BODY_STATE);
	}

	public void setBodyState(int state) {
		entityData.set(BODY_STATE, state);
	}

	public int getAnimTime() {
		return entityData.get(ANIM_TIME);
	}

	public void setAnimTime(int tick) {
		entityData.set(ANIM_TIME, tick);
	}
	
	public void setHandDefence(boolean flag) {
		this.setBodyState(AlgorithmUtil.BitOperator.setBit(this.getBodyState(), HAS_HAND_DEFENCE, flag));
	}
	
	public boolean hasHandDefence() {
		return AlgorithmUtil.BitOperator.hasBitOne(this.getBodyState(), HAS_HAND_DEFENCE);
	}

	public enum BodyType {
		HAND, HEAD, BODY,
	}

}
