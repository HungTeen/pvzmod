package com.hungteen.pvz.compat.jade;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.Util;
import mcp.mobius.waila.api.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-07 16:45
 **/
@WailaPlugin(PVZMod.MOD_ID)
public class JadeRegister implements IWailaPlugin {

    public static final ResourceLocation CONFIG_SHOW_OWNER = Util.prefix("show_owner");
    public static final ResourceLocation CONFIG_SHOW_SKILLS = Util.prefix("show_skills");

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.addConfig(CONFIG_SHOW_OWNER, true);
        registration.addConfig(CONFIG_SHOW_SKILLS, true);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider(JadeEntityProvider.INSTANCE, TooltipPosition.BODY, Entity.class);
    }

}
