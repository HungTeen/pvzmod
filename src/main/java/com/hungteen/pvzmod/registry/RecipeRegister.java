package com.hungteen.pvzmod.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegister {

	public static void init()
	{
		GameRegistry.addSmelting(new ItemStack(BlockRegister.ALUMINUM_ORE,1),new ItemStack(ItemRegister.ALUMINUM_INGOT,1), 1.5f);
		GameRegistry.addSmelting(new ItemStack(Blocks.COAL_BLOCK,1), new ItemStack(ItemRegister.COAL_NUGGET,1), 1.5f);
		
	}
}
