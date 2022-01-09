package com.hungteen.pvz.common.network.toclient;

import java.util.function.Supplier;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.ClientProxy;
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
		    	if(message.pos >= 0 && message.pos <= Resources.SLOT_NUM.max) {
		    		PlayerUtil.getOptManager(ClientProxy.MC.player).ifPresent(l -> l.setItemAt(ItemStack.of(message.data), message.pos));
		    	} else {
		    		if(message.data.contains(FLAG)) {
		    			PlayerUtil.getOptManager(ClientProxy.MC.player).ifPresent(l -> l.setCurrentPos(message.data.getInt(FLAG)));
		    		} else {
		    			PVZMod.LOGGER.error("Card Inventory Packet : receive wrong data !");
		    		}
		    	}
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}
