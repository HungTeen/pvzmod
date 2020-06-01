package com.hungteen.pvzmod.command;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.util.PlayerUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class PVZCommandPlantLvl extends CommandBase{

	@Override
	public String getName() {
		return "plantxp";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "pvz.command.plantLvl.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length<=1) return ;
//		System.out.println(args[0]+" "+Plants.PEA_SHOOTER.ordinal());
		Plants plant=Plants.getPlantByName(args[0]);
		if(plant==null) return ;
		int xp=Integer.parseInt(args[1]);
		if(sender instanceof EntityPlayer) {
		    PlayerUtil.addPlantCardNum((EntityPlayer) sender, plant, xp);
		}
	}	

	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if(args.length==1) {
        	String[] string=new String[Plants.values().length];
//        	System.out.println(Plants.values().length);
        	int cnt=0;
        	for(Plants plant:Plants.values()) {
        		string[cnt]=plant.toString();
//        		System.out.println(string[cnt]);
        		cnt++;
        	}
        	return CommandBase.getListOfStringsMatchingLastWord(args, string);
        }
        return Collections.<String>emptyList();
    }
}
