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
		return this.taskOwner.collidedHorizontally && ForgeEventFactory.getMobGriefingEvent(this.taskOwner.world, this.taskOwner);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void startExecuting() {
		if (this.taskOwner.world.isRemote) {
			return;
		}
		return ;
//		AxisAlignedBB aabb = this.taskOwner.getBoundingBox();
//		int minx = MathHelper.floor(aabb.minX);
//		int miny = MathHelper.floor(aabb.minY);
//		int minz = MathHelper.floor(aabb.minZ);
//		int maxx = MathHelper.ceil(aabb.maxX);
//		int maxy = MathHelper.ceil(aabb.maxY);
//		int maxz = MathHelper.ceil(aabb.maxZ);
//		BlockPos min = new BlockPos(minx, miny, minz);
//		BlockPos max = new BlockPos(maxx, maxy, maxz);
//		System.out.println(max.add(-min.getX(), -min.getY(), -min.getZ()));
//		if (this.taskOwner.world.isAreaLoaded(min, max)) {
//			for (BlockPos pos : BlockPos.getAllInBoxMutable(min, max)) {
//				if (EntityUtil.canDestroyBlock(this.taskOwner.world, pos, this.taskOwner)) {
//					this.taskOwner.world.destroyBlock(pos, true);
//				}
//			}
//		}
	}
	
}