package com.hungteen.pvz.common.entity.zombie.base;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity.BodyType;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class CarZombieEntity extends PVZZombieEntity {

	public CarZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setIsWholeBody();
		this.canBeFrozen = false;
		this.canBeMini = false;
		this.maxDeathTime = 1;
	}
	
	@Override
	public void zombieTick() {
		super.zombieTick();
		if(level.isClientSide && this.isCarShaking()) {
			for(int i = 1; i <= 3; i ++) {
			    this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), (this.getRandom().nextFloat() - 0.5) / 10, 0.05, (this.getRandom().nextFloat() - 0.5) / 10);
			}
		}
	}
	
	@Override
	protected void onZombieRemove() {
		if(! level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.CAR_EXPLOSION.get());
		}
		else {
			for(int i = 0; i < 4; ++ i) {
			    this.level.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
			}
		}
		super.onZombieRemove();
	}
	
	
	@Override
	protected void onFallBody(DamageSource source) {
		ZombieDropBodyEntity body = EntityRegister.ZOMBIE_DROP_BODY.get().create(level);
		body.specialDropBody(this, source, BodyType.HEAD);
		this.setBodyStates(body);
		level.addFreshEntity(body);
	}
	
	public boolean isCarShaking() {
		return this.getHealth() <= this.getMaxHealth() / 4;
	}
	
	@Override
	public boolean canBreakPlantBlock() {
		return false;
	}
	
	@Override
	protected PVZDamageSource getZombieAttackDamageSource() {
		return PVZDamageSource.causeCrushDamage(this, this);
	}
	
	@Override
	protected float getModifyAttackDamage(Entity entity, float f) {
		if(entity instanceof LivingEntity) {
			return EntityUtil.getMaxHealthDamage(((LivingEntity) entity));
		}
		return f;
	}

}