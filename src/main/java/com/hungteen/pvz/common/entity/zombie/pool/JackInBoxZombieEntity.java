package com.hungteen.pvz.common.entity.zombie.pool;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.impl.ZombieType;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class JackInBoxZombieEntity extends PVZZombieEntity implements IHasMetal {

	private static final DataParameter<Boolean> HAS_BOX = EntityDataManager.defineId(JackInBoxZombieEntity.class, DataSerializers.BOOLEAN);
	public static final int JACK_EXPLODE_CD = 30;
	private final int MinExplodeTime = 300;
	private final int MaxExplodeTime = 3000;
	
	public JackInBoxZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setExplosionTime();
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_BOX, true);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new PlayJackBoxGoal(this));
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.updateSpeed(true);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide && this.hasBox()) {
			final int tick = this.getAttackTime();
			if(tick < 0) {
				if(tick == -1 && this.canJackExplode()) {
					this.setAttackTime(JACK_EXPLODE_CD);
					EntityUtil.playSound(this, SoundRegister.JACK_SURPRISE.get());
				} else {
					this.setAttackTime(Math.min(tick + 1, - 1));
				}
			} else {
				if(tick == 1) {
					this.doJackExplode();
				}
				this.setAttackTime(Math.max(tick - 1, 0));
			}
		}
		if(this.level.isClientSide && this.hasBox() && this.getAttackTime() == 3) {
			for(int i = 0; i < 2; ++ i) {
			    level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
		    }
		}
	}
	
	/**
	 * jack box explode.
	 * {@link #normalZombieTick()}
	 */
	private void doJackExplode() {
		final float range =  5F;
		final float damageMultiple = 1.5F;
		EntityUtil.getWholeTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach(target -> {
			final PVZDamageSource source = PVZDamageSource.explode(this);
			if(target instanceof LivingEntity) {
				target.hurt(source, EntityUtil.getMaxHealthDamage((LivingEntity) target, damageMultiple));
			} else {
				target.hurt(source, 100);
			}
		});
		EntityUtil.playSound(this, SoundRegister.CAR_EXPLOSION.get());
		Explosion.Mode mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
		final float strenth = this.level.getDifficulty() == Difficulty.HARD ? 3F : 
			                  this.level.getDifficulty() == Difficulty.NORMAL ? 2.5F : 2F;
		this.level.explode(this, getX(), getY(), getZ(), strenth, mode);
		this.remove();
	}
	
	@Override
	public void onSyncedDataUpdated(DataParameter<?> data) {
		super.onSyncedDataUpdated(data);
		if(data.equals(HAS_BOX)) {
			this.updateSpeed(this.hasBox());
		}
	}
	
	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		return super.canBeTargetBy(living) && ! this.isInExplosion();
	}
	
	@Override
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		return super.isZombieInvulnerableTo(source) || this.isInExplosion();
	}
	
	/**
	 * play sound to surround players.
	 * {@link PlayJackBoxGoal#tick()}
	 */
	protected void playJackBoxSound() {
		level.players().stream().filter(player -> this.distanceToSqr(player) <= 150).forEach(player -> {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				if(l.getPlayerData().getOtherStats().playSoundTick == 0) {
					PlayerUtil.playClientSound(player, SoundRegister.JACK_SAY.get());
					l.getPlayerData().getOtherStats().playSoundTick = 300;
				}
			});
		});
	}
	
	private void updateSpeed(boolean is) {
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.WALK_FAST : ZombieUtil.WALK_LITTLE_FAST);
	}
	
	@Override
	public boolean canLostHand() {
		return super.canLostHand() && ! this.hasBox();
	}
	
	@Override
	protected void setBodyStates(ZombieDropBodyEntity body) {
		super.setBodyStates(body);
		body.setHandDefence(this.hasBox());
	}
	
	/**
	 * can explode if time is enough.
	 * {@link #normalZombieTick()}
	 */
	private boolean canJackExplode() {
		if(this.getTarget() == null) {//no target not explode.
			return false;
		}
		return this.distanceToSqr(this.getTarget()) <= 100;
	}
	
	@Override
	public int getAmbientSoundInterval() {
		return 200;
	}
	
	/**
	 * attack time < 0 means normal, > 0 means in explosion.
	 */
	private void setExplosionTime() {
		this.setAttackTime(- MathUtil.getRandomMinMax(getRandom(), MinExplodeTime, MaxExplodeTime));
	}
	
	@Override
	public float getLife() {
		return 40;
	}
	
	/**
	 * {@link #isZombieInvulnerableTo(DamageSource)}
	 * {@link #canBeTargetBy(LivingEntity)}
	 */
	public boolean isInExplosion() {
		return this.getAttackTime() > 0;
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
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("has_jack_box", this.hasBox());
	}
	
	public void setBox(boolean has) {
		this.entityData.set(HAS_BOX, has);
	}
	
	public boolean hasBox() {
		return this.entityData.get(HAS_BOX);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.JACK_IN_BOX_ZOMBIE;
	}
	
	@Override
    public ZombieType getZombieType() {
	    return PoolZombies.JACK_IN_BOX_ZOMBIE;
    }
	
	static class PlayJackBoxGoal extends Goal{

		private final JackInBoxZombieEntity zombie;
		private final int PlayCD = 50;
		private int delayTick = 20;
		
		public PlayJackBoxGoal(JackInBoxZombieEntity zombie) {
			this.zombie = zombie;
		}
		
		@Override
		public boolean canUse() {
			if(-- this.delayTick > 0 || ! this.zombie.hasBox()) {
				return false;
			}
			return this.zombie.getRandom().nextFloat() < 0.2F;
		}
		
		@Override
		public void start() {
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.zombie.hasBox();
		}
		
		@Override
		public void tick() {
			if(this.zombie.tickCount % this.PlayCD == 0) {
				this.zombie.playJackBoxSound();
			}
		}
		
		@Override
		public void stop() {
			this.delayTick = 20;
		}
		
	}

}
