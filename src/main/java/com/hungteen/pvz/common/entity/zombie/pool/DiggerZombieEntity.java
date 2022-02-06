package com.hungteen.pvz.common.entity.zombie.pool;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZRandomTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.misc.PVZLoot;
import com.hungteen.pvz.client.particle.ParticleRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.WorldUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import com.hungteen.pvz.utils.interfaces.IHasMetal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DiggerZombieEntity extends PVZZombieEntity implements IHasMetal {

	private static final DataParameter<Boolean> HAS_PICKAXE = EntityDataManager.defineId(DiggerZombieEntity.class, DataSerializers.BOOLEAN);
	public static final int MAX_OUT_TIME = 30;
	
	public DiggerZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_PICKAXE, true);
	}
	
	@Override
	protected void registerTargetGoals() {
		this.targetSelector.addGoal(0, new PVZRandomTargetGoal(this, true, true, ZombieUtil.NORMAL_TARGET_RANGE, ZombieUtil.NORMAL_TARGET_HEIGHT));
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {
			LivingEntity target = this.getTarget();
			if(this.hasPickaxe()) {
				if(target != null) {
				    if(this.distanceToSqr(target) <= 8) {
				    	this.setAttackTime(MathHelper.clamp(this.getAttackTime() + 1, 0, MAX_OUT_TIME));
				    } else {
				    	this.setAttackTime(MathHelper.clamp(this.getAttackTime() - 1, 0, MAX_OUT_TIME));
				    }
			    } else {
			    	this.setAttackTime(MathHelper.clamp(this.getAttackTime() - 1, 0, MAX_OUT_TIME));
			    }
			} else {
				this.setAttackTime(MathHelper.clamp(this.getAttackTime() + 1, 0, MAX_OUT_TIME));
			}
		} else{
			if(! this.isNotDigging()){
				WorldUtil.spawnRandomSpeedParticle(this.level, ParticleRegister.DIRT_BURST_OUT.get(), this.position(), 0.1F);
			}
		}
		this.refreshDimensions();
	}
	
	@Override
	public void onSyncedDataUpdated(DataParameter<?> data) {
		super.onSyncedDataUpdated(data);
		if(data.equals(HAS_PICKAXE)) {
			this.updateAttributes(this.hasPickaxe());
		}
	}
	
	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.updateAttributes(this.hasPickaxe());
	}
	
	private void updateAttributes(boolean has){
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(has ? ZombieUtil.NORMAL_DAMAGE : ZombieUtil.LOW);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(has ? ZombieUtil.WALK_LITTLE_FAST : ZombieUtil.WALK_LITTLE_SLOW);
	}
	
	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		if(living instanceof PVZPlantEntity && ! this.isNotDigging()){
			return false;
		}
		return super.canBeTargetBy(living) ;
	}
	
	@Override
	public boolean canBeAttractedBy(ICanAttract defender) {
		return ! this.hasPickaxe();
	}
	
	@Override
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		if(! this.isNotDigging() && source.isProjectile()){
			return true;
		}
		return super.isZombieInvulnerableTo(source);
	}
	
	/**
	 * is zombie digging underground.
	 * {@link #isZombieInvulnerableTo(DamageSource)}
	 * {@link #canBeTargetBy(LivingEntity)}
	 */
	public boolean isNotDigging() {
		return this.getAttackTime() == DiggerZombieEntity.MAX_OUT_TIME;
	}
	
	@Override
	public float getLife() {
		return 40;
	}
	
	@Override
	public boolean hasMetal() {
		return this.hasPickaxe();
	}

	@Override
	public void decreaseMetal() {
		this.setPickaxe(false);
	}

	@Override
	public void increaseMetal() {
		this.setPickaxe(true);
	}
	
	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.IRON_PICKAXE;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.4f, this.getAttackTime() * 0.02F + 0.1F);
		return EntitySize.scalable(0.8f, this.getAttackTime() * 0.06F + 0.2F);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("digger_has_pickaxe")) {
			this.setPickaxe(compound.getBoolean("digger_has_pickaxe"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("digger_has_pickaxe", this.hasPickaxe());
	}
	
	public void setPickaxe(boolean has) {
		this.entityData.set(HAS_PICKAXE, has);
	}
	
	public boolean hasPickaxe() {
		return this.entityData.get(HAS_PICKAXE);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.DIGGER_ZOMBIE;
	}
	
	@Override
    public ZombieType getZombieType() {
	    return PoolZombies.DIGGER_ZOMBIE;
    }

}
