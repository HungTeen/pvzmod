package com.hungteen.pvzmod.entities.plants.ice;

import com.hungteen.pvzmod.entities.bullets.EntityMelon;
import com.hungteen.pvzmod.entities.bullets.EntityPult;
import com.hungteen.pvzmod.entities.plants.common.EntityMelonPult;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityWinterMelon extends EntityMelonPult{

	public EntityWinterMelon(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected EntityPult getBullet() {
        EntityPult.Type type=EntityPult.Type.NORMAL;
        if(this.isPlantInSuperMode()&&!this.getIsSuperOut()) {
        	type=EntityPult.Type.EXPLODE;
        	this.setIsSuperOut(true);
        }
        return new EntityMelon(this.world,this,type,EntityMelon.State.ICE);
	}
	
	public PotionEffect getColdPotionEffect()
	{
		int lvl=this.getPlantLvl();
		int len=10,cnt=6;
		if(lvl<=20) {
			int now=(lvl-1)/5;
			len=10+now*2;
			cnt=6+now;
		}
		return new PotionEffect(PotionRegister.COLD_EFFECT, len*20,cnt,false,false);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.WINTER_MELON;
	}
}
