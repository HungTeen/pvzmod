package com.hungteen.pvzmod.entities.plants.common;

import com.hungteen.pvzmod.entities.bullets.EntityCabbage;
import com.hungteen.pvzmod.entities.bullets.EntityMelon;
import com.hungteen.pvzmod.entities.bullets.EntityPult;
import com.hungteen.pvzmod.entities.plants.base.EntityPultBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.world.World;

public class EntityMelonPult extends EntityPultBase{

	public EntityMelonPult(World worldIn) {
		super(worldIn);
		this.setSize(1f, 1f);
	}

	@Override
	protected EntityPult getBullet() {
        EntityPult.Type type=EntityPult.Type.NORMAL;
        if(this.isPlantInSuperMode()&&!this.getIsSuperOut()) {
        	type=EntityPult.Type.EXPLODE;
        	this.setIsSuperOut(true);
        }
        return new EntityMelon(this.world,this,type,EntityMelon.State.NORMAL);
	}

	public float getSpiltDamage()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=15) {
			int now=(lvl-1)/3;
			return 4+now;
		}
		else if(lvl<=20) {
			return 9;
		}
		return 4;
	}
	
	@Override
	public float getAttackDamage() {
		int lvl=this.getPlantLvl();
		if(lvl<=19) {
			return 7.5f+0.5f*lvl;
		}
		else if(lvl<=20) {
			return 18;
		}
		return 8;
	}

	@Override
	public int getSuperTimeLength() {
		return 200;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.MELON_PULT;
	}

}
