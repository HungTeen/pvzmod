package com.hungteen.pvz.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3d;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 11:41
 **/
public abstract class PVZEntityRender<T extends Entity> extends EntityRenderer<T> {

    protected final EntityModel<T> model;

    public PVZEntityRender(EntityRendererProvider.Context context, EntityModel<T> m) {
        super(context);
        this.model = m;
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn,
                       MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if(canRenderModel(entityIn)){
            matrixStackIn.pushPose();
            matrixStackIn.scale(-1, -1, 1);
            final float f = getScaleByEntity(entityIn);
            matrixStackIn.scale(f, f, f);
            matrixStackIn.translate(0.0, -1.501, 0.0);
            final Vector3d vec = this.getTranslateVec(entityIn);
            matrixStackIn.translate(vec.x, vec.y, vec.z);
            VertexConsumer vertexConsumer = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
            this.model.setupAnim(entityIn, 0, 0, entityIn.tickCount + partialTicks, 0, 0);
            this.model.renderToBuffer(matrixStackIn, vertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.popPose();
        }
    }

    protected boolean canRenderModel(T entityIn){
        return true;
    }

    protected abstract float getScaleByEntity(T entity);

    public Vector3d getTranslateVec(T entity) {
        return new Vector3d(0, 0, 0);
    }

}