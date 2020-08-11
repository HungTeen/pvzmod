package com.hungteen.pvz.model.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.explosion.PotatoMineEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PotatoMineModel extends EntityModel<PotatoMineEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer string;

	public PotatoMineModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 17.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(1, 105).addBox(-10.0F, 5.0F, -10.0F, 20.0F, 2.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(4, 82).addBox(-9.0F, 2.0F, -9.0F, 18.0F, 3.0F, 18.0F, 0.0F, false);
		body.setTextureOffset(4, 61).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		body.setTextureOffset(1, 2).addBox(-6.0F, -2.0F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);
		body.setTextureOffset(21, 24).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

		string = new ModelRenderer(this);
		string.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(string);
		string.setTextureOffset(8, 35).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(PotatoMineEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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