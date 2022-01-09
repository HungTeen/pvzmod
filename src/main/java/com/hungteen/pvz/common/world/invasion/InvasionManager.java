package com.hungteen.pvz.common.world.invasion;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.datapack.InvasionTypeLoader;
import com.hungteen.pvz.common.event.PVZServerEvents;
import com.hungteen.pvz.common.event.events.InvasionEvent;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.register.BiomeRegister;
import com.hungteen.pvz.utils.*;
import com.hungteen.pvz.utils.others.WeightList;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvasionManager {

    private static final ITextComponent START = new TranslationTextComponent("invasion.pvz.start")
            .withStyle(TextFormatting.DARK_RED);
    private static final ITextComponent END = new TranslationTextComponent("invasion.pvz.end")
            .withStyle(TextFormatting.GREEN);
    public static final int PRE_START_TICK = 499;
    public static final int START_TICK = 500;
    public static final int PRE_END_TICK = 99;
    public static final int END_TICK = 100;
    public static final WeightList<SpawnType> spawnList = new WeightList<>();
    public static final Set<EntityType<?>> spawnTypes = new HashSet<>();
    public static ResourceLocation spawnResource;
    public static InvasionType spawnInvasion;
    public static final Set<ResourceLocation> activeResources = new HashSet<>();
    public static Set<InvasionType> activeInvasions;
    public static int invasionDifficulty = 0;
    public static boolean isRunning = false;
    private static int tick = 0;
    private static int spawnCD = 0;

    /**
     * only run when world server start.
     * {@link PVZServerEvents#serverInit(net.minecraftforge.fml.event.server.FMLServerStartingEvent)}
     */
    public static void syncStartInvasionCache(ServerWorld world) {
        final PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
        invasionDifficulty = data.getCurrentDifficulty();
        isRunning = data.isRunning();
        spawnResource = data.getSpawnInvasion();
        {
            activeResources.clear();
            data.getInvasionResources().forEach(res -> activeResources.add(res));
        }
    }

    /**
     * only run when world server shut down.
     * {@link PVZServerEvents#serverShutDown(net.minecraftforge.fml.event.server.FMLServerStoppingEvent)}
     */
    public static void syncEndInvasionCache(ServerWorld world) {

    }

    public static void tick(TickEvent.WorldTickEvent ev) {
        final long dayTime = ev.world.getDayTime() % 24000;
        switch ((int) dayTime) {
            case PRE_START_TICK:
            case PRE_END_TICK: {
                PVZInvasionData.getOverWorldInvasionData(ev.world).setChanged(false);
                break;
            }
            case START_TICK: {
                PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(ev.world);
                if (!data.hasChanged()) {
                    data.setChanged(true);
                    deactivateZombieAttackEvents(ev.world, false);// cancel all invasion happened yesterday.
                    final long dif = getSafeDayDif(ev.world);
                    final boolean isSafe = (dif < 0);
                    final int count = data.getCountDownDay();
                    if (!isSafe && !data.hasCountDownDay()) {// no interval and not safe then invade happen !
                        activateZombieAttackEvents(ev.world);
                    } else if (isSafe) {
                        PlayerUtil.sendMsgToAll(ev.world,
                                new TranslationTextComponent("invasion.pvz.safe_day", Math.ceil(-dif / 24000))
                                        .withStyle(TextFormatting.GREEN));
                    } else {
                        PlayerUtil.sendMsgToAll(ev.world, new TranslationTextComponent("invasion.pvz.count_down", count)
                                .withStyle(TextFormatting.RED));
                    }
                    data.decCountDownDay();
                }
                break;
            }
            case END_TICK: {
                PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(ev.world);
                if (!data.hasChanged()) {
                    data.setChanged(true);
                    deactivateZombieAttackEvents(ev.world, true);
                }
                break;
            }
        }
        // wait for data pack or peaceful mode.
        if (isRunning && getSpawnInvasion() != null && !(ev.world.getDifficulty() == Difficulty.PEACEFUL)) {
            tickSpawn(ev.world);
            WaveManager.tickWave(ev.world, (int) dayTime);
            MissionManager.tickMission(ev.world);
        }
    }

    public static void tickSpawn(World world) {
        world.getProfiler().push("Invasion Tick");
        if (++tick >= getSpawnCD(world)) {
            if (spawnList.isEmpty()) {
                updateSpawns(getSpawnInvasion());
            }
            if (!spawnList.isEmpty()) {
                final List<ServerPlayerEntity> players = PlayerUtil.getServerPlayers(world).stream().filter(player -> {
                    return suitableInvasionPos(world, player.blockPosition());
                }).collect(Collectors.toList());
                for (int i = 0; i < (players.size() + 1) / 2; ++i) {
                    final int pos = world.random.nextInt(players.size());
                    tickSpawn(world, players.get(pos));
                }
            }
        }
        world.getProfiler().pop();
    }

    public static void tickSpawn(World world, PlayerEntity player) {
        final int range = PVZConfig.COMMON_CONFIG.InvasionSettings.MaxSpawnRange.get();
        final int maxCount = PVZConfig.COMMON_CONFIG.InvasionSettings.MaxSpawnEachPlayer.get();
        final int current = EntityUtil
                .getPredicateEntities(player, EntityUtil.getEntityAABB(player, range, range), MobEntity.class, e -> {
                    return spawnTypes.contains(e.getType());
                }).size();
        if (current < maxCount) {
            final int cnt = getSpawnCount(world);
            for (int i = 0; i < cnt; ++i) {
                final SpawnType type = spawnList.getRandomItem(world.random).get();
                final BlockPos pos = WorldUtil.findRandomSpawnPos(world, player.blockPosition(), 10, 8, range,
                        b -> suitableInvasionPos(world, b) && type.checkPos(world, b));
                if (pos != null) {
                    EntityUtil.onEntitySpawn(world, type.getSpawnType().create(world), pos);
                }
            }
        }
    }

    /**
     * check and activate attack event, do not activate in peaceful mode.
     */
    private static void activateZombieAttackEvents(World world) {
        if (world.getDifficulty() != Difficulty.PEACEFUL
                && !MinecraftForge.EVENT_BUS.post(new InvasionEvent.InvasionStartEvent(world))) {
            /* notify all players when invasion start */
            PlayerUtil.getServerPlayers(world).forEach(player -> {
                PlayerUtil.sendMsgTo(player, START);
                PlayerUtil.playClientSound(player, SoundRegister.WARN.get());
                WaveManager.resetPlayerWaveTime(player);
                MissionManager.offerMission(player);
            });
            /* add difficulty */
            PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
            data.addCurrentDifficulty(ConfigUtil.getIncDifficulty());
            /* choose random spawn event */
            activateEvent(world, getSpawnEvent(world));
            /* check assist event */
            activateAssistEvents(world);
            if (PVZConfig.COMMON_CONFIG.InvasionSettings.ShowEventMessages.get()) {
                PlayerUtil.getServerPlayers(world).forEach(player -> {
                    getActiveInvasions().forEach(type -> PlayerUtil.sendMsgTo(player, type.getText()));
                });
                if (!spawnList.isEmpty()) {
                    final IFormattableTextComponent msg = new StringTextComponent("");
                    for (int i = 0; i < spawnList.getLen(); ++i) {
                        final EntityType<?> type = spawnList.getItem(i).getSpawnType();
                        final IFormattableTextComponent component = new TranslationTextComponent("entity."
                                + type.getRegistryName().getNamespace() + "." + type.getRegistryName().getPath());
                        msg.append(i == 0 ? component : new StringTextComponent(",").append(component));
                    }
                    PlayerUtil.sendMsgToAll(world, msg);
                }
            }
        }
    }

    /**
     * deactivate all invasion events.
     */
    public static void deactivateZombieAttackEvents(World world, boolean isNatural) {
        if (isNatural && isRunning) {// end invasion.
            PlayerUtil.getServerPlayers(world).forEach(player -> {
                PlayerUtil.sendMsgTo(player, END);
                PlayerUtil.playClientSound(player, SoundRegister.WIN_MUSIC.get());
            });
        }
        PlayerUtil.getServerPlayers(world).forEach(player -> {
            WaveManager.clearWaveTime(player);
            MissionManager.removeMission(player);
        });
        PVZInvasionData.getOverWorldInvasionData(world).clearInvasion();
        PVZInvasionData.getOverWorldInvasionData(world).setRunning(false);
    }

    public static void activateEvent(World world, InvasionType type) {
        if (!type.isAssistInvasion()) {
            PVZInvasionData.getOverWorldInvasionData(world).setSpawnInvasion(type.resourceLocation);
            updateSpawns(type);
        } else {
            PVZInvasionData.getOverWorldInvasionData(world).addAssistInvasion(type.resourceLocation);
        }
    }

    public static void activateAssistEvents(World world) {
        getInvasionEvents().stream().filter(type -> type.isAssistInvasion() && type.getRequireDifficulty() <= getInvasionDifficulty()).forEach(type -> {
            if (world.getRandom().nextInt(type.getTriggerChance()) == 0) {
                activateEvent(world, type);
            }
        });
    }

    public static void updateSpawns(InvasionType type) {
        spawnList.clear();
        spawnTypes.clear();
        type.getSpawns().forEach(spawn -> {
            if (spawn.getOccurDay() * ConfigUtil.getIncDifficulty() <= InvasionManager.getInvasionDifficulty()) {
                spawnList.addItem(spawn, spawn.getSpawnWeight());
                spawnTypes.add(spawn.getSpawnType());
            }
        });
    }

    /**
     * randomly get a spawn invasion event.
     */
    private static InvasionType getSpawnEvent(World world) {
        WeightList<InvasionType> list = new WeightList<>();
        getInvasionEvents().stream().filter(type -> !type.isAssistInvasion()
                && type.getRequireDifficulty() <= InvasionManager.getInvasionDifficulty()).forEach(type -> {
            list.addItem(type, type.getTriggerChance());
        });
        return list.getRandomItem(world.random).get();
    }

    /**
     * hard : 5s - 10s normal : 5s - 15s easy : 8s - 15s
     */
    public static int getSpawnCD(World world) {
        if (spawnCD == 0) {
            final int max = world.getDifficulty() == Difficulty.HARD ? 200 : 300;
            final int min = world.getDifficulty() == Difficulty.EASY ? 160 : 100;
            spawnCD = MathUtil.getRandomMinMax(world.random, min, max);
        }
        return world.getDifficulty() == Difficulty.PEACEFUL ? 200 : spawnCD;
    }

    /**
     * hard : 2 - 5 normal : 1 - 5 easy : 1 - 4
     */
    public static int getSpawnCount(World world) {
        final int max = world.getDifficulty() == Difficulty.EASY ? 4 : 5;
        final int min = world.getDifficulty() == Difficulty.HARD ? 2 : 1;
        return MathUtil.getRandomMinMax(world.random, min, max);
    }

    /**
     * if it is still in safe day, then the dif must less than 0.
     */
    private static long getSafeDayDif(World world) {
        return world.getGameTime() - getSafeDayLength(world) * 24000;
    }

    public static int getSafeDayLength(World world) {
        final Difficulty difficulty = world.getDifficulty();
        final int multiple = difficulty == Difficulty.HARD ? 0
                : difficulty == Difficulty.NORMAL ? 1 : difficulty == Difficulty.EASY ? 2 : 3;
        return PVZConfig.COMMON_CONFIG.InvasionSettings.SafeDayLength.get() * multiple;
    }

    public static Collection<InvasionType> getActiveResources() {
        return InvasionTypeLoader.INVASIONS.values();
    }

    public static InvasionType getSpawnInvasion() {
        return spawnInvasion == null ? spawnInvasion = getInvasion(spawnResource) : spawnInvasion;
    }

    public static Set<InvasionType> getActiveInvasions() {
        if (activeInvasions == null) {
            activeInvasions = new HashSet<>();
            activeResources.forEach(res -> {
                final InvasionType type = getInvasion(res);
                if (type != null) {
                    activeInvasions.add(type);
                }
            });
            if (isRunning && activeInvasions.isEmpty()) {
                activeInvasions = null;
            }
        }
        return activeInvasions == null ? new HashSet<>() : activeInvasions;
    }

    public static Stream<ResourceLocation> getIds() {
        return InvasionTypeLoader.INVASIONS.keySet().stream();
    }

    public static Collection<InvasionType> getInvasionEvents() {
        return InvasionTypeLoader.INVASIONS.values();
    }

    public static InvasionType getInvasion(ResourceLocation res) {
        return InvasionTypeLoader.INVASIONS.get(res);
    }

    public static int getInvasionDifficulty() {
        return invasionDifficulty;
    }

    public static float getYetiSpawnChance(){
        final boolean isJack = getActiveInvasions().contains(StringUtil.prefix("jack"));
        final boolean isYeti = getActiveInvasions().contains(StringUtil.prefix("yeti"));
        return (isJack && isYeti) ? 0.4F : isYeti ? 0.2F : isJack ? 0.1F : 0;
    }

    /**
     * get players out zen garden. TODO Plant Invasion.
     */
    public static boolean suitableInvasionPos(World world, BlockPos pos){
        return ! world.getBiomeManager().getBiome(pos).equals(BiomeRegister.ZEN_GARDEN.get());
    }
}
