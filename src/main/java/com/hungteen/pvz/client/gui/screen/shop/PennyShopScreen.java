package com.hungteen.pvz.client.gui.screen.shop;

import com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.PlayerInventory;

public class PennyShopScreen extends AbstractDaveShopScreen {

	public PennyShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv, Component titleIn) {
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
	protected Component getShopTitle() {
		return new TranslatableComponent("gui.pvz.penny_shop.title");
	}

}
