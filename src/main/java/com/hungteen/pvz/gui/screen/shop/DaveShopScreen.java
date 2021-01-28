package com.hungteen.pvz.gui.screen.shop;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.gui.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DaveShopScreen extends AbstractDaveShopScreen {

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/dave_shop.png");
	
	public DaveShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
//        blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize, 512, 256);
		blit(this.guiLeft, this.guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.xSize, this.ySize, 256, 512);
		StringUtil.drawCenteredScaledString(font, ClientPlayerResources.getPlayerStats(Resources.MONEY)+"", this.guiLeft + 25 + 44, this.guiTop + 9, Colors.WHITE, 1.4f);
		StringUtil.drawCenteredScaledString(font, new TranslationTextComponent("gui.pvz.dave_shop.title").getFormattedText(), this.guiLeft + 115 + 82, this.guiTop + 6, Colors.BLACK, 1.4f);
	}

	@Override
	protected void renderTrade(TradeType trade, int posX, int posY) {
		StringUtil.drawCenteredScaledString(font, trade.money + "", posX + 31, posY + 4, Colors.ORANGE, 1.2f);
		int offsetX = posX + 81;
		int offsetY = posY + 1;
		if(trade.good == DaveGoods.ENERGY) {
			this.minecraft.getTextureManager().bindTexture(TEXTURE);
			blit(offsetX, offsetY, this.getBlitOffset(), 112, 195, 16, 16, 256, 512);
		} else {
			this.itemRenderer.renderItemIntoGUI(TradeUtil.getGoodItemStack(trade.good), offsetX, offsetY);
		}
	}
	
	protected List<TradeType> getTradeTypes() {
		List<TradeType> list = new ArrayList<>();
		this.getAvailableGoods().forEach((good) -> {
			if(good == DaveGoods.ENERGY) {
				int num = ClientPlayerResources.getPlayerStats(Resources.MAX_ENERGY_NUM);
				if(num < 5) {
				    int cost = TradeUtil.getEnergyCost(num);
				    list.add(new TradeType(cost, good));
				}
			}else { 
				list.add(new TradeType(TradeUtil.getGoodCost(good), good));
			}
		});
		return list;
	}

	@Override
	protected int getShopID() {
		return GuiHandler.DAVE_SHOP;
	}

}
