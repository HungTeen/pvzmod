package com.hungteen.pvz.gui.screen;

import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.gui.container.EssenceAltarContainer;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.systems.RenderSystem;

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
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void init() {
		super.init();
		this.craftButton = this.addButton(new Button(this.guiLeft + 135, this.guiTop + 34, 18, 18, new TranslationTextComponent("gui.pvz.essence_altar.button").getFormattedText(), (button) -> {
			if(this.craftButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.ESSENCE_ALTAR, 0, 0));
			}
		}));
		this.craftButton.visible = false;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.pushMatrix();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        RenderSystem.popMatrix();
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		super.render(mouseX, mouseY, partialTicks);
		this.craftButton.visible = ! this.container.isInventoryEmpty();
		StringUtil.drawCenteredScaledString(font, new TranslationTextComponent("block.pvz.essence_altar").getFormattedText(), this.guiLeft + this.xSize / 2, this.guiTop + 4, Colors.BLACK, 1F);
		renderHoveredToolTip(mouseX, mouseY);
	}

}
