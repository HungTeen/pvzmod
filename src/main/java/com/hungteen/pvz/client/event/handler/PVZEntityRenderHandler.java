package com.hungteen.pvz.client.event.handler;

import com.hungteen.pvz.client.RenderUtil;
import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.misc.FrozenModel;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.Util;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.data.EmptyModelData;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-06 17:02
 **/
public class PVZEntityRenderHandler {

    private static final ResourceLocation FROZEN = Util.texture("entity/layer/frozen.png");
//    public static void checkBungeeHandStand(LivingEntity entity, MatrixStack stack) {
//        if(entity instanceof PVZPlantEntity || entity instanceof PVZZombieEntity) return ;
//        if(entity.getVehicle() instanceof BungeeZombieEntity) {
//            stack.mulPose(Vector3f.ZP.rotationDegrees(180F));
//        }
//    }

    public static void checkAndRenderFrozenIce(LivingEntity entity, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        if(EntityUtil.isEntityValid(entity) && EntityUtil.isEntityFrozen(entity)){
            final float width = entity.getBbWidth();
            final int needIceCount = Mth.ceil(width);
            for(int i = 0; i < needIceCount; ++ i){
                for(int j = 0; j < needIceCount; ++ j){
//                	final int limit = needIceCount / 2;
//                	final int offsetX = (i > needIceCount / 2) ? 
                    poseStack.pushPose();
//                    poseStack.translate(- 0.5 * i - 0.5, 0, - 0.5 * j - 0.5);
//                    Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.ICE.defaultBlockState(), poseStack, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
                    poseStack.popPose();

                    poseStack.pushPose();
                    poseStack.scale(1, -1, 1);
                    poseStack.translate(0, -1.5, 0);
                    FrozenModel model = new FrozenModel(RenderUtil.getModelRoot(PVZModelLayers.FROZEN));
                    model.renderToBuffer(poseStack, bufferIn.getBuffer(model.renderType(FROZEN)), packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
                    poseStack.popPose();
                }
            }
        }
//        poseStack.pushPose();
//        float scale = 0.5F;
//        poseStack.scale(scale, scale, scale);
//        poseStack.pushPose();
//        poseStack.popPose();
//        poseStack.pushPose();
//        poseStack.mulPose(Vector3f.YP.rotationDegrees(90F));
//        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, poseStack, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
//        poseStack.popPose();
//        poseStack.pushPose();
//        poseStack.mulPose(Vector3f.YP.rotationDegrees(180F));
//        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, poseStack, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
//        poseStack.popPose();
//        poseStack.pushPose();
//        poseStack.mulPose(Vector3f.YP.rotationDegrees(- 90F));
//        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, poseStack, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
//        poseStack.popPose();
//        poseStack.popPose();
    }

//    @SuppressWarnings({ "deprecation", "rawtypes" })
//    public static void checkAndRenderButter(LivingRenderer r, LivingEntity entity, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
//        if(! EntityUtil.isEntityValid(entity) || ! EntityUtil.isEntityButter(entity)) return ;
//        matrixStackIn.pushPose();
//        float scale = 0.7F;
//        matrixStackIn.scale(scale, scale, scale);
//        matrixStackIn.translate(- 0.5F, entity.getBbHeight() / scale - 0.5F, - 0.5F);
//        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(BlockRegister.BUTTER_BLOCK.get().defaultBlockState(), matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
//        matrixStackIn.popPose();
//    }

}
