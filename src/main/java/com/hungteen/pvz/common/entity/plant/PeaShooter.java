package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import com.hungteen.pvz.common.entity.bullet.PeaBullet;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.entity.plant.base.ShooterPlant;
import com.hungteen.pvz.common.impl.type.SkillTypes;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 20:17
 **/
public class PeaShooter extends ShooterPlant {

    protected static final double SHOOT_OFFSET = 0.2D;//pea position offset

    public PeaShooter(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void startAttack(@Nonnull Entity target) {
        ++ this.shootCount;
    }

    @Override
    public void shootBullet(@Nonnull Entity target) {
        if(this.shootCount > 5){
            final int cnt = MathUtil.getRandomMinMax(this.random, 1, 3);
            for(int i = 0; i < cnt; ++ i) {
                final float offset = MathUtil.getRandomFloat(getRandom()) / 3;
                final float offsetH = MathUtil.getRandomFloat(getRandom()) / 3;
                this.performShoot(SHOOT_OFFSET, offset, offsetH, this.getExistTick() % 10 == 0, FORWARD_SHOOT_ANGLE);
            }
        } else{
            this.performShoot(SHOOT_OFFSET, 0, 0, true, FORWARD_SHOOT_ANGLE);
        }
        -- this.shootCount;
    }

    @Override
    protected PVZProjectile createBullet() {
        final PeaBullet pea = new PeaBullet(this.level, this, this.getBulletType(), this.getBulletState());
        return pea;
    }

    protected PVZProjectile.BulletTypes getBulletType(){
        return PVZProjectile.BulletTypes.NORMAL;
    }

    protected PVZProjectile.BulletStates getBulletState(){
        return PVZProjectile.BulletStates.NORMAL;
    }
    
    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.7F, 1.4F);
    }

    @Override
    public void onEnergetic(boolean first) {
        super.onEnergetic(first);
        this.shootCount += 100;
    }

    @Override
    public float getAttackDamage() {
        return this.getSkillValue(SkillTypes.PEA_DAMAGE);
    }

    @Override
    public float getBulletSpeed() {
        return this.getSkillValue(SkillTypes.PEA_SPEED_UP);
    }

    @Override
    public int getEnergeticDuration() {
        return 100;
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.PEA_SHOOTER;
    }
}
