package com.hungteen.pvzmod.gui.guicontainer.plantcards.light;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiSunFlower extends GuiPlantBase{

	public GuiSunFlower(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.SUN_FLOWER);
		/*
		 最基本的生产阳光的植物
               每次产生一个阳光
               晚上会减慢生产速度
		Basic plant that produces sunlight
        One sun at a time
        Night will slow down production
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.sun_flower1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.sun_flower2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.sun_flower3.name").getFormattedText();
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
