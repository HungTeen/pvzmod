package com.hungteen.pvz.common.entity.npc;

import javax.annotation.Nullable;

import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import com.hungteen.pvz.common.container.shop.PennyShopContainer;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.sound.SoundRegister;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class PennyEntity extends AbstractDaveEntity {

	public PennyEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setInvulnerable(true);
	}

	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if (! level.isClientSide && player instanceof ServerPlayerEntity && hand == Hand.MAIN_HAND) {
			if(player.getItemInHand(hand).getItem() == ItemRegister.CAR_KEY.get()) {
				NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {
				    @Override
				    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
					    return new MysteryShopContainer(p_createMenu_1_, p_createMenu_3_);
				    }

				    @Override
				    public ITextComponent getDisplayName() {
					    return new TranslationTextComponent("gui.pvz.mystery_shop.show");
				    }
			    });
				return ActionResultType.SUCCESS;
			} else {
				NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {
				    @Override
				    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
					    return new PennyShopContainer(p_createMenu_1_, p_createMenu_3_);
				    }

				    @Override
				    public ITextComponent getDisplayName() {
					    return new TranslationTextComponent("gui.pvz.penny_shop.show");
				    }
			    });
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(1.8f, 2f);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.PENNY_SAY.get();
	}
	
	@Nullable
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.GENERIC_HURT;
	}

	@Nullable
	protected SoundEvent getDeathSound() {
		return SoundEvents.GENERIC_DEATH;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

}
