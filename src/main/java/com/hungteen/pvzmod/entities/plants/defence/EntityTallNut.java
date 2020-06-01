package com.hungteen.pvzmod.entities.plants.defence;

import com.hungteen.pvzmod.entities.plants.base.EntityDefenceBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.world.World;

public class EntityTallNut extends EntityDefenceBase{

	public EntityTallNut(World worldIn) {
		super(worldIn);
		this.setSize(1.1f, 2.2f);
	}

	@Override
	public float getSuperLife() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 1000;
		else if(lvl<=13) return 1400;
		else if(lvl<=20) return 2000;
		return 1000;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.TALL_NUT;
	}
}
