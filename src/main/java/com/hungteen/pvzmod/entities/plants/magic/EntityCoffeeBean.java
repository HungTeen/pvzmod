package com.hungteen.pvzmod.entities.plants.magic;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.plants.base.EntityBombBase;
import com.hungteen.pvzmod.entities.plants.base.EntityShroomBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityCoffeeBean extends EntityBombBase{

	public EntityCoffeeBean(World worldIn) {
		super(worldIn);
		this.setSize(0.5f, 0.5f);
	}

	@Override
	public void startBoom() {
		if(!this.world.isRemote) {
			float len=(this.getPlantLvl()<=10)?1f:2f;
			AxisAlignedBB aabb=new AxisAlignedBB(this.posX+len, this.posY+3, this.posZ+len,this.posX-len, this.posY-1, this.posZ-len);
			for(Entity entity:this.world.getEntitiesWithinAABB(EntityShroomBase.class, aabb)) {
				if(entity instanceof EntityShroomBase) {
					((EntityShroomBase) entity).setAwakeTime(this.getWakeTime());
				}
			}
//			this.world.playSound(null,this.posX,this.posY,this.posZ,SoundsHandler.CHERRY_BOMB,SoundCategory.VOICE, 4, 1);
		}
//		for(int i=1;i<=5;i++) {
//			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX, this.posY, this.posZ, 0, 0, 0);
//		}
	}

	public int getWakeTime()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 24000;
		else if(lvl<=13) return 48000;
		else if(lvl<=20) return 72000;
		return 24000;
	}
	
	@Override
	public int getReadyTime() {
		return 80;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.COFFEE_BEAN;
	}

}
