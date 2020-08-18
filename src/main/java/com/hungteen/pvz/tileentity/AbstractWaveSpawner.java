package com.hungteen.pvz.tileentity;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;

public abstract class AbstractWaveSpawner extends TileEntity implements ITickableTileEntity {

	protected static final TranslationTextComponent RAID = new TranslationTextComponent("event.pvz.raid");
	protected static final TranslationTextComponent VICTORY = new TranslationTextComponent("event.pvz.raid.victory");
	protected static final TranslationTextComponent FAIL = new TranslationTextComponent("event.pvz.raid.fail");
	protected static final ITextComponent RAID_VICTORY = RAID.shallowCopy().appendText(" - ").appendSibling(VICTORY);
	protected static final ITextComponent RAID_DEFEAT = RAID.shallowCopy().appendText(" - ").appendSibling(FAIL);
	private final Map<Integer, Set<PVZZombieEntity>> zombies = Maps.newHashMap();
	protected boolean active;
	protected int ticksActive;
	protected boolean waveStarted;// wave start
	protected int currentWave;
	protected float totalHealth;
	protected final ServerBossInfo bossInfo = new ServerBossInfo(RAID, BossInfo.Color.RED, BossInfo.Overlay.NOTCHED_10);
	protected int preRaidTicks;
	protected AbstractWaveSpawner.Status status;
	protected int celebrationTicks;
	protected Optional<UUID> attackerUUID;
	protected final int maxWaveNum;
	protected final float validRange;
	protected final int maxRaidTick;
	protected final int maxPreRaidTime;

	public AbstractWaveSpawner(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		this.setAttackerUUID(null);
		this.bossInfo.setVisible(false);
		this.maxWaveNum = 10;
		this.validRange = 50;
		this.maxRaidTick = 48000;
		this.maxPreRaidTime = 600;
	}

	public boolean checkCanStart() {
		return !this.active;
	}

	public void startRaid(PlayerEntity player) {
		this.active = true;
		this.ticksActive=0;
		this.waveStarted=false;
		this.currentWave=0;
		this.totalHealth=0;
		this.preRaidTicks=0;
		this.status=AbstractWaveSpawner.Status.ONGOING;
		this.celebrationTicks=0;
		this.setAttackerUUID(player.getUniqueID());
		this.markDirty();
	}

	@Override
	public void tick() {
		if (!this.active) {
			return;
		}
		if (this.status == AbstractWaveSpawner.Status.ONGOING) {
			if (this.world.getDifficulty() == Difficulty.PEACEFUL) {
				this.chanllengeFail();
				return;
			}
			this.bossInfo.setVisible(true);
			++this.ticksActive;
			if (this.ticksActive >= this.maxRaidTick) {//TLE cause fail
				this.chanllengeFail();
				return;
			}

			int cnt = this.getZombiesCount();
			if (cnt == 0 && !this.isFinalWave()) {//pre raid 
				if (this.preRaidTicks <= 0) {// start next wave
					this.preRaidTicks = this.maxPreRaidTime;
					this.bossInfo.setName(RAID);
					return;
				} else {
					spawnExtraZombie();
					if(this.preRaidTicks==this.maxPreRaidTime||this.preRaidTicks%20==0) {
						this.updateBossInfoVisibility();
					}
					--this.preRaidTicks;
				}
				this.bossInfo.setPercent(MathHelper.clamp((float)(this.maxPreRaidTime - this.preRaidTicks) / this.maxPreRaidTime, 0.0F, 1.0F));
			}
			if(this.ticksActive%20==0) {
				this.updateBossInfoVisibility();
//				this.
			}
		}
	}

	/**
	 * fail the challenge
	 */
	protected void chanllengeFail() {
		this.active = false;
		this.bossInfo.removeAllPlayers();
		this.status = AbstractWaveSpawner.Status.STOPPED;
		this.setAttackerUUID(null);
		for (PlayerEntity player : getPlayerInRange()) {
			if (!this.world.isRemote) {
				player.sendMessage(FAIL.applyTextStyle(TextFormatting.RED));
			}
		}
	}

	private int getZombiesCount() {
		return this.zombies.values().stream().mapToInt(Set::size).sum();
	}

	private boolean isFinalWave() {
		return this.currentWave == this.maxWaveNum;
	}

	private void updateRaiders() {
		Iterator<Set<PVZZombieEntity>> iterator = this.zombies.values().iterator();
		while (iterator.hasNext()) {
			for (PVZZombieEntity zombie : iterator.next()) {
				if (zombie.removed) {
//					this.leaveRaid(zombie, true);
				}
			}
		}
	}

//	public void leaveRaid(PVZZombieEntity zombie, boolean p_221322_2_) {
//		Set<PVZZombieEntity> set = this.zombies.get(zombie.func_213642_em());
//		if (set != null) {
//			boolean flag = set.remove(zombie);
//			if (flag) {
//				if (p_221322_2_) {
//					this.totalHealth -= zombie.getHealth();
//				}
//				zombie.setRaid((Raid) null);
//				this.updateBarPercentage();
//				this.markDirty();
//			}
//		}
//	}

	private void updateBossInfoVisibility() {
		Set<PlayerEntity> set = Sets.newHashSet(this.bossInfo.getPlayers());
		List<PlayerEntity> list = getPlayerInRange();
		for (PlayerEntity player : list) {
			if (!set.contains(player) && player instanceof ServerPlayerEntity) {
				this.bossInfo.addPlayer((ServerPlayerEntity) player);
			}
		}
		for (PlayerEntity player : set) {
			if (!list.contains(player) && player instanceof ServerPlayerEntity) {
				this.bossInfo.removePlayer((ServerPlayerEntity) player);
			}
		}
	}

	private List<PlayerEntity> getPlayerInRange() {
		return world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(
				pos.add(-validRange, -validRange, -validRange), pos.add(validRange, validRange, validRange)));
	}

	protected abstract void spawnExtraZombie();

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.active = compound.getBoolean("active");
		this.ticksActive = compound.getInt("tick_active");
		this.waveStarted = compound.getBoolean("is_wave_started");
		this.currentWave = compound.getInt("current_wave");
		this.totalHealth = compound.getFloat("total_health");
		this.preRaidTicks = compound.getInt("pre_raid_ticks");
		this.status = AbstractWaveSpawner.Status.getByName(compound.getString("status"));
		this.celebrationTicks = compound.getInt("celebration_ticks");
		String s;
		if (compound.contains("AttackerUUID", 8)) {
			s = compound.getString("AttackerUUID");
		} else {
			String s1 = compound.getString("Attacker");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.world.getServer(), s1);
		}
		if (!s.isEmpty()) {
			try {
				this.setAttackerUUID(UUID.fromString(s));
			} catch (Throwable var4) {
			}
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putBoolean("active", this.active);
		compound.putInt("tick_active", this.ticksActive);
		compound.putBoolean("is_wave_started", this.waveStarted);
		compound.putInt("current_wave", this.currentWave);
		compound.putFloat("total_health", this.totalHealth);
		compound.putInt("pre_raid_ticks", this.preRaidTicks);
		compound.putString("status", this.status.getName());
		compound.putInt("celebration_ticks", this.celebrationTicks);
		if (this.getAttackerUUID() == null) {
			compound.putString("OwnerUUID", "");
		} else {
			compound.putString("OwnerUUID", this.getAttackerUUID().toString());
		}
		return super.write(compound);
	}

	@Nullable
	public UUID getAttackerUUID() {
		return this.attackerUUID.orElse((UUID) null);
	}

	public void setAttackerUUID(UUID uuid) {
		this.attackerUUID = Optional.ofNullable(uuid);
		this.markDirty();
	}

	static enum Status {
		ONGOING, VICTORY, LOSS, STOPPED;

		private static final AbstractWaveSpawner.Status[] VALUES = values();

		private static AbstractWaveSpawner.Status getByName(String name) {
			for (AbstractWaveSpawner.Status raid$status : VALUES) {
				if (name.equalsIgnoreCase(raid$status.name())) {
					return raid$status;
				}
			}

			return ONGOING;
		}

		public String getName() {
			return this.name().toLowerCase(Locale.ROOT);
		}
	}
}
