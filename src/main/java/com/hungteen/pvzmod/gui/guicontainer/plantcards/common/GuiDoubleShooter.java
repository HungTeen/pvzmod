package com.hungteen.pvzmod.gui.guicontainer.plantcards.common;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiDoubleShooter extends GuiPlantBase{

	public GuiDoubleShooter(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.DOUBLE_SHOOTER);
		this.plantInfo[2]=new TextComponentTranslation("text.double_shooter1.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.pea_shooter3.name").getFormattedText();
	}

}
