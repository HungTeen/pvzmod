package com.hungteen.pvzmod.gui.guicontainer.plantcards.common;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiThreePeater extends GuiPlantBase{

	public GuiThreePeater(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.THREE_PEATER);
		/*
		 * 它有三个并列的脑袋
一次发射三个并行的豌豆
It has three heads side by side
Fire three parallel peas at once
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.three_peater1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.three_peater2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.pea_shooter3.name").getFormattedText();
	}

}
