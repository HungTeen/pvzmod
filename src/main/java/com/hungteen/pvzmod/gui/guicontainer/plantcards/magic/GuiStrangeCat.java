package com.hungteen.pvzmod.gui.guicontainer.plantcards.magic;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiStrangeCat extends GuiPlantBase{

	public GuiStrangeCat(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.STRANGE_CAT);
		/*
		 * 它会把僵尸变成它自己
但是僵尸的血量要适合
奇怪的知识增加了
It will turn zombies into itself
But zombies need suitable HP 
Strange knowledge increased
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.strange_cat1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.strange_cat2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.strange_cat3.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo()
	{
		int x=this.textLength/2+this.textOffsetX;
		int y=this.textOffsetY;
		for(int i=0;i<6;i++) {
			if(!plantInfo[i].isEmpty()) {
				if(i==5) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.YELLOW, RenderUtil.StringRenderType.NORMAL);
				}else if(i==3) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.RED, RenderUtil.StringRenderType.NORMAL);
				}else {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}

}
