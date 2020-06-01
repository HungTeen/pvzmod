package com.hungteen.pvzmod.entities.plants.ice;

import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.entities.bullets.EntityPea.State;
import com.hungteen.pvzmod.entities.plants.base.EntityShooterBase;
import com.hungteen.pvzmod.entities.plants.common.EntityPeaShooter;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntitySnowPea extends EntityPeaShooter{

	public EntitySnowPea(World worldIn) {
		super(worldIn);
	}
	
	protected EntityPea.Type getShootType()
	{
		return EntityPea.Type.NORMAL;
	}
	
	@Override
	protected State getShootState() {
		return EntityPea.State.SNOW;
	}
	
	public PotionEffect getColdPotionEffect()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=4) return new PotionEffect(PotionRegister.COLD_EFFECT, 120, 4,false,false);
		else if(lvl<=8) return new PotionEffect(PotionRegister.COLD_EFFECT, 140, 5,false,false);
		else if(lvl<=12) return new PotionEffect(PotionRegister.COLD_EFFECT, 160, 6,false,false);
		else if(lvl<=16) return new PotionEffect(PotionRegister.COLD_EFFECT, 180, 7,false,false);
		else if(lvl<=20) return new PotionEffect(PotionRegister.COLD_EFFECT, 200, 8,false,false);
		return new PotionEffect(PotionRegister.COLD_EFFECT, 120, 4,false,false);
	}
	
	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 100;
		else if(lvl<=13) return 120;
		else if(lvl<=20) return 150;
		return 100;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SNOW_PEA;
	}
}
