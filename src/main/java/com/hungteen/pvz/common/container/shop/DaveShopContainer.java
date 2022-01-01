package com.hungteen.pvz.common.container.shop;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;

public class DaveShopContainer extends AbstractDaveShopContainer {
	
	public DaveShopContainer(int id, PlayerEntity player) {
		super(ContainerRegister.DAVE_SHOP.get(), id, player);
	}
	
	public void buyGood(DaveGoods good) {
		if(good == DaveGoods.ENERGY) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				PlayerDataManager stats = l.getPlayerData();
				stats.addResource(Resources.MONEY, - TradeUtil.getEnergyCost(stats.getResource(Resources.MAX_ENERGY_NUM)));
				stats.addResource(Resources.MAX_ENERGY_NUM, 1);
			});
		} else {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				PlayerDataManager stats = l.getPlayerData();
				stats.addResource(Resources.MONEY, - TradeUtil.getGoodCost(good));
				this.output.setItem(0, TradeUtil.getGoodItemStack(good));
			});
		}
		this.player.level.playSound(null, this.player, SoundRegister.DAVE_BUY.get(), SoundCategory.AMBIENT, 1f, 1f);
	}
	
}
