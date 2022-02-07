package com.hungteen.pvz.client.gui.screen.shop;

import com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PennyShopScreen extends AbstractDaveShopScreen {

	public PennyShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected int getCurrentMoney() {
		return PlayerUtil.getResource(this.minecraft.player, Resources.GEM_NUM);
	}

	@Override
	protected Pair<Integer, Integer> getMoneyBarPos() {
		return Pair.of(285, 44);
	}

	@Override
	protected ITextComponent getShopTitle() {
		return new TranslationTextComponent("gui.pvz.penny_shop.title");
	}

}
