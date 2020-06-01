package com.hungteen.pvzmod.gui.guicontainer.plantcards.defence;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiTallNut extends GuiPlantBase{

	public GuiTallNut(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.TALL_NUT);
		/*
		 * 它比坚果墙更壮实
有更厚的血量
It's stronger than NutWall
And have more HP
		 */
		this.plantInfo[2]=new TextComponentTranslation("text.tall_nut1.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.tall_nut2.name").getFormattedText();
	}

}
