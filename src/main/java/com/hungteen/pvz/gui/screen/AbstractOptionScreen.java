package com.hungteen.pvz.gui.screen;

import java.util.List;

import com.hungteen.pvz.gui.container.AbstractOptionContainer;
import com.hungteen.pvz.gui.search.OptionSearchGui;
import com.hungteen.pvz.gui.search.SearchCategories;
import com.hungteen.pvz.gui.search.SearchOption;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractOptionScreen<T extends AbstractOptionContainer> extends ContainerScreen<T> {

	protected final OptionSearchGui searchGui = new OptionSearchGui();
	
	public AbstractOptionScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void init() {
		super.init();
		this.searchGui.init(this.minecraft, this, this.menu, this.width, this.height);
		this.leftPos = this.searchGui.updateScreenPosition(this.imageWidth, this.imageHeight);
		this.searchGui.initSearchBar();
		this.children.add(this.searchGui);
		this.setInitialFocus(this.searchGui);
	}

	@Override
	public void tick() {
		super.tick();
		this.searchGui.tick();
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		this.searchGui.render(stack, mouseX, mouseY, partialTicks);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.searchGui.renderGhostRecipe(stack, this.leftPos, this.topPos, true, partialTicks);
		this.renderTooltip(stack, mouseX, mouseY);
		this.searchGui.renderTooltip(stack, this.leftPos, this.topPos, mouseX, mouseY);
	}
	
	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}
	
	@Override
	protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
		boolean flag = mouseX < guiLeftIn || mouseY < guiTopIn || mouseX >= guiLeftIn + this.imageWidth || mouseY >= guiTopIn + this.imageHeight;
	    return this.searchGui.hasClickedOutside(mouseX, mouseY, this.leftPos, this.topPos, mouseButton) && flag;
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
	protected void slotClicked(Slot slotIn, int slotId, int mouseButton, ClickType type) {
		super.slotClicked(slotIn, slotId, mouseButton, type);
		this.searchGui.slotClicked(slotIn);
	}
	
	@Override
	public void removed() {
		this.searchGui.removed();
		super.removed();
	}

	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
	}
	
	public abstract boolean isOptionUnLocked(SearchOption option);
	
	public abstract List<SearchCategories> getSearchCategories();
	

}