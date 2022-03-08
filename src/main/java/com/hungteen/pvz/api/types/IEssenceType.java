package com.hungteen.pvz.api.types;

import com.hungteen.pvz.common.block.cubes.OriginBlock;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;

import java.util.Optional;

/**
 * essence type of plant & zombie.
 */
public interface IEssenceType {

	/**
	 * tags contain blocks which can interact with {@link OriginBlock} to be radiated.
	 */
	Optional<TagKey<Block>> getRadiationBlockTag();

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
