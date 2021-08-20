package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZEntityEvents {

	@SubscribeEvent
	public static void onEntityJoinOverWorld(EntityJoinWorldEvent ev) {
		if(PVZConfig.COMMON_CONFIG.WorldSettings.CanSpawnDefaultMonster.get()) {
			return;
		}
		World world = ev.getWorld();
		if(!world.isClientSide && world.dimension() == World.OVERWORLD) {
			Entity entity = ev.getEntity();
			if(!(entity instanceof PVZZombieEntity)&&entity instanceof MonsterEntity) {
				ev.setCanceled(true);
			}
		}
	}
}
