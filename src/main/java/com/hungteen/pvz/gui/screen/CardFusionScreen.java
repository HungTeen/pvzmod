package com.hungteen.pvz.gui.screen;

import java.util.Arrays;
import java.util.List;

import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.gui.container.CardFusionContainer;
import com.hungteen.pvz.gui.search.SearchCategories;
import com.hungteen.pvz.gui.search.SearchOption;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.tileentity.CardFusionTileEntity;
import com.hungteen.pvz.utils.RenderUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CardFusionScreen extends AbstractOptionScreen<CardFusionContainer> {

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/card_fusion.png");
	protected Button craftButton;
	
	public CardFusionScreen(CardFusionContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 240;
		this.imageHeight = 256;
	}

	@Override
	protected void init() {
		super.init();
		this.craftButton = this.addButton(new Button(this.leftPos + 216, this.topPos + 154, 26, 18, new TranslationTextComponent("gui.pvz.fragment_splice"), (button) -> {
			if(this.craftButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.CARD_FUSION, 0, this.menu.te.array.get(1)));
			}
		}));
		this.craftButton.visible = false;
	}
	
	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}

	@Override
	public void tick() {
		super.tick();
		if(this.searchGui.getCurrentOption().isPresent()) {
			this.searchGui.getRecipeManager().clear();
			PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.FRAGMENT_SPLICE, 1, this.menu.te.array.get(1)));
			this.setGhostRecipe(this.searchGui.getCurrentOption().get());
			this.searchGui.resetCurrentOption();
		}
	}
	
	private void setGhostRecipe(SearchOption option) {
		if(option.isPlant()) {
			Plants plantType = option.getPlant().get();
			this.searchGui.getRecipeManager().setRecipe(this.menu.getRecipeForPlant(plantType));
		}
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		this.craftButton.visible = this.canCraftNow();
		this.renderTooltip(stack, mouseX, mouseY);
		StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("block.pvz.card_fusion_table").getString(), this.leftPos + this.imageWidth / 2, this.topPos + 8, Colors.BLACK, 1F);
		StringUtil.drawCenteredScaledString(stack, font, "" + this.menu.te.array.get(0), this.leftPos + 15, this.topPos + 67, Colors.BLACK, 0.5F);
	    super.render(stack, mouseX, mouseY, partialTicks);
    }
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		int maxLen = 124;
		int len = RenderUtil.getRenderBarLen(this.menu.te.array.get(0), CardFusionTileEntity.MAX_SUN_AMOUNT, maxLen);
		blit(stack, this.leftPos + 7, this.topPos + 149 - len + 1, 240, 0, 16, len);
		stack.popPose();
	}

	protected boolean canCraftNow() {
		return this.menu.te.array.get(1) >= 0;
	}
	
	@Override
	public boolean isOptionUnLocked(SearchOption option) {
		return true;
	}

	@Override
	public List<SearchCategories> getSearchCategories() {
		return Arrays.asList(SearchCategories.FUSION);
	}

}
