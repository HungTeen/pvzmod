package com.hungteen.pvz.client.render.entity.effect;

import com.hungteen.pvz.common.entity.effect.OriginEffectEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 09:55
 **/
@OnlyIn(Dist.CLIENT)
public class OriginEffectRender extends EntityRenderer<OriginEffectEntity> {

    public OriginEffectRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(OriginEffectEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn,
                       MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        final int color = entityIn.getColor();
        matrixStackIn.pushPose();
        final int T = 100;
        final float len = 1.2F;
        final float scale = 1F;
        final float maxScale = 0.1F * AnimationUtil.getUpDownUpDown(entityIn.getExistTick() % T, T, 90);
        matrixStackIn.scale(scale + maxScale, scale + maxScale, scale + maxScale);
        matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(2 * entityIn.getExistTick()));

        matrixStackIn.pushPose();
        renderLight(matrixStackIn, bufferIn, len, color);
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(90));
        renderLight(matrixStackIn, bufferIn, len, color);
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(-90));
        renderLight(matrixStackIn, bufferIn, len, color);
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(180));
        renderLight(matrixStackIn, bufferIn, len, color);
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.ZN.rotationDegrees(90));
        renderLight(matrixStackIn, bufferIn, len, color);
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.ZN.rotationDegrees(-90));
        renderLight(matrixStackIn, bufferIn, len, color);
        matrixStackIn.popPose();

        matrixStackIn.popPose();
    }

    private static void renderLight(PoseStack matrixStackIn, MultiBufferSource bufferIn, float len, int color){
        final VertexConsumer ivertexbuilder2 = bufferIn.getBuffer(RenderType.lightning());
        matrixStackIn.pushPose();
        for(int i = 0; (float)i < 4; ++i) {
            Matrix4f matrix4f = matrixStackIn.last().pose();
            float f3 = 1;
            final int c1 = color / 256 / 256;
            final int c2 = color / 256 % 256;
            final int c3 = color % 256;
            ivertexbuilder2.vertex(matrix4f, 0.0f, 0f, 0.0f).color(c1, c2, c3, 255).endVertex();
            ivertexbuilder2.vertex(matrix4f, - 0.866f * f3, - len, (- 0.5F * f3)).color(c1, c2, c3, 20).endVertex();
            ivertexbuilder2.vertex(matrix4f, 0.866f * f3, - len, (- 0.5F * f3)).color(c1, c2, c3, 20).endVertex();
            ivertexbuilder2.vertex(matrix4f, 0.0f, - len, (1.0F * f3)).color(c1, c2, c3, 20).endVertex();
            ivertexbuilder2.vertex(matrix4f, - 0.866f * f3, - len, (- 0.5F * f3)).color(c1, c2, c3, 20).endVertex();
        }
        matrixStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(OriginEffectEntity entity) {
        return null;
    }
}
