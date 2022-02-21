package com.hungteen.pvz.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

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
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
		super.appendHoverText(stack, world, textComponents, tooltipFlag);
	}
	
}
