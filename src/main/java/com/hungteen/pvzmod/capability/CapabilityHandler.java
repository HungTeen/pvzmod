package com.hungteen.pvzmod.capability;

import com.hungteen.pvzmod.capability.provider.PVZPlayerProvider;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {
	//public static final ResourceLocation SUN_CAP = new ResourceLocation(Reference.MODID, "sun");
	public static final ResourceLocation PVZPLAYER = new ResourceLocation(Reference.MODID,"PVZPlayer");

	@SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if ((event.getObject() instanceof EntityPlayerMP))
        {
        	event.addCapability(PVZPLAYER, new PVZPlayerProvider((EntityPlayer)event.getObject()));
        	//event.addCapability(SUN_CAP, new SunProvider());
        }
    }
}
