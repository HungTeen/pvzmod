package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBalloonZombie extends EntityZombieBase{

	public EntityBalloonZombie(World worldIn) {
		super(worldIn);
		setSize(0.8f, 1.8f);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.SLOW_WALK);
	}
	
	@Override
	protected PathNavigate createNavigator(World worldIn)
    {
		if(this.isRiding()) {
            return new PathNavigateFlying(this, worldIn);
		}
		else {
			return new PathNavigateGround(this, worldIn);
		}
    }
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		if(source.getDamageType()=="fall") return true;
		return super.isEntityInvulnerable(source);
	}
}
