package com.hungteen.pvz.entity.misc;

import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class ZombieHandEntity extends AbstractOwnerEntity {

	private int lifeTick;
	private final int maxLifeTick = 40;

	public ZombieHandEntity(EntityType<? extends Entity> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.setInvulnerable(true);
		this.noPhysics = true;
	}

	@Override
	public void tick() {
		super.tick();
		if(this.lifeTick < maxLifeTick) {
			++ this.lifeTick;
		} else {
			if(! this.level.isClientSide) {
			    this.performAttack();
			    this.remove();
			}
		}
	}
	
	protected void performAttack() {
		for(Entity target : EntityUtil.getAttackEntities(this, EntityUtil.getEntityAABB(this, 0.5f, 1f))) {
			if(target instanceof LivingEntity) {
				target.hurt(PVZDamageSource.causeNormalDamage(this, this.owner), getAttackDamage((LivingEntity) target));
			    target.setPos(target.getX(), target.getY() - 3, target.getZ());
			}
		}
	}
	
	@Override
	public int getInitialEntityGroup() {
		return - 1;
	}
	
	private float getAttackDamage(LivingEntity target) {
		return 5;
	}
	
	public int getTick() {
		return this.lifeTick;
	}
	
	@Override
	public boolean isPickable() {
		return false;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.4f, 0.5f, false);
	}

	@Override
	public boolean isNoGravity() {
		return true;
	}
	
}
