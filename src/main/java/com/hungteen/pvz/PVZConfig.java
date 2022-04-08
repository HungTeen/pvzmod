package com.hungteen.pvz;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 20:55
 *
 * configurations of pvz mod.
 **/
public class PVZConfig {

    private static Common COMMON_CONFIG;
    private static Client CLIENT_CONFIG;

    public static class Common {

        public Common(ForgeConfigSpec.Builder builder) {
            /* Invasion Settings */
            builder.comment("Settings about invasion.").push("Invasion Settings");
            {
//                InvasionSettings.SafeDayLength = builder
//                        .translation("config.pvz.invasion.safe_day")
//                        .comment("If you set to 5, then the first 5 * 20 minutes of the world will not have any zombie invasion event.")
//                        .defineInRange("SafeDayLength", 2, 0, 1000000);
//
//                InvasionSettings.InvasionIntervalLength = builder
//                        .translation("config.pvz.invasion.interval")
//                        .comment("The interval day length between each invasion.")
//                        .defineInRange("InvasionIntervalLength", 2, 0, 1000000);
//
//                InvasionSettings.ShowEventMessages = builder
//                        .translation("config.pvz.invasion.show_message")
//                        .comment("If true, you will receive detail message about each event when zombie invasion happened.")
//                        .define("ShowEventMessages", true);
//
//                InvasionSettings.EnableHugeWave = builder
//                        .translation("config.pvz.invasion.enable_wave")
//                        .comment("If true, players will be invaded by a huge wave of zombies in zombie invasion day")
//                        .define("EnableHugeWave", true);
//
//                InvasionSettings.MaxSpawnEachPlayer = builder
//                        .translation("config.pvz.invasion.spawn_count")
//                        .comment("how many amount of entity will spawn to player.")
//                        .defineInRange("MaxSpawnCount", 50, 1, 1000);
//
//                InvasionSettings.MaxSpawnRange = builder
//                        .translation("config.pvz.invasion.spawn_range")
//                        .comment("how far can entity spawn in invasion.")
//                        .defineInRange("MaxSpawnRange", 50, 1, 100);

            }
            builder.pop();

            /* Challenge Settings */
            builder.comment("Settings about challenge").push("Challenge Settings");
            {
//                ChallengeSettings.EnableChallenge = builder
//                        .comment("Turn to false, no new challenge will happen, and old challenges will be clear.")
//                        .translation("config.pvz.enable_challenge")
//                        .define("EnableChallenge", true);
//
//                ChallengeSettings.ChallengeWaitTime = builder
//                        .comment("how many ticks will challenge wait to remove, when there is no player in it.")
//                        .translation("config.pvz.challenge_wait_time")
//                        .defineInRange("ChallengeWaitTime", 400, 1, 1000000);
//
//                ChallengeSettings.ChallengeRange = builder
//                        .comment("how far will a player join the raid.")
//                        .translation("config.pvz.challenge_range")
//                        .defineInRange("ChallengeRange", 50, 1, 1000);
            }
            builder.pop();

            //World Settings.
            builder.comment("Settings about global rules.").push("Rule Settings");
            {
//                RuleSettings.CanSpawnDefaultMonster = builder
//                        .translation("config.pvz.rule.spawn_monster")
//                        .comment("if turn to false, there will have no monster of other monsters spawn in overworld except pvz zombies.")
//                        .define("CanSpawnDefaultMonster", true);
//
//                RuleSettings.GiveBeginnerReward = builder
//                        .translation("config.pvz.rule.beginner_reward")
//                        .comment("If you set it true, you will get some basic plantcards when you first join world.")
//                        .define("GiveBeginnerReward", false);

                RuleSettings.AllowNaturalTurnOrigin = builder
                        .translation("config.pvz.rule.turn_origin")
                        .comment("If turn to false, saplings no longer to grow to Origin Ore naturally, except there is a block above it.")
                        .define("AllowNaturalTurnOrigin", true);

                RuleSettings.TeamAttack = builder
                        .translation("config.pvz.rule.team_attack")
                        .comment("if turn to true, when plant's owner is in a team, the plant will attack the entity from other team(include players).")
                        .define("PlantAttackTeam", false);

                RuleSettings.LimitPlantCount = builder
                        .translation("config.pvz.rule.plant_count")
                        .comment("how many plants can you place in 30 x 30 area without increasing cost.")
                        .defineInRange("LimitPlantCount", 50, 10, 1000);

                RuleSettings.KeepSunWhenDie = builder
                        .translation("config.pvz.rule.keep_sun")
                        .comment("if turn to true, player will keep its sun after death.")
                        .define("KeepSunWhenDie", false);
//
//                RuleSettings.MaxDamageLimit = builder
//                        .translation("config.pvz.rule.max_damage")
//                        .comment("how many damage could plants and zombies deal to other living entity.")
//                        .defineInRange("MaxDamageLimit", 20, 0, 100000);

                RuleSettings.BulletIgnoreGroup = builder
                        .translation("config.pvz.rule.bullet_ignore_group")
                        .comment("if turn to true, every entity will always damage by bullet, no matter which group you are.")
                        .define("BulletIgnoreGroup", false);
            }
            builder.pop();

            //World Settings
            builder.comment("Settings about world.").push("World Settings");
            {

                builder.comment("Settings about the biome gen.").push("Biome Settings");
                {
                    WorldSettings.GenZenGardenChance = builder
                            .translation("config.pvz.world.zen_garden_chance")
                            .comment("the gen chance of Zen Garden biome(the larger the more chance to see it).")
                            .defineInRange("GenZenGardenChance", 20, 1, 10000);
                }
                builder.pop();

                builder.comment("Settings about the structure gen.").push("Structure Settings");
                {
//                    WorldSettings.DaveVillaDistance = builder
//                            .translation("config.pvz.world.dave_villa_distance")
//                            .comment("the distance value between dave villa.")
//                            .defineInRange("DaveVillaDistance", 40, 1, 1000);
//
//                    WorldSettings.BucketHouseDistance = builder
//                            .translation("config.pvz.world.bucket_house_distance")
//                            .comment("the distance value between bucket house.")
//                            .defineInRange("BucketHouseDistance", 36, 1, 1000);
//
//                    WorldSettings.DolphinHouseDistance = builder
//                            .translation("config.pvz.world.dolphin_house_distance")
//                            .comment("the distance value between dolphin house.")
//                            .defineInRange("DolphinHouseDistance", 32, 1, 1000);
//
//                    WorldSettings.GraveHouseDistance = builder
//                            .translation("config.pvz.world.grave_house_distance")
//                            .comment("the distance value between grave house.")
//                            .defineInRange("GraveHouseDistance", 28, 1, 1000);
//
//                    WorldSettings.SunTempleDistance = builder
//                            .translation("config.pvz.world.sun_temple_distance")
//                            .comment("the distance value between sun temple.")
//                            .defineInRange("SunTempleDistance", 36, 1, 1000);
//
//                    WorldSettings.YetiHouseDistance = builder
//                            .translation("config.pvz.world.yeti_house_distance")
//                            .comment("the distance value between yeti house.")
//                            .defineInRange("YetiHouseDistance", 28, 1, 1000);
                }
                builder.pop();

                builder.comment("Settings about the ore gen.").push("Ore Settings");
                {

                    WorldSettings.GenOriginOreCount = builder
                            .translation("config.pvz.world.origin_ore_count")
                            .comment("how many origin ore in overworld(the larger the more chance to see it).")
                            .defineInRange("OriginOreGenWeight", 5, 1, 10000);

                    WorldSettings.GenAmethystOreCount = builder
                            .translation("config.pvz.world.amethyst_ore_count")
                            .comment("how many amethyst ore in the end(the larger the more chance to see it).")
                            .defineInRange("GenAmethystOreCount", 12, 1, 10000);
                }
                builder.pop();

                builder.comment("The Spawn Weight of entity.").push("EntitySpawnWeight");
                {
//                    WorldSettings.SunSpawnWeight = builder
//                            .translation("config.pvz.world.sun_weight")
//                            .comment("spawn weight of Sun.")
//                            .defineInRange("SunSpawnWeight", 50, 1, 200);

//                    WorldSettings.LavaZombieSpawnWeight = builder
//                            .translation("config.pvz.world.lava_zombie_weight")
//                            .comment("spawn weight of LavaZombie at nether.")
//                            .defineInRange("LavaZombieSpawnWeight", 5, 1, 200);
//
//                    WorldSettings.GigaTombStoneSpawnWeight = builder
//                            .translation("config.pvz.world.giga_tomb_weight")
//                            .comment("spawn weight of GigaTombStone at night in overworld.")
//                            .defineInRange("GigaTombStoneSpawnWeight", 5, 1, 200);
//
//                    WorldSettings.YetiZombieSpawnWeight = builder
//                            .translation("config.pvz.world.yeti_zombie_weight")
//                            .comment("spawn weight of YetiZombie in overworld when thunder.")
//                            .defineInRange("YetiZombieSpawnWeight", 1, 1, 200);
                }
                builder.pop();
            }
            builder.pop();

            //Entity Settings
            builder.comment("Settings about entities.").push("Entity Settings");
            {
                builder.comment("Settings about players.").push("Player Settings");
                {
                    EntitySettings.PlayerInitialGroup = builder
                            .translation("config.pvz.player.initial_group")
                            .comment("Player Initial Group When they join the world for the first time(-2 means other monsters, -1 means zombies, 0 means neutral creatures, 1 means plants and 2 means other guards).")
                            .defineInRange("PlayerInitialGroup", 1, -2, 2);

                    EntitySettings.PlayerBaseSunAmount = builder
                            .translation("config.pvz.player.initial_sun")
                            .comment("players' base sun amount, it will increase when tree maxLevel increasing.")
                            .defineInRange("PlayerInitialSun", 950, 100, 100000);
                }
                builder.pop();

                builder.comment("Settings about zombies.").push("Zombie Settings");
                {
//                    EntitySettings.ZombieSetting.ZombieSuperChance = builder
//                            .translation("config.pvz.zombie.super_chance")
//                            .comment("the spawn chance of zombie with plant energy(the bigger,the more chance it spawn).")
//                            .defineInRange("ZombieSuperChance", 1, 0, 40);
//
//                    EntitySettings.ZombieSetting.ZombieSunChance = builder
//                            .translation("config.pvz.zombie.sun_chance")
//                            .comment("the spawn chance of zombie with sun layer(the bigger,the more chance it spawn).")
//                            .defineInRange("ZombieSunChance", 1, 0, 40);
//
//                    EntitySettings.DropChanceMultiper = builder
//                            .translation("config.pvz.zombie.drop_multiper")
//                            .comment("the drop chance of coin when zombie die(the bigger,the less chance it spawn).")
//                            .defineInRange("ZombieDropMultiper", 10, 3, 100);

                    EntitySettings.ZombieSetting.DropHandChance = builder
                            .translation("config.pvz.zombie.drop_hand_chance")
                            .comment("enable zombies to drop hands when they got hurt(turn 0 to disable).")
                            .defineInRange("DropHandChance", 0.25, 0, 1);

                    EntitySettings.ZombieSetting.DropHeadChance = builder
                            .translation("config.pvz.zombie.drop_head_chance")
                            .comment("enable zombies to drop heads when they got hurt(turn 0 to disable).")
                            .defineInRange("DropHeadChance", 0.04, 0, 1);

                    EntitySettings.ZombieSetting.EnableDropBody = builder
                            .translation("config.pvz.zombie.drop_body")
                            .comment("enable zombies to drop body when they died(turn false to disable).")
                            .define("EnableDropBody", true);

                }
                builder.pop();

                builder.comment("Settings about plants.").push("Plant Settings");
                {
//                    EntitySettings.PlantSetting.StrangeCatCount = builder
//                            .translation("config.pvz.plant.strange_cat_count")
//                            .comment("the max number StrangeCats can copy themselves in a range of 20 * 20.")
//                            .defineInRange("StrangeCatCount", 10, 0, 100);

                }
                builder.pop();

                builder.comment("The Max live time for Entity like sun.").push("EntityLiveTime");
                {
                    EntitySettings.EntityLiveTick.SunLiveTick = builder
                            .translation("config.pvz.entity.sun_live_tick")
                            .comment("how many ticks can the sun entity live.")
                            .defineInRange("SunLiveTick", 500, 1, 100000);

                    EntitySettings.EntityLiveTick.CoinLiveTick = builder
                            .translation("config.pvz.entity.coin_live_tick")
                            .comment("how many ticks can the coin entity live.")
                            .defineInRange("CoinLiveTick", 500, 1, 100000);

                    EntitySettings.EntityLiveTick.JewelLiveTick = builder
                            .translation("config.pvz.entity.jewel_live_tick")
                            .comment("how many ticks can the jewel entity live.")
                            .defineInRange("JewelLiveTick", 600, 1, 100000);

                    EntitySettings.EntityLiveTick.PlantFoodLiveTick = builder
                            .translation("config.pvz.entity.plant_food_live_tick")
                            .comment("how many ticks can the plant food entity live.")
                            .defineInRange("PlantFoodLiveTick", 500, 1, 100000);
//
//                    EntitySettings.EntityLiveTick.YetiLiveTick = builder
//                            .translation("config.pvz.entity.yeti_live_tick")
//                            .comment("how many ticks can yeti entity live(how long will it stay).")
//                            .defineInRange("YetiLiveTick", 2400, 1, 1000000);
//
//                    EntitySettings.EntityLiveTick.BowlingLiveTick = builder
//                            .translation("config.pvz.entity.bowling_live_tick")
//                            .comment("how many ticks can bowling entity live.")
//                            .defineInRange("BowlingLiveTick", 300, 1, 1000000);
//
//                    EntitySettings.EntityLiveTick.LawnMowerLiveTick = builder
//                            .translation("config.pvz.entity.lawn_mower_live_tick")
//                            .comment("how many ticks can lawnmower entity live.")
//                            .defineInRange("LawnMowerLiveTick", 200, 1, 1000000);
//
//                    EntitySettings.EntityLiveTick.ElementBallLiveTick = builder
//                            .translation("config.pvz.entity.element_ball_live_tick")
//                            .comment("how many ticks can element ball entity live.")
//                            .defineInRange("ElementBallLiveTick", 600, 1, 1000000);
                }
                builder.pop();
            }
            builder.pop();
            //Block Settings
            builder.comment("Settings about blocks.").push("Block Settings");
            {
                BlockSettings.OriginEffectChance = builder
                        .translation("config.pvz.block.origin_effect_chance")
                        .comment("The chance to get Essence Ore from Origin Block's effect")
                        .defineInRange("OriginEffectChance", 0.25, 0, 1);
//
//                BlockSettings.SaplingTurnChance = builder
//                        .translation("config.pvz.block.sapling_turn_chance")
//                        .comment("The chance when sapling turn to origin ore")
//                        .defineInRange("SaplingTurnChance", 0.15, 0, 1);
//
//                BlockSettings.AmethystAngerChance = builder
//                        .translation("config.pvz.block.amethyst_anger_chance")
//                        .comment("The chance of anger nearby enderman when break amethyst ore")
//                        .defineInRange("AmethystAngerChance", 0.4, 0, 1);
//
//                builder.comment("Setting about break blocks.").push("Break Block Setting");
//                {
//                    BlockSettings.PeaDropChance = builder
//                            .translation("config.pvz.block.pea_drop_chance")
//                            .comment("the drop chance of pea when you break grass")
//                            .defineInRange("PeaDropChance", 0.05, 0, 1);
//
//                    BlockSettings.CabbageDropChance = builder
//                            .translation("config.pvz.block.cabbage_drop_chance")
//                            .comment("the drop chance of cabbage when you break grass")
//                            .defineInRange("CabbageDropChance", 0.025, 0, 1);
//                }
//                builder.pop();
            }
            builder.pop();
            //Item Settings
            builder.comment("Settings about items.").push("Item Settings");
            {
//                ItemSettings.JackBoxSurpriseChance = builder
//                        .translation("config.pvz.item.jack_surprise_chance")
//                        .comment("The chance when player got a surprise when use jack box.the bigger the value is,the lower chance you get.(more specificly 1/x)")
//                        .defineInRange("JackBoxSurpriseChance", 10, 1, 1000000);
            }
            builder.pop();
        }

        public InvasionSettings InvasionSettings = new InvasionSettings();
        public ChallengeSettings ChallengeSettings = new ChallengeSettings();
        public RuleSettings RuleSettings = new RuleSettings();
        public WorldSettings WorldSettings = new WorldSettings();
        public EntitySettings EntitySettings = new EntitySettings();
        public BlockSettings BlockSettings = new BlockSettings();
        public ItemSettings ItemSettings = new ItemSettings();

        public static class InvasionSettings {
            /* misc */
            public ForgeConfigSpec.IntValue SafeDayLength;
            public ForgeConfigSpec.IntValue InvasionIntervalLength;
            public ForgeConfigSpec.BooleanValue ShowEventMessages;
            public ForgeConfigSpec.BooleanValue EnableHugeWave;
            public ForgeConfigSpec.IntValue MaxSpawnEachPlayer;
            public ForgeConfigSpec.IntValue MaxSpawnRange;

        }

        public static class ChallengeSettings{
            public ForgeConfigSpec.BooleanValue EnableChallenge;
            public ForgeConfigSpec.IntValue ChallengeWaitTime;
            public ForgeConfigSpec.IntValue ChallengeRange;
        }

        public static class RuleSettings {
            public ForgeConfigSpec.BooleanValue CanSpawnDefaultMonster;
            public ForgeConfigSpec.BooleanValue GiveBeginnerReward;
            public ForgeConfigSpec.BooleanValue AllowNaturalTurnOrigin;
            public ForgeConfigSpec.BooleanValue TeamAttack;
            public ForgeConfigSpec.IntValue LimitPlantCount;
            public ForgeConfigSpec.BooleanValue KeepSunWhenDie;
            public ForgeConfigSpec.IntValue MaxDamageLimit;
            public ForgeConfigSpec.BooleanValue BulletIgnoreGroup;
        }

        public static class WorldSettings {

            /* biome gen */
            public ForgeConfigSpec.IntValue GenZenGardenChance;

            /* structure distance */
            public ForgeConfigSpec.IntValue DaveVillaDistance;
            public ForgeConfigSpec.IntValue BucketHouseDistance;
            public ForgeConfigSpec.IntValue DolphinHouseDistance;
            public ForgeConfigSpec.IntValue GraveHouseDistance;
            public ForgeConfigSpec.IntValue SunTempleDistance;
            public ForgeConfigSpec.IntValue YetiHouseDistance;

            /* ore gen */
            public ForgeConfigSpec.IntValue GenAmethystOreCount;
            public ForgeConfigSpec.IntValue GenOriginOreCount;

            /* entity spawn */
            public ForgeConfigSpec.IntValue SunSpawnWeight;
            public ForgeConfigSpec.IntValue ZombieDolphinSpawnWeight;
            public ForgeConfigSpec.IntValue FoodieZombieSpawnWeight;
            public ForgeConfigSpec.IntValue LavaZombieSpawnWeight;
            public ForgeConfigSpec.IntValue GigaTombStoneSpawnWeight;
            public ForgeConfigSpec.IntValue YetiZombieSpawnWeight;
        }

        public static class EntitySettings {

            public EntityLiveTick EntityLiveTick = new EntityLiveTick();
            public ZombieSetting ZombieSetting = new ZombieSetting();
            public PlantSetting PlantSetting = new PlantSetting();

            public ForgeConfigSpec.IntValue PlayerInitialGroup;
            public ForgeConfigSpec.IntValue PlayerBaseSunAmount;
            public ForgeConfigSpec.IntValue DropChanceMultiper;

            public static class ZombieSetting {
                public ForgeConfigSpec.IntValue ZombieSuperChance;
                public ForgeConfigSpec.IntValue ZombieSunChance;
                public ForgeConfigSpec.DoubleValue DropHandChance;
                public ForgeConfigSpec.DoubleValue DropHeadChance;
                public ForgeConfigSpec.BooleanValue EnableDropBody;
            }

            public static class PlantSetting {
                public ForgeConfigSpec.IntValue StrangeCatCount;
            }

            public static class EntityLiveTick {
                public ForgeConfigSpec.IntValue SunLiveTick;
                public ForgeConfigSpec.IntValue CoinLiveTick;
                public ForgeConfigSpec.IntValue JewelLiveTick;
                public ForgeConfigSpec.IntValue PlantFoodLiveTick;
                public ForgeConfigSpec.IntValue YetiLiveTick;
                public ForgeConfigSpec.IntValue BowlingLiveTick;
                public ForgeConfigSpec.IntValue LawnMowerLiveTick;
                public ForgeConfigSpec.IntValue ElementBallLiveTick;
            }

        }

        public static class BlockSettings {
            public ForgeConfigSpec.DoubleValue OriginEffectChance;
            public ForgeConfigSpec.DoubleValue SaplingTurnChance;

            /* break block */
            public ForgeConfigSpec.DoubleValue PeaDropChance;
            public ForgeConfigSpec.DoubleValue CabbageDropChance;

            /* misc */
            public ForgeConfigSpec.DoubleValue AmethystAngerChance;
        }

        public static class ItemSettings {
            public ForgeConfigSpec.IntValue JackBoxSurpriseChance;
        }

    }

    public static class Client {

        public Client(ForgeConfigSpec.Builder builder) {
            builder.comment("Overlay Settings").push("Overlay Settings");
            {
                OverlaySettings.RenderSunBar = builder
                        .comment("turn to false to cancel the display of sun amount bar.")
                        .translation("config.pvz.overlay.sun_bar")
                        .define("RenderSunBar", true);

                OverlaySettings.RenderMoneyBar = builder
                        .comment("turn to false to cancel the display of money bar.")
                        .translation("config.pvz.overlay.money_bar")
                        .define("RenderMoneyBar", true);

                OverlaySettings.RenderGemBar = builder
                        .comment("turn to false to cancel the display of gem bar.")
                        .translation("config.pvz.overlay.gem_bar")
                        .define("RenderGemBar", true);

                OverlaySettings.RenderTreeLevel = builder
                        .comment("turn to false to cancel the display of tree maxLevel bar.")
                        .translation("config.pvz.overlay.level_bar")
                        .define("RenderTreeLevel", true);

                OverlaySettings.RenderPlantFood = builder
                        .comment("turn to false to cancel the display of plant food bar.")
                        .translation("config.pvz.overlay.energy_bar")
                        .define("RenderPlantFood", true);

                OverlaySettings.RenderInvasionProgress = builder
                        .comment("turn to false to cancel the display of invasion progress.")
                        .translation("config.pvz.overlay.invasion_bar")
                        .define("RenderInvasionProgress", true);

//                OverlaySettings.RenderFog = builder
//                        .comment("turn to false to cancel the display of fog.")
//                        .translation("config.pvz.overlay.fog")
//                        .define("RenderFog", true);

//                OverlaySettings.RenderCardSlots = builder
//                        .comment("turn to false to cancel the display of summon card slots.")
//                        .translation("config.pvz.overlay.card_slot")
//                        .define("RenderCardSlots", true);

            }
            builder.pop();

            builder.comment("Other Render Settings").push("Other Render Settings");
            {
                OtherSettings.ShowPVZMainMenu = builder
                        .translation("config.pvz.client.pvz_menu")
                        .comment("show pvz main menu")
                        .define("ShowPVZMainMenu", true);
            }
            builder.pop();
        }

        public OverlaySettings OverlaySettings = new OverlaySettings();
        public OtherSettings OtherSettings = new OtherSettings();

        public static class OverlaySettings {
            public ForgeConfigSpec.BooleanValue RenderSunBar;
            public ForgeConfigSpec.BooleanValue RenderMoneyBar;
            public ForgeConfigSpec.BooleanValue RenderGemBar;
            public ForgeConfigSpec.BooleanValue RenderTreeLevel;
            public ForgeConfigSpec.BooleanValue RenderPlantFood;
            public ForgeConfigSpec.BooleanValue RenderInvasionProgress;
            public ForgeConfigSpec.BooleanValue RenderFog;
            public ForgeConfigSpec.BooleanValue RenderCardSlots;
        }

        public static class OtherSettings {
            public ForgeConfigSpec.BooleanValue ShowPVZMainMenu;
        }
    }

    /**
     * {@link PVZMod#PVZMod()}
     */
    public static void init(){
        {
            final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(PVZConfig.Common::new);
            ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, specPair.getRight());
            PVZConfig.COMMON_CONFIG = specPair.getLeft();
        }
        {
            final Pair<PVZConfig.Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(PVZConfig.Client::new);
            ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPair.getRight());
            PVZConfig.CLIENT_CONFIG = specPair.getLeft();
        }
    }

    /*
    Common configs.
     */
    public static double getOriginEffectChance(){
        return COMMON_CONFIG.BlockSettings.OriginEffectChance.get();
    }

    public static int getPlayerInitialGroup(){
        return COMMON_CONFIG.EntitySettings.PlayerInitialGroup.get();
    }

    public static boolean shouldKeepSunWhenDie(){
        return COMMON_CONFIG.RuleSettings.KeepSunWhenDie.get();
    }

    public static int getBaseSun(){
        return COMMON_CONFIG.EntitySettings.PlayerBaseSunAmount.get();
    }

    public static int getSunLiveTick(){
        return COMMON_CONFIG.EntitySettings.EntityLiveTick.SunLiveTick.get();
    }

    public static int getCoinLiveTick(){
        return COMMON_CONFIG.EntitySettings.EntityLiveTick.CoinLiveTick.get();
    }

    public static int getJewelLiveTick(){
        return COMMON_CONFIG.EntitySettings.EntityLiveTick.JewelLiveTick.get();
    }

    public static int getPlantFoodLiveTick(){
        return COMMON_CONFIG.EntitySettings.EntityLiveTick.PlantFoodLiveTick.get();
    }

    public static int getZenGardenWeight(){
        return COMMON_CONFIG.WorldSettings.GenZenGardenChance.get();
    }

//    public static int getSunSpawnWeight(){
//        return COMMON_CONFIG.WorldSettings.SunSpawnWeight.get();
//    }

    public static int getAmethystOreCount(){
        return COMMON_CONFIG.WorldSettings.GenAmethystOreCount.get();
    }

    public static int getOriginOreCount(){
        return COMMON_CONFIG.WorldSettings.GenOriginOreCount.get();
    }

    public static boolean bulletIngoreGroup(){
        return COMMON_CONFIG.RuleSettings.BulletIgnoreGroup.get();
    }

    public static double dropHandChance(){
        return COMMON_CONFIG.EntitySettings.ZombieSetting.DropHandChance.get();
    }

    public static double dropHeadChance(){
        return COMMON_CONFIG.EntitySettings.ZombieSetting.DropHeadChance.get();
    }

    public static boolean enableDropBody(){
        return COMMON_CONFIG.EntitySettings.ZombieSetting.EnableDropBody.get();
    }

    /*
    Client configs.
     */

    public static boolean renderSunBar(){
        return CLIENT_CONFIG.OverlaySettings.RenderSunBar.get();
    }

    public static boolean renderMoneyBar(){
        return CLIENT_CONFIG.OverlaySettings.RenderMoneyBar.get();
    }

    public static boolean renderGemBar(){
        return CLIENT_CONFIG.OverlaySettings.RenderGemBar.get();
    }

    public static boolean renderTreeLevel(){
        return CLIENT_CONFIG.OverlaySettings.RenderTreeLevel.get();
    }

    public static boolean renderPlantFood(){
        return CLIENT_CONFIG.OverlaySettings.RenderPlantFood.get();
    }

}