package com.hungteen.pvzmod.entities.ai;

import com.hungteen.pvzmod.entities.npcs.EntityTrader;

import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIFaceCustomer extends EntityAIWatchClosest {
	private final EntityTrader trader;

	public EntityAIFaceCustomer(EntityTrader trader) {
		super(trader, EntityPlayer.class, 8.0f);
		this.trader = trader;
	}

	@Override
	public boolean shouldExecute() {
		if (trader.isTrading()) {
			this.closestEntity = trader.getCustomer();

			return true;
		}

		return false;
	}
}