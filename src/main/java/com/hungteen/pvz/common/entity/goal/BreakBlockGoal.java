package com.hungteen.pvz.common.entity.goal;

import javax.annotation.Nullable;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;

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
	public boolean canUse() {
		if(entity.getTarget() != null) {
			return false;
		}
		if(entity instanceof PVZZombieEntity) {
			if(! ((PVZZombieEntity) entity).checkCanZombieBreakBlock()) {
				return false;
			}
		}
		if (!net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.entity.level, this.blockPos,this.entity)) {
			return false;
		} else if (this.nextStartTick > 0) {
			--this.nextStartTick;
			return false;
		} else if (this.tryFindBlock()) {
			this.nextStartTick = 20;
			return true;
		} else {
			this.nextStartTick = this.nextStartTick(this.mob);
			return false;
		}
	}

	private boolean tryFindBlock() {
		return this.blockPos != null && this.isValidTarget(this.mob.level, this.blockPos) ? true
				: this.findNearestBlock();
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by
	 * another one
	 */
	public void stop() {
		super.stop();
		this.entity.fallDistance = 1.0F;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void start() {
		super.start();
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
	public boolean canContinueToUse() {
		if(entity instanceof PVZZombieEntity) {
			if(! ((PVZZombieEntity) entity).checkCanZombieBreakBlock()) {
				return false;
			}
		}
		return super.canContinueToUse();
	}
	
	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		super.tick();
		World world = this.entity.level;
		if (this.isEntityNearBy() && this.blockPos != null) {
//            System.out.println(this.breakingTime);
			if (this.breakingTime % 2 == 0) {
				if (this.breakingTime % 6 == 0) {
					this.playBreakingSound(world, this.blockPos);
				}
			}

			if (this.breakingTime > this.getBreakTime(entity)) {
				world.destroyBlock(this.blockPos, false);
				if (!world.isClientSide) {
					this.playBrokenSound(world, this.blockPos);
				}
			}
			this.entity.level.destroyBlockProgress(this.entity.getId(), this.blockPos, breakingTime / 8);
			++ this.breakingTime;
		}

	}
	
	protected boolean isEntityNearBy() {
		if(Math.abs(this.blockPos.getY()-this.entity.getY()) > 3) return false;
		double x=this.blockPos.getX(),z=this.blockPos.getZ();
		double xx=this.entity.getX(),zz=this.entity.getZ();
		return (x-xx)*(x-xx)+(z-zz)*(z-zz)<=4;
	}

	@Nullable
	private BlockPos findTarget(BlockPos pos, IBlockReader worldIn) {
		if (worldIn.getBlockState(pos).getBlock() == this.block) {
			return pos;
		} else {
			BlockPos[] ablockpos = new BlockPos[] { pos.below(), pos.west(), pos.east(), pos.north(), pos.south(),
					pos.below().below() };

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
	protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
		IChunk ichunk = worldIn.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
		if (ichunk == null) {
			return false;
		} else {
			return ichunk.getBlockState(pos).getBlock() == this.block && ichunk.getBlockState(pos.above()).isAir()
					&& ichunk.getBlockState(pos.above(2)).isAir();
		}
	}
}