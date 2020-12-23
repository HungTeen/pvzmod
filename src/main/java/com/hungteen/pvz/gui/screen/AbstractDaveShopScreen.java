package com.hungteen.pvz.gui.screen;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.hungteen.pvz.gui.container.AbstractDaveShopContainer;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractDaveShopScreen extends ContainerScreen<AbstractDaveShopContainer> {

	public static final int TRADE_NUM_PER_PAGE = 8;
	protected final TradeButton[] trades = new TradeButton[TRADE_NUM_PER_PAGE];
	protected List<TradeType> tradeTypes;
	protected Button buyButton;
	private int downHeight;
	protected DaveGoods selectedGood;
	protected TradeType selectedTrade;
	
	public AbstractDaveShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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
				}
			}));
		}
		this.buyButton = this.addButton(new Button(this.guiLeft +206, this.guiTop + 85, 18, 18, new TranslationTextComponent("gui.pvz.dave_shop.buy").getFormattedText(), (button) -> {
			if(this.buyButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(this.getShopID(), this.selectedGood.ordinal()));
			}
		}));
		this.buyButton.visible = false;
		this.tradeTypes = this.getTradeTypes();
		this.selectedGood = null;
		this.selectedTrade = null;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		super.render(mouseX, mouseY, partialTicks);
		this.tradeTypes = this.getTradeTypes();
		int h = 0;
		int posX = this.guiLeft + 6;
		int posY = this.guiTop + 28;
		for(TradeType type : tradeTypes) {
			if(tradeTypes.size() > TRADE_NUM_PER_PAGE && (h < downHeight || h >= downHeight + TRADE_NUM_PER_PAGE)) {
				++ h;
			}else {
				this.renderTrade(type, posX, posY);
				posY += 20;
			}
		}
		for(TradeButton trade : this.trades) {
			if(trade.isHovered()) {
				trade.renderToolTip(mouseX, mouseY);
			}
			trade.visible = trade.id < tradeTypes.size();
		}
		this.selectedTrade = null;
		for(TradeType trade : this.tradeTypes) {
			if(this.selectedGood == trade.good) {
				this.selectedTrade = trade;
				break;
			}
		}
		if(this.selectedTrade == null) {
			this.selectedGood = null;
		}
		this.buyButton.visible = this.canBuyNow();
		if(this.selectedGood != null && this.selectedTrade != null) {
			this.renderDetails();
		}
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	private void renderDetails() {
		StringUtil.drawCenteredScaledString(font, new TranslationTextComponent("gui.pvz.dave_shop." + this.selectedGood.toString().toLowerCase()).getFormattedText(), this.guiLeft + 117 + 80, this.guiTop + 28 + 20, Colors.BLACK, 1.5f);
	}
	
	protected boolean canBuyNow() {
		return this.selectedGood != null && this.selectedTrade != null && ClientPlayerResources.getPlayerStats(Resources.MONEY) >= this.selectedTrade.money && this.container.canClickBuyButton();
	}
	
	protected abstract void renderTrade(TradeType trade, int posX, int posY);
	
    protected abstract List<TradeType> getTradeTypes();
	
	protected abstract int getShopID();
	
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
		List<String> list = new ArrayList<>();
		for(int i = 1; i <= num; ++ i) {
			TranslationTextComponent text = new TranslationTextComponent("gui.pvz.dave_shop." + good.toString().toLowerCase() + i);
			list.add(text.getFormattedText());
		}
		return list;
	}
	
	public List<DaveGoods> getAvailableGoods(){
		List<DaveGoods> list = new ArrayList<>();
		for(DaveGoods good : DaveGoods.values()) {
			if(good.shopId == this.getShopID()) list.add(good);
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
			AbstractDaveShopScreen.this.renderTooltip(list, mouseX, mouseY);
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