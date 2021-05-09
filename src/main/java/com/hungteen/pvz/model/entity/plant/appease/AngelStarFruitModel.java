package com.hungteen.pvz.model.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.AngelStarFruitEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class AngelStarFruitModel extends EntityModel<AngelStarFruitEntity> {
	
	private final ModelRenderer total;
	private final ModelRenderer base;
	private final ModelRenderer b1;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer b2;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer b3;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer body;
	private final ModelRenderer face;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer eye;
	private final ModelRenderer body2;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer body3;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;
	private final ModelRenderer body4;
	private final ModelRenderer cube_r27;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer cube_r30;
	private final ModelRenderer body5;
	private final ModelRenderer cube_r31;
	private final ModelRenderer cube_r32;
	private final ModelRenderer cube_r33;
	private final ModelRenderer cube_r34;

	public AngelStarFruitModel() {
		texWidth = 32;
		texHeight = 32;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		base = new ModelRenderer(this);
		base.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(base);
		

		b1 = new ModelRenderer(this);
		b1.setPos(0.0F, 1.1F, 0.0F);
		base.addChild(b1);
		setRotationAngle(b1, 0.0F, 0.1571F, 0.0F);
		b1.texOffs(10, 13).addBox(-8.0F, -1.95F, 5.0F, 3.0F, 1.0F, 3.0F, -0.25F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-8.8205F, -1.45F, -4.4943F);
		b1.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -1.2566F, 0.0F);
		cube_r1.texOffs(10, 13).addBox(-1.0F, -0.5F, -2.0F, 3.0F, 1.0F, 3.0F, -0.25F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(1.5486F, -1.45F, -9.7776F);
		b1.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -2.5133F, 0.0F);
		cube_r2.texOffs(10, 13).addBox(-1.0F, -0.5F, -2.0F, 3.0F, 1.0F, 3.0F, -0.25F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(9.7776F, -1.45F, -1.5486F);
		b1.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 2.5133F, 0.0F);
		cube_r3.texOffs(10, 13).addBox(-1.0F, -0.5F, -2.0F, 3.0F, 1.0F, 3.0F, -0.25F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(4.4943F, -1.45F, 8.8205F);
		b1.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 1.2566F, 0.0F);
		cube_r4.texOffs(10, 13).addBox(-1.0F, -0.5F, -2.0F, 3.0F, 1.0F, 3.0F, -0.25F, false);

		b2 = new ModelRenderer(this);
		b2.setPos(0.0F, 1.2F, 0.0F);
		base.addChild(b2);
		setRotationAngle(b2, 0.0F, 0.3665F, 0.0F);
		b2.texOffs(0, 0).addBox(-7.0F, -1.955F, -1.0F, 8.0F, 1.0F, 8.0F, -0.25F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(0.0F, 0.0F, 0.0F);
		b2.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 2.5133F, 0.0F);
		cube_r5.texOffs(0, 0).addBox(-7.0F, -1.955F, -1.0F, 8.0F, 1.0F, 8.0F, -0.25F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(0.0F, 0.0F, 0.0F);
		b2.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 1.2566F, 0.0F);
		cube_r6.texOffs(0, 0).addBox(-7.0F, -1.955F, -1.0F, 8.0F, 1.0F, 8.0F, -0.25F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(0.0F, 0.0F, 0.0F);
		b2.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, -2.5133F, 0.0F);
		cube_r7.texOffs(0, 0).addBox(-7.0F, -1.955F, -1.0F, 8.0F, 1.0F, 8.0F, -0.25F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(0.0F, 0.0F, 0.0F);
		b2.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, -1.2566F, 0.0F);
		cube_r8.texOffs(0, 0).addBox(-7.0F, -1.955F, -1.0F, 8.0F, 1.0F, 8.0F, -0.25F, false);

		b3 = new ModelRenderer(this);
		b3.setPos(0.0F, 1.2F, 0.0F);
		base.addChild(b3);
		setRotationAngle(b3, 0.0F, -0.0524F, 0.0F);
		b3.texOffs(0, 0).addBox(-7.0F, -1.95F, -1.0F, 8.0F, 1.0F, 8.0F, -0.25F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(0.0F, 0.0F, 0.0F);
		b3.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 2.5133F, 0.0F);
		cube_r9.texOffs(0, 0).addBox(-7.0F, -1.95F, -1.0F, 8.0F, 1.0F, 8.0F, -0.25F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(0.0F, 0.0F, 0.0F);
		b3.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 1.2566F, 0.0F);
		cube_r10.texOffs(0, 0).addBox(-7.0F, -1.95F, -1.0F, 8.0F, 1.0F, 8.0F, -0.25F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(0.0F, 0.0F, 0.0F);
		b3.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, -2.5133F, 0.0F);
		cube_r11.texOffs(0, 0).addBox(-7.0F, -1.95F, -1.0F, 8.0F, 1.0F, 8.0F, -0.25F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(0.0F, 0.0F, 0.0F);
		b3.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, -1.2566F, 0.0F);
		cube_r12.texOffs(0, 0).addBox(-7.0F, -1.95F, -1.0F, 8.0F, 1.0F, 8.0F, -0.25F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 1.0F, 0.0F);
		total.addChild(body);
		

		face = new ModelRenderer(this);
		face.setPos(0.0F, -0.45F, 0.0F);
		body.addChild(face);
		face.texOffs(0, 5).addBox(0.0F, -3.81F, -3.5F, 1.0F, 1.0F, 1.0F, -0.25F, false);
		face.texOffs(0, 0).addBox(-1.0F, -3.81F, -4.0F, 2.0F, 1.0F, 1.0F, -0.25F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(3.0611F, -1.35F, -2.3772F);
		face.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 0.9599F, 0.0F);
		cube_r13.texOffs(0, 0).addBox(-1.0F, -2.46F, 0.0F, 2.0F, 1.0F, 1.0F, -0.25F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(1.5405F, -1.35F, -3.7629F);
		face.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, -0.3054F, 0.0F);
		cube_r14.texOffs(0, 0).addBox(-1.0F, -2.46F, 0.0F, 2.0F, 1.0F, 1.0F, -0.25F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(2.9383F, -1.35F, -3.0735F);
		face.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, -0.6109F, 0.0F);
		cube_r15.texOffs(0, 0).addBox(-1.0F, -2.46F, 0.0F, 2.0F, 1.0F, 1.0F, -0.25F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(-1.5405F, -1.35F, -3.7629F);
		face.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.3054F, 0.0F);
		cube_r16.texOffs(0, 0).addBox(-1.0F, -2.46F, 0.0F, 2.0F, 1.0F, 1.0F, -0.25F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(-2.4875F, -1.35F, -1.558F);
		face.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, -0.9599F, 0.0F);
		cube_r17.texOffs(0, 0).addBox(-2.0F, -2.46F, 0.0F, 2.0F, 1.0F, 1.0F, -0.25F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(-2.9383F, -1.35F, -3.0735F);
		face.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, 0.6109F, 0.0F);
		cube_r18.texOffs(0, 0).addBox(-1.0F, -2.46F, 0.0F, 2.0F, 1.0F, 1.0F, -0.25F, false);

		eye = new ModelRenderer(this);
		eye.setPos(0.0F, -1.1F, 1.0F);
		face.addChild(eye);
		eye.texOffs(0, 13).addBox(-3.0F, -2.46F, 0.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		eye.texOffs(0, 17).addBox(1.0F, -2.46F, 0.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setPos(0.0F, -1.0F, 0.0F);
		body.addChild(body2);
		setRotationAngle(body2, 0.0F, 0.7854F, 0.0F);
		body2.texOffs(0, 23).addBox(-7.0F, -1.01F, 0.0F, 7.0F, 1.0F, 7.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(0.0F, 0.0F, 0.0F);
		body2.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0F, 2.5133F, 0.0F);
		cube_r19.texOffs(0, 23).addBox(-7.0F, -1.01F, 0.0F, 7.0F, 1.0F, 7.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(0.0F, 0.0F, 0.0F);
		body2.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 1.2566F, 0.0F);
		cube_r20.texOffs(0, 23).addBox(-7.0F, -1.01F, 0.0F, 7.0F, 1.0F, 7.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(0.0F, 0.0F, 0.0F);
		body2.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0F, -2.5133F, 0.0F);
		cube_r21.texOffs(0, 23).addBox(-7.0F, -1.01F, 0.0F, 7.0F, 1.0F, 7.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(0.0F, 0.0F, 0.0F);
		body2.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, -1.2566F, 0.0F);
		cube_r22.texOffs(0, 23).addBox(-7.0F, -1.01F, 0.0F, 7.0F, 1.0F, 7.0F, 0.0F, false);

		body3 = new ModelRenderer(this);
		body3.setPos(0.0F, 0.5F, 0.0F);
		body.addChild(body3);
		setRotationAngle(body3, 0.0F, 0.7854F, 0.0F);
		body3.texOffs(2, 24).addBox(-6.0F, -3.011F, 0.0F, 6.0F, 1.0F, 6.0F, 0.5F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(0.0F, 0.0F, 0.0F);
		body3.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0F, 2.5133F, 0.0F);
		cube_r23.texOffs(2, 24).addBox(-6.0F, -3.011F, 0.0F, 6.0F, 1.0F, 6.0F, 0.5F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setPos(0.0F, 0.0F, 0.0F);
		body3.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.0F, 1.2566F, 0.0F);
		cube_r24.texOffs(2, 24).addBox(-6.0F, -3.011F, 0.0F, 6.0F, 1.0F, 6.0F, 0.5F, false);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setPos(0.0F, 0.0F, 0.0F);
		body3.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0F, -2.5133F, 0.0F);
		cube_r25.texOffs(2, 24).addBox(-6.0F, -3.011F, 0.0F, 6.0F, 1.0F, 6.0F, 0.5F, false);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setPos(0.0F, 0.0F, 0.0F);
		body3.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.0F, -1.2566F, 0.0F);
		cube_r26.texOffs(2, 24).addBox(-6.0F, -3.011F, 0.0F, 6.0F, 1.0F, 6.0F, 0.5F, false);

		body4 = new ModelRenderer(this);
		body4.setPos(0.0F, -0.5F, 0.0F);
		body.addChild(body4);
		setRotationAngle(body4, 0.0F, 0.7854F, 0.0F);
		body4.texOffs(2, 24).addBox(-6.0F, -3.0F, 0.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

		cube_r27 = new ModelRenderer(this);
		cube_r27.setPos(0.0F, 0.0F, 0.0F);
		body4.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.0F, 2.5133F, 0.0F);
		cube_r27.texOffs(2, 24).addBox(-6.0F, -3.0F, 0.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setPos(0.0F, 0.0F, 0.0F);
		body4.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0F, 1.2566F, 0.0F);
		cube_r28.texOffs(2, 24).addBox(-6.0F, -3.0F, 0.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setPos(0.0F, 0.0F, 0.0F);
		body4.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.0F, -2.5133F, 0.0F);
		cube_r29.texOffs(2, 24).addBox(-6.0F, -3.0F, 0.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

		cube_r30 = new ModelRenderer(this);
		cube_r30.setPos(0.0F, 0.0F, 0.0F);
		body4.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.0F, -1.2566F, 0.0F);
		cube_r30.texOffs(2, 24).addBox(-6.0F, -3.0F, 0.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

		body5 = new ModelRenderer(this);
		body5.setPos(0.0F, -0.5F, 0.0F);
		body.addChild(body5);
		setRotationAngle(body5, 0.0F, 0.7854F, 0.0F);
		body5.texOffs(0, 23).addBox(-6.0F, -4.0F, -1.0F, 7.0F, 2.0F, 7.0F, -0.5F, false);

		cube_r31 = new ModelRenderer(this);
		cube_r31.setPos(0.0F, 0.0F, 0.0F);
		body5.addChild(cube_r31);
		setRotationAngle(cube_r31, 0.0F, 2.5133F, 0.0F);
		cube_r31.texOffs(0, 23).addBox(-6.0F, -4.0F, -1.0F, 7.0F, 2.0F, 7.0F, -0.5F, false);

		cube_r32 = new ModelRenderer(this);
		cube_r32.setPos(0.0F, 0.0F, 0.0F);
		body5.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.0F, 1.2566F, 0.0F);
		cube_r32.texOffs(0, 23).addBox(-6.0F, -4.0F, -1.0F, 7.0F, 2.0F, 7.0F, -0.5F, false);

		cube_r33 = new ModelRenderer(this);
		cube_r33.setPos(0.0F, 0.0F, 0.0F);
		body5.addChild(cube_r33);
		setRotationAngle(cube_r33, 0.0F, -2.5133F, 0.0F);
		cube_r33.texOffs(0, 23).addBox(-6.0F, -4.0F, -1.0F, 7.0F, 2.0F, 7.0F, -0.5F, false);

		cube_r34 = new ModelRenderer(this);
		cube_r34.setPos(0.0F, 0.0F, 0.0F);
		body5.addChild(cube_r34);
		setRotationAngle(cube_r34, 0.0F, -1.2566F, 0.0F);
		cube_r34.texOffs(0, 23).addBox(-6.0F, -4.0F, -1.0F, 7.0F, 2.0F, 7.0F, -0.5F, false);
	}

	@Override
	public void setupAnim(AngelStarFruitEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.total.yRot = 3.14159F;
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