package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.ZombieType;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

public class NormalZombieEntity extends PVZZombieEntity {

	public NormalZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected VariantType getSpawnType() {
		VariantType type = super.getSpawnType();
		if(type == VariantType.NORMAL) {
			if(this.getRandom().nextInt(200) == 0) {
				this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
			    return VariantType.BEARD;
			}
		}
		return type;
	}

	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.91f;
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.NORMAL_ZOMBIE;
	}

}
