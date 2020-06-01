package com.hungteen.pvzmod.gui.guicontainer.plantcards.common;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiKernelPult extends GuiPlantBase{

	public GuiKernelPult(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.KERNEL_PULT);
		/*
		 * 它可以发射玉米粒和黄油
看运气的时候到了
It can fire kernel or butter
It's time to see luck
		 */
		this.plantInfo[2] = new TextComponentTranslation("text.kernel_pult1.name").getFormattedText();
		this.plantInfo[4] = new TextComponentTranslation("text.kernel_pult2.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo() {
		int x = this.textLength / 2 + this.textOffsetX;
		int y = this.textOffsetY;
		for (int i = 0; i < 6; i++) {
			if (!plantInfo[i].isEmpty()) {
				if (i == 4) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y + 10 * i, 1f, Colors.GREEN,
							RenderUtil.StringRenderType.NORMAL);
				} else {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y + 10 * i, 1f, Colors.BLACK,
							RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}

}