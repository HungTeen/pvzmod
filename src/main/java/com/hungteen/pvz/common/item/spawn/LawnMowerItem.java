package com.hungteen.pvz.common.item.spawn;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.misc.LawnMowerEntity;
import com.hungteen.pvz.common.item.PVZToolItem;
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
import net.minecraft.world.server.ServerWorld;

public class LawnMowerItem extends PVZToolItem {

	public LawnMowerItem() {
		super(new Item.Properties());
	}
	
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getItemInHand(hand);
		BlockPos pos = context.getClickedPos();
		BlockPos spawnPos = pos;
		if (! world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
			spawnPos = pos.relative(context.getClickedFace());
		}
		if (context.getClickedFace() == Direction.UP && world.isEmptyBlock(pos.above())) {// can plant here
			if(!world.isClientSide) {
				LawnMowerEntity entity = (LawnMowerEntity) EntityRegister.LAWN_MOWER.get().spawn((ServerWorld) player.level, stack, player,
					spawnPos, SpawnReason.SPAWN_EGG, true, true);
			    if (entity == null) {
				    System.out.println("Error : lawn mower entity spawn error!");
				    return ActionResultType.FAIL;
			    }
			    entity.setPlacer(player);
			    if (! player.abilities.instabuild) {// reset
				    stack.shrink(1);
			    }
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}

}
