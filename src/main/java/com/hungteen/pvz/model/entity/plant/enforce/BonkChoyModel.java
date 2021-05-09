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
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		hair = new ModelRenderer(this);
		hair.setPos(0.0F, -18.25F, -1.0F);
		total.addChild(hair);
		

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, -5.0F, 0.0F);
		hair.addChild(bone2);
		bone2.texOffs(82, 110).addBox(-5.0F, -2.5F, -3.5F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone2.texOffs(72, 103).addBox(-6.0F, -7.5F, -3.5F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 0.1645F, -3.7418F);
		bone2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.4363F, 0.0F, 0.0F);
		cube_r1.texOffs(32, 67).addBox(-4.0F, -0.5F, 0.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, 10.0F, -2.0F);
		hair.addChild(bone7);
		setRotationAngle(bone7, -0.5236F, 0.0F, 0.0F);
		bone7.texOffs(48, 97).addBox(-4.0F, -15.5F, -3.5F, 8.0F, 3.0F, 2.0F, 0.0F, false);
		bone7.texOffs(104, 108).addBox(-5.0F, -17.5F, -3.5F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone7.texOffs(100, 101).addBox(-6.0F, -22.5F, -3.5F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(10.0F, 7.0F, 0.0F);
		hair.addChild(bone5);
		setRotationAngle(bone5, -0.2182F, 0.0F, -0.8727F);
		bone5.texOffs(72, 96).addBox(-4.0F, -15.5F, -3.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		bone5.texOffs(104, 97).addBox(-5.0F, -17.5F, -3.5F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone5.texOffs(36, 88).addBox(-6.0F, -22.5F, -3.5F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(-9.0F, 7.0F, 1.0F);
		hair.addChild(bone6);
		setRotationAngle(bone6, -0.2182F, 0.0F, 0.8727F);
		bone6.texOffs(62, 89).addBox(-4.0F, -15.5F, -3.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);
		bone6.texOffs(104, 93).addBox(-5.0F, -17.5F, -3.5F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone6.texOffs(36, 81).addBox(-6.0F, -22.5F, -3.5F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(5.0F, 9.0F, 2.0F);
		hair.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.4363F);
		bone3.texOffs(82, 90).addBox(-4.0F, -15.5F, -3.5F, 8.0F, 4.0F, 2.0F, 0.0F, false);
		bone3.texOffs(104, 89).addBox(-5.0F, -17.5F, -3.5F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone3.texOffs(100, 82).addBox(-6.0F, -22.5F, -3.5F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(-4.0F, 9.0F, 2.0F);
		hair.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.4363F);
		bone4.texOffs(80, 83).addBox(-4.0F, -15.5F, -3.501F, 8.0F, 4.0F, 2.0F, 0.0F, false);
		bone4.texOffs(104, 78).addBox(-5.0F, -17.5F, -3.501F, 10.0F, 2.0F, 2.0F, 0.0F, false);
		bone4.texOffs(0, 76).addBox(-6.0F, -22.5F, -3.501F, 12.0F, 5.0F, 2.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setPos(-5.0F, -0.75F, 0.0F);
		total.addChild(right_arm);
		right_arm.texOffs(89, 80).addBox(-4.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

		right1 = new ModelRenderer(this);
		right1.setPos(-4.0F, 0.0F, 0.0F);
		right_arm.addChild(right1);
		setRotationAngle(right1, 0.0F, 0.0F, 0.2618F);
		right1.texOffs(63, 78).addBox(-2.8706F, -0.517F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		right2 = new ModelRenderer(this);
		right2.setPos(-3.0F, 0.0F, -0.5F);
		right1.addChild(right2);
		setRotationAngle(right2, 0.0F, 0.0F, 0.2618F);
		right2.texOffs(114, 113).addBox(-3.75F, -0.567F, -0.5F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		right3 = new ModelRenderer(this);
		right3.setPos(-4.0F, 0.0F, 0.0F);
		right2.addChild(right3);
		setRotationAngle(right3, 0.0F, 0.0F, 0.2618F);
		right3.texOffs(64, 85).addBox(-4.6464F, -0.6464F, -0.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);

		right4 = new ModelRenderer(this);
		right4.setPos(-4.0F, 0.0F, 0.5F);
		right3.addChild(right4);
		setRotationAngle(right4, 0.0F, 0.0F, 0.3927F);
		right4.texOffs(97, 72).addBox(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 3.0F, 0.05F, false);

		right5 = new ModelRenderer(this);
		right5.setPos(-4.0F, 0.0F, 0.0F);
		right4.addChild(right5);
		setRotationAngle(right5, 0.0F, 0.0F, 0.5236F);
		right5.texOffs(14, 67).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setPos(5.0F, -0.75F, 0.0F);
		total.addChild(left_arm);
		setRotationAngle(left_arm, 0.0F, 3.1416F, 0.0F);
		left_arm.texOffs(73, 78).addBox(-4.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

		left1 = new ModelRenderer(this);
		left1.setPos(-4.0F, 0.0F, 0.0F);
		left_arm.addChild(left1);
		setRotationAngle(left1, 0.0F, 0.0F, 0.2618F);
		left1.texOffs(57, 123).addBox(-2.8706F, -0.517F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		left2 = new ModelRenderer(this);
		left2.setPos(-3.0F, 0.0F, 0.0F);
		left1.addChild(left2);
		setRotationAngle(left2, 0.0F, 0.0F, 0.2618F);
		left2.texOffs(38, 74).addBox(-3.75F, -0.567F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		left3 = new ModelRenderer(this);
		left3.setPos(-4.0F, 0.0F, 0.0F);
		left2.addChild(left3);
		setRotationAngle(left3, 0.0F, 0.0F, 0.2618F);
		left3.texOffs(52, 74).addBox(-4.6464F, -0.6464F, -2.0F, 5.0F, 1.0F, 3.0F, 0.0F, false);

		left4 = new ModelRenderer(this);
		left4.setPos(-3.8232F, -0.1768F, -0.5F);
		left3.addChild(left4);
		setRotationAngle(left4, 0.0F, 0.0F, 0.3927F);
		left4.texOffs(83, 75).addBox(-5.0F, -1.0F, -1.5F, 5.0F, 2.0F, 3.0F, 0.05F, false);

		left5 = new ModelRenderer(this);
		left5.setPos(-4.0F, 0.0F, 0.0F);
		left4.addChild(left5);
		setRotationAngle(left5, 0.0F, 0.0F, 0.5236F);
		left5.texOffs(68, 69).addBox(-4.0F, -2.0F, -2.5F, 4.0F, 4.0F, 5.0F, 0.0F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, -2.25F, -1.0F);
		total.addChild(face);
		face.texOffs(56, 116).addBox(0.5F, -11.0F, -6.5F, 5.0F, 6.0F, 1.0F, -0.45F, false);
		face.texOffs(0, 88).addBox(-5.5F, -11.0F, -6.5F, 5.0F, 6.0F, 1.0F, -0.45F, false);

		body = new ModelRenderer(this);
		body.setPos(-5.0F, -0.75F, 0.0F);
		total.addChild(body);
		body.texOffs(96, 117).addBox(1.0F, -19.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		body.texOffs(0, 83).addBox(-1.0F, -4.5F, -6.0F, 12.0F, 5.0F, 12.0F, 0.4F, false);
		body.texOffs(0, 100).addBox(-2.0F, -14.5F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		body.texOffs(42, 102).addBox(0.0F, -18.0F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
		body.texOffs(56, 114).addBox(-1.0F, -16.25F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(BonkChoyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAttackTime() == 0) {
			this.left_arm.xRot = 0;
			this.left_arm.yRot = 3.1416F;
			this.left_arm.zRot = 0;
			this.right_arm.xRot = 0;
			this.right_arm.yRot = 0;
			this.right_arm.zRot = 0;
		} else if(entity.getAttackTime() < 0) {
			int now = entity.getAttackTime();
			int tot = entity.getAttackCD();
			this.right_arm.xRot = AnimationUtil.getUpDown(now, tot, 60);
			this.right_arm.yRot = AnimationUtil.getUpDown(now, tot, - 60);
		} else {
			int now = - entity.getAttackTime();
			int tot = entity.getAttackCD();
			this.left_arm.xRot = AnimationUtil.getUpDown(now, tot, - 60);
			this.left_arm.yRot = - (3.1416F - AnimationUtil.getUpDown(now, tot, 60));
		}
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