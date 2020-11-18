package com.hungteen.pvz.entity.plant.appease;

import com.hungteen.pvz.entity.bullet.PeaEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ThreePeaterEntity extends PeaShooterEntity {

	private final double DIS = 0.6d;
	
	public ThreePeaterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.9f, 1.7f, false);
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
        double y = this.getPosY() + this.getSize(getPose()).height * 0.7f;
        double dis = MathHelper.sqrt(dx * dx + dz * dz);
        double tmp= this.LENTH / dis;
        double deltaX = tmp * dx;
        double deltaZ = tmp * dz;
        //shoot mid pea
        PeaEntity pea = new PeaEntity(EntityRegister.PEA.get(), this.world, this, this.getShootType(), this.getShootState());
        pea.setPosition(this.getPosX() + deltaX, y, this.getPosZ() + deltaZ);
        pea.shootPea(dx, target.getPosY() + target.getHeight() - y, dz, this.getBulletSpeed());      
        this.world.addEntity(pea);
        //shoot left pea
        double now = DIS / dis;
        double deltaXX = now * dz;
        double deltaZZ = -now * dx;
        pea = new PeaEntity(EntityRegister.PEA.get(), this.world, this, this.getShootType(), this.getShootState());
        pea.setPosition(this.getPosX() + deltaX + deltaXX, this.getPosY() + this.getSize(getPose()).height * 0.5f, this.getPosZ() + deltaZ + deltaZZ);
        pea.shootPea(dx, target.getPosY() + target.getHeight() - y, dz, this.getBulletSpeed());     
        this.world.addEntity(pea);
        //shoot right pea
        pea = new PeaEntity(EntityRegister.PEA.get(), this.world, this, this.getShootType(), this.getShootState());
        pea.setPosition(this.getPosX() + deltaX - deltaXX, this.getPosY() + this.getSize(getPose()).height * 0.5f, this.getPosZ() + deltaZ - deltaZZ);
        pea.shootPea(dx, target.getPosY() + target.getHeight() - y, dz, this.getBulletSpeed());     
        this.world.addEntity(pea);
        EntityUtil.playSound(this, this.getShootSound());
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.THREE_PEATER;
	}
}
