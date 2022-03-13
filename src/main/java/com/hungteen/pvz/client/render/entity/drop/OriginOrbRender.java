package com.hungteen.pvz.client.render.entity.drop;

import com.hungteen.pvz.common.entity.drop.OriginOrb;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ExperienceOrbRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ExperienceOrb;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-13 09:17
 **/
public class OriginOrbRender extends EntityRenderer<OriginOrb> {

    private static final ResourceLocation EXPERIENCE_ORB_LOCATION = new ResourceLocation("textures/entity/experience_orb.png");
    private static final RenderType RENDER_TYPE = RenderType.itemEntityTranslucentCull(EXPERIENCE_ORB_LOCATION);

    public OriginOrbRender(EntityRendererProvider.Context p_174110_) {
        super(p_174110_);
        this.shadowRadius = 0.15F;
        this.shadowStrength = 0.75F;
    }

    protected int getBlockLightLevel(OriginOrb p_114606_, BlockPos p_114607_) {
        return Mth.clamp(super.getBlockLightLevel(p_114606_, p_114607_) + 7, 0, 15);
    }

    @Override
    public void render(OriginOrb originOrb, float p_114600_, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int p_114604_) {
        stack.pushPose();
        final int i = originOrb.getIcon();
        final float f = (float) (i % 4 * 16 + 0) / 64.0F;
        final float f1 = (float) (i % 4 * 16 + 16) / 64.0F;
        final float f2 = (float) (i / 4 * 16 + 0) / 64.0F;
        final float f3 = (float) (i / 4 * 16 + 16) / 64.0F;
        final float f8 = ((float) originOrb.tickCount + partialTicks) / 10.0F;

        /*
        Color picked by HungTeen.
         */
        int red = (int) ((Mth.sin(f8 + 0.0F) + 1.0F) * 0.5F * 255.0F);
        int green = 255;
        int blue = (int) ((Mth.sin(f8 + 4.1887903F) + 1.0F) * 0.5F * 255.0F);
        stack.translate(0.0D, (double) 0.1F, 0.0D);
        stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        stack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        final float size = 0.4F;
        stack.scale(size, size, size);
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RENDER_TYPE);
        PoseStack.Pose posestack$pose = stack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();

        /*
        //TODO Color picked by Grass Carp.
         */
//        int red = (int) (89F + Mth.sin(f8) * 19F);
//        int green = (int) (118F - Mth.sin(f8) * 61F);
//        int blue = (int) (118 + Mth.sin(f8) * 19F);
        vertex(vertexconsumer, matrix4f, matrix3f, -0.5F, -0.25F, red, green, blue, f, f3, p_114604_);
        vertex(vertexconsumer, matrix4f, matrix3f, 0.5F, -0.25F, red, green, blue, f1, f3, p_114604_);
        vertex(vertexconsumer, matrix4f, matrix3f, 0.5F, 0.75F, red, green, blue, f1, f2, p_114604_);
        vertex(vertexconsumer, matrix4f, matrix3f, -0.5F, 0.75F, red, green, blue, f, f2, p_114604_);
        stack.popPose();
        super.render(originOrb, p_114600_, partialTicks, stack, bufferSource, p_114604_);
    }

    private static void vertex(VertexConsumer p_114609_, Matrix4f p_114610_, Matrix3f p_114611_, float p_114612_, float p_114613_, int p_114614_, int p_114615_, int p_114616_, float p_114617_, float p_114618_, int p_114619_) {
        p_114609_.vertex(p_114610_, p_114612_, p_114613_, 0.0F).color(p_114614_, p_114615_, p_114616_, 128).uv(p_114617_, p_114618_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_114619_).normal(p_114611_, 0.0F, 1.0F, 0.0F).endVertex();
    }

    public ResourceLocation getTextureLocation(OriginOrb p_114597_) {
        return EXPERIENCE_ORB_LOCATION;
    }
}