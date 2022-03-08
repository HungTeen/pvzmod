package com.hungteen.pvz.client.gui.screen.shop;

import com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunShopScreen extends AbstractDaveShopScreen {

	public SunShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected int getCurrentMoney() {
		return PlayerUtil.getResource(this.minecraft.player, Resources.SUN_NUM);
	}

	@Override
	protected Pair<Integer, Integer> getMoneyBarPos() {
		return Pair.of(285, 22);
	}

	@Override
	protected Component getShopTitle() {
		return new TranslatableComponent("gui.pvz.sun_shop.title");
	}


}
