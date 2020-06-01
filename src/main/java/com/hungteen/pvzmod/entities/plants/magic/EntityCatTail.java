package com.hungteen.pvzmod.entities.plants.magic;

import com.hungteen.pvzmod.entities.ai.EntityAIPlantAttackRanged;
import com.hungteen.pvzmod.entities.ai.EntityAISuperPeaShooter;
import com.hungteen.pvzmod.entities.ai.target.PVZAIPlantGlobalTarget;
import com.hungteen.pvzmod.entities.bullets.EntityThorn;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.base.EntityWaterPlantBase;
import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityCatTail extends EntityPlantBase implements IRangedAttackMob{
	
	public EntityCatTail(World worldIn) {
		super(worldIn);
		this.setSize(0.7f, 0.7f);
	}

	@Override
    protected void initEntityAI()
    {
		this.tasks.addTask(10, new EntityAIPlantAttackRanged(this, 0D, this.getShootSpeed(), 50.0F));
        this.tasks.addTask(10, new EntityAISuperPeaShooter(this,0D,2,40.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 1.50F));
        this.targetTasks.addTask(10, new PVZAIPlantGlobalTarget(this,30,50));
    }
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
    }
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(this.getAttackTime()>0) {
			if(!this.world.isRemote) {
				if(this.getAttackTime()%2==0) {
					this.startAttack();
				}
				this.setAttackTime(this.getAttackTime()-1);
			}
		}
	}
	
	protected void startAttack()
	{
		EntityLivingBase target=this.getAttackTarget();
		if(target==null) {
			//System.out.println("no target at all!");
			return ;
		}
		EntityThorn thorn = new EntityThorn(this.world,this);
		thorn.setPosition(this.posX,this.posY+1,this.posZ);
        this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        thorn.shoot();
        this.world.spawnEntity(thorn);
	}
	
	@Override
	protected boolean checkWeak() {
		if(this.isImmuneToWeak) return false;
		Entity entity =this.getRidingEntity();
		int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.posY);
        int k = MathHelper.floor(this.posZ);
    	BlockPos blockpos = new BlockPos(i, j, k);
    	IBlockState iblockstate = world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        return block!=BlockRegister.LILY_PAD;
	}
	
	public int getShootSpeed()
	{
		return 30;
	}
	
	@Override
	public float getAttackDamage() {
		int lvl=this.getPlantLvl();
		if(lvl<=18) {
			int now=(lvl-1)/2;
			return now+2;
		}
		else if(lvl<=20) return 20;
		return 2;
	}
	
	@Override
	public int getSuperTimeLength() {
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 200;
		else if(lvl<=13) return 250;
		else if(lvl<=20) return 300;
		return 200;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.CAT_TAIL;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		this.setAttackTime(4);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
	}
	
}
