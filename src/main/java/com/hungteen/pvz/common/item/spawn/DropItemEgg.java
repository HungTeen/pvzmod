package com.hungteen.pvz.common.item.spawn;

import com.hungteen.pvz.common.entity.drop.PVZDrop;
import com.hungteen.pvz.common.item.base.PVZMiscItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-13 09:23
 **/
public class DropItemEgg extends PVZMiscItem {

    private final Supplier<EntityType<? extends PVZDrop>> entityTypeSupplier;

    public DropItemEgg(Supplier<EntityType<? extends PVZDrop>> entityTypeSupplier) {
        this.entityTypeSupplier = entityTypeSupplier;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);
            BlockPos blockpos1;
            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            EntityType<?> entitytype = this.entityTypeSupplier.get();
            if (entitytype.spawn((ServerLevel)level, itemstack, context.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
                itemstack.shrink(1);
                level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
            }

            return InteractionResult.CONSUME;
        }
    }

}
