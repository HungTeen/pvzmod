package com.hungteen.pvz;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.common.CommonProxy;
import com.hungteen.pvz.common.PVZSounds;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.impl.EssenceTypes;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.data.DataGenHandler;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 19:57
 **/
@Mod(PVZMod.MOD_ID)
public class PVZMod {

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    // Mod ID.
    public static final String MOD_ID = "pvz";
    // Mod Version.
    public static final String MOD_VERSION = "0.7";
    // Proxy of Server and Client.
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public PVZMod() {
        //get mod event bus.
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(EventPriority.NORMAL, PVZMod::setUpComplete);
        modBus.addListener(EventPriority.NORMAL, PVZMod::setUp);
        modBus.addListener(EventPriority.NORMAL, PVZMod::setUpClient);
        modBus.addListener(EventPriority.NORMAL, DataGenHandler::dataGen);
        modBus.addGenericListener(Item.class, PVZBlocks::registerBlockItem);
        deferredRegister(modBus);

        //get forge event bus.
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
//        forgeBus.addListener(EventPriority.NORMAL, GenStructures::addDimensionalSpacing);
//        forgeBus.addListener(EventPriority.HIGH, BiomeRegister::biomeModification);
//        forgeBus.addListener(EventPriority.NORMAL, PVZDataPackManager::addReloadListenerEvent);

//        AdvancementHandler.init();
        PVZConfig.init();
        coreRegister();
    }

    public static void setUpComplete(FMLLoadCompleteEvent event) {
        PROXY.postInit();
    }

    public static void setUp(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
//            CapabilityHandler.registerCapabilities();
//            PVZPacketHandler.init();
//            BiomeRegister.registerBiomes(ev);
//            PotionRecipeHandler.registerPotionRecipes();
//            CommonRegister.registerCompostable();
//            BiomeUtil.initBiomeSet();
//            OriginBlock.updateRadiationMap();
        });
    }

    public static void setUpClient(FMLClientSetupEvent event) {
        PROXY.setUpClient();
    }

    /**
     * register paz stuff at {@link PVZMod#PVZMod()}.
     */
    public static void coreRegister() {
        //register essences.
        EssenceTypes.EssenceType.register();
//        //register ranks.
//        RankTypes.RankType.register();
//        //register skills.
//        SkillTypes.SkillType.register();
//        //register plants.
//        PVZPlants.register();
//        CustomPlants.register();
//        MemePlants.register();
//        OtherPlants.register();
//        //register zombies.
//        GrassZombies.register();
//        PoolZombies.register();
//        RoofZombies.register();
//        CustomZombies.register();
//        Zombotanies.register();
//        OtherZombies.register();
//        //register challenge.
//        ChallengeManager.registerChallengeStuff();
    }

    /**
     * {@link PVZMod#PVZMod()}.
     */
    public static void deferredRegister(IEventBus bus) {

        PVZItems.ITEMS.register(bus);
        PVZBlocks.BLOCKS.register(bus);
        PVZEntities.ENTITY_TYPES.register(bus);
        PVZSounds.SOUNDS.register(bus);
//        ParticleRegister.PARTICLE_TYPES.register(bus);
//        EffectRegister.EFFECTS.register(bus);
//        BiomeRegister.BIOMES.register(bus);
//        FeatureRegister.FEATURES.register(bus);
//        StructureRegister.STRUCTURE_FEATURES.register(bus);
//        TileEntityRegister.TILE_ENTITY_TYPES.register(bus);
//        EnchantmentRegister.ENCHANTMENTS.register(bus);
//        ContainerRegister.CONTAINER_TYPES.register(bus);
//        PotionRegister.POTIONS.register(bus);
//        RecipeRegister.RECIPE_SERIALIZERS.register(bus);
//        PVZAttributes.ATTRIBUTES.register(bus);
    }

}
