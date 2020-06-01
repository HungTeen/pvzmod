package com.hungteen.pvzmod.entities.bullets;

import com.hungteen.pvzmod.client.gui.mainwindow.PVZGuiTabPlayerData;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.explosion.EntityPotatoMine;
import com.hungteen.pvzmod.util.PlantsUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityPotato extends PVZThrowable{

	public EntityPotato(World worldIn) {
		super(worldIn);
	}
	
	public EntityPotato(World worldIn,EntityLivingBase throwerIn) {
		super(worldIn,throwerIn);
	}
	
	public void shoot(double x, double y, double z) {
		this.motionX=x;
		this.motionY=y;
		this.motionZ=z;
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		if(!this.world.isRemote) {
			this.world.setEntityState(this, (byte)3);
            if(!this.checkLive(result)) {
            	this.setDead();
            	if(!(this.shooter instanceof EntityPlantBase)) {
            		System.out.println("who is shooting potato,there are some matter!");
            		return ;
            	}
            	EntityPotatoMine mine=new EntityPotatoMine(world);
            	{//setting
            		PlantsUtil.copyPlantData((EntityPlantBase) getThrower(), mine);
            		mine.setMineState(EntityPotatoMine.MineState.READY);
            	    mine.setPosition(this.posX, this.posY, this.posZ);
            	}
            	
            	this.world.spawnEntity(mine);
            }
		}
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.06f;
	}
}
