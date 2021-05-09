package com.hungteen.pvz.item.blockitem;

import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockItemBase extends BlockItem{

	public BlockItemBase(Block blockIn) {
		super(blockIn,new Item.Properties().tab(GroupRegister.PVZ_MISC));
	}

}
