package com.hungteen.pvz.common.entity.misc;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.entity.AbstractOwnerEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.flame.JalapenoEntity;
import com.hungteen.pvz.common.entity.plant.ice.IceShroomEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.stream.Collectors;

public class ElementBallEntity extends AbstractOwnerEntity {

	private static final DataParameter<Integer> ELEMENTS = EntityDataManager.defineId(ElementBallEntity.class, DataSerializers.INT);
	protected Entity target;
	protected boolean isAutoBall = false;
	protected float speed = 0.25F;
	private static final int SEARCH_CD = 20;
	private static final float SEARCH_RANGE = 50;
	private int removeTick = 0;
	
	public ElementBallEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ELEMENTS, ElementTypes.FLAME.ordinal());
	}
	
	/**
	 * {@link IceShroomEntity#startBomb(boolean)}
	 * {@link JalapenoEntity#startBomb(boolean)}
	 */
	public static void killElementBalls(LivingEntity attacker, float range, ElementTypes type) {
		attacker.level.getEntitiesOfClass(ElementBallEntity.class, EntityUtil.getEntityAABB(attacker, range, range), target -> {
			return target.getElementBallType() == type && EntityUtil.checkCanEntityBeAttack(attacker, target);
		}).forEach(target -> {
			target.onKilledByPlants(attacker);
		});
	}
	
	/**
	 * {@link #killElementBalls(LivingEntity, float, ElementTypes)}
	 */
	public void onKilledByPlants(LivingEntity entity) {
		if(entity instanceof PVZPlantEntity) {
			PVZPlantEntity plant = (PVZPlantEntity) entity;
			plant.getOwnerUUID().ifPresent((uuid) -> {
			    PlayerEntity player = level.getPlayerByUUID(uuid);
			    if(player != null) {
				    PlantCardItem item = (this.getElementBallType() == ElementTypes.FLAME ? ItemRegister.ICE_SHROOM_CARD.get() : ItemRegister.JALAPENO_CARD.get());
					PlayerUtil.setItemStackCD(player, new ItemStack(item), 160);
			    }
		    });
		} 
		this.remove();
	}
	
	@Override
	public void tick() {
		this.noPhysics = true;
		super.tick();
		this.tickMove();
		this.tickCollision();
		if(! level.isClientSide) {
			if(this.tickCount >= PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.ElementBallLiveTick.get()) {
				this.remove();
				return ;
			}
			if(this.isAutoBall){
				if(EntityUtil.isEntityValid(this.target)){
					this.setDeltaMovement(this.target.position().subtract(this.position()).normalize().scale(this.speed));
				} else {
					this.findTarget();
				}
			}
			if(this.getOwner() == null){
				++ this.removeTick;
				if(this.removeTick >= 15){
					this.remove();
				}
			} else{
				this.removeTick = 0;
			}
		}
	}

	public void shoot(LivingEntity target){
		if(target != null){
			if(this.isAutoBall){
				this.target = target;
			} else{
				this.setDeltaMovement(target.position().subtract(this.position()).normalize().scale(this.speed));
			}
		} else if(this.getOwner() != null){
			this.setDeltaMovement(this.getOwner().getLookAngle().normalize().scale(this.speed));
		}
	}

	@Override
	protected void tickMove() {
		Vector3d vec3d = this.getDeltaMovement();
		this.setDeltaMovement(vec3d.scale(this.isAutoBall ? 0.9 : 1));
		this.move(MoverType.SELF, this.getDeltaMovement());
	}

	private void findTarget() {
		if(this.random.nextInt(this.SEARCH_CD) == 0) {
			final List<LivingEntity> list = EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, this.SEARCH_RANGE, this.SEARCH_RANGE))
					.stream().filter(target -> true).collect(Collectors.toList());
			if(! list.isEmpty()) {
				this.target = list.get(0);
			}
		}
	}
	
	private void tickCollision() {
		if(! level.isClientSide && this.tickCount % 10 == 0) {
			EntityUtil.getTargetableEntities(this, this.getBoundingBox().inflate(1F)).forEach(target -> {
				if(target instanceof PVZPlantEntity) {
					if(target instanceof JalapenoEntity && this.getElementBallType() == ElementTypes.ICE) ;
					else if(target instanceof IceShroomEntity && this.getElementBallType() == ElementTypes.FLAME) ;
					else target.hurt(this.getAttackSource(), EntityUtil.getCurrentMaxHealth((PVZPlantEntity) target));
				} else if(target instanceof PVZZombieEntity) {
					target.hurt(this.getAttackSource(), EntityUtil.getCurrentMaxHealth((PVZZombieEntity) target));
				} else {
					target.hurt(this.getAttackSource(), 5);
					target.setDeltaMovement(target.position().subtract(this.position()).normalize().scale(this.speed));
				}
			});
		}
	}
	
	private PVZEntityDamageSource getAttackSource() {
		if(this.getElementBallType() == ElementTypes.FLAME) {
			return PVZEntityDamageSource.causeFlameDamage(this, this.getOwner());
		}
		return PVZEntityDamageSource.causeIceDamage(this, this.getOwner());
	}

	public void setSpeed(float speed){
		this.speed = speed;
	}

	public void setAuto(boolean auto){
		this.isAutoBall = auto;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(3F, 3F);
	}

	@Override
	public boolean isNoGravity() {
		return true;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("element_ball_type")) {
			this.setElementBallType(ElementTypes.values()[compound.getInt("element_ball_type")]);
		}
		if(compound.contains("element_target")) {
			this.target = level.getEntity(compound.getInt("element_target"));
		}
		if(compound.contains("element_speed")) {
			this.speed = compound.getFloat("element_speed"); 
		}
		if(compound.contains("element_speed")) {
			this.isAutoBall = compound.getBoolean("auto_ball");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("element_ball_type", this.getElementBallType().ordinal());
		if(this.target != null) {
			compound.putInt("element_target", this.target.getId());
		}
		compound.putFloat("element_speed", this.speed);
		compound.putBoolean("auto_ball", this.isAutoBall);
	}
	
	public void setElementBallType(ElementTypes type) {
		this.entityData.set(ELEMENTS, type.ordinal());
	}
	
	public ElementTypes getElementBallType() {
		return ElementTypes.values()[this.entityData.get(ELEMENTS)];
	}
	
	public enum ElementTypes {
		FLAME,
		ICE,
	}

}
