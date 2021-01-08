package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.render.tileentity.SunConverterTER;
import com.hungteen.pvz.tileentity.SunConverterTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegister {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, PVZMod.MOD_ID);
	
	public static final RegistryObject<TileEntityType<SunConverterTileEntity>> SUN_CONVERTER = TILE_ENTITY_TYPES.register("sun_converter", () -> {
		return TileEntityType.Builder.create(SunConverterTileEntity::new , BlockRegister.SUN_CONVERTER.get()).build(null);
	});
	
	@OnlyIn(Dist.CLIENT)
	public static void bindRenderers(FMLClientSetupEvent ev) {
		ev.getMinecraftSupplier().get().enqueue(() -> {
			ClientRegistry.bindTileEntityRenderer(TileEntityRegister.SUN_CONVERTER.get(), SunConverterTER::new);
		});
	}
	
}
