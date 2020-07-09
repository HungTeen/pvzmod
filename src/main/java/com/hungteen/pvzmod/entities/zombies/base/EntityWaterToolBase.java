package com.hungteen.pvzmod.entities.zombies.base;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase.Type;
import com.hungteen.pvzmod.entities.zombies.special.EntityDuckyTube;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityWaterToolBase extends EntityZombieToolBase{

	
	public EntityWaterToolBase(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void initEntityAI()
    {
		super.initEntityAI();
        this.tasks.addTask(10, new EntityAISwimming(this));
    }
	
	@Override
	public double getMountedYOffset() {
		return -0.8f;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.9f;
	}
	
	@Override
	public boolean isPushedByWater() {
		return false;
	}
	
	@Override
	public boolean shouldDismountInWater(Entity rider) {
		return false;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TOOL;
	}
}
