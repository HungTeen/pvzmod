package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraftforge.event.world.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class TreeEvents {

	@SubscribeEvent
	public static void onTreeGrow(SaplingGrowTreeEvent ev) {
		if(!ev.getWorld().isClientSide()) {
			if(ev.getRand().nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.SaplingTurnOriginChance.get()) == 0) {
				if(ev.getPos().getY() > 2) {
					ev.getWorld().setBlock(ev.getPos().below(), BlockRegister.ORIGIN_ORE.get().defaultBlockState(), 3);
					ev.setResult(Result.DENY);
				}
			}
		}
	}
	
}
