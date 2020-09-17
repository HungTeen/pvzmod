package com.hungteen.pvz.entity.ai;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.server.ServerWorld;

public class BreakLilyPadGoal extends MoveToBlockGoal {
	
	protected final Block block;
	protected final MobEntity entity;
	protected int breakingTime;

	public BreakLilyPadGoal(Block blockIn, CreatureEntity creature, double speed, int yMax) {
		super(creature, speed, 24, yMax);
		this.block = blockIn;
		this.entity = creature;
	}

	/**
	 * Returns whether execution should begin. You can also read and cache any state
	 * necessary for execution in this method as well.
	 */
	public boolean shouldExecute() {
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
		return 40;
	}
	
	public void playBreakingSound(IWorld worldIn, BlockPos pos) {
	}

	public void playBrokenSound(World worldIn, BlockPos pos) {
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		super.tick();
		World world = this.entity.world;
		BlockPos blockpos = new BlockPos(this.entity);
		BlockPos blockpos1 = this.findTarget(blockpos, world);
		Random random = this.entity.getRNG();
		if (this.isEntityNearBy() && blockpos1 != null) {
			if (this.breakingTime > 0) {
				Vec3d vec3d = this.entity.getMotion();
				this.entity.setMotion(vec3d.x, 0.3D, vec3d.z);
			}

			if (this.breakingTime % 2 == 0) {
				Vec3d vec3d1 = this.entity.getMotion();
				this.entity.setMotion(vec3d1.x, -0.3D, vec3d1.z);
				if (this.breakingTime % 6 == 0) {
					this.playBreakingSound(world, this.destinationBlock);
				}
			}

			if (this.breakingTime > this.getBreakTime(entity)) {
				world.removeBlock(blockpos1, false);
				if (!world.isRemote) {
					for (int i = 0; i < 20; ++i) {
						double d3 = random.nextGaussian() * 0.02D;
						double d1 = random.nextGaussian() * 0.02D;
						double d2 = random.nextGaussian() * 0.02D;
						((ServerWorld) world).spawnParticle(ParticleTypes.POOF, (double) blockpos1.getX() + 0.5D,
								(double) blockpos1.getY(), (double) blockpos1.getZ() + 0.5D, 1, d3, d1, d2,
								(double) 0.15F);
					}

					this.playBrokenSound(world, blockpos1);
				}
			}

			++this.breakingTime;
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