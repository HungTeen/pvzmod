package com.hungteen.pvz.render.drop;

import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.model.drop.SunModel;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SunRender extends EntityRenderer<SunEntity>{

	private EntityModel<Entity> model;
	
	public SunRender(EntityRendererManager renderManager) {
		super(renderManager);
		model=new SunModel();
	}

	@Override
	public ResourceLocation getEntityTexture(SunEntity entity) {
		return StringUtil.prefix("textures/entity/drop/sun.png");
	}
	
	@Override
	public void render(SunEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.push();
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(this.getEntityTexture(entityIn)));
        this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
	}
}
