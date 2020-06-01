package com.hungteen.pvzmod.gui.guicontainer.plantcards.ice;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiWinterMelon extends GuiPlantBase{

	public GuiWinterMelon(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.WINTER_MELON);
		/*
		 * 具有寒冷效果的西瓜投手
攻击并寒冷一群僵尸
种在西瓜投手上
MelonPult with cold effect
it can chill a group of zombies
Plant on MelonPult
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.winter_melon1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.winter_melon2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.winter_melon3.name").getFormattedText();
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
				}else{
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}

}
