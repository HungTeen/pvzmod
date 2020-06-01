package com.hungteen.pvzmod.entities.plants.fight;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.damage.PVZDamageType;
import com.hungteen.pvzmod.entities.ai.target.PVZAIPlantGlobalTarget;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.walkers.EntityTag;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityTangleKelp extends EntityPlantBase{

	public EntityTangleKelp(World worldIn) {
		super(worldIn);
		setSize(0.8f,1f);
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(10, new PVZAIPlantGlobalTarget(this, 4, 1));
	}
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(!this.world.isRemote&&this.getAttackTime()>0) {
			this.setAttackTime(this.getAttackTime()+1);
			if(this.getPassengers().size()==0) {
				this.setDead();
				return ;
			}
			Entity target=this.getPassengers().get(0);
			this.motionY=-0.3;
		    if(this.getAttackTime()==100) {
			    target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this), this.getAttackDamage());
			}
		    else if(this.getAttackTime()==2000) {
		    	this.setDead();
		    }
		}
		else if(!this.world.isRemote&&this.getAttackTime()==0) {
			if(this.getAttackTarget()!=null) {
				EntityLivingBase target=this.getAttackTarget();
				this.setAttackTime(1);
				target.startRiding(this);
			}
		}
		if(this.isPlantInSuperMode()) {
			if(!this.world.isRemote) {
				AxisAlignedBB aabb=new AxisAlignedBB(posX-25, posY-3, posZ-25, posX+25, posY+3, posZ+25);
				int cnt=this.getCount();
				for(Entity target:this.world.getEntitiesWithinAABB(EntityMob.class, aabb)) {
					if(cnt<=0) break;
					if(EntityUtil.checkCanEntityAttack(this,target)) {
					    if(this.world.getBlockState(new BlockPos(target.posX,target.posY-1,target.posZ)).getBlock()==Blocks.WATER) {
						    cnt--;
						    EntityTangleKelp kelp=new EntityTangleKelp(world);
						    kelp.setPosition(target.posX, target.posY-1, target.posZ);
						    world.spawnEntity(kelp);
					    }
					}
				}
			}
		}
	}
	
	@Override
	public double getMountedYOffset() {
		return 0;
	}
	
	private int getCount()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 3;
		else if(lvl<=13) return 4;
		else if(lvl<=20) return 5;
		return 3;
	}
	
	@Override
	protected boolean checkWeak()//检测是否应该因为离开水而虚弱
	{
		if(this.isImmuneToWeak) return false;
		Entity entity =this.getRidingEntity();
    	Block block1 =this.world.getBlockState(new BlockPos(this.posX,this.posY,this.posZ)).getBlock();
    	Block block2 =this.world.getBlockState(new BlockPos(this.posX,this.posY-1,this.posZ)).getBlock();
        if(block2==Blocks.WATER||block1==Blocks.WATER) {
        	return false;
        }
        return true;
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		if(source instanceof PVZDamageSource) {
			if(((PVZDamageSource) source).getPVZDamageType()==PVZDamageType.EAT) return true;
		}
		return super.isEntityInvulnerable(source);
	}
	
	@Override
	public boolean hasNoGravity() {
		return this.isInWater();
	}

	@Override
	public float getAttackDamage() {
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 50+10*now;
		}
		return 50;
	}

	@Override
	public int getSuperTimeLength() {
		return 2;
	}

	@Override
	public boolean shouldDismountInWater(Entity rider) {
		return false;
	}
	
	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.TANGLE_KELP;
	}

}
