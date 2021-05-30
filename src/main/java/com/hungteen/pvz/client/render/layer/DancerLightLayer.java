package com.hungteen.pvz.client.render.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DancerLightLayer<T extends Entity> extends LayerRenderer<T, EntityModel<T>>{
	   
	public DancerLightLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {
		if(! entitylivingbaseIn.isAlive() || entitylivingbaseIn.isInvisible()) {
			return ;
		}
        IVertexBuilder ivertexbuilder2 = bufferIn.getBuffer(RenderType.lightning());
        matrixStackIn.pushPose();
        for(int i = 0; (float)i < 4; ++i) {
           Matrix4f matrix4f = matrixStackIn.last().pose();
           float f3 = 2;
           ivertexbuilder2.vertex(matrix4f, 0.0f, - 10.0f, 0.0f).color(128, 0, 128, 255).endVertex();
           ivertexbuilder2.vertex(matrix4f, - 0.866f * f3, 2, (- 0.5F * f3)).color(128, 0, 128, 20).endVertex();
           ivertexbuilder2.vertex(matrix4f, 0.866f * f3, 2, (- 0.5F * f3)).color(128, 0, 128, 20).endVertex();
           ivertexbuilder2.vertex(matrix4f, 0.0f, 2, (1.0F * f3)).color(128, 0, 128, 20).endVertex();
           ivertexbuilder2.vertex(matrix4f, - 0.866f * f3, 2, (- 0.5F * f3)).color(128 , 0, 128, 20).endVertex();
        }
        matrixStackIn.popPose();
	}

}
