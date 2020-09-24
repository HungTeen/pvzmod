package com.hungteen.pvz.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.hungteen.pvz.gui.container.AlmanacContainer;
import com.hungteen.pvz.gui.widget.AlmanacToggleWidget;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Almanacs;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.recipebook.RecipeTabToggleWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlmanacSearchGui extends AbstractGui implements IRenderable, IGuiEventListener {

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/almanac_search.png");
	private Minecraft mc;
	protected AlmanacContainer container;
	private TextFieldWidget searchBar;
	private final List<AlmanacToggleWidget> toggleTabs = Lists.newArrayList();
	private AlmanacToggleWidget currentTab;
	private int width;
	private int height;
	private int guiLeft;
	private int guiTop;
	private final int xSize = 147;
	private final int ySize = 191;

	public void init(Minecraft mc, AlmanacContainer container, int widthIn, int heightIn) {
		this.mc = mc;
		this.container = container;
		this.width = widthIn;
		this.height = heightIn;
		this.initSearchBar();
	}

	public void initSearchBar() {
//		this.stackedContents.clear();
//		this.mc.player.inventory.accountStacks(this.stackedContents);
//		this.field_201522_g.fillStackedContents(this.stackedContents);
		String s = this.searchBar != null ? this.searchBar.getText() : "";
		this.searchBar = new TextFieldWidget(this.mc.fontRenderer, this.guiLeft + 25, this.guiTop + 14, 100, 14, I18n.format("itemGroup.search"));
		this.searchBar.setMaxStringLength(50);
		this.searchBar.setEnableBackgroundDrawing(false);
		this.searchBar.setVisible(true);
		this.searchBar.setTextColor(16777215);
		this.searchBar.setText(s);
//		this.recipeBookPage.init(this.mc, i, j);
//		this.recipeBookPage.addListener(this);
//		this.toggleRecipesBtn = new ToggleWidget(i + 110, j + 12, 26, 16,
//				this.recipeBook.isFilteringCraftable(this.field_201522_g));
//		this.func_205702_a();
		this.toggleTabs.clear();
		for (Almanacs.Categories c : Almanacs.Categories.values()) {
			this.toggleTabs.add(new AlmanacToggleWidget(c));
		}
		if (this.currentTab == null) {
			this.currentTab = this.toggleTabs.get(0);
		}
		this.currentTab.setStateTriggered(true);
//		this.updateCollections(false);
//		this.updateTabs();
	}

	public void tick() {

	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		RenderSystem.pushMatrix();
		RenderSystem.translatef(0.0F, 0.0F, 100.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		this.searchBar.render(mouseX, mouseY, partialTicks);

		for (AlmanacToggleWidget a : this.toggleTabs) {
			a.render(mouseX, mouseY, partialTicks);
		}

//		this.toggleRecipesBtn.render(mouseX, mouseY, partialTicks);
//		this.recipeBookPage.render(i, j, mouseX, mouseY, partialTicks);
		RenderSystem.popMatrix();
	}

	public int updateScreenPosition(int x, int y) {
		int totWidth = this.xSize + x;
		this.guiLeft = (this.width - totWidth) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		return this.guiLeft + this.xSize;
	}
}
