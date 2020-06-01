package com.hungteen.pvzmod.entities.zombies.special;

import com.hungteen.pvzmod.entities.zombies.EntityBalloonZombie;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieToolBase;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityBalloon extends EntityZombieToolBase{

	public EntityBalloon(World worldIn) {
		super(worldIn);
		this.setSize(0.6f, 1.5f);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.getAttackTarget()!=null) {
			Entity target=this.getAttackTarget(); 
			double dis=Math.sqrt((target.posX-this.posX)*(target.posX-this.posX)+(target.posZ-this.posZ)*(target.posZ-this.posZ));
			if(dis<=2&&this.posY-target.posY>=1) {//在目标头顶 则降落
				this.motionY=-0.1;
			}
			else {
				if(target.posY+15>=this.posY) {//
				    this.motionY=0.2;
			    }
				else this.motionY=0;
			}
			
		}
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		if(!this.world.isRemote) {
			EntityBalloonZombie zombie =new EntityBalloonZombie(world);
			zombie.setPosition(posX, posY, posZ);
			this.world.spawnEntity(zombie);
			zombie.startRiding(this);
		}
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.POLE_SPEED);
	}
	
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	@Override
	public double getMountedYOffset() {
		return -1f;
	}
}
