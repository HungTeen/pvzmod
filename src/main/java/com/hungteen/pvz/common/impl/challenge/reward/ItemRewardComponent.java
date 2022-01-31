package com.hungteen.pvz.common.impl.challenge.reward;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.api.interfaces.IChallenge;
import com.hungteen.pvz.api.raid.IAmountComponent;
import com.hungteen.pvz.api.raid.IRewardComponent;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemRewardComponent implements IRewardComponent {

	public static final String NAME = "items";
	private final List<Pair<ItemStack, IAmountComponent>> items = new ArrayList<>();
	
	@Override
	public void reward(ServerPlayerEntity player) {
	}

	@Override
	public void rewardGlobally(IChallenge challenge) {
		challenge.getWorld().setBlock(challenge.getCenter(), Blocks.CHEST.defaultBlockState(), 3);
		final TileEntity te = challenge.getWorld().getBlockEntity(challenge.getCenter());
		if(te instanceof ChestTileEntity){
			for(int i = 0; i < items.size(); ++ i){
				final ItemStack stack = items.get(i).getFirst().copy();
				stack.setCount(items.get(i).getSecond().getSpawnAmount());
				((ChestTileEntity) te).setItem(i, stack);
			}
		}
	}

	@Override
	public void readJson(JsonElement json) {
		final JsonObject obj = json.getAsJsonObject();
		if(obj != null) {
//			this.reward = AdvancementRewards.deserialize(obj);
		}
	}

}
