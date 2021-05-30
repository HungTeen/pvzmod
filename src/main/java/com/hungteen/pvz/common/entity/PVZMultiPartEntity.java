package com.hungteen.pvz.common.entity;

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
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class PVZMultiPartEntity extends Entity {

	private static final DataParameter<Integer> OWNER_ID = EntityDataManager.defineId(PVZMultiPartEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Float> WIDTH = EntityDataManager.defineId(PVZMultiPartEntity.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> HEIGHT = EntityDataManager.defineId(PVZMultiPartEntity.class,
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
		super(entityTypeIn, owner.level);
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
	protected void defineSynchedData() {
		this.entityData.define(OWNER_ID, Integer.valueOf(0));
		this.entityData.define(WIDTH, 0.5F);
		this.entityData.define(HEIGHT, 0.5F);
	}

	@Override
	public void tick() {
		refreshDimensions();
		if (! level.isClientSide) {
			LivingEntity owner = this.getOwner();
			if (owner != null) {
				this.markHurt();
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
		Entity entity = level.getEntity(id);
		return entity instanceof LivingEntity ? (LivingEntity) entity : null;
	}
	
	public IMultiPartEntity getParent() {
		return this.parent;
	}

	public void setOwner(LivingEntity entity) {
		this.setOwnerId(entity.getId());
	}

	@Override
	public boolean is(Entity entity) {
		return this == entity || this.getOwner() == entity;
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	/**
	 * collide with entities
	 */
	public void collideWithNearbyEntities() {
	}

	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		return ActionResultType.FAIL;
	}

	@Override
	public boolean hurt(DamageSource source, float damage) {
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
	protected void readAdditionalSaveData(CompoundNBT compound) {

	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(getPartWidth(), getPartHeight());
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private int getOwnerId() {
		return this.entityData.get(OWNER_ID).intValue();
	}

	public void setOwnerId(int OwnerId) {
		this.entityData.set(OWNER_ID, OwnerId);
	}

	public float getPartWidth() {
		return this.entityData.get(WIDTH).floatValue();
	}

	public void setPartWidth(float scale) {
		this.entityData.set(WIDTH, scale);
	}

	public float getPartHeight() {
		return this.entityData.get(HEIGHT).floatValue();
	}

	public void setPartHeight(float scale) {
		this.entityData.set(HEIGHT, scale);
	}
	
}