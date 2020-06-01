package com.hungteen.pvzmod.gui.guicontainer.plantcards.magic;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiCoffeeBean extends GuiPlantBase{

	public GuiCoffeeBean(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.COFFEE_BEAN);
		/*
		 * 它可以唤醒附近沉睡的植物
		 * 一次性植物
		 * it could awaken nearby plants.
		 * 
		 */
		this.plantInfo[2]=new TextComponentTranslation("text.coffee_bean1.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.cherry_bomb3.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo()
	{
		int x=this.textLength/2+this.textOffsetX;
		int y=this.textOffsetY;
		for(int i=0;i<6;i++) {
			if(!plantInfo[i].isEmpty()) {
				if(i==5) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.RED, RenderUtil.StringRenderType.NORMAL);
				}else {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}
}