package com.hungteen.pvz.model.entity.plant.light;

import com.hungteen.pvz.entity.plant.light.PlanternEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PlanternModel extends EntityModel<PlanternEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer Eyes;
	private final ModelRenderer light;
	private final ModelRenderer body;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;

	public PlanternModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(-1.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(2.0F, -20.0F, -1.0F);
		total.addChild(head);
		head.setTextureOffset(25, 55).addBox(-4.0F, -6.0F, -2.5F, 6.0F, 6.0F, 7.0F, 0.0F, false);
		head.setTextureOffset(0, 60).addBox(-4.0F, -6.5F, -2.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(60, 28).addBox(-4.0F, -6.0F, -2.0F, 6.0F, 6.0F, 6.0F, 0.3F, false);
		head.setTextureOffset(51, 55).addBox(-4.5F, -6.0F, -2.0F, 7.0F, 6.0F, 6.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(-2.0F, -2.0F, -5.0F);
		head.addChild(Eyes);
		Eyes.setTextureOffset(0, 44).addBox(-1.0F, -2.5F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Eyes.setTextureOffset(42, 4).addBox(2.0F, -2.5F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		light = new ModelRenderer(this);
		light.setRotationPoint(0.0F, -17.0F, 0.0F);
		total.addChild(light);
		light.setTextureOffset(21, 105).addBox(-4.0F, -12.0F, -5.0F, 10.0F, 12.0F, 0.0F, 0.0F, false);
		light.setTextureOffset(0, 95).addBox(-4.0F, -12.0F, -5.0F, 0.0F, 12.0F, 10.0F, 0.0F, false);
		light.setTextureOffset(43, 105).addBox(-4.0F, -12.0F, 5.0F, 10.0F, 12.0F, 0.0F, 0.0F, false);
		light.setTextureOffset(65, 95).addBox(6.0F, -12.0F, -5.0F, 0.0F, 12.0F, 10.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(60, 44).addBox(-2.0F, -14.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(72, 0).addBox(-1.5F, -10.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.2F, false);
		body.setTextureOffset(46, 67).addBox(-1.5F, -10.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(0, 51).addBox(-3.0F, -15.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(42, 0).addBox(-4.0F, -17.0F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(30, 44).addBox(-4.0F, -32.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.5F, false);
		body.setTextureOffset(40, 16).addBox(-4.0F, -33.5F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(0, 38).addBox(-4.0F, -35.0F, -5.0F, 10.0F, 3.0F, 10.0F, -0.5F, false);
		body.setTextureOffset(30, 30).addBox(-4.0F, -36.5F, -5.0F, 10.0F, 4.0F, 10.0F, -1.0F, false);
		body.setTextureOffset(26, 72).addBox(-5.0F, -17.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(66, 67).addBox(-5.0F, -30.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(70, 12).addBox(6.0F, -17.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(14, 68).addBox(6.0F, -30.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(26, 68).addBox(-4.0F, -17.0F, 5.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(40, 28).addBox(-4.0F, -30.0F, 5.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(56, 14).addBox(-4.0F, -17.0F, -6.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(42, 12).addBox(-4.0F, -30.0F, -6.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(66, 78).addBox(6.0F, -30.0F, -6.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(8, 78).addBox(6.0F, -30.0F, 5.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(0, 53).addBox(-4.0F, -18.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(36, 51).addBox(5.0F, -18.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(46, 4).addBox(5.0F, -29.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(30, 44).addBox(5.0F, -29.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(4, 44).addBox(-4.0F, -29.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(28, 51).addBox(5.0F, -18.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(24, 51).addBox(-4.0F, -18.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(4, 51).addBox(-5.0F, -18.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(0, 51).addBox(-5.0F, -18.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(34, 46).addBox(-5.0F, -29.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(30, 46).addBox(-5.0F, -29.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(48, 8).addBox(6.0F, -18.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(4, 46).addBox(6.0F, -29.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(34, 44).addBox(6.0F, -29.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(46, 6).addBox(6.0F, -18.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(32, 51).addBox(-4.0F, -29.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(0, 78).addBox(-5.0F, -30.0F, -6.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(4, 78).addBox(-5.0F, -30.0F, 5.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-6.0F, -31.001F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
		body.setTextureOffset(71, 54).addBox(-2.0F, -36.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 73).addBox(-1.0F, -36.5F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(2.0F, -37.0F, -1.0F);
		body.addChild(bone2);
		bone2.setTextureOffset(0, 16).addBox(-2.0F, -2.5F, 0.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		bone2.setTextureOffset(0, 38).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.2F, false);
		bone2.setTextureOffset(0, 9).addBox(-2.5F, -2.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
		bone2.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -0.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		bone2.setTextureOffset(4, 38).addBox(6.0F, 6.0F, 8.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		bone2.setTextureOffset(4, 23).addBox(-10.0F, 6.0F, 8.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		bone2.setTextureOffset(0, 23).addBox(-10.0F, 6.0F, -8.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		bone2.setTextureOffset(4, 16).addBox(6.0F, 6.0F, -8.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(8.0F, 7.0F, -9.0F);
		bone2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.3491F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(8, 2).addBox(-1.0F, -0.6F, -0.5F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		cube_r1.setTextureOffset(6, 9).addBox(-19.0F, -0.6F, -0.5F, 2.0F, 0.0F, 2.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-10.0F, 6.0F, 10.0F);
		bone2.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.3491F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(5, 0).addBox(-1.0F, -0.0009F, 0.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		cube_r2.setTextureOffset(8, 11).addBox(17.0F, -0.0009F, 0.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-3.0F, 36.0F, 3.0F);
		bone2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.5672F, 0.0F);
		cube_r3.setTextureOffset(42, 0).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r3.setTextureOffset(0, 7).addBox(-4.0F, 0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(1.0F, 36.0F, 3.0F);
		bone2.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, -0.5672F, 0.0F);
		cube_r4.setTextureOffset(1, 29).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r4.setTextureOffset(1, 33).addBox(-1.0F, 0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(1.0F, 36.0F, -2.0F);
		bone2.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.7854F, 0.0F);
		cube_r5.setTextureOffset(30, 38).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r5.setTextureOffset(1, 26).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-3.0F, 36.0F, -2.0F);
		bone2.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, -0.7854F, 0.0F);
		cube_r6.setTextureOffset(0, 42).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r6.setTextureOffset(0, 5).addBox(-3.0F, 0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(PlanternEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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