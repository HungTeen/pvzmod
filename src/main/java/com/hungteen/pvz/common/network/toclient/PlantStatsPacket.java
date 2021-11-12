package com.hungteen.pvz.common.network.toclient;

public class PlantStatsPacket {

//	private String type;
//	private int lvl;
//	private int xp;
//
//	public PlantStatsPacket(String id, int lvl, int xp) {
//		this.type = id;
//		this.lvl = lvl;
//		this.xp = xp;
//	}
//
//	public PlantStatsPacket(PacketBuffer buffer) {
//		this.type = buffer.readUtf();
//		this.lvl = buffer.readInt();
//		this.xp = buffer.readInt();
//	}
//
//	public void encode(PacketBuffer buffer) {
//		buffer.writeUtf(this.type);
//		buffer.writeInt(this.lvl);
//		buffer.writeInt(this.xp);
//	}
//
//	public static class Handler {
//		public static void onMessage(PlantStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
//		    ctx.get().enqueueWork(() -> {
//				PVZAPI.get().getTypeByID(message.type).ifPresent(type -> {
//					ClientPlayerResources.setPlantData(type, message.lvl, message.xp);
//				});
//		    });
//		    ctx.get().setPacketHandled(true);
//	    }
//	}
}
