package com.hungteen.pvzmod.entities.plants.common;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.entities.ai.EntityAIPlantAttackRanged;
import com.hungteen.pvzmod.entities.ai.EntityAISuperPeaShooter;
import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.entities.plants.base.EntityShooterBase;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import ibxm.Player;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityPeaShooter extends EntityShooterBase {

	protected final double LENTH=0.5d;//豌豆生成位置参数
	
	public EntityPeaShooter(World worldIn) {
		super(worldIn);
		this.setSize(0.8f, 1.5f);
	}
	
	public void startShootAttack()
    {
        this.setAttackTime(1);
    }
	
	@Override
	public void shootBullet() {
		EntityLivingBase target=this.getAttackTarget();
		if(target==null) {
			//System.out.println("no target at all!");
			return ;
		}
		double dx = target.posX - this.posX;
        double dz = target.posZ - this.posZ;
        double dis =MathHelper.sqrt(dx * dx + dz * dz);
        double tmp=this.LENTH/dis;
        double deltaX=tmp*dx;
        double deltaZ=tmp*dz;
        EntityPea entitypea = new EntityPea(this.world,this,this.getShootType(),this.getShootState());
        entitypea.setPosition(this.posX+deltaX,this.posY+getEyeHeight(),this.posZ+deltaZ);
        double d0 = target.posY + (double)target.getEyeHeight() ;
        double d1 = target.posX - this.posX;
        double d2 = d0 - entitypea.posY;
        double d3 = target.posZ - this.posZ;
        entitypea.shoot(d1, d2, d3, this.getBulletSpeed(), 1.0F);      
        this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entitypea);
	}
	
	protected EntityPea.Type getShootType()
	{
		return EntityPea.Type.NORMAL;
	}
	
	protected EntityPea.State getShootState()
	{
		return EntityPea.State.NORMAL;
	}

	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 100;
		else if(lvl<=13) return 120;
		else if(lvl<=20) return 150;
		return 100;
	}
    
	@Override
	public Plants getPlantEnumName() {
		return Plants.PEA_SHOOTER;
	}
}
