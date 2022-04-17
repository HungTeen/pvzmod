package com.hungteen.pvz.common.sound;

import com.hungteen.pvz.common.PVZDamageSource;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 22:43
 **/
public class SoundManager {

    /**
     * handle sound when living hurt.
     * {@link PVZLivingEvents#onLivingHurt(net.minecraftforge.event.entity.living.LivingHurtEvent)}
     */
    public static void handleHurtSounds(LivingEntity target, PVZDamageSource source) {
        if(source.isEatDamage()) {
            EntityUtil.playSound(target, PVZSounds.CHOMP.get());
        }
        if(source.isFlameDamage() && source.isAppease()) {
            EntityUtil.playSound(target, PVZSounds.FLAME_HIT.get());
        }
    }

    /**
     * {@link com.hungteen.pvz.common.CombatManager#hurtCurrentlyUsedShield(LivingEntity, InteractionHand, ItemStack, float)}
     */
    public static void playShieldHurtSound(LivingEntity entity, ItemStack stack){
//        EntityUtil.playSound(entity, SoundEvents.SHIELD_BREAK);
    }

    /**
     * {@link com.hungteen.pvz.common.CombatManager#hurtCurrentlyUsedShield(LivingEntity, InteractionHand, ItemStack, float)}
     */
    public static void playShieldBreakSound(LivingEntity entity, ItemStack stack){
        EntityUtil.playSound(entity, SoundEvents.SHIELD_BREAK);
    }

}
