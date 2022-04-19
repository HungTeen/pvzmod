package com.hungteen.pvz.common.world.dimension;

import com.hungteen.pvz.utils.Util;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-14 10:17
 **/
public class PVZDimensions {

    public static final ResourceKey<Level> ABYSSAL_DARK = ResourceKey.create(Registry.DIMENSION_REGISTRY, Util.prefix("abyssal_dark"));

    /**
     * {@link com.hungteen.pvz.PVZMod#setUp(FMLCommonSetupEvent)}
     */
    public static void register(){
        Registry.register(Registry.CHUNK_GENERATOR, Util.prefix("abyssal_dark_chunk_gen"), AbyssalDarkChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_SOURCE, Util.prefix("abyssal_dark_biomes"), AbyssalDarkBiomeProvider.CODEC);

    }
}
