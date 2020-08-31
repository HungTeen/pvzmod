package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZZombieToolBase;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DuckyTubeEntity extends PVZZombieToolBase{

	public DuckyTubeEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
	}
	
	@Override
	public void baseTick() {
		super.baseTick();
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(!this.world.isRemote) {
			Block block1=this.world.getBlockState(new BlockPos(this).down()).getBlock();
			Block block2=this.world.getBlockState(new BlockPos(this)).getBlock();
			if(block1!=Blocks.WATER&&block2!=Blocks.WATER) {
			    for(Entity entity:this.getPassengers()) {
//			    	System.out.println("go out!");
			    	entity.stopRiding();
			    }
			}
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.2f,0.2f,false);
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
	public boolean canBeRiddenInWater() {
		return true;
	}
	
	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return true;
	}
	
}
