package com.hungteen.pvz.client.gui.screen.shop;

import com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DaveShopScreen extends AbstractDaveShopScreen {

	public DaveShopScreen(AbstractDaveShopContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected int getCurrentMoney() {
		return PlayerUtil.getResource(this.minecraft.player, Resources.MONEY);
	}

	@Override
	protected Pair<Integer, Integer> getMoneyBarPos() {
		return Pair.of(285, 0);
	}

	@Override
	protected ITextComponent getShopTitle() {
		return new TranslationTextComponent("gui.pvz.dave_shop.title");
	}
}
