package com.hungteen.pvzmod.items.tools;

import com.hungteen.pvzmod.items.ItemBase;
import com.hungteen.pvzmod.registry.CreativeTabRegister;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemDaveShovel extends ToolShovel{

	public ItemDaveShovel(String name, ToolMaterial material) {
		super(name, material);
		setMaxDamage(-1);
	}

}
