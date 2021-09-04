package com.hungteen.pvz.common.item.card;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.hungteen.pvz.client.render.itemstack.ImitaterCardISTER;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.container.ImitaterContainer;
import com.hungteen.pvz.common.container.inventory.ItemInventory;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.enchantment.EnchantmentUtil;
import com.hungteen.pvz.common.entity.plant.magic.ImitaterEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
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

public class ImitaterCardItem extends PlantCardItem {

	public static final String IMITATE_STRING = "imitate_plant_type";
	
	public ImitaterCardItem() {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_PLANT_CARD).stacksTo(1).setISTER(() -> ImitaterCardISTER::new), PVZPlants.IMITATER, false);
	}
	
	public ImitaterCardItem(boolean isFragment) {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_PLANT_CARD).stacksTo(16), PVZPlants.IMITATER, true);
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand handIn) {
		ItemStack stack = player.getItemInHand(handIn);
		if(handIn == Hand.OFF_HAND) {
			this.openImitateGui(player);
			return ActionResult.success(stack);
		}
		RayTraceResult raytraceresult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) return ActionResult.pass(stack);
		if (world.isClientSide) return ActionResult.success(stack);
		Optional<PlantType> opt = getImitatePlantType(stack);
		if(! opt.isPresent() || ! opt.get().isWaterPlant()) return ActionResult.fail(stack);
		if(opt.get() == PVZPlants.FLOWER_POT) return ActionResult.fail(stack);
		BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
		BlockPos pos = blockraytraceresult.getBlockPos();
		if (!(world.getBlockState(pos).getBlock() instanceof FlowingFluidBlock)) return ActionResult.pass(stack);
		if (world.mayInteract(player, pos) && player.mayUseItemAt(pos, blockraytraceresult.getDirection(), stack)) {
			checkSunAndSummonImitater(player, stack, this, pos, (l) -> {});
			return ActionResult.success(stack);
		} else {
			return ActionResult.fail(stack);
		}
	}
	
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getItemInHand(hand);
		BlockPos pos = context.getClickedPos();
		if (world.isClientSide) return ActionResultType.SUCCESS;
		if(hand == Hand.OFF_HAND) {
			this.openImitateGui(player);
			return ActionResultType.SUCCESS;
		}
		Optional<PlantType> opt = getImitatePlantType(stack);
		if(! opt.isPresent() || (opt.get() != PVZPlants.CAT_TAIL && opt.get().getUpgradeFrom().isPresent())) return ActionResultType.FAIL;
		if(opt.get() == PVZPlants.LILY_PAD) return ActionResultType.PASS;
		//place cat tail
		if(opt.get() == PVZPlants.CAT_TAIL) {
			if(world.getBlockState(pos).getBlock() == BlockRegister.LILY_PAD.get()) {
			    checkSunAndSummonImitater(player, stack, this, pos, (imitater) -> {
				    imitater.targetPos = Optional.of(pos);
			    });
			} else {
				return ActionResultType.FAIL;
			}
		}
		BlockPos spawnPos = pos;
		if(! world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
			spawnPos = pos.relative(context.getClickedFace());
		}
		if (context.getClickedFace() == Direction.UP && world.isEmptyBlock(pos.above())) {// can plant here
			checkSunAndSummonImitater(player, stack, this, spawnPos, (l) -> {});
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
	
	public static void checkSunAndSummonImitater(PlayerEntity player, ItemStack stack, PlantCardItem item, BlockPos pos, Consumer<ImitaterEntity> consumer) {
		checkSunAndSummonPlant(player, stack, item, pos, (l) -> {
			if(l instanceof ImitaterEntity) {
				((ImitaterEntity) l).setImitateCard(stack.copy());
				((ImitaterEntity) l).placeDirection = player.getDirection().getOpposite();
				Optional<PlantType> opt = getImitatePlantType(stack);
				if(opt.isPresent()) {
					player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((data) -> {
						l.setPlantLvl(data.getPlayerData().getPlantStats().getPlantLevel(opt.get()));
					});
				}
				consumer.accept((ImitaterEntity) l);
			}
		});
	}
	
	public boolean isPlantTypeEqual(ItemStack stack, PlantType tmp) {
		Optional<PlantType> opt = getImitatePlantType(stack);
		return opt.isPresent() && opt.get() == tmp;
	}
	
	public int getImitateSunCost(ItemStack stack) {
		Optional<PlantType> opt = getImitatePlantType(stack);
		if(! opt.isPresent() || opt.get() == PVZPlants.IMITATER) return - 1;
		int cost = opt.get().getCost();
		return Math.max(cost - EnchantmentUtil.getSunReduceNum(stack), 0);
	}
	
	@Override
	public int getPlantCardCD(PlayerEntity player, ItemStack stack, PlantType plant, int lvl) {
		Optional<PlantType> opt = getImitatePlantType(stack);
		if(! opt.isPresent() || opt.get() == PVZPlants.IMITATER) {
			System.out.println("ERROR : Wrong Use of Imitater Card !");
			return 100;
		}
		return super.getPlantCardCD(player, stack, opt.get(), lvl);
	}
	
	public static Optional<PlantType> getImitatePlantType(ItemStack stack) {
		ItemStack inv = getInventory(stack).getItem(0);
		if(! (inv.getItem() instanceof PlantCardItem)) return Optional.empty();
		PlantCardItem item = (PlantCardItem) inv.getItem();
		return item.isEnjoyCard ? Optional.empty() : Optional.ofNullable(item.plantType);
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
	
	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(this.isEnjoyCard) {
			tooltip.add(new TranslationTextComponent("tooltip.pvz.imitater_card.no").withStyle(TextFormatting.RED));
			return ;
		}
		Optional<PlantType> opt = getImitatePlantType(stack);
		if(! opt.isPresent() || opt.get() == PVZPlants.IMITATER) {
			tooltip.add(new TranslationTextComponent("tooltip.pvz.imitater_card.empty").withStyle(TextFormatting.RED));
			return ;
		}
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslationTextComponent("tooltip.pvz.imitater_card.yes").append(":").append(new TranslationTextComponent("entity.pvz." + opt.get().toString().toLowerCase())).withStyle(TextFormatting.LIGHT_PURPLE));
	}
	
	@Nonnull
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT oldCapNbt) {
		return new InvProvider(stack);
	}

	public static Inventory getInventory(ItemStack stack) {
		return new ItemInventory(stack, 1) {
			@Override
			public boolean canPlaceItem(int slot, @Nonnull ItemStack stack) {
				return isValidImitateSlot(stack);
			}
		};
	}
	
	public static boolean isValidImitateSlot(ItemStack stack) {
		if(! (stack.getItem() instanceof PlantCardItem)) return false;
		PlantCardItem item = (PlantCardItem) stack.getItem();
		if(item.isEnjoyCard || item instanceof ImitaterCardItem) return false;
		return true;
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
