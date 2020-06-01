package com.hungteen.pvzmod.entities.plants.common;

import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityDoubleShooter extends EntityPeaShooter{
	
	public EntityDoubleShooter(World worldIn) {
		super(worldIn);
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(2);
	}
	
	@Override
	protected EntityPea.Type getShootType()
	{
		if(this.isPlantInSuperMode()&&!this.getIsSuperOut()) {
			this.setIsSuperOut(true);
			return EntityPea.Type.BIG;
		}
		return EntityPea.Type.NORMAL;
	}
	
	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 120;
		else if(lvl<=13) return 140;
		else if(lvl<=20) return 175;
		return 120;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.DOUBLE_SHOOTER;
	}
}
