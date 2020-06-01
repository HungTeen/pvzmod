package com.hungteen.pvzmod.entities.plants.common;

import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.entities.plants.base.EntityShooterBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityThreePeater extends EntityPeaShooter{

	protected final double LENGTH=0.7d;
	public EntityThreePeater(World worldIn) {
		super(worldIn);
		this.setSize(0.6f, 1.5f);
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
        double d0 = target.posY + (double)target.getEyeHeight() ;
        double d1 = target.posX - this.posX;
        double d3 = target.posZ - this.posZ;
        //mid pea
		EntityPea entitypea = new EntityPea(this.world,this,this.getShootType(),this.getShootState());
        entitypea.setPosition(this.posX+deltaX,this.posY+getEyeHeight(),this.posZ+deltaZ);
        double d2 = d0 - entitypea.posY;
        entitypea.shoot(d1, d2, d3, this.getBulletSpeed(), 1.0F);
        this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entitypea);
        
        tmp=this.LENGTH/dis;
        double deltaXX=tmp*dz;
        double deltaZZ=tmp*dx;
        // left pea
        entitypea = new EntityPea(this.world,this,this.getShootType(),this.getShootState());
        entitypea.setPosition(this.posX+deltaX+deltaXX,this.posY+getEyeHeight()-0.2f,this.posZ+deltaZ+deltaZZ);
        entitypea.shoot(d1, d2, d3, this.getBulletSpeed(), 1.0F);
        this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entitypea);
        //right pea
        entitypea = new EntityPea(this.world,this,this.getShootType(),this.getShootState());
        entitypea.setPosition(this.posX+deltaX-deltaXX,this.posY+getEyeHeight()-0.2f,this.posZ+deltaZ-deltaZZ);
        entitypea.shoot(d1, d2, d3, this.getBulletSpeed(), 1.0F);
        this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entitypea);
	}
	
	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 100;
		if(lvl<=13) return 140;
		if(lvl<=20) return 200;
		return 100;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.THREE_PEATER;
	}
}
