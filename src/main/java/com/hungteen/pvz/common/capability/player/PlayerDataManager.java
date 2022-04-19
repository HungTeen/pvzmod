package com.hungteen.pvz.common.capability.player;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.events.PlayerLevelChangeEvent;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.advancement.trigger.ResourceTrigger;
import com.hungteen.pvz.common.event.handler.PlayerEventHandler;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.network.PAZStatsPacket;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.PlayerStatsPacket;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.Util;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 13:05
 **/
public class PlayerDataManager {

    private final Player player;
    /* player resources */
    private final EnumMap<Resources, Integer> resources = new EnumMap<>(Resources.class);
    /* plant & zombie cd */
    private final Map<IPAZType, Integer> pazCardCD = new HashMap<>();
    private final Map<IPAZType, Float> pazCardBar = new HashMap<>();
    /* plant & zombie lock */
    private final Map<IPAZType, Boolean> pazLocked = new HashMap<>();
    /* misc data */
    public String lastVersion = Util.INIT_VERSION;

    public PlayerDataManager(Player player){
        this.player = player;
        {// init player resources.
            for(Resources res : Resources.values()) {
                resources.put(res, Resources.getInitialValue(res));
            }
        }
        {// init about plants & zombies.
            PVZAPI.get().getPAZTypes().forEach(plant -> {
                this.pazCardCD.put(plant, 0);
                this.pazCardBar.put(plant, 0F);
                this.pazLocked.put(plant, true);
            });
        }
    }

    /**
     * {@link PVZPlayerCapProvider#deserializeNBT(CompoundTag)}
     */
    public void loadFromNBT(CompoundTag baseTag){
        {// load player resources.
            if(baseTag.contains("player_stats")) {//old.
                CompoundTag statsTag = baseTag.getCompound("player_stats");
                for(Resources res : Resources.values()) {
                    if(statsTag.contains("player_" + res.toString())) {
                        this.resources.put(res, statsTag.getInt("player_" + res.toString()));
                    }
                }
            }
            if(baseTag.contains("player_resources")) {//new.
                CompoundTag statsTag = baseTag.getCompound("player_resources");
                for(Resources res : Resources.values()) {
                    if(statsTag.contains(res.toString().toLowerCase())) {
                        this.resources.put(res, statsTag.getInt(res.toString().toLowerCase()));
                    }
                }
            }
        }
        {/* old load method */
            {// load plant & zombie card cd.
                if(baseTag.contains("paz_card_item_cd")) {
                    final CompoundTag nbt = baseTag.getCompound("paz_card_item_cd");
                    PVZAPI.get().getPAZTypes().forEach(plant -> {
                        final CompoundTag plantNBT = (CompoundTag) nbt.get(plant.getIdentity());
                        if(plantNBT != null) {
                            if(plantNBT.contains("paz_card_cd")) {
                                this.pazCardCD.put(plant, plantNBT.getInt("paz_card_cd"));
                            }
                            if(plantNBT.contains("paz_card_bar")) {
                                this.pazCardBar.put(plant, (float) plantNBT.getInt("paz_card_bar"));
                            }
                        }
                    });
                }
            }
            {// load plant & zombie lock.
                if(baseTag.contains("paz_locks")) {
                    final CompoundTag nbt = baseTag.getCompound("paz_locks");
                    PVZAPI.get().getPAZTypes().forEach(plant -> {
                        final CompoundTag plantNBT = (CompoundTag) nbt.get(plant.getIdentity());
                        if(plantNBT != null) {
                            if(plantNBT.contains("paz_locked")) {
                                this.pazLocked.put(plant, plantNBT.getBoolean("paz_locked"));
                            }
                        }
                    });
                }
            }
        }
        {// load plant & zombie card cd.
            if(baseTag.contains("paz_data")) {
                final CompoundTag nbt = baseTag.getCompound("paz_data");
                PVZAPI.get().getPAZTypes().forEach(plant -> {
                    final CompoundTag plantNBT = nbt.getCompound(plant.getIdentity());
                    if(plantNBT.contains("paz_card_cd")) {
                        this.pazCardCD.put(plant, plantNBT.getInt("paz_card_cd"));
                    }
                    if(plantNBT.contains("paz_card_bar")) {
                        this.pazCardBar.put(plant, plantNBT.getFloat("paz_card_bar"));
                    }
                    if(plantNBT.contains("paz_locked")) {
                        this.pazLocked.put(plant, plantNBT.getBoolean("paz_locked"));
                    }
                });
            }
        }
        {// load misc data.
//            if(baseTag.contains("invasion_data")){
//                this.invasion.load(baseTag.getCompound("invasion_data"));
//            }
            if(baseTag.contains("last_join_version")) {
                this.lastVersion = baseTag.getString("last_join_version");
            }
//            this.otherStats.loadFromNBT(baseTag);
        }
    }

    /**
     * {@link PVZPlayerCapProvider#serializeNBT()}
     */
    public CompoundTag saveToNBT(){
        CompoundTag baseTag = new CompoundTag();
        {// save player resources.
            CompoundTag statsNBT = new CompoundTag();
            for(Resources res : Resources.values()) {
                statsNBT.putInt(res.toString().toLowerCase(), resources.get(res));
            }
            baseTag.put("player_resources", statsNBT);
        }
        {// save plant & zombie card cd.
            final CompoundTag nbt = new CompoundTag();
            PVZAPI.get().getPAZTypes().forEach(plant -> {
                final CompoundTag plantNBT = new CompoundTag();
                plantNBT.putInt("paz_card_cd", this.pazCardCD.get(plant));
                plantNBT.putFloat("paz_card_bar", this.pazCardBar.get(plant));
                plantNBT.putBoolean("paz_locked", this.isPAZLocked(plant));
                nbt.put(plant.getIdentity(), plantNBT);
            });
            baseTag.put("paz_data", nbt);
        }
        {// load misc data.
//            {
//                final CompoundTag nbt = new CompoundTag();
//                this.invasion.save(nbt);
//                baseTag.put("invasion_data", nbt);
//            }
            baseTag.putString("last_join_version", this.lastVersion);
        }
        return baseTag;
    }

    /**
     * copy player data when clone event happen.
     */
    public void cloneFromExistingPlayerData(PlayerDataManager data, boolean died) {
        this.loadFromNBT(data.saveToNBT());

        /* reset some value when die */
        if(died) {
            if(! PVZConfig.shouldKeepSunWhenDie()) {
                this.setResource(Resources.SUN_NUM, 50);
            }
        }
    }

    /**
     * run when player join world.
     * {@link PlayerEventHandler#onPlayerLogin(Player)}
     */
    public void init() {
        this.syncBounds();
        this.syncToClient();
    }

    /**
     * avoid render out of bound.
     * {@link #init()}
     */
    private void syncBounds() {
        {// player resources.
            for(Resources res : Resources.values()) {
                this.addResource(res, 0);
            }
        }
    }

    /**
     * sync data to client side.
     * {@link #init()}
     */
    public void syncToClient() {
        {// player resources.
            for(Resources res : Resources.values()) {
                this.sendResourcePacket(player, res);
            }
        }
//        {// card inventory.
//            for(int i = 0; i <= Resources.SLOT_NUM.max; ++ i) {
//                this.sendInventoryPacket(player, i, this.getItemAt(i).save(new CompoundNBT()));
//            }
//            this.setCurrentPos(this.getCurrentPos(), true);
//        }
        {// plants & zombies syncs.
            PVZAPI.get().getPAZTypes().forEach(plant -> {
                if(plant.getSummonCard().isPresent()) {//card cd.
                    player.getCooldowns().addCooldown(plant.getSummonCard().get(), this.getPlantCardCD(plant));
                }
                this.sendPAZLockPacket(player, plant);//lock.
            });
        }
//        {//wave.
//            this.invasion.sendAllWavePacket(player);
//        }
    }

    /*
     * Operations of Player Resources.
     */

    public int getResource(Resources res){
        return this.resources.get(res);
    }

    /**
     * check (min, max) and sync send packet.
     */
    public void setResource(Resources res, int num) {
        resources.put(res, num);
        this.addResource(res, 0);
    }

    public void addResource(Resources res, int num) {
        int now = resources.get(res);
        final int old = now;

        if(res == Resources.SUN_NUM) {
            now = Mth.clamp(now + num, 0, resources.get(Resources.MAX_SUN_NUM));
            resources.put(Resources.SUN_NUM, now);
        } else if(res == Resources.ENERGY_NUM) {
            now = Mth.clamp(now + num, 0, resources.get(Resources.MAX_ENERGY_NUM));
            resources.put(Resources.ENERGY_NUM, now);
        } else{
            now = Mth.clamp(now + num, res.min, res.max);
            resources.put(res, now);

            if(res == Resources.TREE_LVL) {
                this.addResource(Resources.SUN_NUM, 0);
                if(player instanceof ServerPlayer){
                    if(old != now){
                        MinecraftForge.EVENT_BUS.post(new PlayerLevelChangeEvent(player, old, now));
                    }
                }
//            } else if(res == Resources.MISSION_VALUE){
//                if(num > 0 && this.getResource(Resources.MISSION_TYPE) == MissionManager.MissionType.INSTANT_KILL.ordinal()){
//                    ++ this.invasion.killInSecond;
//                }
//            } else if(res == Resources.MISSION_FINISH_TIME){
//                if(player instanceof ServerPlayerEntity){
//                    InvasionMissionTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
//                }
            }
        }

        if(player instanceof ServerPlayer){
            ResourceTrigger.INSTANCE.trigger((ServerPlayer) player, res, now);
        }

        this.sendResourcePacket(player, res);
    }

    private void sendResourcePacket(Player player, Resources res){
        if (player instanceof ServerPlayer) {
            PVZPacketHandler.sendToClient((ServerPlayer) player, new PlayerStatsPacket(res.ordinal(), resources.get(res)));
        }
    }

    /*
     * Operation about plant card CD.
     * just store data, no need to manually sync to client.
     */

    public void setPAZCardCD(IPAZType plant, int tick) {
        this.pazCardCD.put(plant, tick);
    }

    public int getPAZCardCoolDown(IPAZType plant) {
        return this.pazCardCD.get(plant);
    }

    public void setPAZCardBar(IPAZType plant, float bar) {
        this.pazCardBar.put(plant, bar);
    }

    public float getPAZCardBarLength(IPAZType plant) {
        return this.pazCardBar.get(plant);
    }

    public int getPlantCardCD(IPAZType plant) {
        return (int) (this.pazCardBar.get(plant) * this.pazCardCD.get(plant));
    }

    public void saveSummonCardCD(SummonCardItem item, int cd) {
        this.setPAZCardCD(item.type, cd);
        this.setPAZCardBar(item.type, 1);
    }

    /**
     * {@link com.hungteen.pvz.common.event.PVZPlayerEvents#tickPlayer(TickEvent.PlayerTickEvent)}
     */
    public void loadSummonCardCDs() {
        PVZAPI.get().getPAZTypes().forEach(paz -> {
            paz.getSummonCard().ifPresent(item -> {
                this.player.getCooldowns().addCooldown(item, this.getPlantCardCD(paz));
            });
        });
    }

    /*
     * Operation about plant lock.
     */

    public void setPAZLocked(IPAZType plant, boolean is) {
        final boolean old = this.isPAZLocked(plant);
        this.pazLocked.put(plant, is);
        if(old != is) {
            this.sendPAZLockPacket(player, plant);
        }
    }

    public boolean isPAZLocked(IPAZType plant) {
        return this.pazLocked.getOrDefault(plant, true);
    }

    private void sendPAZLockPacket(Player player, IPAZType plant){
        if (player instanceof ServerPlayer) {
            PVZPacketHandler.sendToClient((ServerPlayer) player, new PAZStatsPacket(PAZStatsPacket.PAZPacketTypes.UNLOCK, plant.getIdentity(), this.isPAZLocked(plant)));
        }
    }

}
