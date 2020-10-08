package com.hungteen.pvz.model.entity.plant.light;

import com.hungteen.pvz.entity.plant.light.SunShroomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SunShroomModel extends EntityModel<SunShroomEntity> {
	private final ModelRenderer body;
	private final ModelRenderer hat;

	public SunShroomModel() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(1, 1).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, -14.0F, 0.0F);
		body.addChild(hat);
		hat.setTextureOffset(2, 97).addBox(-11.0F, -7.0F, -11.0F, 22.0F, 7.0F, 22.0F, 0.0F, false);
		hat.setTextureOffset(1, 32).addBox(-10.0F, -12.0F, -10.0F, 20.0F, 5.0F, 20.0F, 0.0F, false);
		hat.setTextureOffset(62, 4).addBox(-8.0F, -15.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		hat.setTextureOffset(77, 27).addBox(-6.0F, -16.0F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(SunShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}