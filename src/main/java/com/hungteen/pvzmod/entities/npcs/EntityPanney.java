package com.hungteen.pvzmod.entities.npcs;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.gui.GuiHandler;
import com.hungteen.pvzmod.registry.ItemRegister;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPanney extends EntityLiving{

	public EntityPanney(World worldIn) {
		super(worldIn);
		this.setSize(2.7f, 3.4f);
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack=player.getHeldItem(hand);
		if(stack.getItem()==ItemRegister.CAR_KEY&&!this.world.isRemote) {
			if(this.getPassengers().size()==0) {
			    player.startRiding(this);
			    BlockPos pos=new BlockPos(this);
			    player.openGui(Main.instance, GuiHandler.PANNEY_SHOP, world, pos.getX(),pos.getY(),pos.getZ());
			    return true;
			}
		}
		return false;
	}
}
