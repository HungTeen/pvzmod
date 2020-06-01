package com.hungteen.pvzmod.packet;

import com.hungteen.pvzmod.client.gui.mainwindow.PVZGuiTabPlayerData;
import com.hungteen.pvzmod.util.enums.Plants;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPlantLvlData implements IMessage {
	private int plantId;
	private int level;
	private int xp;
	private boolean islocked;

	public PacketPlantLvlData() {}

	public PacketPlantLvlData(Plants plant, final int lvl,final int xp,final boolean is) {
		this.plantId = plant.ordinal();
		this.level = lvl;
		this.xp = xp;
		this.islocked=is;
	}

	public void fromBytes(final ByteBuf buffer) {
		plantId = buffer.readInt();
		level = buffer.readInt();
		xp = buffer.readInt();
		islocked = buffer.readBoolean();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(plantId);
		buffer.writeInt(level);
		buffer.writeInt(xp);
		buffer.writeBoolean(islocked);
	}

	public static class Handler implements IMessageHandler<PacketPlantLvlData, IMessage> {
		public IMessage onMessage(final PacketPlantLvlData msg, final MessageContext ctx) {

			Plants plant= Plants.values()[msg.plantId];

			if (plant != null) {
				PVZGuiTabPlayerData.setPlayerPlantData(plant, msg.level, msg.xp,msg.islocked);
			}
			else {
				System.out.println("Error trying to set plant data in client plants holder, skipping");
			}

			return null;
		}
	}
}
