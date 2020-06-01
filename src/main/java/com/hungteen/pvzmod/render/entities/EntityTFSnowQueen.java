package com.hungteen.pvzmod.render.entities;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityTFSnowQueen extends EntityMob implements IEntityMultiPart {

	private static final int MAX_SUMMONS = 6;
	private static final DataParameter<Boolean> BEAM_FLAG = EntityDataManager.createKey(EntityTFSnowQueen.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Byte> PHASE_FLAG = EntityDataManager.createKey(EntityTFSnowQueen.class, DataSerializers.BYTE);
	private final BossInfoServer bossInfo = new BossInfoServer(getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS);
	private static final int MAX_DAMAGE_WHILE_BEAMING = 25;
	private static final float BREATH_DAMAGE = 4.0F;

	public enum Phase {SUMMON, DROP, BEAM}

	public final Entity[] iceArray = new Entity[7];

	private int summonsRemaining = 0;
	private int successfulDrops;
	private int maxDrops;
	private int damageWhileBeaming;

	public EntityTFSnowQueen(World world) {
		super(world);
		this.setSize(0.7F, 2.2F);

		for (int i = 0; i < this.iceArray.length; i++) {
			this.iceArray[i] = new EntityTFSnowQueenIceShield(this);
		}

		this.setCurrentPhase(Phase.SUMMON);

		this.isImmuneToFire = true;
		this.experienceValue = 317;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(6, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(BEAM_FLAG, false);
		dataManager.register(PHASE_FLAG, (byte) 0);
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!world.isRemote) {
			bossInfo.setPercent(getHealth() / getMaxHealth());
		} else {
		}
	}


	@Override
	public void onUpdate() {
		super.onUpdate();

		for (int i = 0; i < this.iceArray.length; i++) {

			this.iceArray[i].onUpdate();

			if (i < this.iceArray.length - 1) {
				// set block position
				Vec3d blockPos = this.getIceShieldPosition(i);

				this.iceArray[i].setPosition(blockPos.x, blockPos.y, blockPos.z);
				this.iceArray[i].rotationYaw = this.getIceShieldAngle(i);
			} else {
				// last block beneath
				this.iceArray[i].setPosition(this.posX, this.posY +2, this.posZ);
				this.iceArray[i].rotationYaw = this.getIceShieldAngle(i);
			}

			// collide things with the block
			if (!world.isRemote) {
				this.applyShieldCollisions(this.iceArray[i]);
			}
		}

		// death animation
		if (deathTime > 0) {
			for (int k = 0; k < 5; k++) {
				double d = rand.nextGaussian() * 0.02D;
				double d1 = rand.nextGaussian() * 0.02D;
				double d2 = rand.nextGaussian() * 0.02D;
				EnumParticleTypes explosionType = rand.nextBoolean() ? EnumParticleTypes.EXPLOSION_HUGE : EnumParticleTypes.EXPLOSION_NORMAL;

				world.spawnParticle(explosionType, (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
			}
		}
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void despawnEntity() {
		if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			setDead();
		} else {
			super.despawnEntity();
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		// mark the tower as defeated
	}

	private void applyShieldCollisions(Entity collider) {
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(collider, collider.getEntityBoundingBox().grow(-0.2F, -0.2F, -0.2F));

		for (Entity collided : list) {
			if (collided.canBePushed()) {
				applyShieldCollision(collider, collided);
			}
		}
	}

	/**
	 * Do the effect where the shield hits something
	 */
	private void applyShieldCollision(Entity collider, Entity collided) {
		if (collided != this) {
			collided.applyEntityCollision(collider);
			if (collided instanceof EntityLivingBase && super.attackEntityAsMob(collided)) {
				collided.motionY += 0.4;
				this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.0F);
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		boolean result = super.attackEntityFrom(source, damage);

		if (result && this.getCurrentPhase() == Phase.BEAM) {
			this.damageWhileBeaming += damage;
		}

		return result;

	}

	private Vec3d getIceShieldPosition(int idx) {
		return this.getIceShieldPosition(getIceShieldAngle(idx), 1F);
	}

	private float getIceShieldAngle(int idx) {
		return 60F * idx + (this.ticksExisted * 5F);
	}

	private Vec3d getIceShieldPosition(float angle, float distance) {
		double dx = 3*Math.cos((angle) * Math.PI / 180.0D) * distance;
		double dz = 3*Math.sin((angle) * Math.PI / 180.0D) * distance;

		return new Vec3d(this.posX + dx, this.posY + this.getShieldYOffset(), this.posZ + dz);
	}

	private double getShieldYOffset() {
		return 0.1F;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	public World getWorld() {
		return this.world;
	}

	@Override
	public boolean attackEntityFromPart(MultiPartEntityPart part, DamageSource source, float damage) {
		return false;
	}

	/**
	 * We need to do this for the bounding boxes on the parts to become active
	 */
	@Override
	public Entity[] getParts() {
		return iceArray;
	}


	public Phase getCurrentPhase() {
		return Phase.values()[dataManager.get(PHASE_FLAG)];
	}

	public void setCurrentPhase(Phase currentPhase) {
		dataManager.set(PHASE_FLAG, (byte) currentPhase.ordinal());

		// set variables for current phase
		if (currentPhase == Phase.SUMMON) {
			this.setSummonsRemaining(MAX_SUMMONS);
		}
		if (currentPhase == Phase.DROP) {
			this.successfulDrops = 0;
			this.maxDrops = 2 + this.rand.nextInt(3);
		}
		if (currentPhase == Phase.BEAM) {
			this.damageWhileBeaming = 0;
		}
	}

	public int getSummonsRemaining() {
		return summonsRemaining;
	}

	public void setSummonsRemaining(int summonsRemaining) {
		this.summonsRemaining = summonsRemaining;
	}


	public void incrementSuccessfulDrops() {
		this.successfulDrops++;
	}


	@Override
	public void setCustomNameTag(String name) {
		super.setCustomNameTag(name);
		this.bossInfo.setName(this.getDisplayName());
	}

	@Override
	public void addTrackingPlayer(EntityPlayerMP player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(EntityPlayerMP player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (this.hasCustomName())
			this.bossInfo.setName(this.getDisplayName());
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}
}