package com.hungteen.pvzmod.entities.drops;

import java.util.Random;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.entities.ai.EntityAIDestroyBlocks;
import com.hungteen.pvzmod.entities.ai.EntityAIZombieEat;
import com.hungteen.pvzmod.util.PlayerUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityCoin extends EntityCreature implements IEntityAdditionalSpawnData{

	protected int aliveTick;
	private int type;
	
	public EntityCoin(World worldIn) {
		super(worldIn);
		this.aliveTick=0;
		Random random=new Random();
		this.type=random.nextInt(4);
		this.setSize(0.3f, 0.3f);
	}

	@Override
	protected void initEntityAI()
    {
        this.tasks.addTask(4, new EntityAISwimming(this));
    }
	
	public EntityCoin(World worldIn,int type) {
		super(worldIn);
		this.aliveTick=0;
		this.type=type;
		this.setSize(0.3f, 0.3f);
	}

	public EntityCoin(World worldIn,double x,double y,double z,int type)
	{
		super(worldIn);
		this.setPosition(x, y, z);
		this.aliveTick=0;
		this.type=type;
	}
	
	@Override
	public void onLivingUpdate() {
		this.aliveTick++;
		if(this.aliveTick==300) {
			this.setDead();
		}
		super.onLivingUpdate();
	}
	
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("aliveTick", this.aliveTick);
        compound.setInteger("CoinType", this.type);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compund)
    {
        super.readEntityFromNBT(compund);
        this.aliveTick=compund.getInteger("aliveTick");
        this.type=compund.getInteger("CoinType");
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
			PlayerUtil.addPlayerMoney(player, this.getMoney());
            this.setDead();
            if(this.getCoinType()==CoinType.JEWEL) {
            	world.playSound(null, posX, posY, posZ, SoundsHandler.GET_JEWEL, SoundCategory.NEUTRAL, 4.0f, 1.0f);
            }
            else {
            	world.playSound(null, posX, posY, posZ, SoundsHandler.GET_COIN, SoundCategory.NEUTRAL, 4.0f, 1.0f);
            }
        }
	}
	
	public CoinType getCoinType()
	{
		return CoinType.values()[this.type];
	}
	
	public int getMoney()
	{
		if(this.getCoinType()==CoinType.COPPER) return 1;
		else if(this.getCoinType()==CoinType.SILVER) return 10;
		else if(this.getCoinType()==CoinType.GOLD) return 100;
		else if(this.getCoinType()==CoinType.JEWEL) return 1000;
		return 1;
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
		buffer.writeInt(this.aliveTick);
		buffer.writeInt(this.type);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		this.aliveTick=additionalData.readInt();
		this.type=additionalData.readInt();
	}
	
	public enum CoinType{
		COPPER,
		SILVER,
		GOLD,
		JEWEL
	}
}
