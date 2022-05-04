package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.CabbageBullet;
import com.hungteen.pvz.common.entity.bullet.PultProjectile;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.entity.plant.base.PultPlant;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-04 09:52
 **/
public class CabbagePult extends PultPlant {

    public CabbagePult(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected PultProjectile createBullet() {
        return new CabbageBullet(this.level, this);
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.7F, 1F);
    }

    @Override
    public void onEnergetic(boolean first) {
        super.onEnergetic(first);
        this.powerPultCount += 1;
    }

    @Override
    public float getAttackDamage() {
        return 2;
    }

    @Override
    public IPlantType getPlantType() {
        return PVZPlants.CABBAGE_PULT;
    }

}
