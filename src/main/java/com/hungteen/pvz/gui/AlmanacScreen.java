package com.hungteen.pvz.gui;

import com.hungteen.pvz.gui.container.AlmanacContainer;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlmanacScreen extends ContainerScreen<AlmanacContainer> {

	public static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/almanac.png");
	private final AlmanacSearchGui searchGui = new AlmanacSearchGui();

	public AlmanacScreen(AlmanacContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.xSize = 240;
		this.ySize = 200;
	}

	@Override
	protected void init() {
		super.init();
		this.searchGui.init(this.minecraft, this.container, this.width, this.height);
		this.guiLeft = this.searchGui.updateScreenPosition(this.xSize, this.ySize);
		this.searchGui.initSearchBar();
		this.children.add(this.searchGui);
		this.setFocusedDefault(this.searchGui);
	}

	@Override
	public void tick() {
		super.tick();
		this.searchGui.tick();
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.searchGui.render(mouseX, mouseY, partialTicks);
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
		boolean flag = mouseX < guiLeftIn || mouseY < guiTopIn || mouseX >= guiLeftIn + this.xSize || mouseY >= guiTopIn + this.ySize;
	    return this.searchGui.hasClickedOutside(mouseX, mouseY, this.guiLeft, this.guiTop, mouseButton) && flag;
	}
	
	@Override
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
		if (this.searchGui.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
			return true;
		} else {
			return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
		}
	}

	@Override
	public void removed() {
		this.searchGui.removed();
		super.removed();
	}

}
