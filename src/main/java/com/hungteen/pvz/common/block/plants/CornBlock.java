package com.hungteen.pvz.common.block.plants;

import com.hungteen.pvz.common.item.ItemRegister;

import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

public class CornBlock extends CropsBlock {

	public CornBlock(Properties builder) {
		super(builder);
	}

	@Override
	protected IItemProvider getBaseSeedId() {
		return ItemRegister.CORN_SEEDS.get();
	}
	
}
