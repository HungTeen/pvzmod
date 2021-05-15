package com.hungteen.pvz.gui.screen;

import com.hungteen.pvz.gui.container.PeaGunContainer;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PeaGunScreen extends ContainerScreen<PeaGunContainer>{
	
	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/pea_gun.png");
	
	public PeaGunScreen(PeaGunContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 176;
		this.imageHeight = 187;
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
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		renderTooltip(stack, mouseX, mouseY);
	}

}
