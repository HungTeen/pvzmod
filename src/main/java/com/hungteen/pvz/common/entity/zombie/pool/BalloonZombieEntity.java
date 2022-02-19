package com.hungteen.pvz.common.entity.zombie.pool;

import java.util.Optional;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class BalloonZombieEntity extends PVZZombieEntity {

	private static final DataParameter<Boolean> HAS_BALLOON = EntityDataManager.defineId(BalloonZombieEntity.class, DataSerializers.BOOLEAN);
	private final MovementController FlyController = new FlyingMovementController(this, 360, true);
	private final MovementController GroundController = new MovementController(this);
	private PathNavigator FlyNavigator;
	private PathNavigator GroundNavigator;
	
	public BalloonZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_BALLOON, true);
	}
	
	@Override
	protected void registerGoals() {
		//define at here to avoid crash.
		this.FlyNavigator = new FlyingPathNavigator(this, level);
		this.GroundNavigator = new GroundPathNavigator(this, level);
		super.registerGoals();
	}
	
	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(ZombieUtil.FLY_FAST);
		this.setNoGravity(this.hasBalloon());
		this.moveControl = this.hasBalloon() ? FlyController : GroundController;
	}
	
	@Override
	public void onSyncedDataUpdated(DataParameter<?> data) {
		super.onSyncedDataUpdated(data);
		if(data.equals(HAS_BALLOON)) {
			this.setNoGravity(this.hasBalloon());
			this.moveControl = this.hasBalloon() ? FlyController : GroundController;
		}
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(this.hasBalloon() && this.canHitBalloon(source)) {
			this.onBalloonExplode();
			amount = 0;
		}
		return super.hurt(source, amount);
	}
	
	private boolean canHitBalloon(DamageSource source) {
		if(source.getDirectEntity() instanceof ArrowEntity) {
			return true;
		}
		if(source instanceof PVZEntityDamageSource) {
			return ((PVZEntityDamageSource) source).isThornDamage();
		}
		return false;
	}
	
	/**
	 * trigger when balloon hit thorn.
	 * {@link #hurt(DamageSource, float)}
	 */
	public void onBalloonExplode(){
		if(! level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.BALLOON_POP.get());
		}
		this.setBalloon(false);
	}
	
	@Override
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		if(this.hasBalloon() && source.isProjectile()){
			return true;
		}
		return super.isZombieInvulnerableTo(source);
	}
	
	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		if(living instanceof PVZPlantEntity && this.hasBalloon()){
			return false;
		}
		return super.canBeTargetBy(living);
	}
	
	@Override
	public boolean canClimbWalls() {
		return super.canClimbWalls() && ! this.hasBalloon();
	}
	
	@Override
	public boolean canBeAttractedBy(ICanAttract defender) {
		return ! this.hasBalloon();
	}
	
	@Override
	public float getLife() {
		return 23;
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_LITTLE_SLOW;
	}

	@Override
	public boolean canBeButtered() {
		return ! this.hasBalloon();
	}
	
	@Override
	public boolean canBeFrozen() {
		return ! this.hasBalloon();
	}
	
	@Override
	public boolean canBeCold() {
		return ! this.hasBalloon();
	}
	
	@Override
	public PathNavigator getNavigation() {
		if(this.hasBalloon()) {
			if(! (this.navigation instanceof FlyingPathNavigator)) {
			    this.navigation = this.FlyNavigator;
			}
		} else {
			if(! (this.navigation instanceof GroundPathNavigator)) {
				this.navigation = this.GroundNavigator;
			}
		}
		return super.getNavigation();
	}
	
	@Override
	public Optional<SoundEvent> getSpawnSound() {
		return Optional.ofNullable(SoundRegister.BALLOON_INFLATE.get());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("has_balloon")) {
			this.setBalloon(compound.getBoolean("has_balloon"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("has_balloon", this.hasBalloon());
	}
	
	public void setBalloon(boolean has) {
		this.entityData.set(HAS_BALLOON, has);
	}
	
	public boolean hasBalloon() {
		return this.entityData.get(HAS_BALLOON);
	}
	
    @Override
    public ZombieType getZombieType() {
	    return PoolZombies.BALLOON_ZOMBIE;
    }

}
