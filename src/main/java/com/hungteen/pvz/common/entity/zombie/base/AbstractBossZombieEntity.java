package com.hungteen.pvz.common.entity.zombie.base;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public abstract class AbstractBossZombieEntity extends PVZZombieEntity {

	protected final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenScreen(true);
	protected int refreshCountCD = 30; 
	protected int nearbyPlantCount = 0;
	protected int nearbyZombieCount = 0;
	
	public AbstractBossZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithZombie = false;
		this.setImmuneAllEffects();
		this.canBeMini = false;
		this.canBeInvis = false;
		this.canBeStealByBungee = false;
		this.canBeRemove = false;
	}
	
	@Override
	protected Type getSpawnType() {
		return Type.NORMAL;
	}
	
	@Override
	public void zombieTick() {
		super.zombieTick();
		final float percent = (this.getDefenceLife() + this.getHealth()) / (this.getMaxHealth() + this.getExtraLife());
		this.bossInfo.setPercent(percent);
		if(! level.isClientSide) {
			if(this.tickCount % this.refreshCountCD == 0) {
				this.nearbyPlantCount = this.getNearbyPlantCount();
			    this.nearbyZombieCount = this.getNearbyPlantCount();
			}
		}
	}
	
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		return EntityUtil.canTargetEntity(this, target);
	}

	@Override
	protected void doPush(Entity entityIn) {
		super.doPush(entityIn);
		if (! level.isClientSide && entityIn instanceof LivingEntity && EntityUtil.canTargetEntity(this, entityIn)) {
			if(this.tickCount % 5 == 0) {
				entityIn.hurt(PVZDamageSource.causeCrushDamage(this), EntityUtil.getMaxHealthDamage((LivingEntity) entityIn));
			}
		}
	}
	
	public void onBossSummon(PVZZombieEntity zombie, BlockPos pos) {
		ZombieUtil.copySummonZombieData(this, zombie);
		EntityUtil.onEntitySpawn(this.level, zombie, pos);
		zombie.setZombieLevel(this.getZombieLevel());
	}
	
	protected int getNearbyPlantCount() {
		final float range = 50;
		return level.getEntitiesOfClass(PVZPlantEntity.class, EntityUtil.getEntityAABB(this, range, range), plant -> {
			return EntityUtil.canTargetEntity(this, plant);
		}).size();
	}
	
	protected int getNearbyZombieCount() {
		final float range = 30;
		return level.getEntitiesOfClass(PVZZombieEntity.class, EntityUtil.getEntityAABB(this, range, range), (plant) -> {
			return ! EntityUtil.canTargetEntity(this, plant);
		}).size();
	}
	
	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}
	
}
