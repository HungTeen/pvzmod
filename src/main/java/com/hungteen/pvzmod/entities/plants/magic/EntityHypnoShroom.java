package com.hungteen.pvzmod.entities.plants.magic;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.damage.PVZDamageType;
import com.hungteen.pvzmod.entities.plants.base.EntityShroomBase;
import com.hungteen.pvzmod.entities.zombies.EntityGargantuar;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHypnoShroom extends EntityShroomBase{

	public EntityHypnoShroom(World worldIn) {
		super(worldIn);
	}

	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 24000;
		else if(lvl<=13) return 48000;
		else if(lvl<=20) return 72000;
		return 24000;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if(!this.world.isRemote&&!this.getIsSleeping()&&cause instanceof PVZDamageSource) {
//			System.out.println("1");
			PVZDamageSource source=(PVZDamageSource) cause;
			if(source.getPVZDamageType()==PVZDamageType.EAT) {//±»½©Ê¬³ÔÁË
				Entity attacker=source.getTrueSource();
				if(attacker instanceof EntityZombieBase) {
					if(this.isPlantInSuperMode()) {
						attacker.attackEntityFrom(PVZDamageSource.causeMagicDamage(this, this), ((EntityZombieBase) attacker).getHealth());
						EntityGargantuar gar=new EntityGargantuar(world);
						
						gar.setPosition(posX, posY, posZ);
						gar.setZombieMaxHealth(gar.getHealth()*this.superLifeBuff());
						gar.setIsCharmed(true);
						gar.setZombieType(EntityZombieBase.Type.NORMAL);
						this.world.spawnEntity(gar);
					}else {
						((EntityZombieBase) attacker).setIsCharmed(true);
					    ((EntityZombieBase) attacker).setZombieMaxHealth(((EntityZombieBase) attacker).getHealth()*this.normalLifeBuff());
					}
				}
			}
		}
	}
	
	private float superLifeBuff()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 0.5f;
		else if(lvl<=13) return 1f;
		else if(lvl<=20) return 1.5f;
		return 0.5f;
	}
	
	private float normalLifeBuff()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 1f+now/10f;
		}
		return 1f;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.HYPNO_SHROOM;
	}

	public enum State
	{
		NORMAL,
		SUPER
	}
}
