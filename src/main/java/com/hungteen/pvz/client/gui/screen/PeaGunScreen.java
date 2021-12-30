package com.hungteen.pvz.client.gui.screen;

import java.util.Arrays;

import com.hungteen.pvz.client.gui.widget.DisplayField.TipField;
import com.hungteen.pvz.common.container.PeaGunContainer;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PeaGunScreen extends PVZContainerScreen<PeaGunContainer>{
	
	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/pea_gun.png");
	
	public PeaGunScreen(PeaGunContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 176;
		this.imageHeight = 187;
		this.tips.add(new TipField(2, 2, Arrays.asList(
			new TranslationTextComponent("gui.pvz.pea_gun.tip1"),
			new TranslationTextComponent("gui.pvz.pea_gun.tip2")
		)));
	}

	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
        this.minecraft.getTextureManager().bind(TEXTURE);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        stack.popPose();
        
        super.renderBg(stack, partialTicks, mouseX, mouseY);
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		renderTooltip(stack, mouseX, mouseY);
	}

}
