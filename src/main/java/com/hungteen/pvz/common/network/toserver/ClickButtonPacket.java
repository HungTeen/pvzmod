package com.hungteen.pvz.common.network.toserver;

import java.util.function.Supplier;

import com.hungteen.pvz.client.gui.GuiHandler;
import com.hungteen.pvz.common.container.CardFusionContainer;
import com.hungteen.pvz.common.container.EssenceAltarContainer;
import com.hungteen.pvz.common.container.FragmentSpliceContainer;
import com.hungteen.pvz.common.container.SlotMachineContainer;
import com.hungteen.pvz.common.container.shop.AbstractDaveShopContainer;

import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
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
				} else if(message.type == GuiHandler.SHOP) {
					if(player.containerMenu instanceof AbstractDaveShopContainer) {
						((AbstractDaveShopContainer) player.containerMenu).onSell(message.num);
					}
				} else if(message.type == GuiHandler.FRAGMENT_SPLICE) {
					if(player.containerMenu instanceof FragmentSpliceContainer) {
						FragmentSpliceContainer container = (FragmentSpliceContainer) player.containerMenu;
						if(message.op == 0) {
							container.onCraft();
						}
					}
				} else if(message.type == GuiHandler.SLOT_MACHINE) {
					if(player.containerMenu instanceof SlotMachineContainer) {
						if (PlayerUtil.getResource(player,Resources.LOTTERY_CHANCE) > 0) {
							SlotMachineContainer container = (SlotMachineContainer) player.containerMenu;
							if (message.op == 0) {
								container.te.slowStart(player);
							} else {
								container.te.fastStart(player);
							}
						} else {
							PlayerUtil.sendMsgTo(player, new TranslationTextComponent("help.pvz.out_of_lottery_chance").withStyle(TextFormatting.RED));
						}
					}
				} else if(message.type == GuiHandler.ESSENCE_ALTAR) {
					if(player.containerMenu instanceof EssenceAltarContainer) {
						EssenceAltarContainer container = (EssenceAltarContainer) player.containerMenu;
						container.learnSkillAt(message.op);
					}
				} else if(message.type == GuiHandler.CARD_FUSION) {
					if(player.containerMenu instanceof CardFusionContainer) {
						CardFusionContainer container = (CardFusionContainer) player.containerMenu;
						if(message.op == 0) {
							container.onCraft();
						}
					}
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
