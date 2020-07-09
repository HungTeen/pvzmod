package com.hungteen.pvzmod.entities.zombies.grassnight;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityFootballZombie extends EntityZombieBase{

	public EntityFootballZombie(World worldIn) {
		super(worldIn);
		this.setSize(1f, 2.4f);
	}
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.5f, 0.6f);
	}
	
	@Override
	public float getLife() {
		return 160;
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.BIT_FAST);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.EAT);
    }
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.FOOTBALL_ZOMBIE;
	}
}
