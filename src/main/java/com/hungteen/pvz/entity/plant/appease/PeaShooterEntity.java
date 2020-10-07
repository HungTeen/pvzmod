package com.hungteen.pvz.entity.plant.appease;

import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.PeaEntity;
import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class PeaShooterEntity extends PlantShooterEntity{

	protected final double LENTH=0.2d;//pea position offset
	
	public PeaShooterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getShootRange(), 2, 0));
	}
	
	@Override
	public void shootBullet() {
		LivingEntity target=this.getAttackTarget();
		if(target==null) {
			//System.out.println("no target at all!");
			return ;
		}
		double dx = target.getPosX() - this.getPosX();
        double dz = target.getPosZ() - this.getPosZ();
        double y = this.getPosY()+this.getSize(getPose()).height*0.7f;
        double dis =MathHelper.sqrt(dx * dx + dz * dz);
        double tmp=this.LENTH/dis;
        double deltaX=tmp*dx;
        double deltaZ=tmp*dz;
        PeaEntity pea = new PeaEntity(EntityRegister.PEA.get(),this.world,this,this.getShootType(),this.getShootState());
        pea.setPosition(this.getPosX()+deltaX,y,this.getPosZ()+deltaZ);
        pea.shootPea(dx, target.getPosY()+target.getHeight()-y, dz, this.getBulletSpeed());      
        this.playSound(getShootSound(), 1.0F, 1.0F);
        this.world.addEntity(pea);
	}
	
	protected SoundEvent getShootSound() {
		return SoundEvents.ENTITY_SNOW_GOLEM_SHOOT;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.8f, 1.5f, false);
	}

	protected PeaEntity.Type getShootType(){
		return PeaEntity.Type.NORMAL;
	}
	
	protected PeaEntity.State getShootState(){
		return PeaEntity.State.NORMAL;
	}
	
	@Override
	public float getBulletSpeed() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 1.2f;
		else if(lvl<=13) return 1.6f;
		else if(lvl<=20) return 2.2f;
		return 1.2f;
	}

	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
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
