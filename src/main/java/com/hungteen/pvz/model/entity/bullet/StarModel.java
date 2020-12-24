package com.hungteen.pvz.model.entity.bullet;

import com.hungteen.pvz.entity.bullet.StarEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class StarModel extends EntityModel<StarEntity> {
	private final ModelRenderer total;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;

	public StarModel() {
		textureWidth = 16;
		textureHeight = 16;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(total, 0.0F, 0.7854F, 0.0F);
		total.setTextureOffset(0, 12).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 2.5133F, 0.0F);
		cube_r1.setTextureOffset(0, 0).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 1.2566F, 0.0F);
		cube_r2.setTextureOffset(0, 0).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, -2.5133F, 0.0F);
		cube_r3.setTextureOffset(0, 4).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, -1.2566F, 0.0F);
		cube_r4.setTextureOffset(0, 8).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(StarEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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