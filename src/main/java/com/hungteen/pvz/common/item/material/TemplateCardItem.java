package com.hungteen.pvz.common.item.material;

import com.hungteen.pvz.common.item.PVZItemBase;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.utils.enums.Ranks;

public class TemplateCardItem extends PVZItemBase {

	public final Ranks Rank;
	
	public TemplateCardItem(Ranks rank) {
		super(new Properties().tab(GroupRegister.PVZ_MISC));
		this.Rank = rank;
	}
	
}
