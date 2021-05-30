package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SquashZombieEntity extends AbstractZombotanyEntity {

	public SquashZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.FAST);
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
				EntityUtil.onMobEntitySpawn(level, squash, blockPosition().above(2));
				this.remove();
			}
		}
	}
	
	@Override
	public float getLife() {
		return 25;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.SQUASH_ZOMBIE;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SQUASH_ZOMBIE;
	}

}
