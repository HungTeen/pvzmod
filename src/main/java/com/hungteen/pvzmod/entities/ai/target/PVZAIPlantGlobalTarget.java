package com.hungteen.pvzmod.entities.ai.target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.math.AxisAlignedBB;

public class PVZAIPlantGlobalTarget extends EntityAINearestAttackableTarget<EntityLivingBase>{

    private final int targetChance;
    private final float height;
    private final float width;
    
	public PVZAIPlantGlobalTarget(EntityCreature creature,float h,float w) {
		super(creature,EntityLivingBase.class, 5, true,false, IMob.MOB_SELECTOR);
		this.targetChance=5;
		this.height=h;
		this.width=w;
	}
	
	@Override
	public boolean shouldExecute() {
		if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0)
        {
            return false;
        }
		else{
			List<Entity> list = this.taskOwner.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getAABB());
			List<Entity> list1 =new ArrayList<Entity>();
			for(Entity entity:list) {
				if(EntityUtil.checkCanEntityAttack(this.taskOwner,entity)) {
					if(checkSenses(entity)&&checkPlant(entity)) {
					    list1.add(entity);
					}
				}
			}
			if(list1.isEmpty()) {
				return false;
			}
			Collections.sort(list1, this.sorter);
            this.target= (EntityLivingBase) list1.get(0);
//            System.out.println(this.target);
            return true;
		}
	}
	
	protected boolean checkSenses(Entity entity)
	{
		return this.taskOwner.getEntitySenses().canSee(entity);
	}
	
	protected boolean checkPlant(Entity entity)
	{
		return true;
	}
	
	private AxisAlignedBB getAABB()
	{
		return new AxisAlignedBB(this.taskOwner.posX+width, this.taskOwner.posY-height, this.taskOwner.posZ+width, this.taskOwner.posX-width, this.taskOwner.posY+2+height, this.taskOwner.posZ-width);
	}
}
