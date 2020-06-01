package com.hungteen.pvzmod.entities.ai.target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hungteen.pvzmod.entities.ai.EntityAIPlantAttackRanged;
import com.hungteen.pvzmod.entities.plants.base.EntityShooterBase;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.math.AxisAlignedBB;

public class PVZAIShooterTarget extends PVZAIPlantGlobalTarget{
    
	public PVZAIShooterTarget(EntityCreature creature,float h,float w) {
		super(creature,h,w);
	}
	
	@Override
	protected boolean checkPlant(Entity entity) {
		double dy=entity.posY-this.taskOwner.posY;
		return EntityShooterBase.checkY(dy);
	}
}
