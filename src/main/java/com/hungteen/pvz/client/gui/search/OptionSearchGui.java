package com.hungteen.pvz.client.gui.search;

import com.google.common.collect.Lists;
import com.hungteen.pvz.client.gui.screen.AbstractOptionScreen;
import com.hungteen.pvz.common.container.AbstractOptionContainer;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class OptionSearchGui extends AbstractGui implements IRenderable, IGuiEventListener {

	public static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/almanac_search.png");
	private Minecraft mc;
	protected AbstractOptionContainer container;
	private TextFieldWidget searchBar;
	protected final RecipeManager recipeManager = new RecipeManager();
	private final List<CategoryToggleWidget> toggleTabs = Lists.newArrayList();
	private CategoryToggleWidget currentTab;
	protected OptionPage page;
	private AbstractOptionScreen<?> screen;
	private String lastSearch = "";
	private int width;
	private int height;
	private int guiLeft;
	private int guiTop;
	private final int xSize = 150;
	private final int ySize = 200;
	private boolean canType;

	public void init(Minecraft mc, AbstractOptionScreen<?> screen, AbstractOptionContainer container, int widthIn,
			int heightIn) {
		this.mc = mc;
		this.screen = screen;
		this.container = container;
		this.width = widthIn;
		this.height = heightIn;
		this.page = new OptionPage(screen);
		mc.keyboardHandler.setSendRepeatsToGui(true);
	}

	public void initSearchBar() {
		String s = this.searchBar != null ? this.searchBar.getValue() : "";
		this.searchBar = new TextFieldWidget(this.mc.font, this.guiLeft + 25, this.guiTop + 14, 100, 14, new StringTextComponent(I18n.get("itemGroup.search")));
		this.searchBar.setMaxLength(50);
		this.searchBar.setBordered(false);
		this.searchBar.setVisible(true);
		this.searchBar.setTextColor(16777215);
		this.searchBar.setValue(s);
		this.page.init(this.mc, this.guiLeft, this.guiTop);
		this.toggleTabs.clear();
		for (CategoryToggleWidget.SearchCategories c : this.screen.getSearchCategories()) {
			this.toggleTabs.add(new CategoryToggleWidget(c));
		}
		if (this.currentTab == null) {
			this.currentTab = this.toggleTabs.get(0);
		}
		this.currentTab.setStateTriggered(true);
		this.updateCollections(false);
		this.updateTabs();
	}

	public void slotClicked(@Nullable Slot slotIn) {
		if (this.container.isCraftSlot(slotIn)) {
			this.recipeManager.clear();
		}
	}
	
	private void updateCollections(boolean p_193003_1_) {
		List<SearchOption> list = this.page.getCurrentList(this.currentTab.getCategory());
		String s = this.searchBar.getValue();
		if (! s.isEmpty()) {
			list.removeIf((a) -> {
				String now = a.getType().getText().getString();
				return ! AlgorithmUtil.KMP.kmp(now, s.toLowerCase());
			});

		}
		this.page.updateLists(list, p_193003_1_);
	}

	public void tick() {

	}

	public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
		this.canType = false;
		if (!this.mc.player.isSpectator()) {
			if (this.searchBar.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_)) {
				this.updateSearch();
				return true;
			} else if (this.searchBar.isFocused()) {
				return true;
			} else if (this.mc.options.keyChat.matches(p_keyPressed_1_, p_keyPressed_2_)
					&& !this.searchBar.isFocused()) {
				this.canType = true;
				this.searchBar.setFocus(true);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void updateSearch() {
		String s = this.searchBar.getValue().toLowerCase(Locale.ROOT);
		if (!s.equals(this.lastSearch)) {
			this.updateCollections(false);
			this.lastSearch = s;
		}
	}

	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		this.canType = false;
		return IGuiEventListener.super.keyReleased(keyCode, scanCode, modifiers);
	}

	public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
		if (this.canType) {
			return false;
		} else if (!this.mc.player.isSpectator()) {
			if (this.searchBar.charTyped(p_charTyped_1_, p_charTyped_2_)) {
				this.updateSearch();
				return true;
			} else {
				return IGuiEventListener.super.charTyped(p_charTyped_1_, p_charTyped_2_);
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
		if (!this.mc.player.isSpectator()) {
			if (this.page.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
				return true;
			} else if (this.searchBar.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
				return true;
			} else {
				for (CategoryToggleWidget toggle : this.toggleTabs) {
					if (toggle.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
						if (this.currentTab != toggle) {
							this.currentTab.setStateTriggered(false);
							this.currentTab = toggle;
							this.currentTab.setStateTriggered(true);
							this.updateCollections(true);
						}
						return true;
					}
				}
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		stack.pushPose();
		stack.translate(0.0F, 0.0F, 100.0F);
		this.mc.getTextureManager().bind(TEXTURE);
		this.blit(stack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		this.searchBar.render(stack, mouseX, mouseY, partialTicks);

		for (CategoryToggleWidget a : this.toggleTabs) {
			a.render(stack, mouseX, mouseY, partialTicks);
		}

		this.page.render(stack, this.guiLeft, this.guiTop, mouseX, mouseY, partialTicks);
		stack.popPose();
	}

	/**
	 * render tool tips for ghost recipes and the page.
	 */
	public void renderTooltip(MatrixStack stack, int guiLeft, int guiTop, int mouseX, int mouseY) {
		this.page.renderTooltip(stack, mouseX, mouseY);
	    this.recipeManager.renderGhostRecipeTooltip(this.mc, stack, guiLeft, guiTop, mouseX, mouseY);
	}


	public void removed() {
		this.searchBar = null;
		this.currentTab = null;
		this.mc.keyboardHandler.setSendRepeatsToGui(false);
	}

	public boolean hasClickedOutside(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
		boolean flag = mouseX < guiLeft || mouseY < guiTop || mouseX >= guiLeft + xSize || mouseY >= guiTop + ySize;
		return flag && !this.currentTab.isHovered();
	}

	public Optional<SearchOption> getCurrentOption() {
		return Optional.ofNullable(this.page.getCurrentOption());
	}
	
	public void resetCurrentOption() {
		this.page.resetCurrentOption();
	}

	public AbstractOptionScreen<?> getOptionScreen() {
		return this.screen;
	}
	
	public RecipeManager getRecipeManager() {
		return this.recipeManager;
	}

	private void updateTabs() {
		int x = this.guiLeft - 30;
		int y = this.guiTop + 10;
		int h = 27;
		for (int i = 0; i < this.toggleTabs.size(); i++) {
			CategoryToggleWidget toggle = this.toggleTabs.get(i);
			toggle.visible = true;
			toggle.setPosition(x, y + h * i);
		}
	}

	public int updateScreenPosition(int x, int y) {
		int totWidth = this.xSize + x;
		this.guiLeft = (this.width - totWidth) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		return this.guiLeft + this.xSize;
	}
}
