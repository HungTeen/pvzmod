package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.special.EntityJackOutBoxZombie;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityJackInBoxZombie extends EntityZombieBase{

	public EntityJackInBoxZombie(World worldIn) {
		super(worldIn);
		this.setSize(1f, 2.1f);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.POLE_SPEED);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.world.isRemote) {
			if(ticksExisted%20==0) {
				int chance=this.getExpChance(ticksExisted);
				if(this.getRNG().nextInt(chance)==0) {//±¬Õ¨
					EntityJackOutBoxZombie jack = new EntityJackOutBoxZombie(world);
					jack.setPositionAndRotation(posX, posY, posZ, rotationYaw, rotationPitch);
					this.world.spawnEntity(jack);
					this.world.playSound(null, posX,posY,posZ, SoundsHandler.JACK_SURPRISE, SoundCategory.AMBIENT, 1f,1f);
					this.setDead();
				}
			}
			if(ticksExisted%120==0) {
				this.world.playSound(null, posX,posY,posZ, SoundsHandler.JACK_AMBIENT, SoundCategory.AMBIENT, 1f,1f);
			}
		}
	}
	
	private int getExpChance(int ticknow)
	{
		int now=(2000-ticknow)/20;
		return now>0?now:1;
	}
}
