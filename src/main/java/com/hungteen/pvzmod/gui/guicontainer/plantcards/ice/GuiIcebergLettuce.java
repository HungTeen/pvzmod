package com.hungteen.pvzmod.gui.guicontainer.plantcards.ice;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiIcebergLettuce extends GuiPlantBase{

	public GuiIcebergLettuce(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.ICEBERG_LETTUCE);
		/*
		 * 他会将靠近的一只僵尸冰封
		 * 零消耗
		 * It will freeze a nearby zombie
		 * Zero consumption
		 */
		this.plantInfo[2]=new TextComponentTranslation("text.iceberg_lettuce1.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.iceberg_lettuce2.name").getFormattedText();
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
