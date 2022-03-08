package com.hungteen.pvz.common.item.blockitem;

import com.hungteen.pvz.common.item.PVZItemGroups;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class BlockItemBase extends BlockItem{

	public BlockItemBase(Block blockIn) {
		super(blockIn,new Item.Properties().tab(PVZItemGroups.PVZ_MISC));
	}

}
