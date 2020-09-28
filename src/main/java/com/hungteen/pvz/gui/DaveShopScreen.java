package com.hungteen.pvz.gui;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.capabilities.player.ClientPlayerResources;
import com.hungteen.pvz.gui.container.DaveShopContainer;
import com.hungteen.pvz.gui.widget.PVZButton;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.TradeUtil;
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
	private Goods selectedGood;
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
		this.buyButton = this.addButton(new Button(this.guiLeft +206, this.guiTop + 85, 18, 18, new TranslationTextComponent("gui.pvz.dave_shop").getFormattedText(), (button) -> {
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
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	private void renderTrade(TradeType trade,int posX, int posY) {
		StringUtil.drawCenteredScaledString(font, trade.money + "", posX + 31, posY + 4, Colors.ORANGE, 1.2f);
		int offsetX = posX + 81;
		int offsetY = posY + 1;
		if(trade.good == Goods.ENERGY) {
			this.minecraft.getTextureManager().bindTexture(TEXTURE);
			blit(offsetX, offsetY, this.getBlitOffset(), 112, 195, 16, 16, 256, 512);
		}else if(trade.good == Goods.SLOT) {
			this.minecraft.getTextureManager().bindTexture(TEXTURE);
			blit(offsetX, offsetY, this.getBlitOffset(), 128, 195, 16, 16, 256, 512);
		}else if(trade.good == Goods.ALMANAC) {
			this.itemRenderer.renderItemIntoGUI(new ItemStack(ItemRegister.ALMANAC.get()), offsetX, offsetY);
		}
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
	
	private List<TradeType> getTradeTypes() {
		List<TradeType> list = new ArrayList<>();
		for(Goods good:Goods.values()) {
			if(good == Goods.ENERGY) {
				int num = ClientPlayerResources.getPlayerStats(Resources.MAX_ENERGY_NUM);
				if(num == PlayerUtil.MAX_ENERGY_NUM) {
					continue;
				}
				int cost = TradeUtil.getEnergyCost(num);
				list.add(new TradeType(cost, good));
			}else if(good == Goods.SLOT) {
				int num = ClientPlayerResources.getPlayerStats(Resources.SLOT_NUM);
				if(num == PlayerUtil.MAX_SLOT_NUM) {
					continue;
				}
				int cost = TradeUtil.getSlotCost(num);
				list.add(new TradeType(cost, good));
			}else if(good == Goods.ALMANAC) {
				list.add(new TradeType(TradeUtil.ALMANAC_COST, good));
			}
		}
		return list;
	}

	@OnlyIn(Dist.CLIENT)
	public static class TradeButton extends Button {
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
//			if (this.isHovered && MerchantScreen.this.container.getOffers().size() > this.id
//					+ MerchantScreen.this.field_214139_n) {
//				if (mouseX < this.x + 20) {
//					ItemStack itemstack = MerchantScreen.this.container.getOffers()
//							.get(this.id + MerchantScreen.this.field_214139_n).func_222205_b();
//					MerchantScreen.this.renderTooltip(itemstack, mouseX, mouseY);
//				} else if (mouseX < this.x + 50 && mouseX > this.x + 30) {
//					ItemStack itemstack2 = MerchantScreen.this.container.getOffers()
//							.get(this.id + MerchantScreen.this.field_214139_n).getBuyingStackSecond();
//					if (!itemstack2.isEmpty()) {
//						MerchantScreen.this.renderTooltip(itemstack2, mouseX, mouseY);
//					}
//				} else if (mouseX > this.x + 65) {
//					ItemStack itemstack1 = MerchantScreen.this.container.getOffers()
//							.get(this.id + MerchantScreen.this.field_214139_n).getSellingStack();
//					MerchantScreen.this.renderTooltip(itemstack1, mouseX, mouseY);
//				}
//			}
		}
	}
	
//	@OnlyIn(Dist.CLIENT)
//	public static class BuyButton extends PVZButton{
//
//		public BuyButton(ResourceLocation location, int x, int y, int width, int height, String text,
//				IPressable onPress) {
//			super(location, x, y, width, height, text, false, onPress);
//		}
//		
//	}

	public static class TradeType{
		public Goods good;
		public int money;
		public int exp;
		
		public TradeType(int money, Goods good) {
			this.good = good;
			this.money = money;
		}
	}
	
	public enum Goods{
		ENERGY,
		SLOT,
		ALMANAC
	}
}
