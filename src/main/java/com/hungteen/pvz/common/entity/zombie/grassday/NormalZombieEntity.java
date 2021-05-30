package com.hungteen.pvz.common.entity.zombie.grassday;

import com.hungteen.pvz.common.entity.zombie.base.UnderGroundZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class NormalZombieEntity extends UnderGroundZombieEntity {

	public NormalZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.isRiseType = false;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.91f;
	}

	@Override
	protected Type getSpawnType() {
		Type type = super.getSpawnType();
		if(type == Type.NORMAL) {
			if(this.getRandom().nextInt(200) == 0) {
				this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
			    return Type.BEARD;
			}
		}
		return type;
	}

	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NORMAL_ZOMBIE;
	}
}
