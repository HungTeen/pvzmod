package com.hungteen.pvz.model.entity.plant.enforce;

import com.hungteen.pvz.entity.plant.enforce.UmbrellaLeafEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class UmbrellaLeafModel extends EntityModel<UmbrellaLeafEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer leaves;
	private final ModelRenderer leaf1;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer leaf2;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer leaf3;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer leaf4;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer leaf5;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer leaf6;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer leaf7;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;
	private final ModelRenderer cube_r27;
	private final ModelRenderer cube_r28;
	private final ModelRenderer face;

	public UmbrellaLeafModel() {
		textureWidth = 64;
		textureHeight = 64;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(52, 58).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(21, 46).addBox(-2.5F, -6.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(0, 50).addBox(-3.5F, -13.0F, -3.5F, 7.0F, 7.0F, 7.0F, 0.0F, false);
		body.setTextureOffset(28, 55).addBox(-4.0F, -14.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);

		leaves = new ModelRenderer(this);
		leaves.setRotationPoint(0.0F, -11.0F, -4.1F);
		total.addChild(leaves);
		

		leaf1 = new ModelRenderer(this);
		leaf1.setRotationPoint(-0.5F, -3.0F, 4.1F);
		leaves.addChild(leaf1);
		setRotationAngle(leaf1, 0.0F, 0.0F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-0.5F, 0.2608F, -5.47F);
		leaf1.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.1309F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(36, 47).addBox(-1.5F, -1.1F, -12.0F, 5.0F, 1.0F, 3.0F, -0.4F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-1.5F, 0.6544F, -5.2564F);
		leaf1.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0873F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(0, 35).addBox(-2.5F, -1.1F, -10.0F, 9.0F, 1.0F, 10.0F, -0.4F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-1.5F, 0.7075F, -5.26F);
		leaf1.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.2182F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(41, 52).addBox(-1.5F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.4F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-0.5F, 0.5F, -2.4F);
		leaf1.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.3491F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(8, 47).addBox(-1.5F, 0.1F, -2.5F, 5.0F, 1.0F, 2.0F, -0.4F, false);
		cube_r4.setTextureOffset(0, 48).addBox(-0.5F, 0.1F, -1.3F, 3.0F, 1.0F, 1.0F, -0.4F, false);

		leaf2 = new ModelRenderer(this);
		leaf2.setRotationPoint(0.0F, -3.0F, 3.1F);
		leaves.addChild(leaf2);
		setRotationAngle(leaf2, -0.0873F, -0.9599F, 0.0F);
		

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-0.5F, 0.2608F, -5.47F);
		leaf2.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.1309F, 0.0F, 0.0F);
		cube_r5.setTextureOffset(36, 47).addBox(-1.5F, -1.1F, -12.0F, 5.0F, 1.0F, 3.0F, -0.4F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-1.5F, 0.6544F, -5.2564F);
		leaf2.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0873F, 0.0F, 0.0F);
		cube_r6.setTextureOffset(0, 35).addBox(-2.5F, -1.1F, -10.0F, 9.0F, 1.0F, 10.0F, -0.4F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(-1.5F, 0.7075F, -5.26F);
		leaf2.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.2182F, 0.0F, 0.0F);
		cube_r7.setTextureOffset(41, 52).addBox(-1.5F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.4F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(-0.5F, 0.5F, -2.4F);
		leaf2.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.3491F, 0.0F, 0.0F);
		cube_r8.setTextureOffset(8, 47).addBox(-1.5F, 0.1F, -2.5F, 5.0F, 1.0F, 2.0F, -0.4F, false);
		cube_r8.setTextureOffset(0, 48).addBox(-0.5F, 0.1F, -1.3F, 3.0F, 1.0F, 1.0F, -0.4F, false);

		leaf3 = new ModelRenderer(this);
		leaf3.setRotationPoint(-0.5F, -3.0F, 5.6F);
		leaves.addChild(leaf3);
		setRotationAngle(leaf3, -0.0873F, 0.9599F, 0.0F);
		

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(-0.5F, 0.2608F, -5.47F);
		leaf3.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.1309F, 0.0F, 0.0F);
		cube_r9.setTextureOffset(36, 47).addBox(0.0155F, -1.1382F, -12.8742F, 5.0F, 1.0F, 3.0F, -0.4F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(-1.5F, 0.6544F, -5.2564F);
		leaf3.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0873F, 0.0F, 0.0F);
		cube_r10.setTextureOffset(0, 35).addBox(-0.9845F, -1.1F, -10.875F, 9.0F, 1.0F, 10.0F, -0.4F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-1.5F, 0.7075F, -5.26F);
		leaf3.addChild(cube_r11);
		setRotationAngle(cube_r11, -0.2182F, 0.0F, 0.0F);
		cube_r11.setTextureOffset(41, 52).addBox(0.0155F, -0.7369F, -1.8345F, 7.0F, 1.0F, 2.0F, -0.4F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(-0.5F, 0.5F, -2.4F);
		leaf3.addChild(cube_r12);
		setRotationAngle(cube_r12, -0.3491F, 0.0F, 0.0F);
		cube_r12.setTextureOffset(8, 47).addBox(0.0155F, 0.4698F, -3.293F, 5.0F, 1.0F, 2.0F, -0.4F, false);
		cube_r12.setTextureOffset(0, 48).addBox(1.0155F, 0.4698F, -2.093F, 3.0F, 1.0F, 1.0F, -0.4F, false);

		leaf4 = new ModelRenderer(this);
		leaf4.setRotationPoint(0.25F, -3.0F, 4.1F);
		leaves.addChild(leaf4);
		setRotationAngle(leaf4, 0.0F, 1.9635F, 0.0F);
		

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(-0.5F, 0.2608F, -5.47F);
		leaf4.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.1309F, 0.0F, 0.0F);
		cube_r13.setTextureOffset(36, 47).addBox(-1.5F, -1.1F, -12.0F, 5.0F, 1.0F, 3.0F, -0.4F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(-1.5F, 0.6544F, -5.2564F);
		leaf4.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0873F, 0.0F, 0.0F);
		cube_r14.setTextureOffset(0, 35).addBox(-2.5F, -1.1F, -10.0F, 9.0F, 1.0F, 10.0F, -0.4F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(-1.5F, 0.7075F, -5.26F);
		leaf4.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.2182F, 0.0F, 0.0F);
		cube_r15.setTextureOffset(41, 52).addBox(-1.5F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.4F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(-0.5F, 0.5F, -2.4F);
		leaf4.addChild(cube_r16);
		setRotationAngle(cube_r16, -0.3491F, 0.0F, 0.0F);
		cube_r16.setTextureOffset(8, 47).addBox(-1.5F, 0.1F, -2.5F, 5.0F, 1.0F, 2.0F, -0.4F, false);
		cube_r16.setTextureOffset(0, 48).addBox(-0.5F, 0.1F, -1.3F, 3.0F, 1.0F, 1.0F, -0.4F, false);

		leaf5 = new ModelRenderer(this);
		leaf5.setRotationPoint(0.0F, -3.0F, 3.35F);
		leaves.addChild(leaf5);
		setRotationAngle(leaf5, 0.0F, -1.9635F, 0.0F);
		

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(-0.5F, 0.2608F, -5.47F);
		leaf5.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.1309F, 0.0F, 0.0F);
		cube_r17.setTextureOffset(36, 47).addBox(-1.5F, -1.1F, -12.0F, 5.0F, 1.0F, 3.0F, -0.4F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setRotationPoint(-1.5F, 0.6544F, -5.2564F);
		leaf5.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0873F, 0.0F, 0.0F);
		cube_r18.setTextureOffset(0, 35).addBox(-2.5F, -1.1F, -10.0F, 9.0F, 1.0F, 10.0F, -0.4F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setRotationPoint(-1.5F, 0.7075F, -5.26F);
		leaf5.addChild(cube_r19);
		setRotationAngle(cube_r19, -0.2182F, 0.0F, 0.0F);
		cube_r19.setTextureOffset(41, 52).addBox(-1.5F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.4F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setRotationPoint(-0.5F, 0.5F, -2.4F);
		leaf5.addChild(cube_r20);
		setRotationAngle(cube_r20, -0.3491F, 0.0F, 0.0F);
		cube_r20.setTextureOffset(8, 47).addBox(-1.5F, 0.1F, -2.5F, 5.0F, 1.0F, 2.0F, -0.4F, false);
		cube_r20.setTextureOffset(0, 48).addBox(-0.5F, 0.1F, -1.3F, 3.0F, 1.0F, 1.0F, -0.4F, false);

		leaf6 = new ModelRenderer(this);
		leaf6.setRotationPoint(0.0F, -3.0F, 4.6F);
		leaves.addChild(leaf6);
		setRotationAngle(leaf6, -0.0873F, 2.7053F, 0.0F);
		

		cube_r21 = new ModelRenderer(this);
		cube_r21.setRotationPoint(-0.5F, 0.2608F, -5.47F);
		leaf6.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.1309F, 0.0F, 0.0F);
		cube_r21.setTextureOffset(36, 47).addBox(-1.5F, -1.1F, -12.0F, 5.0F, 1.0F, 3.0F, -0.4F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setRotationPoint(-1.5F, 0.6544F, -5.2564F);
		leaf6.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0873F, 0.0F, 0.0F);
		cube_r22.setTextureOffset(0, 35).addBox(-2.5F, -1.1F, -10.0F, 9.0F, 1.0F, 10.0F, -0.4F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setRotationPoint(-1.5F, 0.7075F, -5.26F);
		leaf6.addChild(cube_r23);
		setRotationAngle(cube_r23, -0.2182F, 0.0F, 0.0F);
		cube_r23.setTextureOffset(41, 52).addBox(-1.5F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.4F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setRotationPoint(-0.5F, 0.5F, -2.4F);
		leaf6.addChild(cube_r24);
		setRotationAngle(cube_r24, -0.3491F, 0.0F, 0.0F);
		cube_r24.setTextureOffset(8, 47).addBox(-1.5F, 0.1F, -2.5F, 5.0F, 1.0F, 2.0F, -0.4F, false);
		cube_r24.setTextureOffset(0, 48).addBox(-0.5F, 0.1F, -1.3F, 3.0F, 1.0F, 1.0F, -0.4F, false);

		leaf7 = new ModelRenderer(this);
		leaf7.setRotationPoint(0.75F, -3.0F, 4.1F);
		leaves.addChild(leaf7);
		setRotationAngle(leaf7, -0.0873F, -2.7053F, 0.0F);
		

		cube_r25 = new ModelRenderer(this);
		cube_r25.setRotationPoint(-0.5F, 0.2608F, -5.47F);
		leaf7.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.1309F, 0.0F, 0.0F);
		cube_r25.setTextureOffset(36, 47).addBox(-1.5F, -1.1F, -12.0F, 5.0F, 1.0F, 3.0F, -0.4F, false);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setRotationPoint(-1.5F, 0.6544F, -5.2564F);
		leaf7.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.0873F, 0.0F, 0.0F);
		cube_r26.setTextureOffset(0, 35).addBox(-2.5F, -1.1F, -10.0F, 9.0F, 1.0F, 10.0F, -0.4F, false);

		cube_r27 = new ModelRenderer(this);
		cube_r27.setRotationPoint(-1.5F, 0.7075F, -5.26F);
		leaf7.addChild(cube_r27);
		setRotationAngle(cube_r27, -0.2182F, 0.0F, 0.0F);
		cube_r27.setTextureOffset(41, 52).addBox(-1.5F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, -0.4F, false);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setRotationPoint(-0.5F, 0.5F, -2.4F);
		leaf7.addChild(cube_r28);
		setRotationAngle(cube_r28, -0.3491F, 0.0F, 0.0F);
		cube_r28.setTextureOffset(8, 47).addBox(-1.5F, 0.1F, -2.5F, 5.0F, 1.0F, 2.0F, -0.4F, false);
		cube_r28.setTextureOffset(0, 48).addBox(-0.5F, 0.1F, -1.3F, 3.0F, 1.0F, 1.0F, -0.4F, false);

		face = new ModelRenderer(this);
		face.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(face);
		face.setTextureOffset(28, 55).addBox(1.25F, -11.75F, -3.92F, 2.0F, 3.0F, 1.0F, -0.4F, false);
		face.setTextureOffset(28, 59).addBox(-3.25F, -11.75F, -3.92F, 2.0F, 3.0F, 1.0F, -0.4F, false);
	}

	@Override
	public void setRotationAngles(UmbrellaLeafEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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