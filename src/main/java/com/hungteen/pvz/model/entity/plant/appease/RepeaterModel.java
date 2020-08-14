package com.hungteen.pvz.model.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.RepeaterEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class RepeaterModel extends EntityModel<RepeaterEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer YEZI_1;

	public RepeaterModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(38, 58).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 32.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(104, 104).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -35.0F, 0.0F);
		total.addChild(head);
		head.setTextureOffset(66, 117).addBox(-5.0F, -10.0F, 7.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(25, 23).addBox(-7.0F, -12.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(106, 80).addBox(7.0F, -10.0F, -5.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(106, 57).addBox(-8.0F, -10.0F, -5.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(88, 30).addBox(-5.0F, -13.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(116, 46).addBox(-1.0F, -3.0F, 8.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(5, 39).addBox(-2.0F, -4.0F, 12.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(5, 39).addBox(-2.0F, -10.0F, 12.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(116, 46).addBox(-1.0F, -9.0F, 8.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(64, 86).addBox(-3.0F, -7.0F, -21.0F, 6.0F, 6.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(84, 6).addBox(-4.0F, -7.0F, -21.0F, 1.0F, 6.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(40, 2).addBox(3.0F, -7.0F, -21.0F, 1.0F, 6.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(1, 112).addBox(-3.0F, -8.0F, -21.0F, 6.0F, 1.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(63, 68).addBox(-3.0F, -1.0F, -21.0F, 6.0F, 1.0F, 14.0F, 0.0F, false);

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, -1.0F, 0.0F);
		total.addChild(YEZI_4);
		YEZI_4.setTextureOffset(0, 88).addBox(-11.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_4.setTextureOffset(49, 104).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 0.0F, -2.0F);
		total.addChild(YEZI_3);
		YEZI_3.setTextureOffset(95, 124).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		YEZI_3.setTextureOffset(0, 0).addBox(-4.0F, -2.0F, -11.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 0.0F, 2.0F);
		total.addChild(YEZI_2);
		YEZI_2.setTextureOffset(0, 74).addBox(-4.0F, -2.0F, 3.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_2.setTextureOffset(114, 118).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 0.0F, 0.0F);
		total.addChild(YEZI_1);
		YEZI_1.setTextureOffset(0, 65).addBox(0.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		YEZI_1.setTextureOffset(0, 50).addBox(3.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(RepeaterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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