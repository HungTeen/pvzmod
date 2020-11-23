package com.hungteen.pvz.network;

import java.util.function.Supplier;

import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.gui.container.DaveShopContainer;
import com.hungteen.pvz.gui.container.PlayerInventoryContainer;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClickButtonPacket {

	private int type;
	private int num;

	public ClickButtonPacket(int type, int num) {
		this.type = type;
		this.num = num;
	}

	public ClickButtonPacket(PacketBuffer buffer) {
		this.type = buffer.readInt();
		this.num = buffer.readInt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
		buffer.writeInt(this.num);
	}

	public static class Handler {
		public static void onMessage(ClickButtonPacket message, Supplier<NetworkEvent.Context> ctx) {
			final ServerPlayerEntity player = ctx.get().getSender();
			ctx.get().enqueueWork(() -> {
				if (message.type == GuiHandler.PLAYER_INVENTORY) { 
					if(player.openContainer instanceof PlayerInventoryContainer) {
						PlayerInventoryContainer inv = (PlayerInventoryContainer) player.openContainer;
						inv.currentPage+=message.num;
						inv.onPageChange();
					}
				}else if(message.type == GuiHandler.DAVE_SHOP) {
					if(player.openContainer instanceof DaveShopContainer) {
						DaveShopContainer container = (DaveShopContainer) player.openContainer;
						container.buyGood(DaveGoods.values()[message.num]);
					}
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
