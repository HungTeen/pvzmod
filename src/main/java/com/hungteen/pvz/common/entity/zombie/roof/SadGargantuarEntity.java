package com.hungteen.pvz.common.entity.zombie.roof;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SadGargantuarEntity extends GargantuarEntity {

	public SadGargantuarEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.isSad = true;
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_SLOW);
	}
	
	@Override
	public boolean doHurtTarget(Entity entityIn) {
		if(! level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.GROUND_SHAKE.get());
		}
		this.groundShack(entityIn);
		if(!EntityUtil.isEntityValid(entityIn)) return false;
		return super.doHurtTarget(entityIn);
	}
	
	private void groundShack(Entity entity) {
		float range = 3;
		EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
			if(! target.is(entity)) {
				target.hurt(getZombieAttackDamageSource(), EntityUtil.getCurrentMaxHealth(target) / 2);
				for(int i = 0; i < 5; ++ i) {
					EntityUtil.spawnParticle(target, 6);
				}
			}
		});
	}
	
	@Override
	protected float getModifyAttackDamage(Entity entity, float f) {
		if(entity instanceof LivingEntity) {
			return 4 * EntityUtil.getCurrentMaxHealth(((LivingEntity) entity));
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
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.7F, 2F);
		return super.getDimensions(poseIn);
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.SAD_GARGANTUAR;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SAD_GARGANTUAR;
	}
	
}
