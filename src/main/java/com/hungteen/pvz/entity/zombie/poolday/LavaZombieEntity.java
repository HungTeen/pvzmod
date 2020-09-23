package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZWaterZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class LavaZombieEntity extends PVZWaterZombieEntity{

	public LavaZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LITTLE_LOW);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		entityIn.setFire(5);
		return super.attackEntityAsMob(entityIn);
	}
	
	@Override
	public float getLife() {
		return 360;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.LAVA_ZOMBIE;
	}

}