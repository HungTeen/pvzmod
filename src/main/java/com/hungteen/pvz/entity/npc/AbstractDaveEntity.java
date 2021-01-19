package com.hungteen.pvz.entity.npc;

import javax.annotation.Nullable;

import com.hungteen.pvz.register.SoundRegister;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class AbstractDaveEntity extends CreatureEntity {

	@Nullable
	private PlayerEntity customer;

	public AbstractDaveEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(3, new LookAtCustomerGoal(this));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.25D) {
			
			@Override
			public boolean shouldExecute() {
				if(this.creature instanceof CrazyDaveEntity) {
					return super.shouldExecute() && ((CrazyDaveEntity) this.creature).getCustomer() == null;
				}
				return super.shouldExecute();
			}
		});
	}
	
	@Override
	public int getTalkInterval() {
		return 180;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.9f, 2.6f);
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundRegister.DAVE_HURT.get();
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundRegister.DAVE_DIE.get();
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.DAVE_SAY.get();
	}

	public void setCustomer(@Nullable PlayerEntity player) {
		this.customer = player;
	}

	@Nullable
	public PlayerEntity getCustomer() {
		return this.customer;
	}

	public boolean hasCustomer() {
		return this.customer != null;
	}

	@Nullable
	public Entity changeDimension(DimensionType destination, net.minecraftforge.common.util.ITeleporter teleporter) {
		this.resetCustomer();
		return super.changeDimension(destination, teleporter);
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}
	
	protected void resetCustomer() {
		this.setCustomer((PlayerEntity) null);
	}

	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		this.resetCustomer();
	}

	public boolean canBeLeashedTo(PlayerEntity player) {
		return false;
	}

	public static class LookAtCustomerGoal extends LookAtGoal {
		private final AbstractDaveEntity dave;

		public LookAtCustomerGoal(AbstractDaveEntity dave) {
			super(dave, PlayerEntity.class, 8.0F);
			this.dave = dave;
		}

		public boolean shouldExecute() {
			if (this.dave.hasCustomer()) {
				this.closestEntity = this.dave.getCustomer();
				return true;
			} else {
				return false;
			}
		}
	}
	
}