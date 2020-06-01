package com.hungteen.pvzmod.entities.plants.base;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.damage.PVZDamageType;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.special.EntityDuckyTube;
import com.hungteen.pvzmod.util.interfaces.IBomber;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityBombBase extends EntityPlantBase implements IBomber{
	
	public EntityBombBase(World worldIn) {
		super(worldIn);
	}

	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		this.setAttackTime(this.getAttackTime()+1);
		if(this.getAttackTime()>=this.getReadyTime()) {
			this.startBoom();
			this.setDead();
			this.setAttackTime(0);
		}
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		if(source instanceof PVZDamageSource) {
			if(((PVZDamageSource) source).getPVZDamageType()==PVZDamageType.WEAK) {
				return false;
			}
		}
		return super.isEntityInvulnerable(source);
	}
	
	@Override
	public boolean getIsInvulnerable() {
		return true;
	}
	
	@Override
	public boolean canStartSuperMode() {
		return false;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 0;
	}
}
