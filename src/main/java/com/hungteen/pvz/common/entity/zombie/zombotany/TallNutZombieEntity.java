package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.Zombotanies;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class TallNutZombieEntity extends AbstractZombotanyEntity {

	public TallNutZombieEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_SLOW);
	}
	
	@Override
	public float getLife() {
		return 320;
	}
	
	@Override
	public ZombieType getZombieType() {
		return Zombotanies.TALLNUT_ZOMBIE;
	}

}
