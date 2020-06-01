package com.hungteen.pvzmod.gui.guicontainer.plantcards.explosion;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiCherryBomb extends GuiPlantBase{

	public GuiCherryBomb(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.CHERRY_BOMB);
		/*
		 它会产生高伤害爆炸
               但是要把它种在靠近僵尸的地方
                一次性植物
		It will make a high damage explosion
        But plant it near zombies
        Disposable plants
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.cherry_bomb1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.cherry_bomb2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.cherry_bomb3.name").getFormattedText();
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
				}else {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}
}
