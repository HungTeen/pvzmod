package com.hungteen.pvz.common.network.toserver;

import java.util.function.Supplier;

import com.hungteen.pvz.common.entity.plant.explosion.CobCannonEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class EntityInteractPacket {
	private int type;
	private int op;
	private int num;
	
	public EntityInteractPacket(int type, int op, int num) {
		this.type = type;
		this.op = op;
		this.num = num;
	}
	
	public EntityInteractPacket(PacketBuffer buffer) {
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
		public static void onMessage(EntityInteractPacket message, Supplier<NetworkEvent.Context> ctx) {
			final ServerPlayerEntity player = ctx.get().getSender();
			ctx.get().enqueueWork(()->{
		    	Entity entity = player.level.getEntity(message.type);
		    	if(entity instanceof CobCannonEntity) {
		    		CobCannonEntity cob = (CobCannonEntity) entity;
		    		cob.checkAndAttack();
		    	}
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
	
}
