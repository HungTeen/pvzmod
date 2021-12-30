package com.hungteen.pvz.api.types;

import com.hungteen.pvz.common.block.cubes.OriginBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

import java.util.Optional;

/**
 * essence type of plant & zombie.
 */
public interface IEssenceType {

	/**
	 * tags contain blocks which can interact with {@link OriginBlock} to be radiated.
	 */
	Optional<ITag.INamedTag<Block>> getRadiationBlockTag();

	/**
	 * corresponding block that can be radiated to essence ore.<br>
	 * players can modify the block tag to add or delete corresponding blocks.
	 */
	Optional<Block> getRadiationBlock();

	/**
	 * corresponding essence item.
	 */
	Item getEssenceItem();

	/**
	 * corresponding essence ore.
	 */
	Block getEssenceOre();
	
	String toString();
	
}
