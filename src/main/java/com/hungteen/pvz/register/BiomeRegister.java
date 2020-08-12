package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.biome.PVZBiome;
import com.hungteen.pvz.biome.ZenGardenBiome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeRegister {

	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, PVZMod.MOD_ID);
	
	public static final RegistryObject<Biome> ZEN_GARDEN = BIOMES.register("zen_garden", ZenGardenBiome::new);
	
    public static void addBiomes() {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(ZEN_GARDEN.get(), 1000));
    }
	
	public static void addBiomeFeatures(){
		for(Biome biome:ForgeRegistries.BIOMES.getValues()) {
			if(biome instanceof PVZBiome) {
				((PVZBiome) biome).addFeatures();
				((PVZBiome) biome).addSpawns();
			}
		}
	}
}
