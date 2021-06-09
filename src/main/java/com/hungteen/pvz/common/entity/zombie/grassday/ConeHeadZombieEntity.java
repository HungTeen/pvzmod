package com.hungteen.pvz.common.entity.zombie.grassday;

import com.hungteen.pvz.client.model.entity.zombie.grassday.ConeHeadZombieModel;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ConeHeadZombieEntity extends NormalZombieEntity{

	private static final float CONE_HEALTH = 40;
	
	public ConeHeadZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.hasDirectDefence = true;
		this.setDefenceLife(CONE_HEALTH);
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	/**
	 * check the visibility of conehead model.
	 * {@link ConeHeadZombieModel#updateFreeParts(ConeHeadZombieEntity)} 
	 */
	public boolean hasConeHead(int stage) {
		final float percent = this.getDefenceLife() / CONE_HEALTH;
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
	public Zombies getZombieEnumName() {
		return Zombies.CONEHEAD_ZOMBIE;
	}
	
}
