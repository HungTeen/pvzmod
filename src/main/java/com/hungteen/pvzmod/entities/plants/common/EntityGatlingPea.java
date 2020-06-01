package com.hungteen.pvzmod.entities.plants.common;

import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityGatlingPea extends EntityPeaShooter{

	public EntityGatlingPea(World worldIn) {
		super(worldIn);
	}

	@Override
	public void startShootAttack()
    {
        this.setAttackTime(4);
    }
	
	@Override
	protected EntityPea.Type getShootType()
	{
		if(this.isPlantInSuperMode()&&!this.getIsSuperOut()) {
			this.setIsSuperOut(true);
			return EntityPea.Type.HUGE;
		}
		return EntityPea.Type.NORMAL;
	}
	
	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 400;
		else if(lvl<=13) return 500;
		else if(lvl<=20) return 600;
		return 400;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.GATLING_PEA;
	}
}
