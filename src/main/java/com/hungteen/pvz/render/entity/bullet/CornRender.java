package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.CornEntity;
import com.hungteen.pvz.model.entity.bullet.CornModel;
import com.hungteen.pvz.render.entity.PVZEntityRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class CornRender extends PVZEntityRender<CornEntity> {

	private static final ResourceLocation CORN_TEX = StringUtil.prefix("textures/entity/misc/corn.png");
	
	public CornRender(EntityRendererManager renderManager) {
		super(renderManager, new CornModel());
	}

	@Override
	public void render(CornEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(
				MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) + 180.0F));
		matrixStackIn.mulPose(Vector3f.ZP
				.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.popPose();
	}

	@Override
	protected float getScaleByEntity(CornEntity entity) {
		return 1.2F;
	}

	@Override
	public ResourceLocation getTextureLocation(CornEntity entity) {
		return CORN_TEX;
	}

}
