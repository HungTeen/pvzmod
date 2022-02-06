package com.hungteen.pvz.common.tileentity;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.render.tileentity.EssenceAltarTER;
import com.hungteen.pvz.client.render.tileentity.SunConverterTER;
import com.hungteen.pvz.client.render.tileentity.SunFlowerTrophyTER;
import com.hungteen.pvz.common.block.BlockRegister;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegister {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, PVZMod.MOD_ID);
	
	public static final RegistryObject<TileEntityType<SunConverterTileEntity>> SUN_CONVERTER = TILE_ENTITY_TYPES.register("sun_converter", () -> {
		return TileEntityType.Builder.of(SunConverterTileEntity::new , BlockRegister.SUN_CONVERTER.get()).build(null);
	});
	public static final RegistryObject<TileEntityType<FragmentSpliceTileEntity>> FRAGMENT_SPLICE = TILE_ENTITY_TYPES.register("fragment_splice", () -> {
		return TileEntityType.Builder.of(FragmentSpliceTileEntity::new , BlockRegister.FRAGMENT_SPLICE.get()).build(null);
	});
	public static final RegistryObject<TileEntityType<SlotMachineTileEntity>> SLOT_MACHINE = TILE_ENTITY_TYPES.register("slot_machine", () -> {
		return TileEntityType.Builder.of(SlotMachineTileEntity::new , BlockRegister.SLOT_MACHINE.get()).build(null);
	});
	public static final RegistryObject<TileEntityType<CardFusionTileEntity>> CARD_FUSION = TILE_ENTITY_TYPES.register("card_fusion", () -> {
		return TileEntityType.Builder.of(CardFusionTileEntity::new , BlockRegister.CARD_FUSION_TABLE.get()).build(null);
	});
	public static final RegistryObject<TileEntityType<SunFlowerTrophyTileEntity>> SUNFLOWER_TROPHY = TILE_ENTITY_TYPES.register("sunflower_trophy", () -> {
		return TileEntityType.Builder.of(SunFlowerTrophyTileEntity::new , BlockRegister.SILVER_SUNFLOWER_TROPHY.get(), BlockRegister.GOLD_SUNFLOWER_TROPHY.get(), BlockRegister.DIAMOND_SUNFLOWER_TROPHY.get()).build(null);
	});
	public static final RegistryObject<TileEntityType<EssenceAltarTileEntity>> ESSENCE_ALTAR = TILE_ENTITY_TYPES.register("essence_altar", () -> {
		return TileEntityType.Builder.of(EssenceAltarTileEntity::new , BlockRegister.ESSENCE_ALTAR.get()).build(null);
	});
	
	@OnlyIn(Dist.CLIENT)
	public static void bindRenderers(FMLClientSetupEvent ev) {
		ev.getMinecraftSupplier().get().tell(() -> {
			ClientRegistry.bindTileEntityRenderer(TileEntityRegister.SUN_CONVERTER.get(), SunConverterTER::new);
			ClientRegistry.bindTileEntityRenderer(TileEntityRegister.SUNFLOWER_TROPHY.get(), SunFlowerTrophyTER::new);
			ClientRegistry.bindTileEntityRenderer(TileEntityRegister.ESSENCE_ALTAR.get(), EssenceAltarTER::new);
		});
	}
	
}
