package com.hungteen.pvzmod.gui.guicontainer.plantcards.flame;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiJalapeno extends GuiPlantBase{

	public GuiJalapeno(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.JALAPENO);
		/*
		 * 他会产生十字交叉的烈焰
特别适合摧毁寒冰
一次性植物
He will produce a cross flame
Especially suitable to destroy ice
Disposable plants
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.jalapeno1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.jalapeno2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.jalapeno3.name").getFormattedText();
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
				}else if(i==3){
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.GREEN, RenderUtil.StringRenderType.NORMAL);
				}else if(i==1){
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}
}
