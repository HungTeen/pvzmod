package com.hungteen.pvz.common.item.blockitem;

import com.hungteen.pvz.common.item.PVZItemGroups;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockItemBase extends BlockItem{

	public BlockItemBase(Block blockIn) {
		super(blockIn,new Item.Properties().tab(PVZItemGroups.PVZ_MISC));
	}

}
