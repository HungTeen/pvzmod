package com.hungteen.pvz.entity.ai;

import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.ForgeEventFactory;

public class DestroyDownBlockGoal extends Goal {

	private final MobEntity taskOwner;

	public DestroyDownBlockGoal(MobEntity taskOwner) {
		this.taskOwner = taskOwner;
	}

	@Override
	public boolean shouldExecute() {
		return taskOwner.collidedHorizontally && ForgeEventFactory.getMobGriefingEvent(taskOwner.world, taskOwner);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void startExecuting() {
		// NAGA SMASH!
		if (taskOwner.world.isRemote) return;

		AxisAlignedBB bb = taskOwner.getBoundingBox();

		int minx = MathHelper.floor(bb.minX - 0.75D);
		int miny = MathHelper.floor(bb.minY + 1.01D);
		int minz = MathHelper.floor(bb.minZ - 0.75D);
		int maxx = MathHelper.floor(bb.maxX + 0.75D);
		int maxy = MathHelper.floor(bb.maxY + 0.0D);
		int maxz = MathHelper.floor(bb.maxZ + 0.75D);

		BlockPos min = new BlockPos(minx, miny, minz);
		BlockPos max = new BlockPos(maxx, maxy, maxz);

		if (taskOwner.world.isAreaLoaded(min, max)) {
			for (BlockPos pos : BlockPos.getAllInBoxMutable(min, max)) {
				if (EntityUtil.canDestroyBlock(taskOwner.world, pos, taskOwner)) {
					taskOwner.world.destroyBlock(pos, true);
				}
			}
		}
	}
}