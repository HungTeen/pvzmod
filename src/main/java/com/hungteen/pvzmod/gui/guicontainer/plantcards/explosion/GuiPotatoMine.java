package com.hungteen.pvzmod.gui.guicontainer.plantcards.explosion;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiPotatoMine extends GuiPlantBase{

	public GuiPotatoMine(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.POTATO_MINE);
		/*
		   它在和敌人接触时爆炸
                 但是需要一段等待时间
                 请控制好种植的位置
		  It explodes on contact with the enemy
          But it takes a while
          Please control the planting position
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.potato_mine1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.potato_mine2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.potato_mine3.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo()
	{
		int x=this.textLength/2+this.textOffsetX;
		int y=this.textOffsetY;
		for(int i=0;i<6;i++) {
			if(!plantInfo[i].isEmpty()) {
				if(i==5) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.GREEN, RenderUtil.StringRenderType.NORMAL);
				}else {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}
}
