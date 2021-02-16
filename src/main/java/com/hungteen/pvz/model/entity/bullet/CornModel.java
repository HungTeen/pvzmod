package com.hungteen.pvz.model.entity.bullet;

import com.hungteen.pvz.entity.bullet.CornEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CornModel extends EntityModel<CornEntity> {
	private final ModelRenderer total;
	private final ModelRenderer corn;

	public CornModel() {
		textureWidth = 64;
		textureHeight = 64;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		corn = new ModelRenderer(this);
		corn.setRotationPoint(0.0F, -1.0F, -2.0F);
		total.addChild(corn);
		corn.setTextureOffset(0, 26).addBox(-4.5F, -28.0F, -2.5F, 9.0F, 29.0F, 9.0F, 0.0F, false);
		corn.setTextureOffset(36, 56).addBox(-3.5F, -28.75F, -1.5F, 7.0F, 1.0F, 7.0F, -0.25F, false);
	}

	@Override
	public void setRotationAngles(CornEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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