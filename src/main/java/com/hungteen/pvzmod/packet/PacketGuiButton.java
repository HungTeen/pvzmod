package com.hungteen.pvzmod.packet;

import com.hungteen.pvzmod.gui.container.ContainerCardTable;
import com.hungteen.pvzmod.gui.container.ContainerPanneyShop;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGuiButton implements IMessage{

	private int type;

	public PacketGuiButton() {
	}
	
	public PacketGuiButton(int type) {
		this.type=type;
	}

	public void fromBytes(final ByteBuf buffer) {
		this.type=buffer.readInt();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(this.type);
	}

	public static class Handler implements IMessageHandler<PacketGuiButton, IMessage> {
		public IMessage onMessage(final PacketGuiButton msg, final MessageContext ctx) {
			EntityPlayerMP player=ctx.getServerHandler().player;
//			System.out.println("packet sent");
			player.getServerWorld().addScheduledTask(()->{
				if(player.openContainer instanceof ContainerCardTable) {
					ContainerCardTable container=(ContainerCardTable) player.openContainer;
					container.onButtonClicked();
				}
				else if(player.openContainer instanceof ContainerPanneyShop) {
//					System.out.println("what?");
					ContainerPanneyShop container=(ContainerPanneyShop) player.openContainer;
					container.onButtonClicked(msg.type);
				}
			});
			return null;
		}
	}
}