package com.hungteen.pvz.common.entity.creature.garden;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-20 23:02
 **/
public class CommonGardenPlant extends GardenPlant{

    public CommonGardenPlant(EntityType<? extends GardenPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected int getNextGrowCD() {
        return 200;
    }

    @Override
    public float getRenderScale() {
        final int stage = this.getGrowStage();
        return stage == 0 ? 0.5F : stage ==  1 ? 0.75F : stage == 2 ? 0.9F : 1F;
    }
    
    @Override
    public EntityDimensions getDimensions(Pose pose) {
    	final int stage = this.getGrowStage();
    	return stage == 3 ? EntityDimensions.scalable(0.9F, 1F) : 
    		stage == 2 ? EntityDimensions.scalable(0.8F, 0.8F) :
    			stage == 1 ? EntityDimensions.scalable(0.6F, 0.6F) : 
    				super.getDimensions(pose);
    }

    @Override
    protected int getMaxStage() {
        return 3;
    }
}
