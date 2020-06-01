package com.hungteen.pvzmod.gui.guicontainer.plantcards.defence;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiLilyPad extends GuiPlantBase {

	public GuiLilyPad(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.LILY_PAD);
		/*
		 * 它要种在水上 大部分植物能种在他身上 静态植物 It needs to be planted on water 
		 * Most plants can be planted on him Static plant
		 * 
		 */
		this.plantInfo[1] = new TextComponentTranslation("text.lily_pad1.name").getFormattedText();
		this.plantInfo[3] = new TextComponentTranslation("text.lily_pad2.name").getFormattedText();
		this.plantInfo[5] = new TextComponentTranslation("text.lily_pad3.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo() {
		int x = this.textLength / 2 + this.textOffsetX;
		int y = this.textOffsetY;
		for (int i = 0; i < 6; i++) {
			if (!plantInfo[i].isEmpty()) {
				if (i == 5) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y + 10 * i, 1f, Colors.RED,
							RenderUtil.StringRenderType.NORMAL);
				} else {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y + 10 * i, 1f, Colors.BLACK,
							RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}
}
