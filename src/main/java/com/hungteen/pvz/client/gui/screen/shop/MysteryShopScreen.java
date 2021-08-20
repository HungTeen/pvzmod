package com.hungteen.pvz.client.gui.screen.shop;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.client.cache.ClientPlayerResources;
import com.hungteen.pvz.client.gui.GuiHandler;
import com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import com.hungteen.pvz.common.network.ClickButtonPacket;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MysteryShopScreen extends AbstractDaveShopScreen {

	MysteryShopContainer mystery;
	
	public MysteryShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		if(screenContainer instanceof MysteryShopContainer) {
			this.mystery = (MysteryShopContainer) screenContainer;
		}
	}

	@Override
	protected void init() {
		super.init();
		this.buyButton = this.addButton(new Button(this.leftPos + 206, this.topPos + 85, 18, 18, new TranslationTextComponent("gui.pvz.dave_shop.buy"), (button) -> {
			if(this.buyButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(this.getShopID(), this.selectedGood.type, this.selectedGood.ordinal()));
			}
		}));
	}
	
	@Override
	protected boolean canBuyNow() {
		return this.selectedGood != null && this.selectedTrade != null && ClientPlayerResources.getPlayerStats(Resources.GEM_NUM) >= this.selectedTrade.money && this.menu.canClickBuyButton();
	}
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
		this.minecraft.getTextureManager().bind(PennyShopScreen.TEXTURE);
		blit(stack, this.leftPos, this.topPos, this.getBlitOffset(), 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 512);
		StringUtil.drawCenteredScaledString(stack, font, ClientPlayerResources.getPlayerStats(Resources.GEM_NUM) + "", this.leftPos + 25 + 44, this.topPos + 9, Colors.WHITE, 1.4f);
		StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("gui.pvz.mystery_shop.title").getString(), this.leftPos + 115 + 82, this.topPos + 6, Colors.BLACK, 1.4f);
		StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("gui.pvz.dave_shop.time_left").append(":" + ClientPlayerResources.updateGoodTick).getString(), this.leftPos + 115 + 130, this.topPos + 20, Colors.BLACK, 0.8f);
	    stack.popPose();
	}

	@Override
	protected void renderTrade(MatrixStack stack, TradeType trade, int posX, int posY) {
		StringUtil.drawCenteredScaledString(stack, font, trade.money + "", posX + 31, posY + 4, Colors.BLUE, 1.2f);
		int offsetX = posX + 81;
		int offsetY = posY + 1;
		this.itemRenderer.renderGuiItem(TradeUtil.getGoodItemStack(trade.good), offsetX, offsetY);
	}
	
	@Override
	protected void renderDetails(MatrixStack stack) {
		if(this.selectedGood.toString().startsWith("ENJOY_CARD")) {
//			Plants plant = Plants.values()[this.selectedGood.type];
//			StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("item.pvz." + plant.toString().toLowerCase() + "_enjoy_card").getString(), this.leftPos + 117 + 80, this.topPos + 28 + 20, Colors.BLACK, 1.5f);
		} else {
			StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("gui.pvz.dave_shop." + this.selectedGood.toString().toLowerCase()).getString(), this.leftPos + 117 + 80, this.topPos + 28 + 20, Colors.BLACK, 1.5f);
		}
		
	}
	
	@Override
	public List<DaveGoods> getAvailableGoods() {
		List<DaveGoods> list = new ArrayList<>();
		for(int i = 0; i < MysteryShopContainer.MAX_MYSTERY_GOOD; ++ i) {
			if(ClientPlayerResources.mysteryGoods[i] != -1) {
				DaveGoods good = DaveGoods.valueOf(DaveGoods.class, "ENJOY_CARD_" + i);
				list.add(good.setType(ClientPlayerResources.mysteryGoods[i]));
			}
		}
		return list;
	}
	
	@Override
	protected List<ITextComponent> getToolTips(TradeType type) {
		DaveGoods good = type.good;
		int num = 1;
		List<ITextComponent> list = new ArrayList<>();
		for(int i = 1; i <= num; ++ i) {
			TranslationTextComponent text = new TranslationTextComponent("gui.pvz.dave_shop." + good.toString().toLowerCase() + i);
			if(good.toString().startsWith("ENJOY_CARD")) {
				text = new TranslationTextComponent("gui.pvz.dave_shop.enjoy_card");
			}
			list.add(text);
		}
		return list;
	}
	
	@Override
	protected List<TradeType> getTradeTypes() {
		List<TradeType> list = new ArrayList<>();
		this.getAvailableGoods().forEach((good) -> {
			list.add(new TradeType(TradeUtil.getGoodCost(good), good));
		});
		return list;
	}

	@Override
	protected ResourceLocation getTexture() {
		return PennyShopScreen.TEXTURE;
	}
	
	@Override
	protected int getShopID() {
		return GuiHandler.MYSTERY_SHOP;
	}

}
