package com.hungteen.pvz.common.item.material;

import com.hungteen.pvz.common.core.EssenceType;
import com.hungteen.pvz.common.item.PVZItemGroups;

import net.minecraft.item.Item;

public class EssenceItem extends Item {

	public final EssenceType essence;
	
	public EssenceItem(EssenceType essence) {
		super(new Properties().tab(PVZItemGroups.PVZ_MISC));
		this.essence = essence;
	}

}
