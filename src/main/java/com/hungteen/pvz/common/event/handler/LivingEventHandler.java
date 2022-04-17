package com.hungteen.pvz.common.event.handler;

import com.hungteen.pvz.api.interfaces.IPAZEntity;
import com.hungteen.pvz.api.interfaces.IZombieEntity;
import com.hungteen.pvz.common.PVZDamageSource;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-17 20:10
 **/
public class LivingEventHandler {

    /**
     * balance the damage between different mods.
     * {@link PVZLivingEvents#onLivingHurt(net.minecraftforge.event.entity.living.LivingHurtEvent)}
     */
    public static void handleHurtDamage(final LivingHurtEvent ev) {
        //all paz entity can not deal more than limit damage to other entities.
//        if(ev.getSource() instanceof PVZDamageSource && ! (ev.getEntityLiving() instanceof IPAZEntity && ev.getSource().getEntity() instanceof IPAZEntity)){
//            ev.setAmount(Math.min(ConfigUtil.getLimitDamage(), ev.getAmount()));
//        }
//        //(not boss)zombie damage to zombie or both are boss entity.
//        if(ev.getEntityLiving() instanceof IZombieEntity && ev.getSource().getEntity() instanceof IZombieEntity && (! (ev.getSource().getEntity() instanceof AbstractBossZombieEntity) || (ev.getEntityLiving() instanceof AbstractBossZombieEntity))){
//            ev.setAmount(Math.min(100, ev.getAmount()));
//        }
//        //avoid instant kill mod.
//        if(ev.getSource() != DamageSource.OUT_OF_WORLD && ev.getAmount() > ev.getEntityLiving().getMaxHealth() * 0.8 && ev.getEntityLiving() instanceof AbstractBossZombieEntity){
//            ev.setAmount(ConfigUtil.getLimitDamage());
//        }
    }

}
