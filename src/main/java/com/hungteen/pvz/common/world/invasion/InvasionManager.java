package com.hungteen.pvz.common.world.invasion;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.advancement.trigger.InvasionTrigger;
import com.hungteen.pvz.common.datapack.InvasionTypeLoader;
import com.hungteen.pvz.common.event.PVZServerEvents;
import com.hungteen.pvz.common.event.events.InvasionEvent;
import com.hungteen.pvz.common.world.biome.BiomeRegister;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.others.WeightList;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvasionManager {

    private static final Component START = new TranslatableComponent("invasion.pvz.start")
            .withStyle(ChatFormatting.DARK_RED);
    private static final Component END = new TranslatableComponent("invasion.pvz.end")
            .withStyle(ChatFormatting.GREEN);
    public static final Component HUGE_WAVE = new TranslatableComponent("invasion.pvz.huge_wave").withStyle(ChatFormatting.DARK_RED);
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
    public static void syncStartInvasionCache(ServerLevel world) {
    }

    /**
     * only run when world server shut down.
     * {@link PVZServerEvents#serverShutDown(net.minecraftforge.fml.event.server.FMLServerStoppingEvent)}
     */
    public static void syncEndInvasionCache(ServerLevel world) {
    }

    /**
     * {@link com.hungteen.pvz.common.event.handler.PlayerEventHandler#onPlayerLogin(Player)}
     */
    public static void addPlayer(Player player){
        INVASIONS.add(PlayerUtil.getInvasion(player));
    }

    /**
     * {@link com.hungteen.pvz.common.event.handler.PlayerEventHandler#onPlayerLogout(Player)}
     */
    public static void removePlayer(Player player){
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
                        activateInvasionEvents(ev.world);
                    } else if (isSafe) {
                        PlayerUtil.sendMsgToAll(ev.world,
                                new TranslatableComponent("invasion.pvz.safe_day", String.format("%.1f", -dif * 1.0 / 24000))
                                        .withStyle(ChatFormatting.GREEN));
                    } else {
                        PlayerUtil.sendMsgToAll(ev.world, new TranslatableComponent("invasion.pvz.count_down", count)
                                .withStyle(ChatFormatting.RED));
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
    public static void activateInvasionEvents(Level world) {
        if (world.getDifficulty() != Difficulty.PEACEFUL && !MinecraftForge.EVENT_BUS.post(new InvasionEvent.InvasionStartEvent(world))) {
            enableInvasion(PlayerUtil.getServerPlayers(world));
        }
    }

    /**
     * {@link #activateInvasionEvents(Level)}
     */
    public static void enableInvasion(Collection<ServerPlayer> players){
        players.forEach(player -> {
            final Invasion invasion = PlayerUtil.getInvasion(player);
            if(! invasion.isRunning()){
                PlayerUtil.sendMsgTo(player, START);
                PlayerUtil.playClientSound(player, SoundRegister.ZOMBIE_SIREN.get());
                InvasionTrigger.INSTANCE.trigger(player);
            }
            invasion.enable();
        });
    }

    /**
     * deactivate all invasion events.
     * {@link #tick(TickEvent.WorldTickEvent)}
     */
    public static void deactivateInvasion(Level world, boolean isNatural) {
        disableInvasion(PlayerUtil.getServerPlayers(world), isNatural);
    }

    /**
     * {@link #deactivateInvasion(Level, boolean)}
     */
    public static void disableInvasion(Collection<ServerPlayer> players, boolean isNatural){
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
    private static long getSafeDayDif(Level world) {
        return world.getGameTime() - getSafeDayLength(world) * 24000;
    }

    public static int getSafeDayLength(Level world) {
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
        final boolean isJack = invasion.getActiveResources().contains(StringUtil.prefix("jack"));
        final boolean isYeti = invasion.getActiveResources().contains(StringUtil.prefix("yeti"));
        return (isJack && isYeti) ? 0.4F : isYeti ? 0.2F : isJack ? 0.1F : 0;
    }

    public static boolean hasInvisInvasion(Invasion invasion){
        return invasion.getActiveResources().contains(StringUtil.prefix("invis"));
    }

    public static boolean hasMiniInvasion(Invasion invasion){
        return invasion.getActiveResources().contains(StringUtil.prefix("mini"));
    }

    /**
     * get players out zen garden. TODO Plant Invasion.
     */
    public static boolean suitableInvasionPos(Level world, Mth pos){
        return ! world.getBiomeManager().getBiome(pos).getRegistryName().equals(BiomeRegister.ZEN_GARDEN.get().getRegistryName());
    }

    public static boolean enableSkills(Level world){
        return world.getDifficulty() == Difficulty.HARD;
    }

}
