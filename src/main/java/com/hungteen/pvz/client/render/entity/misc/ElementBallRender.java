package com.hungteen.pvz.client.render.entity.misc;

import com.hungteen.pvz.common.entity.misc.ElementBallEntity;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity.ElementTypes;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElementBallRender extends EntityRenderer<ElementBallEntity>{

	public ElementBallRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void render(ElementBallEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		float f = getScaleByEntity(entityIn);
		matrixStackIn.scale(f, f, f);
		matrixStackIn.translate(- 0.5F, 0, - 0.5F);
//		matrixStackIn.rotate(Vector3f.XP.rotationDegrees(entityIn.ticksExisted * 15));
//		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) + 180.0F));
//		matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(getBlockByEntity(entityIn), matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
        matrixStackIn.popPose();
	}
	
	public float getScaleByEntity(ElementBallEntity entity) {
		return 3;
	}

	public BlockState getBlockByEntity(ElementBallEntity entity) {
		if(entity.getElementBallType() == ElementTypes.FLAME) return Blocks.MAGMA_BLOCK.defaultBlockState();
		return Blocks.BLUE_ICE.defaultBlockState();
	}

	@Override
	public ResourceLocation getTextureLocation(ElementBallEntity entity) {
		return null;
	}
	
}
