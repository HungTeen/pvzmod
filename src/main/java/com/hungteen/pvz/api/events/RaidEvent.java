package com.hungteen.pvz.api.events;

import com.hungteen.pvz.common.world.challenge.Challenge;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public class RaidEvent extends Event {

	private final Challenge raid;
	
	public RaidEvent(Challenge raid) {
		this.raid = raid;
	}
	
	public Challenge getRaid() {
		return this.raid;
	}
	
	public static class RaidStartEvent extends RaidEvent{
		
		public RaidStartEvent(Challenge raid) {
			super(raid);
		}
		
	}
	
	/**
	 * cancel this event to avoid reward.
	 */
	@Cancelable
    public static class RaidWinEvent extends RaidEvent{
		
		public RaidWinEvent(Challenge raid) {
			super(raid);
		}
		
	}

	public static class RaidLossEvent extends RaidEvent{
		
		public RaidLossEvent(Challenge raid) {
			super(raid);
		}
		
	}
	
}
