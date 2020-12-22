package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.magic.StrangeCatEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZLivingEvents {

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent ev) {
		if(ev.getEntityLiving() instanceof PVZZombieEntity) {
			PVZZombieEntity zombie = (PVZZombieEntity) ev.getEntityLiving();
			int xp = ZombieUtil.getZombieXp(zombie.getZombieEnumName());
			PlayerEntity player = EntityUtil.getEntityOwner(zombie.world, ev.getSource().getTrueSource());
			if(player == null) {//true source has no owner
				if(ev.getSource().getTrueSource() instanceof PlayerEntity) {
					PlayerUtil.addPlayerStats((PlayerEntity) ev.getSource().getTrueSource(), Resources.TREE_XP, xp);
					onPlayerKillZombie((PlayerEntity) ev.getSource().getTrueSource(), zombie.getZombieEnumName());
			    }
			}else {
				if(ev.getSource().getTrueSource() instanceof PVZPlantEntity) {
					PlayerUtil.addPlantXp(player, ((PVZPlantEntity)ev.getSource().getTrueSource()).getPlantEnumName(), xp);
					onPlayerKillZombie(player, zombie.getZombieEnumName());
				}
			}
		}
		if(ev.getSource() instanceof PVZDamageSource) {
			PVZDamageSource source = (PVZDamageSource) ev.getSource();
			if(source.isCopyDamage()) {
				if(source.getTrueSource() instanceof StrangeCatEntity) {
					((StrangeCatEntity) source.getTrueSource()).onSelfCopy(ev.getEntityLiving());
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent ev) {
		if(! ev.getEntityLiving().world.isRemote) {
			ev.getEntityLiving().getArmorInventoryList().forEach((stack)->{
				if(EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.TREE_PROTECTION.get(), stack) > 0){
					float amount = ev.getAmount();
					amount = Math.min(amount, ev.getEntityLiving().getMaxHealth());
					ev.setAmount(amount);
					return ;
				}
		    });
		}
	}
	
	private static void onPlayerKillZombie(PlayerEntity player, Zombies zombie) {
//		Almanacs a = Almanacs.getAlmanacByName(zombie.toString().toLowerCase());
	}
}
