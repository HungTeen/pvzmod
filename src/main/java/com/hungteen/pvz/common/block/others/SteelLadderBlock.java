package com.hungteen.pvz.common.block.others;

import com.hungteen.pvz.common.block.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.ToolType;

public class SteelLadderBlock extends LadderBlock {

	private static final float MAX_SPEED_UP = 0.5F;
	private static final float UP_SPEED = 0.01F;
	private static float ladderSpeed = 0;
	
	public SteelLadderBlock() {
		super(Block.Properties.copy(Blocks.LADDER).strength(6F).harvestTool(ToolType.PICKAXE));
	}
	
	/**
	 * client side.
	 */
	public static void climbUp(PlayerEntity player) {
		if(player.horizontalCollision && player.onClimbable()){
			//is on steel ladder.
			if(player.level.getBlockState(player.blockPosition()).getBlock().is(BlockRegister.STEEL_LADDER.get())) {
				ladderSpeed = Math.min(MAX_SPEED_UP, ladderSpeed + UP_SPEED * 0.8F);
			    final Vector3d vec = player.getDeltaMovement();
			    player.setDeltaMovement(vec.x, ladderSpeed, vec.z);
			} else {
				ladderSpeed = Math.max(0, ladderSpeed - UP_SPEED);
			}
		} else {
			ladderSpeed = 0.06F;
		}
	}

}
