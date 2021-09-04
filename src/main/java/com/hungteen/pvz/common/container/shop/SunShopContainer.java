package com.hungteen.pvz.common.container.shop;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;

public class SunShopContainer extends AbstractDaveShopContainer {
	
	public SunShopContainer(int id, PlayerEntity player) {
		super(ContainerRegister.SUN_SHOP.get(), id, player);
	}
	
	public void buyGood(DaveGoods good) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			PlayerDataManager stats = l.getPlayerData();
			stats.addResource(Resources.SUN_NUM, - TradeUtil.getGoodCost(good));
//			System.out.println(TradeUtil.getGoodItemStack(good) + " , " + this.output.getStackInSlot(0));
			this.output.setItem(0, TradeUtil.getGoodItemStack(good));
		});
		this.player.level.playSound(null, this.player, SoundRegister.DAVE_BUY.get(), SoundCategory.AMBIENT, 1f, 1f);
	}
	
}
