package com.hungteen.pvz.item;

import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.item.Item;

public class PVZItemBase extends Item{

	public PVZItemBase() {
		super(new Properties().group(GroupRegister.PVZ_MISC));
	}
	
}
