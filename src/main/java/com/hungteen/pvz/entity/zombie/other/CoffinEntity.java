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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public class CoffinEntity extends UnderGroundZombieEntity {

	private static final DataParameter<Integer> GUARD_STATE = EntityDataManager.createKey(CoffinEntity.class, DataSerializers.VARINT);
	private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(),
			BossInfo.Color.WHITE, BossInfo.Overlay.NOTCHED_6)).setDarkenSky(true);

	public CoffinEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.particleNum = 3;
		this.canBeButter = false;
		this.canBeCold = false;
		this.canBeCharm = false;
		this.canBeMini = false;
		this.canBeFrozen = false;
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(GUARD_STATE, 0);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LITTLE_LOW);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.SLOW);
	}

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if (! world.isRemote) {
			EntityUtil.playSound(this, SoundRegister.DIRT_RISE.get());
			for (Entity target : EntityUtil.getEntityAttackableTarget(this,
					EntityUtil.getEntityAABB(this, 50, 50))) {
				ZombieHandEntity hand = EntityRegister.ZOMBIE_HAND.get().create(world);
				hand.setOwner(this);
				EntityUtil.onEntitySpawn(world, hand, target.getPosition());
			}
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
					MournerZombieEntity zombie = EntityRegister.MOURNER_ZOMBIE.get().create(world);
					zombie.setRightShake((i & 1) == 0);
					EntityUtil.onMobEntitySpawn(world, zombie, this.getAttackTarget() == null ? this.getPosition() : this.getAttackTarget().getPosition());
				}
			}
		}
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (this.collidedHorizontally && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
            boolean flag = false;
            AxisAlignedBB axisalignedbb = this.getBoundingBox().grow(0.2D);

            for(BlockPos blockpos : BlockPos.getAllInBoxMutable(MathHelper.floor(axisalignedbb.minX), MathHelper.floor(axisalignedbb.minY), MathHelper.floor(axisalignedbb.minZ), MathHelper.floor(axisalignedbb.maxX), MathHelper.floor(axisalignedbb.maxY), MathHelper.floor(axisalignedbb.maxZ))) {
               BlockState blockstate = this.world.getBlockState(blockpos);
               Block block = blockstate.getBlock();
               if (block instanceof LeavesBlock) {
                  flag = this.world.destroyBlock(blockpos, true, this) || flag;
               }
            }

            if (!flag && this.onGround) {
               this.jump();
            }
         }
	}
	
	@Override
	protected void onDeathUpdate() {
		this.remove();
		NobleZombieEntity boss = EntityRegister.NOBLE_ZOMBIE.get().create(world);
		EntityUtil.onMobEntitySpawn(world, boss, getPosition());
	}

	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		return EntityUtil.checkCanEntityAttack(this, target);
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		super.collideWithEntity(entityIn);
		if (entityIn instanceof PVZPlantEntity) {
			PVZPlantEntity plant = (PVZPlantEntity) entityIn;
			plant.attackEntityFrom(PVZDamageSource.causeCrushDamage(this, this), plant.getMaxHealth());
		}
	}
	
	@Override
	protected double getCollideWidthOffset() {
		return 0.8;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(2f, 2f);
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
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("guard_state")) {
			this.setGuardState(compound.getInt("guard_state"));
		}
		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("guard_state", this.getGuardState());
	}

	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	public int getGuardState() {
		return this.dataManager.get(GUARD_STATE);
	}
	
	public void setGuardState(int state) {
		this.dataManager.set(GUARD_STATE, state);
	}
	
	public boolean isGuardGone(int id) {
		return ((this.getGuardState() >> id) & 1) == 1;
	}
	
	public void setGuardStateById(int id, int is) {
		int state = this.getGuardState();
		if((is ^ (state >> id) & 1) == 1) {
			state ^= (1 << id);
		}
		this.dataManager.set(GUARD_STATE, state);
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
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.COFFIN;
	}

}
