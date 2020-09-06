package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.PVZZombiePartEntity;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class ZomboniEntity extends PVZZombieEntity{

	private final PVZZombiePartEntity car = new PVZZombiePartEntity(this, world, "car", 1.2f, 1.5f);
	
	public ZomboniEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		this.car.prevPosX = this.getPosX();
        this.car.prevPosY = this.getPosY();
        this.car.prevPosZ = this.getPosZ();
        this.car.lastTickPosX = this.getPosX();
        this.car.lastTickPosY = this.getPosY();
        this.car.lastTickPosZ = this.getPosZ();
        float j=2*3.14159f*this.rotationYawHead/360;
		this.rotationYaw=this.rotationYawHead;
		//System.out.println(j+" "+MathHelper.sin(j));
		float dis=this.getDefenceDis();
		this.car.setLocationAndAngles(this.getPosX()-Math.sin(j)*dis, this.getPosY()+0.2f, this.getPosZ()+Math.cos(j)*dis, this.rotationYaw, this.rotationPitch);
	}

	protected float getDefenceDis()
	{
//		if(this.getIsSmall()) return 0.5f;
		return 1.7f;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(1f, 2f);
	}
	
	@Override
	public float getLife() {
		return 120;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.ZOMBONI;
	}

}
