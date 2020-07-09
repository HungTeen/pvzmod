package com.hungteen.pvzmod.entities.zombies.roof;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityImp extends EntityZombieBase{

	public EntityImp(World worldIn) {
		super(worldIn);
		this.setSize(0.5f, 1f);
	}

	@Override
	public boolean getIsInvulnerable() {
		return this.isRiding();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.BIT_FAST);
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.IMP;
	}

	@Override
	public float getLife() {
		return 10;
	}
	
}
