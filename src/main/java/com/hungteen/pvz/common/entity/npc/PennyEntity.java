package com.hungteen.pvz.common.entity.npc;

import com.hungteen.pvz.common.container.provider.PVZContainerProvider;
import com.hungteen.pvz.common.container.shop.PennyShopContainer;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class PennyEntity extends AbstractDaveEntity {

	public PennyEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setInvulnerable(true);
		this.transactionResource = StringUtil.prefix("penny");
	}

	@Override
	protected boolean canOpenShop(PlayerEntity player, ItemStack heldItem) {
		return heldItem.getItem().equals(ItemRegister.CAR_KEY.get());
	}

	@Override
	protected void openContainer(ServerPlayerEntity player) {
		NetworkHooks.openGui(player, new PVZContainerProvider() {

			@Override
			public Container createMenu(int id, PlayerInventory inventory,
										PlayerEntity playerEntity) {
				return new PennyShopContainer(id, playerEntity, PennyEntity.this.getId());
			}

		}, buffer -> {
			buffer.writeInt(PennyEntity.this.getId());
		});
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

}
