package com.hungteen.pvz.common.network.toserver;

import java.util.function.Supplier;

import com.hungteen.pvz.client.gui.GuiHandler;
import com.hungteen.pvz.common.container.CardFusionContainer;
import com.hungteen.pvz.common.container.EssenceAltarContainer;
import com.hungteen.pvz.common.container.FragmentSpliceContainer;
import com.hungteen.pvz.common.container.SlotMachineContainer;
import com.hungteen.pvz.common.container.shop.DaveShopContainer;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import com.hungteen.pvz.common.container.shop.PennyShopContainer;
import com.hungteen.pvz.common.container.shop.SunShopContainer;
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
//					if(player.containerMenu instanceof PlayerInventoryContainer) {
//						PlayerInventoryContainer inv = (PlayerInventoryContainer) player.containerMenu;
//						inv.currentPage+=message.num;
//						inv.onPageChange();
//					}
				} else if(message.type == GuiHandler.DAVE_SHOP) {
					if(player.containerMenu instanceof DaveShopContainer) {
						DaveShopContainer container = (DaveShopContainer) player.containerMenu;
						container.buyGood(DaveGoods.values()[message.num]);
					}
				} else if(message.type == GuiHandler.SUN_SHOP) {
					if(player.containerMenu instanceof SunShopContainer) {
						SunShopContainer container = (SunShopContainer) player.containerMenu;
						container.buyGood(DaveGoods.values()[message.num]);
					}
				} else if(message.type == GuiHandler.FRAGMENT_SPLICE) {
					if(player.containerMenu instanceof FragmentSpliceContainer) {
						FragmentSpliceContainer container = (FragmentSpliceContainer) player.containerMenu;
						if(message.op == 0) {
							container.te.setResult(message.num);
						} else if(message.op == 1) {
							container.canPutStackBackToInventory();
						}
					}
				} else if(message.type == GuiHandler.SLOT_MACHINE) {
					if(player.containerMenu instanceof SlotMachineContainer) {
						SlotMachineContainer container = (SlotMachineContainer) player.containerMenu;
						container.te.startRun(player);
					}
				} else if(message.type == GuiHandler.PENNY_SHOP) {
					if(player.containerMenu instanceof PennyShopContainer) {
						PennyShopContainer container = (PennyShopContainer) player.containerMenu;
						container.buyGood(DaveGoods.values()[message.num]);
					}
				} else if(message.type == GuiHandler.MYSTERY_SHOP) {
					if(player.containerMenu instanceof MysteryShopContainer) {
						MysteryShopContainer container = (MysteryShopContainer) player.containerMenu;
						container.buyGood(DaveGoods.values()[message.num], message.op);
					}
				} else if(message.type == GuiHandler.ESSENCE_ALTAR) {
					if(player.containerMenu instanceof EssenceAltarContainer) {
						EssenceAltarContainer container = (EssenceAltarContainer) player.containerMenu;
//						container.destroyAllCards();
					}
				} else if(message.type == GuiHandler.CARD_FUSION) {
					if(player.containerMenu instanceof CardFusionContainer) {
						CardFusionContainer container = (CardFusionContainer) player.containerMenu;
						if(message.op == 0) {
//							container.te.setResult(message.num);
						} else if(message.op == 1) {
							container.canPutStackBackToInventory();
						}
					}
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
