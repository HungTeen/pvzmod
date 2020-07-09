package com.hungteen.pvzmod.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.plants.fight.EntitySquash;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntitySquashZombie extends EntityNormalZombie{

	public EntitySquashZombie(World worldIn) {
		super(worldIn);
		this.setSize(1f, 2.7f);
	}
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.4f, 0.7f);
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
    }

	@Override
	public void onNormalZombieUpdate() {
		super.onNormalZombieUpdate();
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
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SQUASH_ZOMBIE;
	}
}
