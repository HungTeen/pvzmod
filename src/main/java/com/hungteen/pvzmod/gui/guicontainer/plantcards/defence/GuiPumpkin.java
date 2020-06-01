package com.hungteen.pvzmod.gui.guicontainer.plantcards.defence;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiPumpkin extends GuiPlantBase{

	public GuiPumpkin(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.PUMPKIN);
		/*
		 * 它也有较多的血量
可以和多数植物种在一起
也许是为了保护他们吧
It also has more blood
Can be planted with most plants
Maybe to protect them
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.pumpkin1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.pumpkin2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.pumpkin3.name").getFormattedText();
	}

}
