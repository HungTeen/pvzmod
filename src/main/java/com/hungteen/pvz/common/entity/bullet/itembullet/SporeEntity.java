package com.hungteen.pvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.plant.toxic.PuffShroomEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
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

public class SporeEntity extends PVZItemBulletEntity{

	public SporeEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public SporeEntity(World worldIn, LivingEntity living) {
		super(EntityRegister.SPORE.get(), worldIn, living);
	}

	@Override
	public void tick() {
		super.tick();
		if(level.isClientSide) {
			int cnt = 3;
			for(int i = 0; i < cnt; ++i) {
	            this.level.addParticle(ParticleRegister.SPORE.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
	        }
		}
	}
	
	@Override
	protected int getMaxLiveTick() {
		if(this.getThrower() instanceof PuffShroomEntity) return 20;
		return 50;
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
				target.invulnerableTime = 0;
				this.dealSporeDamage(target); // attack 
				flag = true;
			}
		}
		this.level.broadcastEntityEvent(this, (byte) 3);
		if (flag || !this.checkLive(result)) {
			this.remove();
		}
	}
	
	private void dealSporeDamage(Entity target) {
		target.hurt(PVZDamageSource.causeAppeaseDamage(this, this.getThrower()), this.attackDamage);
	}
	
	@Override
	protected float getAttackDamage() {
		if(this.getThrower() instanceof PlantShooterEntity) return ((PlantShooterEntity) this.getThrower()).getAttackDamage();
		return 0;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.25f, 0.25f);
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.0012f;
	}

}
