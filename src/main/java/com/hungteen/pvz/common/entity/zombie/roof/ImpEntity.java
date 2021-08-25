package com.hungteen.pvz.common.entity.zombie.roof;

import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.RoofZombies;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class ImpEntity extends PVZZombieEntity {

	protected boolean isFalling = false;
	protected int protectTick = 0;
	
	public ImpEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! level.isClientSide) {
			int now = this.getRandom().nextInt(10);
			if(now == 0) this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 600, 1));
			else if(now == 1) this.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 600, 1));
			else if(now == 2) this.addEffect(new EffectInstance(Effects.JUMP, 600, 1));
			else if(now == 3) this.addEffect(new EffectInstance(Effects.HEALTH_BOOST, 600, 1));
			else if(now == 3) this.addEffect(new EffectInstance(Effects.ABSORPTION, 600, 1));
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_FAST);
	}
	
	@Override
	public void zombieTick() {
		super.zombieTick();
		if(!this.level.isClientSide) {
			if(this.onGround && this.isFalling) {
				this.isFalling = false;
			}
			if(this.protectTick > 0) {
				-- this.protectTick;
			}
		}
	}
	
	@Override
	public boolean canZombieNormalUpdate() {
		return super.canZombieNormalUpdate() && ! this.isFalling;
	}
	
	@Override
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		return super.isZombieInvulnerableTo(source) || this.protectTick > 0;
	}
	
	public void throwByGargantuar(GargantuarEntity entity, LivingEntity target) {
		Vector3d vec = new Vector3d(entity.getRandom().nextFloat() - 0.5, entity.getRandom().nextFloat() / 4, entity.getRandom().nextFloat() - 0.5).normalize();
		if(target != null) {
			double speed = 2F;
			vec = target.position().subtract(entity.position()).normalize().scale(speed);
		} else {
			double speed = 0.5F;
			vec = vec.scale(speed);
		}
		this.setDeltaMovement(vec);
		ZombieUtil.copySummonZombieData(entity, this);
		this.isFalling = true;
		this.protectTick = 60;
	}
	
	@Override
	public boolean canBeAttractedBy(ICanAttract defender) {
		return ! this.isFalling && super.canBeAttractedBy(defender);
	}
	
	@Override
	public float getLife() {
		return 10;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) {
			return EntitySize.scalable(0.2F, 0.45F);
		}
		return EntitySize.scalable(0.6F, 1.2F);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("protect_tick")) {
			this.protectTick = compound.getInt("protect_tick");
		}
		if(compound.contains("fall_from_throw")) {
			this.isFalling = compound.getBoolean("fall_from_throw");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("protect_tick", this.protectTick);
		compound.putBoolean("fall_from_throw", this.isFalling);
	}

	@Override
    public ZombieType getZombieType() {
	    return RoofZombies.IMP;
    }

}
