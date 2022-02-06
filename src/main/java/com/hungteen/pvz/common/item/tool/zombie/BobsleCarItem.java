package com.hungteen.pvz.common.item.tool.zombie;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.misc.BobsleCarEntity;
import com.hungteen.pvz.common.item.PVZItemGroups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BobsleCarItem extends Item {

    public BobsleCarItem() {
        super(new Properties().tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1));
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        Hand hand = context.getHand();
        PlayerEntity player = context.getPlayer();
        ItemStack stack = player.getItemInHand(hand);
        BlockPos pos = context.getClickedPos();
        World world = context.getLevel();
        if (hand == Hand.OFF_HAND) {//only use right hand can plant
            return ActionResultType.FAIL;
        }
        if (!world.isClientSide && context.getClickedFace() == Direction.UP && world.isEmptyBlock(pos.above())) {//can plant here
            stack.shrink(1);
            BobsleCarEntity car = EntityRegister.BOBSLE_CAR.get().create(world);
            car.yRot = player.yRot;
            car.setPos(pos.getX() + 0.5D, pos.getY() + 1, pos.getZ() + 0.5D);
            world.addFreshEntity(car);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return ActionResultType.SUCCESS;
    }

}
