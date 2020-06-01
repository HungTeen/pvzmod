package com.hungteen.pvzmod.entities.plants.defence;

import com.hungteen.pvzmod.entities.plants.base.EntityDefenceBase;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.world.World;

public class EntityPumpkin extends EntityDefenceBase{

	public EntityPumpkin(World worldIn) {
		super(worldIn);
		this.setSize(1.4f, 0.8f);
	}

	public float getSuperLife()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 600;
		else if(lvl<=13) return 800;
		else if(lvl<=20) return 1000;
		return 600;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.PUMPKIN;
	}

}
