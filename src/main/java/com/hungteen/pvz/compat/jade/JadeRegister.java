package com.hungteen.pvz.compat.jade;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.compat.jade.provider.PVZEntityProvider;
import com.hungteen.pvz.utils.StringUtil;

import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLEnvironment;

@WailaPlugin(PVZMod.MOD_ID)
public class JadeRegister implements IWailaPlugin {

	public static final ResourceLocation RENDER_DEFENCE_HEALTH = StringUtil.prefix("render_defence_health");
	
	public static final ResourceLocation CONFIG_SHOW_DEFENCE_HEALTH = StringUtil.prefix("show_defence_health");
	public static final ResourceLocation CONFIG_SHOW_OWNER = StringUtil.prefix("show_owner");
	public static final ResourceLocation CONFIG_SHOW_SKILLS = StringUtil.prefix("show_skills");

	public JadeRegister() {
	}
	
	@Override
	public void register(IRegistrar reg) {
		reg.registerComponentProvider(PVZEntityProvider.INSTANCE, TooltipPosition.BODY, Entity.class);
		
		reg.addConfig(CONFIG_SHOW_DEFENCE_HEALTH, true);
		reg.addConfig(CONFIG_SHOW_OWNER, true);
		reg.addConfig(CONFIG_SHOW_SKILLS, true);
		
		if(FMLEnvironment.dist.isClient()) {
//			reg.registerTooltipRenderer(RENDER_DEFENCE_HEALTH, arg1);
		}
	}

}
