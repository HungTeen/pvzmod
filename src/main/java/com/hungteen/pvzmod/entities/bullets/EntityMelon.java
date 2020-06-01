package com.hungteen.pvzmod.entities.bullets;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.plants.base.EntityPultBase;
import com.hungteen.pvzmod.entities.plants.common.EntityMelonPult;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityMelon extends EntityPult{

	private static final DataParameter<Integer> MELON_STATE =EntityDataManager.createKey(EntityMelon.class, DataSerializers.VARINT);
	
	public EntityMelon(World worldIn) {
		super(worldIn);
		this.setSize(1.5f, 0.5f);
	}

	public EntityMelon(World world, EntityPultBase pult, Type down,State state) {
		super(world,pult,down);
		this.setMelonState(state);
		this.setSize(1.5f, 0.5f);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(MELON_STATE, State.NORMAL.ordinal());
	}
	
	@Override
	protected void performSpiltDamage() {
		if(this.shooter instanceof EntityMelonPult) {
			//System.out.println("yes");
			float damage=((EntityMelonPult) this.shooter).getSpiltDamage();
			float len=this.getSpiltDis();
			AxisAlignedBB aabb=new AxisAlignedBB(posX-len, posY-len, posZ-len, posX+len, posY+len, posZ+len);
			for(Entity target:EntityUtil.getEntityAttackableTarget(getThrower(), aabb)) {
//					if(!target.equals(((EntityMelonPult) this.shooter).getAttackTarget())) {
					    //System.out.println(target);
					    if(this.getMelonState()==State.ICE) {
					    	target.attackEntityFrom(PVZDamageSource.causeSnowDamage(this, this.shooter), damage);
					    }
					    else {
					    	target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.shooter), damage);
					    }
//					}
			}
			this.world.playSound(null, posX, posY, posZ, SoundsHandler.MELON_HIT, SoundCategory.AMBIENT, 1f,1f);
		}
	}
	
	private float getSpiltDis()
	{
		return 1.5f;//3*3
	}
	
	@Override
	protected void spawnDeathParticle() {
		for(int i=1;i<=20;i++) {
			this.world.spawnParticle(EnumParticleTypes.HEART, this.posX, this.posY, this.posZ, (this.rand.nextFloat()-0.5f), this.rand.nextFloat(), (this.rand.nextFloat()-0.5f));
		}
	}
	
	@Override
	protected EntityPult getPultBullet(EntityPultBase pult) {
		return new EntityMelon(world, pult, Type.DOWN,this.getMelonState());
	}
	
	protected float getAttackDamage()
	{
		float damage=0;
		if(this.shooter instanceof EntityMelonPult) 
			damage=((EntityMelonPult)this.shooter).getAttackDamage();
		if(this.getPultType()!=Type.NORMAL) damage+=20;
		return damage;
	}

	public State getMelonState()
	{
		return State.values()[dataManager.get(MELON_STATE)];
	}
	
	public void setMelonState(State state)
	{
		dataManager.set(MELON_STATE, state.ordinal());
	}
	
	public enum State{
		NORMAL,//ÆÕÍ¨Î÷¹Ï
		ICE,//±ùÎ÷¹Ï
	}
}
