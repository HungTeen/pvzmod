package com.hungteen.pvz.common;

import com.hungteen.pvz.api.events.PVZShieldBlockEvent;
import com.hungteen.pvz.common.effect.PVZEffects;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.sound.SoundManager;
import com.hungteen.pvz.common.tag.PVZItemTags;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ItemUtil;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 20:20
 **/
public class CombatManager {

    /**
     * {@link com.hungteen.pvz.common.event.PVZLivingEvents#onLivingHurt(LivingHurtEvent)}
     * Holding shield tag item to block damage.
     */
    public static void onLivingBlockHurt(LivingHurtEvent ev) {
        if(ItemUtil.isShieldItem(ev.getEntityLiving().getMainHandItem())){
            blockDamage(ev, InteractionHand.MAIN_HAND);
        }
        if(ItemUtil.isShieldItem(ev.getEntityLiving().getOffhandItem())){
            blockDamage(ev, InteractionHand.OFF_HAND);
        }
    }

    /**
     * {@link com.hungteen.pvz.common.event.PVZLivingEvents#onLivingHurt(LivingHurtEvent)}
     * Holding shield tag item to block damage.
     */
    public static void handleInvulnerableTime(LivingEntity entity, DamageSource damageSource) {
        if(damageSource instanceof PVZDamageSource){
//        	System.out.println("???");
            if(EntityUtil.ignoreInvulnerableTime(entity)){
                entity.invulnerableTime = 0;
            }
        }
    }

    /**
     * apply potion effects on living.
     * {@link PVZLivingEvents#onLivingHurt(net.minecraftforge.event.entity.living.LivingHurtEvent)}
     */
    public static void handleHurtEffects(LivingEntity target, PVZDamageSource source) {
        if(! source.isDefended()) {//source not defended by armor.
            if(source.isFlameDamage()) {
                if(target.hasEffect(PVZEffects.COLD_EFFECT.get())){
                    target.removeEffect(PVZEffects.COLD_EFFECT.get());
                }
//                if(target.hasEffect(PVZEffects.FROZEN_EFFECT.get())){
//                    target.removeEffect(PVZEffects.FROZEN_EFFECT.get());
//                }
            }
            source.getEffects().forEach(effect -> EntityUtil.addPotionEffect(target, effect));
        }
    }

    public static void blockDamage(LivingHurtEvent ev, InteractionHand hand){
        final LivingEntity entity = ev.getEntityLiving();
        final DamageSource damageSource = ev.getSource();
        final ItemStack stack = entity.getItemInHand(hand);
        float amount = ev.getAmount();
        if (isDamageSourceBlocked(entity, damageSource)) {//shield help reduce damage successfully ?
//        	System.out.print("yes");
            final PVZShieldBlockEvent event = new PVZShieldBlockEvent(entity, damageSource, amount);
            if (!MinecraftForge.EVENT_BUS.post(event)) {//shield really helpful.
                if (event.shieldTakesDamage()) {//damage item.
                    hurtCurrentlyUsedShield(entity, hand, stack, amount);
                }
                //effect to attacker.
                blockUsingShield(entity, damageSource);
                ev.setAmount(0);
            }
        }
    }
    
    public static void blockUsingShield(LivingEntity entity, DamageSource source){
        if(! source.isProjectile()){
            Entity attacker = source.getDirectEntity();
            if (attacker instanceof LivingEntity) {
                ((LivingEntity) attacker).knockback(0.4D, entity.getX() - attacker.getX(), entity.getZ() - attacker.getZ());
            }
        }
        if(source instanceof PVZDamageSource){
            ((PVZDamageSource) source).setDefended(true);
        }
    }

    /**
     * Copy from {@link Player}
     */
    public static void hurtCurrentlyUsedShield(LivingEntity entity, InteractionHand hand, ItemStack stack, float amount) {
        if (stack.canPerformAction(net.minecraftforge.common.ToolActions.SHIELD_BLOCK)) {
            if (entity instanceof Player) {
                ((Player) entity).awardStat(Stats.ITEM_USED.get(stack.getItem()));
            }

            int damageValue = 1 + Mth.floor(amount);
            stack.hurtAndBreak(damageValue, entity, (living) -> {
                living.broadcastBreakEvent(hand);
                if (entity instanceof Player) {
                    net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem((Player) entity, stack, hand);
                }
            });

            if (stack.isEmpty()) {
                if (hand == InteractionHand.MAIN_HAND) {
                    entity.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                } else {
                    entity.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                }

                SoundManager.playShieldBreakSound(entity, stack);
            } else{
                SoundManager.playShieldHurtSound(entity, stack);
            }

        }
    }

    public static boolean isDamageSourceBlocked(LivingEntity entity, DamageSource damageSource) {
        if (entity.isDamageSourceBlocked(damageSource)) {
//            if(damageSource instanceof PVZDamageSource){
//
//            }
            return true;
        }
        return false;
    }

}
