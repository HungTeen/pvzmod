package com.hungteen.pvzmod.gui.guicontainer.plantcards.fight;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiTangleKelp extends GuiPlantBase{

	public GuiTangleKelp(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.TANGLE_KELP);
		/*
		 * 水鬼一般的存在
将靠近的僵尸拖进水下
水生植物
Water ghost existence
Drag the nearby zombies underwater
Aquatic plant
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.tangle_kelp1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.tangle_kelp2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.tangle_kelp3.name").getFormattedText();
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
