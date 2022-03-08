package com.hungteen.pvz.common.container.shop;

import com.hungteen.pvz.common.container.ContainerRegister;
import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.world.entity.player.Player;

public class SunShopContainer extends AbstractDaveShopContainer {
	
	public SunShopContainer(int id, Player player, int entityId) {
		super(ContainerRegister.SUN_SHOP.get(), id, player, entityId);
	}

	@Override
	public void buyGood(AbstractDaveEntity.GoodType good) {
		super.buyGood(good);
		PlayerUtil.addResource(player, Resources.SUN_NUM, - good.getGoodPrice());
		PlayerUtil.playClientSound(player, SoundRegister.DAVE_HAPPY.get());
	}
	
}
