package com.hungteen.pvz.gui.screen;

import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrangeHelpScreen extends Screen{

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/strange_help.png");
	private static final String[] STRINGS = new String[] {
			new TranslationTextComponent("gui.pvz.strange_help").getContents(),
			new TranslationTextComponent("gui.pvz.strange_help1").getContents(),
			new TranslationTextComponent("gui.pvz.strange_help2").getContents(),
			new TranslationTextComponent("gui.pvz.strange_help3").getContents(),
			new TranslationTextComponent("gui.pvz.strange_help4").getContents(),
	};
	private final int xSize = 256;
	private final int ySize = 160;
	
	public StrangeHelpScreen() {
		super(new TranslationTextComponent("gui.pvz.strange_help"));
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
        this.minecraft.getTextureManager().bind(TEXTURE);
        int cornerX = (this.width - this.xSize) / 2;
        int cornerY = (this.height - this.ySize) / 2;
        this.blit(stack, cornerX, cornerY, 0, 0, this.xSize, this.ySize);
        int midX = this.width / 2;
        int midY = cornerY + 20;
        for(int i = 0; i < STRINGS.length; ++ i) {
        	StringUtil.drawCenteredScaledString(stack, this.font, STRINGS[i], midX, midY, Colors.BLACK, 1.5f);
        	midY += 30;
        }
        super.render(stack, mouseX, mouseY, partialTicks);
	}
	
}
