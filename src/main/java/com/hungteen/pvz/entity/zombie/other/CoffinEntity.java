package com.hungteen.pvz.entity.zombie.other;

import java.util.Random;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public class CoffinEntity extends PVZZombieEntity {

	private static final DataParameter<Integer> SPAWN_TICK = EntityDataManager.createKey(CoffinEntity.class, DataSerializers.VARINT);
	private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.NOTCHED_6)).setDarkenSky(true);
	public static final int SPAWN_TIME = 50;
	
	public CoffinEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(SPAWN_TICK, 0);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
	}

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if (!world.isRemote) {
			this.playSound(SoundRegister.DIRT_RISE.get(), 1f, 1f);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public void tick() {
		super.tick();
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (this.getSpawnTick() < SPAWN_TIME) {
			this.setSpawnTick(this.getSpawnTick() + 1);
			if (world.isRemote) {
				for (int i = 0; i < 3; i++) {
					Random rand = this.getRNG();
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX() + 0.5d, this.getPosY(),
							this.getPosZ() + 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX() + 0.5d, this.getPosY(),
							this.getPosZ() - 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX() - 0.5d, this.getPosY(),
							this.getPosZ() + 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX() - 0.5d, this.getPosY(),
							this.getPosZ() - 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
				}
			}
		}
	}
	 
	@Override
	protected boolean shouldCollideWithEntity(Entity target) {
		if(target instanceof PVZPlantEntity) {
			if(target instanceof SquashEntity) {
			    return false;
		    }
			return EntityUtil.checkCanEntityAttack(this, target);
		}
		return EntityUtil.checkCanEntityAttack(this, target);
	}
	
	@Override
	protected void collideWithEntity(Entity entityIn) {
		super.collideWithEntity(entityIn);
		if(entityIn instanceof PVZPlantEntity) {
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
		return EntitySize.flexible(3f, 2.8f);
	}

	@Override
	protected Type getSpawnType() {
		return Type.NORMAL;
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setSpawnTick(compound.getInt("spawn_tick_time"));
		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("spawn_tick_time", this.getSpawnTick());
	}

	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}
	
	public int getSpawnTick() {
		return this.dataManager.get(SPAWN_TICK);
	}
	
	public void setSpawnTick(int tick) {
		this.dataManager.set(SPAWN_TICK, tick);
	}

	@Override
	public float getLife() {
		return 1000;
	}

	@Override
	public boolean canBeButter() {
		return false;
	}

	@Override
	public boolean canBeCold() {
		return false;
	}

	@Override
	public boolean canBeFrozen() {
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
