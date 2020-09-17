package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZWaterZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class SnorkelZombieEntity extends PVZWaterZombieEntity {

	public SnorkelZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.7f, 1.9f, false);
	}

	@Override
	public float getLife() {
		return 20;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SNORKEL_ZOMBIE;
	}

}
