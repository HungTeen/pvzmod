package com.hungteen.pvz.client.gui.screen.shop;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.client.gui.GuiHandler;
import com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PennyShopScreen extends AbstractDaveShopScreen {

	public static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/penny_shop.png");
	
	public PennyShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected boolean canBuyNow() {
		return this.selectedGood != null && this.selectedTrade != null && PlayerUtil.getResource(this.minecraft.player, Resources.GEM_NUM) >= this.selectedTrade.money && this.menu.canClickBuyButton();
	}
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos, this.topPos, this.getBlitOffset(), 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 512);
		StringUtil.drawCenteredScaledString(stack, font, PlayerUtil.getResource(this.minecraft.player, Resources.GEM_NUM) + "", this.leftPos + 25 + 44, this.topPos + 9, Colors.WHITE, 1.4f);
		StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("gui.pvz.penny_shop.title").getString(), this.leftPos + 115 + 82, this.topPos + 6, Colors.BLACK, 1.4f);
	    stack.popPose();
	}

	@Override
	protected void renderTrade(MatrixStack stack, TradeType trade, int posX, int posY) {
		StringUtil.drawCenteredScaledString(stack, font, trade.money + "", posX + 31, posY + 4, Colors.BLUE, 1.2f);
		int offsetX = posX + 81;
		int offsetY = posY + 1;
		if(trade.good == DaveGoods.MONEY) {
			this.minecraft.getTextureManager().bind(TEXTURE);
			blit(stack, offsetX, offsetY, this.getBlitOffset(), 112, 195, 16, 16, 256, 512);
		} else {
			this.itemRenderer.renderGuiItem(TradeUtil.getGoodItemStack(trade.good), offsetX, offsetY);
		}
	}
	
	protected List<TradeType> getTradeTypes() {
		List<TradeType> list = new ArrayList<>();
		this.getAvailableGoods().forEach((good) -> {
			list.add(new TradeType(TradeUtil.getGoodCost(good), good));
		});
		return list;
	}

	@Override
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}
	
	@Override
	protected int getShopID() {
		return GuiHandler.PENNY_SHOP;
	}

}
