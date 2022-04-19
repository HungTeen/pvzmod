package com.hungteen.pvz.client.render.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-16 16:52
 **/
public abstract class PVZLightLayer<T extends Entity> extends RenderLayer<T, EntityModel<T>> {

    public PVZLightLayer(RenderLayerParent<T, EntityModel<T>> layerParent) {
        super(layerParent);
    }

    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if(this.canRender(entity)){
            VertexConsumer vertexconsumer = bufferSource.getBuffer(this.renderType());
            this.getParentModel().renderToBuffer(poseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public abstract RenderType renderType();

    public boolean canRender(T entity){
        return true;
    }

}
