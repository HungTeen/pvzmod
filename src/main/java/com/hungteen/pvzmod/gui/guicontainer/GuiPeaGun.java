package com.hungteen.pvzmod.gui.guicontainer;

import com.hungteen.pvzmod.util.Reference;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiPeaGun extends GuiContainer{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID+":"+"textures/gui/container/pea_gun.png");
	
	public GuiPeaGun(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		this.xSize=176;
		this.ySize=187;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1f, 1f, 1f, 1f);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX=(this.width-this.xSize)/2;
		int offsetY=(this.height-this.ySize)/2;
		this.drawTexturedModalRect(offsetX, offsetY, 0,0,this.xSize,this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = new TextComponentTranslation("pvzgui.pea_gun.name").getFormattedText();
		RenderUtil.drawCenteredScaledString(fontRenderer, name, 89, 3, 1.5f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
//	
//	@Override
//	protected void renderToolTip(ItemStack stack, int x, int y) {
//		System.out.println(stack.getItem().getRegistryName());
//		super.renderToolTip(stack, x, y);
//	}
}
