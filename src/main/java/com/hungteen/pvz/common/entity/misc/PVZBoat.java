package com.hungteen.pvz.common.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 19:07
 *
 * TODO Custom Boat !
 **/
public class PVZBoat extends Boat {

    private final Supplier<? extends Item> drop;

    public PVZBoat(EntityType<? extends PVZBoat> type, Level level, Supplier<? extends Item> drop) {
        super(type, level);
        this.drop = drop;
    }

//    public PVZBoat(Level level, double x, double y, double z, Supplier<? extends Item> drop) {
//        this(type, level, drop);
//        this.setPos(x, y, z);
//        this.xo = x;
//        this.yo = y;
//        this.zo = z;
//    }

    /**
     * Copy from {@link Boat#checkFallDamage(double, boolean, BlockState, BlockPos)}
     */
    @Override
    @Nullable
    public ItemEntity spawnAtLocation(ItemLike like) {
//        if(like.asItem().equals(Items.OAK_PLANKS)){
//            this.spawnAtLocation()
//        }
        return this.spawnAtLocation(like, 0);
    }

    @Override
    public Item getDropItem() {
        return drop.get();
    }
    
}
