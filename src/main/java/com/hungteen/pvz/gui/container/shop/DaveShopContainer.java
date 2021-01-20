package com.hungteen.pvz.gui.container.shop;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.register.SoundRegister;
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
		if(good == DaveGoods.MONEY) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				PlayerDataManager.PlayerStats stats = l.getPlayerData().getPlayerStats();
				stats.addPlayerStats(Resources.MONEY, - TradeUtil.getEnergyCost(stats.getPlayerStats(Resources.MAX_ENERGY_NUM)));
				stats.addPlayerStats(Resources.MAX_ENERGY_NUM, 1);
			});
		} else {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				PlayerDataManager.PlayerStats stats = l.getPlayerData().getPlayerStats();
				stats.addPlayerStats(Resources.MONEY, - TradeUtil.getGoodCost(good));
				this.output.setInventorySlotContents(0, TradeUtil.getGoodItemStack(good));
			});
		}
		this.player.world.playSound(null, this.player.getPosition(), SoundRegister.DAVE_BUY.get(), SoundCategory.AMBIENT, 1f, 1f);
	}
	
}
