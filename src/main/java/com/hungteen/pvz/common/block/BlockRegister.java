package com.hungteen.pvz.common.block;

import java.util.Arrays;
import java.util.List;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.cubes.ButterBlock;
import com.hungteen.pvz.common.block.cubes.OriginBlock;
import com.hungteen.pvz.common.block.ores.EssenceOreBlock;
import com.hungteen.pvz.common.block.ores.PVZOreBlock;
import com.hungteen.pvz.common.block.others.SteelLadderBlock;
import com.hungteen.pvz.common.block.plants.CabbageBlock;
import com.hungteen.pvz.common.block.plants.ChomperBlock;
import com.hungteen.pvz.common.block.plants.CornBlock;
import com.hungteen.pvz.common.block.plants.LilyPadBlock;
import com.hungteen.pvz.common.block.plants.PVZSaplingBlock;
import com.hungteen.pvz.common.block.plants.PeaBlock;
import com.hungteen.pvz.common.block.plants.ToxicShroomBlock;
import com.hungteen.pvz.common.block.special.CardFusionBlock;
import com.hungteen.pvz.common.block.special.EssenceAltarBlock;
import com.hungteen.pvz.common.block.special.FlowerPotBlock;
import com.hungteen.pvz.common.block.special.FragmentSpliceBlock;
import com.hungteen.pvz.common.block.special.GoldTileBlock;
import com.hungteen.pvz.common.block.special.LanternBlock;
import com.hungteen.pvz.common.block.special.SlotMachineBlock;
import com.hungteen.pvz.common.block.special.SunConverterBlock;
import com.hungteen.pvz.common.block.special.SunFlowerTrophyBlock;
import com.hungteen.pvz.common.impl.EssenceType;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.item.blockitem.LilyPadItem;
import com.hungteen.pvz.common.item.blockitem.SlotMachineItem;
import com.hungteen.pvz.common.world.feature.NutTree;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegister {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PVZMod.MOD_ID);

	// ores.
	public static final RegistryObject<EssenceOreBlock> ORIGIN_ORE = BLOCKS.register("origin_ore", () -> new EssenceOreBlock(EssenceType.ORIGIN));
	public static final RegistryObject<EssenceOreBlock> APPEASE_ORE = BLOCKS.register("appease_ore", () -> new EssenceOreBlock(EssenceType.APPEASE));
	public static final RegistryObject<EssenceOreBlock> LIGHT_ORE = BLOCKS.register("light_ore", () -> new EssenceOreBlock(EssenceType.LIGHT));
	public static final RegistryObject<EssenceOreBlock> EXPLOSION_ORE = BLOCKS.register("explosion_ore", () -> new EssenceOreBlock(EssenceType.EXPLOSION));
	public static final RegistryObject<EssenceOreBlock> DEFENCE_ORE = BLOCKS.register("defence_ore", () -> new EssenceOreBlock(EssenceType.DEFENCE));
	public static final RegistryObject<EssenceOreBlock> ICE_ORE = BLOCKS.register("ice_ore", () -> new EssenceOreBlock(EssenceType.ICE));
	public static final RegistryObject<EssenceOreBlock> ENFORCE_ORE = BLOCKS.register("enforce_ore", () -> new EssenceOreBlock(EssenceType.ENFORCE));
	public static final RegistryObject<EssenceOreBlock> TOXIC_ORE = BLOCKS.register("toxic_ore", () -> new EssenceOreBlock(EssenceType.TOXIC));
	public static final RegistryObject<EssenceOreBlock> ASSIST_ORE = BLOCKS.register("assist_ore", () -> new EssenceOreBlock(EssenceType.ASSIST));
	public static final RegistryObject<EssenceOreBlock> MAGIC_ORE = BLOCKS.register("magic_ore", () -> new EssenceOreBlock(EssenceType.MAGIC));
	public static final RegistryObject<EssenceOreBlock> FLAME_ORE = BLOCKS.register("flame_ore", () -> new EssenceOreBlock(EssenceType.FLAME));
	public static final RegistryObject<EssenceOreBlock> SPEAR_ORE = BLOCKS.register("spear_ore", () -> new EssenceOreBlock(EssenceType.SPEAR));
	public static final RegistryObject<EssenceOreBlock> ARMA_ORE = BLOCKS.register("arma_ore", () -> new EssenceOreBlock(EssenceType.ARMA));
	public static final RegistryObject<EssenceOreBlock> ELECTRIC_ORE = BLOCKS.register("electric_ore", () -> new EssenceOreBlock(EssenceType.ELECTRIC));
	public static final RegistryObject<EssenceOreBlock> SHADOW_ORE = BLOCKS.register("shadow_ore", () -> new EssenceOreBlock(EssenceType.SHADOW));
	public static final RegistryObject<PVZOreBlock> AMETHYST_ORE = BLOCKS.register("amethyst_ore",() -> new PVZOreBlock(Block.Properties.copy(Blocks.DIAMOND_ORE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().harvestLevel(3).strength(4F, 6F)));
	
	//block
	public static final RegistryObject<Block> AMETHYST_BLOCK = BLOCKS.register("amethyst_block", () -> new Block(Block.Properties.copy(Blocks.EMERALD_BLOCK).strength(9, 9))); 
	public static final RegistryObject<Block> ORIGIN_BLOCK = BLOCKS.register("origin_block", OriginBlock::new);
	public static final RegistryObject<Block> BUTTER_BLOCK = BLOCKS.register("butter_block", ButterBlock::new);
	public static final RegistryObject<Block> FROZEN_MELON = BLOCKS.register("frozen_melon", () -> new Block(Block.Properties.copy(Blocks.MELON)));
	public static final RegistryObject<Block> STEEL_LADDER = BLOCKS.register("steel_ladder", SteelLadderBlock::new);
	
	//crops
	public static final RegistryObject<Block> PEA_PLANT = BLOCKS.register("pea_plant", () -> new PeaBlock(Block.Properties.copy(Blocks.WHEAT)));
	public static final RegistryObject<Block> TOXIC_SHROOM = BLOCKS.register("toxic_shroom", () -> new ToxicShroomBlock(Block.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
	public static final RegistryObject<Block> CABBAGE = BLOCKS.register("cabbage", () -> new CabbageBlock(Block.Properties.copy(Blocks.WHEAT)));
	public static final RegistryObject<Block> CORN = BLOCKS.register("corn", () -> new CornBlock(Block.Properties.copy(Blocks.WHEAT)));
	
	//plants
	public static final RegistryObject<Block> NUT_LEAVES = BLOCKS.register("nut_leaves", () -> new LeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> NUT_SAPLING = BLOCKS.register("nut_sapling", () -> new PVZSaplingBlock(NutTree::new));
	public static final RegistryObject<Block> CHOMPER = BLOCKS.register("chomper", ChomperBlock::new);
	public static final RegistryObject<LilyPadBlock> LILY_PAD = BLOCKS.register("lily_pad", LilyPadBlock::new);
	
	//special
	public static final RegistryObject<Block> LANTERN = BLOCKS.register("lantern", LanternBlock::new);
	public static final RegistryObject<FlowerPotBlock> FLOWER_POT = BLOCKS.register("flower_pot", FlowerPotBlock::new);
	public static final RegistryObject<GoldTileBlock> GOLD_TILE1 = BLOCKS.register("gold_tile1", () -> new GoldTileBlock(1));
	public static final RegistryObject<GoldTileBlock> GOLD_TILE2 = BLOCKS.register("gold_tile2", () -> new GoldTileBlock(2));
	public static final RegistryObject<GoldTileBlock> GOLD_TILE3 = BLOCKS.register("gold_tile3", () -> new GoldTileBlock(3));
	public static final RegistryObject<SunFlowerTrophyBlock> SILVER_SUNFLOWER_TROPHY = BLOCKS.register("silver_sunflower_trophy", () -> new SunFlowerTrophyBlock(Block.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(), 1));
	public static final RegistryObject<SunFlowerTrophyBlock> GOLD_SUNFLOWER_TROPHY = BLOCKS.register("gold_sunflower_trophy", () -> new SunFlowerTrophyBlock(Block.Properties.copy(Blocks.GOLD_BLOCK).noOcclusion(), 2));
	public static final RegistryObject<SunFlowerTrophyBlock> DIAMOND_SUNFLOWER_TROPHY = BLOCKS.register("diamond_sunflower_trophy", () -> new SunFlowerTrophyBlock(Block.Properties.copy(Blocks.DIAMOND_BLOCK).noOcclusion(), 3));
	
	//gui & te block
	public static final RegistryObject<SunConverterBlock> SUN_CONVERTER = BLOCKS.register("sun_converter", SunConverterBlock::new);
	public static final RegistryObject<FragmentSpliceBlock> FRAGMENT_SPLICE = BLOCKS.register("fragment_splice", FragmentSpliceBlock::new);
	public static final RegistryObject<SlotMachineBlock> SLOT_MACHINE = BLOCKS.register("slot_machine", SlotMachineBlock::new);
	public static final RegistryObject<EssenceAltarBlock> ESSENCE_ALTAR = BLOCKS.register("essence_altar", EssenceAltarBlock::new);
	public static final RegistryObject<CardFusionBlock> CARD_FUSION_TABLE = BLOCKS.register("card_fusion_table", CardFusionBlock::new);
	
	/**
	 * register block items.
	 */
	@SubscribeEvent
	public static void registerBlockItem(RegistryEvent.Register<Item> ev){
		IForgeRegistry<Item> items = ev.getRegistry();
		List<RegistryObject<? extends Block>> blocks = Arrays.asList(
				ORIGIN_ORE, APPEASE_ORE, LIGHT_ORE, EXPLOSION_ORE, DEFENCE_ORE, ICE_ORE, ENFORCE_ORE, TOXIC_ORE, ASSIST_ORE, MAGIC_ORE, FLAME_ORE, SPEAR_ORE, ARMA_ORE, ELECTRIC_ORE, SHADOW_ORE, AMETHYST_ORE,
				AMETHYST_BLOCK, ORIGIN_BLOCK, BUTTER_BLOCK, FROZEN_MELON, STEEL_LADDER,
				NUT_LEAVES, NUT_SAPLING, CHOMPER, LILY_PAD,
				LANTERN, FLOWER_POT, GOLD_TILE1, GOLD_TILE2, GOLD_TILE3, SILVER_SUNFLOWER_TROPHY, GOLD_SUNFLOWER_TROPHY, DIAMOND_SUNFLOWER_TROPHY,
				SUN_CONVERTER, FRAGMENT_SPLICE, SLOT_MACHINE, ESSENCE_ALTAR, CARD_FUSION_TABLE
		);
		for(RegistryObject<? extends Block> b : blocks) {
			final Block block = b.get();
			if(block.equals(LILY_PAD.get())) {
				items.register(new LilyPadItem().setRegistryName(block.getRegistryName()));
			} else if(block.equals(SLOT_MACHINE.get())){
				items.register(new SlotMachineItem().setRegistryName(block.getRegistryName()));
			} else {
				items.register(new BlockItem(block, new Item.Properties().tab(PVZItemGroups.PVZ_MISC)).setRegistryName(block.getRegistryName()));
			}
		}
		
	}
	
}