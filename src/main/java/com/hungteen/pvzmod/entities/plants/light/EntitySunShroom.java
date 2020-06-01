package com.hungteen.pvzmod.entities.plants.light;

import com.hungteen.pvzmod.entities.plants.base.EntityShroomBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.world.World;

public class EntitySunShroom extends EntityShroomBase{

	public EntitySunShroom(World worldIn) {
		super(worldIn);
	}

	@Override
	public int getCoolDownTime() {
		return 0;
	}

	@Override
	public int getSunCost() {
		return 0;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.SUN_SHROOM;
	}

}
