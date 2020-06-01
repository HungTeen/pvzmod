package com.hungteen.pvzmod.packet;

import com.hungteen.pvzmod.client.gui.mainwindow.PVZGuiTabPlayerData;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPlayerData implements IMessage {
	private int level;
	private int xp;
	private int sunNum;
	private int energyNum;
	private int money;

	public PacketPlayerData() {}

	public PacketPlayerData(final int lvl, final int xp,final int sunNum,final int energyNum,final int money) {
		this.level = lvl;
		this.xp = xp;
		this.sunNum=sunNum;
		this.energyNum=energyNum;
		this.money=money;
	}

	public void fromBytes(final ByteBuf buffer) {
		this.level = buffer.readInt();
		this.xp = buffer.readInt();
		this.energyNum = buffer.readInt();
		this.sunNum=buffer.readInt();
		this.money=buffer.readInt();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(this.level);
		buffer.writeFloat(this.xp);
		buffer.writeInt(this.energyNum);
		buffer.writeInt(this.sunNum);
		buffer.writeInt(this.money);
	}

	public static class Handler implements IMessageHandler<PacketPlayerData, IMessage> {
		public IMessage onMessage(final PacketPlayerData msg, final MessageContext ctx) {
			PVZGuiTabPlayerData.setPlayerData(msg.level,msg.xp,msg.money,msg.sunNum,msg.energyNum);
			return null;
		}
	}
}
