package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Zombies;

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
	public void normalZombieTick() {
		super.normalZombieTick();
		if(!world.isRemote) {
			if(EntityUtil.isOnSnow(this)) {
				this.addPotionEffect(new EffectInstance(Effects.SPEED, 10, 0, false, false));
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
