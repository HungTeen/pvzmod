package com.hungteen.pvz.client.render.entity.layer;

import com.hungteen.pvz.client.RenderUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PowerableMob;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-18 09:54
 **/
public abstract class PVZSkinLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public PVZSkinLayer(RenderLayerParent<T, M> layerParent) {
        super(layerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    	if (this.canRender(entity)) {
            float f = (float) entity.tickCount + partialTicks;
            final VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.energySwirl(this.getTextureLocation(), 0, 0));
            if(this.getParentModel() instanceof HumanoidModel) {
                ((HumanoidModel) this.getParentModel()).hat.visible = false;
            }
            this.getParentModel().renderToBuffer(poseStack, vertexconsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
            if(this.getParentModel() instanceof HumanoidModel) {
                ((HumanoidModel) this.getParentModel()).hat.visible = true;
            }
        }
    }

    public boolean canRender(T entity) {
        return true;
    }

    protected abstract float xOffset(float tick);

    protected abstract ResourceLocation getTextureLocation();

}
