package com.hungteen.pvzmod.entities.zombies.special;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;

import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;

public class EntityNormalDefence extends MultiPartEntityPart{

	private EntityZombieBase owner;
	
	public EntityNormalDefence(EntityZombieBase parent, String partName, float width, float height) {
		super((IEntityMultiPart) parent, partName, width, height);
		owner=parent;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();

		this.ticksExisted++;

		lastTickPosX = posX;
		lastTickPosY = posY;
		lastTickPosZ = posZ;
	}
	
	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
	}
	
//	@Override
//	public void applyEntityCollision(Entity entityIn) {
//		System.out.println(entityIn);
//		super.applyEntityCollision(entityIn);
//	}
	
	public void setSmallSize(float w,float h)
	{
		this.setSize(w,h);
	}
	
	public EntityZombieBase getOwner()
	{
		return this.owner;
	}
}
