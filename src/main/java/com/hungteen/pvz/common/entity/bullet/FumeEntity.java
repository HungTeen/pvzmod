package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.itembullet.PVZItemBulletEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.plant.toxic.GloomShroomEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.ParticleRegister;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class FumeEntity extends PVZItemBulletEntity{

	private int knockback = 0;
	
	public FumeEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public FumeEntity(World worldIn, LivingEntity living) {
		super(EntityRegister.FUME.get(), worldIn, living);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(level.isClientSide) {
			int cnt = this.tickCount < getMaxLiveTick() / 2 ? 6 : 4;
			for(int i = 0; i < cnt; ++i) {
	            this.level.addParticle(ParticleRegister.FUME.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
	        }
		}
	}
	
	@Override
	protected int getMaxLiveTick() {
		if(this.getThrower() instanceof GloomShroomEntity) {
			return 5;
		}
		return 25;
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
				this.dealFumeDamage(target); // attack 
				if(this.hitEntities == null) {
					this.hitEntities = new IntOpenHashSet();
				}
				this.addHitEntity(target);
			}
		}
		this.level.broadcastEntityEvent(this, (byte) 3);
		if (flag || !this.checkLive(result)) {
			this.remove();
		}
	}
	
	@Override
	protected boolean checkLive(RayTraceResult result) {
		if(result.getType() == RayTraceResult.Type.BLOCK) {
    		Block block = level.getBlockState(((BlockRayTraceResult)result).getBlockPos()).getBlock();
    		if(block instanceof BushBlock) {
    			return true;
    		}
    		return false;
    	}
    	return true;
	}
	
	private void dealFumeDamage(Entity target) {
		target.hurt(PVZDamageSource.causeThroughDamage(this, this.getThrower()), this.attackDamage);
		if(!level.isClientSide && this.knockback > 0) {
			Vector3d speed = target.getDeltaMovement();
			Vector3d now = this.getDeltaMovement();
			int lvl = this.knockback;
			target.setDeltaMovement(speed.add(now).multiply(lvl, lvl, lvl));
		}
	}
	
	@Override
	protected float getAttackDamage() {
		if(this.getThrower() instanceof PlantShooterEntity) return ((PlantShooterEntity) this.getThrower()).getAttackDamage();
		return 0;
	}
	
	public void setKnockback(int lvl) {
		this.knockback = lvl;
	}
	
	public int getKnockback() {
		return this.knockback;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.25f, 0.25f);
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.002f;
	}

}
