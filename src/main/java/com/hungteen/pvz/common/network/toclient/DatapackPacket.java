package com.hungteen.pvz.common.network.toclient;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hungteen.pvz.common.datapack.ChallengeTypeLoader;
import com.hungteen.pvz.common.datapack.LotteryTypeLoader;
import com.hungteen.pvz.common.datapack.TransactionTypeLoader;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class DatapackPacket {

	private String type;
	private String res;
	private String data;

	public DatapackPacket(String type, String res, String data) {
		this.type = type;
		this.res = res;
		this.data = data;
	}

	public DatapackPacket(PacketBuffer buffer) {
		this.type = buffer.readUtf();
		this.res = buffer.readUtf();
		this.data = buffer.readUtf();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeUtf(this.type);
		buffer.writeUtf(this.res);
		buffer.writeUtf(this.data);
	}

	public static class Handler {
		public static void onMessage(DatapackPacket message, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				final ResourceLocation resourceLocation = new ResourceLocation(message.res);
				final JsonElement jsonElement = new JsonParser().parse(message.data);

				if(message.type.equals(LotteryTypeLoader.NAME)){
					LotteryTypeLoader.updateResource(resourceLocation, jsonElement);
				} else if (message.type.equals(TransactionTypeLoader.NAME)){
					TransactionTypeLoader.updateResource(resourceLocation, jsonElement);
				} else if (message.type.equals(ChallengeTypeLoader.NAME)){
					ChallengeTypeLoader.updateResource(resourceLocation, jsonElement);
				}
			});
		    ctx.get().setPacketHandled(true);
	    }
	}
}
