package com.hungteen.pvz.gui;

import com.hungteen.pvz.capabilities.player.ClientPlayerResources;
import com.hungteen.pvz.gui.container.AlmanacContainer;
import com.hungteen.pvz.utils.ItemUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.RenderUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Almanacs;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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
		this.renderAlmanac();
		this.searchGui.renderTooltip(mouseX, mouseY);
	}
	
	protected void renderAlmanac(){
		Almanacs current = this.searchGui.getCurrentAlmanac();
		if(this.searchGui.getCurrentAlmanac()==null) {
			current = Almanacs.PEA_SHOOTER;
		}
		this.renderTitle(current);
		if(!ClientPlayerResources.isAlmanacUnLocked(current)) {
			return ;
		}
		this.renderLogo(current);
		this.renderXpBar(current);
		this.renderShortInfo(current);
	}
	
	protected void renderShortInfo(Almanacs a) {
		RenderSystem.pushMatrix();
		int posX = this.guiLeft + 82 + 150 / 2;
		int posY = this.guiTop + 26 + 2;
		for(int i=0;i<4;i++) {
			StringUtil.drawCenteredScaledString(font, new TranslationTextComponent("gui.pvz."+a.toString().toLowerCase()+i).getFormattedText(), posX, posY, Colors.BLACK, 0.8f);
			posY += 12;
		}
		RenderSystem.popMatrix();
	}
	
	protected void renderXpBar(Almanacs a) {
		RenderSystem.pushMatrix();
		int maxLvl = 1, lvl = 1;
		Plants p = null;
		if(Almanacs.isPlant(a)) {
			p = Plants.getPlantByName(a.toString().toLowerCase());
			maxLvl = PlantUtil.getPlantMaxLvl(p);
			lvl = ClientPlayerResources.getPlayerPlantCardLvl(p);
		}
		String lvlInfo = "LVL:"+maxLvl+"[MAX]";
		int barWidth = 62, barHeight = 9;
		int len = barWidth;
		if(maxLvl != lvl && p != null) { 
			lvlInfo = "LVL:"+lvl;
			int xp = ClientPlayerResources.getPlayerPlantCardXp(p);
			int maxXp = PlantUtil.getPlantLevelUpXp(p, lvl);
			len = RenderUtil.getRenderBarLen(xp, maxXp, barWidth);
		}
		int texX = 0;
		int texY = maxLvl == lvl ? 210 : 200;
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		blit(this.guiLeft + 9, this.guiTop + 66, texX, texY, len, barHeight);
		StringUtil.drawCenteredScaledString(this.font, lvlInfo, this.guiLeft + 9 + barWidth / 2, this.guiTop + 66 + 1, Colors.BLACK, 1f);
		RenderSystem.popMatrix();
	}
	
	protected void renderTitle(Almanacs a) {
		RenderSystem.pushMatrix();
		int dx = this.guiLeft + 82 + 150 / 2, dy = this.guiTop + 10 + 2;
		StringUtil.drawCenteredScaledString(this.font, Almanacs.getAlmanacName(a), dx, dy, Colors.BLACK, 1.6f);
		RenderSystem.popMatrix();
	}
	
	protected void renderLogo(Almanacs a) {
		int dx = this.guiLeft + 16, dy = this.guiTop + 14;
		int scale = 3;
		RenderSystem.pushMatrix();
		RenderSystem.scaled(scale, scale, scale);
		RenderSystem.translated((dx % scale) * 1.0d / scale , (dy % scale) * 1.0d / scale, 0);
		this.itemRenderer.renderItemIntoGUI(ItemUtil.getItemStackByAlmanac(a), dx / scale, dy / scale);
		RenderSystem.popMatrix();
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
