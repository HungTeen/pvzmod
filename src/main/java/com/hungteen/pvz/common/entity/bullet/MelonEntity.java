package com.hungteen.pvz.common.entity.bullet;

import java.util.Optional;

import com.hungteen.pvz.common.entity.plant.arma.MelonPultEntity;
import com.hungteen.pvz.common.entity.plant.ice.WinterMelonEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class MelonEntity extends PultBulletEntity {

	private static final DataParameter<Integer> MELON_TYPE = EntityDataManager.defineId(MelonEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> MELON_STATE = EntityDataManager.defineId(MelonEntity.class, DataSerializers.INT);
	private Entity attackEntity = null;
	protected float splashDamage = 0F;
	
	public MelonEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}

	public MelonEntity(World worldIn, LivingEntity shooter) {
		super(EntityRegister.MELON.get(), worldIn, shooter);
		this.splashDamage = this.getSplashDamage();
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(MELON_STATE, MelonStates.NORMAL.ordinal());
		this.entityData.define(MELON_TYPE, MelonTypes.NORMAL.ordinal());
	}
	
	@Override
	protected void dealDamage(Entity target) {
		if(this.getIcePotion().isPresent()) {
			PVZDamageSource source = PVZDamageSource.causeIceDamage(this, this.getThrower());
			source.addEffect(this.getIcePotion().get());
			target.hurt(source, this.getFixDamage());
		} else{
			target.hurt(PVZDamageSource.causeThrowDamage(this, this.getThrower()), this.getFixDamage());
		}
		this.attackEntity = target;
		this.dealSplashDamage();
	}
	
	@Override
	protected void onHitBlock() {
		this.dealSplashDamage();
	}
	
	private void dealSplashDamage() {
		EntityUtil.playSound(this, SoundRegister.MELON_HIT.get());
		float range = 2.5F;
		EntityUtil.getTargetableEntities(this.getThrower(), EntityUtil.getEntityAABB(this, range, range)).forEach((entity) -> {
			if(! entity.is(attackEntity) && (! (entity instanceof LivingEntity) || EntityUtil.canTargetEntity(this.getThrower(), (LivingEntity) entity))) {
				PVZDamageSource source = PVZDamageSource.causeNormalDamage(this, this.getThrower());
				this.getIcePotion().ifPresent((effect) -> {
					source.addEffect(effect);
				});
				entity.hurt(source, this.splashDamage);
			}
		});
		for(int i = 0; i < 10; ++ i) {
			EntityUtil.spawnParticle(this, (this.getMelonState() == MelonStates.ICE ? 2 : 1));
		}
	}
	
	private Optional<EffectInstance> getIcePotion() {
		if(! (this.getThrower() instanceof WinterMelonEntity)) {
			return Optional.empty();
		}
		return ((WinterMelonEntity)this.getThrower()).getColdEffect();
	}
	
	private float getFixDamage() {
		float damage = this.attackDamage;
		if(this.getMelonType() == MelonTypes.POWER) damage += 12;
		return damage;
	}
	
	protected float getAttackDamage() {
		if(this.getThrower() instanceof MelonPultEntity) return ((MelonPultEntity) this.getThrower()).getAttackDamage();
		return 0;
	}
	
	private float getSplashDamage() {
		if(this.getMelonType() == MelonTypes.POWER) return 3;
		if(this.getThrower() instanceof MelonPultEntity) return ((MelonPultEntity) this.getThrower()).getSplashDamage();
		return 0;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.6F, 0.6F);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("melon_type")) {
			this.setMelonType(MelonTypes.values()[compound.getInt("melon_type")]);
		}
		if(compound.contains("melon_state")) {
			this.setMelonState(MelonStates.values()[compound.getInt("melon_state")]);
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("melon_type", this.getMelonType().ordinal());
		compound.putInt("melon_state", this.getMelonState().ordinal());
	}
	
	public void setMelonType(MelonTypes type) {
		this.entityData.set(MELON_TYPE, type.ordinal());
	}
	
	public MelonTypes getMelonType() {
		return MelonTypes.values()[this.entityData.get(MELON_TYPE)];
	}
	
	public void setMelonState(MelonStates type) {
		this.entityData.set(MELON_STATE, type.ordinal());
	}
	
	public MelonStates getMelonState() {
		return MelonStates.values()[this.entityData.get(MELON_STATE)];
	}
	
	public static enum MelonStates {
		NORMAL,
		ICE,
	}
	
	public static enum MelonTypes {
		NORMAL,
		POWER,
	}
	
}
