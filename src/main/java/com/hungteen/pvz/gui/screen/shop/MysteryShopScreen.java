package com.hungteen.pvz.gui.screen.shop;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.gui.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.gui.container.shop.MysteryShopContainer;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
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
		this.buyButton = this.addButton(new Button(this.guiLeft +206, this.guiTop + 85, 18, 18, new TranslationTextComponent("gui.pvz.dave_shop.buy").getFormattedText(), (button) -> {
			if(this.buyButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(this.getShopID(), this.selectedGood.type, this.selectedGood.ordinal()));
			}
		}));
	}
	
	@Override
	protected boolean canBuyNow() {
		return this.selectedGood != null && this.selectedTrade != null && ClientPlayerResources.getPlayerStats(Resources.GEM_NUM) >= this.selectedTrade.money && this.container.canClickBuyButton();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(PennyShopScreen.TEXTURE);
		blit(this.guiLeft, this.guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.xSize, this.ySize, 256, 512);
		StringUtil.drawCenteredScaledString(font, ClientPlayerResources.getPlayerStats(Resources.GEM_NUM) + "", this.guiLeft + 25 + 44, this.guiTop + 9, Colors.WHITE, 1.4f);
		StringUtil.drawCenteredScaledString(font, new TranslationTextComponent("gui.pvz.mystery_shop.title").getFormattedText(), this.guiLeft + 115 + 82, this.guiTop + 6, Colors.BLACK, 1.4f);
	}

	@Override
	protected void renderTrade(TradeType trade, int posX, int posY) {
		StringUtil.drawCenteredScaledString(font, trade.money + "", posX + 31, posY + 4, Colors.BLUE, 1.2f);
		int offsetX = posX + 81;
		int offsetY = posY + 1;
		this.itemRenderer.renderItemIntoGUI(TradeUtil.getGoodItemStack(trade.good), offsetX, offsetY);
	}
	
	@Override
	protected void renderDetails() {
		if(this.selectedGood.toString().toLowerCase().startsWith("ENJOY_CARD")) {
			Plants plant = Plants.values()[this.selectedGood.type];
			StringUtil.drawCenteredScaledString(font, new TranslationTextComponent("item.pvz." + plant.toString().toLowerCase() + "_enjoy_card").getFormattedText(), this.guiLeft + 117 + 80, this.guiTop + 28 + 20, Colors.BLACK, 1.5f);
		} else {
			StringUtil.drawCenteredScaledString(font, new TranslationTextComponent("gui.pvz.dave_shop." + this.selectedGood.toString().toLowerCase()).getFormattedText(), this.guiLeft + 117 + 80, this.guiTop + 28 + 20, Colors.BLACK, 1.5f);
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
	protected List<String> getToolTips(TradeType type) {
		DaveGoods good = type.good;
		int num = 1;
		List<String> list = new ArrayList<>();
		for(int i = 1; i <= num; ++ i) {
			TranslationTextComponent text = new TranslationTextComponent("gui.pvz.dave_shop." + good.toString().toLowerCase() + i);
			if(good.toString().toLowerCase().startsWith("ENJOY_CARD")) {
				text = new TranslationTextComponent("gui.pvz.dave_shop.enjoy_card");
			}
			list.add(text.getFormattedText());
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
	protected int getShopID() {
		return GuiHandler.MYSTERY_SHOP;
	}

}
