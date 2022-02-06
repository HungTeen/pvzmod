package com.hungteen.pvz.common.item.blockitem;

import javax.annotation.Nullable;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.block.special.SlotMachineBlock;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SlotMachineItem extends BlockItem {

	public SlotMachineItem() {
		super(BlockRegister.SLOT_MACHINE.get(), new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL));
	}

	/**
	 * copy from super.
	 */
	@Override
	public ActionResultType place(BlockItemUseContext p_195942_1_) {
		if (!p_195942_1_.canPlace()) {
			return ActionResultType.FAIL;
		} else {
			BlockItemUseContext blockitemusecontext = this.updatePlacementContext(p_195942_1_);
			if (blockitemusecontext == null) {
				return ActionResultType.FAIL;
			} else {
				BlockState blockstate = this.getPlacementState(blockitemusecontext);
				if (blockstate == null) {
					return ActionResultType.FAIL;
				} else if (!this.placeBlock(blockitemusecontext, blockstate)) {
					return ActionResultType.FAIL;
				} else {
					BlockPos blockpos = blockitemusecontext.getClickedPos();
					World world = blockitemusecontext.getLevel();
					PlayerEntity playerentity = blockitemusecontext.getPlayer();
					ItemStack itemstack = blockitemusecontext.getItemInHand();
					BlockState blockstate1 = world.getBlockState(blockpos);
					Block block = blockstate1.getBlock();
					if (block == blockstate.getBlock()) {
						blockstate1 = this.updateBlockStateFromTag(blockpos, world, itemstack, blockstate1);
						this.updateCustomBlockEntityTag(blockpos, world, playerentity, itemstack, blockstate1);
						block.setPlacedBy(world, blockpos, blockstate1, playerentity, itemstack);
						if (playerentity instanceof ServerPlayerEntity) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerentity, blockpos,
									itemstack);
						}
					}

					SoundType soundtype = blockstate1.getSoundType(world, blockpos, p_195942_1_.getPlayer());
					world.playSound(playerentity, blockpos,
							this.getPlaceSound(blockstate1, world, blockpos, p_195942_1_.getPlayer()),
							SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					if (playerentity == null || !playerentity.abilities.instabuild) {
						itemstack.shrink(1);
					}

					return ActionResultType.sidedSuccess(world.isClientSide);
				}
			}
		}
	}

	/**
	 * copy from super.
	 */
	protected boolean updateCustomBlockEntityTag(BlockPos p_195943_1_, World p_195943_2_,
			@Nullable PlayerEntity p_195943_3_, ItemStack p_195943_4_, BlockState p_195943_5_) {
		return updateCustomBlockEntityTag(p_195943_2_, p_195943_3_, p_195943_1_, p_195943_4_);
	}
	
	/**
	 * copy from super.
	 */
	public static boolean updateCustomBlockEntityTag(World p_179224_0_, @Nullable PlayerEntity p_179224_1_,
			BlockPos p_179224_2_, ItemStack stack) {
		MinecraftServer minecraftserver = p_179224_0_.getServer();
		if (minecraftserver == null) {
			return false;
		} else {
			CompoundNBT compoundnbt = stack.getTagElement("BlockEntityTag");
			if (compoundnbt != null) {
				TileEntity tileentity = p_179224_0_.getBlockEntity(p_179224_2_);
				if (tileentity instanceof SlotMachineTileEntity) {
					if (!p_179224_0_.isClientSide && tileentity.onlyOpCanSetNbt()
							&& (p_179224_1_ == null || !p_179224_1_.canUseGameMasterBlocks())) {
						return false;
					}
					
					//new add.
					((SlotMachineTileEntity) tileentity).init(SlotMachineBlock.getResourceTag(stack));

					CompoundNBT compoundnbt1 = tileentity.save(new CompoundNBT());
					CompoundNBT compoundnbt2 = compoundnbt1.copy();
					compoundnbt1.merge(compoundnbt);
					compoundnbt1.putInt("x", p_179224_2_.getX());
					compoundnbt1.putInt("y", p_179224_2_.getY());
					compoundnbt1.putInt("z", p_179224_2_.getZ());
					if (!compoundnbt1.equals(compoundnbt2)) {
						tileentity.load(p_179224_0_.getBlockState(p_179224_2_), compoundnbt1);
						tileentity.setChanged();
						return true;
					}
				}
			}

			return false;
		}
	}

	/**
	 * copy from super.
	 */
	private BlockState updateBlockStateFromTag(BlockPos p_219985_1_, World p_219985_2_, ItemStack p_219985_3_,
			BlockState p_219985_4_) {
		BlockState blockstate = p_219985_4_;
		CompoundNBT compoundnbt = p_219985_3_.getTag();
		if (compoundnbt != null) {
			CompoundNBT compoundnbt1 = compoundnbt.getCompound("BlockStateTag");
			StateContainer<Block, BlockState> statecontainer = p_219985_4_.getBlock().getStateDefinition();

			for (String s : compoundnbt1.getAllKeys()) {
				Property<?> property = statecontainer.getProperty(s);
				if (property != null) {
					String s1 = compoundnbt1.get(s).getAsString();
					blockstate = updateState(blockstate, property, s1);
				}
			}
		}

		if (blockstate != p_219985_4_) {
			p_219985_2_.setBlock(p_219985_1_, blockstate, 2);
		}

		return blockstate;
	}

	/**
	 * copy from super.
	 */
	private static <T extends Comparable<T>> BlockState updateState(BlockState p_219988_0_, Property<T> p_219988_1_,
			String p_219988_2_) {
		return p_219988_1_.getValue(p_219988_2_).map((p_219986_2_) -> {
			return p_219988_0_.setValue(p_219988_1_, p_219986_2_);
		}).orElse(p_219988_0_);
	}

}
