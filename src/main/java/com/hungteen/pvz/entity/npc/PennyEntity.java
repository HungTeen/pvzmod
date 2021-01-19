package com.hungteen.pvz.entity.npc;

import javax.annotation.Nullable;

import com.hungteen.pvz.gui.container.shop.PennyShopContainer;
import com.hungteen.pvz.register.SoundRegister;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class PennyEntity extends AbstractDaveEntity {

	public PennyEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		if (!world.isRemote && player instanceof ServerPlayerEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {

				@Override
				public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
						PlayerEntity p_createMenu_3_) {
					return new PennyShopContainer(p_createMenu_1_, p_createMenu_3_);
				}

				@Override
				public ITextComponent getDisplayName() {
					return new TranslationTextComponent("gui.pvz.penny_shop.show");
				}
			});
			return true;
		}
		return false;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(1.8f, 2f);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.PENNY_SAY.get();
	}
	
	@Nullable
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_GENERIC_HURT;
	}

	@Nullable
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_GENERIC_DEATH;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return true;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

}
