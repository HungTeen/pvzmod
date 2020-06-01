package com.hungteen.pvzmod;

import com.hungteen.pvzmod.event.OverworldEvents;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.registry.RegistryHandler;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void registerItemRenderer(Item item,int meta,String id){}

	public void registerVariantRenderer(Item item, int meta, String filename, String id){}

	public void preInit(FMLPreInitializationEvent event){
		RegistryHandler.preInitRegistries();
	}

    public void init(FMLInitializationEvent event){
    	RegistryHandler.initRegistries();
    }

    public void postInit(FMLPostInitializationEvent event){
    	RegistryHandler.postInitRegistries();
    }

	public void spawnParticle(PVZParticleType particleType, double x, double y, double z, double vx, double vy,double vz) {}
	
}
