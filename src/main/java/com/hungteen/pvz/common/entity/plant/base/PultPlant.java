package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.common.entity.PVZAttributes;
import com.hungteen.pvz.common.entity.ai.goal.attack.PVZRangeAttackGoal;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZScatterTargetGoal;
import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import com.hungteen.pvz.common.entity.bullet.PultProjectile;
import com.hungteen.pvz.common.impl.PAZAlmanacs;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IRangeAttackEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-29 17:02
 **/
public abstract class PultPlant extends PVZPlant implements IRangeAttackEntity {

    public static final int PULT_ANIM_CD = 20;
    protected int powerPultCount = 0;

    public PultPlant(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(PVZAttributes.WORK_CD.get()).setBaseValue(this.getPultCD());
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(this.getPultRange());
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(this.getAttackDamage());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new PVZRangeAttackGoal<>(this));
        this.addTargetGoals();
    }

    protected void addTargetGoals() {
        this.targetSelector.addGoal(2, new PVZScatterTargetGoal(this, true, false));
    }

    @Override
    public void startAttack(@NotNull Entity entity) {
        if(this.powerPultCount > 0){
            -- this.powerPultCount;
            final double range = this.getAttribute(Attributes.FOLLOW_RANGE).getValue() / 2;
            EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range)).forEach(target -> {
                this.performPult(target, true);
            });
        } else{
            this.performPult(entity, false);
        }
    }

    @Override
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(
                Pair.of(PAZAlmanacs.BULLET_DAMAGE, this.getAttackDamage()),
                Pair.of(PAZAlmanacs.ATTACK_CD, this.getPultCD()),
                Pair.of(PAZAlmanacs.ATTACK_RANGE, this.getPultRange())
        ));
    }

    public void performPult(Entity entity, boolean isPower) {
        Optional.ofNullable(entity).ifPresent(target -> {
            PultProjectile bullet = this.createBullet();
            bullet.setPos(this.getX(), this.getY() + 1.5f, this.getZ());
            bullet.shootPultBullet(target);
            bullet.summonByOwner(this);
            bullet.setAttackDamage(this.getAttackDamage());
            bullet.setBulletType(isPower ? PVZProjectile.BulletTypes.BIG : PVZProjectile.BulletTypes.NORMAL);
            this.level.addFreshEntity(bullet);
            EntityUtil.playSound(this, PVZSounds.PULT_THROW.get());
        });
    }

    protected abstract PultProjectile createBullet();

    /**
     * get shooter bullet attack damage.
     */
    public abstract float getAttackDamage();

    @Override
    public double getCurrentWorkCD() {
        return Math.max(super.getCurrentWorkCD(), PULT_ANIM_CD);
    }

    @Override
    public int getEnergeticDuration() {
        return 100;
    }

    public int getPultCD(){
        return 60;
    }

    public float getPultRange(){
        return 30;
    }

    /**
     * check weather the shooter can shoot currently.
     */
    @Override
    public boolean canAttack() {
        return this.canNormalUpdate();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("PowerPultCount")) {
            this.powerPultCount = compound.getInt("PowerPultCount");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("ShootPultCount", this.powerPultCount);
    }

    @Override
    public double getCurrentAttackCD() {
        return this.getCurrentWorkCD();
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
    public int getStartAttackTick() {
        return (int) (this.getCurrentAttackCD() - PULT_ANIM_CD * 0.5F);
    }
}
