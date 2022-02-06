package com.hungteen.pvz.common.world.invasion;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.datapack.InvasionTypeLoader;
import com.hungteen.pvz.common.event.PVZServerEvents;
import com.hungteen.pvz.common.event.events.InvasionEvent;
import com.hungteen.pvz.register.BiomeRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.others.WeightList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

public class InvasionManager {

    private static final ITextComponent START = new TranslationTextComponent("invasion.pvz.start")
            .withStyle(TextFormatting.DARK_RED);
    private static final ITextComponent END = new TranslationTextComponent("invasion.pvz.end")
            .withStyle(TextFormatting.GREEN);
    public static final ITextComponent HUGE_WAVE = new TranslationTextComponent("invasion.pvz.huge_wave").withStyle(TextFormatting.DARK_RED);
    public static final int[] SPAWN_COUNT_EACH_WAVE = new int[] {25, 30, 35, 40, 45, 50, 55, 60, 65, 70};
    public static final int PRE_START_TICK = 499;
    public static final int START_TICK = 500;
    public static final int PRE_END_TICK = 99;
    public static final int END_TICK = 100;
    public static final int MAX_WAVE_NUM = 10;
    private static final Set<Invasion> INVASIONS = new HashSet<>();

    /**
     * only run when world server start.
     * {@link PVZServerEvents#serverInit(net.minecraftforge.fml.event.server.FMLServerStartingEvent)}
     */
    public static void syncStartInvasionCache(ServerWorld world) {
    }

    /**
     * only run when world server shut down.
     * {@link PVZServerEvents#serverShutDown(net.minecraftforge.fml.event.server.FMLServerStoppingEvent)}
     */
    public static void syncEndInvasionCache(ServerWorld world) {
    }

    /**
     * {@link com.hungteen.pvz.common.event.handler.PlayerEventHandler#onPlayerLogin(PlayerEntity)}
     */
    public static void addPlayer(PlayerEntity player){
        INVASIONS.add(PlayerUtil.getInvasion(player));
    }

    /**
     * {@link com.hungteen.pvz.common.event.handler.PlayerEventHandler#onPlayerLogout(PlayerEntity)}
     */
    public static void removePlayer(PlayerEntity player){
        INVASIONS.remove(PlayerUtil.getInvasion(player));
    }

    /**
     * tick for overworld invasion events.
     * {@link com.hungteen.pvz.common.event.PVZWorldEvents#onWorldTick(TickEvent.WorldTickEvent)}
     */
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
                    deactivateInvasion(ev.world, false);// cancel all invasion happened yesterday.
                    final long dif = getSafeDayDif(ev.world);
                    final boolean isSafe = (dif < 0);
                    final int count = data.getCountDownDay();
                    if (!isSafe && !data.hasCountDownDay()) {// no interval and not safe then invade happen !
                        activateZombieAttackEvents(ev.world);
                    } else if (isSafe) {
                        PlayerUtil.sendMsgToAll(ev.world,
                                new TranslationTextComponent("invasion.pvz.safe_day", String.format("%.1f", -dif * 1.0 / 24000))
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
                    deactivateInvasion(ev.world, true);
                }
                break;
            }
        }

        INVASIONS.forEach(invasion -> {
            invasion.tick();
        });
    }

    /**
     * check and activate attack event, do not activate in peaceful mode.
     * {@link #tick(TickEvent.WorldTickEvent)}
     */
    public static void activateZombieAttackEvents(World world) {
        if (world.getDifficulty() != Difficulty.PEACEFUL && !MinecraftForge.EVENT_BUS.post(new InvasionEvent.InvasionStartEvent(world))) {
            enableInvasion(PlayerUtil.getServerPlayers(world));
        }
    }

    /**
     * {@link #activateZombieAttackEvents(World)}
     */
    public static void enableInvasion(Collection<ServerPlayerEntity> players){
        players.forEach(player -> {
            final Invasion invasion = PlayerUtil.getInvasion(player);
            if(! invasion.isRunning()){
                PlayerUtil.sendMsgTo(player, START);
                PlayerUtil.playClientSound(player, SoundRegister.ZOMBIE_SIREN.get());
            }
            invasion.enable();
        });
    }

    /**
     * deactivate all invasion events.
     * {@link #tick(TickEvent.WorldTickEvent)}
     */
    public static void deactivateInvasion(World world, boolean isNatural) {
        disableInvasion(PlayerUtil.getServerPlayers(world), isNatural);
    }

    /**
     * {@link #deactivateInvasion(World, boolean)}
     */
    public static void disableInvasion(Collection<ServerPlayerEntity> players, boolean isNatural){
        players.forEach(player -> {
            final Invasion invasion = PlayerUtil.getInvasion(player);
            if(isNatural && invasion.isRunning()){//send disable msg to player.
                PlayerUtil.sendMsgTo(player, END);
                PlayerUtil.playClientSound(player, SoundRegister.WIN_MUSIC.get());
            }
            invasion.disable();
        });
    }

    /**
     * randomly get a spawn invasion event.
     * {@link Invasion#enable()}
     */
    public static void setSpawnEvent(Invasion invasion) {
        final WeightList<InvasionType> list = new WeightList<>();
        getAllSpawnEvents().stream().filter(type -> type.getRequireDifficulty() <= invasion.getInvasionLvl()).forEach(type -> {
            list.addItem(type, type.getTriggerChance());
        });
        invasion.setInvasionType(list.getRandomItem(invasion.getRandom()).get());
    }

    /**
     * randomly activate assist invasion events.
     * {@link Invasion#enable()}
     */
    public static void setAssistEvent(Invasion invasion) {
        getAllAssistEvents().stream().filter(type -> type.getRequireDifficulty() <= invasion.getInvasionLvl()).forEach(type -> {
            if (invasion.getRandom().nextInt(type.getTriggerChance()) == 0) {
                invasion.setInvasionType(type);
            }
        });
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

    public static Stream<ResourceLocation> getIds() {
        return InvasionTypeLoader.INVASIONS.keySet().stream();
    }

    public static Collection<InvasionType> getAllInvasionEvents() {
        return InvasionTypeLoader.INVASIONS.values();
    }

    public static Collection<InvasionType> getAllSpawnEvents() {
        return InvasionTypeLoader.INVASIONS.values().stream().filter(type -> ! type.isAssistInvasion()).collect(Collectors.toList());
    }

    public static Collection<InvasionType> getAllAssistEvents() {
        return InvasionTypeLoader.INVASIONS.values().stream().filter(type -> type.isAssistInvasion()).collect(Collectors.toList());
    }

    public static InvasionType getInvasion(ResourceLocation res) {
        return InvasionTypeLoader.INVASIONS.get(res);
    }

    public static float getYetiSpawnChance(Invasion invasion){
        final boolean isJack = invasion.getActiveInvasions().contains(StringUtil.prefix("jack"));
        final boolean isYeti = invasion.getActiveInvasions().contains(StringUtil.prefix("yeti"));
        return (isJack && isYeti) ? 0.4F : isYeti ? 0.2F : isJack ? 0.1F : 0;
    }

    public static boolean hasInvisInvasion(Invasion invasion){
        return invasion.getActiveInvasions().contains(StringUtil.prefix("invis"));
    }

    public static boolean hasMiniInvasion(Invasion invasion){
        return invasion.getActiveInvasions().contains(StringUtil.prefix("mini"));
    }

    /**
     * get players out zen garden. TODO Plant Invasion.
     */
    public static boolean suitableInvasionPos(World world, BlockPos pos){
        return ! world.getBiomeManager().getBiome(pos).getRegistryName().equals(BiomeRegister.ZEN_GARDEN.get().getRegistryName());
    }
}
