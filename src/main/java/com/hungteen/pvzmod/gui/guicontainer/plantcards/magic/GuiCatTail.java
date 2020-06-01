package com.hungteen.pvzmod.gui.guicontainer.plantcards.magic;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiCatTail extends GuiPlantBase{

	public GuiCatTail(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.CAT_TAIL);
		/*
		 * 它一次发射两个尖刺
具有跟踪能力
种在睡莲上
It fires two spikes at once
With tracking ability
Plant on LilyPad
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.cat_tail1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.cat_tail2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.cat_tail3.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo()
	{
		int x=this.textLength/2+this.textOffsetX;
		int y=this.textOffsetY;
		for(int i=0;i<6;i++) {
			if(!plantInfo[i].isEmpty()) {
				if(i==5) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLUE, RenderUtil.StringRenderType.NORMAL);
				}else if(i==3) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.GREEN, RenderUtil.StringRenderType.NORMAL);
				}else{
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}

}
