package com.hungteen.pvz.network;

import java.util.function.Supplier;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.container.PlayerInventoryContainer;
import com.hungteen.pvz.utils.enums.Guis;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;

public class OpenGuiPacket {

	private int type;

	public OpenGuiPacket(int type) {
		this.type = type;
	}

	public OpenGuiPacket(PacketBuffer buffer) {
		this.type = buffer.readInt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
	}

	public static class Handler {
		public static void onMessage(OpenGuiPacket message, Supplier<NetworkEvent.Context> ctx) {
			final ServerPlayerEntity player = ctx.get().getSender();
			ctx.get().enqueueWork(() -> {
				switch (Guis.values()[message.type]) {
				case PLAYER_INVENTORY: {
					NetworkHooks.openGui(player, new INamedContainerProvider() {

						@Override
						public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
								PlayerEntity p_createMenu_3_) {
							return new PlayerInventoryContainer(p_createMenu_1_, p_createMenu_3_);
						}

						@Override
						public ITextComponent getDisplayName() {
							return new TranslationTextComponent("gui.pvz.player_inventory.show");
						}
					});
					return;
				}
				default: {
					PVZMod.LOGGER.debug("GUI ID ERROR!");
				}
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
