package com.hungteen.pvz.block.special;

import com.hungteen.pvz.gui.container.EssenceAltarContainer;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EssenceAltarBlock extends Block {

	public EssenceAltarBlock() {
		super(Block.Properties.copy(BlockRegister.ORIGIN_BLOCK.get()));
	}
	
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (! worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
			NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {
				
				@Override
				public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
					return new EssenceAltarContainer(id, player, IWorldPosCallable.create(player.level, pos));
				}
				
				@Override
				public ITextComponent getDisplayName() {
					return new TranslationTextComponent("gui.pvz.essence_altar.title");
				}
			}, pos);
		}
		return ActionResultType.SUCCESS;
	}

}
