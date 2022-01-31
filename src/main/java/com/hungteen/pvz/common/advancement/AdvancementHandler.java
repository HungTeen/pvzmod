package com.hungteen.pvz.common.advancement;

import com.hungteen.pvz.common.advancement.trigger.*;

import net.minecraft.advancements.CriteriaTriggers;

public class AdvancementHandler {

	public static void init() {
		CriteriaTriggers.register(SunAmountTrigger.INSTANCE);
		CriteriaTriggers.register(TreeLevelTrigger.INSTANCE);
		CriteriaTriggers.register(EntityEffectAmountTrigger.INSTANCE);
		CriteriaTriggers.register(PlayerPlacePlantTrigger.INSTANCE);
		CriteriaTriggers.register(CharmZombieTrigger.INSTANCE);
		CriteriaTriggers.register(PlantLevelTrigger.INSTANCE);
		CriteriaTriggers.register(MoneyTrigger.INSTANCE);
		CriteriaTriggers.register(PlantSuperTrigger.INSTANCE);
		CriteriaTriggers.register(ChallengeTrigger.INSTANCE);
	}
	
}
