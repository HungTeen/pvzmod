package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public abstract class PVZItemBulletEntity extends AbstractBulletEntity implements IRendersAsItem {

	public PVZItemBulletEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}

	public PVZItemBulletEntity(EntityType<?> type, World worldIn, LivingEntity shooter) {
		super(type, worldIn, shooter);
	}

	/**
	 * shoot bullet such as pea or spore
	 */
	public void shootPea(double dx, double dy, double dz, double speed) {
		double down = this.getShootPeaAngle();
		double dxz = Math.sqrt(dx * dx + dz * dz);
		dy = MathHelper.clamp(dy, - dxz / down, dxz / down);
		double dis = Math.sqrt(dx * dx + dy * dy + dz * dz);
		double vx = dx / dis * speed;
		double vy = dy / dis * speed;
		double vz = dz / dis * speed;
		this.setMotion(vx, vy, vz);
	}
	
	public void shootPeaAnti(double dx, double dy, double dz, double speed) {
		double down = this.getShootPeaAngle();
		double dxz = Math.sqrt(dx * dx + dz * dz);
		dy = MathHelper.clamp(dy, -dxz / down, dxz / down);
		double dis = Math.sqrt(dx * dx + dy * dy + dz * dz);
		double vx = dx / dis * speed;
		double vy = dy / dis * speed;
		double vz = dz / dis * speed;
		this.setMotion(- vx, - vy, - vz);
	}

	/**
	 * get how much angle can shoot by thrower
	 */
	protected double getShootPeaAngle() {
		if (this.getThrower() instanceof PlantShooterEntity) {
			return ((PlantShooterEntity) this.getThrower()).getMaxShootAngle();
		}
		return 10;
	}

}
