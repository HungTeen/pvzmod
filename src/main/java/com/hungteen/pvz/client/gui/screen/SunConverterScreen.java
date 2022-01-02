package com.hungteen.pvz.client.gui.screen;

import java.util.Arrays;

import com.hungteen.pvz.client.gui.widget.DisplayField.TipField;
import com.hungteen.pvz.common.container.SunConverterContainer;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunConverterScreen extends PVZContainerScreen<SunConverterContainer> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");

	public SunConverterScreen(SunConverterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 176;
		this.imageHeight = 166;
		this.tips.add(new TipField(2, 2, Arrays.asList(
				new TranslationTextComponent("gui.pvz.sun_converter.tip1"),
				new TranslationTextComponent("gui.pvz.sun_converter.tip2")
		)));
	}

	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		stack.popPose();
		
		super.renderBg(stack, partialTicks, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		StringUtil.drawCenteredScaledString(stack, font,
				new TranslationTextComponent("block.pvz.sun_converter").getString(), this.leftPos + this.imageWidth / 2,
				this.topPos + 4, Colors.BLACK, 1F);
		renderTooltip(stack, mouseX, mouseY);
	}

}
