package com.hungteen.pvz.common.item.material;

import com.hungteen.pvz.common.core.RankType;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.item.PVZItemBase;

public class TemplateCardItem extends PVZItemBase {

	public final RankType Rank;
	
	public TemplateCardItem(RankType rank) {
		super(new Properties().tab(PVZItemGroups.PVZ_MISC));
		this.Rank = rank;
	}
	
}
