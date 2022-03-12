package com.hungteen.pvz.client.render.entity.drop;

import java.util.Random;

import com.hungteen.pvz.client.RenderUtil;
import com.hungteen.pvz.common.entity.drop.DropEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-12 22:31
 **/
public class DropItemRender extends EntityRenderer<DropEntity> {

    private final ItemRenderer itemRenderer;

    public DropItemRender(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
        this.shadowRadius = 0.15F;
        this.shadowStrength = 0.75F;
    }

    @Override
    public void render(DropEntity dropEntity, float yaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferSource, int packedLightIn) {
        ItemStack itemstack = dropEntity.getRenderStack();
        if(itemstack.isEmpty()){
            return;
        }
        matrixStackIn.pushPose();
        BakedModel bakedmodel = this.itemRenderer.getModel(itemstack, dropEntity.level, (LivingEntity) null, dropEntity.getId());
        final float sz = 1F;
        matrixStackIn.scale(sz, sz, sz);
        matrixStackIn.translate(0, 0.1, 0);
        matrixStackIn.mulPose(Vector3f.YP.rotation(RenderUtil.getSpin(dropEntity.getExistTick(), partialTicks)));

        matrixStackIn.pushPose();
        this.itemRenderer.render(itemstack, ItemTransforms.TransformType.GROUND, false, matrixStackIn, bufferSource, packedLightIn, OverlayTexture.NO_OVERLAY, bakedmodel);
        matrixStackIn.popPose();

        matrixStackIn.popPose();
        super.render(dropEntity, yaw, partialTicks, matrixStackIn, bufferSource, packedLightIn);
    }

    public ResourceLocation getTextureLocation(DropEntity p_115034_) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}