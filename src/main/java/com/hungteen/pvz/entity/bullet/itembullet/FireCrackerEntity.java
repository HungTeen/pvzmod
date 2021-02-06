package com.hungteen.pvz.entity.bullet.itembullet;

import com.hungteen.pvz.entity.plant.explosion.BambooLordEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireCrackerEntity extends PVZItemBulletEntity{

	private static final float SPEED = 1.5F;
	protected Entity target = null;
	
	public FireCrackerEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public FireCrackerEntity(World worldIn, LivingEntity owner) {
		super(EntityRegister.FIRE_CRACKER.get(), worldIn, owner);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! world.isRemote && EntityUtil.isEntityValid(target)) {
			this.shoot(this.target);
		}
	}

	public void shoot(Vec3d vec) {
		this.setMotion(vec.scale(SPEED));
	}
	
	public void shoot(Entity target) {
		this.target = target;
		Vec3d vec = target.getPositionVec().subtract(this.getPositionVec()).normalize();
		this.shoot(vec);
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (checkCanAttack(target)) {
				target.hurtResistantTime = 0;
				this.dealDamage(target); // attack 
				flag = true;
			}
		}
		this.world.setEntityState(this, (byte) 3);
		if (flag) {
			this.remove();
		} else if(! this.checkLive(result)) {
			this.dealDamage(null);
			this.remove();
		}
	}
	
	private void dealDamage(Entity target) {
		
		if(! world.isRemote) {
			EntityUtil.playSound(this, SoundRegister.POTATO_MINE.get());
		    float range = 1.5F;
		    EntityUtil.getAttackEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((entity) -> {
			    entity.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this.getThrower()), this.attackDamage);
		    });
		    for(int i = 0;i < 3; ++ i) {
			    EntityUtil.spawnParticle(this, 5);
		    }
		} 
	}
	
	protected float getAttackDamage() {
		if(this.getThrower() instanceof BambooLordEntity) return ((BambooLordEntity) this.getThrower()).getAttackDamage() * 2;
		if(this.getThrower() instanceof PlayerEntity) return 3;
		return 0;
	}
	
	@Override
	protected int getMaxLiveTick() {
		return 50;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("target_entity_id")) {
			this.target = (Entity) world.getEntityByID(compound.getInt("target_entity_id"));
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		if(this.target != null) {
			compound.putInt("target_entity_id", this.target.getEntityId());
		}
	}
	
	@Override
	public ItemStack getItem() {
		return new ItemStack(ItemRegister.FIRE_CRACKER.get());
	}
	
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.5F, 0.5F);
	}

}
