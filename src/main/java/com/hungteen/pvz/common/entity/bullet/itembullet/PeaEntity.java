package com.hungteen.pvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.api.interfaces.IIceEffect;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.entity.bullet.AbstractShootBulletEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity.FlameTypes;
import com.hungteen.pvz.common.entity.zombie.zombotany.PeaShooterZombieEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.WeaponUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class PeaEntity extends AbstractShootBulletEntity implements IRendersAsItem {

	private static final DataParameter<Integer> PEA_STATE = EntityDataManager.defineId(PeaEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> PEA_TYPE = EntityDataManager.defineId(PeaEntity.class, DataSerializers.INT);
	public TorchWoodEntity torchWood = null;
	private int power = 0;

	public PeaEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}

	public PeaEntity(World worldIn, LivingEntity shooter, Type peaType, State peaState) {
		super(EntityRegister.PEA.get(), worldIn, shooter);
		this.setPeaState(peaState);
		this.setPeaType(peaType);
	}

	@Override
	protected void defineSynchedData() {
		entityData.define(PEA_STATE, State.NORMAL.ordinal());
		entityData.define(PEA_TYPE, Type.NORMAL.ordinal());
	}
	
	@Override
	protected float getAttackDamage() {
		if(this.getThrower() instanceof TorchWoodEntity) {
			return 2;
		}
		if (this.getThrower() instanceof PlantShooterEntity) {
			return ((PlantShooterEntity) this.getThrower()).getAttackDamage();
		}
		if(this.getThrower() instanceof PlayerEntity) {
			return 2;
		}
		if(this.getThrower() instanceof PeaShooterZombieEntity) {
			return ((PeaShooterZombieEntity) this.getThrower()).getAttackDamage();
		}
		return 0;
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (checkCanAttack(target)) {
				target.invulnerableTime = 0;
				this.dealPeaDamage(target); // attack 
				flag = true;
			}
		}
		this.level.broadcastEntityEvent(this, (byte) 3);
		if (flag || !this.checkLive(result)) {
			this.remove();
		}
	}
	
	/**
	 * {@link TorchWoodEntity#heatPeas()}
	 */
	public void heatBy(TorchWoodEntity wood) {
		if (this.torchWood == null || !this.torchWood.is(wood)) {// don't fire twice by the same torchwood
			this.torchWood = wood;
			if (this.torchWood.getFlameType() == FlameTypes.BLUE) {// blue fire
				if (this.getPeaState() == State.ICE) {//ice to fire
					this.setPeaState(State.FIRE);
				} else if (this.getPeaState().ordinal() < State.BLUE_FIRE.ordinal()) {// pea and fire to blue fire
					this.setPeaState(State.BLUE_FIRE);
				}
			} else {// fire
				if (this.getPeaState() == State.ICE) {//ice to normal 
					this.setPeaState(State.NORMAL);
				} else if (this.getPeaState() == State.NORMAL) {//normal to fire
					this.setPeaState(State.FIRE);
				}
			}
		}
	}

	private void dealPeaDamage(Entity target) {
		final float damage = this.getFixDamage();
		if (this.getPeaState() == State.NORMAL) {// normal pea attack
			target.hurt(PVZDamageSource.pea(this, this.getThrower()), damage);
		} else if (this.getPeaState() == State.ICE) {// snow pea attack
			PVZDamageSource source = PVZDamageSource.snowPea(this, this.getThrower());
			LivingEntity owner = this.getThrower();
			if (owner instanceof IIceEffect) {
				((IIceEffect) owner).getColdEffect().ifPresent(e -> source.addEffect(e));
				((IIceEffect) owner).getFrozenEffect().ifPresent(e -> source.addEffect(e));
			} else if(owner instanceof PlayerEntity) {
				((PlayerEntity)owner).getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
					int lvl = l.getPlayerData().getPlantStats().getPlantLevel(Plants.SNOW_PEA);
					source.addEffect(WeaponUtil.getPeaGunColdEffect(lvl));
				});
			}
			target.hurt(source, damage);
		} else if (this.getPeaState() == State.FIRE || this.getPeaState() == State.BLUE_FIRE) {
			target.hurt(PVZDamageSource.flamePea(this, this.getThrower()), damage);
		}
	}
	
	@Override
	protected boolean shouldHit(Entity target) {
		return super.shouldHit(target) || target instanceof TorchWoodEntity;
	}
	
	private float getFixDamage() {
		float damage = this.attackDamage;
		damage *= (1 + this.power * 1.0f / 5);
		// size
		if (this.getPeaType() == Type.BIG) {
			damage += 20f;
		} else if (this.getPeaType() == Type.HUGE) {
			damage += 75f;
		}
		// fire 
		if (this.getPeaState() == State.FIRE) {
			damage *= 2;
		} else if (this.getPeaState() == State.BLUE_FIRE) {
			damage *= 3;
		}
		return damage;
	}
	
	@Override
	protected int getMaxLiveTick() {
		return 40;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if (this.getPeaType() == Type.NORMAL) {
			return new EntitySize(0.2f, 0.2f, false);
		}
		if (this.getPeaType() == Type.BIG) {
			return new EntitySize(0.4f, 0.4f, false);
		}
		if (this.getPeaType() == Type.HUGE) {
			return new EntitySize(0.6f, 0.6f, false);
		}
		return new EntitySize(0.2f, 0.2f, false);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("peaState", this.getPeaState().ordinal());
		compound.putInt("peaType", this.getPeaType().ordinal());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("peaState")) {
			this.setPeaState(State.values()[compound.getInt("peaState")]);
		}
		if(compound.contains("peaType")) {
			this.setPeaType(Type.values()[compound.getInt("peaType")]);
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.002f;
	}

	public State getPeaState() {
		return State.values()[entityData.get(PEA_STATE)];
	}

	public void setPeaState(State state) {
		entityData.set(PEA_STATE, state.ordinal());
	}

	public Type getPeaType() {
		return Type.values()[entityData.get(PEA_TYPE)];
	}

	public void setPeaType(Type type) {
		entityData.set(PEA_TYPE, type.ordinal());
	}

	@Override
	public ItemStack getItem() {
		if (this.getPeaState() == State.NORMAL) {
			return new ItemStack(ItemRegister.PEA.get());
		}
		if (this.getPeaState() == State.ICE) {
			return new ItemStack(ItemRegister.SNOW_PEA.get());
		}
		if (this.getPeaState() == State.FIRE) {
			return new ItemStack(ItemRegister.FLAME_PEA.get());
		}
		if(this.getPeaState() == State.BLUE_FIRE) {
			return new ItemStack(ItemRegister.BLUE_FLAME_PEA.get());
		}
		return new ItemStack(ItemRegister.PEA.get());
	}
	
	public void setPower(int lvl) {
		this.power = lvl;
	}

	public enum Type {
		NORMAL,
		BIG,
		HUGE,
	}

	public enum State {
		ICE,
		NORMAL,
		FIRE,
		BLUE_FIRE,
		ELECTRICITY,
	}

}
