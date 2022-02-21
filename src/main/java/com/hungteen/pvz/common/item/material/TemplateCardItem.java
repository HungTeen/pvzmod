package com.hungteen.pvz.common.item.material;

import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.item.PVZMiscItem;
import com.hungteen.pvz.common.item.PVZItemGroups;

public class TemplateCardItem extends PVZMiscItem {

	public final IRankType Rank;
	
	public TemplateCardItem(IRankType rank) {
		super(new Properties().tab(PVZItemGroups.PVZ_MISC));
		this.Rank = rank;
	}
	
}
