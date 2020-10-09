package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.ParticleRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SporeEntity extends PVZThrowableEntity{

	private static final int MAX_LIVE_TICK = 20;
	
	public SporeEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public SporeEntity(EntityType<?> type, World worldIn, LivingEntity living) {
		super(type, worldIn, living);
	}

	@Override
	public void tick() {
		super.tick();
		if(world.isRemote) {
			int cnt = this.ticksExisted < MAX_LIVE_TICK / 2 ? 3 : 2;
			for(int i = 0; i < cnt; ++i) {
	            this.world.addParticle(ParticleRegister.SPORE.get(), this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
	        }
		}
	}
	
	@Override
	protected int getMaxLiveTick() {
		return MAX_LIVE_TICK;
	}
	
	@Override
	public ItemStack getItem() {
		return new ItemStack(ItemRegister.SPORE.get());
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (checkCanAttack(target)) {
				target.hurtResistantTime = 0;
				this.dealSporeDamage(target); // attack 
				flag = true;
			}
		}
		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			if (flag || !this.checkLive(result)) {
				this.remove();
			}
		}
	}
	
	private void dealSporeDamage(Entity target) {
		target.attackEntityFrom(PVZDamageSource.causeAppeaseDamage(this, this.getThrower()), this.getSporeDamage());
	}
	
	private float getSporeDamage() {
		if(this.getThrower() instanceof PlantShooterEntity) {
			return ((PlantShooterEntity) this.getThrower()).getAttackDamage();
		}
		return 0;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.25f, 0.25f);
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.002f;
	}

}
