package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.api.raid.IChallengeComponent;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChallengeEnvelopeScreen extends Screen{

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/challenge_envelope.png");
	private final int xSize = 256;
	private final int ySize = 160;
	private final IChallengeComponent challengeComponent;
	
	public ChallengeEnvelopeScreen(IChallengeComponent challengeComponent) {
		super(StringUtil.EMPTY);
		this.challengeComponent = challengeComponent;
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
        this.minecraft.getTextureManager().bind(TEXTURE);
        int cornerX = (this.width - this.xSize) / 2;
        int cornerY = (this.height - this.ySize) / 2;
        this.blit(stack, cornerX, cornerY, 0, 0, this.xSize, this.ySize);

		int midX = this.width / 2;
		int midY = cornerY + 30;
		for (Pair<IFormattableTextComponent, Integer> message : challengeComponent.getMessages()) {
			StringUtil.drawCenteredScaledString(stack, this.font, message.getFirst().getString(), midX, midY, message.getSecond(), 1.2f);
			midY += 20;
		}
        super.render(stack, mouseX, mouseY, partialTicks);
	}
	
}
