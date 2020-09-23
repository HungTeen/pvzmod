package com.hungteen.pvz.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PVZPlayerInventoryGui extends Screen{

	protected PVZPlayerInventoryGui(ITextComponent titleIn) {
		super(titleIn);
	}

}
