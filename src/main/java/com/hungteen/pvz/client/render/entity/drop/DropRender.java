package com.hungteen.pvz.client.render.entity.drop;

import com.hungteen.pvz.common.entity.drop.DropEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class DropRender<T extends DropEntity> extends EntityRenderer<T>{

	private EntityModel<Entity> model;
	
	public DropRender(EntityRendererManager renderManager,EntityModel<Entity> m) {
		super(renderManager);
		this.model=m;
	}

	protected abstract float getRenderSize(T entity);
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		matrixStackIn.scale(-1, -1, 1);
		float f=getRenderSize(entityIn);
		matrixStackIn.scale(f,f,f);
		matrixStackIn.translate(0.0, -1.501, 0.0);
//		matrixStackIn.rotate(Vector3f.XN.rotationDegrees(45));
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
        this.model.setupAnim(entityIn, 0,0,entityIn.tickCount+partialTicks,0,0);
        this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
	}
}
