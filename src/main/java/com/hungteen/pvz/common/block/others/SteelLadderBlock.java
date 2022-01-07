package com.hungteen.pvz.common.block.others;

import com.hungteen.pvz.common.block.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

public class SteelLadderBlock extends LadderBlock {

	private static final float MAX_SPEED_UP = 0.5F;
	private static final float UP_SPEED = 0.01F;
	private static float ladderSpeed = 0;
	
	public SteelLadderBlock() {
		super(Block.Properties.copy(Blocks.LADDER).strength(6F).harvestTool(ToolType.PICKAXE));
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader blockReader, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
		super.appendHoverText(itemStack, blockReader, textComponents, tooltipFlag);
		textComponents.add(new TranslationTextComponent("tooltip.pvz.steel_ladder").withStyle(TextFormatting.ITALIC));
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
