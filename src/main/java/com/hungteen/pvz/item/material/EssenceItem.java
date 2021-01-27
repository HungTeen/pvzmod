package com.hungteen.pvz.item.material;

import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.utils.enums.Essences;

import net.minecraft.item.Item;

public class EssenceItem extends Item {

	public final Essences essence;
	
	public EssenceItem(Essences essence) {
		super(new Properties().group(GroupRegister.PVZ_MISC));
		this.essence = essence;
	}

}
