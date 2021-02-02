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
		this.noClip = true;
	}

	@Override
	public void tick() {
		super.tick();
		if(this.lifeTick < maxLifeTick) {
			++ this.lifeTick;
		} else {
			if(! this.world.isRemote) {
			    this.performAttack();
			    this.remove();
			}
		}
	}
	
	protected void performAttack() {
		for(Entity target : EntityUtil.getAttackEntities(this, EntityUtil.getEntityAABB(this, 0.5f, 1f))) {
			target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.owner), getAttackDamage((LivingEntity) target));
			target.setPosition(target.getPosX(), target.getPosY() - 3, target.getPosZ());
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
	public boolean canBeCollidedWith() {
		return false;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.4f, 0.5f, false);
	}

	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
}
