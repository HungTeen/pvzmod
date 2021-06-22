package com.hungteen.pvz.common.entity.zombie.part;

import com.hungteen.pvz.common.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.misc.damage.PVZDamageType;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class PVZHealthPartEntity extends PVZZombiePartEntity{

	protected DefenceZombieEntity zombie;
	
	public PVZHealthPartEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}
	
	public PVZHealthPartEntity(DefenceZombieEntity owner, float sizeX, float sizeY) {
		super(owner, sizeX, sizeY);
		this.zombie = owner;
	}
	
	@Override
	public boolean hurt(DamageSource source, float damage) {
		//the source can pass through defence and deal damage to owner directly.
		if(source instanceof PVZDamageSource) {
			if(((PVZDamageSource) source).getPVZDamageType() == PVZDamageType.THROUGH) {
				return super.hurt(source, damage);
			}
			((PVZDamageSource) source).setDefended(true);
		}
		//reduce the amount.
		damage = damage * this.zombie.getHurtReduction();
		if(this.zombie.getDefenceLife() > 0) {
			SoundEvent sound = null;
			if(this.zombie.getDefenceLife() > damage) {
			    this.zombie.setDefenceLife(this.zombie.getDefenceLife() - damage);
			    sound = this.zombie.getPartHurtSound();
			    damage = 0;
		    } else {
			    damage -= this.zombie.getDefenceLife();
			    this.zombie.setDefenceLife(0);
			    sound = this.zombie.getPartDeathSound();
		    }
			if(sound != null) {
				EntityUtil.playSound(this, sound);
			}
		}
		if(damage == 0) damage = 0.001F;
		return super.hurt(source, damage);
	}
	
	@Override
	public boolean canExist() {
		return super.canExist() && this.zombie.canPartsExist();
	}
	
}