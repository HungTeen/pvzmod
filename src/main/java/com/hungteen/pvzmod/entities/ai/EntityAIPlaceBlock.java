package com.hungteen.pvzmod.entities.ai;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EntityAIPlaceBlock extends EntityAIBase{

	private EntityLivingBase target;
	private EntityLiving attacker;
	
	private static IBlockState placedBlock = Blocks.COBBLESTONE.getDefaultState();
	private static final EnumFacing[] placeSurface = new EnumFacing[]{EnumFacing.DOWN,EnumFacing.NORTH,EnumFacing.EAST,EnumFacing.SOUTH,EnumFacing.WEST};
	private int placeDelay = 15;
	private BlockPos block;
	
	public EntityAIPlaceBlock(EntityLiving attacker) {
		this.attacker=attacker;
	}
	
	@Override
	public boolean shouldExecute() {
		if (this.target != null && this.target.isEntityAlive() && this.attacker.getNavigator().noPath()
				&& this.attacker.getDistance(this.target) > 1D&&this.target.posY>this.attacker.posY+2&&this.attacker.posY<250&&this.attacker.onGround) {
			return true;
		}
		return false;
		//this.target.setJumping(jumping);
	}

	
	
}
