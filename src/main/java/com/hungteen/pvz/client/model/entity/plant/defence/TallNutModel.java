package com.hungteen.pvz.client.model.entity.plant.defence;

import com.hungteen.pvz.common.entity.plant.defence.TallNutEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TallNutModel extends EntityModel<TallNutEntity> {
	private final ModelRenderer body;

	public TallNutModel() {
		texWidth = 512;
		texHeight = 512;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(416, 486).addBox(-11.0F, -1.0F, -11.0F, 22.0F, 1.0F, 22.0F, 0.0F, false);
		body.texOffs(272, 476).addBox(-15.0F, -3.0F, -15.0F, 30.0F, 2.0F, 30.0F, 0.0F, false);
		body.texOffs(6, 408).addBox(-17.0F, -71.0F, -17.0F, 34.0F, 68.0F, 34.0F, 0.0F, false);
		body.texOffs(156, 420).addBox(17.0F, -69.0F, -14.0F, 2.0F, 64.0F, 28.0F, 0.0F, false);
		body.texOffs(0, 308).addBox(-19.0F, -69.0F, -14.0F, 2.0F, 64.0F, 28.0F, 0.0F, false);
		body.texOffs(84, 334).addBox(-14.0F, -69.0F, -19.0F, 28.0F, 64.0F, 2.0F, 0.0F, false);
		body.texOffs(174, 348).addBox(-14.0F, -69.0F, 17.0F, 28.0F, 64.0F, 2.0F, 0.0F, false);
		body.texOffs(246, 428).addBox(-14.0F, -73.0F, -14.0F, 28.0F, 2.0F, 28.0F, 0.0F, false);
		body.texOffs(380, 442).addBox(-11.0F, -75.0F, -11.0F, 22.0F, 2.0F, 22.0F, 0.0F, false);
		body.texOffs(466, 354).addBox(-20.0F, -66.0F, -10.0F, 1.0F, 57.0F, 20.0F, 0.0F, false);
		body.texOffs(410, 342).addBox(19.0F, -64.0F, -10.0F, 1.0F, 55.0F, 20.0F, 0.0F, false);
		body.texOffs(346, 356).addBox(-11.0F, -65.0F, -20.0F, 22.0F, 57.0F, 1.0F, 0.0F, false);
		body.texOffs(278, 344).addBox(-11.0F, -65.0F, 19.0F, 22.0F, 57.0F, 1.0F, 0.0F, false);
		body.texOffs(458, 326).addBox(-7.0F, -77.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(TallNutEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}