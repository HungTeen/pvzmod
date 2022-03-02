package com.hungteen.pvz;

import com.hungteen.pvz.client.particle.ParticleRegister;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.container.ContainerRegister;
import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.PVZAttributes;
import com.hungteen.pvz.common.impl.EssenceTypes;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.CustomPlants;
import com.hungteen.pvz.common.impl.plant.MemePlants;
import com.hungteen.pvz.common.impl.plant.OtherPlants;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.impl.zombie.*;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.misc.PVZSpawnEggItem;
import com.hungteen.pvz.common.CommonRegister;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.common.potion.PotionRecipeHandler;
import com.hungteen.pvz.common.potion.PotionRegister;
import com.hungteen.pvz.common.recipe.RecipeRegister;
import com.hungteen.pvz.common.tileentity.TileEntityRegister;
import com.hungteen.pvz.common.world.FeatureRegister;
import com.hungteen.pvz.common.world.biome.BiomeRegister;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import com.hungteen.pvz.common.world.structure.StructureRegister;
import com.hungteen.pvz.utils.BiomeUtil;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = PVZMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

	/**
	 * put all deferred register together.
	 * {@link PVZMod#PVZMod()}
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
		RecipeRegister.RECIPE_SERIALIZERS.register(bus);
		PVZAttributes.ATTRIBUTES.register(bus);
	}

	/**
	 * register paz stuff.
	 */
	public static void coreRegister() {
		//register essences.
		EssenceTypes.EssenceType.register();
		//register ranks.
		RankTypes.RankType.register();
		//register skills.
		SkillTypes.SkillType.register();
		//register plants.
		PVZPlants.register();
		CustomPlants.register();
		MemePlants.register();
		OtherPlants.register();
		//register zombies.
		GrassZombies.register();
		PoolZombies.register();
		RoofZombies.register();
		CustomZombies.register();
		Zombotanies.register();
		OtherZombies.register();
		//register challenge.
		ChallengeManager.registerChallengeStuff();
	}
	
	/**
	 * {@link PVZMod#setUp(FMLCommonSetupEvent)}
	 */
    public static void setUp(FMLCommonSetupEvent ev){
    	CapabilityHandler.registerCapabilities();
    	PVZPacketHandler.init();
    	BiomeRegister.registerBiomes(ev);
    	PotionRecipeHandler.registerPotionRecipes();
    	CommonRegister.registerCompostable();
    	BiomeUtil.initBiomeSet();
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
