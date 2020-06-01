package com.hungteen.pvzmod.gui.guicontainer.plantcards.fight;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiSpikeRock extends GuiPlantBase{

	public GuiSpikeRock(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.SPIKE_ROCK);
		/*
		 * 拥有3个大刺的地刺
一个大刺能扎破好几个轮胎
种在地刺上
SpikeWeed with 3 big spikes
A big Spike can pierce several tires
Plant on SpikeWeed
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.spike_rock1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.spike_rock2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.spike_rock3.name").getFormattedText();
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
