package com.hungteen.pvzmod.entities.plants.electricity;

import com.hungteen.pvzmod.entities.ai.target.PVZAIPlantGlobalTarget;
import com.hungteen.pvzmod.entities.plants.base.EntityGenPlantBase;
import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityLightningRod extends EntityGenPlantBase{

	public EntityLightningRod(World worldIn) {
		super(worldIn);
		this.setSize(0.6f, 1.0f);
		this.isImmuneToFire=true;
	}
	
	@Override
    protected void initEntityAI()
    {
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 1.50F));
        this.targetTasks.addTask(10, new PVZAIPlantGlobalTarget(this,20,20));
    }
	
	@Override
	public void genSomething() {
		EntityLivingBase target=this.getAttackTarget();
		if(target==null) target=this;//没目标 自己就是目标
		EntityLightningBolt bolt=new EntityLightningBolt(world, target.posX, target.posY+target.getEyeHeight(), target.posZ, false);
		this.world.spawnEntity(bolt);
		if(this.posY>=3) {
			this.world.setBlockState(new BlockPos(posX, posY-1, posZ), BlockRegister.ELECTRICITY_ORE.getDefaultState());
		}
	}
	
	@Override
	public void genSuper() {
		if(this.posY>=2) {
		    this.world.setBlockState(new BlockPos(posX, posY-1, posZ), BlockRegister.ELECTRICITY_ORE.getDefaultState());
		    this.world.setBlockState(new BlockPos(posX-1, posY-1, posZ), BlockRegister.ELECTRICITY_ORE.getDefaultState());
		    this.world.setBlockState(new BlockPos(posX+1, posY-1, posZ), BlockRegister.ELECTRICITY_ORE.getDefaultState());
		    this.world.setBlockState(new BlockPos(posX, posY-1, posZ-1), BlockRegister.ELECTRICITY_ORE.getDefaultState());
		    this.world.setBlockState(new BlockPos(posX, posY-1, posZ+1), BlockRegister.ELECTRICITY_ORE.getDefaultState());
		    if(this.getPlantLvl()>=14) {
		    	this.world.setBlockState(new BlockPos(posX-1, posY-1, posZ-1), BlockRegister.ELECTRICITY_ORE.getDefaultState());
		    	this.world.setBlockState(new BlockPos(posX-1, posY-1, posZ+1), BlockRegister.ELECTRICITY_ORE.getDefaultState());
		    	this.world.setBlockState(new BlockPos(posX+1, posY-1, posZ-1), BlockRegister.ELECTRICITY_ORE.getDefaultState());
		    	this.world.setBlockState(new BlockPos(posX+1, posY-1, posZ+1), BlockRegister.ELECTRICITY_ORE.getDefaultState());
		    }
		}
		int len=this.getPlantLvl()>=7?20:10;
		AxisAlignedBB aabb=new AxisAlignedBB(posX+len, posY+10, posZ+len, posX-len, posY-1, posZ-len);
		for(Entity target:EntityUtil.getEntityAttackableTarget(this, aabb)) {
			EntityLightningBolt bolt=new EntityLightningBolt(world, target.posX, target.posY+target.getEyeHeight(), target.posZ, false);
			this.world.spawnEntity(bolt);
		}
	}
	
	public int getAttackCD()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 1200-now*40;
		}
		return 1200;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 2;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.LIGHTLING_ROD;
	}

}
