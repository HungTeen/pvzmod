package com.hungteen.pvz.client.render.blockentity;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.blockentity.FloatOriginModel;
import com.hungteen.pvz.common.blockentity.EssenceAltarBlockEntity;
import com.hungteen.pvz.utils.Util;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 13:08
 **/
public class EssenceAltarRender implements BlockEntityRenderer<EssenceAltarBlockEntity> {

    private static final ResourceLocation RES = Util.texture("blockentity/float_origin.png");
    private final FloatOriginModel<Entity> origin;

    public EssenceAltarRender(BlockEntityRendererProvider.Context context) {
        origin = new FloatOriginModel<>(context.bakeLayer(PVZModelLayers.FLOAT_ORIGIN));
    }

    @Override
    public void render(EssenceAltarBlockEntity blockEntity, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        stack.pushPose();
        stack.scale(- 1, - 1, 1);
        float size = 1F;
        stack.scale(size, size, size);
        stack.translate(- 0.5 / size, - 2.3D - 0.15 * Math.sin(blockEntity.tick * 0.1), 0.5 / size);
        VertexConsumer builder = bufferIn.getBuffer(RenderType.entityTranslucentCull(RES));
        origin.renderToBuffer(stack, builder, combinedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1 ,1);
        origin.setupAnim(blockEntity.tick + partialTicks);
        stack.popPose();
    }

}
