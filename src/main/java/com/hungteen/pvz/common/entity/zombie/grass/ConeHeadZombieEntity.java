package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.client.model.entity.zombie.grass.ConeHeadZombieModel;
import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ConeHeadZombieEntity extends NormalZombieEntity{

	public ConeHeadZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.hasDirectDefence = true;
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public float getExtraLife() {
		return 40;
	}
	
	/**
	 * check the visibility of conehead model.
	 * {@link ConeHeadZombieModel#updateFreeParts(ConeHeadZombieEntity)} 
	 */
	public boolean hasConeHead(int stage) {
		final float percent = this.getDefenceLife() / this.getExtraLife();
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
		return this.getDefenceLife() > 0 ? SoundRegister.PLASTIC_HIT.get() : super.getHurtSound(damageSourceIn);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.CONEHEAD_ZOMBIE;
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.CONEHEAD_ZOMBIE;
	}
	
}
