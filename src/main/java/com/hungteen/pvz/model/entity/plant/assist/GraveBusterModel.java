package com.hungteen.pvz.model.entity.plant.assist;

import com.hungteen.pvz.entity.plant.assist.GraveBusterEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GraveBusterModel extends EntityModel<GraveBusterEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer mouse;
	private final ModelRenderer front;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer m1;
	private final ModelRenderer m2;
	private final ModelRenderer back;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer m5;
	private final ModelRenderer m6;
	private final ModelRenderer left;
	private final ModelRenderer bone9;
	private final ModelRenderer m4;
	private final ModelRenderer m3;
	private final ModelRenderer bone12;
	private final ModelRenderer right;
	private final ModelRenderer bone13;
	private final ModelRenderer m7;
	private final ModelRenderer m8;
	private final ModelRenderer bone16;
	private final ModelRenderer head;
	private final ModelRenderer right_wine;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer left_wine;
	private final ModelRenderer bone25;
	private final ModelRenderer bone26;
	private final ModelRenderer bone31;
	private final ModelRenderer bone32;
	private final ModelRenderer back_wine;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone29;
	private final ModelRenderer bone30;
	private final ModelRenderer front_wine;
	private final ModelRenderer bone24;
	private final ModelRenderer bone23;
	private final ModelRenderer bone28;
	private final ModelRenderer bone27;
	private final ModelRenderer bone33;
	private final ModelRenderer bone34;
	private final ModelRenderer bone35;
	private final ModelRenderer bone36;

	public GraveBusterModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 8.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(1, 99).addBox(-8.0F, -32.0F, -6.0F, 16.0F, 16.0F, 12.0F, 0.0F, false);
		body.setTextureOffset(59, 110).addBox(-7.0F, -32.0F, -7.0F, 14.0F, 16.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(92, 110).addBox(-6.0F, -32.0F, 7.0F, 12.0F, 16.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(97, 91).addBox(-7.0F, -32.0F, 6.0F, 14.0F, 16.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(69, 91).addBox(-6.0F, -32.0F, -8.0F, 12.0F, 16.0F, 1.0F, 0.0F, false);

		mouse = new ModelRenderer(this);
		mouse.setRotationPoint(0.0F, 8.0F, 0.0F);
		total.addChild(mouse);
		

		front = new ModelRenderer(this);
		front.setRotationPoint(0.0F, -16.0F, -7.0F);
		mouse.addChild(front);
		

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-5.0F, 0.0F, 0.0F);
		front.addChild(bone);
		bone.setTextureOffset(58, 97).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(6.0F, 0.0F, 0.0F);
		front.addChild(bone2);
		bone2.setTextureOffset(48, 99).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		m1 = new ModelRenderer(this);
		m1.setRotationPoint(-2.0F, 0.0F, 0.0F);
		front.addChild(m1);
		setRotationAngle(m1, -0.0873F, 0.0F, 0.0F);
		m1.setTextureOffset(54, 84).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, 0.0F, false);

		m2 = new ModelRenderer(this);
		m2.setRotationPoint(2.0F, 0.0F, 0.0F);
		front.addChild(m2);
		setRotationAngle(m2, 0.0873F, 0.0F, 0.0F);
		m2.setTextureOffset(40, 84).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, 0.0F, false);

		back = new ModelRenderer(this);
		back.setRotationPoint(0.0F, -16.0F, 7.0F);
		mouse.addChild(back);
		

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-5.0F, 0.0F, 0.0F);
		back.addChild(bone5);
		bone5.setTextureOffset(29, 85).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(6.0F, 0.0F, 0.0F);
		back.addChild(bone6);
		bone6.setTextureOffset(18, 84).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		m5 = new ModelRenderer(this);
		m5.setRotationPoint(-2.0F, 0.0F, 0.0F);
		back.addChild(m5);
		setRotationAngle(m5, 0.0873F, 0.0F, 0.0F);
		m5.setTextureOffset(3, 83).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, 0.0F, false);

		m6 = new ModelRenderer(this);
		m6.setRotationPoint(2.0F, 0.0F, 0.0F);
		back.addChild(m6);
		setRotationAngle(m6, -0.0873F, 0.0F, 0.0F);
		m6.setTextureOffset(115, 77).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, 0.0F, false);

		left = new ModelRenderer(this);
		left.setRotationPoint(-7.0F, -16.0F, 0.0F);
		mouse.addChild(left);
		

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 5.0F);
		left.addChild(bone9);
		bone9.setTextureOffset(104, 77).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		m4 = new ModelRenderer(this);
		m4.setRotationPoint(0.0F, 0.0F, 2.0F);
		left.addChild(m4);
		setRotationAngle(m4, 0.0F, 0.0F, -0.0873F);
		m4.setTextureOffset(89, 76).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		m3 = new ModelRenderer(this);
		m3.setRotationPoint(0.0F, 0.0F, -2.0F);
		left.addChild(m3);
		setRotationAngle(m3, 0.0F, 0.0F, 0.0873F);
		m3.setTextureOffset(74, 76).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, -5.0F);
		left.addChild(bone12);
		bone12.setTextureOffset(118, 65).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		right = new ModelRenderer(this);
		right.setRotationPoint(7.0F, -16.0F, 0.0F);
		mouse.addChild(right);
		

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 0.0F, 5.0F);
		right.addChild(bone13);
		bone13.setTextureOffset(107, 64).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		m7 = new ModelRenderer(this);
		m7.setRotationPoint(0.0F, 0.0F, 2.0F);
		right.addChild(m7);
		setRotationAngle(m7, 0.0F, 0.0F, -0.0873F);
		m7.setTextureOffset(92, 62).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		m8 = new ModelRenderer(this);
		m8.setRotationPoint(0.0F, 0.0F, -2.0F);
		right.addChild(m8);
		setRotationAngle(m8, 0.0F, 0.0F, 0.0873F);
		m8.setTextureOffset(77, 62).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, -5.0F);
		right.addChild(bone16);
		bone16.setTextureOffset(64, 68).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		total.addChild(head);
		head.setTextureOffset(73, 49).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(84, 43).addBox(-6.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(63, 50).addBox(-6.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(56, 48).addBox(5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(48, 48).addBox(5.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		right_wine = new ModelRenderer(this);
		right_wine.setRotationPoint(-8.0F, 0.0F, 0.0F);
		head.addChild(right_wine);
		right_wine.setTextureOffset(30, 74).addBox(-2.0F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(1.0F, 0.0F, 5.0F);
		right_wine.addChild(bone17);
		setRotationAngle(bone17, -0.5236F, 1.0472F, 0.0F);
		bone17.setTextureOffset(46, 73).addBox(0.0F, -1.0F, -4.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(1.0F, 0.0F, -5.0F);
		right_wine.addChild(bone18);
		setRotationAngle(bone18, 0.5236F, -1.0472F, 0.0F);
		bone18.setTextureOffset(99, 38).addBox(0.0F, -1.0F, -2.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(-1.0F, -1.0F, -2.0F);
		right_wine.addChild(bone19);
		setRotationAngle(bone19, -0.5236F, -0.2618F, 0.0F);
		bone19.setTextureOffset(15, 76).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(-1.0F, -1.0F, 2.0F);
		right_wine.addChild(bone20);
		setRotationAngle(bone20, 0.5236F, 0.2618F, 0.0F);
		bone20.setTextureOffset(3, 74).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		left_wine = new ModelRenderer(this);
		left_wine.setRotationPoint(8.0F, 0.0F, 0.0F);
		head.addChild(left_wine);
		left_wine.setTextureOffset(26, 64).addBox(1.0F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(-1.0F, 0.0F, -5.0F);
		left_wine.addChild(bone25);
		setRotationAngle(bone25, 0.5236F, 1.0472F, 0.0F);
		bone25.setTextureOffset(61, 57).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(-1.0F, 0.0F, 5.0F);
		left_wine.addChild(bone26);
		setRotationAngle(bone26, -0.5236F, -1.0472F, 0.0F);
		bone26.setTextureOffset(43, 64).addBox(-1.0F, -1.0F, -4.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone31 = new ModelRenderer(this);
		bone31.setRotationPoint(1.0F, -1.0F, -2.0F);
		left_wine.addChild(bone31);
		setRotationAngle(bone31, -0.5236F, 0.2618F, 0.0F);
		bone31.setTextureOffset(12, 68).addBox(0.0F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		bone32 = new ModelRenderer(this);
		bone32.setRotationPoint(1.0F, -1.0F, 2.0F);
		left_wine.addChild(bone32);
		setRotationAngle(bone32, 0.5236F, -0.2618F, 0.0F);
		bone32.setTextureOffset(3, 62).addBox(0.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		back_wine = new ModelRenderer(this);
		back_wine.setRotationPoint(0.0F, 0.0F, 6.0F);
		head.addChild(back_wine);
		setRotationAngle(back_wine, -0.2618F, 0.0F, 0.0F);
		

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(-5.0F, 1.0F, -1.0F);
		back_wine.addChild(bone21);
		setRotationAngle(bone21, -0.2618F, 0.0F, 1.0472F);
		bone21.setTextureOffset(123, 53).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(5.0F, 1.0F, -1.0F);
		back_wine.addChild(bone22);
		setRotationAngle(bone22, -0.2618F, 0.0F, -1.0472F);
		bone22.setTextureOffset(116, 54).addBox(0.0F, -7.0F, 0.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		bone29 = new ModelRenderer(this);
		bone29.setRotationPoint(0.0F, -2.0F, 1.0F);
		back_wine.addChild(bone29);
		setRotationAngle(bone29, 0.5236F, 0.0F, 0.0F);
		

		bone30 = new ModelRenderer(this);
		bone30.setRotationPoint(0.0F, -1.0F, 1.0F);
		bone29.addChild(bone30);
		setRotationAngle(bone30, 0.0F, 0.7854F, 0.0F);
		bone30.setTextureOffset(105, 58).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		front_wine = new ModelRenderer(this);
		front_wine.setRotationPoint(0.0F, 0.0F, -6.0F);
		head.addChild(front_wine);
		setRotationAngle(front_wine, 0.2618F, 0.0F, 0.0F);
		

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(5.0F, 1.0F, 1.0F);
		front_wine.addChild(bone24);
		setRotationAngle(bone24, 0.2618F, 0.0F, -1.0472F);
		bone24.setTextureOffset(123, 43).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(-5.0F, 1.0F, 1.0F);
		front_wine.addChild(bone23);
		setRotationAngle(bone23, 0.2618F, 0.0F, 1.0472F);
		bone23.setTextureOffset(116, 43).addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		bone28 = new ModelRenderer(this);
		bone28.setRotationPoint(0.0F, -2.0F, -1.0F);
		front_wine.addChild(bone28);
		setRotationAngle(bone28, -0.5236F, 0.0F, 0.0F);
		

		bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(0.0F, -1.0F, -1.0F);
		bone28.addChild(bone27);
		setRotationAngle(bone27, 0.0F, 0.7854F, 0.0F);
		bone27.setTextureOffset(104, 50).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone33 = new ModelRenderer(this);
		bone33.setRotationPoint(-5.0F, 0.0F, 4.0F);
		head.addChild(bone33);
		setRotationAngle(bone33, -0.2618F, -0.7854F, 0.0F);
		bone33.setTextureOffset(83, 50).addBox(0.0F, -1.0F, -7.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);

		bone34 = new ModelRenderer(this);
		bone34.setRotationPoint(5.0F, 0.0F, 4.0F);
		head.addChild(bone34);
		setRotationAngle(bone34, -0.2618F, 0.7854F, 0.0F);
		bone34.setTextureOffset(40, 52).addBox(-1.0F, -1.0F, -7.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);

		bone35 = new ModelRenderer(this);
		bone35.setRotationPoint(-5.0F, 0.0F, -4.0F);
		head.addChild(bone35);
		setRotationAngle(bone35, 0.2618F, 0.7854F, 0.0F);
		bone35.setTextureOffset(19, 51).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);

		bone36 = new ModelRenderer(this);
		bone36.setRotationPoint(5.0F, 0.0F, -4.0F);
		head.addChild(bone36);
		setRotationAngle(bone36, 0.2618F, -0.7854F, 0.0F);
		bone36.setTextureOffset(3, 45).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(GraveBusterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		final float angle = 0.15f;
		if(entity.isEating()) {
			int x = entity.getAttackTime();
			final int times = 7;
			float T = entity.getAttackCD() * 1.0f / times;
			double w = 2 * Math.PI / T;
			this.m1.rotateAngleX = (float) - Math.cos(w * x) * angle;
			this.m2.rotateAngleX = (float) Math.cos(w * x) * angle;
			this.m3.rotateAngleZ = (float) Math.cos(w * x) * angle;
			this.m4.rotateAngleZ = (float) - Math.cos(w * x) * angle;
			this.m5.rotateAngleX = (float) Math.cos(w * x) * angle;
			this.m6.rotateAngleX = (float) - Math.cos(w * x) * angle;
			this.m7.rotateAngleZ = (float) - Math.cos(w * x) * angle;
			this.m8.rotateAngleZ = (float) Math.cos(w * x) * angle;
		}else {
			this.m1.rotateAngleX = -angle;
			this.m2.rotateAngleX = angle;
			this.m3.rotateAngleZ = angle;
			this.m4.rotateAngleZ = -angle;
			this.m5.rotateAngleX = angle;
			this.m6.rotateAngleX = -angle;
			this.m7.rotateAngleZ = -angle;
			this.m8.rotateAngleZ = angle;
		}
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