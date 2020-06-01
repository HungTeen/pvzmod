package com.hungteen.pvzmod.entities.bullets;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.bullets.EntityPult.Type;
import com.hungteen.pvzmod.entities.plants.base.EntityPultBase;
import com.hungteen.pvzmod.entities.plants.common.EntityCabbagePult;
import com.hungteen.pvzmod.particles.base.PVZParticleType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPult extends PVZThrowable{

    private static final DataParameter<Integer> PULT_TYPE =EntityDataManager.createKey(EntityPult.class, DataSerializers.VARINT);
	
	public EntityPult(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(PULT_TYPE, Type.NORMAL.ordinal());
	}
	
	public EntityPult(World worldIn, EntityLivingBase throwerIn,Type type)
    {
        super(worldIn, throwerIn);
        this.setPultType(type);
    }
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.getPultType()==Type.EXPLODE&&this.motionY<0) {
			//System.out.println("here we go!");
			this.explode();
		}
		if(this.getPultType()!=Type.NORMAL) {
			for (int i = 0; i < 3; ++i)
            {
				Main.proxy.spawnParticle(PVZParticleType.SPEED_PEA_TWO, this.posX, this.posY, this.posZ,0,0,0);
            }
		}
		this.motionX/=0.99f;
		this.motionY/=0.99f;
		this.motionZ/=0.99f;
	}
	
	@Override
    public void shoot(double dx,double dy,double dz){
    	if(this.getPultType()==Type.EXPLODE) {
    		this.motionY=2;
    		return ;
    	}
    	super.shoot(dx, dy, dz);
    }
    
    protected void explode()
    {
    	if(this.getThrower() instanceof EntityPultBase) {
    		if(!this.world.isRemote) {
    			EntityPultBase pult=(EntityPultBase) this.getThrower();
    		    int r=pult.getRange();
    		    AxisAlignedBB aabb=new AxisAlignedBB(pult.posX-r,pult.posY-5,pult.posZ-r,pult.posX+r,pult.posY+20,pult.posZ+r);
    		    for(EntityLivingBase target:pult.world.getEntitiesWithinAABB(EntityMob.class, aabb)) {
    			    EntityPult cabbage=this.getPultBullet(pult);
    			    cabbage.setPosition(this.posX, this.posY, this.posZ);
    			    cabbage.shoot1(target);
    			    this.world.spawnEntity(cabbage);
    		    }
    		}
		}
		else {
			System.out.println("wrong thrower!");
			return ;
		}
    	this.setDead();
    }
    
    protected abstract EntityPult getPultBullet(EntityPultBase pult);
    
    protected void shoot1(EntityLivingBase target)
    {
    	double dx=target.posX-this.posX;
    	double dy=target.posY-this.posY;
    	double dz=target.posZ-this.posZ;
    	double dis=Math.sqrt(dx*dx+dy*dy+dz*dz);
    	this.motionX=dx/dis*5;
    	this.motionY=dy/dis*5;
    	this.motionZ=dz/dis*5;
    }
    
	@Override
	protected void onImpact(RayTraceResult result) {
		Entity target=result.entityHit;
        if (checkCanAttack(target))
        {
        	this.performAttack(target);
        }
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            if(!this.checkLive(result)) {
            	performSpiltDamage();
            	//System.out.println(result.entityHit);
            	this.setDead();
            }
        }else {
        	if(!this.checkLive(result)) {
        		spawnDeathParticle();
        	}
        }
	}
	
	protected void performSpiltDamage()
	{
		
	}
	
	protected void spawnDeathParticle()
	{
		
	}

	protected void performAttack(Entity target)
	{
		target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.getThrower()), this.getAttackDamage());//damage
	}
	
	protected abstract float getAttackDamage();

    @Override
	protected float getGravityVelocity()
    {
    	if(this.getPultType()==Type.DOWN) return 0;
        return 0.1F;
    }
    
    public Type getPultType()
	{
		return Type.values()[dataManager.get(PULT_TYPE)];
	}
	
	public void setPultType(Type type)
	{
		dataManager.set(PULT_TYPE, type.ordinal());
	}
	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("pult_type", this.getPultType().ordinal());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compund)
    {
        super.readEntityFromNBT(compund);
        this.setPultType(Type.values()[compund.getInteger("pult_type")]);
    }
    
	public enum Type{
		NORMAL,//普通
		EXPLODE,//爆炸
		DOWN,//爆炸分裂出来的
	}
}
