package com.hungteen.pvz.advancement;

import com.hungteen.pvz.advancement.trigger.CharmZombieTrigger;
import com.hungteen.pvz.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.advancement.trigger.MoneyTrigger;
import com.hungteen.pvz.advancement.trigger.PlantLevelTrigger;
import com.hungteen.pvz.advancement.trigger.PlantSuperTrigger;
import com.hungteen.pvz.advancement.trigger.PlayerPlacePlantTrigger;
import com.hungteen.pvz.advancement.trigger.SunAmountTrigger;
import com.hungteen.pvz.advancement.trigger.TreeLevelTrigger;

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
