package com.hungteen.pvz.common.event.handler;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.event.PVZPlayerEvents;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;

public class BlockEventHandler {

	/**
	 * {@link PVZPlayerEvents#onPlayerBreakBlock(net.minecraftforge.event.world.BlockEvent.BreakEvent)}
	 */
	public static void checkAndDropSeeds(BlockEvent.BreakEvent ev) {
		PlayerEntity player = ev.getPlayer();
		BlockState state = ev.getState();
		BlockPos pos = ev.getPos();
		if(! player.level.isClientSide) {
			if(state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.TALL_GRASS) {//break grass
				if(player.getRandom().nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.PeaDropChance.get()) == 0) {
					player.level.addFreshEntity(new ItemEntity(player.level,pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemRegister.PEA.get(), 1)));
				} else if(player.getRandom().nextInt(PVZConfig.COMMON_CONFIG.BlockSettings.CabbageDropChance.get()) == 0) {
					player.level.addFreshEntity(new ItemEntity(player.level,pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemRegister.CABBAGE_SEEDS.get(), 1)));
				}
			}
		}
	}
}
