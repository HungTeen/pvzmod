package com.hungteen.pvz.common.item.tool.zombie;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.misc.BobsleCarEntity;
import com.hungteen.pvz.common.item.PVZItemGroups;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class BobsleCarItem extends Item {

    public BobsleCarItem() {
        super(new Properties().tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1));
    }

    @Override
    public InteractionResult useOn(ItemUseContext context) {
        InteractionHand hand = context.getHand();
        Player player = context.getPlayer();
        ItemStack stack = player.getItemInHand(hand);
        Mth pos = context.getClickedPos();
        Level world = context.getLevel();
        if (hand == InteractionHand.OFF_HAND) {//only use right hand can plant
            return InteractionResult.FAIL;
        }
        if (!world.isClientSide && context.getClickedFace() == Direction.UP && world.isEmptyBlock(pos.above())) {//can plant here
            stack.shrink(1);
            BobsleCarEntity car = EntityRegister.BOBSLE_CAR.get().create(world);
            car.yRot = player.yRot;
            car.setPos(pos.getX() + 0.5D, pos.getY() + 1, pos.getZ() + 0.5D);
            world.addFreshEntity(car);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResult.SUCCESS;
    }

}
