package com.hungteen.pvzmod.gui.guicontainer.plantcards.fight;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiSquash extends GuiPlantBase{

	public GuiSquash(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.SQUASH);
		/*
		 * 它会跳到附近的僵尸的上空
碾压粉碎僵尸
事后大概率会死亡
It will jump over nearby zombies
Crush and crush zombies
High probability of death afterwards
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.squash1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.squash2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.squash3.name").getFormattedText();
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
