package com.hungteen.pvz.event;

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
		if(!ev.getWorld().isRemote()) {
			if(ev.getRand().nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.SaplingTurnOriginChance.get()) == 0) {
				if(ev.getPos().getY() > 2) {
					ev.getWorld().setBlockState(ev.getPos().down(), BlockRegister.ORIGIN_ORE.get().getDefaultState(), 3);
					ev.setResult(Result.DENY);
				}
			}
		}
	}
	
}
