package com.hungteen.pvz.common.capability.player;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.events.PlayerLevelChangeEvent;
import com.hungteen.pvz.common.advancement.trigger.ResourceTrigger;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.PlayerStatsPacket;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.PacketDistributor;

import java.util.EnumMap;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 13:05
 **/
public class PlayerDataManager {

    private final Player player;
    /* player resources */
    private final EnumMap<Resources, Integer> resources = new EnumMap<>(Resources.class);

    public PlayerDataManager(Player player){
        this.player = player;
        {// init player resources.
            for(Resources res : Resources.values()) {
                resources.put(res, Resources.getInitialValue(res));
            }
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
     * {@link PlayerEventHandler#onPlayerLogin(PlayerEntity)}
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
//        {// plants & zombies syncs.
//            PVZAPI.get().getPAZs().forEach(plant -> {
//                if(plant.getSummonCard().isPresent()) {//card cd.
//                    player.getCooldowns().addCooldown(plant.getSummonCard().get(), this.getPlantCardCD(plant));
//                }
//                this.sendPAZLockPacket(player, plant);//lock.
//            });
//        }
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
        if(res == Resources.TREE_LVL) {
            resources.put(Resources.TREE_XP, 0);
            this.addResource(Resources.TREE_XP, 0);
        }
        this.addResource(res, 0);
    }

    public void addResource(Resources res, int num) {
        int now = resources.get(res);
        final int old = now;

        if(res == Resources.TREE_XP) {
            addTreeXp(now, num);
        } else if(res == Resources.SUN_NUM) {
            now = Mth.clamp(now + num, 0, PlayerUtil.getPlayerMaxSunNum(resources.get(Resources.TREE_LVL)));
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

    /**
     * add tree xp and maxLevel up.
     */
    private void addTreeXp(int now, int num) {
        int lvl = resources.get(Resources.TREE_LVL);
        if(num > 0) {
            int req = PlayerUtil.getPlayerLevelUpXp(lvl);
            while(lvl < Resources.TREE_LVL.max && num + now >= req) {
                num -= req - now;
                this.addResource(Resources.TREE_LVL, 1);
                now = 0;
                req = PlayerUtil.getPlayerLevelUpXp(lvl);
            }
            resources.put(Resources.TREE_XP, num + now);
        } else {
            num = - num;
            while(lvl > 1 && num > now) {
                num -= now;
                -- lvl;
                now = PlayerUtil.getPlayerLevelUpXp(lvl);
                this.addResource(Resources.TREE_LVL, - 1);
            }
            resources.put(Resources.TREE_XP, Math.max(now - num, 0));
        }
    }

    private void sendResourcePacket(Player player, Resources res){
        if (player instanceof ServerPlayer) {
            PVZPacketHandler.sendToClient((ServerPlayer) player, new PlayerStatsPacket(res.ordinal(), resources.get(res)));
        }
    }

}
