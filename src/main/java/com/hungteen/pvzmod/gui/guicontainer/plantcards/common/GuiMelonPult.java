package com.hungteen.pvzmod.gui.guicontainer.plantcards.common;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiMelonPult extends GuiPlantBase{

	public GuiMelonPult(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.MELON_PULT);
		/*
		 * 它投掷巨大的西瓜
对一群僵尸造成伤害
It throws huge watermelon
Damage to a group of zombies
		 */
		this.plantInfo[2]=new TextComponentTranslation("text.melon_pult1.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.melon_pult2.name").getFormattedText();
	}

}