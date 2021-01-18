package com.hungteen.pvz.model.entity.plant.defence;

import com.hungteen.pvz.entity.plant.defence.WallNutEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class WallNutModel<T extends WallNutEntity> extends EntityModel<T> {
	private final ModelRenderer body;

	public WallNutModel() {
		textureWidth = 256;
		textureHeight = 256;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureOffset(168, 233).addBox(-11.0F, 23.0F, -11.0F, 22.0F, 1.0F, 22.0F, 0.0F, false);
		body.setTextureOffset(144, 197).addBox(-14.0F, 21.0F, -14.0F, 28.0F, 2.0F, 28.0F, 0.0F, false);
		body.setTextureOffset(0, 180).addBox(-16.0F, -23.0F, -16.0F, 32.0F, 44.0F, 32.0F, 0.0F, false);
		body.setTextureOffset(0, 107).addBox(16.0F, -21.0F, -14.0F, 2.0F, 40.0F, 28.0F, 0.0F, false);
		body.setTextureOffset(65, 106).addBox(-18.0F, -21.0F, -14.0F, 2.0F, 40.0F, 28.0F, 0.0F, false);
		body.setTextureOffset(196, 0).addBox(-14.0F, -21.0F, -18.0F, 28.0F, 40.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(196, 47).addBox(-14.0F, -21.0F, 16.0F, 28.0F, 40.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(144, 96).addBox(-14.0F, -25.0F, -14.0F, 28.0F, 2.0F, 28.0F, 0.0F, false);
		body.setTextureOffset(176, 132).addBox(-11.0F, -27.0F, -11.0F, 22.0F, 2.0F, 22.0F, 0.0F, false);
		body.setTextureOffset(144, 3).addBox(-19.0F, -17.0F, -10.0F, 1.0F, 32.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(88, 0).addBox(18.0F, -17.0F, -10.0F, 1.0F, 32.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(30, 47).addBox(-11.0F, -18.0F, -19.0F, 22.0F, 34.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(2, 4).addBox(-11.0F, -18.0F, 18.0F, 22.0F, 34.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(187, 166).addBox(-7.0F, -29.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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