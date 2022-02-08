package com.hungteen.pvz.client.gui.screen.shop;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.client.gui.GuiHandler;
import com.hungteen.pvz.client.gui.screen.PVZContainerScreen;
import com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toserver.ClickButtonPacket;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractDaveShopScreen extends PVZContainerScreen<AbstractDaveShopContainer> {

    private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/shop.png");
    public static final int TRADE_NUM_PER_PAGE = 8;
    protected final TradeButton[] trades = new TradeButton[TRADE_NUM_PER_PAGE];
    protected Button buyButton;
    private int downHeight;
    protected int selectedPos;

    public AbstractDaveShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv,
                                  ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.imageWidth = 285;
        this.imageHeight = 195;
    }

    @Override
    protected void init() {
        super.init();
        for (int i = 0; i < TRADE_NUM_PER_PAGE; ++ i) {
            this.trades[i] = this.addButton(new TradeButton(this.leftPos + 5, this.topPos + 27 + 20 * i, i, (button) -> {
                // select the trade.
                if (button instanceof TradeButton) {
                    this.selectedPos = ((TradeButton) button).getId() + this.downHeight;
                }
            }));
        }
        this.buyButton = this.addButton(new Button(this.leftPos + 206, this.topPos + 85, 18, 18,
                new TranslationTextComponent("gui.pvz.dave_shop.buy"), (button) -> {
            if (this.buyButton.visible) {
                PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(this.getShopID(), 0, this.selectedPos));
            }
        }));
        this.buyButton.visible = false;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        final List<AbstractDaveEntity.GoodType> goods = this.getAvailableGoods();

        //avoid crash.
        this.selectedPos = MathHelper.clamp(this.selectedPos, 0, goods.size() - 1);
        //render scroll.
        this.renderScroll(stack, goods);
        //render trades.
        int h = 0;
        int posX = this.leftPos + 6;
        int posY = this.topPos + 28;
        for (AbstractDaveEntity.GoodType type : goods) {
            if (h >= downHeight && h < downHeight + TRADE_NUM_PER_PAGE) {
                this.renderTrade(stack, type, posX, posY);
                posY += 20;
            }
            ++h;
        }
        //update buy button.
        if(goods.isEmpty()){
            this.buyButton.visible = false;
        } else{
        	final AbstractDaveEntity.GoodType goodType = goods.get(this.selectedPos);
            this.buyButton.visible = (goodType != null && this.menu.canClickBuyButton() && this.getCurrentMoney() >= goodType.getGoodPrice());
            //update good details.
            this.renderDetails(stack, goodType);
        }
        //update refresh time.
        this.menu.getLeftRefreshTime().ifPresent(time -> {
            StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("gui.pvz.shop.left_time", time).getString(), this.leftPos + 117 + 120, this.topPos + 28, time > 12000 ? Colors.GREEN : time > 1200 ? Colors.YELLOW : Colors.RED, 0.8f);
        });
        //update trade buttons.
        for (TradeButton trade : this.trades) {
            if (trade.isHovered()) {
                trade.renderToolTip(stack, goods, mouseX, mouseY);
            }
            trade.visible = this.downHeight + trade.id < goods.size();
        }
        //tooltips.
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        stack.pushPose();
        this.minecraft.getTextureManager().bind(TEXTURE);
        blit(stack, this.leftPos, this.topPos, this.getBlitOffset(), 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 512);

        final int width = 112;
        final int height = 22;
        final Pair<Integer, Integer> pair = this.getMoneyBarPos();
        blit(stack, this.leftPos + 3, this.topPos + 3, this.getBlitOffset(), pair.getFirst(), pair.getSecond(), width, height, 256, 512);

        StringUtil.drawCenteredScaledString(stack, font, this.getCurrentMoney() + "", this.leftPos + 25 + 44, this.topPos + 9, Colors.WHITE, 1.4f);
        StringUtil.drawCenteredScaledString(stack, font, getShopTitle().getString(), this.leftPos + 115 + 82, this.topPos + 6, Colors.BLACK, 1.4f);
        stack.popPose();
    }

    protected void renderDetails(MatrixStack stack, AbstractDaveEntity.GoodType goodType) {
        if (goodType != null) {
            StringUtil.drawCenteredScaledString(stack, font, goodType.getGoodDescription().getString(), this.leftPos + 117 + 80, this.topPos + 28 + 20, Colors.BLACK, 1.5f);
        }
    }

    @Nullable
    public AbstractDaveEntity.GoodType getSelectedGood(){
        final List<AbstractDaveEntity.GoodType> goods = this.getAvailableGoods();
        return goods.size() > 0 ? goods.get(this.selectedPos) : null;
    }

    protected void renderTrade(MatrixStack stack, AbstractDaveEntity.GoodType trade, int posX, int posY) {
        StringUtil.drawCenteredScaledString(stack, font, trade.getGoodPrice() + "", posX + 31, posY + 4, Colors.WHITE, 1.2f);
        final int offsetX = posX + 81;
        final int offsetY = posY + 1;
        if(trade.getType().isEnergy()) {
            this.minecraft.getTextureManager().bind(TEXTURE);
            blit(stack, offsetX, offsetY, this.getBlitOffset(), 112, 195, 16, 16, 256, 512);
        } else if(trade.getType().isSlot()){
            this.minecraft.getTextureManager().bind(TEXTURE);
            blit(stack, offsetX, offsetY, this.getBlitOffset(), 128, 195, 16, 16, 256, 512);
        } else if(trade.getType().isMoney()){
            this.minecraft.getTextureManager().bind(TEXTURE);
            blit(stack, offsetX, offsetY, this.getBlitOffset(), 144, 195, 16, 16, 256, 512);
        } else {
            this.itemRenderer.renderGuiItem(trade.getGood(), offsetX, offsetY);
        }
    }

    protected int getShopID(){
        return GuiHandler.SHOP;
    }

    protected abstract int getCurrentMoney();

    protected abstract Pair<Integer, Integer> getMoneyBarPos();

    protected abstract ITextComponent getShopTitle();

    private void renderScroll(MatrixStack stack, List<AbstractDaveEntity.GoodType> types) {
        final int x = (this.width - this.imageWidth) / 2;
        final int y = (this.height - this.imageHeight) / 2;
        int i = types.size() - TRADE_NUM_PER_PAGE + 1;
        stack.pushPose();
        this.minecraft.getTextureManager().bind(TEXTURE);
        if (i > 1) {
            final int j = 159 - (27 + (i - 1) * 159 / i);
            final int k = 1 + j / i + 159 / i;
            final int len = 133;
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
        final int size = this.getAvailableGoods().size();
        if (size > TRADE_NUM_PER_PAGE) {
            int next = (int) ((double) this.downHeight - p_mouseScrolled_5_);
            this.downHeight = MathHelper.clamp(next, 0, size - TRADE_NUM_PER_PAGE);
        }
        return true;
    }

    public List<AbstractDaveEntity.GoodType> getAvailableGoods() {
        return this.menu.getValidGoods(ClientProxy.MC.player);
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

        public void renderToolTip(MatrixStack stack, List<AbstractDaveEntity.GoodType> goods, int mouseX, int mouseY) {
        	final int pos = downHeight + this.getId();
        	if(pos >= 0 && pos < goods.size()) {
        		final AbstractDaveEntity.GoodType goodType = goods.get(pos);
        		if(goodType != null) {
                    if (goodType.getType().isItem()) {
                        AbstractDaveShopScreen.this.renderComponentTooltip(stack, AbstractDaveShopScreen.this.getTooltipFromItem(goodType.getGood()), mouseX, mouseY);
                    } else {
                        AbstractDaveShopScreen.this.renderComponentTooltip(stack, Arrays.asList(goodType.getGoodDescription()), mouseX, mouseY);
                    }
                }
        	}
        }
    }

}