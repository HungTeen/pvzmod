package com.hungteen.pvz.common.item.tool.plant;

import com.hungteen.pvz.common.container.CardPackContainer;
import com.hungteen.pvz.common.container.inventory.ItemInventory;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.util.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CardPackItem extends Item {

	public static final int SLOT_NUM = 36;
	public CardPackItem() {
		super(new Properties().tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1));
	}

	@Nonnull
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag oldCapNbt) {
		return new InvProvider(stack);
	}

	public static Inventory getInventory(ItemStack stack) {
		return new ItemInventory(stack, SLOT_NUM) {
			@Override
			public boolean canPlaceItem(int slot, @Nonnull ItemStack stack) {
				return isValidItemStack(stack);
			}
		};
	}
	
	public static boolean isValidItemStack(ItemStack stack) {
		return stack.getItem() instanceof SummonCardItem;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		if (!worldIn.isClientSide) {
			if (playerIn instanceof ServerPlayer && handIn == InteractionHand.MAIN_HAND) {
				NetworkHooks.openGui((ServerPlayer) playerIn, new INamedContainerProvider() {
					
					@Override
					public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
							Player p_createMenu_3_) {
						return new CardPackContainer(p_createMenu_1_, p_createMenu_3_);
					}

					@Override
					public Component getDisplayName() {
						return new TranslatableComponent("gui.pvz.card_pack.show");
					}
				});
			}
		}
		return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
	}

	private static class InvProvider implements ICapabilityProvider {

		private final LazyOptional<IItemHandler> opt;

		private InvProvider(ItemStack stack) {
			opt = LazyOptional.of(() -> new InvWrapper(getInventory(stack)));
		}

		@Nonnull
		@Override
		public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(capability, opt);
		}
	}

}
