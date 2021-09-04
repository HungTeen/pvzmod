package com.hungteen.pvz.client.gui.screen.shop;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.client.cache.ClientPlayerResources;
import com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toserver.ClickButtonPacket;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
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

	public AbstractDaveShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv,
			ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 285;
		this.imageHeight = 195;
	}

	@Override
	protected void init() {
		super.init();
		for (int i = 0; i < TRADE_NUM_PER_PAGE; i++) {
			this.trades[i] = this.addButton(new TradeButton(this.leftPos + 5, this.topPos + 27 + 20 * i, i,
					(button) -> {
						// select the trade
						if (button instanceof TradeButton) {
							int id = ((TradeButton) button).getId() + this.downHeight;
							this.selectedGood = this.getTradeTypes().get(id).good;
						}
					}));
		}
		this.buyButton = this.addButton(new Button(this.leftPos + 206, this.topPos + 85, 18, 18,
				new TranslationTextComponent("gui.pvz.dave_shop.buy"), (button) -> {
					if (this.buyButton.visible) {
						PVZPacketHandler.CHANNEL
								.sendToServer(new ClickButtonPacket(this.getShopID(), 0, this.selectedGood.ordinal()));
					}
				}));
		this.buyButton.visible = false;
		this.tradeTypes = this.getTradeTypes();
		this.selectedGood = null;
		this.selectedTrade = null;
	}
	
	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		this.tradeTypes = this.getTradeTypes();
		int h = 0;
		int posX = this.leftPos + 6;
		int posY = this.topPos + 28;
		this.renderScroll(stack, this.tradeTypes);
		for (TradeType type : tradeTypes) {
			if (h >= downHeight && h < downHeight + TRADE_NUM_PER_PAGE) {
				this.renderTrade(stack, type, posX, posY);
				posY += 20;
			}
			++h;
		}
		for (TradeButton trade : this.trades) {
			if (trade.isHovered()) {
				trade.renderToolTip(stack, mouseX, mouseY);
			}
			trade.visible = trade.id < tradeTypes.size();
		}
		this.selectedTrade = null;
		for (TradeType trade : this.tradeTypes) {
			if (this.selectedGood == trade.good) {
				this.selectedTrade = trade;
				break;
			}
		}
		if (this.selectedTrade == null) {
			this.selectedGood = null;
		}
		this.buyButton.visible = this.canBuyNow();
		if (this.selectedGood != null && this.selectedTrade != null) {
			this.renderDetails(stack);
		}
		this.renderTooltip(stack, mouseX, mouseY);
	}

	protected void renderDetails(MatrixStack stack) {
		StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("gui.pvz.dave_shop." + this.selectedGood.toString().toLowerCase()).getString(), this.leftPos + 117 + 80, this.topPos + 28 + 20, Colors.BLACK, 1.5f);
	}

	protected boolean canBuyNow() {
		return this.selectedGood != null && this.selectedTrade != null
				&& ClientPlayerResources.getPlayerStats(Resources.MONEY) >= this.selectedTrade.money
				&& this.menu.canClickBuyButton();
	}

	protected abstract void renderTrade(MatrixStack stack, TradeType trade, int posX, int posY);

	protected abstract List<TradeType> getTradeTypes();

	protected abstract int getShopID();
	
	protected abstract ResourceLocation getTexture();

	private void renderScroll(MatrixStack stack, List<TradeType> types) {
		int x = (this.width - this.imageWidth) / 2;
		int y = (this.height - this.imageHeight) / 2;
		int i = types.size() - TRADE_NUM_PER_PAGE + 1;
		stack.pushPose();
		this.minecraft.getTextureManager().bind(this.getTexture());
		if (i > 1) {
			int j = 159 - (27 + (i - 1) * 159 / i);
			int k = 1 + j / i + 159 / i;
			int len = 133;
			int i1 = Math.min(len, this.downHeight * k);
			if (this.downHeight == i - 1) {
				i1 = 133;
			}
			blit(stack, x + 106, y + 27 + i1, this.getBlitOffset(), 0.0F, 195.0F, 6, 27, 256, 512);
		} else {
			blit(stack, x + 106, y + 27, this.getBlitOffset(), 6.0F, 195.0F, 6, 27, 256, 512);
		}
		stack.popPose();
	}

	@Override
	public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_) {
		int size = this.getTradeTypes().size();
		if (size > TRADE_NUM_PER_PAGE) {
			int next = (int) ((double) this.downHeight - p_mouseScrolled_5_);
			this.downHeight = MathHelper.clamp(next, 0, size - TRADE_NUM_PER_PAGE);
		}
		return true;
	}

	protected List<ITextComponent> getToolTips(TradeType type) {
		DaveGoods good = type.good;
		int num = 1;
		List<ITextComponent> list = new ArrayList<>();
		for (int i = 1; i <= num; ++i) {
			TranslationTextComponent text = new TranslationTextComponent(
					"gui.pvz.dave_shop." + good.toString().toLowerCase() + i);
			list.add(text);
		}
		return list;
	}

	public List<DaveGoods> getAvailableGoods() {
		List<DaveGoods> list = new ArrayList<>();
		for (DaveGoods good : DaveGoods.values()) {
			if (good.shopId == this.getShopID())
				list.add(good);
		}
		return list;
	}

	@OnlyIn(Dist.CLIENT)
	public class TradeButton extends Button {

		final int id;

		public TradeButton(int x, int y, int id, Button.IPressable press) {
			super(x, y, 100, 20, StringUtil.EMPTY, press);
			this.id = id;
			this.visible = false;
		}

		public int getId() {
			return this.id;
		}

		public void renderToolTip(MatrixStack stack, int mouseX, int mouseY) {
			List<ITextComponent> list = getToolTips(tradeTypes.get(this.id + AbstractDaveShopScreen.this.downHeight));
			AbstractDaveShopScreen.this.renderComponentTooltip(stack, list, mouseX, mouseY);
		}
	}

	public static class TradeType {
		public DaveGoods good;
		public int money;
		public int exp;

		public TradeType(int money, DaveGoods good) {
			this.good = good;
			this.money = money;
		}
	}

}