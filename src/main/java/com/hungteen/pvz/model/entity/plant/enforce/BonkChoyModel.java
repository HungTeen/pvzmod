package com.hungteen.pvz.model.entity.plant.enforce;

import com.hungteen.pvz.entity.plant.enforce.BonkChoyEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BonkChoyModel extends EntityModel<BonkChoyEntity> {
	private final ModelRenderer total;
	private final ModelRenderer hair;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r1;
	private final ModelRenderer bone7;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer right_arm;
	private final ModelRenderer right1;
	private final ModelRenderer right2;
	private final ModelRenderer right3;
	private final ModelRenderer right4;
	private final ModelRenderer right5;
	private final ModelRenderer left_arm;
	private final ModelRenderer left1;
	private final ModelRenderer left2;
	private final ModelRenderer left3;
	private final ModelRenderer left4;
	private final ModelRenderer left5;
	private final ModelRenderer face;
	private final ModelRenderer body;

	public BonkChoyModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		hair = new ModelRenderer(this);
		hair.setRotationPoint(0.0F, -18.25F, -1.0F);
		total.addChild(hair);
		

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -5.0F, 0.0F);
		hair.addChild(bone2);
		bone2.setTextureOffset(82, 110).addBox(-5.0F, -2.5F, -3.5F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone2.setTextureOffset(72, 103).addBox(-6.0F, -7.5F, -3.5F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.1645F, -3.7418F);
		bone2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.4363F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(32, 67).addBox(-4.0F, -0.5F, 0.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 10.0F, -2.0F);
		hair.addChild(bone7);
		setRotationAngle(bone7, -0.5236F, 0.0F, 0.0F);
		bone7.setTextureOffset(48, 97).addBox(-4.0F, -15.5F, -3.5F, 8.0F, 3.0F, 2.0F, 0.0F, false);
		bone7.setTextureOffset(104, 108).addBox(-5.0F, -17.5F, -3.5F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone7.setTextureOffset(100, 101).addBox(-6.0F, -22.5F, -3.5F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(10.0F, 7.0F, 0.0F);
		hair.addChild(bone5);
		setRotationAngle(bone5, -0.2182F, 0.0F, -0.8727F);
		bone5.setTextureOffset(72, 96).addBox(-4.0F, -15.5F, -3.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		bone5.setTextureOffset(104, 97).addBox(-5.0F, -17.5F, -3.5F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone5.setTextureOffset(36, 88).addBox(-6.0F, -22.5F, -3.5F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-9.0F, 7.0F, 1.0F);
		hair.addChild(bone6);
		setRotationAngle(bone6, -0.2182F, 0.0F, 0.8727F);
		bone6.setTextureOffset(62, 89).addBox(-4.0F, -15.5F, -3.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		bone6.setTextureOffset(104, 93).addBox(-5.0F, -17.5F, -3.5F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone6.setTextureOffset(36, 81).addBox(-6.0F, -22.5F, -3.5F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(5.0F, 9.0F, 2.0F);
		hair.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.4363F);
		bone3.setTextureOffset(82, 90).addBox(-4.0F, -15.5F, -3.5F, 8.0F, 4.0F, 2.0F, 0.0F, false);
		bone3.setTextureOffset(104, 89).addBox(-5.0F, -17.5F, -3.5F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone3.setTextureOffset(100, 82).addBox(-6.0F, -22.5F, -3.5F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-4.0F, 9.0F, 2.0F);
		hair.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.4363F);
		bone4.setTextureOffset(80, 83).addBox(-4.0F, -15.5F, -3.501F, 8.0F, 4.0F, 2.0F, 0.0F, false);
		bone4.setTextureOffset(104, 78).addBox(-5.0F, -17.5F, -3.501F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone4.setTextureOffset(0, 76).addBox(-6.0F, -22.5F, -3.501F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-5.0F, -0.75F, 0.0F);
		total.addChild(right_arm);
		right_arm.setTextureOffset(89, 80).addBox(-4.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

		right1 = new ModelRenderer(this);
		right1.setRotationPoint(-4.0F, 0.0F, 0.0F);
		right_arm.addChild(right1);
		setRotationAngle(right1, 0.0F, 0.0F, 0.2618F);
		right1.setTextureOffset(63, 78).addBox(-2.8706F, -0.517F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		right2 = new ModelRenderer(this);
		right2.setRotationPoint(-3.0F, 0.0F, -0.5F);
		right1.addChild(right2);
		setRotationAngle(right2, 0.0F, 0.0F, 0.2618F);
		right2.setTextureOffset(114, 113).addBox(-3.75F, -0.567F, -0.5F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		right3 = new ModelRenderer(this);
		right3.setRotationPoint(-4.0F, 0.0F, 0.0F);
		right2.addChild(right3);
		setRotationAngle(right3, 0.0F, 0.0F, 0.2618F);
		right3.setTextureOffset(64, 85).addBox(-4.6464F, -0.6464F, -0.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);

		right4 = new ModelRenderer(this);
		right4.setRotationPoint(-4.0F, 0.0F, 0.5F);
		right3.addChild(right4);
		setRotationAngle(right4, 0.0F, 0.0F, 0.3927F);
		right4.setTextureOffset(97, 72).addBox(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 3.0F, 0.05F, false);

		right5 = new ModelRenderer(this);
		right5.setRotationPoint(-4.0F, 0.0F, 0.0F);
		right4.addChild(right5);
		setRotationAngle(right5, 0.0F, 0.0F, 0.5236F);
		right5.setTextureOffset(14, 67).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(5.0F, -0.75F, 0.0F);
		total.addChild(left_arm);
		setRotationAngle(left_arm, 0.0F, 3.1416F, 0.0F);
		left_arm.setTextureOffset(73, 78).addBox(-4.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

		left1 = new ModelRenderer(this);
		left1.setRotationPoint(-4.0F, 0.0F, 0.0F);
		left_arm.addChild(left1);
		setRotationAngle(left1, 0.0F, 0.0F, 0.2618F);
		left1.setTextureOffset(57, 123).addBox(-2.8706F, -0.517F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		left2 = new ModelRenderer(this);
		left2.setRotationPoint(-3.0F, 0.0F, 0.0F);
		left1.addChild(left2);
		setRotationAngle(left2, 0.0F, 0.0F, 0.2618F);
		left2.setTextureOffset(38, 74).addBox(-3.75F, -0.567F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		left3 = new ModelRenderer(this);
		left3.setRotationPoint(-4.0F, 0.0F, 0.0F);
		left2.addChild(left3);
		setRotationAngle(left3, 0.0F, 0.0F, 0.2618F);
		left3.setTextureOffset(52, 74).addBox(-4.6464F, -0.6464F, -2.0F, 5.0F, 1.0F, 3.0F, 0.0F, false);

		left4 = new ModelRenderer(this);
		left4.setRotationPoint(-3.8232F, -0.1768F, -0.5F);
		left3.addChild(left4);
		setRotationAngle(left4, 0.0F, 0.0F, 0.3927F);
		left4.setTextureOffset(83, 75).addBox(-5.0F, -1.0F, -1.5F, 5.0F, 2.0F, 3.0F, 0.05F, false);

		left5 = new ModelRenderer(this);
		left5.setRotationPoint(-4.0F, 0.0F, 0.0F);
		left4.addChild(left5);
		setRotationAngle(left5, 0.0F, 0.0F, 0.5236F);
		left5.setTextureOffset(68, 69).addBox(-4.0F, -2.0F, -2.5F, 4.0F, 4.0F, 5.0F, 0.0F, false);

		face = new ModelRenderer(this);
		face.setRotationPoint(0.0F, -2.25F, -1.0F);
		total.addChild(face);
		face.setTextureOffset(56, 116).addBox(0.5F, -11.0F, -6.5F, 5.0F, 6.0F, 1.0F, -0.45F, false);
		face.setTextureOffset(0, 88).addBox(-5.5F, -11.0F, -6.5F, 5.0F, 6.0F, 1.0F, -0.45F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(-5.0F, -0.75F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(96, 117).addBox(1.0F, -19.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(0, 83).addBox(-1.0F, -4.5F, -6.0F, 12.0F, 5.0F, 12.0F, 0.4F, false);
		body.setTextureOffset(0, 100).addBox(-2.0F, -14.5F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		body.setTextureOffset(42, 102).addBox(0.0F, -18.0F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(56, 114).addBox(-1.0F, -16.25F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(BonkChoyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAttackTime() == 0) {
			this.left_arm.rotateAngleX = 0;
			this.left_arm.rotateAngleY = 3.1416F;
			this.left_arm.rotateAngleZ = 0;
			this.right_arm.rotateAngleX = 0;
			this.right_arm.rotateAngleY = 0;
			this.right_arm.rotateAngleZ = 0;
		} else if(entity.getAttackTime() < 0) {
			int now = entity.getAttackTime();
			int tot = entity.getAttackCD();
			this.right_arm.rotateAngleX = AnimationUtil.getUpDown(now, tot, 60);
			this.right_arm.rotateAngleY = AnimationUtil.getUpDown(now, tot, - 60);
		} else {
			int now = - entity.getAttackTime();
			int tot = entity.getAttackCD();
			this.left_arm.rotateAngleX = AnimationUtil.getUpDown(now, tot, - 60);
			this.left_arm.rotateAngleY = - (3.1416F - AnimationUtil.getUpDown(now, tot, 60));
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