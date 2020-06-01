package com.hungteen.pvzmod.entities.plants.common;

import com.hungteen.pvzmod.entities.ai.attack.PVZAIPultAttack;
import com.hungteen.pvzmod.entities.bullets.EntityCabbage;
import com.hungteen.pvzmod.entities.bullets.EntityPult;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.base.EntityPultBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityCabbagePult extends EntityPultBase{

	public EntityCabbagePult(World worldIn){
		super(worldIn);
		this.setSize(0.75f, 0.65f);
	}
	
	@Override
	protected EntityPult getBullet() {
        EntityCabbage.Type type=EntityCabbage.Type.NORMAL;
        if(this.isPlantInSuperMode()&&!this.getIsSuperOut()) {
        	type=EntityCabbage.Type.EXPLODE;
        	this.setIsSuperOut(true);
        }
        return new EntityCabbage(this.world,this,type);
	}
	
	@Override
	public int getPultSpeed()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 60-5*now;
		}
		return 60;
	}
	
	@Override
	public float getAttackDamage()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=19) return lvl*0.5f+3.5f;
		else if(lvl<=20) return 14;
		return 4;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.CABBAGE_PULT;
	}
}
