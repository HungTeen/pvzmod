package com.hungteen.pvz.gui.widget;

import com.hungteen.pvz.capabilities.player.ClientPlayerResources;
import com.hungteen.pvz.gui.AlmanacSearchGui;
import com.hungteen.pvz.utils.ItemUtil;
import com.hungteen.pvz.utils.enums.Almanacs;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CardWidget extends Widget {

	private Almanacs card;

	public CardWidget() {
		super(0, 0, 25, 25, "");
	}

	public void init(Almanacs a) {
		this.card = a;
	}

	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		RenderSystem.pushMatrix();
		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getTextureManager().bindTexture(AlmanacSearchGui.TEXTURE);
		int posX = ClientPlayerResources.isAlmanacUnLocked(this.card) ? 29 : 29 + 25;
		int posY = 206;
		this.blit(this.x, this.y, posX , posY, this.width, this.height);
		minecraft.getItemRenderer().renderItemAndEffectIntoGUI(ItemUtil.getItemStackByAlmanac(this.card), this.x + 4, this.y + 4);
		RenderSystem.popMatrix();
	}

	public void setPosition(int xx, int yy) {
		this.x = xx;
		this.y = yy;
	}
}
