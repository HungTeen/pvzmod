package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.impl.ZombieType;
import com.hungteen.pvz.common.impl.zombie.Zombotanies;
import com.hungteen.pvz.data.loot.PVZLoot;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class WallNutZombieEntity extends AbstractZombotanyEntity {

	public WallNutZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
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
	public ZombieType getZombieType() {
		return Zombotanies.WALLNUT_ZOMBIE;
	}

}
