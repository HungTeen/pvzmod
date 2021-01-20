package com.hungteen.pvz.network;

import java.util.function.Supplier;

import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.gui.container.FragmentSpliceContainer;
import com.hungteen.pvz.gui.container.PlayerInventoryContainer;
import com.hungteen.pvz.gui.container.SlotMachineContainer;
import com.hungteen.pvz.gui.container.shop.DaveShopContainer;
import com.hungteen.pvz.gui.container.shop.PennyShopContainer;
import com.hungteen.pvz.gui.container.shop.SunShopContainer;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClickButtonPacket {

	private final int type;
	private final int op;
	private final int num;

	public ClickButtonPacket(int type, int op, int num) {
		this.type = type;
		this.op = op;
		this.num = num;
	}

	public ClickButtonPacket(PacketBuffer buffer) {
		this.type = buffer.readInt();
		this.op = buffer.readInt();
		this.num = buffer.readInt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
		buffer.writeInt(this.op);
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
				} else if(message.type == GuiHandler.DAVE_SHOP) {
					if(player.openContainer instanceof DaveShopContainer) {
						DaveShopContainer container = (DaveShopContainer) player.openContainer;
						container.buyGood(DaveGoods.values()[message.num]);
					}
				} else if(message.type == GuiHandler.SUN_SHOP) {
					if(player.openContainer instanceof SunShopContainer) {
						SunShopContainer container = (SunShopContainer) player.openContainer;
						container.buyGood(DaveGoods.values()[message.num]);
					}
				} else if(message.type == GuiHandler.FRAGMENT_SPLICE) {
					if(player.openContainer instanceof FragmentSpliceContainer) {
						FragmentSpliceContainer container = (FragmentSpliceContainer) player.openContainer;
						if(message.op == 0) {
							container.te.setResult(message.num);
						} else if(message.op == 1) {
							container.canPutStackBackToInventory();
						}
					}
				} else if(message.type == GuiHandler.SLOT_MACHINE) {
					if(player.openContainer instanceof SlotMachineContainer) {
						SlotMachineContainer container = (SlotMachineContainer) player.openContainer;
						container.te.startRun();
					}
				} else if(message.type == GuiHandler.PENNY_SHOP) {
					if(player.openContainer instanceof PennyShopContainer) {
						PennyShopContainer container = (PennyShopContainer) player.openContainer;
						container.buyGood(DaveGoods.values()[message.num]);
					}
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
