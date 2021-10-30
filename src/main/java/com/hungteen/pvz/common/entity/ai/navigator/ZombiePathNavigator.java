package com.hungteen.pvz.common.entity.ai.navigator;

import com.hungteen.pvz.common.entity.ai.processor.ZombieNodeProcessor;

import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;

public class ZombiePathNavigator extends GroundPathNavigator {

	public ZombiePathNavigator(MobEntity p_i45875_1_, World p_i45875_2_) {
		super(p_i45875_1_, p_i45875_2_);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void followThePath() {
		/* use to debug path. */
//		if(this.mob.tickCount % 100 == 0) {
//			final Path path = this.mob.getNavigation().getPath();
//			if(path != null) {
//				for(int i = 0;i < path.getNodeCount(); ++ i) {
//					ItemEntity item = EntityType.ITEM.create(this.mob.maxLevel);
//					item.setItem(new ItemStack(Items.OAK_LOG));
//					item.setPos(path.getNode(i).x, path.getNode(i).y, path.getNode(i).z);
//					this.mob.maxLevel.addFreshEntity(item);
//				}
//			}
//		}
		/* end */
		Vector3d vector3d = this.getTempMobPos();
		this.maxDistanceToWaypoint = this.mob.getBbWidth() > 0.75F ? this.mob.getBbWidth() / 2.0F : 0.75F - this.mob.getBbWidth() / 2.0F;
		Vector3i vector3i = this.path.getNextNodePos();
		final double d0 = Math.abs(this.mob.getX() - ((double)vector3i.getX() + (this.mob.getBbWidth() + 1) / 2D)); //Forge: Fix MC-94054
		final double d2 = Math.abs(this.mob.getZ() - ((double)vector3i.getZ() + (this.mob.getBbWidth() + 1) / 2D)); //Forge: Fix MC-94054
		final boolean flag = d0 < (double) this.maxDistanceToWaypoint && d2 < (double) this.maxDistanceToWaypoint;
		if (flag || this.mob.canCutCorner(this.path.getNextNode().type)
				&& this.shouldTargetNextNodeInDirection(vector3d)) {
			this.path.advance();
		}
		this.doStuckDetection(vector3d);
	}

	/**
	 * copy from super.
	 */
	private boolean shouldTargetNextNodeInDirection(Vector3d p_234112_1_) {
		if (this.path.getNextNodeIndex() + 1 >= this.path.getNodeCount()) {
			return false;
		} else {
			Vector3d vector3d = Vector3d.atBottomCenterOf(this.path.getNextNodePos());
			if (!p_234112_1_.closerThan(vector3d, 2.0D)) {
				return false;
			} else {
				Vector3d vector3d1 = Vector3d.atBottomCenterOf(this.path.getNodePos(this.path.getNextNodeIndex() + 1));
				Vector3d vector3d2 = vector3d1.subtract(vector3d);
				Vector3d vector3d3 = p_234112_1_.subtract(vector3d);
				return vector3d2.dot(vector3d3) > 0.0D;
			}
		}
	}

	@Override
	protected PathFinder createPathFinder(int p_179679_1_) {
		this.nodeEvaluator = new ZombieNodeProcessor();
		this.nodeEvaluator.setCanPassDoors(true);
		return new PathFinder(this.nodeEvaluator, p_179679_1_);
	}

}
