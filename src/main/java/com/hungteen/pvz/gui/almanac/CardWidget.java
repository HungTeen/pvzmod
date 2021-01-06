package com.hungteen.pvz.gui.almanac;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CardWidget extends Widget {

	private Almanac card;

	public CardWidget() {
		super(0, 0, 25, 25, "");
	}

	public void init(Almanac a) {
		this.card = a;
	}

	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		RenderSystem.pushMatrix();
		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getTextureManager().bindTexture(AlmanacSearchGui.TEXTURE);
		int posX = ClientPlayerResources.isAlmanacUnLocked(this.card) ? 29 : 29 + 25;
		int posY = 206;
		this.blit(this.x, this.y, posX, posY, this.width, this.height);
		minecraft.getItemRenderer().renderItemAndEffectIntoGUI(Almanac.getItemStackByAlmanac(this.card), this.x + 4,
				this.y + 4);
		RenderSystem.popMatrix();
	}

	public List<String> getToolTipText(Screen p_191772_1_) {
		List<String> list = new ArrayList<>();
		list.add(Almanac.getAlmanacName(card));
		return list;
	}

	public void setPosition(int xx, int yy) {
		this.x = xx;
		this.y = yy;
	}

	public Almanac getAlmanac() {
		return this.card;
	}

}
