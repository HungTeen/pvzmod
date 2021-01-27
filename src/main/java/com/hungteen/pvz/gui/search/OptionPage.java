package com.hungteen.pvz.gui.search;

import java.util.List;

import com.google.common.collect.Lists;
import com.hungteen.pvz.gui.screen.AbstractOptionScreen;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OptionPage {

	private static final int MAX_NUM_PER_PAGE = 25;
	private static final int NUM_PER_ROW = 5;
	private final int xOffset = 10;
	private final int yOffset = 30;
	private final int size = 25;
	private final List<OptionWidget> buttons = Lists.newArrayListWithCapacity(MAX_NUM_PER_PAGE);
	 private OptionWidget hoveredButton;
	private List<SearchOption> optionList;
	private Minecraft mc;
	private ToggleWidget forwardButton;
	private ToggleWidget backButton;
	private int totalPages;
	private int currentPage;
	private SearchOption lastClickedButton;

	public OptionPage(AbstractOptionScreen<?> screen) {
		for (int i = 0; i < MAX_NUM_PER_PAGE; ++i) {
			this.buttons.add(new OptionWidget(screen));
		}
	}

	public void init(Minecraft minecraft, int x, int y) {
		this.mc = minecraft;
		for (int i = 0; i < this.buttons.size(); ++i) {
			int xx = x + this.xOffset + this.size * (i % NUM_PER_ROW);
			int yy = y + this.yOffset + this.size * (i / NUM_PER_ROW);
			this.buttons.get(i).setPosition(xx, yy);
		}
		int width = 150;
		int height = 170;
		int dis = 90;  
		int side = (width - dis) / 2;
		this.forwardButton = new ToggleWidget(x + width /2 + side, y + height, 12, 17, false);
		this.forwardButton.initTextureValues(1, 208, 13, 18, OptionSearchGui.TEXTURE);
		this.backButton = new ToggleWidget(x + width / 2 - side - 13, y + height, 12, 17, true);
		this.backButton.initTextureValues(1, 208, 13, 18, OptionSearchGui.TEXTURE);
		this.forwardButton.visible = true;
		this.backButton.visible = true;
	}

	public void render(int x, int y, int mouseX, int mouseY, float partialTicks) {
		if (this.totalPages > 1) {
			String s = (this.currentPage + 1) + "/" + this.totalPages;
			StringUtil.drawCenteredScaledString(this.mc.fontRenderer, s, x + 75, y + 175, Colors.WHITE, 1.5f);
		}
		this.hoveredButton = null;
		for (OptionWidget card : this.buttons) {
			card.render(mouseX, mouseY, partialTicks);
			if (card.visible && card.isHovered()) {
	            this.hoveredButton = card;
	         }
		}
		this.backButton.render(mouseX, mouseY, partialTicks);
		this.forwardButton.render(mouseX, mouseY, partialTicks);
	}

	public boolean mouseClicked(double p_198955_1_, double p_198955_3_, int p_198955_5_) {
		if (this.forwardButton.mouseClicked(p_198955_1_, p_198955_3_, p_198955_5_)) {
			++this.currentPage;
			this.updateButtonsForPage();
			return true;
		} else if (this.backButton.mouseClicked(p_198955_1_, p_198955_3_, p_198955_5_)) {
			--this.currentPage;
			this.updateButtonsForPage();
			return true;
		} else {
			for (OptionWidget card : this.buttons) {
				if (card.mouseClicked(p_198955_1_, p_198955_3_, p_198955_5_)) {
					this.lastClickedButton = card.getSearchOption();
					return true;
				}
			}
			return false;
		}
	}

	public void renderTooltip(int mouseX, int mouseY) {
		if (this.mc.currentScreen != null && this.hoveredButton != null) {
			this.mc.currentScreen.renderTooltip(this.hoveredButton.getToolTipText(this.mc.currentScreen),
					mouseX, mouseY);
		}

	}

	public void updateLists(List<SearchOption> list, boolean flag) {
		this.optionList = list;
		this.totalPages = (int) Math.ceil((double) list.size() / MAX_NUM_PER_PAGE);
		if (this.totalPages <= this.currentPage || flag) {
			this.currentPage = 0;
		}
		this.updateButtonsForPage();
	}

	private void updateButtonsForPage() {
		int i = MAX_NUM_PER_PAGE * this.currentPage;
		for (int j = 0; j < this.buttons.size(); ++j) {
			OptionWidget card = this.buttons.get(j);
			if (i + j < this.optionList.size()) {
				SearchOption a = this.optionList.get(i + j);
				card.init(a);
				card.visible = true;
			} else {
				card.visible = false;
			}
		}

		this.updateArrowButtons();
	}

	private void updateArrowButtons() {
		this.forwardButton.visible = this.totalPages > 1 && this.currentPage < this.totalPages - 1;
		this.backButton.visible = this.totalPages > 1 && this.currentPage > 0;
	}

	public List<SearchOption> getCurrentList(SearchCategories category) {
		return SearchOption.getSearchOptionsByCategory(category);
	}

	public SearchOption getCurrentOption() {
		return this.lastClickedButton;
	}
	
	public void resetCurrentOption() {
		this.lastClickedButton = null;
	}

}
