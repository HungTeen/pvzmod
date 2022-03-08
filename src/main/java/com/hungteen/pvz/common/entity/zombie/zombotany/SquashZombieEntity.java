package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.Zombotanies;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class SquashZombieEntity extends AbstractZombotanyEntity {

	public SquashZombieEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
		this.canLostHead = false;
	}

	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_VERY_FAST);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {
			LivingEntity target = this.getTarget();
			if(target != null && this.distanceToSqr(target) <= 10) {
				SquashEntity squash = EntityRegister.SQUASH.get().create(level);
				squash.setCharmed(! this.isCharmed());
				squash.setTarget(target);
				EntityUtil.onEntitySpawn(level, squash, blockPosition().above(2));
				this.remove();
			}
		}
	}
	
	@Override
	public float getLife() {
		return 25;
	}
	
	@Override
	public ZombieType getZombieType() {
		return Zombotanies.SQUASH_ZOMBIE;
	}

}
