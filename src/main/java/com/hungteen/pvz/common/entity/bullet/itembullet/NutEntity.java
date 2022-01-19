package com.hungteen.pvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class NutEntity extends PVZItemBulletEntity {

	public NutEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
//	public NutEntity(World worldIn, LivingEntity thrower) {
//		super(EntityRegister.NUT.get(), worldIn, thrower);
//	}
//	
	public void shoot(double x, double y, double z) {
		this.setDeltaMovement(x, y, z);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ItemRegister.NUT.get());
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if(result.getType() == RayTraceResult.Type.BLOCK) {
			if(this.getThrower() != null && level.isEmptyBlock(this.blockPosition().above()) && this.random.nextInt(12) == 0) {
				WallNutEntity nut = EntityRegister.WALL_NUT.get().create(level);
				nut.setOwnerUUID(this.getThrower().getUUID());
				EntityUtil.onEntitySpawn(level, nut, this.blockPosition().above());
				flag = true;
			}
		} else if(result.getType() ==  RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (this.shouldHit(target)) {
				target.invulnerableTime = 0;
				this.dealNutDamage(target); // attack 
				flag = true;
			}
		}
		this.level.broadcastEntityEvent(this, (byte) 3);
		if (flag) {
			this.remove();
		}
	}
	
	private void dealNutDamage(Entity target) {
//		target.hurt(PVZDamageSource.causeNormalDamage(this, this.getThrower()), 2);
	}
	
	@Override
	protected int getMaxLiveTick() {
		return 80;
	}

	@Override
	protected float getGravityVelocity() {
		return 0.06f;
	}

}
