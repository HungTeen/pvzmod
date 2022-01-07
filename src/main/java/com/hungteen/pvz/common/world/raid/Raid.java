package com.hungteen.pvz.common.world.raid;

import com.google.common.collect.Sets;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.events.RaidEvent;
import com.hungteen.pvz.api.raid.IPlacementComponent;
import com.hungteen.pvz.api.raid.IRaidComponent;
import com.hungteen.pvz.api.raid.ISpawnComponent;
import com.hungteen.pvz.common.advancement.trigger.RaidTrigger;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import net.minecraft.command.impl.SummonCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Raid {

	private static final ITextComponent RAID_NAME_COMPONENT = new TranslationTextComponent("event.minecraft.raid");
	private static final ITextComponent RAID_WARN = new TranslationTextComponent("raid.pvz.warn").withStyle(TextFormatting.RED);
	private final ServerBossInfo raidBar = new ServerBossInfo(RAID_NAME_COMPONENT, BossInfo.Color.RED, BossInfo.Overlay.NOTCHED_10);
	private final int id;//unique specify id.
	public final ServerWorld world;
	public final ResourceLocation resource;//res to read raid component.
	protected IRaidComponent raid;
	protected BlockPos center;//raid center block position.
	protected Status status = Status.PREPARE;
	protected int tick = 0;
	protected int stopTick = 0;
	protected int currentWave = 0;
	protected int currentSpawn = 0;
	protected Set<Entity> raiders = new HashSet<>();
	protected Set<UUID> heroes = new HashSet<>();
	
	
	public Raid(int id, ServerWorld world, ResourceLocation res, BlockPos pos) {
		this.id = id;
		this.world = world;
		this.resource = res;
		this.center = pos;
	}
	
	public Raid(ServerWorld world, CompoundNBT nbt) {
		this.world = world;
		this.id = nbt.getInt("raid_id");
		this.status = Status.values()[nbt.getInt("raid_status")];
		this.resource = new ResourceLocation(nbt.getString("raid_resource"));
		this.tick = nbt.getInt("raid_tick");
		this.stopTick = nbt.getInt("stop_tick");
		this.currentWave = nbt.getInt("current_wave");
		{// for raid center position.
			CompoundNBT tmp = nbt.getCompound("center_pos");
			this.center = new BlockPos(tmp.getInt("pos_x"), tmp.getInt("pos_y"), tmp.getInt("pos_z"));
		}
		{// for raiders entity id.
			ListNBT list = nbt.getList("raiders", 10);
			for(int i = 0; i < list.size(); ++ i) {
				final int id = list.getInt(i);
				final Entity entity = world.getEntity(id);
				if(entity != null) {
					this.raiders.add(entity);
				}
			}
		}
		{// for heroes uuid.
			ListNBT list = nbt.getList("heroes", 10);
			for(int i = 0; i < list.size(); ++ i) {
				final UUID uuid = NBTUtil.loadUUID(list.getCompound(i));
				if(uuid != null) {
					this.heroes.add(uuid);
				}
			}
		}
	}
	
	public void save(CompoundNBT nbt) {
		nbt.putInt("raid_id", this.id);
		nbt.putInt("raid_status", this.status.ordinal());
		nbt.putString("raid_resource", this.resource.toString());
		nbt.putInt("raid_tick", this.tick);
		nbt.putInt("stop_tick", this.stopTick);
		nbt.putInt("current_wave", this.currentWave);
		{// for raid center position.
			CompoundNBT tmp = new CompoundNBT();
			tmp.putInt("pos_x", this.center.getX());
		    tmp.putInt("pos_y", this.center.getY());
			tmp.putInt("pos_z", this.center.getZ());
			nbt.put("center_pos", tmp);
		}
		{// for raiders entity id.
			ListNBT list = new ListNBT();
			for(Entity entity : this.raiders) {
				list.add(IntNBT.valueOf(entity.getId()));
			}
			nbt.put("raiders", list);
		}
		{// for heroes uuid.
			ListNBT list = new ListNBT();
			for(UUID uuid : this.heroes) {
				list.add(NBTUtil.createUUID(uuid));
			}
			nbt.put("heroes", list);
		}
	}
	
	/**
	 * {@link PVZRaidData#tick()}
	 */
	public void tick() {
		/* skip tick */
		if(this.isRemoving() || this.world.players().isEmpty()) {
			return ;
		}
		/* is raid component valid */
		if(this.getRaidComponent() == null) {
			this.remove();
			PVZMod.LOGGER.warn("Raid Tick Error : Where is the raid component ?");
			return ;
		}
		/* not allow to be peaceful */
		if(this.world.getDifficulty() == Difficulty.PEACEFUL) {
			this.remove();
			return ;
		}
		this.tickBar();
		if(this.isStopping()) {
			/* has stopped */
			if(++ this.stopTick >= ConfigUtil.getRaidWaitTime()) {
				this.remove();
			}
		}
//		System.out.println(this.tick + " " + this.stopTick + " " + this.status + " " + this.center);
		if(this.isPreparing()) {
			/* prepare state */
			if(this.tick >= this.raid.getPrepareCD(this.currentWave)) {
				this.waveStart();
			}
		} else if(this.isRunning()) {
			/* running state */
			if(this.tick >= this.raid.getLastDuration(this.currentWave) 
					|| (this.raiders.isEmpty() && this.raid.isWaveFinish(this.currentWave, this.currentSpawn))) {
				this.checkNextWave();
			}
			if(this.isLoss()) {//fail to start next wave.
				this.onLoss();
				return ;
			}
			if(this.isVictory()) {
				this.onVictory();
				return ;
			}
			this.tickWave();
		} else if(this.isLoss()) {
			/* loss state */
			if(this.tick >= this.raid.getLossCD()) {
				this.remove();
			}
		} else if(this.isVictory()) {
			/* running state */
			if(this.tick >= this.raid.getWinCD()) {
				this.remove();
			}
		}
		++ this.tick;
	}
	
	/**
	 * {@link #tick()}
	 */
	protected void tickWave() {
		/* check spawn entities */
		final List<ISpawnComponent> spawns = this.raid.getSpawns(this.currentWave);
		while(this.currentSpawn < spawns.size() && this.tick >= spawns.get(this.currentSpawn).getSpawnTick()) {
			this.spawnEntities(spawns.get(this.currentSpawn ++));
		}
		
		/* update raiders list */
		Iterator<Entity> it = this.raiders.iterator();
		while(it.hasNext()) {
			Entity entity = it.next();
			if(! entity.isAlive()) {
				it.remove();
			}
		}
	}
	
	protected void spawnEntities(ISpawnComponent spawn) {
		final int count = spawn.getSpawnAmount();
		for(int i = 0; i < count; ++ i) {
			Entity entity = this.createEntity(spawn);
			if(entity != null) {
				this.raiders.add(entity);
				if(entity instanceof MobEntity) {
					// avoid despawn.
					((MobEntity) entity).setPersistenceRequired();
				}
			}
		}
	}
	
	/**
	 * copy from {@link SummonCommand}
	 */
	private Entity createEntity(ISpawnComponent spawn) {
		final IPlacementComponent placement = spawn.getPlacement() != null ? spawn.getPlacement() : this.raid.getPlacement(this.currentWave);
		final BlockPos pos = placement.getPlacePosition(this.world, this.center);
		if(! World.isInSpawnableBounds(pos)) {
			PVZMod.LOGGER.error("Invalid position when trying summon entity !");
			return null;
		}
		final CompoundNBT compound = spawn.getNBT().copy();
		compound.putString("id", spawn.getSpawnType().getRegistryName().toString());
		Entity entity = EntityType.loadEntityRecursive(compound, world, e -> {
			e.moveTo(pos, e.xRot, e.yRot);
			return e;
		});
		if(entity == null) {
			PVZMod.LOGGER.error("summon entity failed !");
			return null;
		} else {
			if(! world.tryAddFreshEntityWithPassengers(entity)) {
				PVZMod.LOGGER.error("summon entity duplicated uuid !");
				return null;
			}
		}
		return entity;
	}
	
	/**
	 * {@link #tick()}
	 */
	protected void tickBar() {
		if(this.tick % 10 == 0 && ! this.world.players().isEmpty()) {
			this.updatePlayers();
		}
		this.raidBar.setColor(this.raid.getBarColor());
		if(this.isPreparing()) {
			this.raidBar.setName(this.raid.getRaidTitle());
			this.raidBar.setPercent(this.tick * 1.0F / this.raid.getPrepareCD(this.currentWave));
		} else if(this.isRunning()) {
			this.raidBar.setName(this.raid.getRaidTitle().copy().append(" - ").append(new TranslationTextComponent("event.minecraft.raid.raiders_remaining", this.raiders.size())));
			this.raidBar.setPercent(1 - this.tick * 1.0F / this.raid.getLastDuration(this.currentWave));
		} else if(this.isVictory()) {
			this.raidBar.setName(this.raid.getRaidTitle().copy().append(" - ").append(this.raid.getWinTitle()));
			this.raidBar.setPercent(1F);
		} else if(this.isLoss()) {
			this.raidBar.setName(this.raid.getRaidTitle().copy().append(" - ").append(this.raid.getLossTitle()));
			this.raidBar.setPercent(1F);
		}
	}
	
	/**
	 * player who is alive and in suitable range can be tracked.
	 */
	private Predicate<ServerPlayerEntity> validPlayer() {
		return (player) -> {
			final int range = ConfigUtil.getRaidRange();
			return player.isAlive() && Math.abs(player.getX() - this.center.getX()) < range
					&& Math.abs(player.getY() - this.center.getY()) < range
					&& Math.abs(player.getZ() - this.center.getZ()) < range;
		};
	}
	
	/**
	 * {@link #tickBar()}
	 */
	protected void updatePlayers() {
		final Set<ServerPlayerEntity> oldPlayers = Sets.newHashSet(this.raidBar.getPlayers());
		final Set<ServerPlayerEntity> newPlayers = Sets.newHashSet(this.world.getPlayers(this.validPlayer()));
		
		/* add new join players */
		newPlayers.forEach(p -> {
			if(! oldPlayers.contains(p)) {
				this.raidBar.addPlayer(p);
			}
		});
		
		/* remove offline players */
		oldPlayers.forEach(p -> {
			if(! newPlayers.contains(p)) {
				
				this.raidBar.removePlayer(p);
			}
		});
		
		/* add heroes */
		this.raidBar.getPlayers().forEach(p -> {
			if(! this.heroes.contains(p.getUUID())) {
				this.heroes.add(p.getUUID());
			}
		});
		
		if(this.raidBar.getPlayers().isEmpty()){
			if(! this.isStopping()) {
				++ this.stopTick;
				this.heroes.forEach(uuid -> {
					PlayerEntity player = this.world.getPlayerByUUID(uuid);
					if(player != null) {
						PlayerUtil.sendMsgTo(player, RAID_WARN);
					}
				});
			}
		} else {
			this.stopTick = 0;
		}
	}
	
	/**
	 * run when prepare time is finished.
	 */
	protected void waveStart() {
		this.tick = 0;
		this.status = Status.RUNNING;
		this.getPlayers().forEach(p -> PlayerUtil.playClientSound(p, this.raid.getPrepareSound()));
	}
	
	/**
	 * check can start next wave or not.
	 */
	public boolean canNextWave() {
		return this.raiders.isEmpty();
	}
	
	/**
	 * {@link #tick()}
	 */
	protected void checkNextWave() {
		this.tick = 0;
		if(this.canNextWave()) {
			this.currentSpawn = 0;
			if(++ this.currentWave >= this.raid.getMaxWaveCount()) {
				this.status = Status.VICTORY;
			} else {
				this.status = Status.PREPARE;
			}
		} else {
			this.status = Status.LOSS;
		}
	}
	
	/**
	 * run when raid is not defeated.
	 */
	protected void onLoss() {
		this.tick = 0;
		this.getPlayers().forEach(p -> PlayerUtil.playClientSound(p, this.raid.getLossSound()));
		MinecraftForge.EVENT_BUS.post(new RaidEvent.RaidLossEvent(this));
	}

	/**
	 * run when raid is defeated.
	 */
	protected void onVictory() {
		this.tick = 0;
		this.getPlayers().forEach(p -> {
			PlayerUtil.playClientSound(p, this.raid.getWinSound());
			RaidTrigger.INSTANCE.trigger(p, this.resource.toString());
		});
		if(! MinecraftForge.EVENT_BUS.post(new RaidEvent.RaidWinEvent(this))) {
			this.getPlayers().forEach(p -> {
				this.raid.getRewards().forEach(r -> r.reward(p));
			});
			this.raid.getRewards().forEach(r -> r.rewardGlobally(world));
		}
	}
	
	public void remove() {
		this.status = Status.REMOVING;
		this.raidBar.removeAllPlayers();
		this.raiders.forEach(e -> e.remove());
	}
	
	public int getId() {
		return this.id;
	}
	
	public BlockPos getCenter() {
		return this.center;
	}
	
	public boolean isRaider(Entity raider) {
		return this.raiders.contains(raider);
	}
	
	public boolean isStopping() {
		return this.stopTick > 0;
	}
	
	public boolean isPreparing() {
		return this.status == Status.PREPARE;
	}
	
	public boolean isRunning() {
		return this.status == Status.RUNNING;
	}
	
	public boolean isRemoving() {
		return this.status == Status.REMOVING;
	}
	
	public boolean isLoss() {
		return this.status == Status.LOSS;
	}
	
	public boolean isVictory() {
		return this.status == Status.VICTORY;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * get raid component by resource.
	 */
	public IRaidComponent getRaidComponent() {
		return this.raid != null ? this.raid : (this.raid = RaidManager.getRaidComponent(this.resource));
	}
	
	/**
	 * get tracked players by raid bar.
	 */
	public List<ServerPlayerEntity> getPlayers(){
		return this.raidBar.getPlayers().stream().collect(Collectors.toList());
	}
	
	public boolean hasTag(String tag) {
		return this.raid.hasTag(tag);
	}
	
	public List<String> getAuthors(){
		return this.raid.getAuthors();
	}
	
	public static enum Status {
		  PREPARE,
	      RUNNING,
	      VICTORY,
	      LOSS,
	      REMOVING;
	}
	
}
