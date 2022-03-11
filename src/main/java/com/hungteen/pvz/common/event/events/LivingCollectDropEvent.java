package com.hungteen.pvz.common.event.events;

import com.hungteen.pvz.common.entity.drop.DropEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 09:22
 *
 * This event is fired after the player collect drop(collide or use collector), but before the player has been added. <br>
 * It can be cancelled, and no further processing will be done.
 **/
@Cancelable
public class LivingCollectDropEvent extends LivingEvent {

    protected final DropEntity dropEntity;

    public LivingCollectDropEvent(LivingEntity livingEntity, DropEntity dropEntity) {
        super(livingEntity);
        this.dropEntity = dropEntity;
    }

    public DropEntity getDropEntity() {
        return dropEntity;
    }
}
