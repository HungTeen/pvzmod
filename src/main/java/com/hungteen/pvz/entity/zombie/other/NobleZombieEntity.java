package com.hungteen.pvz.entity.zombie.other;

import com.hungteen.pvz.entity.misc.ZombieHandEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public class NobleZombieEntity extends PVZZombieEntity{

	private static final DataParameter<Integer> SPAWN_TICK = EntityDataManager.createKey(CoffinEntity.class,
			DataSerializers.VARINT);
	private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(),
			BossInfo.Color.WHITE, BossInfo.Overlay.NOTCHED_6)).setDarkenSky(true);
	public static final int SPAWN_TIME = 100;
	
	public NobleZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(SPAWN_TICK, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		float percent = this.getHealth() / this.getMaxHealth();
		this.bossInfo.setPercent(percent);
		if(!world.isRemote && this.getSpawnTick() < SPAWN_TIME) {
			if(this.getSpawnTick() == 0) {
				for(Entity target : EntityUtil.getEntityAttackableTarget(this, EntityUtil.getEntityAABB(this, 50, 50))) {
					ZombieHandEntity hand = EntityRegister.ZOMBIE_HAND.get().create(world);
					hand.setOwner(this);
					EntityUtil.onMobEntitySpawn(world, hand, target.getPosition());
				}
			}
			this.setSpawnTick(this.getSpawnTick() + 1);
		}
	}
	
	@Override
	public float getLife() {
		return 1000;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.9f, 1.9f);
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
	public boolean isNonBoss() {
		return false;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NOBLE_ZOMBIE;
	}
	
}
