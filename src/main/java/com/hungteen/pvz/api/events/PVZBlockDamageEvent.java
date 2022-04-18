package com.hungteen.pvz.api.events;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 21:08
 *
 * Copy from {@link net.minecraftforge.event.entity.living.ShieldBlockEvent}
 **/
@Cancelable
public class PVZBlockDamageEvent extends LivingEvent {

    private final DamageSource source;
    private final float originalBlocked;
    private float dmgBlocked;
    private boolean shouldTakesDamage = true;

    public PVZBlockDamageEvent(LivingEntity blocker, ItemStack stack, DamageSource source, float blocked) {
        super(blocker);
        this.source = source;
        this.originalBlocked = blocked;
        this.dmgBlocked = blocked;
    }

    /**
     * @return The damage source.
     */
    public DamageSource getDamageSource()
    {
        return this.source;
    }

    /**
     * @return The original amount of damage blocked, which is the same as the original
     * incoming damage value.
     */
    public float getOriginalBlockedDamage() {
        return this.originalBlocked;
    }

    /**
     * @return The current amount of damage blocked, as a result of this event.
     */
    public float getBlockedDamage() {
        return this.dmgBlocked;
    }

    /**
     * @return If the shield item will take durability damage or not.
     */
    public boolean shouldTakesDamage() {
        return this.shouldTakesDamage;
    }

    /**
     * Set if the shield will take durability damage or not.
     */
    public void setShouldTakesDamage(boolean damage) {
        this.shouldTakesDamage = damage;
    }

    public static class PVZShieldBlockEvent extends PVZBlockDamageEvent{

        public PVZShieldBlockEvent(LivingEntity blocker, ItemStack stack, DamageSource source, float blocked) {
            super(blocker, stack, source, blocked);
        }
    }

    public static class PVZArmorBlockEvent extends PVZBlockDamageEvent{

        public PVZArmorBlockEvent(LivingEntity blocker, ItemStack stack, DamageSource source, float blocked) {
            super(blocker, stack, source, blocked);
        }
    }

}