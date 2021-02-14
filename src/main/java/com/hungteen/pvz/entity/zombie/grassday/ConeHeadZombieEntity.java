package com.hungteen.pvz.entity.zombie.grassday;

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

	public static final float CONE_HEALTH = 40;
	
	public ConeHeadZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.hasDirectDefence = true;
		this.setDefenceLife(CONE_HEALTH);
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return this.getDefenceLife() > 0 ? SoundRegister.PLASTIC_HIT.get() : super.getHurtSound(damageSourceIn);
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.CONEHEAD_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.CONEHEAD_ZOMBIE;
	}
	
}
