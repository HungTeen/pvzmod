package com.hungteen.pvz.common.item.display;

import com.hungteen.pvz.common.container.AlmanacContainer;
import com.hungteen.pvz.common.item.PVZItemGroups;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.NetworkHooks;

public class AlmanacItem extends Item{

	public AlmanacItem() {
		super(new Properties().tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		if(!worldIn.isClientSide && playerIn instanceof ServerPlayer) {
			NetworkHooks.openGui((ServerPlayer) playerIn, new INamedContainerProvider() {

				@Override
				public Container createMenu(int id, PlayerInventory inventory, Player player) {
					return new AlmanacContainer(id, player);
				}

				@Override
				public Component getDisplayName() {
					return new TranslatableComponent("gui.pvz.almanac.show");
				}
			});
		}
		return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
	}
	
}
