package com.hungteen.pvz.common.entity.misc;

import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.entity.AbstractOwnerEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.flame.JalapenoEntity;
import com.hungteen.pvz.common.entity.plant.ice.IceShroomEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ElementBallEntity extends AbstractOwnerEntity {

	private static final DataParameter<Integer> ELEMENTS = EntityDataManager.defineId(ElementBallEntity.class, DataSerializers.INT);
	protected Optional<Entity> target = Optional.empty(); 
	public float speed = 0.25F;
	private final int SEARACH_CD = 20;
	private final float SEARCH_RANGE = 50;
	
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
				    float percent = player.getCooldowns().getCooldownPercent(item, 0F);
				    player.getCooldowns().addCooldown(item, MathHelper.floor(200 * percent));
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
			if(this.target.isPresent()) {
			    if(! EntityUtil.isEntityValid(target.get())) { //check target alive
				    this.target = Optional.empty();
				    return ;
			    }
			    //move to target
			    this.setDeltaMovement(this.target.get().position().subtract(this.position()).normalize().scale(this.speed));
		    } else { // search for target
			    if(this.random.nextInt(this.SEARACH_CD) == 0) {
				    List<LivingEntity> list = EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, this.SEARCH_RANGE, this.SEARCH_RANGE));
				    if(list.isEmpty()) return ;
				    this.target = Optional.ofNullable(list.get(this.random.nextInt(list.size())));
			    }
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
	
	private PVZDamageSource getAttackSource() {
		if(this.getElementBallType() == ElementTypes.FLAME) {
			return PVZDamageSource.causeFlameDamage(this, this.getOwner());
		}
		return PVZDamageSource.causeIceDamage(this, this.getOwner());
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
			this.target = Optional.ofNullable(level.getEntity(compound.getInt("element_target")));
		}
		if(compound.contains("element_speed")) {
			this.speed = compound.getFloat("element_speed"); 
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("element_ball_type", this.getElementBallType().ordinal());
		if(this.target.isPresent()) {
			compound.putInt("element_target", this.target.get().getId());
		}
		compound.putFloat("element_speed", this.speed);
	}
	
	public void setElementBallType(ElementTypes type) {
		this.entityData.set(ELEMENTS, type.ordinal());
	}
	
	public ElementTypes getElementBallType() {
		return ElementTypes.values()[this.entityData.get(ELEMENTS)];
	}
	
	public static enum ElementTypes {
		FLAME,
		ICE,
	}

}
