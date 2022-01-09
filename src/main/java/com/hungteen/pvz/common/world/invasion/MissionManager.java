package com.hungteen.pvz.common.world.invasion;

import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.utils.others.WeightList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.Random;

public class MissionManager {

    public static final int MAX_MISSION_STAGE = 5;
    public static final int KILL_IN_SECOND = 10;
    private static final int[] KILL_MISSIONS = new int[]{50, 100, 200, 300, 500};
    private static final int[] INSTANT_KILL_MISSIONS = new int[]{10, 20, 40, 60, 80};
    private static final int[] COLLECT_SUN_MISSIONS = new int[]{5000, 10000, 15000, 20000, 25000};

    public static void offerMission(PlayerEntity player){
        final MissionType type = getMission(player.getRandom());
        PlayerUtil.getOptManager(player).ifPresent(l -> l.resetMission(type));
    }

    /**
     * tick each second.
     */
    public static void tickMission(World world){
        PlayerUtil.getServerPlayers(world).stream().filter(player -> player.tickCount % 20 == 5).forEach(player -> {
            final MissionType type = getPlayerMission(player);
            if(type != MissionType.EMPTY){
                final int now = PlayerUtil.getResource(player, Resources.MISSION_VALUE);
                int stage = PlayerUtil.getResource(player, Resources.MISSION_STAGE);
                int require = getRequireMissionValue(type, stage);
                while (stage < MAX_MISSION_STAGE && now >= require){
                    rewardPlayer(player, type, stage);
                    ++ stage;
                    require = getRequireMissionValue(type, stage);
                }
                if(type == MissionType.INSTANT_KILL){
                    PlayerUtil.getOptManager(player).ifPresent(l -> l.updateKillQueue());
                }
                if(stage >= MAX_MISSION_STAGE){
                    removeMission(player);
                } else {
                	PlayerUtil.setResource(player, Resources.MISSION_STAGE, stage);
                }
            }
        });
    }

    public static void removeMission(PlayerEntity player){
        PlayerUtil.getOptManager(player).ifPresent(l -> l.clearMission());
    }

    public static void rewardPlayer(PlayerEntity player, MissionType type, int stage){
        PlayerUtil.sendMsgTo(player, new TranslationTextComponent("invasion.pvz.mission.finish", stage));
        if(stage == 0 || stage == 2){
            rewardMoney(player, stage == 0 ? 250 : 500);
        } else if(stage == 1 || stage == 3){
            rewardLottery(player, 5);
        } else{
            rewardJewel(player, player.getRandom().nextInt(1) + 1);
        }
    }
    
    private static void rewardMoney(PlayerEntity player, int amount) {
    	PlayerUtil.addResource(player, Resources.MONEY, amount);
        PlayerUtil.playClientSound(player, SoundRegister.SUN_PICK.get());
    }

    private static void rewardJewel(PlayerEntity player, int amount){
        PlayerUtil.addResource(player, Resources.GEM_NUM, amount);
        PlayerUtil.playClientSound(player, SoundRegister.JEWEL_PICK.get());
    }

//    private static void rewardCard(PlayerEntity player, int count){
//        for(int i = 0; i < count; ++ i){
//            Optional.ofNullable(InvasionManager.getSpawnInvasion()).ifPresent(type -> {
//                LotteryTypeLoader.getLotteryType(type.getBonusResource()).ifPresent(lotteryType -> {
//                    final SlotMachineTileEntity.SlotType slotType = lotteryType.getSlotType(player.getRandom());
//                    if(slotType.getStack().isPresent()){
//                        player.addItem(slotType.getStack().get());
//                    } else{
//                        rewardJewel(player, 1);
//                    }
//                });
//            });
//        }
//    }

    private static void rewardLottery(PlayerEntity player, int amount){
        PlayerUtil.addResource(player, Resources.LOTTERY_CHANCE, amount);
        PlayerUtil.playClientSound(player, SoundRegister.SLOT_MACHINE.get());
    }

    public static MissionType getMission(Random rand){
        WeightList<MissionType> list = new WeightList<>();
        for(MissionType type : MissionType.values()){
            if(type.weight != 0){
                list.addItem(type, type.weight);
            }
        }
        return list.getRandomItem(rand).get();
    }

    public static MissionType getPlayerMission(PlayerEntity player){
        final PlayerDataManager manager = PlayerUtil.getManager(player);
        if(manager != null){
            return MissionType.values()[manager.getResource(Resources.MISSION_TYPE)];
        }
        return MissionType.EMPTY;
    }

    public static int getRequireMissionValue(MissionType type, int stage){
        if(stage >= 0 && stage < MAX_MISSION_STAGE){
            switch (type){
                case KILL: return KILL_MISSIONS[stage];
                case INSTANT_KILL: return INSTANT_KILL_MISSIONS[stage];
                case COLLECT_SUN: return COLLECT_SUN_MISSIONS[stage];
            }
        }
        return 9999999;
    }

    public enum MissionType{
        EMPTY(0),
        KILL(10),
        INSTANT_KILL(8),
        COLLECT_SUN(5);

        public final int weight;

        MissionType(int weight){
            this.weight = weight;
        }
    }
}
