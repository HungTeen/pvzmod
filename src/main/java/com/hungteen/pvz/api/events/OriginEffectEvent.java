package com.hungteen.pvz.api.events;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * fire when origin block grow nearby block to essence ore.
 */
@Cancelable
public class OriginEffectEvent extends Event {

    private final World world;
    private final BlockPos pos;

    public OriginEffectEvent(World world, BlockPos pos){
        this.world = world;
        this.pos = pos;
    }

    public BlockPos getPos() {
        return pos;
    }

    public World getWorld() {
        return world;
    }

}
