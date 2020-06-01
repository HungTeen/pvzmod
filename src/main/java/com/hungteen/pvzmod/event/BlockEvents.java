package com.hungteen.pvzmod.event;

import java.util.Random;

import com.hungteen.pvzmod.registry.ItemRegister;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEvents {
	
	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent ev) {//打草有几率得豌豆
		BlockPos pos=ev.getPos();
		if(ev.getState().getBlock()==Blocks.TALLGRASS) {
			Random rand=new Random();
			if(rand.nextInt(50)==0&&!ev.getWorld().isRemote) {
				EntityItem item=new EntityItem(ev.getWorld(),pos.getX(),pos.getY(),pos.getZ(),new ItemStack(ItemRegister.PEA,1));
				ev.getWorld().spawnEntity(item);
			}
			if(rand.nextInt(100)==0&&!ev.getWorld().isRemote) {
				EntityItem item=new EntityItem(ev.getWorld(),pos.getX(),pos.getY(),pos.getZ(),new ItemStack(ItemRegister.CABBAGE,1));
				ev.getWorld().spawnEntity(item);
			}
		}
	}
}
