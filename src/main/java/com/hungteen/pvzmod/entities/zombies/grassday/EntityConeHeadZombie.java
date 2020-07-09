package com.hungteen.pvzmod.entities.zombies.grassday;

import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Zombies;

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
	public float getLife() {
		return 56;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundsHandler.PLASTIC_HURT;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.CONEHEAD_ZOMBIE;
	}
}
