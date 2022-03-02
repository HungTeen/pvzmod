package com.hungteen.pvz.common.item.spawn.card;

import com.hungteen.pvz.api.types.ICoolDown;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.render.itemstack.ImitaterCardISTER;
import com.hungteen.pvz.common.container.ImitaterContainer;
import com.hungteen.pvz.common.container.inventory.ItemInventory;
import com.hungteen.pvz.common.entity.plant.magic.ImitaterEntity;
import com.hungteen.pvz.common.impl.plant.PlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ImitaterCardItem extends PlantCardItem {

	public static final String IMITATE_STRING = "imitate_plant_type";
	
	public ImitaterCardItem() {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_PLANT_CARD).stacksTo(1).setISTER(() -> ImitaterCardISTER::new), PVZPlants.IMITATER, false);
	}

	public ImitaterCardItem(boolean isEnjoyCard) {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_PLANT_CARD).stacksTo(16), PVZPlants.IMITATER, isEnjoyCard);
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand handIn) {
		ItemStack heldStack = player.getItemInHand(handIn);
		final ItemStack plantStack = getImitatedCard(heldStack);
		/* left hand to open gui */
		if(handIn == Hand.OFF_HAND) {
			if(! world.isClientSide) {
				this.openImitateGui(player);
			}
			return ActionResult.success(heldStack);
		}
		/* imitated card use */
		if(plantStack.getItem() instanceof PlantCardItem) {
			return super.use(world, player, handIn);
		}
		return ActionResult.fail(heldStack);
	}
	
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		final PlayerEntity player = context.getPlayer();
		final ItemStack heldStack = context.getItemInHand();
		final ItemStack plantStack = getImitatedCard(heldStack);
		/* left hand click means open gui */
		if(context.getHand() == Hand.OFF_HAND) {
			if(! player.level.isClientSide) {
				this.openImitateGui(player);
			}
			return ActionResultType.SUCCESS;
		}
		/* imitated card use on block */ 
		if(plantStack.getItem() instanceof PlantCardItem) {
			return super.useOn(context);
		}
		return ActionResultType.FAIL;
	}
	
	private void openImitateGui(PlayerEntity player) {
		if (player instanceof ServerPlayerEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {

				@Override
				public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
					return new ImitaterContainer(id, player);
				}

				@Override
				public ITextComponent getDisplayName() {
					return new TranslationTextComponent("gui.pvz.imitater.show");
				}
				
			});
		}
	}
	
	public static boolean summonImitater(PlayerEntity player, ItemStack heldStack, ItemStack plantStack, PlantCardItem cardItem, BlockPos pos, Consumer<ImitaterEntity> consumer) {
		return PlantCardItem.handlePlantEntity(player, PVZPlants.IMITATER, plantStack, pos, i -> {
			if(i instanceof ImitaterEntity) {
				final ImitaterEntity imitater = (ImitaterEntity) i;
				imitater.setImitateCard(plantStack.copy());
    	        imitater.setDirection(player.getDirection().getOpposite());
		        imitater.onSpawnedByPlayer(player, cardItem.getBasisSunCost(plantStack));
		        /* enchantment effects */
				enchantPlantEntityByCard(imitater, plantStack);
		        consumer.accept(imitater);
		        
		        PlantCardItem.onUsePlantCard(player, heldStack, plantStack, cardItem);
			}
		});
	}
	
	public boolean isPlantTypeEqual(ItemStack stack, PlantType tmp) {
		Optional<IPlantType> opt = getImitatePlantType(stack);
		return opt.isPresent() && opt.get() == tmp;
	}
	
	@Override
	public int getBasisSunCost(ItemStack stack) {
		return super.getBasisSunCost(getDoubleStack(stack).getSecond());
	}
	
	@Override
	public ICoolDown getBasisCoolDown(ItemStack stack) {
		return super.getBasisCoolDown(getDoubleStack(stack).getSecond());
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return false;//can not enchant.
	}
	
	/**
	 * first is imitater card item, second is imitated card item.
	 */
	public static Pair<ItemStack, ItemStack> getDoubleStack(ItemStack stack){
		final Inventory inv = getInventory(stack);
		return inv != null ? Pair.of(stack, inv.getItem(0)) : Pair.of(stack, stack);
	}
	
	public static Optional<IPlantType> getImitatePlantType(ItemStack stack) {
		final Inventory inv = getInventory(stack);
		if(inv != null) {
			final ItemStack itemstack = getInventory(stack).getItem(0);
			if(itemstack.getItem() instanceof PlantCardItem) {
				return Optional.ofNullable(((PlantCardItem) itemstack.getItem()).plantType);
			}
		}
		return Optional.empty();
	}
	
	public static ItemStack getImitatedCard(ItemStack stack) {
		return getInventory(stack).getItem(0);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(this.isEnjoyCard){
			return;
		}
		Optional<IPlantType> opt = getImitatePlantType(stack);
		if(! opt.isPresent()) {
			tooltip.add(new TranslationTextComponent("tooltip.pvz.imitater_card.empty").withStyle(TextFormatting.RED));
		} else {
			tooltip.add(new TranslationTextComponent("tooltip.pvz.imitater_card.full", opt.get().getText().getString()).withStyle(TextFormatting.LIGHT_PURPLE));
		    super.appendHoverText(getDoubleStack(stack).getSecond(), worldIn, tooltip, flagIn);
		}
	}
	
	@Nonnull
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT oldCapNbt) {
		return new InvProvider(stack);
	}

	@Nullable
	public static Inventory getInventory(ItemStack stack) {
		return (stack.getItem() instanceof ImitaterCardItem) ? new ItemInventory(stack, 1) {
			@Override
			public boolean canPlaceItem(int slot, @Nonnull ItemStack stack) {
				return isValidImitateSlot(stack);
			}
		} : null;
	}
	
	public static boolean isValidImitateSlot(ItemStack stack) {
		return (! (stack.getItem() instanceof PlantCardItem)) ? false : 
			! ((PlantCardItem) stack.getItem()).isEnjoyCard;
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
