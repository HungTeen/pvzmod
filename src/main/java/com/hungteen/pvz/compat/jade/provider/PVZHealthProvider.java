package com.hungteen.pvz.compat.jade.provider;

import java.util.List;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;

import mcp.mobius.waila.api.IEntityAccessor;
import mcp.mobius.waila.api.IEntityComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;

public class PVZHealthProvider implements IEntityComponentProvider {

	@Override
	public void appendBody(List<ITextComponent> tooltip, IEntityAccessor accessor, IPluginConfig config) {
		IEntityComponentProvider.super.appendBody(tooltip, accessor, config);
		if(accessor.getEntity() instanceof PVZPlantEntity) {
//			tooltip.add(new StringTextComponent("233 pvz 666").withStyle(TextFormatting.DARK_RED));
		}
	}
}
