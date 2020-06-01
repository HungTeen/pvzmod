package com.hungteen.pvzmod.gui.guicontainer.plantcards.magic;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiMariGold extends GuiPlantBase{

	public GuiMariGold(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.MARIGOLD);
		/*
		 * 它是你的财神爷
可以给你生产钱币
It's your god of wealth
Can produce coins for you
		 */
		this.plantInfo[2]=new TextComponentTranslation("text.marigold1.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.marigold2.name").getFormattedText();
	}

}