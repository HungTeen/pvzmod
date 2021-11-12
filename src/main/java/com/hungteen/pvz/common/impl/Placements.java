package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.api.types.ICardPlacement;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.misc.tag.PVZBlockTags;

import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;

public class Placements {

	public static final ICardPlacement NONE = block -> false;
	
	public static final ICardPlacement ANY = block -> true;
	
	public static final ICardPlacement COMMON = (block) -> {
		return block.is(PVZBlockTags.PLANT_SUIT_BLOCKS);
	};
	
	public static final ICardPlacement STABLE = (block) -> {
		return block.is(PVZBlockTags.PLANT_SUIT_BLOCKS) && ! block.is(BlockRegister.LILY_PAD.get());
	};
	
	public static final ICardPlacement GOLD = (block) -> {
		return block.is(PVZBlockTags.GOLD_TILES) || block.is(Blocks.GOLD_BLOCK);
	};
	
	public static final ICardPlacement SAND = (block) -> {
		return block.is(PVZBlockTags.PLANT_SUIT_BLOCKS) || block.is(BlockTags.SAND);
	};
	
	public static final ICardPlacement SHROOM = (block) -> {
		return block.is(PVZBlockTags.PLANT_SUIT_BLOCKS) || block.is(Blocks.MYCELIUM);
	};
	
	public static final ICardPlacement LILY_PAD = (block) -> {
		return block.is(BlockRegister.LILY_PAD.get());
	};
	
	public static final ICardPlacement WATER = (block) -> {
		return block.is(Blocks.WATER);
	};
	
}
