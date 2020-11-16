package com.hungteen.pvz.render.layer;

import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DancerLightLayer<T extends Entity> extends LayerRenderer<T, EntityModel<T>>{

	public DancerLightLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        RenderHelper.disableStandardItemLighting();
        float f = 0.2f;
        float f1 = 0.0F;
        if (f > 0.8F){
            f1 = (f - 0.8F) / 0.2F;
        }
        Random random = new Random(432L);
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        RenderSystem.disableAlphaTest();
        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
        matrixStackIn.push();
        for (int i = 0; (float)i < (f + f * f) / 2.0F * 60.0F; ++i) {
            float f2 = random.nextFloat() * 5.0F + f1 * 10.0F;
            float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.pos(0.0D, -20.0D, 0.0D).color(128,0,128, (int)(255.0F * (1.0F - f1))).endVertex();
            bufferbuilder.pos(-0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(128,0,128, 0).endVertex();
            bufferbuilder.pos(0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(128,0,128, 0).endVertex();
            bufferbuilder.pos(0.0D, (double)f2, (double)(1.0F * f3)).color(128,0,128, 0).endVertex();
            bufferbuilder.pos(-0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(128,0,128, 0).endVertex();
            tessellator.draw();
        }
        matrixStackIn.pop();
        RenderSystem.depthMask(true);
        RenderSystem.disableCull();
        RenderSystem.disableBlend();
        RenderSystem.color4f(1, 1, 1, 1);
        RenderSystem.enableTexture();
        RenderSystem.enableAlphaTest();
        RenderHelper.enableStandardItemLighting();
	}

}
