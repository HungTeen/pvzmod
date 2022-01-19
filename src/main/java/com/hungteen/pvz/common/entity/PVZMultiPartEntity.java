package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IHasMultiPart;
import net.minecraft.entity.*;
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

import javax.annotation.Nullable;

public abstract class PVZMultiPartEntity extends Entity {

	private static final DataParameter<Integer> OWNER_ID = EntityDataManager.defineId(PVZMultiPartEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Float> WIDTH = EntityDataManager.defineId(PVZMultiPartEntity.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> HEIGHT = EntityDataManager.defineId(PVZMultiPartEntity.class,
			DataSerializers.FLOAT);
	private IHasMultiPart parent;
	protected final float MaxHeight;
	protected final float MaxWidth;

	public PVZMultiPartEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.MaxHeight = 0.5F;
		this.MaxWidth = 0.5F;
	}

	public PVZMultiPartEntity(EntityType<?> entityTypeIn, LivingEntity owner, float sizeX, float sizeY) {
		super(entityTypeIn, owner.level);
		if(owner instanceof IHasMultiPart) {
			this.parent = (IHasMultiPart) owner;
		} else {
			PVZMod.LOGGER.warn("Error Multipart Owner");
		}
		this.setOwner(owner);
		this.MaxWidth = sizeX;
		this.MaxHeight = sizeY;
		this.setPartWidth(this.MaxWidth);
		this.setPartHeight(this.MaxHeight);
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(OWNER_ID, Integer.valueOf(0));
		this.entityData.define(WIDTH, 0.5F);
		this.entityData.define(HEIGHT, 0.5F);
	}

	@Override
	public void tick() {
		if(this.tickCount <= 5) {
			refreshDimensions();
		}
		if (! level.isClientSide) {
			if (this.canExist()) {//has owner.
				this.markHurt();
				this.collideWithNearbyEntities();
			} else {
				this.remove();
			}
		}
		super.tick();
	}
	
	/**
	 * {@link #tick()}
	 */
	public boolean canExist() {
		return EntityUtil.isEntityValid(this.getOwner());
	}

	/**
	 * get the owner of current part.
	 */
	@Nullable
	public LivingEntity getOwner() {
		final int id = this.getOwnerId();
		Entity entity = level.getEntity(id);
		return entity instanceof LivingEntity ? (LivingEntity) entity : null;
	}
	
	public IHasMultiPart getParent() {
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
	 * collide with entities.
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

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(this.getPartWidth(), this.getPartHeight());
	}
	
	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
	}
	
	private int getOwnerId() {
		return this.entityData.get(OWNER_ID);
	}

	public void setOwnerId(int OwnerId) {
		this.entityData.set(OWNER_ID, OwnerId);
	}

	public float getPartWidth() {
		return this.entityData.get(WIDTH);
	}

	public void setPartWidth(float scale) {
		this.entityData.set(WIDTH, scale);
	}

	public float getPartHeight() {
		return this.entityData.get(HEIGHT);
	}

	public void setPartHeight(float scale) {
		this.entityData.set(HEIGHT, scale);
	}
	
	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
}