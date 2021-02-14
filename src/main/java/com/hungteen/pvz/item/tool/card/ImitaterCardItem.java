package com.hungteen.pvz.item.tool.card;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.enchantment.EnchantmentUtil;
import com.hungteen.pvz.entity.plant.magic.ImitaterEntity;
import com.hungteen.pvz.gui.container.ImitaterContainer;
import com.hungteen.pvz.gui.inventory.ItemInventory;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.render.itemstack.ImitaterCardISTER;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

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
		super(new Item.Properties().group(GroupRegister.PVZ_CARD).maxStackSize(1).setISTER(() -> ImitaterCardISTER::new), Plants.IMITATER, false);
	}
	
	public ImitaterCardItem(boolean isFragment) {
		super(new Item.Properties().group(GroupRegister.PVZ_CARD).maxStackSize(16), Plants.IMITATER, true);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		if(handIn == Hand.OFF_HAND) {
			this.openImitateGui(player);
			return ActionResult.resultSuccess(stack);
		}
		RayTraceResult raytraceresult = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) return ActionResult.resultPass(stack);
		if (world.isRemote) return ActionResult.resultSuccess(stack);
		Optional<Plants> opt = getImitatePlantType(stack);
		if(! opt.isPresent() || ! opt.get().isWaterPlant) return ActionResult.resultFail(stack);
		if(opt.get() == Plants.FLOWER_POT) return ActionResult.resultFail(stack);
		BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
		BlockPos pos = blockraytraceresult.getPos();
		if (!(world.getBlockState(pos).getBlock() instanceof FlowingFluidBlock)) return ActionResult.resultPass(stack);
		if (world.isBlockModifiable(player, pos) && player.canPlayerEdit(pos, blockraytraceresult.getFace(), stack)) {
			checkSunAndSummonImitater(player, stack, this, pos, (l) -> {});
			return ActionResult.resultSuccess(stack);
		} else {
			return ActionResult.resultFail(stack);
		}
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getHeldItem(hand);
		BlockPos pos = context.getPos();
		if (world.isRemote) return ActionResultType.SUCCESS;
		if(hand == Hand.OFF_HAND) {
			this.openImitateGui(player);
			return ActionResultType.SUCCESS;
		}
		Optional<Plants> opt = getImitatePlantType(stack);
		if(! opt.isPresent() || (opt.get() != Plants.CAT_TAIL && opt.get().isUpgradePlant)) return ActionResultType.FAIL;
		if(opt.get() == Plants.LILY_PAD) return ActionResultType.PASS;
		//place cat tail
		if(opt.get() == Plants.CAT_TAIL) {
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
			spawnPos = pos.offset(context.getFace());
		}
		if (context.getFace() == Direction.UP && world.isAirBlock(pos.up())) {// can plant here
			checkSunAndSummonImitater(player, stack, this, spawnPos, (l) -> {});
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
	
	public static void checkSunAndSummonImitater(PlayerEntity player, ItemStack stack, PlantCardItem item, BlockPos pos, Consumer<ImitaterEntity> consumer) {
		checkSunAndSummonPlant(player, stack, item, pos, (l) -> {
			if(l instanceof ImitaterEntity) {
				((ImitaterEntity) l).setImitateCard(stack.copy());
				((ImitaterEntity) l).placeDirection = player.getHorizontalFacing().getOpposite();
				Optional<Plants> opt = getImitatePlantType(stack);
				if(opt.isPresent()) {
					player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((data) -> {
						l.setPlantLvl(data.getPlayerData().getPlantStats().getPlantLevel(opt.get()));
					});
				}
				consumer.accept((ImitaterEntity) l);
			}
		});
	}
	
	public boolean isPlantTypeEqual(ItemStack stack, Plants tmp) {
		Optional<Plants> opt = getImitatePlantType(stack);
		return opt.isPresent() && opt.get() == tmp;
	}
	
	public int getImitateSunCost(ItemStack stack) {
		Optional<Plants> opt = getImitatePlantType(stack);
		if(! opt.isPresent() || opt.get() == Plants.IMITATER) return - 1;
		int cost = PlantUtil.getPlantSunCost(opt.get());
		return Math.max(cost - EnchantmentUtil.getSunReduceNum(stack), 0);
	}
	
	@Override
	public int getPlantCardCD(PlayerEntity player, ItemStack stack, Plants plant, int lvl) {
		Optional<Plants> opt = getImitatePlantType(stack);
		if(! opt.isPresent() || opt.get() == Plants.IMITATER) {
			System.out.println("ERROR : Wrong Use of Imitater Card !");
			return 100;
		}
		return super.getPlantCardCD(player, stack, opt.get(), lvl);
	}
	
	public static Optional<Plants> getImitatePlantType(ItemStack stack) {
		ItemStack inv = getInventory(stack).getStackInSlot(0);
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
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(this.isEnjoyCard) {
			tooltip.add(new TranslationTextComponent("tooltip.pvz.imitater_card.no").applyTextStyle(TextFormatting.RED));
			return ;
		}
		Optional<Plants> opt = getImitatePlantType(stack);
		if(! opt.isPresent() || opt.get() == Plants.IMITATER) {
			tooltip.add(new TranslationTextComponent("tooltip.pvz.imitater_card.empty").applyTextStyle(TextFormatting.RED));
			return ;
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslationTextComponent("tooltip.pvz.imitater_card.yes").appendText(":").appendSibling(new TranslationTextComponent("entity.pvz." + opt.get().toString().toLowerCase())).applyTextStyle(TextFormatting.LIGHT_PURPLE));
	}
	
	@Nonnull
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT oldCapNbt) {
		return new InvProvider(stack);
	}

	public static Inventory getInventory(ItemStack stack) {
		return new ItemInventory(stack, 1) {
			@Override
			public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
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
