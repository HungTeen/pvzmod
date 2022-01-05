package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.client.gui.widget.DisplayField;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class PVZContainerScreen<T extends Container> extends ContainerScreen<T> {

	private static final ResourceLocation WIDGETS = StringUtil.prefix("textures/gui/widgets.png");
	protected final List<DisplayField> tips = new ArrayList<>();

	public PVZContainerScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
		this.minecraft.getTextureManager().bind(WIDGETS);
		this.tips.forEach(tip -> {
			blit(stack, this.leftPos + tip.getX(), this.topPos + tip.getY(), tip.getTexX(), tip.getTexY(), tip.getWidth(), tip.getHeight());
		});
		stack.popPose();
	}
	
	@Override
	protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
	}

	@Override
	protected void renderTooltip(MatrixStack stack, int mouseX, int mouseY) {
		super.renderTooltip(stack, mouseX, mouseY);
		this.tips.forEach(tip -> {
			if (tip.isInField(mouseX - this.leftPos, mouseY - this.topPos)) {
				this.minecraft.screen.renderComponentTooltip(stack, tip.getTexts(), mouseX, mouseY);
			}
		});
	}

}
