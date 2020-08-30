package com.hungteen.pvz.gui;

import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrangeHelpScreen extends Screen{

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/strange_help.png");
	private static final String[] STRINGS = new String[] {
			new TranslationTextComponent("gui.pvz.strange_help").getFormattedText(),
			new TranslationTextComponent("gui.pvz.strange_help1").getFormattedText(),
			new TranslationTextComponent("gui.pvz.strange_help2").getFormattedText(),
			new TranslationTextComponent("gui.pvz.strange_help3").getFormattedText(),
			new TranslationTextComponent("gui.pvz.strange_help4").getFormattedText(),
	};
	
	public StrangeHelpScreen() {
		super(new TranslationTextComponent("gui.pvz.strange_help"));
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
//		System.out.println("1");
		this.renderBackground();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        int textureWidth = 256;
        int textureHeight = 160;
        this.blit((this.width-textureWidth)/2, (this.height-textureHeight)/2, 0, 0, textureWidth, textureHeight);
        int midX = this.width/2;
        int midY = 70;
        for(int i=0;i<STRINGS.length;i++) {
        	StringUtil.drawCenteredScaledString(this.font, STRINGS[i], midX, midY, Colors.BLACK, 1.5f);
        	midY+=30;
        }
        super.render(mouseX, mouseY, partialTicks);
	}
	
}
