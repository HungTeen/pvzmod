package com.hungteen.pvzmod.entities.zombies.normal;

import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityConeHeadZombie extends EntityNormalZombie{
	
	public EntityConeHeadZombie(World worldIn) {
		super(worldIn);
		this.setSize(0.8f, 2.2f);
	}

	@Override
	protected void setSmallSize() {
		this.setSize(0.3f, 0.6f);
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(56.0D);
    }
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundsHandler.PLASTIC_HURT;
	}
}
