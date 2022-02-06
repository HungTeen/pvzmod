package com.hungteen.pvz.client.render.entity.effects;

import com.hungteen.pvz.common.entity.effect.OriginEffectEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OriginEffectRender extends EntityRenderer<OriginEffectEntity>{


	public OriginEffectRender(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(OriginEffectEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		final int color = entityIn.getColor();
		matrixStackIn.pushPose();
		final int T = 100;
		final float len = 1.2F;
		final float scale = 1F;
		final float maxScale = 0.1F * AnimationUtil.getUpDownUpDown(entityIn.getExistTick() % T, T, 90);
		matrixStackIn.scale(scale + maxScale, scale + maxScale, scale + maxScale);
		matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(2 * entityIn.getExistTick()));
		
		matrixStackIn.pushPose();
		renderLight(matrixStackIn, bufferIn, len, color);
		matrixStackIn.popPose();
		
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(90));
		renderLight(matrixStackIn, bufferIn, len, color);
		matrixStackIn.popPose();
		
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(-90));
		renderLight(matrixStackIn, bufferIn, len, color);
		matrixStackIn.popPose();
		
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(180));
		renderLight(matrixStackIn, bufferIn, len, color);
		matrixStackIn.popPose();
		
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.ZN.rotationDegrees(90));
		renderLight(matrixStackIn, bufferIn, len, color);
		matrixStackIn.popPose();
		
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.ZN.rotationDegrees(-90));
		renderLight(matrixStackIn, bufferIn, len, color);
		matrixStackIn.popPose();
		
		matrixStackIn.popPose();
	}

	private static void renderLight(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, float len, int color){
		final IVertexBuilder ivertexbuilder2 = bufferIn.getBuffer(RenderType.lightning());
		matrixStackIn.pushPose();
		for(int i = 0; (float)i < 4; ++i) {
			Matrix4f matrix4f = matrixStackIn.last().pose();
			float f3 = 1;
			final int c1 = color / 256 / 256;
			final int c2 = color / 256 % 256;
			final int c3 = color % 256;
			ivertexbuilder2.vertex(matrix4f, 0.0f, 0f, 0.0f).color(c1, c2, c3, 255).endVertex();
			ivertexbuilder2.vertex(matrix4f, - 0.866f * f3, - len, (- 0.5F * f3)).color(c1, c2, c3, 20).endVertex();
			ivertexbuilder2.vertex(matrix4f, 0.866f * f3, - len, (- 0.5F * f3)).color(c1, c2, c3, 20).endVertex();
			ivertexbuilder2.vertex(matrix4f, 0.0f, - len, (1.0F * f3)).color(c1, c2, c3, 20).endVertex();
			ivertexbuilder2.vertex(matrix4f, - 0.866f * f3, - len, (- 0.5F * f3)).color(c1, c2, c3, 20).endVertex();
		}
		matrixStackIn.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(OriginEffectEntity p_110775_1_) {
		return null;
	}


}
