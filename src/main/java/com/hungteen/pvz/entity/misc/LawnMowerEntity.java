package com.hungteen.pvz.entity.misc;

import java.util.List;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.SoundRegister;
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
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LawnMowerEntity extends AbstractOwnerEntity {

	private static final DataParameter<Boolean> START_RUN = EntityDataManager.createKey(LawnMowerEntity.class, DataSerializers.BOOLEAN);
	
	public LawnMowerEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.setMotion(Vec3d.ZERO);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(START_RUN, false);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! this.world.isRemote) {
			if(this.isInWater() || this.ticksExisted >= PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.LawnMowerLiveTick.get()) {
				this.remove();
				return ;
			}
			if(this.isStartRun()) {
				this.world.getEntitiesWithinAABB(Entity.class, this.getBoundingBox().grow(0.5D), (target) -> {
			        return EntityUtil.checkCanEntityAttack(this, target);
		        }).forEach((target) -> {
		        	if(EntityUtil.canEntityBeRemoved(target)) {
		        		target.remove();// kill all entity pass by.
		        	}
		        });
				double angle = this.rotationYaw * Math.PI / 180;
				double dx = - Math.sin(angle);
				double dz = Math.cos(angle);
				double speed = 0.4D;
				this.setMotion(dx * speed, this.getMotion().getY(), dz * speed);
			} else {
				-- ticksExisted;
				List<Entity> list = this.world.getEntitiesWithinAABB(Entity.class, this.getBoundingBox().grow(1), (target) -> {
			        return EntityUtil.checkCanEntityAttack(this, target);
		        });
				if(! list.isEmpty()) {
			       this.onStartRun(list.get(0));
				}
			}
		}
		if(this.ticksExisted < 5) {
			BlockPos pos = this.getPosition();
			this.setPosition(pos.getX() + 0.5D, this.getPosY(), pos.getZ() + 0.5D);
		}
		this.tickMove();
	}
	
	@Override
	public boolean processInitialInteract(PlayerEntity player, Hand hand) {
		if(! this.isStartRun() && hand == Hand.MAIN_HAND && player.getHeldItemMainhand().isEmpty()) {
			if(! world.isRemote) {
				player.addItemStackToInventory(new ItemStack(ItemRegister.LAWN_MOWER.get()));
			    this.remove();
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return true;
	}
	
	private void onStartRun(Entity target) {
		this.lookAt(Type.FEET, target.getPositionVec());
		this.setStartRun(true);
		EntityUtil.playSound(this, SoundRegister.LAWN_MOWER.get());
	}
	
	public void setPlacer(PlayerEntity player) {
		this.setOwner(player);
		this.rotationYaw = player.getHorizontalFacing().getHorizontalAngle();
	}
	
	/**
	 * Updates the entity motion clientside, called by packets from the server
	 */
	@OnlyIn(Dist.CLIENT)
	public void setVelocity(double x, double y, double z) {
		this.setMotion(x, y, z);
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt(x * x + z * z);
			this.rotationYaw = (float) (MathHelper.atan2(x, z) * (double) (180F / (float) Math.PI));
			this.rotationPitch = (float) (MathHelper.atan2(y, (double) f) * (double) (180F / (float) Math.PI));
			this.prevRotationYaw = this.rotationYaw;
			this.prevRotationPitch = this.rotationPitch;
			this.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw,
					this.rotationPitch);
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8F, 0.8F);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("start_running")) {
			this.setStartRun(compound.getBoolean("start_running"));
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("start_running", this.isStartRun());
	}
	
	public void setStartRun(boolean is) {
		this.dataManager.set(START_RUN, is);
	}
	
	public boolean isStartRun() {
		return this.dataManager.get(START_RUN);
	}

}
