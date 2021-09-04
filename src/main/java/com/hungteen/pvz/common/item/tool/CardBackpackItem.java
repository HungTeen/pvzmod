package com.hungteen.pvz.common.item.tool;

import com.hungteen.pvz.common.item.PVZItemGroups;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CardBackpackItem extends Item {

	public final int MaxSlotNum;
	
	public CardBackpackItem(int num) {
		super(new Properties().tab(PVZItemGroups.PVZ_MISC).stacksTo(1));
		this.MaxSlotNum = num;
	}

//	@Nonnull
//	@Override
//	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT oldCapNbt) {
//		return new InvProvider(stack);
//	}
//
//	public static Inventory getInventory(ItemStack stack) {
//		return new ItemInventory(stack, PEA_GUN_SLOT_NUM) {
//			@Override
//			public boolean canPlaceItem(int slot, @Nonnull ItemStack stack) {
//				if (slot == 0) {
//					return true;
//				} else {
//					return stack.getItem() == ItemRegister.PEA.get() || stack.getItem() == ItemRegister.SNOW_PEA.get()
//							|| stack.getItem() == ItemRegister.FLAME_PEA.get();
//				}
//			}
//		};
//	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (!worldIn.isClientSide) {
//			if (playerIn instanceof ServerPlayerEntity) {
//				NetworkHooks.openGui((ServerPlayerEntity) playerIn, new INamedContainerProvider() {
//					
//					@Override
//					public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
//							PlayerEntity p_createMenu_3_) {
//						return new PeaGunContainer(p_createMenu_1_, p_createMenu_3_);
//					}
//
//					@Override
//					public ITextComponent getDisplayName() {
//						return new TranslationTextComponent("gui.pvz.pea_gun.show");
//					}
//				});
//			}
		}
		return ActionResult.success(playerIn.getItemInHand(handIn));
	}

//	private static class InvProvider implements ICapabilityProvider {
//
//		private final LazyOptional<IItemHandler> opt;
//
//		private InvProvider(ItemStack stack) {
//			opt = LazyOptional.of(() -> new InvWrapper(getInventory(stack)));
//		}
//
//		@Nonnull
//		@Override
//		public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
//			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(capability, opt);
//		}
//	}

}
