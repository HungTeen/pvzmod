package com.hungteen.pvz.model.entity.plant.light;

import com.hungteen.pvz.entity.plant.light.SunFlowerEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SunFlowerModel extends EntityModel<SunFlowerEntity> {
	private final ModelRenderer total;
	private final ModelRenderer leftarm2;
	private final ModelRenderer rightarm;
	private final ModelRenderer TUO;
	private final ModelRenderer HUABAN;
	private final ModelRenderer body;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_1;
	private final ModelRenderer YEZI_2;

	public SunFlowerModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		leftarm2 = new ModelRenderer(this);
		leftarm2.setRotationPoint(3.0F, -15.0F, 0.0F);
		total.addChild(leftarm2);
		setRotationAngle(leftarm2, 0.0F, 0.0F, -0.1745F);
		leftarm2.setTextureOffset(0, 0).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 3.0F, 2.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-3.0F, -15.0F, 0.0F);
		total.addChild(rightarm);
		setRotationAngle(rightarm, 0.0F, 0.0F, 0.1745F);
		rightarm.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 3.0F, 2.0F, 0.0F, false);

		TUO = new ModelRenderer(this);
		TUO.setRotationPoint(0.0F, -36.0F, 0.0F);
		total.addChild(TUO);
		TUO.setTextureOffset(72, 100).addBox(-12.0F, -14.0F, -6.0F, 24.0F, 24.0F, 4.0F, 0.0F, false);
		TUO.setTextureOffset(0, 106).addBox(12.0F, -11.0F, -6.0F, 3.0F, 18.0F, 4.0F, 0.0F, false);
		TUO.setTextureOffset(17, 106).addBox(-15.0F, -11.0F, -6.0F, 3.0F, 18.0F, 4.0F, 0.0F, false);
		TUO.setTextureOffset(0, 85).addBox(-9.0F, -17.0F, -6.0F, 18.0F, 3.0F, 4.0F, 0.0F, false);
		TUO.setTextureOffset(0, 74).addBox(-9.0F, 10.0F, -6.0F, 18.0F, 3.0F, 4.0F, 0.0F, false);

		HUABAN = new ModelRenderer(this);
		HUABAN.setRotationPoint(0.0F, -3.0F, 0.0F);
		TUO.addChild(HUABAN);
		HUABAN.setTextureOffset(0, 62).addBox(-4.0F, -16.0F, -6.0F, 8.0F, 2.0F, 4.0F, 0.0F, false);
		HUABAN.setTextureOffset(29, 63).addBox(-4.0F, 16.0F, -6.0F, 8.0F, 2.0F, 4.0F, 0.0F, false);
		HUABAN.setTextureOffset(42, 36).addBox(15.0F, -3.0F, -6.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN.setTextureOffset(73, 73).addBox(-17.0F, -3.0F, -6.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -1.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(112, 43).addBox(-2.0F, -36.0F, -2.0F, 4.0F, 37.0F, 4.0F, 0.0F, false);

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, -1.0F, 0.0F);
		total.addChild(YEZI_4);
		YEZI_4.setTextureOffset(78, 26).addBox(-11.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_4.setTextureOffset(114, 28).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 0.0F, -2.0F);
		total.addChild(YEZI_3);
		YEZI_3.setTextureOffset(114, 19).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		YEZI_3.setTextureOffset(0, 36).addBox(-4.0F, -2.0F, -11.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 0.0F, 0.0F);
		total.addChild(YEZI_1);
		YEZI_1.setTextureOffset(114, 0).addBox(0.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		YEZI_1.setTextureOffset(79, 0).addBox(3.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 0.0F, 2.0F);
		total.addChild(YEZI_2);
		YEZI_2.setTextureOffset(78, 12).addBox(-4.0F, -2.0F, 3.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_2.setTextureOffset(114, 9).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(SunFlowerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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