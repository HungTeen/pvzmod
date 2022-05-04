package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.PVZDamageSource;
import com.hungteen.pvz.common.entity.PVZEntities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-29 11:32
 **/
public class CabbageBullet extends PultProjectile{

    public CabbageBullet(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    public CabbageBullet(Level level, LivingEntity livingEntity) {
        super(PVZEntities.CABBAGE_BULLET.get(), level, livingEntity);
    }

    @Override
    protected void dealDamageTo(Entity target) {
        super.dealDamageTo(target);
        this.canExist = false;

        //default normal pea damage.
        DamageSource source = PVZDamageSource.cabbage(this, this.getOwner());

        hurtTarget(target, source, this.getAttackDamage());
    }

    @Override
    public float getAttackDamage() {
        float damage = this.attackDamage;

        if(this.getBulletType() == BulletTypes.BIG){
            damage += 20;
        }

        return damage;
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.5f, 0.5f);
    }

}
