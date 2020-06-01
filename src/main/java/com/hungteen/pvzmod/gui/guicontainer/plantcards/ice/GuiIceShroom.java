package com.hungteen.pvzmod.gui.guicontainer.plantcards.ice;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiIceShroom extends GuiPlantBase{

	public GuiIceShroom(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.ICE_SHROOM);
		/**
		 * 它会冰封周围的敌人
一次性植物
It will freeze enemies around
Disposable plants

		 */
		this.plantInfo[2]=new TextComponentTranslation("text.ice_shroom1.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.ice_shroom2.name").getFormattedText();
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
