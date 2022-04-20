package com.hungteen.pvz.common.advancement;

import com.hungteen.pvz.common.advancement.trigger.ResourceTrigger;
import com.hungteen.pvz.common.advancement.trigger.SummonCardUseTrigger;
import net.minecraft.advancements.CriteriaTriggers;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 16:49
 **/
public class AdvancementHandler {

    public static void init() {
        CriteriaTriggers.register(ResourceTrigger.INSTANCE);
        CriteriaTriggers.register(SummonCardUseTrigger.INSTANCE);
//        CriteriaTriggers.register(EntityEffectAmountTrigger.INSTANCE);
//        CriteriaTriggers.register(PlayerPlacePAZTrigger.INSTANCE);
//        CriteriaTriggers.register(CharmZombieTrigger.INSTANCE);
//        CriteriaTriggers.register(PlantLevelTrigger.INSTANCE);
//        CriteriaTriggers.register(PlantSuperTrigger.INSTANCE);
//        CriteriaTriggers.register(ChallengeTrigger.INSTANCE);
//        CriteriaTriggers.register(InvasionTrigger.INSTANCE);
//        CriteriaTriggers.register(InvasionMissionTrigger.INSTANCE);
//        CriteriaTriggers.register(PVZTradeTrigger.INSTANCE);
//        CriteriaTriggers.register(SlotMachineTrigger.INSTANCE);
    }

}
