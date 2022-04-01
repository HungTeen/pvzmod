package com.hungteen.pvz.common.block;

import java.util.Arrays;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.crops.PeaCropBlock;
import com.hungteen.pvz.common.block.cubes.EssenceOreBlock;
import com.hungteen.pvz.common.block.cubes.OriginBlock;
import com.hungteen.pvz.common.impl.type.EssenceTypes;
import com.hungteen.pvz.common.item.PVZItemTabs;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 22:45
 **/
public class PVZBlocks {

    /**
     * {@link PVZMod#deferredRegister(IEventBus)}
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PVZMod.MOD_ID);

    // ores.
    public static final RegistryObject<EssenceOreBlock> ORIGIN_ORE = BLOCKS.register("origin_ore", () -> new EssenceOreBlock(EssenceTypes.ORIGIN, 12, 3, 8));
    public static final RegistryObject<EssenceOreBlock> APPEASE_ORE = BLOCKS.register("appease_ore", () -> new EssenceOreBlock(EssenceTypes.APPEASE, 0, 1, 2));
    public static final RegistryObject<EssenceOreBlock> LIGHT_ORE = BLOCKS.register("light_ore", () -> new EssenceOreBlock(EssenceTypes.LIGHT, 12, 2, 5));
    public static final RegistryObject<EssenceOreBlock> EXPLOSION_ORE = BLOCKS.register("explosion_ore", () -> new EssenceOreBlock(EssenceTypes.EXPLOSION, 0, 2, 4));
    public static final RegistryObject<EssenceOreBlock> DEFENCE_ORE = BLOCKS.register("defence_ore", () -> new EssenceOreBlock(EssenceTypes.DEFENCE, 0, 1, 4));
    public static final RegistryObject<EssenceOreBlock> ICE_ORE = BLOCKS.register("ice_ore", () -> new EssenceOreBlock(EssenceTypes.ICE, 0, 3, 6));
    public static final RegistryObject<EssenceOreBlock> ENFORCE_ORE = BLOCKS.register("enforce_ore", () -> new EssenceOreBlock(EssenceTypes.ENFORCE, 0, 1, 4));
//    public static final RegistryObject<EssenceOreBlock> TOXIC_ORE = BLOCKS.register("toxic_ore", () -> new EssenceOreBlock(EssenceTypes.TOXIC, 0, 2, 6));
    public static final RegistryObject<EssenceOreBlock> ASSIST_ORE = BLOCKS.register("assist_ore", () -> new EssenceOreBlock(EssenceTypes.ASSIST, 0, 1, 4));
    public static final RegistryObject<EssenceOreBlock> MAGIC_ORE = BLOCKS.register("magic_ore", () -> new EssenceOreBlock(EssenceTypes.MAGIC, 0, 1, 3));
    public static final RegistryObject<EssenceOreBlock> FLAME_ORE = BLOCKS.register("flame_ore", () -> new EssenceOreBlock(EssenceTypes.FLAME, 12, 1, 4));
    public static final RegistryObject<EssenceOreBlock> SPEAR_ORE = BLOCKS.register("spear_ore", () -> new EssenceOreBlock(EssenceTypes.SPEAR, 0, 1, 4));
    public static final RegistryObject<EssenceOreBlock> ARMA_ORE = BLOCKS.register("arma_ore", () -> new EssenceOreBlock(EssenceTypes.ARMA, 0, 1, 3));
//    public static final RegistryObject<EssenceOreBlock> ELECTRIC_ORE = BLOCKS.register("electric_ore", () -> new EssenceOreBlock(EssenceTypes.ELECTRIC, 12, 2, 5));
//    public static final RegistryObject<EssenceOreBlock> SHADOW_ORE = BLOCKS.register("shadow_ore", () -> new EssenceOreBlock(EssenceTypes.SHADOW, 0, 2, 5));
    public static final RegistryObject<OreBlock> AMETHYST_ORE = BLOCKS.register("amethyst_ore",() -> new OreBlock(Block.Properties.copy(Blocks.DIAMOND_ORE).strength(4F, 6F)));

    //block
    public static final RegistryObject<Block> AMETHYST_BLOCK = BLOCKS.register("amethyst_block", () -> new Block(Block.Properties.copy(Blocks.EMERALD_BLOCK).strength(9, 9)));
    public static final RegistryObject<Block> ORIGIN_BLOCK = BLOCKS.register("origin_block", OriginBlock::new);
//    public static final RegistryObject<Block> BUTTER_BLOCK = BLOCKS.register("butter_block", ButterBlock::new);
//    public static final RegistryObject<Block> FROZEN_MELON = BLOCKS.register("frozen_melon", () -> new Block(Block.Properties.copy(Blocks.MELON)));
//    public static final RegistryObject<Block> STEEL_LADDER = BLOCKS.register("steel_ladder", SteelLadderBlock::new);
//
//    //crops
    public static final RegistryObject<Block> PEA_CROP = BLOCKS.register("pea_crop", PeaCropBlock::new);
//    public static final RegistryObject<Block> TOXIC_SHROOM = BLOCKS.register("toxic_shroom", () -> new ToxicShroomBlock(Block.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
//    public static final RegistryObject<Block> CABBAGE = BLOCKS.register("cabbage", () -> new CabbageBlock(Block.Properties.copy(Blocks.WHEAT)));
//    public static final RegistryObject<Block> CORN = BLOCKS.register("corn", () -> new CornBlock(Block.Properties.copy(Blocks.WHEAT)));

    //plants
    public static final RegistryObject<Block> NUT_LEAVES = BLOCKS.register("nut_leaves", () -> new LeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> NUT_LEAVES_WITH_NUTS = BLOCKS.register("nut_leaves_with_nuts", () -> new LeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> NUT_LOG = BLOCKS.register("nut_log", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_LOG).strength(4.0F)));
    public static final RegistryObject<Block> STRIPPED_NUT_LOG = BLOCKS.register("stripped_nut_log", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(4.0F)));
    public static final RegistryObject<Block> NUT_WOOD = BLOCKS.register("nut_wood", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(4.0F)));
    public static final RegistryObject<Block> STRIPPED_NUT_WOOD = BLOCKS.register("stripped_nut_wood", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(4.0F)));
    public static final RegistryObject<Block> NUT_PLANKS = BLOCKS.register("nut_planks", () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> NUT_DOOR = BLOCKS.register("nut_door", () -> new DoorBlock(Block.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<Block> NUT_TRAPDOOR = BLOCKS.register("nut_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> NUT_FENCE = BLOCKS.register("nut_fence", () -> new FenceBlock(Block.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> NUT_FENCE_GATE = BLOCKS.register("nut_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE)));
    public static final RegistryObject<Block> NUT_SIGN = BLOCKS.register("nut_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), WoodType.OAK));
    public static final RegistryObject<Block> NUT_WALL_SIGN = BLOCKS.register("nut_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), WoodType.OAK));
//    public static final RegistryObject<Block> NUT_SAPLING = BLOCKS.register("nut_sapling", () -> new Block(NutTree::new));
//    public static final RegistryObject<Block> CHOMPER = BLOCKS.register("chomper", ChomperBlock::new);
//    public static final RegistryObject<LilyPadBlock> LILY_PAD = BLOCKS.register("lily_pad", LilyPadBlock::new);
//
//    //special
//    public static final RegistryObject<Block> LANTERN = BLOCKS.register("lantern", LanternBlock::new);
//    public static final RegistryObject<FlowerPotBlock> FLOWER_POT = BLOCKS.register("flower_pot", FlowerPotBlock::new);
//    public static final RegistryObject<GoldTileBlock> GOLD_TILE1 = BLOCKS.register("gold_tile1", () -> new GoldTileBlock(1));
//    public static final RegistryObject<GoldTileBlock> GOLD_TILE2 = BLOCKS.register("gold_tile2", () -> new GoldTileBlock(2));
//    public static final RegistryObject<GoldTileBlock> GOLD_TILE3 = BLOCKS.register("gold_tile3", () -> new GoldTileBlock(3));
//    public static final RegistryObject<SunFlowerTrophyBlock> SILVER_SUNFLOWER_TROPHY = BLOCKS.register("silver_sunflower_trophy", () -> new SunFlowerTrophyBlock(Block.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(), 1));
//    public static final RegistryObject<SunFlowerTrophyBlock> GOLD_SUNFLOWER_TROPHY = BLOCKS.register("gold_sunflower_trophy", () -> new SunFlowerTrophyBlock(Block.Properties.copy(Blocks.GOLD_BLOCK).noOcclusion(), 2));
//    public static final RegistryObject<SunFlowerTrophyBlock> DIAMOND_SUNFLOWER_TROPHY = BLOCKS.register("diamond_sunflower_trophy", () -> new SunFlowerTrophyBlock(Block.Properties.copy(Blocks.DIAMOND_BLOCK).noOcclusion(), 3));
//
//    //gui & te block
//    public static final RegistryObject<SunConverterBlock> SUN_CONVERTER = BLOCKS.register("sun_converter", SunConverterBlock::new);
//    public static final RegistryObject<FragmentSpliceBlock> FRAGMENT_SPLICE = BLOCKS.register("fragment_splice", FragmentSpliceBlock::new);
//    public static final RegistryObject<SlotMachineBlock> SLOT_MACHINE = BLOCKS.register("slot_machine", SlotMachineBlock::new);
//    public static final RegistryObject<EssenceAltarBlock> ESSENCE_ALTAR = BLOCKS.register("essence_altar", EssenceAltarBlock::new);
//    public static final RegistryObject<CardFusionBlock> CARD_FUSION_TABLE = BLOCKS.register("card_fusion_table", CardFusionBlock::new);

    /**
     * register block items.
     * {@link PVZMod#PVZMod()}
     */
    public static void registerBlockItem(RegistryEvent.Register<Item> ev){
        IForgeRegistry<Item> items = ev.getRegistry();

        Arrays.asList(
                ORIGIN_ORE, APPEASE_ORE, LIGHT_ORE, EXPLOSION_ORE, DEFENCE_ORE, ICE_ORE, ENFORCE_ORE,
//                TOXIC_ORE,
                ASSIST_ORE, MAGIC_ORE, FLAME_ORE, SPEAR_ORE, ARMA_ORE, //ELECTRIC_ORE, SHADOW_ORE,
                AMETHYST_ORE, AMETHYST_BLOCK, ORIGIN_BLOCK,// ,BUTTER_BLOCK, FROZEN_MELON,
                NUT_LEAVES, NUT_LEAVES_WITH_NUTS, NUT_LOG, STRIPPED_NUT_LOG, NUT_WOOD, STRIPPED_NUT_WOOD, NUT_PLANKS, NUT_DOOR, NUT_TRAPDOOR, NUT_FENCE, NUT_FENCE_GATE//, NUT_SAPLING, CHOMPER,
//                LANTERN, FLOWER_POT, GOLD_TILE1, GOLD_TILE2, GOLD_TILE3, SILVER_SUNFLOWER_TROPHY, GOLD_SUNFLOWER_TROPHY, DIAMOND_SUNFLOWER_TROPHY
        ).forEach(block -> {
            items.register(new BlockItem(block.get(), new Item.Properties().tab(PVZItemTabs.PVZ_MISC)).setRegistryName(block.get().getRegistryName()));
        });

//        Arrays.asList(
//                STEEL_LADDER, SUN_CONVERTER, FRAGMENT_SPLICE, ESSENCE_ALTAR, CARD_FUSION_TABLE
//        ).forEach(block -> {
//            items.register(new BlockItem(block.get(), new Item.Properties().tab(PVZItemTabs.PVZ_USEFUL)).setRegistryName(block.get().getRegistryName()));
//        });

//        items.register(new LilyPadItem().setRegistryName(LILY_PAD.get().getRegistryName()));
//        items.register(new SlotMachineItem().setRegistryName(SLOT_MACHINE.get().getRegistryName()));

    }

}
