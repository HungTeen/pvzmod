package com.hungteen.pvz.entity.plant.appease;

import com.hungteen.pvz.entity.bullet.itembullet.PeaEntity;
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
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.9f, 1.7f, false);
	}
	
	@Override
	public void shootBullet() {
		LivingEntity target=this.getTarget();
		if(target==null) {
			//System.out.println("no target at all!");
			return ;
		}
		double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        double y = this.getY() + this.getDimensions(getPose()).height * 0.7f;
        double dis = MathHelper.sqrt(dx * dx + dz * dz);
        double tmp= this.LENTH / dis;
        double deltaX = tmp * dx;
        double deltaZ = tmp * dz;
        //shoot mid pea
        PeaEntity pea = new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
        pea.setPos(this.getX() + deltaX, y, this.getZ() + deltaZ);
        pea.shootPea(dx, target.getY() + target.getBbHeight() - y, dz, this.getBulletSpeed());      
        this.level.addFreshEntity(pea);
        //shoot left pea
        double now = DIS / dis;
        double deltaXX = now * dz;
        double deltaZZ = -now * dx;
        pea = new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
        pea.setPos(this.getX() + deltaX + deltaXX, this.getY() + this.getDimensions(getPose()).height * 0.5f, this.getZ() + deltaZ + deltaZZ);
        pea.shootPea(dx, target.getY() + target.getBbHeight() - y, dz, this.getBulletSpeed());     
        this.level.addFreshEntity(pea);
        //shoot right pea
        pea = new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
        pea.setPos(this.getX() + deltaX - deltaXX, this.getY() + this.getDimensions(getPose()).height * 0.5f, this.getZ() + deltaZ - deltaZZ);
        pea.shootPea(dx, target.getY() + target.getBbHeight() - y, dz, this.getBulletSpeed());     
        this.level.addFreshEntity(pea);
        EntityUtil.playSound(this, this.getShootSound());
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.THREE_PEATER;
	}
}
