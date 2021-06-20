package com.hungteen.pvz.common.entity.ai.navigator;

import net.minecraft.entity.MobEntity;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.SwimNodeProcessor;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ZombieWaterPathNavigator extends SwimmerPathNavigator {

	public ZombieWaterPathNavigator(MobEntity p_i45873_1_, World p_i45873_2_) {
		super(p_i45873_1_, p_i45873_2_);
	}

	@Override
	protected PathFinder createPathFinder(int p_179679_1_) {
		this.nodeEvaluator = new SwimNodeProcessor(true);
		return new PathFinder(this.nodeEvaluator, p_179679_1_);
	}

	@Override
	public void tick() {
		++this.tick;
		if (tick % 20 == 0) {
			this.recomputePath();
		}
		if (!this.isDone()) {
			if (this.canUpdatePath()) {
				this.followThePath();
			} else if (this.path != null && !this.path.isDone()) {
				Vector3d vector3d = this.path.getNextEntityPos(this.mob);
				if (MathHelper.floor(this.mob.getX()) == MathHelper.floor(vector3d.x)
						&& MathHelper.floor(this.mob.getY()) == MathHelper.floor(vector3d.y)
						&& MathHelper.floor(this.mob.getZ()) == MathHelper.floor(vector3d.z)) {
					this.path.advance();
				}
			}

			DebugPacketSender.sendPathFindingPacket(this.level, this.mob, this.path, this.maxDistanceToWaypoint);
			if (!this.isDone()) {
				Vector3d vector3d1 = this.path.getNextEntityPos(this.mob);
				this.mob.getMoveControl().setWantedPosition(vector3d1.x, vector3d1.y, vector3d1.z, this.speedModifier);
			}
		}
	}

	@Override
	protected void followThePath() {
		if (this.path != null) {

			Vector3d vector3d = this.getTempMobPos();
			float f = this.mob.getBbWidth();
			float f1 = f > 0.75F ? f / 2.0F : 0.75F - f / 2.0F;
			Vector3d vector3d1 = this.mob.getDeltaMovement();
			if (Math.abs(vector3d1.x) > 0.2D || Math.abs(vector3d1.z) > 0.2D) {
				f1 = (float) ((double) f1 * vector3d1.length() * 6.0D);
			}

			Vector3d vector3d2 = Vector3d.atBottomCenterOf(this.path.getNextNodePos());
			if (Math.abs(this.mob.getX() - vector3d2.x) < (double) f1
					&& Math.abs(this.mob.getZ() - vector3d2.z) < (double) f1
//					&& Math.abs(this.mob.getY() - vector3d2.y) < (double) (f1 * 2.0F)
			) {

				this.path.advance();
			}

			for (int j = Math.min(this.path.getNextNodeIndex() + 6, this.path.getNodeCount() - 1); j > this.path
					.getNextNodeIndex(); --j) {
				vector3d2 = this.path.getEntityPosAtNode(this.mob, j);
				if (!(vector3d2.distanceToSqr(vector3d) > 36.0D)
						&& this.canMoveDirectly(vector3d, vector3d2, 0, 0, 0)) {
					this.path.setNextNodeIndex(j);
					break;
				}
			}

			this.doStuckDetection(vector3d);
		}
	}

}
