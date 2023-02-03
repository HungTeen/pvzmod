package com.hungteen.pvz.common.entity.zombie.base;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public abstract class AbstractBossZombieEntity extends PVZZombieEntity {

	protected final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenScreen(true);
	protected int refreshCountCD = 30; 
	protected int spawnImmuneCD = 100;
	protected float kickRange = 0;
	protected int maxZombieSurround = 40;
	protected int maxPlantSurround = 50;
	protected int nearbyPlantCount = 0;
	protected int nearbyZombieCount = 0;
	private int noTargetTick = 0;
	
	public AbstractBossZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithZombie = false;
		this.canBeCharm = false;
		this.setImmuneAllEffects();
		this.canBeMini = false;
		this.canBeInvisible = false;
		this.canBeStealByBungee = false;
		this.canBeRemove = false;
	}
	
	@Override
	protected VariantType getSpawnType() {
		return VariantType.NORMAL;
	}
	
	@Override
	public void zombieTick() {
		super.zombieTick();
		final float percent = EntityUtil.getCurrentHealth(this) / EntityUtil.getCurrentMaxHealth(this);
		this.bossInfo.setPercent(percent);
		if(! level.isClientSide) {
			this.checkAndHeal(percent);
			if(this.tickCount % this.refreshCountCD == 0) {
				this.nearbyPlantCount = this.getNearbyPlantCount();
			    this.nearbyZombieCount = this.getNearbyZombieCount();
			}
			this.kickEnemiesNearby();
		}
	}
	
	public void kickEnemiesNearby() {
		if(this.tickCount % 10 == 0 && this.kickRange > 0) {
			level.getEntitiesOfClass(LivingEntity.class, EntityUtil.getEntityAABB(this, this.kickRange, this.getBbHeight() + 1), target -> {
				return EntityUtil.canAttackEntity(target, this);
			}).forEach(target -> {
				if(target instanceof PVZPlantEntity) {
					target.setHealth(0);
				} else {
					target.hurt(PVZEntityDamageSource.normal(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
					target.setDeltaMovement(target.position().add(0, target.getEyeHeight(), 0).subtract(this.position()).normalize().scale(2F));
				}
			});
		}
	}
	
	public void checkAndHeal(float percent) {
		if(this.getTarget() == null) {
			if(++ this.noTargetTick >= 40) {
				this.heal(1);
			}
		}
	}
	
	@Override
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		return super.isZombieInvulnerableTo(source) || this.tickCount <= this.spawnImmuneCD;
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
				entityIn.hurt(PVZEntityDamageSource.causeCrushDamage(this), EntityUtil.getMaxHealthDamage((LivingEntity) entityIn, 0.5F));
			}
		}
	}
	
	public void onBossSummon(PVZZombieEntity zombie, BlockPos pos) {
		ZombieUtil.copySummonZombieData(this, zombie);
		EntityUtil.onEntitySpawn(this.level, zombie, pos);
	}
	
	protected int getNearbyPlantCount() {
		final float range = 50;
		return level.getEntitiesOfClass(PVZPlantEntity.class, EntityUtil.getEntityAABB(this, range, range), plant -> {
			return EntityUtil.canTargetEntity(this, plant);
		}).size();
	}
	
	protected int getNearbyZombieCount() {
		final float range = 40;
		return level.getEntitiesOfClass(PVZZombieEntity.class, EntityUtil.getEntityAABB(this, range, range), (plant) -> {
			return ! EntityUtil.canTargetEntity(this, plant);
		}).size();
	}

	@Override
	public float getKBValue() {
		return 1;
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
