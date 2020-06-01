package com.hungteen.pvzmod.gui.guicontainer.plantcards.common;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiCabbagePult extends GuiPlantBase{

	public GuiCabbagePult(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.CABBAGE_PULT);
		/*
		 * 不同于射手
它射程稍远
但是经常打不准目标
抛物线弹道
Unlike a shooter
It has a slightly longer range
But often miss the target
Parabolic trajectory
		 */
		this.plantInfo[1] = new TextComponentTranslation("text.cabbage_pult1.name").getFormattedText();
		this.plantInfo[2] = new TextComponentTranslation("text.cabbage_pult2.name").getFormattedText();
		this.plantInfo[3] = new TextComponentTranslation("text.cabbage_pult3.name").getFormattedText();
		this.plantInfo[5] = new TextComponentTranslation("text.cabbage_pult4.name").getFormattedText();
	}

}