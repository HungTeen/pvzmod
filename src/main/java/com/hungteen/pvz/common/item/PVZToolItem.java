package com.hungteen.pvz.common.item;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class PVZToolItem extends Item{

	public PVZToolItem() {
		super(new Properties().tab(PVZItemGroups.PVZ_USEFUL));
	}

	public PVZToolItem(Properties p) {
		super(p.tab(PVZItemGroups.PVZ_USEFUL));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, world, textComponents, tooltipFlag);
	}
	
}
