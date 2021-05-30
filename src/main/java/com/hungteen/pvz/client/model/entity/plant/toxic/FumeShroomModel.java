package com.hungteen.pvz.client.model.entity.plant.toxic;

import com.hungteen.pvz.common.entity.plant.toxic.FumeShroomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class FumeShroomModel extends EntityModel<FumeShroomEntity> {
	private final ModelRenderer total;

	public FumeShroomModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(1, 105).addBox(-6.0F, -10.0F, -6.0F, 12.0F, 10.0F, 12.0F, 0.0F, false);
		total.texOffs(51, 101).addBox(-8.0F, -21.0F, -8.0F, 16.0F, 10.0F, 16.0F, 0.0F, false);
		total.texOffs(1, 88).addBox(-7.0F, -11.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		total.texOffs(1, 70).addBox(-7.0F, -22.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		total.texOffs(4, 63).addBox(-1.0F, -17.0F, -10.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		total.texOffs(16, 60).addBox(-2.0F, -18.0F, -13.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(FumeShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}