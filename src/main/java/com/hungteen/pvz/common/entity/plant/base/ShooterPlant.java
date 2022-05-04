package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.common.entity.PVZAttributes;
import com.hungteen.pvz.common.entity.ai.goal.attack.PVZRangeAttackGoal;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZScatterTargetGoal;
import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import com.hungteen.pvz.common.impl.PAZAlmanacs;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.interfaces.IRangeAttackEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:59
 *
 * WorkTick used in Shooting AI <br>
 **/
public abstract class ShooterPlant extends PVZPlant implements IRangeAttackEntity {

    public static final float BACK_SHOOT_ANGLE = 180;
    public static final float FORWARD_LEFT_SHOOT_ANGLE = -7.5F;
    public static final float FORWARD_RIGHT_SHOOT_ANGLE = 7.5F;
    public static final float FORWARD_SHOOT_ANGLE = 0;
    public static final int SHOOT_ANIM_CD = 10;
    protected int shootCount = 0;
    protected int powerShootCount = 0;

    public ShooterPlant(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(PVZAttributes.WORK_CD.get()).setBaseValue(this.getShootCD());
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(this.getShootRange());
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(this.getAttackDamage());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new ShooterAttackGoal(this));
        this.addTargetGoals();
    }

    protected void addTargetGoals() {
        this.targetSelector.addGoal(2, new ShooterNearestTargetGoal(this, true, false));
    }

    @Override
    public void normalPlantTick() {
        super.normalPlantTick();
        //when having target then attack !
        if(! this.level.isClientSide && this.getTarget() != null) {
            if(this.shootCount > 0 || this.powerShootCount > 0){
                this.shootBullet(this.getTarget());
            }
        }
    }

    /**
     * shoot bullet.
     */
    protected abstract void shootBullet(@Nonnull Entity target);

    @Override
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(
                Pair.of(PAZAlmanacs.BULLET_DAMAGE, this.getAttackDamage()),
                Pair.of(PAZAlmanacs.SHOOT_CD, this.getShootCD()),
                Pair.of(PAZAlmanacs.SHOOT_RANGE, this.getShootRange())
        ));
    }

    /**
     * shoot pea with offsets.
     */
    public void performShoot(double forwardOffset, double rightOffset, double heightOffset, boolean needSound, double angleOffset) {
        Optional.ofNullable(this.getTarget()).ifPresent(target -> {
            final Vec3 vec = MathUtil.getHorizontalVec(this.position(), target.position()).normalize();
            final double deltaY = this.getDimensions(getPose()).height * 0.7F + heightOffset;
            final double deltaX = forwardOffset * vec.x - rightOffset * vec.z;
            final double deltaZ = forwardOffset * vec.z + rightOffset * vec.x;
            final PVZProjectile bullet = this.createBullet();
            bullet.setPos(this.getX() + deltaX, this.getY() + deltaY, this.getZ() + deltaZ);
            bullet.shootPea(target.getX() - bullet.getX(), target.getY() + target.getBbHeight() - bullet.getY(), target.getZ() - bullet.getZ(), this.getBulletSpeed(), angleOffset);
            if(needSound) {
                EntityUtil.playSound(this, this.getShootSound());
            }
            bullet.summonByOwner(this);
            bullet.setAttackDamage(this.getAttackDamage());
            this.level.addFreshEntity(bullet);
        });
    }

    /**
     * shoot pea by angle.
     */
    public void shootByAngle(float angle, float height) {
        angle *= 3.14159F / 180F;
        final double vx = - Mth.sin(angle);
        final double vz = Mth.cos(angle);
        final PVZProjectile bullet = this.createBullet();
        bullet.setPos(getX(), getY() + height, getZ());
        bullet.setDeltaMovement(vx * this.getBulletSpeed(), 0, vz * this.getBulletSpeed());
        bullet.summonByOwner(this);
        bullet.setAttackDamage(this.getAttackDamage());
        level.addFreshEntity(bullet);
    }

    protected abstract PVZProjectile createBullet();

    /**
     * no sound source. [bruh] !
     */
    protected SoundEvent getShootSound() {
        return SoundEvents.SNOW_GOLEM_SHOOT;
    }

    /**
     * get shooter bullet attack damage.
     */
    public abstract float getAttackDamage();

    @Override
    public double getCurrentWorkCD() {
        return Math.max(super.getCurrentWorkCD(), SHOOT_ANIM_CD);
    }

    @Override
    public boolean canPAZTarget(Entity entity) {
        return this.checkY(entity) && super.canPAZTarget(entity);
    }

    public boolean checkY(Entity target) {
        final double dx = target.getX() - this.getX();
        final double dz = target.getZ() - this.getZ();
        final double minY = target.getY() - this.getY() - this.getEyeHeight();
        final double maxY = minY + target.getBbHeight();
        final double dis = Math.sqrt(dx * dx + dz * dz);
        final double y = dis / getMaxShootAngle();
        return minY < y && maxY > - y;
    }

    /**
     * use to check horizontal shoot path.
     * {@link #checkY(Entity)}
     */
    public double getMaxShootAngle() {
        return 3;
    }

    /**
     * max target horizontal distance.
     */
    public float getShootRange() {
        return 30;
    }

    public int getShootCD() {
        return 30;
    }

    /**
     * check weather the shooter can shoot currently.
     */
    @Override
    public boolean canAttack() {
        return this.canNormalUpdate();
    }

    public float getBulletSpeed() {
        return 1.2F;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("ShootCount")) {
            this.shootCount = compound.getInt("ShootCount");
        }
        if(compound.contains("PowerShootCount")) {
            this.powerShootCount = compound.getInt("PowerShootCount");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("ShootCount", this.shootCount);
        compound.putInt("PowerShootCount", this.powerShootCount);
    }

    @Override
    public void setAttackTick(int tick) {
        this.setWorkTick(tick);
    }

    @Override
    public int getAttackTick() {
        return this.getWorkTick();
    }

    @Override
    public double getCurrentAttackCD() {
        return this.getCurrentWorkCD();
    }

    @Override
    public int getStartAttackTick() {
        return (int) (this.getCurrentAttackCD() - SHOOT_ANIM_CD * 0.25F);
    }

    static class ShooterAttackGoal extends PVZRangeAttackGoal<ShooterPlant> {

        public ShooterAttackGoal(ShooterPlant shooter) {
            super(shooter);
        }

        @Override
        public void tick() {
//            if(! (this.shooter instanceof StarFruitEntity)) {//star fruit don't need to look at target.
//                this.attacker.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
//            }
//            if(this.shooter.isPlantInSuperMode()) {
//                this.shooter.startShootAttack();
//                this.shooter.setShootTick(0);
//                return ;
//            }
            super.tick();
        }

//        private boolean checkTarget() {
//            if(EntityUtil.canAttackEntity(this.shooter, this.target)) {
////                if(this.shooter instanceof CatTailEntity) {
////                    return EntityUtil.canSeeEntity(this.shooter, this.target);
////                }
////                return this.shooter.getSensing().hasLineOfSight(this.target);
////            }
//            return false;
//        }

    }

    protected static class ShooterNearestTargetGoal extends PVZScatterTargetGoal {

        private final ShooterPlant shooter;

        public ShooterNearestTargetGoal(ShooterPlant mobIn, boolean checkSight, boolean memory) {
            super(mobIn, checkSight, memory);
            this.shooter = mobIn;
        }

        @Override
        protected boolean checkOther(LivingEntity entity) {
            return super.checkOther(entity) && this.shooter.checkY(entity);
        }

    }

}
