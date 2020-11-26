package com.hungteen.pvz.entity.zombie.other;

import java.util.List;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.misc.ZombieHandEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public class NobleZombieEntity extends PVZZombieEntity {

	private static final DataParameter<Integer> TP_TICK = EntityDataManager.createKey(NobleZombieEntity.class,
			DataSerializers.VARINT);
	private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(),
			BossInfo.Color.WHITE, BossInfo.Overlay.NOTCHED_6)).setDarkenSky(true);
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
		this.summonTick = this.getRNG().nextInt(this.maxSummonTick - this.minSummonTick) + this.minSummonTick;
		this.setTpTick(-this.getRNG().nextInt(this.maxTpCD - this.minTpCD + 1) - this.minTpCD);
		this.experienceValue = 1000;
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(TP_TICK, 0);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.VERY_SLOW);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LITTLE_HIGH);
	}

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(!world.isRemote) {
			for (Entity target : EntityUtil.getEntityAttackableTarget(this,
					EntityUtil.getEntityAABB(this, 50, 50))) {
				if(this.getRNG().nextInt(4) == 0) {
					ZombieHandEntity hand = EntityRegister.ZOMBIE_HAND.get().create(world);
				    hand.setOwner(this);
				    EntityUtil.onMobEntitySpawn(world, hand, target.getPosition());
				}
			}
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
		if (!world.isRemote) {
			// summon MournerZombie
			if (this.summonTick > 0) {
				--this.summonTick;
			} else if (this.summonTick == 0) {
				this.summonTick = this.getRNG().nextInt(this.maxSummonTick - this.minSummonTick) + this.minSummonTick;
				this.checkAndSummonMournerZombie();
			}
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			} else {
				this.checkAndSleepPlant();
			}
		} else {
			if(this.getAttackTime() < 20) {
				this.world.addParticle(ParticleTypes.NOTE, getPosX(), getPosY() + 2f, getPosZ(), 0, 0, 0);
			}
		}
		this.checkAndHeal();
		if (this.getTpTick() < 0) {
			this.setTpTick(this.getTpTick() + 1);
		} else if (this.getTpTick() == 0) {
			if (this.getRNG().nextInt(5) == 0) {
				this.setTpTick(1);
			}
		} else {
			if (this.getTpTick() >= this.getTpCD()) {
				this.checkAndTeleport();
			}
			if(world.isRemote) {
				for(int i = 0; i < 9; ++ i) {
					world.addParticle(ParticleTypes.PORTAL, this.getPosX() + (this.getRNG().nextInt(5) - 2), this.getPosY() + (this.getRNG().nextInt(3)), this.getPosZ() + (this.getRNG().nextInt(5) - 2), 0, 0, 0);
				}
			}
			this.setTpTick(this.getTpTick() + 1);
		}
	}

	/**
	 * Skill 1 : Teleport and make explosion
	 */
	private void checkAndTeleport() {
		if (this.getAttackTarget() == null) {
			return;
		}
		BlockPos teleportPos = this.getAttackTarget().getPosition();
		this.setTpTick(-this.getRNG().nextInt(this.maxTpCD - this.minTpCD + 1) - this.minTpCD);
		float range = this.getExpRange();
		this.teleportTo(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
		for (Entity target : EntityUtil.getEntityAttackableTarget(this, EntityUtil.getEntityAABB(this, range, range))) {
			if (target instanceof PVZPlantEntity) {
				target.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this),
						((LivingEntity) target).getMaxHealth());
			}
		}
	}

	/**
	 * Skill 2 : Summon MournerZombie.
	 */
	private void checkAndSummonMournerZombie() {
		int num = this.world.getEntitiesWithinAABB(EntityRegister.MOURNER_ZOMBIE.get(),
				EntityUtil.getEntityAABB(this, 50, 50), (zombie) -> {
					return !zombie.isCharmed();
				}).size();
		if (num >= this.maxSummonedCnt) {// zombies in range are too many.
			this.checkAndSummonZombieHand();
			return;
		}
		int max = Math.min(this.maxSummonedCnt - num, 3);
		int min = 1;
		int cnt = this.getRNG().nextInt(max - min + 1) + min;
		for (int i = 0; i < cnt; ++i) {
			MournerZombieEntity zombie = EntityRegister.MOURNER_ZOMBIE.get().create(world);
			BlockPos pos = this.getAttackTarget() == null ? this.getPosition() : this.getAttackTarget().getPosition();
			for (int chance = 0; chance < 30; ++chance) {
				int x = this.getPosition().getX()
						+ MathHelper.nextInt(this.rand, 5, 20) * MathHelper.nextInt(this.rand, -1, 1);
				int y = this.getPosition().getY() + MathHelper.nextInt(this.rand, 0, 10);
				int z = this.getPosition().getZ()
						+ MathHelper.nextInt(this.rand, 5, 20) * MathHelper.nextInt(this.rand, -1, 1);
				BlockPos tmp = new BlockPos(x, y, z);
				zombie.setPosition(x, y, z);
				if (world.getBlockState(tmp).isTopSolid(world, tmp, zombie) && world.checkNoEntityCollision(zombie)
						&& world.hasNoCollisions(zombie)) {
					pos = tmp;
					break;
				}
			}
			EntityUtil.onMobEntitySpawn(world, zombie, pos);
		}
	}
	
	/**
	 * Skill 3 : Sleep plants in a specific area.
	 */
	private void checkAndSleepPlant() {
		List<PVZPlantEntity> list = this.world.getEntitiesWithinAABB(PVZPlantEntity.class, EntityUtil.getEntityAABB(this, 50, 50), (plant)->{
			return !plant.isCharmed();
		});
		int len = list.size();
		if(len == 0) {
			this.setAttackTime(this.getRNG().nextInt(this.maxSleepAttackCD - this.minSleepAttackCD + 1) + this.minSleepAttackCD);
			return ;
		}
		int pos = this.getRNG().nextInt(len);
		PVZPlantEntity plant = list.get(pos);
		final float range = 1.5f;
		for(PVZPlantEntity target : this.world.getEntitiesWithinAABB(PVZPlantEntity.class, EntityUtil.getEntityAABB(plant, range, range), (p)->{
			return !p.isCharmed();
		})) {
			target.setSleepTime(2400);
		}
		this.setAttackTime(this.getRNG().nextInt(this.maxSleepAttackCD - this.minSleepAttackCD + 1) + this.minSleepAttackCD);
	}
	
	/**
	 * Skill 4 : Summon Zombie's Hands.
	 */
	private void checkAndSummonZombieHand() {
		int cnt = this.getHandSummonNum();
		List<LivingEntity> list = EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, 50, 50));
		for(int i = 0; i < list.size(); ++ i) {
			if(i + cnt >= list.size() || this.getRNG().nextInt(5) == 0) {
				ZombieHandEntity hand = EntityRegister.ZOMBIE_HAND.get().create(world);
				hand.setOwner(this);
				EntityUtil.onMobEntitySpawn(world, hand, list.get(i).getPosition());
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
		if(!world.isRemote) {
			if(this.getAttackTarget() == null) {
			    this.heal(0.3f);
			}else if(percent < 1f / 2) {
				this.heal(0.2f);
			}else if(percent < 1f / 6) {
				this.heal(0.5f);
			}
		}
	}
	
	private boolean teleportTo(double x, double y, double z) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(x, y, z);
		while (blockpos$mutable.getY() > 0
				&& !this.world.getBlockState(blockpos$mutable).getMaterial().blocksMovement()) {
			blockpos$mutable.move(Direction.DOWN);
		}
		BlockState blockstate = this.world.getBlockState(blockpos$mutable);
		boolean flag = blockstate.getMaterial().blocksMovement();
		if (flag) {
			boolean flag2 = this.attemptTeleport(x, y, z, true);
			if (flag2) {
				this.world.playSound((PlayerEntity) null, this.prevPosX, this.prevPosY, this.prevPosZ,
						SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
				this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
			}
			return flag2;
		} else {
			return false;
		}
	}
	
	@Override
	protected void onDeathUpdate() {
		super.onDeathUpdate();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(amount >= 10) {
			amount /= 3f;
		}else {
			amount /= 2f;
		}
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public float getLife() {
		return 1000;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.9f, 1.9f);
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
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.summonTick = compound.getInt("summon_zombie_tick");
		this.setTpTick(compound.getInt("zombie_tp_tick"));
		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("summon_zombie_tick", this.summonTick);
		compound.putInt("zombie_tp_tick", this.getTpTick());
	}

	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}
	
	public int getTpTick() {
		return this.dataManager.get(TP_TICK);
	}

	public void setTpTick(int tick) {
		this.dataManager.set(TP_TICK, tick);
	}
	
	@Override
	public boolean canBeCold() {
		return false;
	}

	@Override
	public boolean canBeInvis() {
		return false;
	}

	@Override
	public boolean canBeSmall() {
		return false;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.NOBLE_ZOMBIE;
	}
	
	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NOBLE_ZOMBIE;
	}

}
