package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.zombie.base.SwimmerZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LavaZombieEntity extends SwimmerZombieEntity{

	public LavaZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathPriority(PathNodeType.LAVA, 1);
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
	protected float getWaterSlowDown() {
		return 0.935f;
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.LAVA_ZOMBIE;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.LAVA_ZOMBIE;
	}

}
