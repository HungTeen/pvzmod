package com.hungteen.pvz.gui.screen;

import com.hungteen.pvz.gui.container.SunConverterContainer;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunConverterScreen extends ContainerScreen<SunConverterContainer> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");
	
	public SunConverterScreen(SunConverterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
        this.minecraft.getTextureManager().bind(TEXTURE);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        stack.popPose();
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("block.pvz.sun_converter").getContents(), this.leftPos + this.imageWidth / 2, this.topPos + 4, Colors.BLACK, 1F);
		renderTooltip(stack, mouseX, mouseY);
	}
	
}
