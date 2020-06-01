package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase.Type;
import com.hungteen.pvzmod.entities.zombies.normal.EntityNormalZombie;
import com.hungteen.pvzmod.entities.zombies.special.EntityNormalDefence;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBobsle extends EntityZombieBase implements IEntityMultiPart{

	public EntityNormalDefence car1 = new EntityNormalDefence(this, "car1", 1f, 1.3f);
	public EntityNormalDefence car2 = new EntityNormalDefence(this, "car2", 1f, 1.3f);
	
	public EntityBobsle(World worldIn) {
		super(worldIn);
		this.setSize(1f, 1.1f);
	}

	@Override
	protected Type getType() {
		return Type.NORMAL;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.FAST_SPEED);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.world.isRemote&&this.ticksExisted%20==0&&this.checkWeak()) {
			this.attackEntityFrom(PVZDamageSource.causeWeakDamage(this, this), 10);
		}
		this.car1.onUpdate();
		this.car2.onUpdate();
		float j=2*3.14159f*this.rotationYawHead/360;
		this.rotationYaw=this.rotationYawHead;
		//System.out.println(j+" "+MathHelper.sin(j));
		float dis1=this.getDefenceDis1();
		float dis2=this.getDefenceDis2();
		this.car1.setLocationAndAngles(this.posX-Math.sin(j)*dis1, this.posY, this.posZ+Math.cos(j)*dis1, this.rotationYaw, this.rotationPitch);
		this.car2.setLocationAndAngles(this.posX-Math.sin(j)*dis2, this.posY, this.posZ+Math.cos(j)*dis2, this.rotationYaw, this.rotationPitch);
	}
	
	@Override
	protected void onDeathUpdate() {
		super.onDeathUpdate();
		if(this.deathTime==19) {
			if(!this.world.isRemote) {
				for(int i=1;i<=4;i++) {
					EntityBobsleZombie zombie=new EntityBobsleZombie(world);
					zombie.setPosition(this.posX+this.getRNG().nextInt(5)-2, this.posY+1, this.posZ+this.getRNG().nextInt(5)-2);
					this.world.spawnEntity(zombie);
				}
			}
		}
	}
	
	@Override
	protected void setSmallSize() {
		super.setSmallSize();
		this.setSize(0.3f, 0.3f);
		this.car1.setSmallSize(0.3f, 0.4f);
		this.car2.setSmallSize(0.3f, 0.4f);
	}
	
	private float getDefenceDis2() {
		if(this.getIsSmall()) return -1f;
		return -2.5f;
	}

	private float getDefenceDis1() {
		if(this.getIsSmall()) return -0.5f;
		return -1.5f;
	}

	private boolean checkWeak()
	{
		if(this.world.getBlockState(new BlockPos(this.posX,this.posY-1,this.posZ)).getBlock()==Blocks.SNOW) return false;
		if(this.world.getBlockState(new BlockPos(this.posX,this.posY,this.posZ)).getBlock()==Blocks.SNOW_LAYER) return false;
		if(this.world.getBlockState(new BlockPos(this.posX,this.posY-1,this.posZ)).getMaterial()==Material.AIR) return false;
		return true;
	}
	
	@Override
	public World getWorld() {
		return this.world;
	}

	@Override
	public Entity[] getParts() {
		return new Entity[] {this.car1,this.car2};
	}
	
	@Override
	public boolean attackEntityFromPart(MultiPartEntityPart dragonPart, DamageSource source, float damage) {
		return this.attackEntityFrom(PVZDamageSource.causeNormalDamage(source.getImmediateSource(), source.getTrueSource()), damage);
	}
}
