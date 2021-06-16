package com.hungteen.pvz.common.entity.plant.explosion;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZRandomTargetGoal;
import com.hungteen.pvz.common.entity.bullet.CornEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CobCannonEntity extends PVZPlantEntity {

	protected static final DataParameter<Integer> CORN_NUM = EntityDataManager.defineId(CobCannonEntity.class,
			DataSerializers.INT);
	protected Optional<LivingEntity> lockTarget = Optional.empty();
	protected Optional<BlockPos> lockPos = Optional.empty();
	protected int cornCnt = 0;
	protected final int MaxCornCnt = 16;
	protected int preTick = 0;

	public CobCannonEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithPlant = false;
		this.isImmuneToWeak = true;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CORN_NUM, 0);
	}

	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) 0.2F);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(2, new PVZRandomTargetGoal(this, true, 10, 48, 16));
	}
	
	@Override
	public void onSpawnedByPlayer(PlayerEntity player, int lvl) {
		super.onSpawnedByPlayer(player, lvl);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.getMoveSpeed());
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if (!level.isClientSide) {
			++ this.preTick;
			if (this.getAttackTime() == 0 && this.preTick >= this.getPreCD()) {
				this.preTick = 0;
				this.setCornNum(Math.min(2, this.getCornNum() + 1));
			}
			if(this.getCornNum() >= 2 && !this.isPlayerRiding() && this.getTarget() != null) {
				this.setAttackTime(this.getAnimCD());
				this.setCornNum(this.getCornNum() - 1);
			}
			if (this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
				this.getPassengers().forEach((entity) -> {
					entity.stopRiding();
				});
				if (this.getAttackTime() == this.getAnimCD() / 2) {
					this.startAttack();
				}
			}
		}
	}
	
	public void checkAndAttack() {
		if(this.getAttackTime() > 0) return ;
		if(this.isPlayerRiding()) {
			PlayerEntity player = (PlayerEntity) this.getPassengers().get(0);
			Vector3d look = player.getLookAngle();
		    Vector3d start = player.position().add(0, player.getEyeHeight(), 0);
		    double range = 60;
		    Vector3d end = start.add(look.normalize().multiply(range, range, range));
		    RayTraceContext ray = new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, player);
		    RayTraceResult result = level.clip(ray);
		    if(result.getType() != RayTraceResult.Type.MISS) {// hit something
			    end = result.getLocation();
		    }
			EntityRayTraceResult entityRay = this.rayTraceEntities(level, player, range, start, end);
		    if(entityRay != null && entityRay.getType() == Type.ENTITY) {
			    if(entityRay.getEntity() instanceof LivingEntity) {
			    	this.setAttackTime(this.getAnimCD());
			    	this.setCornNum(this.getCornNum() - 1);
			    	this.lockTarget = Optional.ofNullable((LivingEntity) entityRay.getEntity());
			    }
		    } else if(result.getType() == RayTraceResult.Type.BLOCK) {
		    	this.setAttackTime(this.getAnimCD());
		    	BlockPos pos = new BlockPos(end.x(), end.y(), end.z());
		    	this.setCornNum(this.getCornNum() - 1);
		    	this.lockPos = Optional.ofNullable(pos);
		    }
		}
	}
	
	private void startAttack() {
		if(this.lockTarget.isPresent()) {
			this.shootCorn(this.lockTarget.get());
			this.lockTarget = Optional.empty();
			return ;
		}
		if(this.lockPos.isPresent()) {
			this.shootCorn(this.lockPos.get());
			this.lockPos = Optional.empty();
			return ;
		}
		float range = 45F;
		List<LivingEntity> list = EntityUtil.getViewableTargetableEntity(this, EntityUtil.getEntityAABB(this, range, range));
		int num = (this.isPlantInSuperMode() ? this.getSuperCornNum() : 1);
		for(int i = 0; i < num; ++ i) {
			LivingEntity res = this;
		    if(! list.isEmpty()) {
			    int pos = this.getRandom().nextInt(list.size());
			    res = list.get(pos);
		    }
		    this.shootCorn(res);
		}
	}

	private void shootCorn(LivingEntity target) {
		CornEntity corn = new CornEntity(level, this);
		corn.setPos(this.getX(), this.getY() + 1.5D, this.getZ());
		corn.shootPultBullet(target);
		corn.cornCnt = this.cornCnt;
		this.cornCnt = 0;
		EntityUtil.playSound(this, SoundRegister.COB_LAUNCH.get());
		level.addFreshEntity(corn);
	}
	
	private void shootCorn(BlockPos pos) {
		CornEntity corn = new CornEntity(level, this);
		corn.setPos(this.getX(), this.getY() + 1.5D, this.getZ());
		corn.shootPultBullet(pos);
		corn.cornCnt = this.cornCnt;
		this.cornCnt = 0;
		EntityUtil.playSound(this, SoundRegister.COB_LAUNCH.get());
		level.addFreshEntity(corn);
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.setAttackTime(this.getAnimCD());
	}

	@Override
	protected boolean shouldLockXZ() {
		return !this.isPlayerRiding();
	}
	
	/**
	 * Gets the EntityRayTraceResult representing the entity hit
	 */
	@Nullable
	protected EntityRayTraceResult rayTraceEntities(World world, PlayerEntity player, double range, Vector3d startVec, Vector3d endVec) {
		return ProjectileHelper.getEntityHitResult(world, player, startVec, endVec, 
				player.getBoundingBox().inflate(range), (entity) -> {
			return EntityUtil.isEntityValid(entity) && entity instanceof LivingEntity && ! entity.is(this);
		});
	}

	public boolean isPlayerRiding() {
		for (Entity entity : this.getPassengers()) {
			if (entity instanceof PlayerEntity)
				return true;
		}
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	private boolean isRidingPlayer(PlayerEntity player) {
		return player.getVehicle() != null && player.getVehicle() == this;
	}

	public void travel(Vector3d p_213352_1_) {
		if (this.isAlive()) {
			if (this.isVehicle() && this.isPlayerRiding()) {
				PlayerEntity player = (PlayerEntity) this.getPassengers().get(0);
				if (player == null) {
					System.out.println("ERROR : Wrong judge !");
					return;
				}
				this.yRot = player.yRot;
				this.yRotO = this.yRot;
				this.xRot = player.xRot * 0.5F;
				this.setRot(this.yRot, this.xRot);
				this.yBodyRot = this.yRot;
				this.yHeadRot = this.yBodyRot;
				float f = player.xxa * 0.5F;
				float f1 = player.zza;
				if (f1 <= 0.0F) {
					f1 *= 0.25F;
				}
				if(this.horizontalCollision) {
					Vector3d Vector3d = this.getDeltaMovement();
	                this.setDeltaMovement(Vector3d.x, 0.2D, Vector3d.z);
				}
				this.flyingSpeed = this.getSpeed() * 0.1F;
				this.setSpeed((float) this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
				super.travel(new Vector3d((double) f, p_213352_1_.y, (double) f1));
				this.animationSpeedOld = this.animationSpeed;
				double d2 = this.getX() - this.xo;
				double d3 = this.getZ() - this.zo;
				float f4 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4.0F;
				if (f4 > 1.0F) {
					f4 = 1.0F;
				}
				this.animationSpeed += (f4 - this.animationSpeed) * 0.4F;
				this.animationPosition += this.animationSpeed;
			} else {
				this.flyingSpeed = 0.02F;
				super.travel(p_213352_1_);
			}
		}
	}
	
	@Override
	protected boolean checkNormalPlantWeak() {
		if(this.isInWater()) return true;
		return super.checkNormalPlantWeak();
	}

	protected boolean mountTo(PlayerEntity player) {
		if (!this.level.isClientSide) {
			this.yRot = player.yRot;
			this.xRot = player.xRot;
			player.startRiding(this);
			return true;
		}
		return false;
	}

	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if (player.isSecondaryUseActive() || EntityUtil.canTargetEntity(this, player)) {
			return ActionResultType.FAIL;
		}
		ItemStack stack = player.getItemInHand(hand);
		if(this.getAttackTime() == 0 && stack.isEmpty()) {
			if(this.mountTo(player)) return ActionResultType.SUCCESS;
		} else if(stack.getItem() == ItemRegister.CORN.get()) { 
			if(this.cornCnt < this.MaxCornCnt) {
			    ++ this.cornCnt;
			    stack.shrink(Math.min(stack.getCount(), this.MaxCornCnt - this.cornCnt));
			}
			return ActionResultType.CONSUME;
		}
		return super.interactAt(player, vec3d, hand);
	}
	
	@Override
	public double getPassengersRidingOffset() {
		return 0.8D;
	}

	@Override
	protected boolean canAddPassenger(Entity passenger) {
		return ! EntityUtil.canTargetEntity(this, passenger) && super.canAddPassenger(passenger);
	}

	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return false;
	}

	@Override
	public boolean rideableUnderWater() {
		return false;
	}

	public int getPreCD() {
		return 1000;
	}
	
	public int getSuperCornNum() {
		if(this.isPlantInStage(1)) return 4;
		if(this.isPlantInStage(2)) return 5;
		return 6;
	}
	
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 145 + 5 * lvl;
		return 250F;
	}
	
	public float getMoveSpeed() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 0.19F + 0.1F * lvl;
		return 0.4F;
	}

	public int getAnimCD() {
		return 60;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(1.25f, 1f);
	}

	@Override
	public int getSuperTimeLength() {
		return 60;
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("cannon_pre_tick")) {
			this.preTick = compound.getInt("cannon_pre_tick");
		}
		if (compound.contains("cannon_corn_num")) {
			this.setCornNum(compound.getInt("cannon_corn_num"));
		}
		if(compound.contains("cannon_pop_corn_cnt")) {
			this.cornCnt = compound.getInt("cannon_pop_corn_cnt");
		}
		if(compound.contains("cannon_lock_target")) {
			Entity entity = level.getEntity(compound.getInt("cannon_lock_target"));
			if(entity instanceof LivingEntity) {
				this.lockTarget = Optional.ofNullable((LivingEntity) entity);
			}
		}
		if(compound.contains("cannon_lock_pos")) {
			CompoundNBT nbt = compound.getCompound("cannon_lock_pos");
			this.lockPos = Optional.ofNullable(new BlockPos(nbt.getInt("lock_posX"), nbt.getInt("lock_posY"), nbt.getInt("lock_posZ")));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("cannon_pre_tick", this.preTick);
		compound.putInt("cannon_corn_num", this.getCornNum());
		compound.putInt("cannon_pop_corn_cnt", this.cornCnt);
		if(this.lockTarget.isPresent()) {
			compound.putInt("cannon_lock_target", this.lockTarget.get().getId());
		}
		if(this.lockPos.isPresent()) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putInt("lock_posX", this.lockPos.get().getX());
			nbt.putInt("lock_posY", this.lockPos.get().getY());
			nbt.putInt("lock_posZ", this.lockPos.get().getZ());
			compound.put("cannon_lock_pos", nbt);
		}
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.COB_CANNON;
	}

	public int getCornNum() {
		return this.entityData.get(CORN_NUM);
	}

	public void setCornNum(int num) {
		this.entityData.set(CORN_NUM, num);
	}

}
