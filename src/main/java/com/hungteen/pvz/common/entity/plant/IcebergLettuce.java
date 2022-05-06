package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.PVZDamageSource;
import com.hungteen.pvz.common.effect.PVZEffects;
import com.hungteen.pvz.common.entity.plant.base.CloseInstantPlant;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.utils.EffectUtil;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-06 13:20
 **/
public class IcebergLettuce extends CloseInstantPlant {

    private static final int FROZEN_TICK = 200;

    public IcebergLettuce(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void performAttack(LivingEntity target) {
//        for(int i = 0; i < 2; ++ i) {
//            EntityUtil.spawnParticle(this, 5);
//        }
        EntityUtil.playSound(this, PVZSounds.FROZEN.get());
        this.dealDamageTo(target);
        this.discard();
    }

    @Override
    public void onEnergetic(boolean first) {
        super.onEnergetic(first);
        final float range = 20;
//        for(int i = 0; i < 2; ++ i) {
//            EntityUtil.spawnParticle(this, 5);
//        }
        EntityUtil.playSound(this, PVZSounds.FROZEN.get());
        int cnt = 0;
        for(Entity target : EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range))) {
            this.dealDamageTo(target);
            if(target instanceof LivingEntity && EntityUtil.isEntityCold((LivingEntity) target)) {
                ++ cnt;
            }
        };
//        Player player = EntityUtil.getEntityOwner(level, this);
//        if(player != null && player instanceof ServerPlayer) {
//            EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this, cnt);
//        }
    }

    private void dealDamageTo(Entity target) {
        PVZDamageSource source = PVZDamageSource.causeIceDamage(this, this);
        source.addEffect(EffectUtil.effect(PVZEffects.FROZEN_EFFECT.get(), FROZEN_TICK, 0));
        source.addEffect(EffectUtil.effect(PVZEffects.COLD_EFFECT.get(), FROZEN_TICK * 2, 4));
        target.hurt(source, 0.001F);
    }

    @Override
    public EntityDimensions getDimensions(Pose p_21047_) {
        return EntityDimensions.scalable(0.5F, 0.5F);
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.ICEBERG_LETTUCE;
    }
}
