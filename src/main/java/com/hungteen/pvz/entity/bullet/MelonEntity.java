package com.hungteen.pvz.entity.bullet;

import java.util.Optional;

import com.hungteen.pvz.entity.plant.arma.MelonPultEntity;
import com.hungteen.pvz.entity.plant.ice.WinterMelonEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
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

	private static final DataParameter<Integer> MELON_TYPE = EntityDataManager.createKey(MelonEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> MELON_STATE = EntityDataManager.createKey(MelonEntity.class, DataSerializers.VARINT);
	private Entity attackEntity = null;
	
	public MelonEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}

	public MelonEntity(EntityType<?> type, World worldIn, LivingEntity shooter) {
		super(type, worldIn, shooter);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MELON_STATE, MelonStates.NORMAL.ordinal());
		this.dataManager.register(MELON_TYPE, MelonTypes.NORMAL.ordinal());
	}
	
	@Override
	protected void dealDamage(Entity target) {
		if(this.getIcePotion().isPresent()) {
			PVZDamageSource source = PVZDamageSource.causeIceDamage(this, this.getThrower());
			source.addEffect(this.getIcePotion().get());
			target.attackEntityFrom(source, this.getAttackDamage());
		} else{
			target.attackEntityFrom(PVZDamageSource.causeThrowDamage(this, this.getThrower()), this.getAttackDamage());
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
		EntityUtil.getAttackEntities(this.getThrower(), EntityUtil.getEntityAABB(this, range, range)).forEach((entity) -> {
			if(! entity.isEntityEqual(attackEntity) && (! (entity instanceof LivingEntity) || EntityUtil.checkCanEntityTarget(this.getThrower(), (LivingEntity) entity))) {
				PVZDamageSource source = PVZDamageSource.causeNormalDamage(this, this.getThrower());
				this.getIcePotion().ifPresent((effect) -> {
					source.addEffect(effect);
				});
				entity.attackEntityFrom(source, this.getSplashDamage());
			}
		});
		for(int i = 0; i < 10; ++ i) {
			EntityUtil.spawnParticle(this, (this.getMelonState() == MelonStates.ICE ? 2 : 1));
		}
	}
	
	private Optional<EffectInstance> getIcePotion() {
		if(! (this.getThrower() instanceof WinterMelonEntity)) return Optional.empty();
		return Optional.of(((WinterMelonEntity)this.getThrower()).getColdEffect());
	}
	
	private float getAttackDamage() {
		float damage = 0;
		if(this.getThrower() instanceof MelonPultEntity) {
			damage = ((MelonPultEntity) this.getThrower()).getAttackDamage();
		}
		if(this.getMelonType() == MelonTypes.POWER) damage += 12;
		return damage;
	}
	
	private float getSplashDamage() {
		float damage = 0;
		if(this.getMelonType() == MelonTypes.POWER) return 3;
		if(this.getThrower() instanceof MelonPultEntity) {
			damage = ((MelonPultEntity) this.getThrower()).getSplashDamage();
		}
		return damage;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.6F, 0.6F);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("melon_type")) {
			this.setMelonType(MelonTypes.values()[compound.getInt("melon_type")]);
		}
		if(compound.contains("melon_state")) {
			this.setMelonState(MelonStates.values()[compound.getInt("melon_state")]);
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("melon_type", this.getMelonType().ordinal());
		compound.putInt("melon_state", this.getMelonState().ordinal());
	}
	
	public void setMelonType(MelonTypes type) {
		this.dataManager.set(MELON_TYPE, type.ordinal());
	}
	
	public MelonTypes getMelonType() {
		return MelonTypes.values()[this.dataManager.get(MELON_TYPE)];
	}
	
	public void setMelonState(MelonStates type) {
		this.dataManager.set(MELON_STATE, type.ordinal());
	}
	
	public MelonStates getMelonState() {
		return MelonStates.values()[this.dataManager.get(MELON_STATE)];
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
