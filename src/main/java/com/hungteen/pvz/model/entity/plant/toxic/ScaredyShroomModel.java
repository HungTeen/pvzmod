package com.hungteen.pvz.model.entity.plant.toxic;

import com.hungteen.pvz.entity.plant.toxic.ScaredyShroomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ScaredyShroomModel extends EntityModel<ScaredyShroomEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer hat;
	private final ModelRenderer body;

	public ScaredyShroomModel() {
		textureWidth = 64;
		textureHeight = 64;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -3.0F, -3.0F);
		total.addChild(head);
		head.setTextureOffset(52, 2).addBox(-1.0F, -18.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(1, 3).addBox(-2.0F, -19.0F, -4.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(31, 33).addBox(-4.0F, -23.0F, -1.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, -23.0F, 2.0F);
		head.addChild(hat);
		setRotationAngle(hat, -0.6109F, 0.0F, 0.0F);
		hat.setTextureOffset(0, 0).addBox(-6.0F, -10.1161F, -3.4601F, 12.0F, 12.0F, 13.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(-1.0F, -13.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(0, 40).addBox(-1.0F, -4.0F, -2.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(17, 55).addBox(-2.0F, 11.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(17, 55).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ScaredyShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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