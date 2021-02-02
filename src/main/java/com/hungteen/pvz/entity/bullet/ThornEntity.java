package com.hungteen.pvz.entity.bullet;

import java.util.List;

import com.hungteen.pvz.entity.plant.spear.CactusEntity;
import com.hungteen.pvz.entity.plant.spear.CatTailEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ThornEntity extends AbstractBulletEntity {

	private static final DataParameter<Integer> THORN_TYPE = EntityDataManager.createKey(ThornEntity.class,
			DataSerializers.VARINT);
	private static final DataParameter<Integer> THORN_STATE = EntityDataManager.createKey(ThornEntity.class,
			DataSerializers.VARINT);
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
	protected void registerData() {
		super.registerData();
		dataManager.register(THORN_TYPE, ThornTypes.NORMAL.ordinal());
		dataManager.register(THORN_STATE, ThornStates.NORMAL.ordinal());
	}

	/**
	 * Updates the entity motion clientside, called by packets from the server
	 */
	@OnlyIn(Dist.CLIENT)
	public void setVelocity(double x, double y, double z) {
		this.setMotion(x, y, z);
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt(x * x + z * z);
			this.rotationPitch = (float) (MathHelper.atan2(y, (double) f) * (double) (180F / (float) Math.PI));
			this.rotationYaw = (float) (MathHelper.atan2(x, z) * (double) (180F / (float) Math.PI));
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw,
					this.rotationPitch);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		super.tick();
		this.noClip = true;
		if (! world.isRemote) {
			Vec3d vec = getShootVec().normalize();
			if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
				float f = MathHelper.sqrt(horizontalMag(vec));
				this.rotationYaw = (float) (MathHelper.atan2(vec.x, vec.z) * (double) (180F / (float) Math.PI));
				this.rotationPitch = (float) (MathHelper.atan2(vec.y, (double) f) * (double) (180F / (float) Math.PI));
				this.prevRotationYaw = this.rotationYaw;
				this.prevRotationPitch = this.rotationPitch;
			}
			if (this.getThornType() == ThornTypes.GUILD || this.getThornType() == ThornTypes.AUTO) {
				if (vec != Vec3d.ZERO) {
					double speed = this.getBulletSpeed();
					this.setMotion(vec.mul(speed, speed, speed));
				}
			}
			if (this.getThornType() == ThornTypes.AUTO) {
				if ((this.thornTarget == null || ! this.isAlive() || this.thornTarget.removed) && this.ticksExisted % 20 == 0) {
					this.thornTarget = this.getRandomAttackTarget();
				}
			}
			if (this.thornTarget != null) {
				if (this.getDistanceSq(thornTarget) <= 4) {
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
		List<LivingEntity> list = world.getEntitiesWithinAABB(LivingEntity.class,
				EntityUtil.getEntityAABB(this, 40, 40), (entity) -> {
					return !entity.equals(thornTarget) && EntityUtil.checkCanSeeEntity(this, entity)
							&& EntityUtil.checkCanEntityTarget(this.getThrower(), entity);
				});
		if (list.size() == 0)
			return null;
		return list.get(this.rand.nextInt(list.size()));
	}

	public Vec3d getShootVec() {
		LivingEntity target = this.thornTarget;
		if (target == null)
			return Vec3d.ZERO;
		return target.getPositionVec().add(0, target.getEyeHeight(), 0).subtract(this.getPositionVec());
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
			return 0.4D;
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
		if (checkCanAttack(target)) {
			target.hurtResistantTime = 0;
			this.dealThornDamage(target); // attack
			if (this.getThornType() == ThornTypes.GUILD) {
				this.setThornType(ThornTypes.NORMAL);
				set.add(target.getEntityId());
			} else if (this.getThornType() == ThornTypes.AUTO) {
				this.thornTarget = this.getRandomAttackTarget();
			} else {
				set.add(target.getEntityId());
				-- this.extraHitCount;
			}
		}
		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			if ((! (this.getThornType() == ThornTypes.AUTO) && this.extraHitCount == 0)) {
				this.remove();
			}
		}
	}

	@Override
	protected boolean checkCanAttack(Entity target) {
		if (!super.checkCanAttack(target))
			return false;
		if (this.getThornType() == ThornTypes.AUTO) {
			return target.equals(this.thornTarget);
		} else {
			return ! set.contains(target.getEntityId());
		}
	}

	protected void dealThornDamage(Entity target) {
		target.attackEntityFrom(PVZDamageSource.causeThornDamage(this, this), this.getFixDamage());
	}
	
	private float getFixDamage() {
		float damage = this.attackDamage;
		if (this.getThornType() == ThornTypes.AUTO) {
			damage += 10;
		}
		return damage;
	}

	protected float getAttackDamage() {
		if (this.getThrower() instanceof CatTailEntity) return ((CatTailEntity) this.getThrower()).getAttackDamage();
		if(this.getThrower() instanceof CactusEntity) return ((CactusEntity) this.getThrower()).getAttackDamage();
		return 0;
		
	}

	@Override
	protected boolean checkLive(RayTraceResult result) {
		return true;
	}

	@Override
	protected int getMaxLiveTick() {
		return this.getThornType() == ThornTypes.AUTO ? 600 : super.getMaxLiveTick() + 100;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.2f, 0.2f);
	}

	@Override
	protected float getGravityVelocity() {
		return 0f;
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("thorn_state", this.getThornState().ordinal());
		compound.putInt("thorn_type", this.getThornType().ordinal());
		compound.putInt("extra_hit_count", this.extraHitCount);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
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
		return ThornStates.values()[dataManager.get(THORN_STATE)];
	}

	public void setThornState(ThornStates state) {
		dataManager.set(THORN_STATE, state.ordinal());
	}

	public ThornTypes getThornType() {
		return ThornTypes.values()[dataManager.get(THORN_TYPE)];
	}

	public void setThornType(ThornTypes type) {
		dataManager.set(THORN_TYPE, type.ordinal());
	}

	public enum ThornStates {
		NORMAL, POWER,
	}

	public enum ThornTypes {
		NORMAL, GUILD, AUTO
	}

}
