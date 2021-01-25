package com.hungteen.pvz.entity.bullet.itembullet;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.entity.plant.flame.TorchWoodEntity;
import com.hungteen.pvz.entity.plant.interfaces.IIcePlant;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.WeaponUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
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

public class PeaEntity extends PVZItemBulletEntity {

	private static final DataParameter<Integer> PEA_STATE = EntityDataManager.createKey(PeaEntity.class,
			DataSerializers.VARINT);
	private static final DataParameter<Integer> PEA_TYPE = EntityDataManager.createKey(PeaEntity.class,
			DataSerializers.VARINT);
	
	private int power = 0;
	private TorchWoodEntity torchWood = null;

	public PeaEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}

	public PeaEntity(EntityType<?> type, World worldIn, LivingEntity shooter, Type peaType,
			State peaState) {
		super(type, worldIn, shooter);
		this.setPeaState(peaState);
		this.setPeaType(peaType);
		this.recalculateSize();
	}

	@Override
	protected void registerData() {
		dataManager.register(PEA_STATE, State.NORMAL.ordinal());
		dataManager.register(PEA_TYPE, Type.NORMAL.ordinal());
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (checkCanAttack(target)) {
				target.hurtResistantTime = 0;
				this.dealPeaDamage(target); // attack 
				flag = true;
			}
			if (!this.world.isRemote && target instanceof TorchWoodEntity) {// pea interact with torchwood
				TorchWoodEntity tmp = (TorchWoodEntity) target;
				if (this.torchWood == null || !this.torchWood.isEntityEqual(tmp)) {// don't fire twice by the same torchwood
					this.torchWood = tmp;
					if (this.torchWood.IsSuperFlame()) {// blue fire
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
		}
		if (!this.world.isRemote) {
//        	if(result.entityHit instanceof Entity) {
//        		System.out.println(result.entityHit);
//        	}
			this.world.setEntityState(this, (byte) 3);
			if (flag || !this.checkLive(result)) {
				this.remove();
			}
		}
	}

	private void dealPeaDamage(Entity target) {
		if (this.getPeaState() == State.NORMAL) {// normal pea attack
			// System.out.println(this.getThrower());
			target.attackEntityFrom(PVZDamageSource.causeAppeaseDamage(this, this.getThrower()), this.getAttackDamage());
		} else if (this.getPeaState() == State.ICE) {// snow pea attack
			PVZDamageSource source = PVZDamageSource.causeIceDamage(this, this.getThrower());
			LivingEntity owner = this.getThrower();
			if (owner instanceof IIcePlant) {
				source.addEffect(((IIcePlant) owner).getColdEffect());
//				source.addEffect(((IIcePlant) owner).getFrozenEffect());
			} else if(owner instanceof PlayerEntity) {
				((PlayerEntity)owner).getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
					int lvl = l.getPlayerData().getPlantStats().getPlantLevel(Plants.SNOW_PEA);
					source.addEffect(WeaponUtil.getPeaGunColdEffect(lvl));
				});
			}
			target.attackEntityFrom(source, this.getAttackDamage());
		} else if (this.getPeaState() == State.FIRE || this.getPeaState() == State.BLUE_FIRE) {
			target.attackEntityFrom(PVZDamageSource.causeFireDamage(this, this.getThrower()), this.getAttackDamage());
		}
	}
	
	@Override
	protected boolean shouldHit(Entity target) {
		return super.shouldHit(target) || target instanceof TorchWoodEntity;
	}
	
	private float getAttackDamage() {
		float damage = 0;

		// look at the thrower first
		if(this.getThrower() instanceof TorchWoodEntity) {
			damage = 2;
		}else if (this.getThrower() instanceof PlantShooterEntity) {
			damage = ((PlantShooterEntity) this.getThrower()).getAttackDamage();
		}
//		else if(this.shooter instanceof EntityZombieBase) {//
//			damage=3;
//		}
		else if(this.getThrower() instanceof PlayerEntity) {
			damage = 2;
		}

		damage *= (1 + this.power * 1.0f / 5);
		
//		System.out.println(this.power);
		
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
	public EntitySize getSize(Pose poseIn) {
		if (this.getPeaType() == Type.NORMAL)
			return new EntitySize(0.2f, 0.2f, false);
		if (this.getPeaType() == Type.BIG)
			return new EntitySize(0.4f, 0.4f, false);
		if (this.getPeaType() == Type.HUGE)
			return new EntitySize(0.6f, 0.6f, false);
		return new EntitySize(0.2f, 0.2f, false);
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("peaState", this.getPeaState().ordinal());
		compound.putInt("peaType", this.getPeaType().ordinal());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
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
		return State.values()[dataManager.get(PEA_STATE)];
	}

	public void setPeaState(State state) {
		dataManager.set(PEA_STATE, state.ordinal());
	}

	public Type getPeaType() {
		return Type.values()[dataManager.get(PEA_TYPE)];
	}

	public void setPeaType(Type type) {
		dataManager.set(PEA_TYPE, type.ordinal());
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
//		System.out.println(lvl);
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
