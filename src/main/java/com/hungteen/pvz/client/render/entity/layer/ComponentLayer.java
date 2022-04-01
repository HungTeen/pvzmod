package com.hungteen.pvz.client.render.entity.layer;

import com.hungteen.pvz.client.model.misc.ComponentModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 18:21
 **/
public abstract class ComponentLayer<T extends Entity> extends RenderLayer<T, EntityModel<T>> {

    private final ComponentModel model;

    public ComponentLayer(RenderLayerParent<T, EntityModel<T>> parent, ComponentModel model) {
        super(parent);
        this.model = model;
    }

    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if(this.canRender(entity)){
            poseStack.pushPose();
            VertexConsumer builder = bufferSource.getBuffer(RenderType.entityCutoutNoCull(this.getRenderTexture(entity)));
            this.model.render(poseStack, builder, packedLightIn, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
    }

    public boolean canRender(T entity){
        return true;
    }

    public abstract ResourceLocation getRenderTexture(T entity);

}
