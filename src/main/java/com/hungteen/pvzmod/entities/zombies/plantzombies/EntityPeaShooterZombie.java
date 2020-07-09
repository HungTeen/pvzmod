package com.hungteen.pvzmod.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityPeaShooterZombie extends EntityNormalZombie{

	private static final DataParameter<Integer> ATTACK_TIME =EntityDataManager.createKey(EntityPeaShooterZombie.class, DataSerializers.VARINT);
	private final int MAXTIME=30;
	private final float LENTH=0.4f;
	
	public EntityPeaShooterZombie(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(ATTACK_TIME, 0);
	}
	
	@Override
	public void onNormalZombieUpdate() {
		super.onNormalZombieUpdate();
		if(this.getAttackTime()+this.getShootNum()>MAXTIME) {
			this.shoot();
		}
		if(this.getAttackTime()>0) {
			this.setAttackTime(this.getAttackTime()-1);
		}
		else {
			this.setAttackTime(MAXTIME);
		}
	}
	
	protected int getShootNum()
	{
		return 1;
	}
	
	protected void shoot()
	{
		EntityLivingBase target =this.getAttackTarget();
		if(target!=null&&!this.world.isRemote) {
			double dx = target.posX - this.posX;
	        double dz = target.posZ - this.posZ;
	        double dis =MathHelper.sqrt(dx * dx + dz * dz);
	        double tmp=this.LENTH/dis;
	        double deltaX=tmp*dx;
	        double deltaZ=tmp*dz;
	        EntityPea pea=new EntityPea(this.world,this,EntityPea.Type.NORMAL,EntityPea.State.NORMAL);
	        pea.setPosition(this.posX+deltaX,this.posY+getEyeHeight(),this.posZ+deltaZ);
	        double d0 = target.posY + (double)target.getEyeHeight() ;
	        double d1 = target.posX - this.posX;
	        double d2 = d0 - pea.posY;
	        double d3 = target.posZ - this.posZ;
	        pea.shoot(d1, d2, d3, this.getShootSpeed(), 1.0F);      
	        this.world.spawnEntity(pea);
		}
	}
	
	protected float getShootSpeed()
	{
		return 1.3f;
	}
	
	public int getAttackTime()
	{
		return dataManager.get(ATTACK_TIME);
	}
	
	public void setAttackTime(int time)
	{
		dataManager.set(ATTACK_TIME, time);
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.PEASHOOTER_ZOMBIE;
	}

	@Override
	public float getLife() {
		return 20;
	}
}
