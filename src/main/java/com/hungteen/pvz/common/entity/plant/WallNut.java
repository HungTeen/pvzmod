package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.particle.PVZParticles;
import com.hungteen.pvz.client.particle.ParticleUtil;
import com.hungteen.pvz.common.PVZDamageSource;
import com.hungteen.pvz.common.entity.plant.base.DefenderPlant;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.impl.type.SkillTypes;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 17:58
 **/
public class WallNut extends DefenderPlant {

    public WallNut(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if(this.hasSkill(SkillTypes.EXPLOSION_NUT)){
            final float range = 2F;
            final AABB aabb = EntityUtil.getEntityAABB(this, range, range);
            EntityUtil.getWholeTargetableEntities(this, aabb).forEach(targetEntity -> {
                targetEntity.hurt(PVZDamageSource.explode(this), this.getSkillValue(SkillTypes.EXPLOSION_NUT));
            });
            EntityUtil.playSound(this, PVZSounds.POTATO_MINE.get());
            for(int i = 1; i <= 5; ++ i) {
                ParticleUtil.spawnParticles(this.level, PVZParticles.POTATO_EXPLOSION.get(), this.position());
            }
        }
    }

    @Override
    protected float getLife() {
        return 300;
    }

    @Override
    public double getAttractRange() {
        return 2.5;
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.85f, 1.1f);
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.WALL_NUT;
    }

}
