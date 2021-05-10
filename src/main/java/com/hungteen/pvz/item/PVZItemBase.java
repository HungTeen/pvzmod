package com.hungteen.pvz.item;

import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.item.Item;

public class PVZItemBase extends Item{

	public PVZItemBase() {
		super(new Properties().tab(GroupRegister.PVZ_MISC));
	}
	
	public PVZItemBase(Properties p) {
		super(p.tab(GroupRegister.PVZ_MISC));
	}
	
}
