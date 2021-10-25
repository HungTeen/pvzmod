package com.hungteen.pvz.common.item.material;

import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.item.PVZItemBase;
import com.hungteen.pvz.common.item.PVZItemGroups;

public class TemplateCardItem extends PVZItemBase {

	public final IRankType Rank;
	
	public TemplateCardItem(IRankType rank) {
		super(new Properties().tab(PVZItemGroups.PVZ_MISC));
		this.Rank = rank;
	}
	
}
