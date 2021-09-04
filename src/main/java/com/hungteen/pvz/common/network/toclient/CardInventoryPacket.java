package com.hungteen.pvz.common.network.toclient;

import java.util.function.Supplier;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.cache.ClientPlayerResources;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class CardInventoryPacket{

	public static final String FLAG = "empty_slot";
	private final int type;
	private final CompoundNBT data;
	
	public CardInventoryPacket(int type, CompoundNBT data) {
		this.type = type;
		this.data = data;
	}
	
	public CardInventoryPacket(PacketBuffer buffer) {
		this.type = buffer.readInt();
		this.data = buffer.readNbt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
		buffer.writeNbt(this.data);
	}

	public static class Handler {
		public static void onMessage(CardInventoryPacket message, Supplier<NetworkEvent.Context> ctx) {
		    ctx.get().enqueueWork(() -> {
		    	if(message.type >= 0 && message.type <= Resources.SLOT_NUM.max) {
		    		ClientPlayerResources.SUMMON_CARDS.set(message.type, ItemStack.of(message.data));
		    	} else {
		    		if(message.data.contains(FLAG)) {
		    			ClientPlayerResources.emptySlot = message.data.getInt(FLAG);
		    		} else {
		    			PVZMod.LOGGER.error("Card Inventory Packet : receive wrong data !");
		    		}
		    	}
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}
