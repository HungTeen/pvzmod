package com.hungteen.pvz.api.types;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import net.minecraft.block.Block;

public interface ICardPlacement {

	/**
	 * {@link PVZPlantEntity#shouldWilt()}
	 */
	boolean canPlaceOnBlock(Block block);
	
}
