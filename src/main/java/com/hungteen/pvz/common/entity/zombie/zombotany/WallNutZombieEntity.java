package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class WallNutZombieEntity extends AbstractZombotanyEntity {

	public WallNutZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getLife() {
		return 160;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.WALLNUT_ZOMBIE;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.WALLNUT_ZOMBIE;
	}

}
