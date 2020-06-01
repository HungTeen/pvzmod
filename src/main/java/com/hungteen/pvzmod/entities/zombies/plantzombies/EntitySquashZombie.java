package com.hungteen.pvzmod.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.plants.fight.EntitySquash;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntitySquashZombie extends EntityZombieBase{

	public EntitySquashZombie(World worldIn) {
		super(worldIn);
		this.setSize(1f, 2.7f);
	}
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.4f, 0.7f);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.getAttackTarget()!=null&&!this.world.isRemote) {
			EntityLivingBase target=this.getAttackTarget();
			double dis=this.getDistanceSq(target);
			if(dis<=(this.width/2+target.width/2+2)*(this.width/2+target.width/2+2)) {
				EntitySquash squash = new EntitySquash(world);
				squash.setPosition(target.posX, target.posY+target.getEyeHeight()+2, target.posZ);
				squash.setIsCharmed(true);
				this.world.spawnEntity(squash);
				this.setDead();
			}
		}
	}
}
