package com.hungteen.pvz.common.entity.zombie.roof;

import com.hungteen.pvz.common.impl.zombie.RoofZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

public class GigaGargantuarEntity extends GargantuarEntity {

	public GigaGargantuarEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
		this.isSad = true;
	}
	
	@Override
	public boolean doHurtTarget(Entity entityIn) {
		this.groundShack(entityIn);
		return super.doHurtTarget(entityIn);
	}
	
	private void groundShack(Entity entity) {
		final float range = 4;
		EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
			if(! target.is(entity)) {
				target.hurt(getZombieAttackDamageSource(), EntityUtil.getMaxHealthDamage(target, 0.5F));
				for(int i = 0; i < 5; ++ i) {
					EntityUtil.spawnParticle(target, 6);
				}
			}
		});
	}
	
	@Override
	protected float getModifyAttackDamage(Entity entity, float f) {
		if(entity instanceof LivingEntity) {
			return EntityUtil.getMaxHealthDamage(((LivingEntity) entity), 4);
		}
		return f;
	}
	
	@Override
	public void throwImp(LivingEntity target) {
		for(int i = 0; i < 3; ++ i) {
			super.throwImp(target);
		}
	}
	
	@Override
	public float getLife() {
		return 600;
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_LITTLE_SLOW;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) {
			return EntitySize.scalable(0.7F, 2F);
		}
		return super.getDimensions(poseIn);
	}
	
	@Override
    public ZombieType getZombieType() {
	    return RoofZombies.GIGA_GARGANTUAR;
    }
	
}
