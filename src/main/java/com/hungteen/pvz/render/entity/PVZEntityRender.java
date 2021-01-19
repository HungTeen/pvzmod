package com.hungteen.pvz.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public abstract class PVZEntityRender <T extends Entity> extends EntityRenderer<T> {

	protected EntityModel<T> model;
	
	public PVZEntityRender(EntityRendererManager renderManager, EntityModel<T> m) {
		super(renderManager);
		this.model = m;
	}

	protected abstract float getScaleByEntity(T entity);
	
	public Vec3d getTranslateVec(T entity) {
		return new Vec3d(0, 0, 0);
	}
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.push();
		matrixStackIn.scale(-1, -1, 1);
		float f = getScaleByEntity(entityIn);
		matrixStackIn.scale(f, f, f);
		matrixStackIn.translate(0.0, -1.501, 0.0);
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(this.getEntityTexture(entityIn)));
        this.model.setRotationAngles(entityIn, 0, 0, entityIn.ticksExisted + partialTicks, 0, 0);
        this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
	}
	
}