package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.PVZDamageSource;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.item.PVZItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-31 22:43
 **/
public class PeaBullet extends PVZProjectile {

    //    public TorchWoodEntity torchWood = null;
    private int power = 0;

    public PeaBullet(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    public PeaBullet(Level level, LivingEntity owner, BulletTypes peaType, BulletStates peaState) {
        super(PVZEntities.PEA_BULLET.get(), level, owner);
        this.setBulletState(peaState);
        this.setBulletType(peaType);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.canExist = false;
    }

    @Override
    protected void dealDamageTo(Entity target) {
        super.dealDamageTo(target);
        this.canExist = false;
        final float damage = this.getAttackDamage();
        if (this.getBulletState() == BulletStates.NORMAL) {// normal pea attack
            target.hurt(PVZDamageSource.pea(this, this.getOwner()), damage);
//        } else if (this.getBulletState() == State.ICE) {// snow pea attack
//            PVZDamageSource source = PVZDamageSource.snowPea(this, this.getThrower());
//            LivingEntity owner = this.getThrower();
//            if (owner instanceof IIceEffect) {
//                ((IIceEffect) owner).getColdEffect().ifPresent(e -> source.addEffect(e));
//                ((IIceEffect) owner).getFrozenEffect().ifPresent(e -> source.addEffect(e));
//            } else if (owner instanceof PlayerEntity) {
//                source.addEffect(EffectUtil.effect(EffectRegister.COLD_EFFECT.get(), 100, 5));
//            }
//            target.hurt(source, damage);
//        } else if (this.getBulletState() == State.FIRE || this.getBulletState() == State.BLUE_FIRE) {
//            target.hurt(PVZEntityDamageSource.flamePea(this, this.getThrower()), damage);
        }
    }

//    /**
//     * {@link TorchWoodEntity#heatPeas()}
//     */
//    public void heatBy(TorchWoodEntity wood) {
//        if (this.torchWood == null || !this.torchWood.is(wood)) {// don't fire twice by the same torchwood
//            this.torchWood = wood;
//            if (this.torchWood.getFlameType() == FlameTypes.BLUE) {// blue fire
//                if (this.getBulletState() == State.ICE) {//ice to fire
//                    this.setBulletState(State.FIRE);
//                } else if (this.getBulletState().ordinal() < State.BLUE_FIRE.ordinal()) {// pea and fire to blue fire
//                    this.setBulletState(State.BLUE_FIRE);
//                }
//            } else {// fire
//                if (this.getBulletState() == State.ICE) {//ice to normal
//                    this.setBulletState(State.NORMAL);
//                } else if (this.getBulletState() == State.NORMAL) {//normal to fire
//                    this.setBulletState(State.FIRE);
//                }
//            }
//        }
//    }

    @Override
    public float getAttackDamage() {
        float damage = this.attackDamage;
        damage *= (1 + this.power * 1.0f / 5);
        // size
        if (this.getBulletType() == BulletTypes.BIG) {
            damage += 20f;
        } else if (this.getBulletType() == BulletTypes.HUGE) {
            damage += 75f;
        }
        // fire
        if (this.getBulletState() == BulletStates.FIRE) {
            damage *= 1.5F;
        } else if (this.getBulletState() == BulletStates.BLUE_FIRE) {
            damage *= 1.75F;
        }
        return damage;
    }

    @Override
    protected ParticleOptions getHitParticle() {
        return this.getBulletState() == BulletStates.NORMAL ? new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(PVZItems.PEA.get())) : null;
    }

    @Override
    public int getMaxLiveTick() {
        return 40;
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
//        if (this.getBulletType() == Type.NORMAL) {
//            return new EntitySize(0.2f, 0.2f, false);
//        }
//        if (this.getBulletType() == Type.BIG) {
//            return new EntitySize(0.4f, 0.4f, false);
//        }
//        if (this.getBulletType() == Type.HUGE) {
//            return new EntitySize(0.6f, 0.6f, false);
//        }
        return EntityDimensions.scalable(0.2f, 0.2f);
    }

    public void setPower(int lvl) {
        this.power = lvl;
    }

    @Override
    protected float getGravity() {
        return 0;
    }
}