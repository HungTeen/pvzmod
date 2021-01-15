package com.hungteen.pvz.entity.misc;

import com.hungteen.pvz.misc.damage.PVZDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class WallNutBowlingEntity extends AbstractBowlingEntity {

	public WallNutBowlingEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public WallNutBowlingEntity(EntityType<?> type, World worldIn, PlayerEntity entity) {
		super(type, worldIn, entity);
	}

	@Override
	protected void dealDamageTo(Entity entity) {
		entity.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.getThrower()), 30);
	}

}
