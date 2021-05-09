package com.hungteen.pvz.entity.zombie.other;

import com.hungteen.pvz.entity.misc.ZombieHandEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.base.UnderGroundZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public class CoffinEntity extends UnderGroundZombieEntity {

	private static final DataParameter<Integer> GUARD_STATE = EntityDataManager.defineId(CoffinEntity.class, DataSerializers.INT);
	private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(),
			BossInfo.Color.WHITE, BossInfo.Overlay.NOTCHED_6)).setDarkenScreen(true);

	public CoffinEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.particleNum = 3;
		this.canBeButter = false;
		this.canBeCold = false;
		this.canBeCharm = false;
		this.canBeMini = false;
		this.canBeFrozen = false;
		this.canBeStealByBungee = false;
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(GUARD_STATE, 0);
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LITTLE_LOW);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.SLOW);
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if (! level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.DIRT_RISE.get());
			for (Entity target : EntityUtil.getEntityAttackableTarget(this,
					EntityUtil.getEntityAABB(this, 50, 50))) {
				ZombieHandEntity hand = EntityRegister.ZOMBIE_HAND.get().create(level);
				hand.setOwner(this);
				EntityUtil.onEntitySpawn(level, hand, target.blockPosition());
			}
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public void tick() {
		super.tick();
		float percent = this.getHealth() / this.getMaxHealth();
		this.bossInfo.setPercent(percent);
		if(this.isAlive()) {
			for(int i = 0; i < 4; i ++) {
				if(this.isGuardGone(i)) {
					continue;
				}
				if(percent < (5 - i) * 1.0f / 6f) {
					this.setGuardStateById(i, 1);
					MournerZombieEntity zombie = EntityRegister.MOURNER_ZOMBIE.get().create(level);
					zombie.setRightShake((i & 1) == 0);
					EntityUtil.onMobEntitySpawn(level, zombie, this.getTarget() == null ? this.blockPosition() : this.getTarget().blockPosition());
				}
			}
		}
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.horizontalCollision && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
            boolean flag = false;
            AxisAlignedBB axisalignedbb = this.getBoundingBox().inflate(0.2D);

            for(BlockPos blockpos : BlockPos.betweenClosed(MathHelper.floor(axisalignedbb.minX), MathHelper.floor(axisalignedbb.minY), MathHelper.floor(axisalignedbb.minZ), MathHelper.floor(axisalignedbb.maxX), MathHelper.floor(axisalignedbb.maxY), MathHelper.floor(axisalignedbb.maxZ))) {
               BlockState blockstate = this.level.getBlockState(blockpos);
               Block block = blockstate.getBlock();
               if (block instanceof LeavesBlock) {
                  flag = this.level.destroyBlock(blockpos, true, this) || flag;
               }
            }

            if (!flag && this.onGround) {
               this.jumpFromGround();
            }
         }
	}
	
	@Override
	protected void tickDeath() {
		this.remove();
		NobleZombieEntity boss = EntityRegister.NOBLE_ZOMBIE.get().create(level);
		EntityUtil.onMobEntitySpawn(level, boss, blockPosition());
	}

	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		return EntityUtil.checkCanEntityAttack(this, target);
	}

	@Override
	protected void doPush(Entity entityIn) {
		super.doPush(entityIn);
		if (entityIn instanceof PVZPlantEntity) {
			PVZPlantEntity plant = (PVZPlantEntity) entityIn;
			plant.hurt(PVZDamageSource.causeCrushDamage(this, this), plant.getMaxHealth());
		}
	}
	
	@Override
	protected double getCollideWidthOffset() {
		return 0.8;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(2f, 2f);
	}

	@Override
	protected Type getSpawnType() {
		return Type.NORMAL;
	}
	
	@Override
	public int getSpawnTime() {
		return 40;
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("guard_state")) {
			this.setGuardState(compound.getInt("guard_state"));
		}
		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("guard_state", this.getGuardState());
	}

	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	public int getGuardState() {
		return this.entityData.get(GUARD_STATE);
	}
	
	public void setGuardState(int state) {
		this.entityData.set(GUARD_STATE, state);
	}
	
	public boolean isGuardGone(int id) {
		return ((this.getGuardState() >> id) & 1) == 1;
	}
	
	public void setGuardStateById(int id, int is) {
		int state = this.getGuardState();
		if((is ^ (state >> id) & 1) == 1) {
			state ^= (1 << id);
		}
		this.entityData.set(GUARD_STATE, state);
	}

	@Override
	public float getLife() {
		return 1000;
	}

	@Override
	public boolean canZombieBeRemoved() {
		return false;
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

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.COFFIN;
	}

}
