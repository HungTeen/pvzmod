package com.hungteen.pvz.common.entity.zombie.poolnight;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.MetalTypes;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class JackInBoxZombieEntity extends PVZZombieEntity implements IHasMetal {

	private static final DataParameter<Boolean> HAS_BOX = EntityDataManager.defineId(JackInBoxZombieEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> ANIM_TICK = EntityDataManager.defineId(JackInBoxZombieEntity.class, DataSerializers.INT);
	public static final int MIN_ANIM_TICK = 20;
	
	public JackInBoxZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		if(! level.isClientSide) {
			this.setExplosionTime();
		}
		this.setBox(true);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_BOX, true);
		this.entityData.define(ANIM_TICK, 0);
	}

	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {
			if(this.getAnimTick() > 0) {
				if(this.getAnimTick() == MIN_ANIM_TICK) {
					EntityUtil.playSound(this, SoundRegister.JACK_SURPRISE.get());
				}
				this.setAnimTick(this.getAnimTick() - 1);
			}
			if(this.hasBox() && this.tickCount % 40 == 0) {
				level.players().forEach((player) -> {
					if(this.distanceToSqr(player) <= 400) {
						player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
							if(l.getPlayerData().getOtherStats().playSoundTick == 0) {
								PlayerUtil.playClientSound(player, 10);
								l.getPlayerData().getOtherStats().playSoundTick = 300;
							}
						});
					}
				});
			}
		}
		if(this.hasBox()) {
			if(this.getAnimTick() == 1) {
			    if(level.isClientSide) {
				    for(int i = 0; i < 2; ++ i) {
					    level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
				    }
			    }
		    } else if(this.getAnimTick() == 0) {
		    	if(! level.isClientSide && this.canExplode()) {
				    this.doExplosion();
				    Explosion.Mode mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
				    this.level.explode(this, getX(), getY(), getZ(), 3f, mode);
			        this.remove();
		    	}
		    }
		}
	}
	
	@Override
	public boolean canLostHead() {
		return super.canLostHead() && ! this.hasBox();
	}
	
	@Override
	protected void setBodyStates(ZombieDropBodyEntity body) {
		super.setBodyStates(body);
		body.setHandDefence(this.hasBox());
	}
	
	private boolean canExplode() {
		if(this.getTarget() == null) return false;
		return this.distanceToSqr(this.getTarget()) <= 300;
	}
	
	@Override
	public int getAmbientSoundInterval() {
		return 200;
	}
	
	private void doExplosion() {
		EntityUtil.getEntityAttackableTarget(this, EntityUtil.getEntityAABB(this, 3, 3)).forEach((entity)->{
			if(entity instanceof LivingEntity) {
				entity.hurt(PVZDamageSource.causeExplosionDamage(this, this), EntityUtil.getCurrentMaxHealth((LivingEntity) entity) * 2);
			}
		});
		EntityUtil.playSound(this, SoundRegister.CAR_EXPLOSION.get());
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.FAST);
	}
	
	private void setExplosionTime() {
		int min = 200, max = 2400;
	    this.setAnimTick(this.getRandom().nextInt(max - min + 1) + min);
	}
	
	@Override
	public float getLife() {
		return 40;
	}
	
	@Override
	public boolean hasMetal() {
		return this.hasBox();
	}

	@Override
	public void decreaseMetal() {
		this.setBox(false);
	}

	@Override
	public void increaseMetal() {
		this.setBox(true);
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.JACK_BOX;
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("has_jack_box")) {
			this.setBox(compound.getBoolean("has_jack_box"));
		}
		if(compound.contains("jack_anim_tick")) {
			this.setAnimTick(compound.getInt("jack_anim_tick"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("has_jack_box", this.hasBox());
		compound.putInt("jack_anim_tick", this.getAnimTick());
	}
	
	public void setBox(boolean has) {
		this.entityData.set(HAS_BOX, has);
	}
	
	public boolean hasBox() {
		return this.entityData.get(HAS_BOX);
	}
	
	public void setAnimTick(int tick) {
		this.entityData.set(ANIM_TICK, tick);
	}
	
	public int getAnimTick() {
		return this.entityData.get(ANIM_TICK);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.JACK_IN_BOX_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.JACK_IN_BOX_ZOMBIE;
	}

}
