package com.hungteen.pvz.client.gui.screen.shop;

import com.hungteen.pvz.client.gui.GuiHandler;
import com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MysteryShopScreen extends PennyShopScreen {

	MysteryShopContainer mystery;
	
	public MysteryShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		if(screenContainer instanceof MysteryShopContainer) {
			this.mystery = (MysteryShopContainer) screenContainer;
		}
	}
//
////	@Override
////	protected void renderDetails(MatrixStack stack) {
////		if(this.selectedGood.toString().startsWith("ENJOY_CARD")) {
//////			Plants plant = Plants.values()[this.selectedGood.type];
//////			StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("item.pvz." + plant.toString().toLowerCase() + "_enjoy_card").getString(), this.leftPos + 117 + 80, this.topPos + 28 + 20, Colors.BLACK, 1.5f);
////		} else {
////			StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("gui.pvz.dave_shop." + this.selectedGood.toString().toLowerCase()).getString(), this.leftPos + 117 + 80, this.topPos + 28 + 20, Colors.BLACK, 1.5f);
////		}
////
////	}
//
////	@Override
////	public List<DaveGoods> getAvailableGoods() {
////		List<DaveGoods> list = new ArrayList<>();
////		for(int i = 0; i < MysteryShopContainer.MAX_MYSTERY_GOOD; ++ i) {
////			if(ClientPlayerResources.mysteryGoods[i] != -1) {
////				DaveGoods good = DaveGoods.valueOf(DaveGoods.class, "ENJOY_CARD_" + i);
////				list.add(good.setType(ClientPlayerResources.mysteryGoods[i]));
////			}
////		}
////		return list;
////	}
//
//	@Override
//	protected List<ITextComponent> getToolTips(TradeType type) {
//		DaveGoods good = type.good;
//		int num = 1;
//		List<ITextComponent> list = new ArrayList<>();
//		for(int i = 1; i <= num; ++ i) {
//			TranslationTextComponent text = new TranslationTextComponent("gui.pvz.dave_shop." + good.toString().toLowerCase() + i);
//			if(good.toString().startsWith("ENJOY_CARD")) {
//				text = new TranslationTextComponent("gui.pvz.dave_shop.enjoy_card");
//			}
//			list.add(text);
//		}
//		return list;
//	}
//
//	@Override
//	protected List<TradeType> getTradeTypes() {
//		List<TradeType> list = new ArrayList<>();
//		this.getAvailableGoods().forEach((good) -> {
//			list.add(new TradeType(TradeUtil.getGoodCost(good), good));
//		});
//		return list;
//	}
	
	@Override
	protected int getShopID() {
		return GuiHandler.MYSTERY_SHOP;
	}

}
