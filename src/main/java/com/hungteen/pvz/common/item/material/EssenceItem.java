package com.hungteen.pvz.common.item.material;

import com.hungteen.pvz.common.core.EssenceType;
import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.item.Item;

public class EssenceItem extends Item {

	public final EssenceType essence;
	
	public EssenceItem(EssenceType essence) {
		super(new Properties().tab(GroupRegister.PVZ_MISC));
		this.essence = essence;
	}

}
