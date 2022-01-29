package com.hungteen.pvz.common.entity.plant.explosion;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZRandomTargetGoal;
import com.hungteen.pvz.common.entity.bullet.CornEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.network.toserver.EntityInteractPacket;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.*;
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
import net.minecraft.util.math.*;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CobCannonEntity extends PVZPlantEntity {

	protected static final DataParameter<Integer> CORN_NUM = EntityDataManager.defineId(CobCannonEntity.class,
			DataSerializers.INT);
	protected Optional<LivingEntity> lockTarget = Optional.empty();
	protected Optional<BlockPos> lockPos = Optional.empty();
	protected int cornCnt = 0;
	protected final int MaxCornCnt = 16;
	protected int preTick = 0;
	private int climbTick = 0;

	public CobCannonEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithPlant = false;
		this.isImmuneToWeak = true;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CORN_NUM, 1);
	}

	protected void initAttributes() {
		super.initAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(2, new PVZRandomTargetGoal(this, true, false, 10, 48, 16));
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
	
	/**
	 * {@link EntityInteractPacket.Handler#onMessage(EntityInteractPacket, java.util.function.Supplier)}
	 */
	public void checkAndAttack() {
		//is in player's control and not in attacking.
		if(this.getAttackTime() == 0 && this.isPlayerRiding()) {
			final PlayerEntity player = (PlayerEntity) this.getPassengers().get(0);
			final Vector3d look = player.getLookAngle();
		    final Vector3d start = player.position().add(0, player.getEyeHeight(), 0);
		    final double range = 60;
		    Vector3d end = start.add(look.normalize().multiply(range, range, range));
		    RayTraceContext ray = new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, player);
		    RayTraceResult result = level.clip(ray);
		    if(result.getType() != RayTraceResult.Type.MISS) {// hit something
			    end = result.getLocation();
		    }
			EntityRayTraceResult entityRay = this.rayTraceEntities(level, player, range, start, end);
		    if(entityRay != null && entityRay.getType() == Type.ENTITY) {
			    if(entityRay.getEntity() instanceof LivingEntity) {//attack entity
			    	this.setAttackTime(this.getAnimCD());
			    	this.setCornNum(this.getCornNum() - 1);
			    	this.lockTarget = Optional.ofNullable((LivingEntity) entityRay.getEntity());
			    }
		    } else if(result.getType() == RayTraceResult.Type.BLOCK) {//attack block.
		    	this.setAttackTime(this.getAnimCD());
		    	BlockPos pos = new BlockPos(end.x(), end.y(), end.z());
		    	this.setCornNum(this.getCornNum() - 1);
		    	this.lockPos = Optional.ofNullable(pos);
		    }
		}
	}
	
	protected void startAttack() {
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
		final float range = 45F;
		final List<LivingEntity> list = EntityUtil.getViewableTargetableEntity(this, EntityUtil.getEntityAABB(this, range, range));
		final int num = (this.isPlantInSuperMode() ? this.getSuperCornNum() : 1);
		for(int i = 0; i < num; ++ i) {
			LivingEntity res = this;
		    if(! list.isEmpty()) {
			    int pos = this.getRandom().nextInt(list.size());
			    res = list.get(pos);
		    }
		    this.shootCorn(res);
		}
	}

	/**
	 * shoot to entity.
	 * {@link #startAttack()}
	 */
	protected void shootCorn(LivingEntity target) {
		CornEntity corn = new CornEntity(level, this);
		this.onShootCorn(corn);
		corn.shootPultBullet(target);
		level.addFreshEntity(corn);
	}
	
	/**
	 * shoot to block.
	 * {@link #startAttack()}
	 */
	protected void shootCorn(BlockPos pos) {
		CornEntity corn = new CornEntity(level, this);
		this.onShootCorn(corn);
		corn.shootPultBullet(pos);
		level.addFreshEntity(corn);
	}
	
	private void onShootCorn(CornEntity corn) {
		corn.setPos(this.getX(), this.getY() + 1.5D, this.getZ());
		corn.setAttackDamage(this.getAttackDamage());
		corn.cornCnt = this.cornCnt;
		this.cornCnt = 0;
		EntityUtil.playSound(this, SoundRegister.COB_LAUNCH.get());
	}
	
	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if (player.isSecondaryUseActive() || EntityUtil.canTargetEntity(this, player)) {
			return ActionResultType.FAIL;
		}
		ItemStack stack = player.getItemInHand(hand);
		if(this.getAttackTime() == 0 && stack.isEmpty()) {
			if(this.mountTo(player)) {
				return ActionResultType.SUCCESS;
			}
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
				player.getBoundingBox().inflate(range), entity -> {
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
	
	@Override
	public boolean canPlaceOuterPlant() {
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
				//jump
				if(this.horizontalCollision) {
					if(++ this.climbTick <= 8) {
						final Vector3d Vector3d = this.getDeltaMovement();
	                    this.setDeltaMovement(Vector3d.x, 0.2D, Vector3d.z);
					}
				} else {
					this.climbTick = 0;
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
	public boolean shouldWilt() {
		return this.isInWaterOrBubble();
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

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.PREPARE_CD, this.getPreCD()),
				Pair.of(PAZAlmanacs.ATTACK_DAMAGE, this.getAttackDamage())
		));
	}

	public int getPreCD() {
		return 1000;
	}
	
	public int getSuperCornNum() {
		return 4;
	}
	
	public float getAttackDamage() {
		return this.getSkillValue(SkillTypes.NORMAL_BOMB_DAMAGE);
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
		return 80;
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

	public int getCornNum() {
		return this.entityData.get(CORN_NUM);
	}

	public void setCornNum(int num) {
		this.entityData.set(CORN_NUM, num);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.COB_CANNON;
	}

}
