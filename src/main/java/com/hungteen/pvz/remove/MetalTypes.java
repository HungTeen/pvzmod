package com.hungteen.pvz.remove;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.item.ItemRegister;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum MetalTypes {

	EMPTY,
	BUCKET_HEAD,
	SCREEN_DOOR,
	FOOTBALL_HELMET,
	GIGA_HELMET,
	JACK_BOX,
	IRON_PICKAXE,
	POGO,
	LADDER;
	
	public static Item getMetalItem(MetalTypes type) {
		switch (type) {
		case BUCKET_HEAD: return ItemRegister.BUCKET_HEAD.get();
		case SCREEN_DOOR: return ItemRegister.SCREEN_DOOR.get();
		case FOOTBALL_HELMET: return ItemRegister.FOOTBALL_HELMET.get();
		case GIGA_HELMET: return ItemRegister.GIGA_HELMET.get();
		case JACK_BOX: return ItemRegister.JACK_BOX.get();
		case IRON_PICKAXE: return Items.IRON_PICKAXE;
		case LADDER: return BlockRegister.STEEL_LADDER.get().asItem();
		default:
			return Items.IRON_INGOT;
		}
	}
}
