package com.hungteen.pvz.common.item.spawn.card;

import com.hungteen.pvz.common.item.base.PVZUsefulItem;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 21:00
 *
 * //TODO Easter Egg for Summer Y
 **/
public class SummerCardItem extends PVZUsefulItem {

//    @Override
//    public InteractionResult useOn(UseOnContext context) {
//        Level level = context.getLevel();
//        if (!(level instanceof ServerLevel)) {
//            return InteractionResult.SUCCESS;
//        } else {
//            ItemStack itemstack = context.getItemInHand();
//            BlockPos blockpos = context.getClickedPos();
//            Direction direction = context.getClickedFace();
//            BlockState blockstate = level.getBlockState(blockpos);
//            BlockPos blockpos1;
//            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
//                blockpos1 = blockpos;
//            } else {
//                blockpos1 = blockpos.relative(direction);
//            }
//
//            EntityType<?> entitytype = this.entityTypeSupplier.get();
//            if (entitytype.spawn((ServerLevel)level, itemstack, context.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
//                itemstack.shrink(1);
//                level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
//            }
//
//            return InteractionResult.CONSUME;
//        }
//    }
//
}
