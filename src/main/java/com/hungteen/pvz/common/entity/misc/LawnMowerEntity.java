package com.hungteen.pvz.common.entity.misc;

import java.util.List;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.entity.AbstractOwnerEntity;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.command.arguments.EntityAnchorArgument.Type;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LawnMowerEntity extends AbstractOwnerEntity {

	private static final DataParameter<Boolean> START_RUN = EntityDataManager.defineId(LawnMowerEntity.class, DataSerializers.BOOLEAN);
	
	public LawnMowerEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.setDeltaMovement(Vector3d.ZERO);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(START_RUN, false);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! this.level.isClientSide) {
			if(this.isInWater() || this.tickCount >= PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.LawnMowerLiveTick.get()) {
				this.remove();
				return ;
			}
			if(this.isStartRun()) {
				this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(0.5D), entity -> {
			        return EntityUtil.canTargetEntity(this.getOwnerOrSelf(), entity);
		        }).forEach(target -> {
		        	this.checkAndRemoveEntity(target);
		        });
				double angle = this.yRot * Math.PI / 180;
				double dx = - Math.sin(angle);
				double dz = Math.cos(angle);
				double speed = 0.4D;
				this.setDeltaMovement(dx * speed, this.getDeltaMovement().y(), dz * speed);
			} else {
				-- tickCount;
				List<Entity> list = this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(1), (target) -> {
					 return EntityUtil.canTargetEntity(this.getOwnerOrSelf(), target);
		        });
				if(! list.isEmpty()) {
			       this.onStartRun(list.get(0));
				}
			}
		}
		if(this.tickCount < 5) {
			BlockPos pos = this.blockPosition();
			this.setPos(pos.getX() + 0.5D, this.getY(), pos.getZ() + 0.5D);
		}
		this.tickMove();
	}
	
	public void checkAndRemoveEntity(Entity target) {
		if(EntityUtil.canEntityBeRemoved(target)) {
    		target.remove();// kill all entity pass by.
    	}
	}
	
	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if(! this.isStartRun() && hand == Hand.MAIN_HAND && player.getMainHandItem().isEmpty()) {
			if(! level.isClientSide) {
				player.addItem(new ItemStack(ItemRegister.LAWN_MOWER.get()));
			    this.remove();
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
	
	@Override
	public boolean isPickable() {
		return true;
	}
	
	private void onStartRun(Entity target) {
		this.lookAt(Type.FEET, target.position());
		this.setStartRun(true);
		EntityUtil.playSound(this, SoundRegister.LAWN_MOWER.get());
	}
	
	public void setPlacer(PlayerEntity player) {
		this.setOwner(player);
		this.yRot = player.getDirection().toYRot();
	}
	
	/**
	 * Updates the entity motion clientside, called by packets from the server
	 */
	@OnlyIn(Dist.CLIENT)
	public void lerpMotion(double x, double y, double z) {
		this.setDeltaMovement(x, y, z);
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			float f = MathHelper.sqrt(x * x + z * z);
			this.yRot = (float) (MathHelper.atan2(x, z) * (double) (180F / (float) Math.PI));
			this.xRot = (float) (MathHelper.atan2(y, (double) f) * (double) (180F / (float) Math.PI));
			this.yRotO = this.yRot;
			this.xRotO = this.xRot;
			this.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot,
					this.xRot);
		}
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 0.8F);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("start_running")) {
			this.setStartRun(compound.getBoolean("start_running"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("start_running", this.isStartRun());
	}
	
	public void setStartRun(boolean is) {
		this.entityData.set(START_RUN, is);
	}
	
	public boolean isStartRun() {
		return this.entityData.get(START_RUN);
	}

}
