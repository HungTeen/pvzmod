package com.hungteen.pvz.api.events;

import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * fire when origin block grow nearby block to essence ore.
 */
@Cancelable
public class OriginEffectEvent extends Event {

    private final Level world;
    private final Mth pos;

    public OriginEffectEvent(Level world, Mth pos){
        this.world = world;
        this.pos = pos;
    }

    public Mth getPos() {
        return pos;
    }

    public Level getWorld() {
        return world;
    }

}
