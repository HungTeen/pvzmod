package com.hungteen.pvz.client.render.entity.misc;

import com.hungteen.pvz.client.model.entity.npc.PennyModel;
import com.hungteen.pvz.client.render.entity.npc.PennyRender;
import com.hungteen.pvz.common.entity.misc.DestroyCarEntity;
import com.hungteen.pvz.common.entity.npc.PennyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class DestroyCarRender extends EntityRenderer<DestroyCarEntity> {

	private final EntityModel<PennyEntity> model = new PennyModel();
	
	public DestroyCarRender(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(DestroyCarEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		matrixStackIn.scale(-1, -1, 1);
		float f = 1F;
		matrixStackIn.scale(f, f, f);
		matrixStackIn.translate(0.0, -1.501, 0.0);
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
        this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
	}
	
	@Override
	public ResourceLocation getTextureLocation(DestroyCarEntity entity) {
		return PennyRender.PENNY_TEX;
	}

}
