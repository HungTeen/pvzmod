package com.hungteen.pvzmod.gui.guicontainer.plantcards.light;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiGoldLeaf extends GuiPlantBase{

	public GuiGoldLeaf(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.GOLD_LEAF);
		/*
		 * 在脚下生成黄金瓷砖
只能种在金块上
一次性植物
Generate gold tiles underfoot
Could only live on GoldenBlock
Disposable plants
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.gold_leaf1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.gold_leaf2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.cherry_bomb3.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo()
	{
		int x=this.textLength/2+this.textOffsetX;
		int y=this.textOffsetY;
		for(int i=0;i<6;i++) {
			if(!plantInfo[i].isEmpty()) {
				if(i==1) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}else {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.RED, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}
}

