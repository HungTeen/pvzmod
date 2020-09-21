package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZWaterZombieEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.world.World;

public class DolphinRiderEntity extends PVZWaterZombieEntity{

	private static final float JUMP_DISTANCE = 10;
	
	public DolphinRiderEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.VERY_FAST);
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.7f, 1.6f);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(!world.isRemote&&this.getAttackTarget()!=null) {
			
		}
	}

	@Override
	public float getLife() {
		return 60;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.DOLPHIN_RIDER;
	}

}
