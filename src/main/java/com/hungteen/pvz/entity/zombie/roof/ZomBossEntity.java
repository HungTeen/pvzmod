package com.hungteen.pvz.entity.zombie.roof;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.ai.target.ZombieNearestTargetGoal;
import com.hungteen.pvz.entity.drop.JewelEntity;
import com.hungteen.pvz.entity.misc.DestroyCarEntity;
import com.hungteen.pvz.entity.misc.ElementBallEntity;
import com.hungteen.pvz.entity.misc.ElementBallEntity.ElementTypes;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.roof.BungeeZombieEntity.BungeeStates;
import com.hungteen.pvz.entity.zombie.roof.BungeeZombieEntity.BungeeTypes;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public class ZomBossEntity extends PVZZombieEntity {

	private static final DataParameter<Integer> STATES = EntityDataManager.createKey(ZomBossEntity.class, DataSerializers.VARINT);
	private static final List<Zombies> ZOMBIES_1 = Arrays.asList(Zombies.NORMAL_ZOMBIE, Zombies.NEWSPAPER_ZOMBIE, Zombies.SCREENDOOR_ZOMBIE, Zombies.SNORKEL_ZOMBIE, Zombies.BALLOON_ZOMBIE);
	private static final List<Zombies> ZOMBIES_2 = Arrays.asList(Zombies.CONEHEAD_ZOMBIE, Zombies.POLE_ZOMBIE, Zombies.DANCING_ZOMBIE, Zombies.OLD_ZOMBIE, Zombies.JACK_IN_BOX_ZOMBIE, Zombies.DIGGER_ZOMBIE, Zombies.BALLOON_ZOMBIE, Zombies.POGO_ZOMBIE);
	private static final List<Zombies> ZOMBIES_3 = Arrays.asList(Zombies.BUCKETHEAD_ZOMBIE, Zombies.FOOTBALL_ZOMBIE, Zombies.ZOMBONI, Zombies.JACK_IN_BOX_ZOMBIE, Zombies.DIGGER_ZOMBIE, Zombies.LADDER_ZOMBIE, Zombies.CATAPULT_ZOMBIE);
	private static final List<Zombies> ZOMBIES_4 = Arrays.asList(Zombies.FOOTBALL_ZOMBIE, Zombies.GIGA_FOOTBALL_ZOMBIE, Zombies.ZOMBONI, Zombies.LAVA_ZOMBIE, Zombies.CATAPULT_ZOMBIE, Zombies.GARGANTUAR);
	private static final List<Zombies> ZOMBIES_5 = Arrays.asList(Zombies.GIGA_FOOTBALL_ZOMBIE, Zombies.SUNDAY_EDITION_ZOMBIE, Zombies.LAVA_ZOMBIE, Zombies.CATAPULT_ZOMBIE, Zombies.GARGANTUAR, Zombies.SAD_GARGANTUAR);
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
	private final int maxPlantsInRange = 40;
	private final int maxZombiesInRange = 30;
	private int summonZombieTick = 0;
	public int shootBallCD = 100;
	public int stealPlantCD = 100;
	
	public ZomBossEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithZombie = false;
		this.canBeMini = false;
		this.canBeButter = false;
		this.canBeCharm = false;
		this.canBeCold = false;
		this.canBeFrozen = false;
		this.canBeInvis = false;
		this.canBeStealByBungee = false;
		this.hasDirectDefence = true;
		this.maxDeathTime = 60;
		this.resetShootBallCD();
		this.resetStealCD();
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! world.isRemote) {
			this.setDefenceLife(this.getZomBossDefenceLife());
			final float range = 50F;
			EntityUtil.getAttackEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
				if(target instanceof PVZPlantEntity) {
					target.remove();
				}
			});
			EntityUtil.playSound(this, SoundRegister.ZOMBOSS_LAUGH.get());
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(STATES, ZomBossStates.NORMAL.ordinal());
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 40.0F));
		this.targetSelector.addGoal(0, new ZombieNearestTargetGoal(this, false, 70, 60));
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
	}
	
	@Override
	public void tick() {
		super.tick();
		float percent = (this.getDefenceLife() + this.getHealth()) / (this.getMaxHealth() + this.getZomBossDefenceLife());
		this.bossInfo.setPercent(percent);
		if(this.shootBallCD > 0) {
			-- this.shootBallCD;
			-- this.stealPlantCD;
		}
		if(! world.isRemote) {
			this.checkAndHeal();
			this.kickEnemiesNearby();
		}
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! world.isRemote) {
			++ this.summonZombieTick;
			if(this.summonZombieTick >= this.getSummonZombieCD()) {
				this.summonZombieTick = 0;
				this.summonZombieByBungee();
			}
			if(this.getZomBossState() == ZomBossStates.NORMAL) {
				int flag = this.getRNG().nextInt(10);
				if(this.ticksExisted % 100 == 0 && this.getNearPlantsCount() > this.maxPlantsInRange) { 
					this.setZomBossState(ZomBossStates.CAR);
				} else if(flag < 4 && this.stealPlantCD <= 0) { 
					this.setZomBossState(ZomBossStates.STEAL);
					EntityUtil.playSound(this, SoundRegister.DRAG.get());
					this.resetStealCD();
				} else if(this.shootBallCD <= 0) {// 5 - 10
					this.setZomBossState(this.getRNG().nextInt(2) == 0 ? ZomBossStates.FLAME : ZomBossStates.ICE);
					EntityUtil.playSound(this, SoundRegister.ZOMBOSS_SHOOT.get());
					this.resetShootBallCD();
				}
			} else if(this.getZomBossState() == ZomBossStates.FLAME || this.getZomBossState() == ZomBossStates.ICE) {
				this.setAttackTime(this.getAttackTime() + 1);
				if(this.getAttackTime() >= this.getAnimShootCD()) {
					this.shootElementBall();
				}
			} else if(this.getZomBossState() == ZomBossStates.CAR) {
				this.setAttackTime(this.getAttackTime() + 1);
				if(this.getAttackTime() >= this.getAnimThrowCD()) {
					this.throwDestroyCar();
				}
			} else if(this.getZomBossState() == ZomBossStates.STEAL) {
				this.setAttackTime(this.getAttackTime() + 1);
				if(this.getAttackTime() >= this.getAnimThrowCD()) {
					this.stealRandomTargets();
				}
			}
		}
	}
	
	/**
	 * Skill 1 : Summon Zombie by Bungee
	 */
	public void summonZombieByBungee() {
		if(this.getNearZombiesCount() >= this.maxZombiesInRange) return ;//too many zombies nearby
		for(int i = 0; i < this.getRNG().nextInt(2) + 1; ++ i) {
			getSummonZombie().ifPresent((zombie) -> {
			    EntityUtil.onMobEntitySpawn(world, zombie, getPosition());
			    BungeeZombieEntity bungee = EntityRegister.BUNGEE_ZOMBIE.get().create(world);
		        bungee.setBungeeType(BungeeTypes.SUMMON);
		        bungee.setBungeeState(BungeeStates.DOWN);
		        bungee.setStealTarget(zombie);
		        EntityUtil.onMobEntityRandomPosSpawn(world, bungee, getPosition().up(20), 5);
		        EntityUtil.playSound(this, SoundRegister.BUNGEE_SCREAM.get());
		    });
		}
	}
	
	/**
	 * Skill 2 : Shoot ElementBall To Target
	 */
	public void shootElementBall() {
		int now = getBossStage();
		float speed = (now < 2 ? 0.1F : (now < 4 ? 0.2F : 0.25F));
		ElementBallEntity ball = new ElementBallEntity(world, this, speed);
		ball.setPosition(getPosX(), getPosY() + this.getEyeHeight(), getPosZ());
		ball.setElementBallType(this.getZomBossState() == ZomBossStates.ICE ? ElementTypes.ICE : ElementTypes.FLAME);
		world.addEntity(ball);
		this.setAttackTime(0);
		this.setZomBossState(ZomBossStates.NORMAL);
	}
	
	/**
	 * Skill 3 : throw destroy car to random target
	 */
	public void throwDestroyCar() {
		this.setZomBossState(ZomBossStates.NORMAL);
		int plantCount = this.getNearPlantsCount();
		int throwNum = (plantCount <= 40 ? 1 : ((plantCount - 40 + 1) / 2 + 1));
		float range = 50;
		List<LivingEntity> list = EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, range, range));
		if(list.isEmpty()) return ;
		for(int i = 0; i < throwNum; ++ i) {
			int pos = this.getRNG().nextInt(list.size());
			DestroyCarEntity car = new DestroyCarEntity(world, this);
			car.setPosition(getPosX(), getPosY() + this.getEyeHeight(), getPosZ());
			car.shootPultBullet(list.get(pos));
			world.addEntity(car);
		}
	}
	
	/**
	 * Skill 4 : if no target, then heal for 100 life per second
	 */
	public void checkAndHeal() {
		if(this.getAttackTarget() == null) {
			this.heal(10);
		}
	}
	
	/**
	 * Skill 5 : Kick all enemies nearby zomboss
	 */
	public void kickEnemiesNearby() {
		if(this.ticksExisted % 20 == 0) {
			float range = 5;
			world.getEntitiesWithinAABB(LivingEntity.class, EntityUtil.getEntityAABB(this, range, 15), (target) -> {
				return EntityUtil.checkCanEntityAttack(target, this);
			}).forEach((target) -> {
				if(target instanceof PVZPlantEntity) target.setHealth(0);
				else {
					target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this), 12F);
					target.setMotion(target.getPositionVec().add(0, target.getEyeHeight(), 0).subtract(this.getPositionVec()).normalize().scale(2F));
				}
			});
		}
	}
	
	/**
	 * Skill 6 : Steal Target By Bungee
	 */
	public void stealRandomTargets() {
		this.setZomBossState(ZomBossStates.NORMAL);
		int now = this.getBossStage();
		int minCnt = 3 + now / 2;
		int maxCnt = 3 + now;
		int cnt = this.getRNG().nextInt(maxCnt - minCnt + 1) + minCnt;
		float range = 50;
		List<LivingEntity> list = EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, range, range));
		if(list.isEmpty()) return ; // no target at all
		for(int i = 0; i < cnt; ++ i) {
			LivingEntity target = list.get(this.getRNG().nextInt(list.size()));
			BungeeZombieEntity zombie = EntityRegister.BUNGEE_ZOMBIE.get().create(world);
			zombie.setBungeeType(BungeeTypes.STEAL);
			zombie.setStealTarget(target);
			zombie.setBungeeState(BungeeStates.DOWN);
			EntityUtil.onMobEntitySpawn(world, zombie, getPosition().up(18));
		}
	}
	
	@Override
	protected void onDeathUpdate() {
		super.onDeathUpdate();
		if(this.deathTime % 20 == 1) {
			if(world.isRemote) {
				world.addParticle(ParticleTypes.EXPLOSION_EMITTER, getPosX(), getPosY(), getPosZ(), 0, 0, 0);
				world.addParticle(ParticleTypes.EXPLOSION_EMITTER, getPosX(), getPosY() + 5, getPosZ(), 0, 0, 0);
			}
		}
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if(! world.isRemote) {
			this.bossInfo.getPlayers().forEach((player) -> {
				CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger(player, this, cause);
			});
		}
	}
	
	@Override
	protected void dropCoinOrSpecial() {
		float range = 50;
		int playerCnt = world.getEntitiesWithinAABB(PlayerEntity.class , EntityUtil.getEntityAABB(this, range, range), (player) -> {
			return PlayerUtil.isPlayerSurvival(player);
		}).size();
		for(int i = 0; i < 3 + 2 * playerCnt; ++ i) {
			JewelEntity jewel = EntityRegister.JEWEL.get().create(world);
			EntityUtil.onMobEntityRandomPosSpawn(world, jewel, getPosition().up(5), 4);
		}
	}
	
	private Optional<PVZZombieEntity> getSummonZombie() {
		int stage = getBossStage();
		List<Zombies> summon_zombie_list = new ArrayList<>();
		if(stage == 1) summon_zombie_list = ZOMBIES_1;
		else if(stage == 2) summon_zombie_list = ZOMBIES_2;
		else if(stage == 3) summon_zombie_list = ZOMBIES_3;
		else if(stage == 4) summon_zombie_list = ZOMBIES_4;
		else if(stage == 5) summon_zombie_list = ZOMBIES_5;
		if(summon_zombie_list.isEmpty()){
			System.out.println("Error : Wrong Boss Stage !");
			return Optional.empty();
		}
		int pos = this.getRNG().nextInt(summon_zombie_list.size());
		return Optional.of(ZombieUtil.getZombieEntity(world, summon_zombie_list.get(pos)));
	}
	
	private int getNearPlantsCount() {
		float range = 50;
		return world.getEntitiesWithinAABB(PVZPlantEntity.class, EntityUtil.getEntityAABB(this, range, range), (plant) -> {
			return EntityUtil.checkCanEntityAttack(this, plant);
		}).size();
	}
	
	private int getNearZombiesCount() {
		float range = 30;
		return world.getEntitiesWithinAABB(PVZZombieEntity.class, EntityUtil.getEntityAABB(this, range, range), (plant) -> {
			return ! EntityUtil.checkCanEntityAttack(this, plant);
		}).size();
	}
	
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(2F, 9F);
	}
	
	private void resetShootBallCD() {
		int minCD = 600;
		int maxCD = 1000;// 30s ~ 50s
		if(this.getBossStage() > 4) {// 15s ~ 30s
			minCD -= 300;
			maxCD -= 400;
		} else if(this.getBossStage() > 2) {// 20s ~ 40S
			minCD -= 200;
		    maxCD -= 200;
		}
		this.shootBallCD = this.getRNG().nextInt(maxCD - minCD + 1) + minCD;
	}
	
	private void resetStealCD() {
		int now = this.getBossStage();
		int maxCD = (now <= 3 ? 800 : 600); // 40s 30s
		int minCD = (now <= 3 ? 500 : 400); // 25s 20s
		this.stealPlantCD = this.getRNG().nextInt(maxCD - minCD + 1) + minCD;
	}
	
	public int getBossStage() {
		float percent = this.getDefenceLife() / this.getZomBossDefenceLife();
		if(percent > 4F / 5) return 1;
		if(percent > 3F / 5) return 2;
		if(percent > 2F / 5) return 3;
		if(percent > 1F / 5) return 4;
		return 5;
	}
	
	public float getZomBossDefenceLife() {
		return 10000;
	}

	@Override
	public float getLife() {
		return 1000;
	}
	
	protected int getSummonZombieCD() {
		return 200;
	}
	
	public int getAnimShootCD() {
		return 40;
	}
	
	public int getAnimThrowCD() {
		return 30;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundRegister.ZOMBOSS_DEATH.get();
	}
	
	@Override
	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.ZOMBOSS;
	}
	
	@Override
	public boolean canZombieBeRemoved() {
		return false;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("zomboss_state")) {
			this.setZomBossState(ZomBossStates.values()[compound.getInt("zomboss_state")]);
		}
		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}
		if(compound.contains("zomboss_summon_tick")) {
			this.summonZombieTick = compound.getInt("zomboss_summon_tick");
		}
		if(compound.contains("zomboss_shoot_ball_cd")) {
			this.shootBallCD = compound.getInt("zomboss_shoot_ball_cd");
		}
		if(compound.contains("zomboss_steal_cd")) {
			this.stealPlantCD = compound.getInt("zomboss_steal_cd");
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("zomboss_state", this.getZomBossState().ordinal());
		compound.putInt("zomboss_summon_tick", this.summonZombieTick);
		compound.putInt("zomboss_shoot_ball_cd", this.shootBallCD);
		compound.putInt("zomboss_steal_cd", this.stealPlantCD);
	}
	
	public void setZomBossState(ZomBossStates state) {
		this.dataManager.set(STATES, state.ordinal());
	}
	
	public ZomBossStates getZomBossState() {
		return ZomBossStates.values()[this.dataManager.get(STATES)];
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.ZOMBOSS;
	}
	
	public static enum ZomBossStates {
		NORMAL,//yellow eyes
		FLAME,//red eyes
		ICE,//blue eyes
		STEAL,
		CAR,
	}
	
}
