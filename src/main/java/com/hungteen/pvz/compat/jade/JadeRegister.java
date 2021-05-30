package com.hungteen.pvz.compat.jade;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.compat.jade.provider.PVZHealthProvider;

import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;

@WailaPlugin(PVZMod.MOD_ID)
public class JadeRegister implements IWailaPlugin {

	public JadeRegister() {
	}
	
	@Override
	public void register(IRegistrar reg) {
		reg.registerComponentProvider(new PVZHealthProvider(), TooltipPosition.BODY, PVZPlantEntity.class);
	}

}
