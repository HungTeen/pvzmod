package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZEntityEvent {

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent ev) {
		if(ev.getEntityLiving() instanceof PVZZombieEntity) {
			PVZZombieEntity zombie = (PVZZombieEntity) ev.getEntityLiving();
			int xp = ZombieUtil.getZombieXp(zombie.getZombieEnumName());
			PlayerEntity player = EntityUtil.getEntityOwner(zombie.world, ev.getSource().getTrueSource());
			if(player==null) {//true source has no owner
				if(ev.getSource().getTrueSource() instanceof PlayerEntity) {
					ev.getSource().getTrueSource().getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
						l.getPlayerData().getPlayerStats().addPlayerStats(Resources.TREE_XP, xp);
					});
			    }
			}else {
				if(ev.getSource().getTrueSource() instanceof PVZPlantEntity) {
					player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
						l.getPlayerData().getPlantStats().addPlantXp(((PVZPlantEntity)ev.getSource().getTrueSource()).getPlantEnumName(), xp);
					});
				}
			}
		}
	}
	
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
