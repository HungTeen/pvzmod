package com.hungteen.pvzmod.util;

import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftUtil {

	//element
	public static final ItemStack ORI = new ItemStack(ItemRegister.ORIGIN_ELEMENT);
	public static final ItemStack COM = new ItemStack(ItemRegister.COMMON_ELEMENT);
	public static final ItemStack LIT = new ItemStack(ItemRegister.LIGHT_ELEMENT);
	public static final ItemStack EXP = new ItemStack(ItemRegister.EXPLOSION_ELEMENT);
	public static final ItemStack ICE = new ItemStack(ItemRegister.ICE_ELEMENT);
	public static final ItemStack DAK = new ItemStack(ItemRegister.DARKNESS_ELEMENT);
	public static final ItemStack DEF = new ItemStack(ItemRegister.DEFENCE_ELEMENT);
	public static final ItemStack FIG = new ItemStack(ItemRegister.FIGHT_ELEMENT);
	public static final ItemStack MAG = new ItemStack(ItemRegister.MAGIC_ELEMENT);
	//card model
	public static final ItemStack GRAY = new ItemStack(ItemRegister.GRAY_CARD);
	public static final ItemStack WHITE = new ItemStack(ItemRegister.WHITE_CARD);
	public static final ItemStack GREEN = new ItemStack(ItemRegister.GREEN_CARD);
	public static final ItemStack BLUE = new ItemStack(ItemRegister.BLUE_CARD);
	public static final ItemStack PURPLE = new ItemStack(ItemRegister.PURPLE_CARD);
	public static final ItemStack GOLD = new ItemStack(ItemRegister.GOLD_CARD);
	public static final ItemStack RED = new ItemStack(ItemRegister.RED_CARD);
	
	/**
	 * 获取程序认识的配方（麻将反人类！）
	 */
	public static ItemStack[] getCardRecipesByPlant(Plants plant)
	{
		ItemStack[][] itemstacks=getCardRecipesByPlant1(plant);
		ItemStack[] stacks =new ItemStack[9];
//		System.out.println(items.length);
		if(itemstacks!=null) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					stacks[i*3+j]=itemstacks[j][i];
				}
			}
			return stacks;
		}
		else {
			System.out.println("RECIPES ERROR!");
			return null;
		}
	}
	
	/**
	 * 获取人类友好的植物配方
	 */
	public static ItemStack[][] getCardRecipesByPlant1(Plants plant)
	{
		switch(plant) {
		case PEA_SHOOTER:return new ItemStack[][]{
				{ORI,ORI,ORI},
				{COM,new ItemStack(ItemRegister.PEA),COM},
				{COM,GRAY,COM}
		};
		case SUN_FLOWER:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{LIT,new ItemStack(Blocks.DOUBLE_PLANT,1,0),LIT},
			{LIT,GRAY,LIT}
	    };
		case NUT_WALL:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{DEF,new ItemStack(ItemRegister.NUT),DEF},
			{DEF,WHITE,DEF}
	    };
		case POTATO_MINE:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{EXP,new ItemStack(Items.POTATO),EXP},
			{EXP,WHITE,EXP}
	    };
		case SNOW_PEA:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{ICE,new ItemStack(ItemRegister.SNOW_PEA),ICE},
			{ICE,GREEN,ICE}
	    };
		case DOUBLE_SHOOTER:return new ItemStack[][]{
			{COM,new ItemStack(ItemRegister.PEA_SHOOTER_CARD),COM},
			{COM,new ItemStack(ItemRegister.PEA_SHOOTER_CARD),COM},
			{COM,GREEN,COM}
	    };
		case HYPNO_SHROOM:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{MAG,new ItemStack(Blocks.RED_MUSHROOM),MAG},
			{MAG,BLUE,MAG}
	    };
		case ICE_SHROOM:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{ICE,new ItemStack(Blocks.BROWN_MUSHROOM),ICE},
			{ICE,BLUE,ICE}
	    };
		case LILY_PAD:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{DEF,new ItemStack(BlockRegister.LILY_PAD),DEF},
			{DEF,GRAY,DEF}
	    };
//		case SQUASH:return new ItemStack[][]{
//			{ORI,ORI,ORI},
//			{ICE,new ItemStack(Blocks.BROWN_MUSHROOM),ICE},
//			{ICE,GREEN,ICE}
//	    };
		case THREE_PEATER:return new ItemStack[][]{
			{COM,COM,COM},
			{new ItemStack(ItemRegister.PEA_SHOOTER_CARD),new ItemStack(ItemRegister.PEA_SHOOTER_CARD),new ItemStack(ItemRegister.PEA_SHOOTER_CARD)},
			{COM,BLUE,COM}
	    };
		case TANGLE_KELP:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{FIG,new ItemStack(Blocks.VINE),FIG},
			{FIG,WHITE,FIG}
	    };
//		case JALAPENO:return new ItemStack[][]{
//			{ORI,ORI,ORI},
//			{FIG,new ItemStack(Blocks.VINE),FIG},
//			{FIG,WHITE,FIG}
//	    };
		case SPIKE_WEED:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{FIG,new ItemStack(Blocks.TALLGRASS),FIG},
			{FIG,WHITE,FIG}
	    };
//		case TORCH_WOOD:return new ItemStack[][]{
//			{ORI,ORI,ORI},
//			{FIG,new ItemStack(Blocks.TALLGRASS),FIG},
//			{FIG,GREEN,FIG}
//	    };
		case TALL_NUT:return new ItemStack[][]{
			{DEF,new ItemStack(ItemRegister.NUT_WALL_CARD),DEF},
			{DEF,new ItemStack(ItemRegister.NUT_WALL_CARD),DEF},
			{DEF,WHITE,DEF}
	    };
		case SPLIT_PEA:return new ItemStack[][]{
			{COM,COM,COM},
			{new ItemStack(ItemRegister.DOUBLE_SHOOTER_CARD),COM,new ItemStack(ItemRegister.PEA_SHOOTER_CARD)},
			{COM,WHITE,COM}
	    };
		case PUMPKIN:return new ItemStack[][]{
			{DEF,DEF,DEF},
			{DEF,new ItemStack(Items.PUMPKIN_SEEDS),DEF},
			{DEF,WHITE,DEF}
	    };
		case CABBAGE_PULT:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{COM,new ItemStack(ItemRegister.CABBAGE),COM},
			{COM,GRAY,COM}
	    };
		case FLOWER_POT:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{DEF,new ItemStack(Blocks.FLOWER_POT),DEF},
			{DEF,GRAY,DEF}
	    };
//		case KERNEL_PULT:return new ItemStack[][]{
//			{DEF,DEF,DEF},
//			{DEF,new ItemStack(Items.PUMPKIN_SEEDS),DEF},
//			{DEF,WHITE,DEF}
//	    };
		case COFFEE_BEAN:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{MAG,new ItemStack(Blocks.COCOA),MAG},
			{MAG,GREEN,MAG}
	    };
		case MARIGOLD:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{MAG,new ItemStack(Blocks.RED_FLOWER,1,8),MAG},
			{MAG,GREEN,MAG}
	    };
		case MELON_PULT:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{COM,new ItemStack(Items.MELON_SEEDS),COM},
			{COM,PURPLE,COM}
	    };
		case ICEBERG_LETTUCE:return new ItemStack[][]{
			{ORI,ORI,ORI},
			{ICE,new ItemStack(ItemRegister.CABBAGE),ICE},
			{ICE,WHITE,ICE}
	    };
		default:return null;
		}
	}
	
	/**
	 * 通过中间的物品来找配方，高效
	 */
	public static Plants[] getMatchPlantByMidItem(Item item)
	{
//		System.out.println(item+" "+Item.getItemFromBlock(Blocks.DOUBLE_PLANT));
		if(item==ItemRegister.PEA) {
			return new Plants[] {Plants.PEA_SHOOTER};
		}else if(item==new ItemStack(Blocks.DOUBLE_PLANT,1,0).getItem()) {
			return new Plants[] {Plants.SUN_FLOWER};
		}else if(item==ItemRegister.NUT) {
			return new Plants[] {Plants.NUT_WALL};
		}else if(item==Items.POTATO) {
			return new Plants[] {Plants.POTATO_MINE};
		}else if(item==ItemRegister.SNOW_PEA) {
			return new Plants[] {Plants.SNOW_PEA};
		}else if(item==ItemRegister.PEA_SHOOTER_CARD) {
			return new Plants[] {Plants.DOUBLE_SHOOTER,Plants.THREE_PEATER};
		}else if(item==new ItemStack(Blocks.RED_MUSHROOM).getItem()) {
			return new Plants[] {Plants.HYPNO_SHROOM};
		}else if(item==new ItemStack(Blocks.BROWN_MUSHROOM).getItem()) {
			return new Plants[] {Plants.ICE_SHROOM};
		}else if(item==new ItemStack(BlockRegister.LILY_PAD).getItem()) {
			return new Plants[] {Plants.LILY_PAD};
		}
//		else if(item==new ItemStack(Blocks.BROWN_MUSHROOM).getItem()) {
//			return new Plants[] {Plants.ICE_SHROOM};
//		}
	    else if(item==new ItemStack(Blocks.VINE).getItem()) {
			return new Plants[] {Plants.TANGLE_KELP};
		}else if(item==new ItemStack(Blocks.TALLGRASS).getItem()) {
			return new Plants[] {Plants.SPIKE_WEED};
		}else if(item==ItemRegister.NUT_WALL_CARD) {
			return new Plants[] {Plants.TALL_NUT};
		}else if(item==COM.getItem()) {
			return new Plants[] {Plants.SPLIT_PEA};
		}else if(item==Items.PUMPKIN_SEEDS) {
			return new Plants[] {Plants.PUMPKIN};
		}else if(item==ItemRegister.CABBAGE) {
			return new Plants[] {Plants.CABBAGE_PULT,Plants.ICEBERG_LETTUCE};
		}else if(item==new ItemStack(Blocks.FLOWER_POT).getItem()) {
			return new Plants[] {Plants.FLOWER_POT};
		}else if(item==new ItemStack(Blocks.COCOA).getItem()) {
			return new Plants[] {Plants.COFFEE_BEAN};
		}else if(item==new ItemStack(Blocks.RED_FLOWER,1,8).getItem()) {
			return new Plants[] {Plants.MARIGOLD};
		}else if(item==Items.MELON_SEEDS) {
			return new Plants[] {Plants.MELON_PULT};
		}
		return null;
	}
}
