package com.hungteen.pvzmod.event;

import java.util.Random;

import com.hungteen.pvzmod.entities.drops.EntityCoin;
import com.hungteen.pvzmod.entities.drops.EntityPlantFood;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.poolnight.EntityYetiZombie;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityEvents {

//	@SubscribeEvent
//	public void onEntityBorn(LivingSpawnEvent ev) {
//		EntityLivingBase entity=ev.getEntityLiving();
//		World world=ev.getWorld();
//		if(entity instanceof EntityPlant) {
//			//System.out.println("spawn!");
//			if(!world.isRemote) {
//				world.playSound((EntityPlayer)null,entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1, 1);
//			}
//		}
//	}
	
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent ev)
	{
		if(ev.getEntityLiving() instanceof EntityZombieBase&&!ev.getEntityLiving().world.isRemote){
			EntityZombieBase entity=(EntityZombieBase) ev.getEntity();
			if(entity instanceof EntityYetiZombie) {
				for(int i=1;i<=4;i++) {
					spawnJewel(entity);
				}
				return ;
			}
			Random rand=new Random();
			int x=rand.nextInt(10000)+1;
			
			if(x>=1&&x<=1000) {//copper coin 10%
				EntityCoin coin=new EntityCoin(entity.world,entity.posX,entity.posY,entity.posZ,0);
				entity.world.spawnEntity(coin);
				entity.world.playSound(null, coin.posX, coin.posY,coin.posZ, SoundsHandler.DROP_COIN, SoundCategory.VOICE, 4,1);
			}
			else if(x>=1001&&x<=1100) {//silver coin 1%
				EntityCoin coin=new EntityCoin(entity.world,entity.posX,entity.posY,entity.posZ,1);
				entity.world.spawnEntity(coin);
				entity.world.playSound(null, coin.posX, coin.posY,coin.posZ, SoundsHandler.DROP_COIN, SoundCategory.VOICE, 4,1);
			}
			else if(x>=1101&&x<=1110) {//gold coin 0.1%
				EntityCoin coin=new EntityCoin(entity.world,entity.posX,entity.posY,entity.posZ,2);
				entity.world.spawnEntity(coin);
				entity.world.playSound(null, coin.posX, coin.posY,coin.posZ, SoundsHandler.DROP_COIN, SoundCategory.VOICE, 4,1);
			}
			else if(x==1111){//jewel 0.01%  
				spawnJewel(entity);
			}
			if(entity.getZombieType()==EntityZombieBase.Type.SUPER) {//生成能量豆
				EntityPlantFood plantFood=new EntityPlantFood(entity.world);
				plantFood.setPosition(entity.posX,entity.posY,entity.posZ);
				entity.world.spawnEntity(plantFood);
				entity.world.playSound(null, plantFood.posX, plantFood.posY,plantFood.posZ, SoundsHandler.DROP_SPECIAL, SoundCategory.VOICE, 4,1);
			}
		}
	}
	
//	@SubscribeEvent
//	public void onEntityDeath(LivingFallEvent ev) {
//		EntityLivingBase entity=ev.getEntityLiving();
//		if(entity instanceof EntityNormalZombie) {
//			
//		}
//	}
	
	private void spawnJewel(Entity entity)
	{
		EntityCoin coin=new EntityCoin(entity.world,entity.posX,entity.posY,entity.posZ,3);
		entity.world.spawnEntity(coin);
		entity.world.playSound(null, coin.posX, coin.posY,coin.posZ, SoundsHandler.DROP_JEWEL, SoundCategory.VOICE, 4,1);
	}
}
