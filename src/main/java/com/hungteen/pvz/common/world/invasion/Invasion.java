package com.hungteen.pvz.common.world.invasion;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.misc.PVZPacketTypes;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.OtherStatsPacket;
import com.hungteen.pvz.utils.*;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.utils.others.WeightList;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.hungteen.pvz.common.world.invasion.InvasionManager.suitableInvasionPos;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-01-23 15:13
 **/
public class Invasion {

    private final World world;
    private final PlayerEntity player;
    //what will spawn in this invasion. (update)
    private final WeightList<SpawnType> spawnList = new WeightList<>();
    //the set of every spawn type, used to check invader. (update)
    private final Set<EntityType<?>> spawnTypes = new HashSet<>();
    //current invasion events resources (for storage).
    private final Set<ResourceLocation> activeResources = new HashSet<>();
    //current spawn resource (for storage).
    private ResourceLocation spawnResource;
    private InvasionType spawnInvasion;
    private Set<InvasionType> activeInvasions;
    private int invasionLvl;
    private boolean isRunning = false;
    private static int tick = 0;
    private static int spawnCD = 0;
    private int currentCount = 0;
    /* wave */
    private int[] waveTime = new int[InvasionManager.MAX_WAVE_NUM];
    private boolean[] waveTriggered = new boolean[InvasionManager.MAX_WAVE_NUM];
    private int currentWave = 0;
    private int totalWaveCount = 0;
    /* mission */
    private int[] killQueue = new int[MissionManager.KILL_IN_SECOND];
    public int killInSecond = 0;
    public int killPos = 0;

    public Invasion(PlayerEntity player) {
        this.player = player;
        this.world = player.level;
        this.invasionLvl = PlayerUtil.getResource(player, Resources.TREE_LVL);
        for (int i = 0; i < InvasionManager.MAX_WAVE_NUM; ++i) {
            this.waveTime[i] = 0;
            this.waveTriggered[i] = false;
        }
        for (int i = 0; i < MissionManager.KILL_IN_SECOND; ++i) {
            killQueue[i] = 0;
        }
    }

    /**
     * {@link InvasionManager#tick(TickEvent.WorldTickEvent)}
     */
    public void tick() {
        // wait for data pack or peaceful mode.
        if (this.isRunning() && this.getSpawnInvasion() != null && this.world.getDifficulty() != Difficulty.PEACEFUL) {
            world.getProfiler().push("Invasion Spawn Tick");
            if (++tick >= this.getSpawnCD() && !this.getSpawnList().isEmpty()) {
                this.spawnInvaders();
                tick = 0;
            }
            world.getProfiler().pop();

            world.getProfiler().push("Invasion Wave Tick");
            final int dayTime = (int) world.getDayTime();
            if (this.currentWave < this.getTotalWaveCount() && dayTime == this.getWaveTime(this.currentWave)) {
                this.setWaveTriggered(this.currentWave++, this.spawnWaveInvaders());//wave spawn.
            }
            world.getProfiler().pop();

            world.getProfiler().push("Invasion Mission Tick");
            MissionManager.tickMission(this);
            world.getProfiler().pop();
        }
    }

    public void spawnInvaders() {
        final int range = PVZConfig.COMMON_CONFIG.InvasionSettings.MaxSpawnRange.get();
        final int maxCount = PVZConfig.COMMON_CONFIG.InvasionSettings.MaxSpawnEachPlayer.get();
        final int current = EntityUtil
                .getPredicateEntities(player, EntityUtil.getEntityAABB(player, range, range), MobEntity.class, e -> {
                    return isInvasionEntity(e.getType());
                }).size();
        if (current < maxCount) {
            for (int i = 0; i < this.getSpawnCount(); ++i) {
                final SpawnType type = getSpawnList().getRandomItem(world.random).get();
                final BlockPos pos = WorldUtil.findRandomSpawnPos(world, player.blockPosition(), 10, 8, range,
                        b -> InvasionManager.suitableInvasionPos(world, b) && type.checkPos(world, b));

                if (pos != null) {
                    EntityUtil.onEntitySpawn(world, type.getSpawnType().create(world), pos);
                }
            }
        }
        this.currentCount = Math.min(current, maxCount);
    }

    public boolean spawnWaveInvaders() {
        //can only spawn in overworld, and peaceful, and wave enable.
        if (!PlayerUtil.isPlayerSurvival(player) || !world.dimension().equals(World.OVERWORLD) || world.getDifficulty() == Difficulty.PEACEFUL || !ConfigUtil.enableHugeWave()) {
            return false;
        }
        if (getSpawnList().isEmpty()) {
            PVZMod.LOGGER.warn("WaveManager : Why cause spawn list empty ?");
            return false;
        }
        int cnt = this.getSpawnCount(this.currentWave);
        boolean spawned = false;
        while (cnt >= 15) {//split whole zombie to serveral zombie teams.
            final int teamCnt = (cnt < 20 ? cnt : 10);
            spawned |= this.spawnZombieTeam(teamCnt);
            cnt -= teamCnt;
        }
        if (cnt > 0) {
            spawned |= this.spawnZombieTeam(cnt);
        }
        if (spawned) {
            PlayerUtil.playClientSound(player, SoundRegister.HUGE_WAVE.get());
            PlayerUtil.sendSubTitleToPlayer(player, InvasionManager.HUGE_WAVE);
            // TODO 一大波额外生成
//		    PVZFlagData data = PVZFlagData.getGlobalFlagData(world);
//		    if(data.isZombossDefeated()) {
//		        this.activateTombStone();
//		        this.checkAndSummonBungee();
//		    }
        }
        return spawned;
    }

    /**
     * spawn a zombie invade team.
     */
    private boolean spawnZombieTeam(int cnt) {
        final BlockPos mid = WorldUtil.findRandomSpawnPos(world, player.blockPosition(), 20, 16, 48,
                b -> suitableInvasionPos(world, b) && world.getBlockState(b.below()).getFluidState().isEmpty());

        boolean flag = false;
        if (mid != null) {//find spawn position.
            for (int i = 0; i < cnt; ++i) {
                final SpawnType type = getSpawnList().getRandomItem(world.random).get();
                final BlockPos pos = WorldUtil.findRandomSpawnPos(world, mid, 4, 1, 7, b -> type.checkPos(world, b));
                if (pos != null) {
                    flag = true;
                    world.addFreshEntity(EntityUtil.createWithNBT(world, type.getSpawnType(), type.getNbt(), pos));
                }
            }
            if (flag) {
                //spawn team leader -- flag zombie.
                EntityUtil.onEntitySpawn(world, EntityRegister.FLAG_ZOMBIE.get().create(world), mid.offset(0, 1, 0));

                //spawn yeti zombie when it's Yeti Invasion.
                if (world.random.nextFloat() < InvasionManager.getYetiSpawnChance(this)) {
                    EntityUtil.onEntitySpawn(world, EntityRegister.YETI_ZOMBIE.get().create(world), mid.offset(0, 1, 0));
                }
            }
        }
        return flag;
    }

    /**
     * calculate how many zombies will spawn each wave.
     */
    private int getSpawnCount(int currentWave) {
        final int maxCnt = InvasionManager.SPAWN_COUNT_EACH_WAVE[currentWave];
        final int minCnt = maxCnt / 2;
        return MathUtil.getRandomMinMax(world.random, minCnt, maxCnt);
    }

    public void load(CompoundNBT baseTag) {
        this.invasionLvl = baseTag.getInt("invasion_level");
        this.isRunning = baseTag.getBoolean("invasion_running");
        if (baseTag.contains("wave_nbt")) {//wave.
            final CompoundNBT nbt = baseTag.getCompound("wave_nbt");
            for (int i = 0; i < InvasionManager.MAX_WAVE_NUM; ++i) {
                this.waveTime[i] = nbt.getInt("wave_time_" + i);
                this.waveTriggered[i] = nbt.getBoolean("wave_triggered_" + i);
            }
            this.totalWaveCount = nbt.getInt("wave_count");
            this.currentWave = nbt.getInt("current_wave");
        }
        if (baseTag.contains("mission_nbt")) {
            final CompoundNBT nbt = baseTag.getCompound("mission_nbt");
            for (int i = 0; i < MissionManager.KILL_IN_SECOND; ++i) {
                if (nbt.contains("kill_count" + i)) {
                    this.killQueue[i] = nbt.getInt("kill_count" + i);
                }
            }
            this.killPos = nbt.getInt("kill_pos");
            this.killInSecond = nbt.getInt("kill_in_second");
        }
        if (baseTag.contains("invasion_resources")) {
            this.activeResources.clear();
            final ListNBT list = (ListNBT) baseTag.get("invasion_resources");
            for (int i = 0; i < list.size(); ++i) {
                final CompoundNBT tmp = (CompoundNBT) list.get(i);
                this.activeResources.add(new ResourceLocation(tmp.getString("type")));
            }
        }
        if (baseTag.contains("spawn_resource")) {
            this.spawnResource = new ResourceLocation(baseTag.getString("spawn_resource"));
        }
    }

    public void save(CompoundNBT baseTag) {
        baseTag.putInt("invasion_level", this.invasionLvl);
        baseTag.putBoolean("invasion_running", this.isRunning);
        {//wave.
            final CompoundNBT nbt = new CompoundNBT();
            for (int i = 0; i < InvasionManager.MAX_WAVE_NUM; ++i) {
                nbt.putInt("wave_time_" + i, this.waveTime[i]);
                nbt.putBoolean("wave_triggered_" + i, this.waveTriggered[i]);
            }
            nbt.putInt("wave_count", this.totalWaveCount);
            nbt.putInt("current_wave", this.currentWave);
            baseTag.put("wave_nbt", nbt);
        }
        {//mission.
            final CompoundNBT nbt = new CompoundNBT();
            for (int i = 0; i < MissionManager.KILL_IN_SECOND; ++i) {
                nbt.putInt("kill_count" + i, killQueue[i]);
            }
            nbt.putInt("kill_pos", this.killPos);
            nbt.putInt("kill_in_second", killInSecond);
            baseTag.put("mission_nbt", nbt);
        }
        {
            ListNBT list = new ListNBT();
            this.activeResources.forEach(res -> {
                final CompoundNBT tmp = new CompoundNBT();
                tmp.putString("type", res.toString());
                list.add(tmp);
            });
            baseTag.put("invasion_resources", list);
        }
        if (this.spawnResource != null) {
            baseTag.putString("spawn_resource", this.spawnResource.toString());
        }
    }

    /**
     * start invasion.
     * send random mission to player.
     * {@link InvasionManager#enableInvasion(Collection)}
     */
    public void enable() {
    	this.invasionLvl = PlayerUtil.getResource(player, Resources.TREE_LVL);
        this.resetWaveTime();
        this.isRunning = true;
        this.resetMission(MissionManager.getMission(player.getRandom()));
        /* choose random spawn event & assist events */
        InvasionManager.setSpawnEvent(this);
        InvasionManager.setAssistEvent(this);
        /* send msg */
        if (PVZConfig.COMMON_CONFIG.InvasionSettings.ShowEventMessages.get()) {
            this.getActiveInvasions().forEach(type -> PlayerUtil.sendMsgTo(player, type.getText()));
            if (!getSpawnList().isEmpty()) {
                final IFormattableTextComponent msg = new StringTextComponent("");
                for (int i = 0; i < getSpawnList().getLen(); ++i) {
                    final EntityType<?> type = getSpawnList().getItem(i).getSpawnType();
                    final IFormattableTextComponent component = new TranslationTextComponent("entity."
                            + type.getRegistryName().getNamespace() + "." + type.getRegistryName().getPath());
                    msg.append(i == 0 ? component : new StringTextComponent(",").append(component));
                }
                PlayerUtil.sendMsgToAll(world, msg);
            }
        }
    }

    /**
     * stop invasion.
     * clear mission of player.
     * {@link InvasionManager#disableInvasion(Collection, boolean)}
     */
    public void disable() {
        this.setTotalWaveCount(0);
        this.isRunning = false;
        this.clearMission();

        this.spawnResource = null;
        this.activeResources.clear();
        this.activeInvasions = null;
    }

    public void setInvasionType(InvasionType invasionType) {
        if (!invasionType.isAssistInvasion()) {
            this.setSpawnInvasion(invasionType.resourceLocation);
            this.updateSpawns(invasionType);
        } else {
            this.addAssistInvasion(invasionType.resourceLocation);
        }
    }

    public void updateSpawns(@Nullable InvasionType type) {
        this.spawnList.clear();
        this.spawnTypes.clear();
        if (type != null) {
            type.getSpawns().forEach(spawn -> {
                if (spawn.getInvasionLevel() <= this.invasionLvl) {
                    this.spawnList.addItem(spawn, spawn.getSpawnWeight());
                    this.spawnTypes.add(spawn.getSpawnType());
                }
            });
        }
    }

    /**
     * reset the huge wave time of each player.
     */
    public void resetWaveTime() {
        final int cnt = getPlayerWaveCount(player.getRandom(), this.invasionLvl);
        //not happen at the first 2000 ticks and the last 2000 ticks of the day.
        final int eachTime = 20000 / cnt;
        for (int i = 0; i < cnt; ++i) {
            final int offset = 2000 + i * eachTime + player.getRandom().nextInt(eachTime);
            final int pos = i;
            this.setWaveTime(pos, offset);
            this.setWaveTriggered(pos, false);
        }
        this.currentWave = 0;
        this.setTotalWaveCount(cnt);
    }

    public void setSpawnInvasion(ResourceLocation resourceLocation) {
        if (this.spawnResource != null) {
            this.activeResources.remove(this.spawnResource);
        }
        this.spawnResource = resourceLocation;
        this.activeResources.add(this.spawnResource);
        this.updateSpawns(InvasionManager.getInvasion(resourceLocation));
    }

    public void addAssistInvasion(ResourceLocation resourceLocation) {
        if (!this.activeResources.contains(resourceLocation)) {
            this.activeResources.add(resourceLocation);
        }
    }

    public void removeAssistInvasion(ResourceLocation resourceLocation) {
        if (this.activeResources.contains(resourceLocation)) {
            this.activeResources.remove(resourceLocation);
        }
    }

    public void clearInvasion() {
        this.spawnInvasion = null;
        this.activeResources.clear();
    }

    public ResourceLocation getSpawnResource() {
        return this.spawnResource;
    }

    public Set<ResourceLocation> getActiveResources() {
        return this.activeResources;
    }

    public InvasionType getSpawnInvasion() {
        return this.spawnInvasion == null ? this.spawnInvasion = InvasionManager.getInvasion(this.spawnResource) : this.spawnInvasion;
    }

    public WeightList<SpawnType> getSpawnList() {
        if (this.spawnList.isEmpty()) {
            this.updateSpawns(this.getSpawnInvasion());
        }
        return this.spawnList;
    }

    public Set<InvasionType> getActiveInvasions() {
        if (this.activeInvasions == null) {
            this.activeInvasions = new HashSet<>();
            this.activeResources.forEach(res -> {
                final InvasionType type = InvasionManager.getInvasion(res);
                if (type != null) {
                    this.activeInvasions.add(type);
                }
            });
            if (isRunning && this.activeInvasions.isEmpty()) {
                this.activeInvasions = null;
            }
        }
        return this.activeInvasions == null ? new HashSet<>() : this.activeInvasions;
    }

    public void setWaveTime(int pos, int data) {
        if (pos >= 0 && pos < InvasionManager.MAX_WAVE_NUM) {
            this.waveTime[pos] = data;
            this.sendWavePacket(player, pos, data);
        }
    }

    public void setTotalWaveCount(int cnt) {
        this.totalWaveCount = cnt;
        this.sendWavePacket(player, -1, cnt);
    }

    public void setWaveTriggered(int pos, boolean is) {
        if (pos >= 0 && pos < InvasionManager.MAX_WAVE_NUM) {
            this.waveTriggered[pos] = is;
            this.sendWaveFlagPacket(player, pos, is);
        }
    }

    public int getTotalWaveCount() {
        return totalWaveCount;
    }

    public boolean getWaveTriggered(int pos) {
        return waveTriggered[pos];
    }

    public int getWaveTime(int pos) {
        return waveTime[pos];
    }

    public int getInvasionLvl() {
        return invasionLvl;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public World getWorld() {
        return this.world;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public Random getRandom() {
        return this.player.getRandom();
    }

    public boolean isInvasionEntity(EntityType<?> entityType) {
        if (this.spawnTypes.isEmpty()) {
            updateSpawns(getSpawnInvasion());
        }
        return this.spawnTypes.contains(entityType);
    }

    private void sendWavePacket(PlayerEntity player, int pos, int data) {
        if (player instanceof ServerPlayerEntity) {
            PVZPacketHandler.CHANNEL.send(
                    PacketDistributor.PLAYER.with(() -> {
                        return (ServerPlayerEntity) player;
                    }),
                    new OtherStatsPacket(PVZPacketTypes.WAVE, pos, data)
            );
        }
    }

    private void sendWaveFlagPacket(PlayerEntity player, int pos, boolean flag) {
        if (player instanceof ServerPlayerEntity) {
            PVZPacketHandler.CHANNEL.send(
                    PacketDistributor.PLAYER.with(() -> {
                        return (ServerPlayerEntity) player;
                    }),
                    new OtherStatsPacket(PVZPacketTypes.WAVE_FLAG, pos, flag)
            );
        }
    }

    public void sendAllWavePacket(PlayerEntity player) {
        for (int i = 0; i < InvasionManager.MAX_WAVE_NUM; ++i) {
            sendWavePacket(player, i, this.waveTime[i]);
            sendWaveFlagPacket(player, i, this.waveTriggered[i]);
        }
        sendWavePacket(player, -1, this.totalWaveCount);
    }

    public void resetMission(MissionManager.MissionType type) {
        PlayerUtil.setResource(player, Resources.MISSION_TYPE, type.ordinal());
        PlayerUtil.setResource(player, Resources.MISSION_VALUE, 0);
        PlayerUtil.setResource(player, Resources.MISSION_STAGE, 0);
        for (int i = 0; i < MissionManager.KILL_IN_SECOND; ++i) {
            this.killQueue[i] = 0;
        }
        this.killInSecond = 0;
    }

    public void updateKillQueue() {
        final int next = (killPos + 1) % MissionManager.KILL_IN_SECOND;
        PlayerUtil.setResource(player, Resources.MISSION_VALUE, -this.killQueue[next]);
        this.killQueue[next] = this.killInSecond;
        this.killPos = next;
        this.killInSecond = 0;
    }

    public void clearMission() {
        this.resetMission(MissionManager.MissionType.EMPTY);
    }

    /**
     * max : 10 20 35 50 65 80 100 ...
     * min : 20 40 60 80 100
     */
    private static int getPlayerWaveCount(Random random, int lvl) {
        final int max = Math.min(lvl <= 20 ? (lvl + 9) / 10 : lvl <= 80 ? (lvl - 6) / 15 + 2 : lvl / 20 + 3, InvasionManager.MAX_WAVE_NUM);
        final int min = Math.max(1, Math.min(lvl <= 40 ? (lvl + 19) / 20 : (lvl + 39) / 40 + 1, max - 1));
        return MathUtil.getRandomMinMax(random, min, max);
    }

    /**
     * hard : 1s normal : 3s easy : 5s
     * 20 min = 1200 s
     */
    private int getSpawnCD() {
        if (spawnCD == 0) {
            final int mid = world.getDifficulty() == Difficulty.HARD ? 20 : world.getDifficulty() == Difficulty.NORMAL ? 60 : 100;
            this.spawnCD = MathUtil.getRandomMinMax(world.random, -10, 10) + mid;
        }
        return world.getDifficulty() == Difficulty.PEACEFUL ? 2000 : spawnCD;
    }

    /**
     * hard : 3 - 5 normal : 2 - 5 easy : 2 - 3
     */
    private int getSpawnCount() {
        final int max = world.getDifficulty() == Difficulty.EASY ? 3 : 5;
        final int min = world.getDifficulty() == Difficulty.HARD ? 3 : 2;
        return MathUtil.getRandomMinMax(world.random, min, max);
    }

}
