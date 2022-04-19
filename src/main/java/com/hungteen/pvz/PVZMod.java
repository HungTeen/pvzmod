package com.hungteen.pvz;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.client.particle.PVZParticles;
import com.hungteen.pvz.common.CommonProxy;
import com.hungteen.pvz.common.CommonRegister;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.common.advancement.AdvancementHandler;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.command.PVZCommandHandler;
import com.hungteen.pvz.common.effect.PVZEffects;
import com.hungteen.pvz.common.effect.PVZPotions;
import com.hungteen.pvz.common.enchantment.PVZEnchantments;
import com.hungteen.pvz.common.entity.PVZAttributes;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.impl.type.CardTypes;
import com.hungteen.pvz.common.impl.type.EssenceTypes;
import com.hungteen.pvz.common.impl.type.RankTypes;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.common.impl.type.zombie.PVZZombies;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.misc.PVZWoodType;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.recipe.PVZRecipeTypes;
import com.hungteen.pvz.common.recipe.PVZRecipes;
import com.hungteen.pvz.common.world.biome.PVZBiomes;
import com.hungteen.pvz.common.world.dimension.PVZDimensions;
import com.hungteen.pvz.common.world.feature.PVZFeatures;
import com.hungteen.pvz.common.world.spawn.SpawnRegister;
import com.hungteen.pvz.data.DataGenHandler;
import com.hungteen.pvz.utils.BiomeUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
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
        modBus.addListener(EventPriority.NORMAL, CapabilityHandler::registerCapabilities);
        modBus.addListener(EventPriority.NORMAL, PVZEntities::addEntityAttributes);
        modBus.addGenericListener(Item.class, PVZBlocks::registerBlockItem);
        modBus.addGenericListener(Item.class, PVZItems::registerCards);

        deferredRegister(modBus);

        //get forge event bus.
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addGenericListener(Entity.class, CapabilityHandler::attachCapabilities);
        forgeBus.addListener(EventPriority.NORMAL, PVZCommandHandler::init);
//        forgeBus.addListener(EventPriority.NORMAL, GenStructures::addDimensionalSpacing);
        forgeBus.addListener(EventPriority.HIGH, PVZBiomes::biomeModification);
//        forgeBus.addListener(EventPriority.NORMAL, PVZDataPackManager::addReloadListenerEvent);

        AdvancementHandler.init();
        PVZConfig.init();
        coreRegister();
    }

    public static void setUpComplete(FMLLoadCompleteEvent event) {
        PROXY.postInit();
    }

    public static void setUp(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        	PVZRecipeTypes.registerRecipeTypes();
            PVZBiomes.registerBiomes();
            PVZFeatures.registerFeatures();
//            PotionRecipeHandler.registerPotionRecipes();
            CommonRegister.registerCompostable();
            BiomeUtil.initBiomeSet();
            SpawnRegister.registerEntitySpawns();
            PVZDimensions.register();
        });

        PVZPacketHandler.init();
    }

    public static void setUpClient(FMLClientSetupEvent event) {
        PROXY.setUpClient();
        event.enqueueWork(() -> {
            PVZWoodType.register();
        });
    }

    /**
     * register paz stuff at {@link PVZMod#PVZMod()}.
     */
    public static void coreRegister() {
        EssenceTypes.EssenceType.register();
        RankTypes.RankType.register();
        CardTypes.CardType.register();
//        //register skills.
//        SkillTypes.SkillType.register();
        //register plants.
        PVZPlants.PVZPlantType.register();
//        CustomPlants.register();
//        MemePlants.register();
//        OtherPlants.register();
        //register zombies.
        PVZZombies.PVZZombieType.register();
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
        PVZBiomes.BIOMES.register(bus);
        PVZRecipes.RECIPE_SERIALIZERS.register(bus);
        PVZEnchantments.ENCHANTMENTS.register(bus);
        PVZEffects.EFFECTS.register(bus);
        PVZPotions.POTIONS.register(bus);
        PVZParticles.PARTICLE_TYPES.register(bus);
        PVZAttributes.ATTRIBUTES.register(bus);
//        FeatureRegister.FEATURES.register(bus);
//        StructureRegister.STRUCTURE_FEATURES.register(bus);
//        TileEntityRegister.TILE_ENTITY_TYPES.register(bus);
//        ContainerRegister.CONTAINER_TYPES.register(bus);
    }

}
