package com.hungteen.pvz.model.entity.plant.assist;

import com.hungteen.pvz.entity.plant.assist.BloverEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BloverModel extends EntityModel<BloverEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer fan;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r20;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;
	private final ModelRenderer cube_r27;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer cube_r30;
	private final ModelRenderer cube_r31;
	private final ModelRenderer cube_r32;
	private final ModelRenderer cube_r33;
	private final ModelRenderer cube_r34;
	private final ModelRenderer cube_r35;
	private final ModelRenderer body;

	public BloverModel() {
		textureWidth = 64;
		textureHeight = 64;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 9.0F, -0.5F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(head);
		head.setTextureOffset(16, 24).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 6.0F, 2.0F, -0.28F, false);
		head.setTextureOffset(26, 20).addBox(-2.25F, -2.0F, -2.14F, 2.0F, 3.0F, 1.0F, -0.4F, false);
		head.setTextureOffset(20, 20).addBox(0.25F, -2.0F, -2.14F, 2.0F, 3.0F, 1.0F, -0.4F, false);
		head.setTextureOffset(26, 17).addBox(-1.0F, 0.5F, -2.03F, 2.0F, 2.0F, 1.0F, -0.3F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -1.25F, -3.0F);
		head.addChild(bone);
		bone.setTextureOffset(8, 29).addBox(-1.0F, 4.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-0.634F, 0.1699F, 1.0F);
		bone.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.5236F);
		cube_r1.setTextureOffset(24, 8).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-1.0981F, 0.634F, 1.0F);
		bone.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -1.0472F);
		cube_r2.setTextureOffset(16, 14).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-1.2679F, 1.2679F, 1.0F);
		bone.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -1.5708F);
		cube_r3.setTextureOffset(24, 11).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-1.0981F, 1.9019F, 1.0F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -2.0944F);
		cube_r4.setTextureOffset(12, 20).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-0.634F, 2.366F, 1.0F);
		bone.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -2.618F);
		cube_r5.setTextureOffset(18, 17).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 2.5359F, 1.0F);
		bone.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -3.1416F);
		cube_r6.setTextureOffset(24, 14).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.634F, 2.366F, 1.0F);
		bone.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 2.618F);
		cube_r7.setTextureOffset(0, 23).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(1.0981F, 1.9019F, 1.0F);
		bone.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 2.0944F);
		cube_r8.setTextureOffset(10, 23).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(1.2679F, 1.2679F, 1.0F);
		bone.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 1.5708F);
		cube_r9.setTextureOffset(8, 26).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(1.0981F, 0.634F, 1.0F);
		bone.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 1.0472F);
		cube_r10.setTextureOffset(0, 26).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.634F, 0.1699F, 1.0F);
		bone.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, 0.5236F);
		cube_r11.setTextureOffset(0, 29).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		fan = new ModelRenderer(this);
		fan.setRotationPoint(0.0F, 0.0F, -2.5F);
		head.addChild(fan);
		

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		fan.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, -2.0944F);
		bone4.setTextureOffset(22, 0).addBox(-2.0F, -7.5F, 1.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bone4.setTextureOffset(12, 17).addBox(-1.5F, -4.6F, 1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone4.setTextureOffset(18, 11).addBox(-1.5F, -8.5F, 1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(2.4142F, -4.7198F, 0.0F);
		bone4.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, -1.0472F);
		cube_r12.setTextureOffset(0, 17).addBox(-2.0F, -1.0F, 1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(-2.4142F, -4.7198F, 0.0F);
		bone4.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 0.0F, 1.0472F);
		cube_r13.setTextureOffset(2, 19).addBox(-1.0F, -1.0F, 1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(-1.5F, -7.0858F, 0.0F);
		bone4.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, -0.7854F);
		cube_r14.setTextureOffset(1, 21).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(1.5F, -7.0858F, 0.0F);
		bone4.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 0.0F, 0.7854F);
		cube_r15.setTextureOffset(6, 22).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(-1.9142F, -5.5858F, 0.0F);
		bone4.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, -1.5708F);
		cube_r16.setTextureOffset(10, 19).addBox(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(1.9142F, -6.0858F, 0.0F);
		bone4.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.0F, 1.5708F);
		cube_r17.setTextureOffset(22, 8).addBox(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setRotationPoint(-1.9142F, -6.0858F, 0.0F);
		bone4.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, 0.0F, -1.5708F);
		cube_r18.setTextureOffset(22, 14).addBox(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setRotationPoint(1.9142F, -5.5858F, 0.0F);
		bone4.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0F, 0.0F, 1.5708F);
		cube_r19.setTextureOffset(6, 29).addBox(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		fan.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, 2.0944F);
		bone3.setTextureOffset(12, 0).addBox(-2.0F, -7.5F, 1.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(24, 4).addBox(-1.5F, -4.6F, 1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(24, 6).addBox(-1.5F, -8.5F, 1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setRotationPoint(2.4142F, -4.7198F, 0.0F);
		bone3.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 0.0F, -1.0472F);
		cube_r20.setTextureOffset(10, 10).addBox(-2.0F, -1.0F, 1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setRotationPoint(-2.4142F, -4.7198F, 0.0F);
		bone3.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0F, 0.0F, 1.0472F);
		cube_r21.setTextureOffset(11, 8).addBox(-1.0F, -1.0F, 1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setRotationPoint(-1.5F, -7.0858F, 0.0F);
		bone3.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, 0.0F, -0.7854F);
		cube_r22.setTextureOffset(10, 4).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setRotationPoint(1.5F, -7.0858F, 0.0F);
		bone3.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0F, 0.0F, 0.7854F);
		cube_r23.setTextureOffset(14, 6).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setRotationPoint(-1.9142F, -5.5858F, 0.0F);
		bone3.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.0F, 0.0F, -1.5708F);
		cube_r24.setTextureOffset(16, 4).addBox(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setRotationPoint(1.9142F, -6.0858F, 0.0F);
		bone3.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0F, 0.0F, 1.5708F);
		cube_r25.setTextureOffset(18, 9).addBox(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setRotationPoint(-1.9142F, -6.0858F, 0.0F);
		bone3.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.0F, 0.0F, -1.5708F);
		cube_r26.setTextureOffset(20, 6).addBox(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r27 = new ModelRenderer(this);
		cube_r27.setRotationPoint(1.9142F, -5.5858F, 0.0F);
		bone3.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.0F, 0.0F, 1.5708F);
		cube_r27.setTextureOffset(20, 4).addBox(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		fan.addChild(bone2);
		bone2.setTextureOffset(8, 12).addBox(-2.0F, -7.5F, 1.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(6, 6).addBox(-1.5F, -4.6F, 1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(3, 8).addBox(-1.5F, -8.5F, 1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setRotationPoint(2.4142F, -4.7198F, 1.0F);
		bone2.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0F, 0.0F, -1.0472F);
		cube_r28.setTextureOffset(0, 32).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setRotationPoint(-2.4142F, -4.7198F, 1.0F);
		bone2.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.0F, 0.0F, 1.0472F);
		cube_r29.setTextureOffset(4, 2).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r30 = new ModelRenderer(this);
		cube_r30.setRotationPoint(-1.5F, -7.0858F, 1.0F);
		bone2.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.0F, 0.0F, -0.7854F);
		cube_r30.setTextureOffset(6, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r31 = new ModelRenderer(this);
		cube_r31.setRotationPoint(1.5F, -7.0858F, 1.0F);
		bone2.addChild(cube_r31);
		setRotationAngle(cube_r31, 0.0F, 0.0F, 0.7854F);
		cube_r31.setTextureOffset(2, 14).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r32 = new ModelRenderer(this);
		cube_r32.setRotationPoint(-1.9142F, -5.5858F, 1.0F);
		bone2.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.0F, 0.0F, -1.5708F);
		cube_r32.setTextureOffset(4, 12).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r33 = new ModelRenderer(this);
		cube_r33.setRotationPoint(1.9142F, -6.0858F, 1.0F);
		bone2.addChild(cube_r33);
		setRotationAngle(cube_r33, 0.0F, 0.0F, 1.5708F);
		cube_r33.setTextureOffset(6, 10).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r34 = new ModelRenderer(this);
		cube_r34.setRotationPoint(-1.9142F, -6.0858F, 1.0F);
		bone2.addChild(cube_r34);
		setRotationAngle(cube_r34, 0.0F, 0.0F, -1.5708F);
		cube_r34.setTextureOffset(6, 26).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r35 = new ModelRenderer(this);
		cube_r35.setRotationPoint(1.9142F, -5.5858F, 1.0F);
		bone2.addChild(cube_r35);
		setRotationAngle(cube_r35, 0.0F, 0.0F, 1.5708F);
		cube_r35.setTextureOffset(9, 16).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 15.0F, 0.5F);
		total.addChild(body);
		body.setTextureOffset(32, 0).addBox(-0.5F, -15.0F, -0.5F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(36, 0).addBox(-1.0F, -16.0F, -2.0F, 2.0F, 2.0F, 3.0F, -0.35F, false);
		body.setTextureOffset(36, 5).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(BloverEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.fan.rotateAngleZ = ageInTicks / 1.5F;
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