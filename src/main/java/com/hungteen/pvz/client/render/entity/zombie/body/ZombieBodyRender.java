package com.hungteen.pvz.client.render.entity.zombie.body;

import com.hungteen.pvz.common.entity.zombie.body.ZombieBodyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class ZombieBodyRender extends EntityRenderer<ZombieBodyEntity> {

//	private final NormalZombieModel model = new NormalZombieModel();
	
	public ZombieBodyRender(EntityRendererManager p_i46179_1_) {
		super(p_i46179_1_);
	}
	
	@Override
	public void render(ZombieBodyEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
//		matrixStackIn.pushPose();
//		matrixStackIn.scale(-1, -1, 1);
//		matrixStackIn.translate(0.0, -1.501, 0.0);
//		float sz = 1F;
//		matrixStackIn.scale(sz, sz, sz);
//        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.renderType(NormalZombieRender.TEXTURE));
////        this.model.setupAnim(entityIn, 0, 0, entityIn.tickCount + partialTicks, 0, 0);
//        this.model.renderHand(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
//        matrixStackIn.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieBodyEntity p_110775_1_) {
		return null;
	}
 
}
