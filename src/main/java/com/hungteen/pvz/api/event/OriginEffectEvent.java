package com.hungteen.pvz.api.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 09:33
 *
 * fire when origin block grow nearby block to essence ore.
 */
@Cancelable
public class OriginEffectEvent extends Event {

    private final Level world;
    private final BlockPos pos;

    public OriginEffectEvent(Level world, BlockPos pos){
        this.world = world;
        this.pos = pos;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Level getWorld() {
        return world;
    }

}
