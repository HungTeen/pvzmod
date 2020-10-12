package com.hungteen.pvz.model.entity.plant.magic;

import com.hungteen.pvz.entity.plant.magic.HypnoShroomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class HypnoShroomModel extends EntityModel<HypnoShroomEntity> {
	private final ModelRenderer body;
	private final ModelRenderer hat;

	public HypnoShroomModel() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(63, 86).addBox(-8.0F, -25.0F, -8.0F, 16.0F, 25.0F, 16.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, -26.0F, 0.0F);
		body.addChild(hat);
		setRotationAngle(hat, -0.0873F, 0.0F, 0.0F);
		hat.setTextureOffset(1, 1).addBox(-12.0F, -6.0F, -12.0F, 24.0F, 8.0F, 24.0F, 0.0F, false);
		hat.setTextureOffset(1, 66).addBox(-7.0F, -20.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);
		hat.setTextureOffset(1, 35).addBox(-10.0F, -14.0F, -10.0F, 20.0F, 8.0F, 20.0F, 0.0F, false);
		hat.setTextureOffset(2, 90).addBox(-4.0F, -24.0F, -4.0F, 8.0F, 4.0F, 8.0F, 0.0F, false);
		hat.setTextureOffset(4, 107).addBox(-2.0F, -26.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(HypnoShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.hat.rotateAngleY = ageInTicks / 30;
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