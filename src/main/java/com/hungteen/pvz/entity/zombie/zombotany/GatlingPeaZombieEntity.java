package com.hungteen.pvz.entity.zombie.zombotany;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GatlingPeaZombieEntity extends PeaShooterZombieEntity {

	public GatlingPeaZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected int getShootNum() {
		return 4;
	}
	
	@Override
	public float getLife() {
		return 30;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.GATLINGPEA_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.GATLINGPEA_ZOMBIE;
	}
	
}
