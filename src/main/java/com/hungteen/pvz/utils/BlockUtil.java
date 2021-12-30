package com.hungteen.pvz.utils;

import java.util.stream.Stream;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.items.IItemHandler;

public class BlockUtil {
	/**
	 * Calculate the redstone current from a item stack handler
	 * 
	 * @param handler The handler
	 * @return The redstone power
	 */
	public static int calculateRedstone(IItemHandler handler) {
		int i = 0;
		float f = 0.0F;
		for (int j = 0; j < handler.getSlots(); j++) {
			ItemStack stack = handler.getStackInSlot(j);
			if (!stack.isEmpty()) {
				f += (float) stack.getCount() / (float) Math.min(handler.getSlotLimit(j), stack.getMaxStackSize());
				i++;
			}
		}
		f = f / (float) handler.getSlots();
		return MathHelper.floor(f * 14.0F) + (i > 0 ? 1 : 0);
	}
	
	public static AxisAlignedBB getAABB(BlockPos pos, double w, double h) {
		return new AxisAlignedBB(pos.getX() - w, pos.getY() - h, pos.getZ() - w, pos.getX() + w, pos.getY() + h, pos.getZ() + w);
	}

	public static double getBlockPosOffset(IWorldReader worldReader, BlockPos pos, AxisAlignedBB aabb) {
		AxisAlignedBB axisalignedbb = new AxisAlignedBB(pos);
		Stream<VoxelShape> stream = worldReader.getCollisions((Entity) null, axisalignedbb, e -> true);
		return 1.0D + VoxelShapes.collide(Direction.Axis.Y, aabb, stream, -1.0D);
	}

}
