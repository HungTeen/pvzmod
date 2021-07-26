package com.hungteen.pvz.common.entity.bullet;

import java.util.List;

import com.hungteen.pvz.common.entity.plant.spear.CactusEntity;
import com.hungteen.pvz.common.entity.plant.spear.CatTailEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ThornEntity extends AbstractBulletEntity {

	private static final DataParameter<Integer> THORN_TYPE = EntityDataManager.defineId(ThornEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> THORN_STATE = EntityDataManager.defineId(ThornEntity.class,
			DataSerializers.INT);
	private IntOpenHashSet set = new IntOpenHashSet();
	private LivingEntity thornTarget;
	private int extraHitCount = 0;

	public ThornEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}

	public ThornEntity(World worldIn, LivingEntity shooter) {
		super(EntityRegister.THORN.get(), worldIn, shooter);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(THORN_TYPE, ThornTypes.NORMAL.ordinal());
		entityData.define(THORN_STATE, ThornStates.NORMAL.ordinal());
	}
	
	protected float getPreAttackDamage() {
		if (this.getThrower() instanceof CatTailEntity) return ((CatTailEntity) this.getThrower()).getAttackDamage();
		if(this.getThrower() instanceof CactusEntity) {
			final CactusEntity cactus = (CactusEntity) this.getThrower();
			final float mult = cactus.isCactusPowered() ? 2F : 1F;
			return cactus.getAttackDamage() * mult;
		}
		return 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		super.tick();
		this.noPhysics = true;
		if (! level.isClientSide) {
			Vector3d vec = getShootVec().normalize();
			if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
				float f = MathHelper.sqrt(getHorizontalDistanceSqr(vec));
				this.yRot = (float) (MathHelper.atan2(vec.x, vec.z) * (double) (180F / (float) Math.PI));
				this.xRot = (float) (MathHelper.atan2(vec.y, (double) f) * (double) (180F / (float) Math.PI));
				this.yRotO = this.yRot;
				this.xRotO = this.xRot;
			}
			if (this.getThornType() == ThornTypes.GUILD || this.getThornType() == ThornTypes.AUTO) {
				if (vec != Vector3d.ZERO) {
					double speed = this.getBulletSpeed();
					this.setDeltaMovement(vec.multiply(speed, speed, speed));
				}
			}
			if (this.getThornType() == ThornTypes.AUTO) {
				if ((this.thornTarget == null || ! this.isAlive() || this.thornTarget.removed) && this.tickCount % 20 == 0) {
					this.thornTarget = this.getRandomAttackTarget();
				}
			}
			if (this.thornTarget != null) {
				if (this.distanceToSqr(thornTarget) <= 4) {
					this.onImpact(this.thornTarget);
				}
			}
			if(this.getThrower() == null) this.remove();
		}
	}

	public LivingEntity getRandomAttackTarget() {
		if (this.getThrower() == null) {
			this.remove();
			return null;
		}
		List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class,
				EntityUtil.getEntityAABB(this, 40, 40), (entity) -> {
					return !entity.equals(thornTarget) && EntityUtil.canSeeEntity(this, entity)
							&& EntityUtil.canTargetEntity(this.getThrower(), entity);
				});
		if (list.size() == 0)
			return null;
		return list.get(this.random.nextInt(list.size()));
	}

	public Vector3d getShootVec() {
		LivingEntity target = this.thornTarget;
		if (target == null)
			return Vector3d.ZERO;
		return target.position().add(0, target.getEyeHeight(), 0).subtract(this.position());
	}

	public void setThornTarget(LivingEntity target) {
		this.thornTarget = target;
	}

	public void setExtraHitCount(int cnt) {
		this.extraHitCount = cnt;
	}

	public double getBulletSpeed() {
		if (this.getThrower() instanceof CatTailEntity) {
			if(this.getThornType() == ThornTypes.AUTO) return 1D;
			return 0.5D;
		}
		return 0.1D;
	}

	public boolean isInControl() {
		return this.getThornType() == ThornTypes.GUILD;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			this.onImpact(target);
		}
	}

	private void onImpact(Entity target) {
		if (this.shouldHit(target)) {
			target.invulnerableTime = 0;
			this.dealThornDamage(target); // attack
			if (this.getThornType() == ThornTypes.GUILD) {
				this.setThornType(ThornTypes.NORMAL);
				set.add(target.getId());
			} else if (this.getThornType() == ThornTypes.AUTO) {
				this.thornTarget = this.getRandomAttackTarget();
			} else {
				set.add(target.getId());
				-- this.extraHitCount;
			}
		}
		this.level.broadcastEntityEvent(this, (byte) 3);
		if ((! (this.getThornType() == ThornTypes.AUTO) && this.extraHitCount == 0)) {
			this.remove();
		}
	}

//	@Override
//	protected boolean checkCanAttack(Entity target) {
//		if (!super.checkCanAttack(target))
//			return false;
//		if (this.getThornType() == ThornTypes.AUTO) {
//			return target.equals(this.thornTarget);
//		} else {
//			return ! set.contains(target.getId());
//		}
//	}

	protected void dealThornDamage(Entity target) {
		target.hurt(PVZDamageSource.causeThornDamage(this, this), this.getAttackDamage());
	}
	
	@Override
	public float getAttackDamage() {
		float damage = this.attackDamage;
		if (this.getThornType() == ThornTypes.AUTO) {
			damage += 10;
		}
		return damage;
	}

	@Override
	protected boolean checkLive(RayTraceResult result) {
		return true;
	}

	@Override
	protected int getMaxLiveTick() {
		return this.getThornType() == ThornTypes.AUTO ? 600 : 200;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.2f, 0.2f);
	}

	@Override
	protected float getGravityVelocity() {
		return 0f;
	}
	
	/**
	 * Updates the entity motion clientside, called by packets from the server
	 */
	@OnlyIn(Dist.CLIENT)
	public void lerpMotion(double x, double y, double z) {
		this.setDeltaMovement(x, y, z);
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			float f = MathHelper.sqrt(x * x + z * z);
			this.xRot = (float) (MathHelper.atan2(y, (double) f) * (double) (180F / (float) Math.PI));
			this.yRot = (float) (MathHelper.atan2(x, z) * (double) (180F / (float) Math.PI));
			this.xRotO = this.xRot;
			this.yRotO = this.yRot;
			this.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot,
					this.xRot);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("thorn_state", this.getThornState().ordinal());
		compound.putInt("thorn_type", this.getThornType().ordinal());
		compound.putInt("extra_hit_count", this.extraHitCount);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (this.getThrower() != null && this.getThrower() instanceof CatTailEntity && this.isInControl()) {
			((CatTailEntity) this.getThrower()).thorns.add(this);
		}
		if(compound.contains("thorn_state")) {
			this.setThornState(ThornStates.values()[compound.getInt("thorn_state")]);
		}
		if(compound.contains("thorn_type")) {
			this.setThornType(ThornTypes.values()[compound.getInt("thorn_type")]);
		}
		if(compound.contains("extra_hit_count")) {
			this.extraHitCount = compound.getInt("extra_hit_count");
		}
	}

	public ThornStates getThornState() {
		return ThornStates.values()[entityData.get(THORN_STATE)];
	}

	public void setThornState(ThornStates state) {
		entityData.set(THORN_STATE, state.ordinal());
	}

	public ThornTypes getThornType() {
		return ThornTypes.values()[entityData.get(THORN_TYPE)];
	}

	public void setThornType(ThornTypes type) {
		entityData.set(THORN_TYPE, type.ordinal());
	}

	public enum ThornStates {
		NORMAL, POWER,
	}

	public enum ThornTypes {
		NORMAL, GUILD, AUTO
	}

}
