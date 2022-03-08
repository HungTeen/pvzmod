package com.hungteen.pvz.common.entity.misc;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.common.entity.AbstractOwnerEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.other.CoffinEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.WorldUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

public class ZombieHandEntity extends AbstractOwnerEntity {

	private int lifeTick;
	private final int maxLifeTick = 40;

	public ZombieHandEntity(EntityType<? extends Entity> entityTypeIn, Level worldIn) {
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
	
	/**
	 * {@link CoffinEntity#finalizeSpawn(net.minecraft.world.IServerWorld, net.minecraft.world.DifficultyInstance, net.minecraft.world.entity.SpawnReason, net.minecraft.world.entity.ILivingEntityData, CompoundTag)}
	 */
	public static void spawnRangeZombieHands(Level world, PVZZombieEntity zombie, int range) {
		for(int i = - range; i <= range; ++ i) {
			for(int j = - range; j <= range; ++ j) {
				final ZombieHandEntity hand = EntityRegister.ZOMBIE_HAND.get().create(world);
				hand.summonByOwner(zombie);
				EntityUtil.onEntitySpawn(world, hand, WorldUtil.getSuitableHeightPos(world, zombie.blockPosition().offset(i, 0, j)));
			}
		}
	}
	
	protected void performAttack() {
		EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, 0.5f, 1f)).forEach(target -> {
		    target.hurt(PVZEntityDamageSource.normal(this, this.getOwnerOrSelf()), this.getAttackDamage(target));
			target.setPos(target.getX(), target.getY() - 3, target.getZ());
		});
	}
	
	@Override
	public PVZGroupType getInitialEntityGroup() {
		return PVZGroupType.ZOMBIES;
	}
	
	private float getAttackDamage(LivingEntity target) {
		return 10;
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
