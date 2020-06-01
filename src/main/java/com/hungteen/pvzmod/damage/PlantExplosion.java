package com.hungteen.pvzmod.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class PlantExplosion {

	private double range;
	private float damage;
	private double x;
	private double y;
	private double z;
	private World world;
	PVZDamageSource src;
	Class<? extends EntityLivingBase> entityClass;
	
	public PlantExplosion(World world,Class<? extends EntityLivingBase> entityClass,double range,float damage,double x,double y,double z) {
		this.world=world;
		this.entityClass=entityClass;
		this.range=range;
		this.damage=damage;
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public void setSource(PVZDamageSource src) {
		this.src=src;
	}
	
	public void doExplosion()
	{
		AxisAlignedBB aabb=new AxisAlignedBB(x-range, y-10, z-range, x+range, y+20, z+range);
		for(EntityLivingBase target:this.world.getEntitiesWithinAABB(entityClass,aabb)) {
			target.attackEntityFrom(src, damage);
		}
	}
}
