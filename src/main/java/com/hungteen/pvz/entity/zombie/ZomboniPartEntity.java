package com.hungteen.pvz.entity.zombie;

import com.hungteen.pvz.entity.zombie.poolday.ZomboniEntity;
import com.hungteen.pvz.register.EntityRegister;

import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ZomboniPartEntity extends PVZZombiePartEntity{

	private ZomboniEntity zomboni;
	
	public ZomboniPartEntity(EntityType<?> entityTypeIn, World world) {
		super(entityTypeIn, world);
	}
	
	public ZomboniPartEntity(ZomboniEntity owner, float sizeX, float sizeY) {
		super(EntityRegister.ZOMBONI_PART.get(), owner, sizeX, sizeY);
//		System.out.println(sizeX + " " + sizeY);
		this.zomboni = owner;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.ticksExisted % 20 == 0) {
			System.out.println(this.getWidth());
		}
		if(this.zomboni != null) {
			float j = 2 * 3.14159f * this.zomboni.rotationYawHead / 360;
			float dis = this.zomboni.getPartOffset();
			Vec3d pos = this.zomboni.getPositionVec();
			this.prevRotationYaw = this.rotationYaw;
			this.prevRotationPitch = this.rotationPitch;
			this.setLocationAndAngles(pos.getX() - Math.sin(j) * dis, pos.getY() + 0.1f, pos.getZ() + Math.cos(j) * dis, this.zomboni.rotationYaw, this.zomboni.rotationPitch);
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if(zomboni != null) {
			return zomboni.attackEntityFrom(source, damage);
		}
		return false;
	}
	
	@Override
	public boolean shouldNotExist() {
		return this.zomboni == null || !this.zomboni.isAlive();
	}

}
