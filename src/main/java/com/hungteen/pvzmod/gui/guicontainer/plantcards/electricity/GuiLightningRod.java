package com.hungteen.pvzmod.gui.guicontainer.plantcards.electricity;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiLightningRod extends GuiPlantBase{

	public GuiLightningRod(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.LIGHTLING_ROD);
		/*
		 * 电之精华矿的生产者
每次攻击时会生成该矿
Producer of ElectricityOre
generate ore when attacking
		 */
		this.plantInfo[2]=new TextComponentTranslation("text.lightning_rod1.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.lightning_rod2.name").getFormattedText();
	}
}
