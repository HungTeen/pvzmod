package com.hungteen.pvz.gui.container.shop;

import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;

public class DaveShopContainer extends AbstractDaveShopContainer {
	
	public DaveShopContainer(int id, PlayerEntity player) {
		super(ContainerRegister.DAVE_SHOP.get(), id, player);
	}
	
	public void buyGood(DaveGoods good) {
		if(good == DaveGoods.MONEY) {
			PlayerUtil.addPlayerStats(player, Resources.GEM_NUM, - TradeUtil.getGoodCost(good));
			PlayerUtil.addPlayerStats(player, Resources.MONEY, 1000);
		} else {
			PlayerUtil.addPlayerStats(player, Resources.GEM_NUM, - TradeUtil.getGoodCost(good));
			this.output.setInventorySlotContents(0, TradeUtil.getGoodItemStack(good));
		}
//		this.player.world.playSound(null, this.player.getPosition(), SoundRegister.DAVE_BUY.get(), SoundCategory.AMBIENT, 1f, 1f);
	}
	
}
