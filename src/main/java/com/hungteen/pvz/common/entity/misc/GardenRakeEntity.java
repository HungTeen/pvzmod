package com.hungteen.pvz.common.entity.misc;

import java.util.List;

import com.hungteen.pvz.common.entity.AbstractOwnerEntity;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.level.Level;

public class GardenRakeEntity extends AbstractOwnerEntity {
	
    private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.defineId(GardenRakeEntity.class, DataSerializers.INT);
	
	public GardenRakeEntity(EntityType<?> entityTypeIn, Level worldIn) {
		super(entityTypeIn, worldIn);
		this.setDeltaMovement(Vector3d.ZERO);
	}
	
	public GardenRakeEntity(Level worldIn, LivingEntity livingEntityIn) {
		super(EntityRegister.GARDEN_RAKE.get(), worldIn, livingEntityIn);
		this.yRot = livingEntityIn.getDirection().toYRot();
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ATTACK_TIME, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! level.isClientSide) {
			if(this.isStartAttack()) {
				this.setAttackTime(this.getAttackTime() + 1);
				if(this.getAttackTime() >= this.getAnimTime()) {
					this.dealDamage();
				}
			} else {
				List<Entity> list = this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(0.2D), (target) -> {
			        return EntityUtil.canTargetEntity(this, target);
		        });
				if(! list.isEmpty()) {
			       this.onStartAttack();
				}
			}
		}
		this.tickMove();
	}
	
	private void dealDamage() {
		this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(0.25D), (target) -> {
	        return EntityUtil.canTargetEntity(this, target);
        }).forEach((target) -> {
        	target.hurt(PVZEntityDamageSource.normal(this), 180F);
        });
		EntityUtil.playSound(this, SoundRegister.SWING.get());
		this.remove();
	}
	
	protected void onStartAttack() {
		this.setAttackTime(1);
	}
	
	@Override
	public InteractionResult interactAt(Player player, Vector3d vec3d, InteractionHand hand) {
		if(! this.isStartAttack() && hand == InteractionHand.MAIN_HAND && player.getMainHandItem().isEmpty()) {
			if(! level.isClientSide) {
				player.addItem(new ItemStack(ItemRegister.GARDEN_RAKE.get()));
			    this.remove();
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}
	
	@Override
	public boolean isPickable() {
		return true;
	}
	
	public boolean isStartAttack() {
		return this.getAttackTime() > 0;
	}
	
	public int getAnimTime() {
		return 10;
	}
	
	public void setPlacer(Player player) {
		this.setOwner(player);
		this.yRot = player.getDirection().toYRot();
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9F, 0.8F);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("rake_attack_time")) {
			this.setAttackTime(compound.getInt("rake_attack_time"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("rake_attack_time", this.getAttackTime());
	}
	
	public int getAttackTime() {
		return entityData.get(ATTACK_TIME);
	}

	public void setAttackTime(int cd) {
		entityData.set(ATTACK_TIME, cd);
	}

	
}
