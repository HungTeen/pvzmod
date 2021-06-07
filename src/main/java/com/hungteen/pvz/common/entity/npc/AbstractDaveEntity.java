package com.hungteen.pvz.common.entity.npc;

import javax.annotation.Nullable;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.interfaces.IGroupEntity;
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
import net.minecraft.world.server.ServerWorld;

public class AbstractDaveEntity extends CreatureEntity implements IGroupEntity {

	@Nullable
	private PlayerEntity customer;

	public AbstractDaveEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.refreshDimensions();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(3, new LookAtCustomerGoal(this));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.25D) {
			
			@Override
			public boolean canUse() {
				if(this.mob instanceof CrazyDaveEntity) {
					return super.canUse() && ((CrazyDaveEntity) this.mob).getCustomer() == null;
				}
				return super.canUse();
			}
		});
	}
	
	@Override
	public int getAmbientSoundInterval() {
		return 180;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9f, 2.6f);
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
	public Entity changeDimension(ServerWorld level, net.minecraftforge.common.util.ITeleporter teleporter) {
		this.resetCustomer();
		return super.changeDimension(level, teleporter);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}
	
	protected void resetCustomer() {
		this.setCustomer((PlayerEntity) null);
	}

	public void die(DamageSource cause) {
		super.die(cause);
		this.resetCustomer();
	}

	public boolean canBeLeashed(PlayerEntity player) {
		return false;
	}

	public static class LookAtCustomerGoal extends LookAtGoal {
		private final AbstractDaveEntity dave;

		public LookAtCustomerGoal(AbstractDaveEntity dave) {
			super(dave, PlayerEntity.class, 8.0F);
			this.dave = dave;
		}

		public boolean canUse() {
			if (this.dave.hasCustomer()) {
				this.lookAt = this.dave.getCustomer();
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public PVZGroupType getEntityGroupType() {
		return PVZGroupType.CREATURES;
	}
	
}