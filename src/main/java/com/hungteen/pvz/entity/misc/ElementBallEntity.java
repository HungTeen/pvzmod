package com.hungteen.pvz.entity.misc;

import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.flame.JalapenoEntity;
import com.hungteen.pvz.entity.plant.ice.IceShroomEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.roof.ZomBossEntity;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
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

	private static final DataParameter<Integer> ELEMENTS = EntityDataManager.createKey(ElementBallEntity.class, DataSerializers.VARINT);
	protected Optional<Entity> target = Optional.empty(); 
	private float speed;
	private final int SEARACH_CD = 20;
	private final float SEARCH_RANGE = 50;
	
	public ElementBallEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.speed = 0.25F;
	}
	
	public ElementBallEntity(World worldIn, ZomBossEntity boss, float speed) {
		super(EntityRegister.ELEMENT_BALL.get(), worldIn, boss);
		this.speed = speed;
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(ELEMENTS, ElementTypes.FLAME.ordinal());
	}
	
	public void onKilledByPlants(LivingEntity entity) {
		if(entity instanceof PVZPlantEntity) {
			PVZPlantEntity plant = (PVZPlantEntity) entity;
			plant.getOwnerUUID().ifPresent((uuid) -> {
			    PlayerEntity player = world.getPlayerByUuid(uuid);
			    if(player != null) {
				    PlantCardItem item = (this.getElementBallType() == ElementTypes.FLAME ? ItemRegister.ICE_SHROOM_CARD.get() : ItemRegister.JALAPENO_CARD.get());
				    float percent = player.getCooldownTracker().getCooldown(item, 0F);
				    player.getCooldownTracker().setCooldown(item, MathHelper.floor(200 * percent));
			    }
		    });
		} 
		this.remove();
	}
	
	@Override
	public void tick() {
		this.noClip = true;
		super.tick();
		this.tickMove();
		this.tickCollision();
		if(! world.isRemote) {
			if(this.ticksExisted >= PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.ElementBallLiveTick.get()) {
				this.remove();
				return ;
			}
			if(this.target.isPresent()) {
			    if(! EntityUtil.isEntityValid(target.get())) { //check target alive
				    this.target = Optional.empty();
				    return ;
			    }
			    //move to target
			    this.setMotion(this.target.get().getPositionVec().subtract(this.getPositionVec()).normalize().scale(this.speed));
		    } else { // search for target
			    if(this.rand.nextInt(this.SEARACH_CD) == 0) {
				    List<LivingEntity> list = EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, this.SEARCH_RANGE, this.SEARCH_RANGE));
				    if(list.isEmpty()) return ;
				    this.target = Optional.ofNullable(list.get(this.rand.nextInt(list.size())));
			    }
		    }
		}
	}
	
	private void tickCollision() {
		if(! world.isRemote && this.ticksExisted % 10 == 0) {
			EntityUtil.getAttackEntities(this, this.getBoundingBox().grow(0.5F)).forEach((target) -> {
				if(target instanceof PVZPlantEntity) {
					if(target instanceof JalapenoEntity && this.getElementBallType() == ElementTypes.ICE) ;
					else if(target instanceof IceShroomEntity && this.getElementBallType() == ElementTypes.FLAME) ;
					else target.attackEntityFrom(this.getAttackSource(), EntityUtil.getCurrentMaxHealth((PVZPlantEntity) target));
				} else if(target instanceof PVZZombieEntity) {
					target.attackEntityFrom(this.getAttackSource(), EntityUtil.getCurrentMaxHealth((PVZZombieEntity) target));
				} else {
					target.attackEntityFrom(this.getAttackSource(), 5);
					target.setMotion(target.getPositionVec().subtract(this.getPositionVec()).normalize().scale(this.speed));
				}
			});
		}
	}
	
	private PVZDamageSource getAttackSource() {
		if(this.getElementBallType() == ElementTypes.FLAME) return PVZDamageSource.causeFireDamage(this, this.getOwner());
		return PVZDamageSource.causeIceDamage(this, this.getOwner());
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(3F, 3F);
	}
	
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("element_ball_type")) {
			this.setElementBallType(ElementTypes.values()[compound.getInt("element_ball_type")]);
		}
		if(compound.contains("element_target")) {
			this.target = Optional.ofNullable(world.getEntityByID(compound.getInt("element_target")));
		}
		if(compound.contains("element_speed")) {
			this.speed = compound.getFloat("element_speed"); 
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("element_ball_type", this.getElementBallType().ordinal());
		if(this.target.isPresent()) {
			compound.putInt("element_target", this.target.get().getEntityId());
		}
		compound.putFloat("element_speed", this.speed);
	}
	
	public void setElementBallType(ElementTypes type) {
		this.dataManager.set(ELEMENTS, type.ordinal());
	}
	
	public ElementTypes getElementBallType() {
		return ElementTypes.values()[this.dataManager.get(ELEMENTS)];
	}
	
	public static enum ElementTypes {
		FLAME,
		ICE,
	}

}
