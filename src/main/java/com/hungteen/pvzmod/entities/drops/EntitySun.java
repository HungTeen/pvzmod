package com.hungteen.pvzmod.entities.drops;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.util.PlayerUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySun extends EntityCreature implements IEntityAdditionalSpawnData{

	private int aliveTick;
	private int amount;
	
	public EntitySun(World worldIn)
	{
		super(worldIn);
		this.amount=25;
		this.aliveTick=0;
	}
	
	public EntitySun(World worldIn,int num)
	{
		super(worldIn);
		this.amount=num;
		this.aliveTick=0;
	}
	
	public EntitySun(World worldIn,double x,double y,double z,int num)
	{
		super(worldIn);
		this.setPosition(x, y, z);
		this.aliveTick=0;
		this.amount=num;
		//System.out.println("2:"+num+" "+this.amount);
	}
	
	@Override
	public void onLivingUpdate() {
		this.aliveTick++;
		//System.out.println(this.aliveTick+" "+this.posX+" "+this.posY+" "+this.posZ);
		if(this.aliveTick==200) {
			this.setDead();
		}
		if(!this.onGround) {
			this.motionY-=0.004f;
		}
		this.setSize(amount*1.0f/100, amount*1.0f/100);
		super.onLivingUpdate();
	}
	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("amount", this.amount);
        compound.setInteger("aliveTick", this.aliveTick);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compund)
    {
        super.readEntityFromNBT(compund);
        this.amount=compund.getInteger("amount");
        this.aliveTick=compund.getInteger("aliveTick");
    }
	
	@Override
    protected void initEntityAI(){
		
    }

    @Override
    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
    }
	
	
	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		if (!this.world.isRemote)
        {
//			ISun sun=player.getCapability(SunProvider.SUN_CAP, null);
//			sun.add(this.amount);
			PlayerUtil.addPlayerSunNum(player, this.amount);
			//System.out.println("dead");
            this.setDead();
            world.playSound(null, posX, posY, posZ, SoundsHandler.SUN_DIE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        }
	}
	
	public int getAmount()
	{
		return this.amount;
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

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(this.amount);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		this.amount=additionalData.readInt();
	}
    
}
