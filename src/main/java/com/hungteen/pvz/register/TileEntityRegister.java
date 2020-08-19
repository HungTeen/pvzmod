package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegister {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, PVZMod.MOD_ID);
	
//	public static final RegistryObject<TileEntityType<WaveSpawnerTileEntity>> WAVE_SPAWNER = TILE_ENTITY_TYPES.register("wave_spawner", ()->{
//		return TileEntityType.Builder.create(()->{return new WaveSpawnerTileEntity();}, BlockRegister.BUCKET_WAVE.get()).build(null);
//	});
}
