package com.hungteen.pvz.common.item.material;

import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.common.item.PVZItemGroups;
import net.minecraft.item.Item;

public class EssenceItem extends Item {

	public final IEssenceType essence;
	
	public EssenceItem(IEssenceType essence) {
		super(new Properties().tab(PVZItemGroups.PVZ_MISC));
		this.essence = essence;
	}

}
