package com.hungteen.pvzmod.registry;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvzmod.blocks.BlockBase;
import com.hungteen.pvzmod.blocks.BlockGoldenTile;
import com.hungteen.pvzmod.blocks.BlockOrigin;
import com.hungteen.pvzmod.blocks.BlockOriginAltar;
import com.hungteen.pvzmod.blocks.JewelOre;
import com.hungteen.pvzmod.blocks.OreBase;
import com.hungteen.pvzmod.blocks.OreBase1;
import com.hungteen.pvzmod.blocks.crops.BlockPeaPlant;
import com.hungteen.pvzmod.blocks.misc.BlockFlowerPot;
import com.hungteen.pvzmod.blocks.misc.BlockPVZLilyPad;
import com.hungteen.pvzmod.blocks.misc.BlockPole;
import com.hungteen.pvzmod.blocks.misc.BlockWarningSign;
import com.hungteen.pvzmod.blocks.misc.BlockZombieDoll;
import com.hungteen.pvzmod.blocks.plants.BlockLeavesBase;
import com.hungteen.pvzmod.blocks.plants.BlockSaplingBase;
import com.hungteen.pvzmod.blocks.special.BlockCardTable;
import com.hungteen.pvzmod.blocks.special.BlockJuicer;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockRegister {

	public static List<Block> BLOCKS = new ArrayList<Block>();
	//ores
	public static final Block ALUMINUM_ORE = new OreBase("aluminum_ore",Material.ROCK);
	public static final Block JEWEL_ORE = new JewelOre("jewel_ore",Material.ROCK);
	public static final Block ORIGIN_ORE = new OreBase1("origin_ore",Material.ROCK, ItemRegister.ORIGIN_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	public static final Block COMMON_ORE = new OreBase1("common_ore",Material.ROCK, ItemRegister.COMMON_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	public static final Block ELECTRICITY_ORE = new OreBase1("electricity_ore",Material.ROCK, ItemRegister.ELECTRICITY_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	public static final Block FLAME_ORE = new OreBase1("flame_ore",Material.ROCK, ItemRegister.FLAME_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	public static final Block ICE_ORE = new OreBase1("ice_ore",Material.ROCK, ItemRegister.ICE_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	public static final Block LIGHT_ORE = new OreBase1("light_ore",Material.ROCK, ItemRegister.LIGHT_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	public static final Block DARKNESS_ORE = new OreBase1("darkness_ore",Material.ROCK, ItemRegister.DARKNESS_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	public static final Block MAGIC_ORE = new OreBase1("magic_ore",Material.ROCK, ItemRegister.MAGIC_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	public static final Block EXPLOSION_ORE = new OreBase1("explosion_ore",Material.ROCK, ItemRegister.EXPLOSION_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	public static final Block DEFENCE_ORE = new OreBase1("defence_ore",Material.ROCK, ItemRegister.DEFENCE_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	public static final Block FIGHT_ORE = new OreBase1("fight_ore",Material.ROCK, ItemRegister.FIGHT_ELEMENT, Reference.ENDERCHEST_HARDNESS, Reference.ENDERCHEST_RESISTANCE, 3, 0.64f, 1,4);
	//crops
	public static final Block PEA_PLANT = new BlockPeaPlant("pea_plant"); 
	public static final Block NUT_SAPLING = new BlockSaplingBase("nut_sapling"); 
	public static final Block NUT_LEAVES = new BlockLeavesBase("nut_leaves");
	
	//blocks
	public static final Block STEEL_BLOCK = new BlockBase("steel_block",Material.IRON);
	public static final Block THE_ORIGIN = new BlockOrigin("the_origin",Material.ROCK);
	public static final Block ORIGIN_ALTAR = new BlockOriginAltar("origin_altar", Material.IRON);
	public static final Block FLOWER_POT = new BlockFlowerPot("flower_pot", Material.CLAY);
	public static final Block GOLDENTILE1 = new BlockGoldenTile("golden_tile1", Material.IRON);
	public static final Block GOLDENTILE2 = new BlockGoldenTile("golden_tile2", Material.IRON);
	public static final Block GOLDENTILE3 = new BlockGoldenTile("golden_tile3", Material.IRON);
	public static final Block JUICER = new BlockJuicer("juicer", Material.IRON);
	public static final Block POLE = new BlockPole("pole",Material.IRON);
	public static final Block ZOMBIE_DOLL = new BlockZombieDoll("zombie_doll",Material.IRON);
	public static final Block WARNING_SIGN = new BlockWarningSign("warning_sign",Material.IRON);
	public static final Block LILY_PAD = new BlockPVZLilyPad("lily_pad", Material.GRASS);
	public static final Block CARD_TABLE = new BlockCardTable("card_table", Material.WOOD);
}