package com.hungteen.pvz.common.network;

import java.util.function.Supplier;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class CardInventoryPacket{

	public static final String FLAG = "empty_slot";
	private final int pos;
	private final CompoundNBT data;
	
	public CardInventoryPacket(int pos, CompoundNBT data) {
		this.pos = pos;
		this.data = data;
	}
	
	public CardInventoryPacket(PacketBuffer buffer) {
		this.pos = buffer.readInt();
		this.data = buffer.readNbt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.pos);
		buffer.writeNbt(this.data);
	}

	public static class Handler {
		public static void onMessage(CardInventoryPacket message, Supplier<NetworkEvent.Context> ctx) {
		    ctx.get().enqueueWork(() -> {
				if(ctx.get().getDirection().getReceptionSide().isClient()){
//					System.out.println("Server to Client");
					if(message.pos >= 0 && message.pos <= Resources.SLOT_NUM.max) {
						PlayerUtil.getOptManager(PVZMod.PROXY.getPlayer()).ifPresent(l -> l.setItemAt(ItemStack.of(message.data), message.pos, false));
					} else {
						if(message.data.contains(FLAG)) {
							PlayerUtil.getOptManager(PVZMod.PROXY.getPlayer()).ifPresent(l -> l.setCurrentPos(message.data.getInt(FLAG), false));
						} else {
							PVZMod.LOGGER.error("Card Inventory Packet : receive wrong data !");
						}
					}
				} else{
//					System.out.println("Client to Server");
					if(message.pos >= 0 && message.pos <= Resources.SLOT_NUM.max) {
						PlayerUtil.getOptManager(ctx.get().getSender()).ifPresent(l -> l.setItemAt(ItemStack.of(message.data), message.pos, false));
					} else {
						if(message.data.contains(FLAG)) {
							PlayerUtil.getOptManager(ctx.get().getSender()).ifPresent(l -> l.setCurrentPos(message.data.getInt(FLAG), false));
						} else {
							PVZMod.LOGGER.error("Card Inventory Packet : receive wrong data !");
						}
					}
				}
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}
