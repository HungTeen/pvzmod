package com.hungteen.pvz.entity.ai;

import javax.annotation.Nullable;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;

import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;

public class BreakBlockGoal extends MoveToBlockGoal {
	
	protected final Block block;
	protected final MobEntity entity;
	protected int breakingTime;

	public BreakBlockGoal(Block blockIn, CreatureEntity creature, double speed, int yMax) {
		super(creature, speed, 24, yMax);
		this.block = blockIn;
		this.entity = creature;
	}

	/**
	 * Returns whether execution should begin. You can also read and cache any state
	 * necessary for execution in this method as well.
	 */
	public boolean shouldExecute() {
		if(entity.getAttackTarget() != null) {
			return false;
		}
		if(entity instanceof PVZZombieEntity) {
			if(! ((PVZZombieEntity) entity).checkCanZombieBreakBlock()) {
				return false;
			}
		}
		if (!net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.entity.world, this.destinationBlock,this.entity)) {
			return false;
		} else if (this.runDelay > 0) {
			--this.runDelay;
			return false;
		} else if (this.func_220729_m()) {
			this.runDelay = 20;
			return true;
		} else {
			this.runDelay = this.getRunDelay(this.creature);
			return false;
		}
	}

	private boolean func_220729_m() {
		return this.destinationBlock != null && this.shouldMoveTo(this.creature.world, this.destinationBlock) ? true
				: this.searchForDestination();
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by
	 * another one
	 */
	public void resetTask() {
		super.resetTask();
		this.entity.fallDistance = 1.0F;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		super.startExecuting();
		this.breakingTime = 0;
	}

	protected int getBreakTime(MobEntity entity) {
		return 60;
	}
	
	public void playBreakingSound(IWorld worldIn, BlockPos pos) {
	}

	public void playBrokenSound(World worldIn, BlockPos pos) {
	}

	@Override
	public boolean shouldContinueExecuting() {
		if(entity instanceof PVZZombieEntity) {
			if(! ((PVZZombieEntity) entity).checkCanZombieBreakBlock()) {
				return false;
			}
		}
		return super.shouldContinueExecuting();
	}
	
	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		super.tick();
		World world = this.entity.world;
		if (this.isEntityNearBy() && this.destinationBlock != null) {
//            System.out.println(this.breakingTime);
			if (this.breakingTime % 2 == 0) {
				if (this.breakingTime % 6 == 0) {
					this.playBreakingSound(world, this.destinationBlock);
				}
			}

			if (this.breakingTime > this.getBreakTime(entity)) {
				world.destroyBlock(this.destinationBlock, false);
				if (!world.isRemote) {
					this.playBrokenSound(world, this.destinationBlock);
				}
			}
			this.entity.world.sendBlockBreakProgress(this.entity.getEntityId(), this.destinationBlock, breakingTime / 8);
			++ this.breakingTime;
		}

	}
	
	protected boolean isEntityNearBy() {
		if(Math.abs(this.destinationBlock.getY()-this.entity.getPosY()) > 3) return false;
		double x=this.destinationBlock.getX(),z=this.destinationBlock.getZ();
		double xx=this.entity.getPosX(),zz=this.entity.getPosZ();
		return (x-xx)*(x-xx)+(z-zz)*(z-zz)<=4;
	}

	@Nullable
	private BlockPos findTarget(BlockPos pos, IBlockReader worldIn) {
		if (worldIn.getBlockState(pos).getBlock() == this.block) {
			return pos;
		} else {
			BlockPos[] ablockpos = new BlockPos[] { pos.down(), pos.west(), pos.east(), pos.north(), pos.south(),
					pos.down().down() };

			for (BlockPos blockpos : ablockpos) {
				if (worldIn.getBlockState(blockpos).getBlock() == this.block) {
					return blockpos;
				}
			}

			return null;
		}
	}

	/**
	 * Return true to set given position as destination
	 */
	@SuppressWarnings("deprecation")
	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
		IChunk ichunk = worldIn.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
		if (ichunk == null) {
			return false;
		} else {
			return ichunk.getBlockState(pos).getBlock() == this.block && ichunk.getBlockState(pos.up()).isAir()
					&& ichunk.getBlockState(pos.up(2)).isAir();
		}
	}
}