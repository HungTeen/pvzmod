package com.hungteen.pvz.items;

import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.item.Item;

public class PVZItemBase extends Item{

	public PVZItemBase() {
		super(new Item.Properties().group(GroupRegister.GROUP_MATERIALS));
		
	}

}
