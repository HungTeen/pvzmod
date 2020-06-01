package com.hungteen.pvzmod.entities.zombies.special;

import com.hungteen.pvzmod.entities.zombies.EntityDolphinRider;
import com.hungteen.pvzmod.entities.zombies.base.EntityWaterToolBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.init.Blocks;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityDolphin extends EntityWaterToolBase{

	public EntityDolphin(World worldIn) {
		super(worldIn);
		this.setSize(0.4f, 0.2f);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		if(!this.world.isRemote) {
			EntityDolphinRider rider=new EntityDolphinRider(world);
			rider.setPosition(posX, posY, posZ);
			this.world.spawnEntity(rider);
			rider.startRiding(this);
		}
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.FAST_SPEED);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.world.isRemote) {
			Material m1=this.world.getBlockState(new BlockPos(posX,posY-1,posZ)).getMaterial();
			Material m2=this.world.getBlockState(new BlockPos(posX,posY,posZ)).getMaterial();
			if(m1!=Material.AIR&&m2!=Material.WATER&&m2!=Material.AIR&&m1!=Material.WATER) {
				//if(this.ticksExisted%20==0) System.out.println(block);
			    for(Entity entity:this.getPassengers()) {
			    	entity.dismountRidingEntity();
			    }
			}
		}
		if(!this.world.isRemote&&this.ticksExisted%100==0) {
			EntityLivingBase target=this.getAttackTarget();
//			System.out.println("1");
			if(target!=null&&this.getDistanceSq(target)>=100) {
//				System.out.println("2");
				this.motionY=0.7;
				this.motionX*=4;
				this.motionZ*=4;
				this.world.playSound(null, posX, posY, posZ, SoundsHandler.DOLPHIN_JUMP, SoundCategory.VOICE, 1f,1f);
			}
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsHandler.DOLPHIN_AMBIENT;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.95f;
	}
	
	@Override
	public double getMountedYOffset() {
		return -0.65f;
	}
}
