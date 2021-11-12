package com.hungteen.pvz.client.events.handler;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3f;

public class PVZEntityRenderHandler {

	public static final BlockState ICE_BLOCK = Blocks.FROSTED_ICE.defaultBlockState();
	
	public static void checkBungeeHandStand(LivingEntity entity, MatrixStack stack) {
		if(entity instanceof PVZPlantEntity || entity instanceof PVZZombieEntity) return ;
		if(entity.getVehicle() instanceof BungeeZombieEntity) {
			stack.mulPose(Vector3f.ZP.rotationDegrees(180F));
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void checkAndRenderFrozenIce(LivingEntity entity, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(! EntityUtil.isEntityValid(entity) || ! EntityUtil.isEntityFrozen(entity)) return ;
		matrixStackIn.pushPose();
		float scale = 0.5F;
		matrixStackIn.scale(scale, scale, scale);
		matrixStackIn.pushPose();
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90F));
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180F));
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(- 90F));
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
		matrixStackIn.popPose();
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void checkAndRenderButter(LivingRenderer r, LivingEntity entity, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(! EntityUtil.isEntityValid(entity) || ! EntityUtil.isEntityButter(entity)) return ;
		matrixStackIn.pushPose();
		float scale = 0.7F;
		matrixStackIn.scale(scale, scale, scale);
		matrixStackIn.translate(- 0.5F, entity.getBbHeight() / scale - 0.5F, - 0.5F);
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(BlockRegister.BUTTER_BLOCK.get().defaultBlockState(), matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
	}
}
