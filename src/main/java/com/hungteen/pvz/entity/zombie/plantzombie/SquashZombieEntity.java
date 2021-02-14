package com.hungteen.pvz.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class SquashZombieEntity extends PVZZombieEntity {

	public SquashZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.FAST);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! world.isRemote) {
			LivingEntity target = this.getAttackTarget();
			if(target != null && this.getDistanceSq(target) <= 10) {
				SquashEntity squash = EntityRegister.SQUASH.get().create(world);
				squash.setCharmed(! this.isCharmed());
				squash.setAttackTarget(target);
				EntityUtil.onMobEntitySpawn(world, squash, getPosition().up(2));
				this.remove();
			}
		}
	}
	
	@Override
	public float getLife() {
		return 25;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SQUASH_ZOMBIE;
	}

}
