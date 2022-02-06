package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.client.model.entity.zombie.grass.ConeHeadZombieModel;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ConeHeadZombieEntity extends NormalZombieEntity{

	public ConeHeadZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public float getInnerLife() {
		return 40;
	}
	
	/**
	 * check the visibility of conehead model.
	 * {@link ConeHeadZombieModel#updateFreeParts(ConeHeadZombieEntity)} 
	 */
	public boolean hasConeHead(int stage) {
		final double percent = this.getInnerDefenceLife() / this.getInnerLife();
		if(stage == 3) {
			return percent > 2.0f / 3;
		} else if(stage == 2) {
			return percent > 1.0f / 3;
		} else if(stage == 1) {
			return percent > 0;
		}
		return false;
	}
	
	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return this.getInnerDefenceLife() > 0 ? SoundRegister.PLASTIC_HIT.get() : super.getHurtSound(damageSourceIn);
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.CONEHEAD_ZOMBIE;
	}
	
}
