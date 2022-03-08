package com.hungteen.pvz.common.item.spawn;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.misc.GardenRakeEntity;
import com.hungteen.pvz.common.item.PVZItemGroups;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class GardenRakeItem extends Item {

	public GardenRakeItem() {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL));
	}
	
	@Override
	public InteractionResult useOn(ItemUseContext context) {
		Level world = context.getLevel();
		Player player = context.getPlayer();
		InteractionHand hand = context.getHand();
		ItemStack stack = player.getItemInHand(hand);
		Mth pos = context.getClickedPos();
		Mth spawnPos = pos;
		if (! world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
			spawnPos = pos.relative(context.getClickedFace());
		}
		if (context.getClickedFace() == Direction.UP && world.isEmptyBlock(pos.above())) {// can plant here
			if(!world.isClientSide) {
				GardenRakeEntity entity = (GardenRakeEntity) EntityRegister.GARDEN_RAKE.get().spawn((ServerLevel) player.level, stack, player,
					spawnPos, SpawnReason.SPAWN_EGG, true, true);
			    if (entity == null) {
				    System.out.println("Error : garden rake entity spawn error!");
				    return InteractionResult.FAIL;
			    }
			    entity.setPlacer(player);
			    entity.summonByOwner(player);
			    if (! player.abilities.instabuild) {// reset
				    stack.shrink(1);
			    }
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

}
