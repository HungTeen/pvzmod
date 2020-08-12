package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZMod;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZEntityEvent {

//	@SubscribeEvent
//	public static void onEntityDeath(LivingDeathEvent ev)
//	{
//		LivingEntity entity = ev.getEntityLiving();
//		World world=entity.world;
//		if(entity instanceof PVZZombieEntity) {
//			PVZZombieEntity zombie=(PVZZombieEntity) entity;
//			if(!world.isRemote) {
//				if(zombie.getZombieType()==PVZZombieEntity.Type.SUPER) {
//					EnergyEntity energy = EntityRegister.ENERGY.get().create(world);
//					energy.setPortal(zombie.getPosition());
//					world.addEntity(energy);
//				}
//				else if(zombie.getZombieType()==PVZZombieEntity.Type.BEARD) {
//					//finish achievement
//				}
//			}
//		}
//	}
}
