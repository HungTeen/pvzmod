package com.hungteen.pvzmod.entities.plants.fight;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.ai.PVZAISpikeAttack;
import com.hungteen.pvzmod.entities.ai.target.PVZAIPlantGlobalTarget;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.zombies.EntityBobsle;
import com.hungteen.pvzmod.entities.zombies.EntityZomboni;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySpikeWeed extends EntitySpikeRock{

	public EntitySpikeWeed(World worldIn) {
		super(worldIn);
		this.setSize(0.8f, 0.3f);
	}
	
	@Override
	public int getMaxSpikeNum() {
		return 0;
	}
	
	@Override
	public float getAttackDamage() {
		int lvl=this.getPlantLvl();
		int now=(lvl-1)/4;
		if(lvl<=20) return 2+now;
		return 2;
	}

	@Override
	public int getSuperTimeLength() {
		return 2;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.SPIKE_WEED;
	}

}
