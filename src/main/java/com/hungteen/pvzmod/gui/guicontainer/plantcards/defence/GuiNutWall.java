package com.hungteen.pvzmod.gui.guicontainer.plantcards.defence;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiNutWall extends GuiPlantBase{

	public GuiNutWall(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.NUT_WALL);
		/*
		   这家伙浑身都是坚硬的外壳
		   为什么不用它来保护植物呢？
		  This guy has a hard shell
		  Why not use it to protect plants?
		 */
		this.plantInfo[2]=new TextComponentTranslation("text.nut_wall1.name").getFormattedText();
		this.plantInfo[4]=new TextComponentTranslation("text.nut_wall2.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo()
	{
		int x=this.textLength/2+this.textOffsetX;
		int y=this.textOffsetY;
		for(int i=0;i<6;i++) {
			if(!plantInfo[i].isEmpty()) {
				if(i==4) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.GREEN, RenderUtil.StringRenderType.NORMAL);
				}else {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}
}
