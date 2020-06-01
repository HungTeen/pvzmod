package com.hungteen.pvzmod.gui.guicontainer.plantcards.magic;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiHypnoShroom extends GuiPlantBase{

	public GuiHypnoShroom(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.HYPNO_SHROOM);
		/**
		 * 吃了它的僵尸会被魅惑
被魅惑的僵尸会为你而战
注意有些僵尸不吃植物
who eat it will be charmed
Charmed zombies will fight for you
some zombies do not eat plants
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.hypno_shroom1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.hypno_shroom2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.hypno_shroom3.name").getFormattedText();
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
