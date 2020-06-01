package com.hungteen.pvzmod.event;

import java.util.Random;

import com.hungteen.pvzmod.registry.BlockRegister;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TreeEvents {

	@SubscribeEvent
	public void onCropGrow(SaplingGrowTreeEvent ev)
	{
		//System.out.println("grow!");
		World world=ev.getWorld();
		if(!world.isRemote) {
		    int chance=16;
		    Random rand=new Random();
		    if(rand.nextInt(chance)==0) {
			    BlockPos pos=ev.getPos();
			    int x=pos.getX();
			    int y=pos.getY();
			    int z=pos.getZ();
			    if(y>2) {//没有破基岩大法~
			    	world.setBlockState(new BlockPos(x,y-1,z), BlockRegister.ORIGIN_ORE.getDefaultState());
			    }
		    }
		}
	}
}
