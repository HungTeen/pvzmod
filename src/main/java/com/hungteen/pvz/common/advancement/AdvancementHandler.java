package com.hungteen.pvz.common.advancement;

import com.hungteen.pvz.common.advancement.trigger.CharmZombieTrigger;
import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.advancement.trigger.MoneyTrigger;
import com.hungteen.pvz.common.advancement.trigger.PlantLevelTrigger;
import com.hungteen.pvz.common.advancement.trigger.PlantSuperTrigger;
import com.hungteen.pvz.common.advancement.trigger.PlayerPlacePlantTrigger;
import com.hungteen.pvz.common.advancement.trigger.SunAmountTrigger;
import com.hungteen.pvz.common.advancement.trigger.TreeLevelTrigger;

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
	}
	
}
