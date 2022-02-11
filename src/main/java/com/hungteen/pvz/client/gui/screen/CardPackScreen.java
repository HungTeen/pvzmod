package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.common.container.CardPackContainer;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CardPackScreen extends PVZContainerScreen<CardPackContainer>{
	
	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/card_pack.png");
	
	public CardPackScreen(CardPackContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 198;
		this.imageHeight = 222;
//		this.tips.add(new DisplayField.TipField(3, 3, Arrays.asList(
//			    new TranslationTextComponent("gui.pvz.card_pack.tip1"),
//				new TranslationTextComponent("gui.pvz.card_pack.tip2")
//		)));
	}
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
        this.minecraft.getTextureManager().bind(TEXTURE);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
//		final PlayerDataManager manager = PlayerUtil.getManager(ClientProxy.MC.player);
//		if(manager != null){
//			final int cnt = manager.getResource(Resources.SLOT_NUM);
//			blit(stack, this.leftPos + 18 + 18 * cnt, this.topPos + 20, 18, 52, 162 - 18 * cnt, 18);
//		}
//        stack.popPose();
        
        super.renderBg(stack, partialTicks, mouseX, mouseY);
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		renderTooltip(stack, mouseX, mouseY);
	}

}
