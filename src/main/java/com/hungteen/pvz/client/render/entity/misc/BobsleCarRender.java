package com.hungteen.pvz.client.render.entity.misc;

import com.hungteen.pvz.client.model.entity.misc.BobsleCarModel;
import com.hungteen.pvz.common.entity.misc.BobsleCarEntity;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BobsleCarRender extends EntityRenderer<BobsleCarEntity>{

	private EntityModel<BobsleCarEntity> model;
	
	public BobsleCarRender(EntityRendererManager renderManager) {
		super(renderManager);
		this.model=new BobsleCarModel();
	}

	@Override
	public void render(BobsleCarEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		matrixStackIn.translate(0.0D, 0.75D, 0.0D);
	    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
		matrixStackIn.scale(-1, -1, 1);
		float f=0.5f;
		matrixStackIn.scale(f,f,f);
//		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
//		matrixStackIn.translate(0.0, -1.501, 0.0);
//		matrixStackIn.rotate(Vector3f.XN.rotationDegrees(45));
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
        this.model.setupAnim(entityIn, 0,0,entityIn.tickCount+partialTicks,0,0);
        this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
	}
	
	@Override
	public ResourceLocation getTextureLocation(BobsleCarEntity entity) {
		return StringUtil.prefix("textures/entity/misc/bobsle_car.png");
	}

}
