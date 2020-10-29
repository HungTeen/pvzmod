package com.hungteen.pvz.model.entity.plant.defence;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.7.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PumpkinModel extends EntityModel<Entity> {
	private final ModelRenderer total;

	public PumpkinModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.setTextureOffset(0, 95).addBox(-16.0F, -1.0F, -16.0F, 32.0F, 1.0F, 32.0F, 0.0F, false);
		total.setTextureOffset(0, 72).addBox(-16.0F, -17.0F, -16.0F, 32.0F, 16.0F, 1.0F, 0.0F, false);
		total.setTextureOffset(62, 51).addBox(-16.0F, -17.0F, 15.0F, 32.0F, 16.0F, 1.0F, 0.0F, false);
		total.setTextureOffset(0, 0).addBox(-16.0F, -17.0F, -15.0F, 1.0F, 16.0F, 30.0F, 0.0F, false);
		total.setTextureOffset(66, 0).addBox(15.0F, -17.0F, -15.0F, 1.0F, 16.0F, 30.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}