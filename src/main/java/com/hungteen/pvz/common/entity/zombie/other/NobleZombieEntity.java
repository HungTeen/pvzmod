package com.hungteen.pvz.common.entity.zombie.other;

import java.util.List;

import com.hungteen.pvz.common.entity.misc.ZombieHandEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public class NobleZombieEntity extends PVZZombieEntity {

	private static final DataParameter<Integer> TP_TICK = EntityDataManager.defineId(NobleZombieEntity.class,
			DataSerializers.INT);
	private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(),
			BossInfo.Color.WHITE, BossInfo.Overlay.NOTCHED_6)).setDarkenScreen(true);
	private int summonTick;
	private final int minSummonTick = 300;
	private final int maxSummonTick = 600;
	private final int maxSummonedCnt = 8;
	private final int minTpCD = 400;
	private final int maxTpCD = 800;
	private final int minSleepAttackCD = 360;
	private final int maxSleepAttackCD = 1000;
	
	public NobleZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setAttackTime(this.maxSleepAttackCD);
		this.summonTick = this.getRandom().nextInt(this.maxSummonTick - this.minSummonTick) + this.minSummonTick;
		this.setTpTick(- this.getRandom().nextInt(this.maxTpCD - this.minTpCD + 1) - this.minTpCD);
		this.xpReward = 1000;
		this.canBeButter = false;
		this.canBeCold = false;
		this.canBeCharm = false;
		this.canBeMini = false;
		this.canBeStealByBungee = false;
		this.canLostHand = false;
		this.canLostHead = false;
		this.canBeRemove = false;
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(TP_TICK, 0);
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_VERY_SLOW);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LITTLE_HIGH);
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! level.isClientSide) {
			for (Entity target : EntityUtil.getTargetableEntities(this,
					EntityUtil.getEntityAABB(this, 50, 50))) {
				if(this.getRandom().nextInt(4) == 0) {
					ZombieHandEntity hand = EntityRegister.ZOMBIE_HAND.get().create(level);
				    hand.setOwner(this);
				    EntityUtil.onEntitySpawn(level, hand, target.blockPosition());
				}
			}
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public void tick() {
		super.tick();
		float percent = this.getHealth() / this.getMaxHealth();
		this.bossInfo.setPercent(percent);
		if(!this.canZombieNormalUpdate()) {
			this.setTpTick(- this.maxTpCD);
		}
	}

	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if (!level.isClientSide) {
			// summon MournerZombie
			if (this.summonTick > 0) {
				--this.summonTick;
			} else if (this.summonTick == 0) {
				this.summonTick = this.getRandom().nextInt(this.maxSummonTick - this.minSummonTick + 1) + this.minSummonTick;
				this.checkAndSummonMournerZombie();
			}
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			} else {
				this.checkAndSleepPlant();
			}
		} else {
			if(this.getAttackTime() < 20) {
				this.level.addParticle(ParticleTypes.NOTE, getX(), getY() + 2f, getZ(), 0, 0, 0);
			}
		}
		this.checkAndHeal();
		if (this.getTpTick() < 0) {
			this.setTpTick(this.getTpTick() + 1);
		} else if (this.getTpTick() == 0) {
			if (this.getRandom().nextInt(5) == 0) {
				this.setTpTick(1);
			}
		} else {
			if (this.getTpTick() >= this.getTpCD()) {
				this.checkAndTeleport();
			}
			if(level.isClientSide) {
				for(int i = 0; i < 9; ++ i) {
					level.addParticle(ParticleTypes.PORTAL, this.getX() + (this.getRandom().nextInt(5) - 2), this.getY() + (this.getRandom().nextInt(3)), this.getZ() + (this.getRandom().nextInt(5) - 2), 0, 0, 0);
				}
			}
			this.setTpTick(this.getTpTick() + 1);
		}
	}

	/**
	 * Skill 1 : Teleport and make explosion
	 */
	private void checkAndTeleport() {
		if (this.getTarget() == null) {
			return;
		}
		BlockPos teleportPos = this.getTarget().blockPosition();
		this.setTpTick(-this.getRandom().nextInt(this.maxTpCD - this.minTpCD + 1) - this.minTpCD);
		float range = this.getExpRange();
		this.teleportToPos(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
		for (Entity target : EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range))) {
			if (target instanceof PVZPlantEntity) {
				target.hurt(PVZDamageSource.causeExplosionDamage(this, this),
						((LivingEntity) target).getMaxHealth());
			}
		}
	}

	/**
	 * Skill 2 : Summon MournerZombie.
	 */
	private void checkAndSummonMournerZombie() {
		int num = this.level.getEntities(EntityRegister.MOURNER_ZOMBIE.get(),
				EntityUtil.getEntityAABB(this, 50, 50), (zombie) -> {
					return !zombie.isCharmed();
				}).size();
		if (num >= this.maxSummonedCnt) {// zombies in range are too many.
			this.checkAndSummonZombieHand();
			return;
		}
		int max = Math.min(this.maxSummonedCnt - num, 3);
		int min = 1;
		int cnt = this.getRandom().nextInt(max - min + 1) + min;
		for (int i = 0; i < cnt; ++i) {
			MournerZombieEntity zombie = EntityRegister.MOURNER_ZOMBIE.get().create(level);
			BlockPos pos = this.getTarget() == null ? this.blockPosition() : this.getTarget().blockPosition();
			for (int chance = 0; chance < 30; ++chance) {
				int x = this.blockPosition().getX()
						+ MathHelper.nextInt(this.random, 5, 20) * MathHelper.nextInt(this.random, -1, 1);
				int y = this.blockPosition().getY() + MathHelper.nextInt(this.random, 0, 10);
				int z = this.blockPosition().getZ()
						+ MathHelper.nextInt(this.random, 5, 20) * MathHelper.nextInt(this.random, -1, 1);
				BlockPos tmp = new BlockPos(x, y, z);
				zombie.setPos(x, y, z);
				if (level.getBlockState(tmp).entityCanStandOnFace(level, tmp, zombie, Direction.UP) && level.isUnobstructed(zombie)
						&& level.noCollision(zombie)) {
					pos = tmp;
					break;
				}
			}
			EntityUtil.onEntitySpawn(level, zombie, pos);
		}
	}
	
	/**
	 * Skill 3 : Sleep plants in a specific area.
	 */
	private void checkAndSleepPlant() {
		List<PVZPlantEntity> list = this.level.getEntitiesOfClass(PVZPlantEntity.class, EntityUtil.getEntityAABB(this, 50, 50), (plant)->{
			return !plant.isCharmed();
		});
		int len = list.size();
		if(len == 0) {
			this.setAttackTime(this.getRandom().nextInt(this.maxSleepAttackCD - this.minSleepAttackCD + 1) + this.minSleepAttackCD);
			return ;
		}
		int pos = this.getRandom().nextInt(len);
		PVZPlantEntity plant = list.get(pos);
		final float range = 1.5f;
		for(PVZPlantEntity target : this.level.getEntitiesOfClass(PVZPlantEntity.class, EntityUtil.getEntityAABB(plant, range, range), (p)->{
			return !p.isCharmed();
		})) {
			target.sleepTime = 2400;
		}
		this.setAttackTime(this.getRandom().nextInt(this.maxSleepAttackCD - this.minSleepAttackCD + 1) + this.minSleepAttackCD);
	}
	
	/**
	 * Skill 4 : Summon Zombie's Hands.
	 */
	private void checkAndSummonZombieHand() {
		int cnt = this.getHandSummonNum();
		List<LivingEntity> list = EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, 50, 50));
		for(int i = 0; i < list.size(); ++ i) {
			if(i + cnt >= list.size() || this.getRandom().nextInt(5) == 0) {
				ZombieHandEntity hand = EntityRegister.ZOMBIE_HAND.get().create(level);
				hand.setOwner(this);
				EntityUtil.onEntitySpawn(level, hand, list.get(i).blockPosition());
				-- cnt;
				if(cnt == 0) {
					break;
				}
			}
		}
	}
	
	/**
	 * Skill 5 : Heal itself.
	 */
	private void checkAndHeal() {
		float percent = this.getHealth() / this.getMaxHealth();
		if(!level.isClientSide) {
			if(this.getTarget() == null) {
			    this.heal(0.3f);
			} else if(percent < 1f / 2) {
				this.heal(0.2f);
			} else if(percent < 1f / 6) {
				this.heal(0.5f);
			}
		}
	}
	
	public void teleportToPos(double x, double y, double z) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(x, y, z);
		while (blockpos$mutable.getY() > 0
				&& !this.level.getBlockState(blockpos$mutable).getMaterial().blocksMotion()) {
			blockpos$mutable.move(Direction.DOWN);
		}
		BlockState blockstate = this.level.getBlockState(blockpos$mutable);
		boolean flag = blockstate.getMaterial().blocksMotion();
		if (flag) {
			boolean flag2 = this.randomTeleport(x, y, z, true);
			if (flag2) {
				this.level.playSound((PlayerEntity) null, this.xo, this.yo, this.zo,
						SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
				this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
			}
		}
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(amount >= 10) {
			amount /= 3f;
		}else {
			amount /= 2f;
		}
		return super.hurt(source, amount);
	}
	
	@Override
	public float getLife() {
		return 1000;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.9f);
	}

	protected int getTpCD() {
		float percent = this.getHealth() / this.getMaxHealth();
		if (percent < 1f / 3) {
			return 60;
		} else if (percent < 2f / 3) {
			return 80;
		}
		return 100;
	}

	protected float getExpRange() {
		float percent = this.getHealth() / this.getMaxHealth();
		if (percent < 1f / 2) {
			return 2.5f;
		}
		return 1.5f;
	}
	
	protected int getHandSummonNum() {
		float percent = this.getHealth() / this.getMaxHealth();
		if (percent < 1f / 3) {
			return 3;
		} else if (percent < 2f / 3) {
			return 2;
		}
		return 1;
	}

	@Override
	protected Type getSpawnType() {
		return Type.NORMAL;
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("summon_zombie_tick")) {
			this.summonTick = compound.getInt("summon_zombie_tick");
		}
		if(compound.contains("zombie_tp_tick")) {
			this.setTpTick(compound.getInt("zombie_tp_tick"));
		}
		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("summon_zombie_tick", this.summonTick);
		compound.putInt("zombie_tp_tick", this.getTpTick());
	}

	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}
	
	public int getTpTick() {
		return this.entityData.get(TP_TICK);
	}

	public void setTpTick(int tick) {
		this.entityData.set(TP_TICK, tick);
	}
	
	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.NOBLE_ZOMBIE;
	}
	
	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NOBLE_ZOMBIE;
	}

}
