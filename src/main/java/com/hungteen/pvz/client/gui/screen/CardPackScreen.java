package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.common.container.CardPackContainer;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CardPackScreen extends ContainerScreen<CardPackContainer>{
	
	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/card_pack.png");
	
	public CardPackScreen(CardPackContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 198;
		this.imageHeight = 222;
	}

	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
        this.minecraft.getTextureManager().bind(TEXTURE);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
//        int pos = 0;
//        for(int i = 0; i < Resources.SLOT_NUM.max; ++ i) {
//        	if(i != ClientPlayerResources.emptySlot) {
//        		this.itemRenderer.renderGuiItem(ClientPlayerResources.SUMMON_CARDS.get(i), this.leftPos + 18 * pos + 18, this.topPos);
//        		++ pos;
//        	}
//        }
        stack.popPose();
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		renderTooltip(stack, mouseX, mouseY);
	}

}
