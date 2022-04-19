package com.hungteen.pvz.common.item.spawn;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 19:03
 **/
public class PVZBoatItem extends BoatItem {

    public PVZBoatItem(Properties properties) {
        super(Boat.Type.OAK, properties);
    }

//    /**
//     * Copy from {@link BoatItem#use(Level, Player, InteractionHand)}
//     */
//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        final ItemStack itemstack = player.getItemInHand(hand);
//        final HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
//        if (hitresult.getType() == HitResult.Type.MISS) {
//            return InteractionResultHolder.pass(itemstack);
//        } else {
//            final Vec3 vec3 = player.getViewVector(1.0F);
//            final double d0 = 5.0D;
//            List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
//            if (!list.isEmpty()) {
//                Vec3 vec31 = player.getEyePosition();
//
//                for(Entity entity : list) {
//                    AABB aabb = entity.getBoundingBox().inflate((double)entity.getPickRadius());
//                    if (aabb.contains(vec31)) {
//                        return InteractionResultHolder.pass(itemstack);
//                    }
//                }
//            }
//
//            if (hitresult.getType() == HitResult.Type.BLOCK) {
//                PVZBoat boat = new PVZBoat(level, hitresult.getLocation().x, hitresult.getLocation().y, hitresult.getLocation().z, () -> this);
//                boat.setYRot(player.getYRot());
//                if (!level.noCollision(boat, boat.getBoundingBox())) {
//                    return InteractionResultHolder.fail(itemstack);
//                } else {
//                    if (!level.isClientSide) {
//                        level.addFreshEntity(boat);
//                        level.gameEvent(player, GameEvent.ENTITY_PLACE, new BlockPos(hitresult.getLocation()));
//                        if (!player.getAbilities().instabuild) {
//                            itemstack.shrink(1);
//                        }
//                    }
//
//                    player.awardStat(Stats.ITEM_USED.get(this));
//                    return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
//                }
//            } else {
//                return InteractionResultHolder.pass(itemstack);
//            }
//        }
//    }

}
