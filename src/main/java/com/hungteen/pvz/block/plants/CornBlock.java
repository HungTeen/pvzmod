package com.hungteen.pvz.block.plants;

import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

public class CornBlock extends CropsBlock {

	public CornBlock(Properties builder) {
		super(builder);
	}

	@Override
	protected IItemProvider getSeedsItem() {
		return ItemRegister.CORN_SEEDS.get();
	}
	
}
