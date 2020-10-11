package com.hungteen.pvz.model.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.TombStoneEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TombStoneModel extends EntityModel<TombStoneEntity> {
	private final ModelRenderer total;

	public TombStoneModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.setTextureOffset(56, 105).addBox(-9.0F, -4.0F, -9.0F, 18.0F, 4.0F, 18.0F, 0.0F, false);
		total.setTextureOffset(56, 81).addBox(-9.0F, -38.0F, -9.0F, 18.0F, 4.0F, 18.0F, 0.0F, false);
		total.setTextureOffset(0, 82).addBox(-8.0F, -34.0F, -8.0F, 2.0F, 30.0F, 16.0F, 0.0F, false);
		total.setTextureOffset(92, 0).addBox(6.0F, -34.0F, -8.0F, 2.0F, 30.0F, 16.0F, 0.0F, false);
		total.setTextureOffset(3, 5).addBox(-6.0F, -34.0F, -7.0F, 12.0F, 30.0F, 15.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(TombStoneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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