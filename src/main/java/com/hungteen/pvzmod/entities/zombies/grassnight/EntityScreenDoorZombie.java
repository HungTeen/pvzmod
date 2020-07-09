package com.hungteen.pvzmod.entities.zombies.grassnight;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.entities.zombies.special.EntityNormalDefence;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import scala.reflect.internal.Trees.This;

public class EntityScreenDoorZombie extends EntityNormalZombie implements IEntityMultiPart{

	public EntityNormalDefence door = new EntityNormalDefence(this, "door", 0.7f, 1.75f);
	
	public EntityScreenDoorZombie(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.3f, 0.4f);
		this.door.setSmallSize(0.3f, 0.5f);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		this.door.onUpdate();
		float j=2*3.14159f*this.rotationYaw/360;
		//System.out.println(j+" "+MathHelper.sin(j));
		float dis=this.getDefenceDis();
		this.door.setLocationAndAngles(this.posX-Math.sin(j)*dis, this.posY, this.posZ+Math.cos(j)*dis, this.rotationYaw, this.rotationPitch);
	}
	
	protected float getDefenceDis()
	{
		if(this.getIsSmall()) return 0.2f;
		else return 0.6f;
	}

	@Override
	public World getWorld() {
		return this.world;
	}
	
	@Override
	public Entity[] getParts() {
		return new Entity[] {this.door};
	}

	@Override
	public boolean attackEntityFromPart(MultiPartEntityPart dragonPart, DamageSource source, float damage) {
		return this.attackEntityFrom(PVZDamageSource.causeNormalDamage(source.getImmediateSource(), source.getTrueSource()), damage/10f);
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundsHandler.METAL_HURT;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SCREENDOOR_ZOMBIE;
	}
}
