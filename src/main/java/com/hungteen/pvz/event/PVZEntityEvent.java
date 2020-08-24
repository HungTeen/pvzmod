package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZEntityEvent {

	@SubscribeEvent
	public static void onEntityJoinOverWorld(EntityJoinWorldEvent ev) {
		if(PVZConfig.COMMON_CONFIG.EntitySettings.CanSpawnDefaultMonster.get()) {
			return;
		}
		World world = ev.getWorld();
		if(!world.isRemote&&world.getDimension().getDimension().getType()==DimensionType.OVERWORLD) {
			Entity entity = ev.getEntity();
			if(!(entity instanceof PVZZombieEntity)&&entity instanceof MonsterEntity) {
//				System.out.println(entity);
				ev.setCanceled(true);
			}
		}
	}
}
