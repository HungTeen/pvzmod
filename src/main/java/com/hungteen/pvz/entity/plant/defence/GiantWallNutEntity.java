package com.hungteen.pvz.entity.plant.defence;

import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class GiantWallNutEntity extends WallNutEntity {

	public GiantWallNutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(2F, 3F);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.GIANT_WALL_NUT;
	}

}
