package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.particle.PVZParticles;
import com.hungteen.pvz.client.particle.ParticleUtil;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.common.PVZDamageSource;
import com.hungteen.pvz.common.entity.plant.base.CloseInstantPlant;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.impl.PAZAlmanacs;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Arrays;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-08 15:57
 **/
public class PotatoMine extends CloseInstantPlant {

    public static final int RISING_ANIM_CD = 20;

    public PotatoMine(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void normalPlantTick() {
        super.normalPlantTick();
        if(! this.level.isClientSide) {
            if(this.getExistTick() == this.getPrepareCD() - RISING_ANIM_CD + 1) {
                EntityUtil.playSound(this, PVZSounds.DIRT_RISE.get());
            }
        } else {
            if(this.isRisingFromDirt()) {
                for(int i = 0; i < 2; ++ i) {
                    final Vec3 offset = new Vec3(MathUtil.getRandomFloat(getRandom()), 0, MathUtil.getRandomFloat(getRandom())).normalize();
                    WorldUtil.spawnRandomSpeedParticle(level, new BlockParticleOption(ParticleTypes.BLOCK, level.getBlockState(this.blockPosition().below())), this.position().add(offset), MathUtil.getRandomFloat(getRandom()) / 8, 0.06F);
                }
            }
        }
    }

    @Override
    public void onEnergetic(boolean first) {
        super.onEnergetic(first);
        this.setRisingFromDirt();
    }

    @Override
    public boolean canPAZTarget(Entity target) {
//        if(target instanceof DiggerZombieEntity) {
//            return true;
//        }
        return super.canPAZTarget(target);
    }

    @Override
    public void performAttack(LivingEntity target) {
        if(! this.level.isClientSide) {
            final float range = 1.6F;
            final AABB aabb = EntityUtil.getEntityAABB(this, range, range);
            EntityUtil.getWholeTargetableEntities(this, aabb).forEach(targetEntity -> {
                targetEntity.hurt(PVZDamageSource.explode(this), this.getExplodeDamage());
            });
//            PVZPlantEntity.clearLadders(this, aabb);
            EntityUtil.playSound(this, PVZSounds.POTATO_MINE.get());
            for(int i = 1; i <= 5; ++ i) {
                ParticleUtil.spawnParticles(this.level, PVZParticles.POTATO_EXPLOSION.get(), this.position());
            }
            this.discard();
        }
    }

    public boolean isRisingFromDirt() {
        return this.getExistTick() >= this.getPrepareCD() - RISING_ANIM_CD && this.getExistTick() <= this.getPrepareCD();
    }

    /**
     * #{@link #onEnergetic(boolean)}
     */
    public void setRisingFromDirt() {
        this.setExistTick(this.getPrepareCD() - RISING_ANIM_CD - 2);
    }

    @Override
    protected boolean canBeImmuneToEnforce(Entity entity) {
        return super.canBeImmuneToEnforce(entity) && (this.isMineReady() || this.isRisingFromDirt());
    }

    @Override
    public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
        super.addAlmanacEntries(list);
        list.addAll(Arrays.asList(
                Pair.of(PAZAlmanacs.EXPLODE_DAMAGE, this.getExplodeDamage()),
                Pair.of(PAZAlmanacs.EXPLODE_DAMAGE, this.getExplodeRange()),
                Pair.of(PAZAlmanacs.PREPARE_CD, this.getPrepareCD())
        ));
    }

    public float getExplodeRange(){
        return 1.8F;
    }

    public float getExplodeDamage(){
        return 150;
//        return this.getSkillValue(SkillTypes.NORMAL_BOMB_DAMAGE);
    }

    public int getPrepareCD(){
        return 300;
//        return (int) this.getSkillValue(SkillTypes.MINE_FAST_PREPARE);
    }

    @Override
    public boolean canCheckDistance() {
        return this.isMineReady();
    }

    public boolean isMineReady() {
        return this.getExistTick() > this.getPrepareCD();
    }

    /**
     * use to change Signal String's color.
     * {@link com.hungteen.pvz.client.render.entity.plant.PotatoMineRender.MineLightLayer#canRender(PotatoMine)}
     */
    public int getSignChangeCD(){
        return this.isMineReady() ? 10 : 20;
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.6f, 0.4f);
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.POTATO_MINE;
    }
}
