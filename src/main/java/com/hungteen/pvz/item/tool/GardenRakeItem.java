package com.hungteen.pvz.item.tool;

import com.hungteen.pvz.entity.misc.GardenRakeEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GardenRakeItem extends Item {

	public GardenRakeItem() {
		super(new Item.Properties().group(GroupRegister.PVZ_MISC));
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getHeldItem(hand);
		BlockPos pos = context.getPos();
		BlockPos spawnPos = pos;
		if (! world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
			spawnPos = pos.offset(context.getFace());
		}
		if (context.getFace() == Direction.UP && world.isAirBlock(pos.up())) {// can plant here
			if(!world.isRemote) {
				GardenRakeEntity entity = (GardenRakeEntity) EntityRegister.GARDEN_RAKE.get().spawn(player.world, stack, player,
					spawnPos, SpawnReason.SPAWN_EGG, true, true);
			    if (entity == null) {
				    System.out.println("Error : garden rake entity spawn error!");
				    return ActionResultType.FAIL;
			    }
			    entity.setPlacer(player);
			    if (! player.abilities.isCreativeMode) {// reset
				    stack.shrink(1);
			    }
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}

}
