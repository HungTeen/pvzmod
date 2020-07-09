package com.hungteen.pvzmod.entities.plants.fight;

import java.util.List;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.damage.PVZDamageType;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityZomboni;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntitySpikeRock extends EntityPlantBase{

	private static final DataParameter<Integer> SPIKE_NUM = EntityDataManager.createKey(EntitySpikeRock.class, DataSerializers.VARINT);
	
	public EntitySpikeRock(World worldIn) {
		super(worldIn);
		this.setSize(0.9f, 0.3f);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SPIKE_NUM,0);
	}
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(!this.world.isRemote&&this.isPlantInSuperMode()) {
			float r=this.getSuperRange();
			AxisAlignedBB aabb=new AxisAlignedBB(this.posX-r, this.posY, this.posZ-r, this.posX+r, this.posY+0.5, this.posZ+r);
			for(Entity entity :EntityUtil.getEntityAttackableTarget(this, aabb)) {
				entity.setPosition(this.posX, this.posY, this.posZ);
			}
		}
	}
	
	public float getSuperRange()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 7.5f;
		else if(lvl<=13) return 10;
		else if(lvl<=20) return 15;
		return 7.5f;
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn)
    {
        if (!this.isRidingSameEntity(entityIn))
        {
            if (!entityIn.noClip && !this.noClip)
            {
                double d0 = entityIn.posX - this.posX;
                double d1 = entityIn.posZ - this.posZ;
                double d2 = MathHelper.absMax(d0, d1);
                if (d2 >= 0.009999999776482582D)
                {
                    d2 = (double)MathHelper.sqrt(d2);
                    d0 = d0 / d2;
                    d1 = d1 / d2;
                    double d3 = 1.0D / d2;

                    if (d3 > 1.0D)
                    {
                        d3 = 1.0D;
                    }

                    d0 = d0 * d3;
                    d1 = d1 * d3;
                    d0 = d0 * 0.05000000074505806D;
                    d1 = d1 * 0.05000000074505806D;
                    d0 = d0 * (double)(1.0F - this.entityCollisionReduction);
                    d1 = d1 * (double)(1.0F - this.entityCollisionReduction);

                    if (!this.isBeingRidden())
                    {
                    	if(!(entityIn instanceof EntityZombieBase))
                        this.addVelocity(-d0, 0.0D, -d1);
                    }

                    if (!entityIn.isBeingRidden())
                    {
                        entityIn.addVelocity(d0, 0.0D, d1);
                    }
                }
                else {
                	//System.out.println(entityIn);
                	this.addVelocity(this.getRNG().nextFloat()-0.5f, 0, this.getRNG().nextFloat()-0.5f);
                	entityIn.addVelocity(this.getRNG().nextFloat()-0.5f, 0, this.getRNG().nextFloat()-0.5f);
                }
            }
        }
    }
	
	@Override
	protected void collideWithNearbyEntities() {
		List<Entity> list = this.world.getEntitiesWithinAABB(EntityLiving.class, this.getEntityBoundingBox());
				//(this, this.getEntityBoundingBox(),EntitySelectors.getTeamCollisionPredicate(this));
        
		if (!list.isEmpty())
        {
            int i = this.world.getGameRules().getInt("maxEntityCramming");

            if (i > 0 && list.size() > i - 1 && this.rand.nextInt(4) == 0)
            {
                int j = 0;

                for (int k = 0; k < list.size(); ++k)
                {
                    if (!((Entity)list.get(k)).isRiding())
                    {
                        ++j;
                    }
                }

                if (j > i - 1)
                {
                    this.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
                }
            }

            for (int l = 0; l < list.size(); ++l)
            {
                Entity entity = list.get(l);
                //System.out.println(entity);
                if(entity instanceof EntityPlantBase) {
                    this.collideWithEntity(entity);
                }
                else {
                	this.spikeNormalAttack(entity);
                }
            }
        }
	}
	
	private void spikeNormalAttack(Entity entity)
	{
		if(this.world.isRemote) return ;
    	if(this.getAttackTime()<this.getAttackCD()) {
    		this.setAttackTime(this.getAttackTime()+1);
    		return ;
    	}
    	this.setAttackTime(0);
    	if(EntityUtil.checkCanEntityAttack(this,entity)) {
    		if(entity instanceof EntityZomboni&&((EntityZomboni) entity).getHealth()>0) {//攻击的目标要破尖刺
    			entity.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this), ((EntityZomboni) entity).getHealth());
    			//扎轮子的声音
    			if(this.getSpikeNum()<this.getMaxSpikeNum()) {
    				this.setSpikeNum(this.getSpikeNum()+1);
    			}
    			else {
    				this.setDead();
    			}
    		}
    		else {
    			entity.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this), this.getAttackDamage());
    		}
    	}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!net.minecraftforge.common.ForgeHooks.onLivingAttack(this, source, amount)) return false;
        if (this.isEntityInvulnerable(source))//无敌状态
        {
            return false;
        }
        else if (this.world.isRemote)//只在逻辑端处理
        {
            return false;
        }
        if(source instanceof PVZDamageSource) {
        	if(((PVZDamageSource) source).getPVZDamageType()==PVZDamageType.DEAD) {
        		if(this.getSpikeNum()<this.getMaxSpikeNum()) {
        			this.setSpikeNum(this.getSpikeNum()+1);
        			return true;
        		}
        		else {
        			this.setDead();
        			return true;
        		}
        	}
        }
		return super.attackEntityFrom(source, amount);
	}
	
	public int getMaxSpikeNum()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 3;
		else if(lvl<=13) return 6;
		else if(lvl<=20) return 9;
		return 3;
	}
	
	public int getAttackCD()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 10-now;
		}
		return 10;
	}
	
	@Override
	public float getAttackDamage() {
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 4+2*now;
		}
		return 4;
	}
	
	public int getSpikeNum()
	{
		return dataManager.get(SPIKE_NUM);
	}
	
	public void setSpikeNum(int num)
	{
		dataManager.set(SPIKE_NUM, num);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setSpikeNum(compound.getInteger("spike_num"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("spike_num", this.getSpikeNum());
	}

	@Override
	public int getSuperTimeLength() {
		return 2;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.SPIKE_ROCK;
	}
}
