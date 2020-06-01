package com.hungteen.pvzmod.entities.plants.defence;

import com.hungteen.pvzmod.entities.plants.base.EntityDefenceBase;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityNutWall extends EntityDefenceBase{

	public EntityNutWall(World worldIn) {
		super(worldIn);
		this.setSize(0.8f, 1.3f);
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
		return Plants.NUT_WALL;
	}
}
