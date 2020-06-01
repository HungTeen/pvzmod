package com.hungteen.pvzmod.entities.ai;

import com.hungteen.pvzmod.entities.npcs.EntityTrader;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAITradeWithPlayer extends EntityAIBase {
	private final EntityTrader trader;

	public EntityAITradeWithPlayer(EntityTrader trader) {
		this.trader = trader;
		this.setMutexBits(5);
	}

	@Override
	public boolean shouldExecute() {
		if (!trader.isEntityAlive())
			return false;

		if (trader.isInWater() || trader.isInLava())
			return false;

		if (!trader.onGround)
			return false;

		if (trader.velocityChanged)
			return false;

		EntityPlayer customer = trader.getCustomer();

		if (customer == null || trader.getDistance(customer) > 16)
			return false;

		return customer.openContainer != null;
	}

	@Override
	public void startExecuting() {
		trader.getNavigator().clearPath();
	}

	@Override
	public void resetTask() {
		trader.setCustomer(null);
	}
}
