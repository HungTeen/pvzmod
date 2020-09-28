package com.hungteen.pvz.entity.npc;

import javax.annotation.Nullable;

import com.hungteen.pvz.gui.container.DaveShopContainer;
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
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.NetworkHooks;

public class CrazyDaveEntity extends CreatureEntity {

	@Nullable
	private PlayerEntity customer;

	public CrazyDaveEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
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
		return 150;
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		if (!world.isRemote && player instanceof ServerPlayerEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {

				@Override
				public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
						PlayerEntity p_createMenu_3_) {
					return new DaveShopContainer(p_createMenu_1_, p_createMenu_3_);
				}

				@Override
				public ITextComponent getDisplayName() {
					return new TranslationTextComponent("gui.pvz.dave_shop.show");
				}
			});
			return true;
		}
		return false;
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
		private final CrazyDaveEntity dave;

		public LookAtCustomerGoal(CrazyDaveEntity dave) {
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
