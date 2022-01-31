package com.hungteen.pvz.client.render.layer.fullskin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PVZFullSkinLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    protected IEntityRenderer<T, M> entityRender;
    protected EntityModel<T> entityModel;
    protected float scale = 1F;

    public PVZFullSkinLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
        this.entityRender = entityRendererIn;
        this.entityModel = this.entityRender.getModel();
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                       float headPitch) {
        if (this.canRender(entitylivingbaseIn)) {
            matrixStackIn.pushPose();
            matrixStackIn.scale(this.scale, this.scale, this.scale);
            float f = (float) entitylivingbaseIn.tickCount + partialTicks;
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.energySwirl(this.getResourceLocation(entitylivingbaseIn), this.getU(f), this.getV(f)));
            entityModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
            matrixStackIn.popPose();
        }
    }

    protected float getU(float f) {
        return 0f;
    }

    protected float getV(float f) {
        return 0f;
    }

    protected abstract boolean canRender(T entity);

    protected abstract ResourceLocation getResourceLocation(T entity);

}
