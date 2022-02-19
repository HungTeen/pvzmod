package com.hungteen.pvz.common.block.others;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

public class SteelLadderBlock extends LadderBlock {

	public static final float MAX_SPEED_UP = 0.5F;
	public static final float UP_SPEED = 0.01F;
	
	public SteelLadderBlock() {
		super(Block.Properties.copy(Blocks.LADDER).strength(6F).harvestTool(ToolType.PICKAXE));
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader blockReader, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
		super.appendHoverText(itemStack, blockReader, textComponents, tooltipFlag);
		textComponents.add(new TranslationTextComponent("tooltip.pvz.steel_ladder").withStyle(TextFormatting.ITALIC));
	}

}
