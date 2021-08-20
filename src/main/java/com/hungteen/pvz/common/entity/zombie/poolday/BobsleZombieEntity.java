package com.hungteen.pvz.common.entity.zombie.poolday;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.remove.Zombies;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class BobsleZombieEntity extends PVZZombieEntity{

	public BobsleZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void zombieTick() {
		super.zombieTick();
		if(! level.isClientSide) {
			if(this.tickCount % 30 == 0 && (EntityUtil.isOnSnow(this) || EntityUtil.isOnIce(this))) {
				this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 0, false, false));
				this.addEffect(new EffectInstance(Effects.REGENERATION, 40, 3, false, false));
			}
		}
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BOBSLE_ZOMBIE;
	}

}
