package com.hungteen.pvzmod.entities.plants.light;

import com.hungteen.pvzmod.entities.drops.EntitySun;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.init.SoundEvents;
import net.minecraft.world.World;

public class EntityTwinSunFlower extends EntitySunFlower{

	public EntityTwinSunFlower(World worldIn) {
		super(worldIn);
	}

	@Override
	public void genSomething() {
		for(int i=1;i<=2;i++) {
		    this.genSun(this.getSunAmount());
		}
	}
	
	public void genSuper()
	{
		int lvl=this.getPlantLvl();
		int ge=(lvl<=6)?3:4;
		int amount=(lvl<=13)?50:75;
		for(int i=1;i<=ge;i++) {
		    this.genSun(amount);
		}
		this.genSun(75);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.TWIN_SUNFLOWER;
	}


}
