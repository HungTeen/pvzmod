package com.hungteen.pvzmod.event;

import com.hungteen.pvzmod.capability.data.PlayerDataManager;
import com.hungteen.pvzmod.client.gui.mainwindow.PVZGuiTabPlayerData;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.items.tools.ItemDaveShovel;
import com.hungteen.pvzmod.items.tools.ItemSummonCard;
import com.hungteen.pvzmod.packet.PacketHandler;
import com.hungteen.pvzmod.packet.PacketPlantLvlData;
import com.hungteen.pvzmod.packet.PacketPlayerData;
import com.hungteen.pvzmod.util.PlantsUtil;
import com.hungteen.pvzmod.util.PlayerUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerEvents {
	
	@SubscribeEvent
	public void onPlayerLogin(final net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.player instanceof EntityPlayerMP && !ev.player.world.isRemote) {
//				String msg = null;
//				if (msg != null)
//					FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendMessage(new TextComponentString(msg));

			PlayerDataManager plData = PlayerUtil.getPVZPlayer(ev.player);
			PlayerDataManager.PlayerStats playerStats = plData.getPlayerStats();
			PlayerDataManager.PlantStats plantStats = plData.getPlantStats();

			for (Plants plant : Plants.values()) {
				PacketHandler.CHANNEL.sendTo(new PacketPlantLvlData(plant,plantStats.getPlantLevel(plant),plantStats.getPlantXp(plant),plantStats.getIsPlantLocked(plant)), (EntityPlayerMP) ev.player);
			}

			PacketHandler.CHANNEL.sendTo(new PacketPlayerData(playerStats.getPlayerLevel(), playerStats.getPlayerXp(),
					playerStats.getPlayerSunNum(), playerStats.getPlayerEnergyNum(), playerStats.getPlayerMoney()),
					(EntityPlayerMP) ev.player);
//			WorldDataSpecialDay data=WorldDataSpecialDay.getGlobalData(ev.player.getEntityWorld());
//			for(SpecialEvents event:SpecialEvents.values()) {
//				if(data.hasEvent(event)) {
//					System.out.println("have-"+event);
//				}
//				System.out.println(event);
//			}
			//ev.player.addItemStackToInventory(new ItemStack(ItemRegister.PEA_SHOOTER_CARD));
		}
	}

	@SubscribeEvent
	public void onPlayerClone(final PlayerEvent.Clone ev) {
		if (!ev.getEntityPlayer().world.isRemote)
			PlayerUtil.clonePlayerData(ev.getOriginal(), ev.getEntityPlayer());
	}
	
	@SubscribeEvent
	public void onPlayerRightClick(PlayerInteractEvent.EntityInteract ev)
	{
		
		World world=ev.getWorld();
		EntityPlayer player=ev.getEntityPlayer();
		Entity entity=ev.getTarget();
//		System.out.println(entity);
		Item item=player.getHeldItemMainhand().getItem();
		if(!world.isRemote&&item instanceof ItemSummonCard&&entity instanceof EntityPlantBase){
			Plants plant=((ItemSummonCard) item).getPlant();
			if(!player.getCooldownTracker().hasCooldown(item)) {//冷却过了
//				System.out.println("in");
				EntityPlantBase newplant=PlantsUtil.getEntityForUpgrade(world,entity, plant);
				if(newplant==null) {
					return;
				}
				newplant.setPlantLvl(PVZGuiTabPlayerData.getPlayerPlantCardLvl(plant));
				newplant.setPosition(entity.posX, entity.posY, entity.posZ);
				newplant.setOwnerName(player.getName());
				int cost=newplant.getSunCost();
				int cooldownTime=newplant.getCoolDownTime();
//				System.out.println("here");
				if(PlayerUtil.getPlayerSunNum(player)>=cost) {
					PlayerUtil.addPlayerSunNum(player, -cost);
					entity.setDead();
					world.spawnEntity(newplant);
					player.getCooldownTracker().setCooldown(item, cooldownTime);
					newplant.playSound(SoundEvents.BLOCK_GRASS_PLACE, 1f, 1f);
					player.addStat(StatList.getObjectUseStats(item));
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific event)
	{
		Entity entity = event.getTarget();
		EntityPlayer player=event.getEntityPlayer();
		
		if(entity instanceof EntityPlantBase){//debug
			
			ItemStack stack=player.getHeldItem(EnumHand.MAIN_HAND);
			Item item=stack.getItem();
			if(item==Items.DIAMOND_SWORD) {
				if(((EntityPlantBase) entity).canStartSuperMode()) {
			        ((EntityPlantBase)entity).startSuperMode();//放大招
				}
			}
			else { 
				//((EntityPlantBase)entity).plantLvlUp();//升级
//				System.out.println(((EntityPlantBase) entity).getOwnerName());
//				System.out.println(((EntityPlantBase) entity).getPlantLvl());
			}
			//System.out.println(((EntityPlant) entity).getPlantLvl());
			if(item instanceof ItemSpade) {
				if(!(item instanceof ItemDaveShovel))
				    stack.damageItem(5, player);
				player.world.playSound(null, player.posX,player.posY,player.posZ,SoundEvents.BLOCK_GRASS_BREAK,SoundCategory.NEUTRAL,1,1);
				entity.setDead();
			}
		}
	}
	
	
	private void dropItem(World world,ItemStack stack,double x,double y,double z)
	{
		EntityItem entityitem = new EntityItem(world, x,y,z, stack);
        entityitem.setDefaultPickupDelay();
        world.spawnEntity(entityitem);
	}
}
