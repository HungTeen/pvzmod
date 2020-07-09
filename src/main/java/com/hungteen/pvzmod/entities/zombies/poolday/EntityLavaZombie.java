package com.hungteen.pvzmod.entities.zombies.poolday;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.ai.EntityAIZombieEat;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.ConfigurationUtil;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityLavaZombie extends EntityZombieBase {

	public EntityLavaZombie(World worldIn) {
		super(worldIn);
		this.setSize(0.9f, 2.1f);
		this.isImmuneToFire=true;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(2, new EntityAIZombieEat(this, 1.0, false));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		initAITargetTask();
	}

	@Override
	public float getLife() {
		return 360;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.BIT_FAST);
	}

	@Override
	public void onNormalZombieUpdate() {
		super.onNormalZombieUpdate();
		if (!this.world.isRemote && this.getAttackTarget() != null) {
			Entity target = this.getAttackTarget();
			if (this.isInLava()) {
				double dx = target.posX - this.posX;
				double dy = target.posY - this.posY;
				double dz = target.posZ - this.posZ;
				double dis = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
				this.motionX = dx * getWaterSpeed() / dis;
				this.motionY = dy * getWaterSpeed() / dis;
				this.motionZ = dz * getWaterSpeed() / dis;
			}
		}
		if(!this.world.isRemote&&this.ticksExisted%20==0&&this.checkWeak()) {
			this.damageEntity(PVZDamageSource.causeWeakDamage(this, this), 10);
		}
	}

	private boolean checkWeak()
	{
		return this.isInWater();
	}
	
	protected double getWaterSpeed() {
		return this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() / 2;
	}

	@Override
	public boolean hasNoGravity() {
		if (this.isInLava())
			return true;
		return super.hasNoGravity();
	}

	@Override
	protected boolean dealAttackDamage(float damage, Entity target) {
		if(target instanceof EntityLivingBase) {
			((EntityLivingBase)target).setFire(3+ConfigurationUtil.getPVZDifficulty()*3);
		}
		return super.dealAttackDamage(damage, target);
	}
	
	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	public boolean getCanSpawnHere() {
		return this.posY > 5.0D && this.posY < (double) this.world.getSeaLevel() && super.getCanSpawnHere();
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.9f;
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.LAVA_ZOMBIE;
	}
}
