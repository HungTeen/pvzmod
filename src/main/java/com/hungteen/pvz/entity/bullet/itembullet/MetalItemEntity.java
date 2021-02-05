package com.hungteen.pvz.entity.bullet.itembullet;

import java.util.List;

import com.hungteen.pvz.entity.plant.assist.MagnetShroomEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.MetalTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MetalItemEntity extends PVZItemBulletEntity {

	private static final DataParameter<Integer> METAL_TYPE = EntityDataManager.createKey(MetalItemEntity.class,
			DataSerializers.VARINT);
	private static final DataParameter<Integer> METAL_STATE = EntityDataManager.createKey(MetalItemEntity.class,
			DataSerializers.VARINT);
	private ItemStack stack = null;
	
	public MetalItemEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public MetalItemEntity(World worldIn, LivingEntity shooter, MetalTypes metalType) {
		super(EntityRegister.METAL.get(), worldIn, shooter);
		this.setMetalType(metalType);
	}
	
	@Override
	protected void registerData() {
		dataManager.register(METAL_TYPE, MetalTypes.EMPTY.ordinal());
		dataManager.register(METAL_STATE, MetalStates.ABSORB.ordinal());
	}
	
	@Override
	public void tick() {
		super.tick();
		this.noClip = (this.getMetalState() != MetalStates.SHOOT);
		if(! world.isRemote && this.getThrower() != null && this.getThrower() instanceof MagnetShroomEntity) {
			MagnetShroomEntity thrower = (MagnetShroomEntity) this.getThrower();
			if(this.getDistanceSq(thrower) <= 3) {
				// near the thrower
				if(this.getMetalState() == MetalStates.ABSORB) {
					thrower.setMetalType(getMetalType());
				    this.remove();
				} else if(this.getMetalState() == MetalStates.BULLET){
					this.setMetalState(MetalStates.WAIT);
				}
			}
			if(this.getMetalState() == MetalStates.BULLET || this.getMetalState() == MetalStates.ABSORB) {
				Vec3d vec = thrower.getPositionVec().add(0, thrower.getHeight(), 0).subtract(this.getPositionVec());
			    double speed = 0.8D;
			    vec = vec.normalize().mul(speed, speed, speed);
			    this.setMotion(vec);
			} else if(this.getMetalState() == MetalStates.WAIT){
				LivingEntity target = getAttackTarget(thrower);
				if(target == null) {
					this.setMotion(0, 0, 0);
					return ;
				}
				this.setMetalState(MetalStates.SHOOT);
				Vec3d vec = target.getPositionVec().add(0, target.getEyeHeight(), 0).subtract(this.getPositionVec());
				this.shootPea(vec.x, vec.y, vec.z, 1.4F);
			}
		}
	}
	
	private LivingEntity getAttackTarget(MagnetShroomEntity thrower) {
		if(this.ticksExisted % 50 != 0) return null;
		List<LivingEntity> list = world.getEntitiesWithinAABB(LivingEntity.class, EntityUtil.getEntityAABB(thrower, 20, 20), (entity)->{
			return EntityUtil.checkCanEntityAttack(thrower, entity);
		});
		if(list.size() == 0) return null;
		int pos = thrower.getRNG().nextInt(list.size());
		return list.get(pos);
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (this.getMetalState() == MetalStates.SHOOT && checkCanAttack(target)) {
				target.hurtResistantTime = 0;
				target.attackEntityFrom(PVZDamageSource.causeAppeaseDamage(this, this.getThrower()), getAttackDamage());
				EntityUtil.playSound(this, SoundRegister.METAL_HIT.get());
				flag = true;
			}
		}
		this.world.setEntityState(this, (byte) 3);
		if (flag || ! this.checkLive(result)) {
			this.remove();
		}
	}
	
	@Override
	protected boolean checkLive(RayTraceResult result) {
		if(this.getMetalState() != MetalStates.SHOOT || result.getType() == RayTraceResult.Type.BLOCK) return true;
		return super.checkLive(result);
	}
	
	protected float getAttackDamage() {
		return 50;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.2f, 0.2f, false);
	}

	@Override
	protected int getMaxLiveTick() {
		return 1200;
	}
	
	@Override
	public ItemStack getItem() {
		return this.stack == null ? this.stack = new ItemStack(MetalTypes.getMetalItem(this.getMetalType())) : this.stack;
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0;
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("metal_type", this.getMetalType().ordinal());
		compound.putInt("metal_state", this.getMetalState().ordinal());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("metal_type")) {
			this.setMetalType(MetalTypes.values()[compound.getInt("metal_type")]);
		}
		if(compound.contains("metal_state")) {
			this.setMetalState(MetalStates.values()[compound.getInt("metal_state")]);
		}
	}

	public MetalTypes getMetalType() {
		return MetalTypes.values()[dataManager.get(METAL_TYPE)];
	}

	public void setMetalType(MetalTypes type) {
		dataManager.set(METAL_TYPE, type.ordinal());
	}
	
	public MetalStates getMetalState() {
		return MetalStates.values()[dataManager.get(METAL_STATE)];
	}

	public void setMetalState(MetalStates type) {
		dataManager.set(METAL_STATE, type.ordinal());
	}
	
	public enum MetalStates {
		ABSORB,
		BULLET,
		WAIT,
		SHOOT;
	}
	
}
