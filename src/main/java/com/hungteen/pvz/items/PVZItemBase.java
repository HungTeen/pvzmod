package com.hungteen.pvz.items;

import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.item.Item;

public class PVZItemBase extends Item{

	public PVZItemBase() {
		super(getNewProperties());
	}
	
	public static Properties getNewProperties()
	{
		return new Properties().group(GroupRegister.PVZ_GROUP);
	}
}
