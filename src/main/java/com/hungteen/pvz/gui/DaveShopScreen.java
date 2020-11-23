package com.hungteen.pvz.gui;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.hungteen.pvz.gui.container.DaveShopContainer;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DaveShopScreen extends ContainerScreen<DaveShopContainer> {

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/dave_shop.png");
	public static final int TRADE_NUM_PER_PAGE = 8;
	private final TradeButton[] trades = new TradeButton[TRADE_NUM_PER_PAGE];
	private List<TradeType> tradeTypes;
	private Button buyButton;
	private int downHeight;
	private DaveGoods selectedGood;
	private TradeType selectedTrade;
	
	public DaveShopScreen(DaveShopContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.xSize = 285;
		this.ySize = 195;
	}
	
	@Override
	protected void init() {
		super.init();
		for(int i = 0;i < TRADE_NUM_PER_PAGE;i ++) {
			this.trades[i] = this.addButton(new TradeButton(this.guiLeft + 5, this.guiTop + 27 + 20 * i, i, (button) -> {
				//select the trade
				if(button instanceof TradeButton) {
				    int id = ((TradeButton) button).getId() + this.downHeight;
				    this.selectedGood = this.getTradeTypes().get(id).good;
//				    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.DAVE_SHOP, this.selectedButton));
				}
			}));
		}
		this.buyButton = this.addButton(new Button(this.guiLeft +206, this.guiTop + 85, 18, 18, new TranslationTextComponent("gui.pvz.dave_shop.buy").getFormattedText(), (button) -> {
			if(this.buyButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.DAVE_SHOP, this.selectedGood.ordinal()));
			}
		}));
		this.buyButton.visible = false;
		this.tradeTypes = this.getTradeTypes();
		this.selectedGood = null;
		this.selectedTrade = null;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
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
	public void render(int mouseX, int mouseY, float partialTicks) {
		super.render(mouseX, mouseY, partialTicks);
		this.tradeTypes = this.getTradeTypes();
		int h=0;
		int posX = this.guiLeft + 6;
		int posY = this.guiTop + 28;
		for(TradeType type:tradeTypes) {
			if(tradeTypes.size() > TRADE_NUM_PER_PAGE && (h < downHeight || h >= downHeight + TRADE_NUM_PER_PAGE)) {
				h++;
			}else {
				this.renderTrade(type, posX, posY);
				posY += 20;
			}
		}
		for(TradeButton trade:this.trades) {
			if(trade.isHovered()) {
				trade.renderToolTip(mouseX, mouseY);
			}
			trade.visible = trade.id < tradeTypes.size();
		}
		this.selectedTrade = null;
		for(TradeType trade:this.tradeTypes) {
			if(this.selectedGood == trade.good) {
				this.selectedTrade = trade;
				break;
			}
		}
		if(this.selectedTrade == null) {
			this.selectedGood = null;
		}
		this.buyButton.visible = this.selectedGood != null && this.selectedTrade != null && ClientPlayerResources.getPlayerStats(Resources.MONEY) >= this.selectedTrade.money && this.container.canClickBuyButton();
		if(this.selectedGood != null && this.selectedTrade != null) {
			this.renderDetails();
		}
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	private void renderDetails() {
		StringUtil.drawCenteredScaledString(font, new TranslationTextComponent("gui.pvz.dave_shop." + this.selectedGood.toString().toLowerCase()).getFormattedText(), this.guiLeft + 117 + 80, this.guiTop + 28 + 20, Colors.BLACK, 1.5f);
	}
	
	private void renderTrade(TradeType trade, int posX, int posY) {
		StringUtil.drawCenteredScaledString(font, trade.money + "", posX + 31, posY + 4, Colors.ORANGE, 1.2f);
		int offsetX = posX + 81;
		int offsetY = posY + 1;
		if(trade.good == DaveGoods.ENERGY) {
			this.minecraft.getTextureManager().bindTexture(TEXTURE);
			blit(offsetX, offsetY, this.getBlitOffset(), 112, 195, 16, 16, 256, 512);
		} else {
			this.itemRenderer.renderItemIntoGUI(new ItemStack(TradeUtil.getGoodItem(trade.good)), offsetX, offsetY);
		}
//		else if(trade.good == Goods.ALMANAC) {
//			this.itemRenderer.renderItemIntoGUI(new ItemStack(ItemRegister.ALMANAC.get()), offsetX, offsetY);
//		}else if(trade.good == Goods.GATLING_PEA_CARD) {
//			this.itemRenderer.renderItemIntoGUI(new ItemStack(ItemRegister.GATLING_PEA_CARD.get()), offsetX, offsetY);
//		}else if(trade.good == Goods.TWIN_SUNFLOWER_CARD) {
//			this.itemRenderer.renderItemIntoGUI(new ItemStack(ItemRegister.TWIN_SUNFLOWER_CARD.get()), offsetX, offsetY);
//		}
	}
	
	@Override
	public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_) {
		int size = this.getTradeTypes().size();
		if(size > TRADE_NUM_PER_PAGE) {
			int next = (int) ((double)this.downHeight - p_mouseScrolled_5_);
			this.downHeight = MathHelper.clamp(next, 0, size - TRADE_NUM_PER_PAGE);
		}
		return true;
	}
	
	private List<String> getToolTips(TradeType type) {
		DaveGoods good = type.good;
		int num = 1;
//		if(good == DaveGoods.ENERGY || good == DaveGoods.ALMANAC) num = 1;
		List<String> list = new ArrayList<>();
		for(int i = 1; i <= num; ++ i) {
			TranslationTextComponent text = new TranslationTextComponent("gui.pvz.dave_shop." + good.toString().toLowerCase() + i);
			list.add(text.getFormattedText());
		}
		return list;
	}
	
	private List<TradeType> getTradeTypes() {
		List<TradeType> list = new ArrayList<>();
		for(DaveGoods good : DaveGoods.values()) {
			if(good == DaveGoods.ENERGY) {
				int num = ClientPlayerResources.getPlayerStats(Resources.MAX_ENERGY_NUM);
				if(num < PlayerUtil.MAX_ENERGY_NUM) {
				    int cost = TradeUtil.getEnergyCost(num);
				    list.add(new TradeType(cost, good));
				}
			}else { 
				list.add(new TradeType(TradeUtil.getGoodCost(good), good));
			}
		}
		return list;
	}

	@OnlyIn(Dist.CLIENT)
	public class TradeButton extends Button {
		final int id;

		public TradeButton(int x, int y, int id, Button.IPressable press) {
			super(x, y, 100, 20, "", press);
			this.id = id;
			this.visible = false;
		}

		public int getId() {
			return this.id;
		}
		
		public void renderToolTip(int mouseX, int mouseY) {
			List<String> list = getToolTips(tradeTypes.get(this.id));
			DaveShopScreen.this.renderTooltip(list, mouseX, mouseY);
		}
	}
	
	public static class TradeType{
		public DaveGoods good;
		public int money;
		public int exp;
		
		public TradeType(int money, DaveGoods good) {
			this.good = good;
			this.money = money;
		}
	}
	
}
