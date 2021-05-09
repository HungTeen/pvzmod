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
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(-1.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setPos(2.0F, -20.0F, -1.0F);
		total.addChild(head);
		head.texOffs(25, 55).addBox(-4.0F, -6.0F, -2.5F, 6.0F, 6.0F, 7.0F, 0.0F, false);
		head.texOffs(0, 60).addBox(-4.0F, -6.5F, -2.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);
		head.texOffs(60, 28).addBox(-4.0F, -6.0F, -2.0F, 6.0F, 6.0F, 6.0F, 0.3F, false);
		head.texOffs(51, 55).addBox(-4.5F, -6.0F, -2.0F, 7.0F, 6.0F, 6.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setPos(-2.0F, -2.0F, -5.0F);
		head.addChild(Eyes);
		Eyes.texOffs(0, 44).addBox(-1.0F, -2.5F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Eyes.texOffs(42, 4).addBox(2.0F, -2.5F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		light = new ModelRenderer(this);
		light.setPos(0.0F, -17.0F, 0.0F);
		total.addChild(light);
		light.texOffs(21, 105).addBox(-4.0F, -12.0F, -5.0F, 10.0F, 12.0F, 0.0F, 0.0F, false);
		light.texOffs(0, 95).addBox(-4.0F, -12.0F, -5.0F, 0.0F, 12.0F, 10.0F, 0.0F, false);
		light.texOffs(43, 105).addBox(-4.0F, -12.0F, 5.0F, 10.0F, 12.0F, 0.0F, 0.0F, false);
		light.texOffs(65, 95).addBox(6.0F, -12.0F, -5.0F, 0.0F, 12.0F, 10.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(60, 44).addBox(-2.0F, -14.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);
		body.texOffs(72, 0).addBox(-1.5F, -10.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.2F, false);
		body.texOffs(46, 67).addBox(-1.5F, -10.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
		body.texOffs(0, 51).addBox(-3.0F, -15.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		body.texOffs(42, 0).addBox(-4.0F, -17.0F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
		body.texOffs(30, 44).addBox(-4.0F, -32.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.5F, false);
		body.texOffs(40, 16).addBox(-4.0F, -33.5F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
		body.texOffs(0, 38).addBox(-4.0F, -35.0F, -5.0F, 10.0F, 3.0F, 10.0F, -0.5F, false);
		body.texOffs(30, 30).addBox(-4.0F, -36.5F, -5.0F, 10.0F, 4.0F, 10.0F, -1.0F, false);
		body.texOffs(26, 72).addBox(-5.0F, -17.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		body.texOffs(66, 67).addBox(-5.0F, -30.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		body.texOffs(70, 12).addBox(6.0F, -17.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		body.texOffs(14, 68).addBox(6.0F, -30.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		body.texOffs(26, 68).addBox(-4.0F, -17.0F, 5.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(40, 28).addBox(-4.0F, -30.0F, 5.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(56, 14).addBox(-4.0F, -17.0F, -6.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(42, 12).addBox(-4.0F, -30.0F, -6.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(66, 78).addBox(6.0F, -30.0F, -6.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);
		body.texOffs(8, 78).addBox(6.0F, -30.0F, 5.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);
		body.texOffs(0, 53).addBox(-4.0F, -18.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(36, 51).addBox(5.0F, -18.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(46, 4).addBox(5.0F, -29.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(30, 44).addBox(5.0F, -29.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(4, 44).addBox(-4.0F, -29.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(28, 51).addBox(5.0F, -18.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(24, 51).addBox(-4.0F, -18.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(4, 51).addBox(-5.0F, -18.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(0, 51).addBox(-5.0F, -18.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(34, 46).addBox(-5.0F, -29.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(30, 46).addBox(-5.0F, -29.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(48, 8).addBox(6.0F, -18.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(4, 46).addBox(6.0F, -29.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(34, 44).addBox(6.0F, -29.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(46, 6).addBox(6.0F, -18.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(32, 51).addBox(-4.0F, -29.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(0, 78).addBox(-5.0F, -30.0F, -6.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);
		body.texOffs(4, 78).addBox(-5.0F, -30.0F, 5.0F, 1.0F, 14.0F, 1.0F, 0.0F, false);
		body.texOffs(0, 0).addBox(-6.0F, -31.001F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
		body.texOffs(71, 54).addBox(-2.0F, -36.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		body.texOffs(0, 73).addBox(-1.0F, -36.5F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(2.0F, -37.0F, -1.0F);
		body.addChild(bone2);
		bone2.texOffs(0, 16).addBox(-2.0F, -2.5F, 0.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		bone2.texOffs(0, 38).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.2F, false);
		bone2.texOffs(0, 9).addBox(-2.5F, -2.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
		bone2.texOffs(0, 0).addBox(-2.0F, -2.0F, -0.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		bone2.texOffs(4, 38).addBox(6.0F, 6.0F, 8.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		bone2.texOffs(4, 23).addBox(-10.0F, 6.0F, 8.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		bone2.texOffs(0, 23).addBox(-10.0F, 6.0F, -8.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		bone2.texOffs(4, 16).addBox(6.0F, 6.0F, -8.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(8.0F, 7.0F, -9.0F);
		bone2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.3491F, 0.0F, 0.0F);
		cube_r1.texOffs(8, 2).addBox(-1.0F, -0.6F, -0.5F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		cube_r1.texOffs(6, 9).addBox(-19.0F, -0.6F, -0.5F, 2.0F, 0.0F, 2.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-10.0F, 6.0F, 10.0F);
		bone2.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.3491F, 0.0F, 0.0F);
		cube_r2.texOffs(5, 0).addBox(-1.0F, -0.0009F, 0.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		cube_r2.texOffs(8, 11).addBox(17.0F, -0.0009F, 0.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(-3.0F, 36.0F, 3.0F);
		bone2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.5672F, 0.0F);
		cube_r3.texOffs(42, 0).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r3.texOffs(0, 7).addBox(-4.0F, 0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(1.0F, 36.0F, 3.0F);
		bone2.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, -0.5672F, 0.0F);
		cube_r4.texOffs(1, 29).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r4.texOffs(1, 33).addBox(-1.0F, 0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(1.0F, 36.0F, -2.0F);
		bone2.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.7854F, 0.0F);
		cube_r5.texOffs(30, 38).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r5.texOffs(1, 26).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(-3.0F, 36.0F, -2.0F);
		bone2.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, -0.7854F, 0.0F);
		cube_r6.texOffs(0, 42).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r6.texOffs(0, 5).addBox(-3.0F, 0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(PlanternEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}