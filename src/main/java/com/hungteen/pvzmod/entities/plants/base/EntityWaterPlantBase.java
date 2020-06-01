package com.hungteen.pvzmod.entities.plants.base;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class EntityWaterPlantBase extends EntityPlantBase{

	public EntityWaterPlantBase(World worldIn) {
		super(worldIn);
	}
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		BlockPos pos=new BlockPos(this.posX,this.posY,this.posZ);
		IBlockState state=this.world.getBlockState(pos);
		Block block=state.getBlock();
		boolean flag=true;
		pos=new BlockPos(this.posX,this.posY+0.05d,this.posZ);
		state=this.world.getBlockState(pos);
		if(state.getBlock()==Blocks.WATER&&block==Blocks.WATER) {
			this.motionY=0.05;
		}
		else {
			this.motionY=0;
		}
		if(block==Blocks.AIR) {
			this.motionY=-0.1;
		}
	}
	
	@Override
	protected void initEntityAI() {
		
	}
	
	@Override
	protected boolean checkWeak()//检测是否应该因为离开水而虚弱
	{
		if(this.isImmuneToWeak) return false;
		Entity entity =this.getRidingEntity();
    	BlockPos blockpos = new BlockPos(this.posX,this.posY,this.posZ);
    	IBlockState iblockstate = world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        if(block==Blocks.WATER) {
        	return false;
        }
        return true;
	}
	
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	public boolean isPushedByWater()
    {
        return false;
    }
}
