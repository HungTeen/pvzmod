package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.utils.ZombieUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FlagZombieEntity extends NormalZombieEntity{

	public FlagZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_FAST);
	}
	
	@Override
	public float getLife() {
		return 19;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.FLAG_ZOMBIE;
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.FLAG_ZOMBIE;
	}
}
