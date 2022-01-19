package com.hungteen.pvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.plant.toxic.PuffShroomEntity;
import com.hungteen.pvz.common.entity.plant.toxic.SeaShroomEntity;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.utils.WorldUtil;

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
			for(int i = 0; i < 3; ++i) {
				WorldUtil.spawnRandomSpeedParticle(level, ParticleRegister.SPORE.get(), this.position(), 0);
	        }
		}
	}
	
	@Override
	protected int getMaxLiveTick() {
		if(this.getThrower() instanceof PuffShroomEntity || this.getThrower() instanceof SeaShroomEntity) {
			return 10;
		}
		return 24;
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
			if (this.shouldHit(target)) {
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
		target.hurt(PVZDamageSource.spore(this, this.getThrower()), this.attackDamage);
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
