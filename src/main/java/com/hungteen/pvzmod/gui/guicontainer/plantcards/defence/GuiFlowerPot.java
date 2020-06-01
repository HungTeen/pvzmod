package com.hungteen.pvzmod.gui.guicontainer.plantcards.defence;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiFlowerPot extends GuiPlantBase{

	public GuiFlowerPot(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.FLOWER_POT);
		/*
		 * 它不需要种在草方块上
                 植物可以种在花盆上
静态植物
It needn't to be planted on grass
Plants can be planted on it
Static plant
		 */
		this.plantInfo[1] = new TextComponentTranslation("text.flower_pot1.name").getFormattedText();
		this.plantInfo[3] = new TextComponentTranslation("text.flower_pot2.name").getFormattedText();
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
