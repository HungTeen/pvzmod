package com.hungteen.pvz.common.item;

import net.minecraft.item.Item;

public class PVZItemBase extends Item{

	public PVZItemBase() {
		super(new Properties().tab(PVZItemGroups.PVZ_MISC));
	}
	
	public PVZItemBase(Properties p) {
		super(p.tab(PVZItemGroups.PVZ_MISC));
	}
	
}
