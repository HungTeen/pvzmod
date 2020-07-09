package com.hungteen.pvzmod.entities.zombies.grassnight;

import com.hungteen.pvzmod.entities.ai.EntityAIDestroyBlocks;
import com.hungteen.pvzmod.entities.ai.EntityAIZombieEat;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase.Type;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityTombStone extends EntityZombieBase{

	public EntityTombStone(World worldIn) {
		super(worldIn);
	}

	@Override
	protected Type getType() {
		return Type.NORMAL;
	}
	
	@Override
	protected void initEntityAI()
    {
    }
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(0);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0f);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
	}
	
	@Override
	public boolean getCanBeInvis() {
		return false;
	}
	
	@Override
	public boolean getCanBeSmall() {
		return false;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TOMB_STONE;
	}

	@Override
	public float getLife() {
		return 70;
	}
}
