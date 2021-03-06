package com.hungteen.pvz.gui.container.shop;

import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;

public class PennyShopContainer extends AbstractDaveShopContainer {

	public PennyShopContainer(int id, PlayerEntity player) {
		super(ContainerRegister.PENNY_SHOP.get(), id, player);
	}

	public void buyGood(DaveGoods good) {
		if(good == DaveGoods.MONEY) {
			PlayerUtil.addPlayerStats(player, Resources.GEM_NUM, - TradeUtil.getGoodCost(good));
			PlayerUtil.addPlayerStats(player, Resources.MONEY, 1000);
		} else {
			PlayerUtil.addPlayerStats(player, Resources.GEM_NUM, - TradeUtil.getGoodCost(good));
			this.output.setItem(0, TradeUtil.getGoodItemStack(good));
		}
		this.player.level.playSound(null, this.player, SoundRegister.DAVE_BUY.get(), SoundCategory.AMBIENT, 1f, 1f);
	}
	
}
