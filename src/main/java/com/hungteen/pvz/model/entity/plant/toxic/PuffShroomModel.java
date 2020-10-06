package com.hungteen.pvz.model.entity.plant.toxic;

import com.hungteen.pvz.entity.plant.toxic.PuffShroomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PuffShroomModel extends EntityModel<PuffShroomEntity> {
	private final ModelRenderer total;

	public PuffShroomModel() {
		textureWidth = 64;
		textureHeight = 64;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.setTextureOffset(31, 47).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		total.setTextureOffset(0, 0).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 4.0F, 12.0F, 0.0F, false);
		total.setTextureOffset(0, 17).addBox(-5.0F, -15.0F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, false);
		total.setTextureOffset(0, 31).addBox(-4.0F, -16.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		total.setTextureOffset(1, 42).addBox(-1.0F, -4.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		total.setTextureOffset(9, 41).addBox(-2.0F, -5.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(PuffShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}