package com.hungteen.pvzmod.entities.npcs;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.gui.GuiHandler;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.INpc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityCrazyDave extends EntityCreature implements INpc{

	public EntityCrazyDave(World worldIn) {
		super(worldIn);
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			heldStack.interactWithEntity(player, this, hand);

			return true;
		}

		if (isEntityAlive() && !player.isSneaking()) {
			if (!world.isRemote) {
				player.openGui(Main.instance, GuiHandler.CRAZY_DAVE_TRADE, world, getEntityId(), 0, 0);
			}

			return true;
		}

		return super.processInteract(player, hand);
	}
}
