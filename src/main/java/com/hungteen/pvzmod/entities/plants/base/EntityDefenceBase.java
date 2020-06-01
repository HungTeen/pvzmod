package com.hungteen.pvzmod.entities.plants.base;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityDefenceBase extends EntityPlantBase{

	public EntityDefenceBase(World worldIn) {
		super(worldIn);
	}

	@Override
    protected void initEntityAI()
    {
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
    }
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(!this.world.isRemote&&this.isPlantInSuperMode()) {
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getLife()+this.getSuperLife());
			this.heal(this.getMaxHealth());
        }
	}
	
	@Override
	public int getSuperTimeLength() {
		return 2;
	}
	
	public abstract float getSuperLife();
}
