package com.hungteen.pvzmod.entities.drops;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.util.PlayerUtil;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPlantFood extends EntityCreature{

	private int aliveTick;
	
	public EntityPlantFood(World worldIn) {
		super(worldIn);
		this.aliveTick=0;
		this.motionX=(this.getRNG().nextFloat()-0.5)/2;
		this.motionY=(this.getRNG().nextFloat()-0.5)/5;
		this.motionZ=(this.getRNG().nextFloat()-0.5)/2;
		this.setSize(0.2f,1.5f);
	}
	
	public EntityPlantFood(World worldIn,double x,double y,double z)
	{
		super(worldIn);
		this.setPosition(x, y, z);
		this.aliveTick=0;
		this.setSize(0.2f,1.5f);
	}
	
	@Override
	public void onEntityUpdate(){
		super.onEntityUpdate();
		this.aliveTick++;
		//System.out.println(this.aliveTick);
		if(this.aliveTick==400) {
			this.setDead();
		}
		if(this.aliveTick%10==0) {
			this.motionX=(this.getRNG().nextFloat()-0.5)/2;
			this.motionY=(this.getRNG().nextFloat()-0.5)/5;
			this.motionZ=(this.getRNG().nextFloat()-0.5)/2;
		}
	}
	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("aliveTick", this.aliveTick);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compund)
    {
        super.readEntityFromNBT(compund);
        this.aliveTick=compund.getInteger("aliveTick");
    }
    
	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		if(!this.world.isRemote) {
			PlayerUtil.addPlayerEnergyNum(player, 1);
			this.setDead();
		}
	}
	
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	@Override
    public boolean canBeHitWithPotion() {
        return false;
    }


    @Override
    public boolean getIsInvulnerable() {
        return true;
    }

    @Override
    protected SoundEvent getFallSound(int heightIn) {
        return null;
    }
    
    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return source != DamageSource.OUT_OF_WORLD;
    }
    
    
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }
    
    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }
}
