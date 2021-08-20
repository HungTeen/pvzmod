package com.hungteen.pvz.common.item.material;

import com.hungteen.pvz.common.core.CardRank;
import com.hungteen.pvz.common.item.PVZItemBase;
import com.hungteen.pvz.register.GroupRegister;

public class TemplateCardItem extends PVZItemBase {

	public final CardRank Rank;
	
	public TemplateCardItem(CardRank rank) {
		super(new Properties().tab(GroupRegister.PVZ_MISC));
		this.Rank = rank;
	}
	
}
