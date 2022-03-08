package com.hungteen.pvz.common.entity.zombie.pool;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.Effects;
import net.minecraft.world.level.Level;

public class BobsleZombieEntity extends PVZZombieEntity{

	public BobsleZombieEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public void zombieTick() {
		super.zombieTick();
		if(! level.isClientSide) {
			if(this.tickCount % 30 == 0 && (EntityUtil.isOnSnow(this) || EntityUtil.isOnIce(this))) {
				this.addEffect(new MobEffectInstance(Effects.MOVEMENT_SPEED, 40, 0, false, false));
				this.addEffect(new MobEffectInstance(Effects.REGENERATION, 40, 3, false, false));
			}
		}
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public ZombieType getZombieType() {
		return PoolZombies.BOBSLE_ZOMBIE;
	}

}
