package com.hungteen.pvz;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.item.PVZSpawnEggItem;
import com.hungteen.pvz.item.armor.BucketArmorItem;
import com.hungteen.pvz.item.armor.ConeArmorItem;
import com.hungteen.pvz.item.armor.FootballArmorItem;
import com.hungteen.pvz.item.armor.GigaArmorItem;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.potion.PotionRecipeHandler;
import com.hungteen.pvz.register.BiomeRegister;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.CommonRegister;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.FeatureRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.KeyBindRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.PotionRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.register.StructureRegister;
import com.hungteen.pvz.register.TileEntityRegister;
import com.hungteen.pvz.utils.BiomeUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.ZombieUtil;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = PVZMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

	/**
	 * put all deferred register together
	 */
	public static void deferredRegister(IEventBus bus) {
		SoundRegister.SOUNDS.register(bus);
		ItemRegister.ITEMS.register(bus);
		BlockRegister.BLOCKS.register(bus);
		EntityRegister.ENTITY_TYPES.register(bus);
		ParticleRegister.PARTICLE_TYPES.register(bus);
		EffectRegister.EFFECTS.register(bus);
		BiomeRegister.BIOMES.register(bus);
		FeatureRegister.FEATURES.register(bus);
		StructureRegister.STRUCTURE_FEATURES.register(bus);
		TileEntityRegister.TILE_ENTITY_TYPES.register(bus);
		EnchantmentRegister.ENCHANTMENTS.register(bus);
		ContainerRegister.CONTAINER_TYPES.register(bus);
		PotionRegister.POTIONS.register(bus);
	}
	
	@SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent ev){
		ZombieUtil.initZombieMap();
		PlantUtil.initPlantMap();
    	CapabilityHandler.registerCapabilities();
    	PVZPacketHandler.init();
    	BiomeRegister.registerBiomes(ev);
    	ev.enqueueWork(() -> {
    		FeatureRegister.setupConfiguredFeatures();
    	});
    	PotionRecipeHandler.registerPotionRecipes();
    	TradeUtil.initTrades();
    	CommonRegister.registerCompostable();
    	BiomeUtil.initBiomeSet();
    }
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent evt) {
		ConeArmorItem.initArmorModel();
		BucketArmorItem.initArmorModel();
		FootballArmorItem.initArmorModel();
		GigaArmorItem.initArmorModel();
		KeyBindRegister.init();
	}
	
	/**
	 * Exists to work around a limitation with Spawn Eggs:
	 * Spawn Eggs require an EntityType, but EntityTypes are created AFTER Items.
	 * Therefore it is "impossible" for modded spawn eggs to exist.
	 * To get around this we have our own custom SpawnEggItem, but it needs
	 * some extra setup after Item and EntityType registration completes.
	 */
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
		PVZSpawnEggItem.initUnaddedEggs();
	}
}
