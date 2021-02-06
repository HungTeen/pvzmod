package com.hungteen.pvz.entity;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.interfaces.IMultiPartEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class PVZMultiPartEntity extends Entity {

	private static final DataParameter<Integer> OWNER_ID = EntityDataManager.createKey(PVZMultiPartEntity.class,
			DataSerializers.VARINT);
	private static final DataParameter<Float> WIDTH = EntityDataManager.createKey(PVZMultiPartEntity.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> HEIGHT = EntityDataManager.createKey(PVZMultiPartEntity.class,
			DataSerializers.FLOAT);
	protected final float MaxWidth;
	protected final float MaxHeight;
	private IMultiPartEntity parent;

	public PVZMultiPartEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.MaxHeight = 0.5F;
		this.MaxWidth = 0.5F;
	}

	public PVZMultiPartEntity(EntityType<?> entityTypeIn, LivingEntity owner, float sizeX, float sizeY) {
		super(entityTypeIn, owner.world);
		if(owner instanceof IMultiPartEntity) {
			this.parent = (IMultiPartEntity) owner;
		} else {
			PVZMod.LOGGER.debug("error multipart owner");
		}
		this.MaxHeight = sizeY;
		this.MaxWidth = sizeX;
		this.setOwner(owner);
		this.setPartWidth(sizeX);
		this.setPartHeight(sizeY);
	}

	@Override
	protected void registerData() {
		this.dataManager.register(OWNER_ID, Integer.valueOf(0));
		this.dataManager.register(WIDTH, 0.5F);
		this.dataManager.register(HEIGHT, 0.5F);
	}

	@Override
	public void tick() {
		recalculateSize();
		if (! world.isRemote) {
			LivingEntity owner = this.getOwner();
			if (owner != null) {
				this.markVelocityChanged();
				this.collideWithNearbyEntities();
				if(! owner.isAlive()) {
					this.remove();
				}
			} else {
				this.remove();
			}
			if(this.shouldNotExist()) {
				this.remove();
			}
		}
		super.tick();
	}

	/**
	 * get owner of this
	 */
	public LivingEntity getOwner() {
		int id = getOwnerId();
		Entity entity = world.getEntityByID(id);
		return entity instanceof LivingEntity ? (LivingEntity) entity : null;
	}
	
	public IMultiPartEntity getParent() {
		return this.parent;
	}

	public void setOwner(LivingEntity entity) {
		this.setOwnerId(entity.getEntityId());
	}

	@Override
	public boolean isEntityEqual(Entity entity) {
		return this == entity || this.getOwner() == entity;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	/**
	 * collide with entities
	 */
	public void collideWithNearbyEntities() {
	}

	@Override
	public boolean processInitialInteract(PlayerEntity player, Hand hand) {
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		return false;
	}

	public boolean shouldNotExist() {
		LivingEntity Owner = getOwner();
		return !Owner.isAlive();
	}

	@SuppressWarnings("deprecation")
	public boolean shouldContinuePersisting() {
		return isAddedToWorld() || this.removed;
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {

	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(getPartWidth(), getPartHeight());
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private int getOwnerId() {
		return this.dataManager.get(OWNER_ID).intValue();
	}

	public void setOwnerId(int OwnerId) {
		this.dataManager.set(OWNER_ID, OwnerId);
	}

	public float getPartWidth() {
		return this.dataManager.get(WIDTH).floatValue();
	}

	public void setPartWidth(float scale) {
		this.dataManager.set(WIDTH, scale);
	}

	public float getPartHeight() {
		return this.dataManager.get(HEIGHT).floatValue();
	}

	public void setPartHeight(float scale) {
		this.dataManager.set(HEIGHT, scale);
	}
	
}