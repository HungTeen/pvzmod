package com.hungteen.pvz.entity.misc;

import com.hungteen.pvz.entity.plant.enforce.ChomperEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class SmallChomperEntity extends AbstractOwnerEntity {

	private int lifeTick;
	private final int maxLifeTick = 20;

	public SmallChomperEntity(EntityType<? extends Entity> entityTypeIn, World worldIn) {
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
		Entity owner = this.owner;
		if(owner == null) {
			owner = this;
		}
		for(Entity target : EntityUtil.getAttackEntities(this, EntityUtil.getEntityAABB(this, 0.5f, 1f))) {
			if(target instanceof LivingEntity) {
				target.attackEntityFrom(PVZDamageSource.causeEatDamage(this, this.owner), getAttackDamage((LivingEntity) target));
			}
		}
		this.playSound(SoundRegister.CHOMP.get(), 1, 1);
	}
	
	private float getAttackDamage(LivingEntity target) {
		if(this.owner instanceof ChomperEntity) {
			return ((ChomperEntity) this.owner).getAttackDamage(target);
		}
		return 40;
	}
	
	public int getTick() {
		return this.lifeTick;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
	
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.4f, 0.5f, false);
	}

}
