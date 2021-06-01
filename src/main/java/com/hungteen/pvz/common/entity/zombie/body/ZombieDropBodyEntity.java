package com.hungteen.pvz.common.entity.zombie.body;

import java.util.Optional;

import com.hungteen.pvz.common.entity.PVZEntityBase;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
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
	private static final DataParameter<Integer> ANIM_TIME = EntityDataManager.defineId(ZombieDropBodyEntity.class,
			DataSerializers.INT);
	public static final int MAX_EXIST_TICK = 60;
	public final int HEAD_ROT;

	public ZombieDropBodyEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
		super(p_i48580_1_, p_i48580_2_);
		HEAD_ROT = this.random.nextInt(60) - 30;
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(ZOMBIE_TYPE, Zombies.NORMAL_ZOMBIE.ordinal());
		this.entityData.define(BODY_TYPE, BodyType.HAND.ordinal());
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
//				this.remove();
			this.setDeltaMovement(this.getDeltaMovement().scale(0.3D));
		}
	}

	public void droppedByOwner(PVZZombieEntity zombie, DamageSource source, BodyType type) {
		this.setZombieType(zombie.getZombieEnumName());
		this.setBodyType(type);
		switch(type) {
		case HAND:{
			float j = 2 * 3.14159f * this.yRot / 360;
			final float dis = 0.6F;
			this.setPos(zombie.position().x - Math.sin(j) * dis, zombie.position().y + zombie.getEyeHeight(), zombie.position().z + Math.cos(j) * dis);
			break;
		}
		case HEAD:{
			this.setPos(zombie.position().x, zombie.position().y + zombie.getEyeHeight(), zombie.position().z);
			final double speed = 0.3D;
			double speedX = (this.random.nextDouble() - 0.5D) * speed;
			double speedZ = (this.random.nextDouble() - 0.5D) * speed;
			double speedY = this.random.nextDouble() * speed * 2;
			Optional.ofNullable(source.getSourcePosition()).ifPresent(vec -> {
				Vector3d v = this.position().subtract(vec);
				v = v.normalize().add(speedX, speedY, speedZ);
				this.setDeltaMovement(v.normalize().multiply(speed, speed, speed));
			});
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
	protected void readAdditionalSaveData(CompoundNBT nbt) {
		if (nbt.contains("body_anim_tick")) {
			this.setAnimTime(nbt.getInt("body_anim_tick"));
		}
		if (nbt.contains("body_zombie_type")) {
			this.setZombieType(Zombies.values()[nbt.getInt("body_zombie_type")]);
		}
		if (nbt.contains("body_part_type")) {
			this.setBodyType(BodyType.values()[nbt.getInt("body_part_type")]);
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT nbt) {
		nbt.putInt("body_anim_tick", this.getAnimTime());
		nbt.putInt("body_zombie_type", this.getZombieType().ordinal());
		nbt.putInt("body_part_type", this.getBodyType().ordinal());
	}

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

	public int getAnimTime() {
		return entityData.get(ANIM_TIME);
	}

	public void setAnimTime(int tick) {
		entityData.set(ANIM_TIME, tick);
	}

	public enum BodyType {
		HAND, HEAD, BODY,
	}

}
