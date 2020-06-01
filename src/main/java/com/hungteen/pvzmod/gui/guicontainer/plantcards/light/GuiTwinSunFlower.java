package com.hungteen.pvzmod.gui.guicontainer.plantcards.light;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiTwinSunFlower extends GuiPlantBase{

	public GuiTwinSunFlower(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.TWIN_SUNFLOWER);
		/*
		 * 一次生产两个阳光
		 * 种在向日葵上
		 * 晚上会减慢生产速度
		 * Produce double sun at a time
		 * Plant on SunFlower
		 * Night will slow down production
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.twin_sunflower1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.twin_sunflower2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.twin_sunflower3.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo()
	{
		int x=this.textLength/2+this.textOffsetX;
		int y=this.textOffsetY;
		for(int i=0;i<6;i++) {
			if(!plantInfo[i].isEmpty()) {
				if(i==3) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLUE, RenderUtil.StringRenderType.NORMAL);
				}else if(i==5){
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.RED, RenderUtil.StringRenderType.NORMAL);
				}else {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}
}
