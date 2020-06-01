package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPogoZombie extends EntityZombieBase{

	public EntityPogoZombie(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.SLOW_WALK);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.world.isRemote&&this.ticksExisted%25==0) {
			this.motionY=0.8f;
			this.playSound(SoundsHandler.POGO, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		}
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		if(source.getDamageType()=="fall") return true;
		return super.isEntityInvulnerable(source);
	}
}
