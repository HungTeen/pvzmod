package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TallNutZombieEntity extends AbstractZombotanyEntity {

	public TallNutZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_SLOW);
	}
	
	@Override
	public float getLife() {
		return 320;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.TALLNUT_ZOMBIE;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TALLNUT_ZOMBIE;
	}

}
