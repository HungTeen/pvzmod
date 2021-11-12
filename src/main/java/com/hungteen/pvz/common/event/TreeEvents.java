package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.BlockRegister;

import net.minecraftforge.event.world.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class TreeEvents {

	@SubscribeEvent
	public static void onTreeGrow(SaplingGrowTreeEvent ev) {
		if(! ev.getWorld().isClientSide()) {
			final boolean isBlocked = ! ev.getWorld().isEmptyBlock(ev.getPos().above());
			if(PVZConfig.COMMON_CONFIG.RuleSettings.AllowNaturalTurnOrigin.get() || isBlocked) {
				final int chance = PVZConfig.COMMON_CONFIG.BlockSettings.SaplingTurnOriginChance.get();
				if(ev.getRand().nextInt(isBlocked ? chance : chance + 2) == 0 && ev.getPos().getY() > 2) {
					ev.getWorld().setBlock(ev.getPos().below(), BlockRegister.ORIGIN_ORE.get().defaultBlockState(), 3);
					ev.setResult(Result.DENY);
				}
			}
		}
	}
	
}
