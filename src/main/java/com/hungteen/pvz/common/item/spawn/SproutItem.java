package com.hungteen.pvz.common.item.spawn;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.entity.creature.garden.GardenPlant;
import com.hungteen.pvz.common.item.base.PVZUsefulItem;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Objects;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-21 13:06
 **/
public class SproutItem extends PVZUsefulItem {

    public SproutItem(){

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

            IPlantType plantType = GardenPlant.getRandomType(level.random);
            if(plantType != null){
                Entity entity = PVZEntities.COMMON_GARDEN_PLANT.get().spawn((ServerLevel)level, itemstack, context.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
                if (entity instanceof GardenPlant) {
                    ((GardenPlant) entity).setPlantType(plantType);
                    EntityUtil.playSound(entity, PVZSounds.PLACE_PLANT_GROUND.get());
                    itemstack.shrink(1);
                    level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
                } else{
                    entity.discard();
                }
            }

            return InteractionResult.CONSUME;
        }
    }
}
