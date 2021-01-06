package com.hungteen.pvz.gui.almanac;

import java.util.List;
import java.util.Locale;

import com.google.common.collect.Lists;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlmanacSearchGui extends AbstractGui implements IRenderable, IGuiEventListener {

	public static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/almanac_search.png");
	private Minecraft mc;
	protected AlmanacContainer container;
	private TextFieldWidget searchBar;
	private final List<AlmanacToggleWidget> toggleTabs = Lists.newArrayList();
	private AlmanacToggleWidget currentTab;
	protected final AlmanacPage page = new AlmanacPage();
	private String lastSearch = "";
	private int width;
	private int height;
	private int guiLeft;
	private int guiTop;
	private final int xSize = 150;
	private final int ySize = 200;
	private boolean canType;

	public void init(Minecraft mc, AlmanacContainer container, int widthIn, int heightIn) {
		this.mc = mc;
		this.container = container;
		this.width = widthIn;
		this.height = heightIn;
		mc.keyboardListener.enableRepeatEvents(true);
	}

	public void initSearchBar() {
		String s = this.searchBar != null ? this.searchBar.getText() : "";
		this.searchBar = new TextFieldWidget(this.mc.fontRenderer, this.guiLeft + 25, this.guiTop + 14, 100, 14,
				I18n.format("itemGroup.search"));
		this.searchBar.setMaxStringLength(50);
		this.searchBar.setEnableBackgroundDrawing(false);
		this.searchBar.setVisible(true);
		this.searchBar.setTextColor(16777215);
		this.searchBar.setText(s);
		this.page.init(this.mc, this.guiLeft, this.guiTop);
		this.toggleTabs.clear();
		for (Almanac.Categories c : Almanac.Categories.values()) {
			this.toggleTabs.add(new AlmanacToggleWidget(c));
		}
		if (this.currentTab == null) {
			this.currentTab = this.toggleTabs.get(0);
		}
		this.currentTab.setStateTriggered(true);
		this.updateCollections(false);
		this.updateTabs();
	}

	private void updateCollections(boolean p_193003_1_) {
		List<Almanac> list = this.page.getCurrentList(this.currentTab.getCategory());
		String s = this.searchBar.getText();
		if (!s.isEmpty()) {
			list.removeIf((a) -> {
				String now = Almanac.getAlmanacName(a).toLowerCase();
				return !StringUtil.KMP.kmp(now, s.toLowerCase());
			});

		}
		this.page.updateLists(list, p_193003_1_);
	}

	public void tick() {

	}

	public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
//		System.out.println(p_keyPressed_1_);
		this.canType = false;
		if (!this.mc.player.isSpectator()) {
			if (this.searchBar.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_)) {
//				System.out.println("can update !");
				this.updateSearch();
				return true;
			} else if (this.searchBar.isFocused()) {
//				System.out.println("focus !");
				return true;
			} else if (this.mc.gameSettings.keyBindChat.matchesKey(p_keyPressed_1_, p_keyPressed_2_)
					&& !this.searchBar.isFocused()) {
//				System.out.println("prepare focus !");
				this.canType = true;
				this.searchBar.setFocused2(true);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void updateSearch() {
		String s = this.searchBar.getText().toLowerCase(Locale.ROOT);
		this.pirateRecipe(s);
		if (!s.equals(this.lastSearch)) {
//			System.out.println("yeah");
			this.updateCollections(false);
			this.lastSearch = s;
		}
	}

	/**
	 * "Check if we should activate the pirate speak easter egg"
	 */
	private void pirateRecipe(String text) {
		if ("excitedze".equals(text)) {
			LanguageManager languagemanager = this.mc.getLanguageManager();
			Language language = languagemanager.getLanguage("en_pt");
			if (languagemanager.getCurrentLanguage().compareTo(language) == 0) {
				return;
			}
			languagemanager.setCurrentLanguage(language);
			this.mc.gameSettings.language = language.getCode();
			net.minecraftforge.client.ForgeHooksClient.refreshResources(this.mc,
					net.minecraftforge.resource.VanillaResourceType.LANGUAGES);
			this.mc.fontRenderer.setBidiFlag(languagemanager.isCurrentLanguageBidirectional());
			this.mc.gameSettings.saveOptions();
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
				for (AlmanacToggleWidget toggle : this.toggleTabs) {
					if (toggle.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
//						System.out.println("clicked");
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

		this.page.render(this.guiLeft, this.guiTop, mouseX, mouseY, partialTicks);
		RenderSystem.popMatrix();
	}

	public void renderTooltip(int mouseX, int mouseY) {
		this.page.renderTooltip(mouseX, mouseY);
	}

	public void removed() {
		this.searchBar = null;
		this.currentTab = null;
		this.mc.keyboardListener.enableRepeatEvents(false);
	}

	public boolean hasClickedOutside(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
		boolean flag = mouseX < guiLeft || mouseY < guiTop || mouseX >= guiLeft + xSize || mouseY >= guiTop + ySize;
		return flag && !this.currentTab.isHovered();
	}

	public Almanac getCurrentAlmanac() {
		return this.page.getCurrentAlmanacs();
	}
	
	private void updateTabs() {
		int x = this.guiLeft - 30;
		int y = this.guiTop + 10;
		int h = 27;
		for (int i = 0; i < this.toggleTabs.size(); i++) {
			AlmanacToggleWidget toggle = this.toggleTabs.get(i);
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
