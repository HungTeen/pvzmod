package com.hungteen.pvz.common.entity.misc;

import java.util.Optional;

import com.hungteen.pvz.common.entity.AbstractOwnerEntity;
import com.hungteen.pvz.common.entity.plant.enforce.ChomperEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class SmallChomperEntity extends AbstractOwnerEntity {

	private final int maxLifeTick = 20;
	private int lifeTick;

	public SmallChomperEntity(EntityType<? extends Entity> entityTypeIn, World worldIn) {
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
		Optional.ofNullable(this.getOwner()).ifPresent(owner -> {
			for(LivingEntity target : EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, 1.5F, 2F))) {
				target.hurt(PVZDamageSource.eat(this, owner), getAttackDamage(owner));
			}
		});
		EntityUtil.playSound(this, SoundRegister.CHOMP.get());
	}
	
	/**
	 * get damage by owner.
	 */
	private float getAttackDamage(Entity owner) {
		if(owner instanceof ChomperEntity) {
			return ((ChomperEntity) owner).getAttackDamage();
		}
		return 40;
	}
	
	public int getTick() {
		return this.lifeTick;
	}
	
	@Override
	public boolean isPickable() {
		return false;
	}
	
	@Override
	public boolean isNoGravity() {
		return true;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.4f, 0.5f, false);
	}

}
