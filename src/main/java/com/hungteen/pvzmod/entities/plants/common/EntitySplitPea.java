package com.hungteen.pvzmod.entities.plants.common;

import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.entities.plants.base.EntityShooterBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntitySplitPea extends EntityPeaShooter{

	protected final double LENGTH=0.8d;
	
	public EntitySplitPea(World worldIn) {
		super(worldIn);
	}

	@Override
	public void startShootAttack() {
		this.setAttackTime(2);
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
        double tmp=this.LENGTH/dis;
        double deltaX=tmp*dx;
        double deltaZ=tmp*dz;
        EntityPea entitypea = new EntityPea(this.world,this,EntityPea.Type.NORMAL,this.getShootState());
        entitypea.setPosition(this.posX+deltaX,this.posY+getEyeHeight(),this.posZ+deltaZ);
        double d0 = target.posY + (double)target.getEyeHeight() ;
        double d1 = target.posX - this.posX;
        double d2 = d0 - entitypea.posY;
        double d3 = target.posZ - this.posZ;
        entitypea.shoot(d1, d2, d3, this.getBulletSpeed(), 1.0F);
//        System.out.println(this.getAttackTime());
        if(this.getAttackTime()>1) {
        	this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
            this.world.spawnEntity(entitypea);
        }
        entitypea = new EntityPea(this.world,this,this.getShootType(),this.getShootState());
        entitypea.setPosition(this.posX-deltaX,this.posY+getEyeHeight(),this.posZ-deltaZ);
        entitypea.shoot(-d1, 0, -d3, this.getBulletSpeed(), 1.0F);      
        this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entitypea);
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
		if(lvl<=6) return 100;
		else if(lvl<=13) return 120;
		else if(lvl<=20) return 150;
		return 100;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.SPLIT_PEA;
	}
}
