package com.hungteen.pvz.client.render.layer.component;

import com.hungteen.pvz.client.model.entity.ComponentModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-03-02 17:20
 **/
public abstract class ComponentLayer<T extends Entity> extends LayerRenderer<T, EntityModel<T>> {

    private final ComponentModel model;

    public ComponentLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn, ComponentModel model) {
        super(entityRendererIn);
        this.model = model;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
                       T entity, float limbSwing, float limbSwingAmount, float partialTicks,
                       float ageInTicks, float netHeadYaw, float headPitch) {
        if(this.canRender(entity)){
            matrixStackIn.pushPose();
            IVertexBuilder builder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(this.getRenderTexture(entity)));
            this.model.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY);
            matrixStackIn.popPose();
        }
    }

    public boolean canRender(T entity){
        return true;
    }

    public abstract ResourceLocation getRenderTexture(T entity);

}