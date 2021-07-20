package com.hungteen.pvz.common.entity.zombie.poolnight;

import java.util.Optional;
import java.util.Random;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.ICanAttract;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class BalloonZombieEntity extends PVZZombieEntity {

	private static final DataParameter<Boolean> HAS_BALLOON = EntityDataManager.defineId(BalloonZombieEntity.class, DataSerializers.BOOLEAN);
	private PathNavigator FlyNavigator = new FlyingPathNavigator(this, level);
	private PathNavigator GroundNavigator = new GroundPathNavigator(this, level);
	
	public BalloonZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveControl = new FlyingMovementController(this, 360, true);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_BALLOON, true);
	}
	
	@Override
	protected void registerGoals() {
		this.FlyNavigator = new FlyingPathNavigator(this, level);
		this.GroundNavigator = new GroundPathNavigator(this, level);
		super.registerGoals();
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_SLOW);
		this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(ZombieUtil.FLY_FAST);
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
		if(source instanceof PVZDamageSource) {
			return ((PVZDamageSource) source).isThornDamage();
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
		this.setNoGravity(false);
		this.moveControl = new MovementController(this);
	}
	
	public static boolean canBalloonSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
			SpawnReason reason, BlockPos pos, Random rand) {
		if(! checkZombieSpawn(zombieType, worldIn, reason)) {
			return false;
		}
		return worldIn.getBrightness(LightType.BLOCK, pos) < 8 && worldIn.getDifficulty() != Difficulty.PEACEFUL && (reason == SpawnReason.SPAWNER || worldIn.isEmptyBlock(pos));
	}
	
	@Override
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		return super.isZombieInvulnerableTo(source) || this.hasBalloon();
	}
	
	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		return ! this.hasBalloon();
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
	public boolean canBeButter() {
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
	protected Optional<SoundEvent> getSpawnSound() {
		return Optional.ofNullable(SoundRegister.BALLOON_FULL.get());
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
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.BALLOON_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BALLOON_ZOMBIE;
	}

}
