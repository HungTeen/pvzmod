package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.client.gui.GuiHandler;
import com.hungteen.pvz.common.container.EssenceAltarContainer;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toserver.ClickButtonPacket;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EssenceAltarScreen extends ContainerScreen<EssenceAltarContainer> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");
	protected Button craftButton;
	
	public EssenceAltarScreen(EssenceAltarContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	protected void init() {
		super.init();
		this.craftButton = this.addButton(new Button(this.leftPos + 135, this.topPos + 34, 18, 18, new TranslationTextComponent("gui.pvz.essence_altar.button"), (button) -> {
			if(this.craftButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.ESSENCE_ALTAR, 0, 0));
			}
		}));
		this.craftButton.visible = false;
	}
	
	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
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
		this.craftButton.visible = ! this.menu.isInventoryEmpty();
		StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("block.pvz.essence_altar").getString(), this.leftPos + this.imageWidth / 2, this.topPos + 4, Colors.BLACK, 1F);
		this.renderTooltip(stack, mouseX, mouseY);
	}

}
