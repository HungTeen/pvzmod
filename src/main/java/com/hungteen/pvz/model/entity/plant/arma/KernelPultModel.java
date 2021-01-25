package com.hungteen.pvz.model.entity.plant.arma;

import com.hungteen.pvz.entity.plant.arma.ButterPultEntity;
import com.hungteen.pvz.entity.plant.arma.KernelPultEntity;
import com.hungteen.pvz.entity.plant.arma.KernelPultEntity.CornTypes;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class KernelPultModel<T extends KernelPultEntity> extends EntityModel<T> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer face;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer leaves;
	private final ModelRenderer leave1;
	private final ModelRenderer cube_r3;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r4;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r5;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r6;
	private final ModelRenderer leave2;
	private final ModelRenderer cube_r7;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r8;
	private final ModelRenderer bone5;
	private final ModelRenderer cube_r9;
	private final ModelRenderer bone6;
	private final ModelRenderer cube_r10;
	private final ModelRenderer leave3;
	private final ModelRenderer cube_r11;
	private final ModelRenderer bone7;
	private final ModelRenderer cube_r12;
	private final ModelRenderer bone8;
	private final ModelRenderer cube_r13;
	private final ModelRenderer bone9;
	private final ModelRenderer cube_r14;
	private final ModelRenderer leave4;
	private final ModelRenderer cube_r15;
	private final ModelRenderer bone10;
	private final ModelRenderer cube_r16;
	private final ModelRenderer bone11;
	private final ModelRenderer cube_r17;
	private final ModelRenderer bone12;
	private final ModelRenderer cube_r18;
	private final ModelRenderer leave5;
	private final ModelRenderer cube_r19;
	private final ModelRenderer bone13;
	private final ModelRenderer cube_r20;
	private final ModelRenderer bone14;
	private final ModelRenderer cube_r21;
	private final ModelRenderer bone15;
	private final ModelRenderer cube_r22;
	private final ModelRenderer leave6;
	private final ModelRenderer cube_r23;
	private final ModelRenderer bone16;
	private final ModelRenderer cube_r24;
	private final ModelRenderer bone17;
	private final ModelRenderer cube_r25;
	private final ModelRenderer bone18;
	private final ModelRenderer cube_r26;
	private final ModelRenderer pult;
	private final ModelRenderer cube_r27;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer basket;
	private final ModelRenderer butter;
	private final ModelRenderer kernel;

	public KernelPultModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -1.0F, 0.0F);
		total.addChild(head);
		head.setTextureOffset(0, 111).addBox(-6.0F, -4.5F, -6.0F, 12.0F, 5.0F, 12.0F, -0.4F, false);
		head.setTextureOffset(48, 111).addBox(-6.0F, -8.25F, -6.0F, 12.0F, 5.0F, 12.0F, -0.8F, false);
		head.setTextureOffset(0, 95).addBox(-6.0F, -11.25F, -6.0F, 12.0F, 5.0F, 12.0F, -1.2F, false);
		head.setTextureOffset(48, 98).addBox(-5.0F, -12.25F, -5.0F, 10.0F, 3.0F, 10.0F, -0.7F, false);
		head.setTextureOffset(88, 109).addBox(-5.0F, -13.75F, -5.0F, 10.0F, 4.0F, 10.0F, -1.3F, false);
		head.setTextureOffset(88, 102).addBox(-3.0F, -13.25F, -3.0F, 6.0F, 1.0F, 6.0F, -0.1F, false);
		head.setTextureOffset(44, 117).addBox(-2.0F, -14.0F, -2.0F, 4.0F, 2.0F, 4.0F, -0.4F, false);

		face = new ModelRenderer(this);
		face.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(face);
		face.setTextureOffset(2, 84).addBox(-3.0F, -7.75F, -5.65F, 2.0F, 3.0F, 1.0F, -0.4F, false);
		face.setTextureOffset(1, 89).addBox(0.5F, -7.75F, -5.65F, 2.0F, 3.0F, 1.0F, -0.4F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(3.3279F, -8.2351F, -5.5F);
		face.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.2443F);
		cube_r1.setTextureOffset(98, 123).addBox(-3.0F, 0.0F, 0.25F, 3.0F, 1.0F, 1.0F, -0.4F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-0.75F, -8.0F, -5.5F);
		face.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.2618F);
		cube_r2.setTextureOffset(98, 126).addBox(-3.0F, -1.0F, 0.25F, 3.0F, 1.0F, 1.0F, -0.4F, false);

		leaves = new ModelRenderer(this);
		leaves.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(leaves);
		leaves.setTextureOffset(0, 82).addBox(-6.0F, -1.0F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);

		leave1 = new ModelRenderer(this);
		leave1.setRotationPoint(-5.0F, 0.0F, -3.0F);
		leaves.addChild(leave1);
		setRotationAngle(leave1, 0.0F, -0.5236F, 0.0F);
		

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		leave1.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.2618F);
		cube_r3.setTextureOffset(36, 100).addBox(-3.0F, -1.0F, -3.0F, 4.0F, 1.0F, 6.0F, -0.4F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-2.0F, 0.0F, 0.0F);
		leave1.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.4363F);
		

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.4377F, -0.6026F, -3.5F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.2618F);
		cube_r4.setTextureOffset(43, 111).addBox(-3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 5.0F, -0.4F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-1.9981F, -0.0872F, 0.0F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.3491F);
		

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(2.9218F, -0.5407F, -4.0F);
		bone3.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.1745F);
		cube_r5.setTextureOffset(79, 102).addBox(-4.9981F, -1.0872F, 2.0F, 3.0F, 1.0F, 4.0F, -0.4F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-3.0019F, 0.0872F, 0.0F);
		bone3.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.3927F);
		

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(1.0857F, -2.3915F, -1.5F);
		bone2.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.48F);
		cube_r6.setTextureOffset(37, 112).addBox(-2.9676F, -0.3365F, 0.5F, 3.0F, 1.0F, 2.0F, -0.4F, false);

		leave2 = new ModelRenderer(this);
		leave2.setRotationPoint(0.0F, 0.0F, -5.0F);
		leaves.addChild(leave2);
		setRotationAngle(leave2, 0.0F, -1.5708F, 0.0F);
		

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
		leave2.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.2618F);
		cube_r7.setTextureOffset(108, 100).addBox(-3.0F, -1.0F, -3.0F, 4.0F, 1.0F, 6.0F, -0.4F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-2.0F, 0.0F, 0.0F);
		leave2.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.4363F);
		

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.4377F, -0.6026F, -3.5F);
		bone4.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.2618F);
		cube_r8.setTextureOffset(97, 96).addBox(-3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 5.0F, -0.4F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-1.9981F, -0.0872F, 0.0F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.3491F);
		

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(2.9218F, -0.5407F, -4.0F);
		bone5.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 0.1745F);
		cube_r9.setTextureOffset(49, 93).addBox(-4.9981F, -1.0872F, 2.0F, 3.0F, 1.0F, 4.0F, -0.4F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-3.0019F, 0.0872F, 0.0F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.3927F);
		

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(1.0857F, -2.3915F, -1.5F);
		bone6.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.48F);
		cube_r10.setTextureOffset(79, 99).addBox(-2.9676F, -0.3365F, 0.5F, 3.0F, 1.0F, 2.0F, -0.4F, false);

		leave3 = new ModelRenderer(this);
		leave3.setRotationPoint(5.0F, 0.0F, -3.0F);
		leaves.addChild(leave3);
		setRotationAngle(leave3, 0.0F, -2.618F, 0.0F);
		

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 0.0F, 0.0F);
		leave3.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, 0.2618F);
		cube_r11.setTextureOffset(64, 91).addBox(-3.0F, -1.0F, -3.0F, 4.0F, 1.0F, 6.0F, -0.4F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-2.0F, 0.0F, 0.0F);
		leave3.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.0F, -0.4363F);
		

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(0.4377F, -0.6026F, -3.5F);
		bone7.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, 0.2618F);
		cube_r12.setTextureOffset(85, 92).addBox(-3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 5.0F, -0.4F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-1.9981F, -0.0872F, 0.0F);
		bone7.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.3491F);
		

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(2.9218F, -0.5407F, -4.0F);
		bone8.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 0.0F, 0.1745F);
		cube_r13.setTextureOffset(114, 95).addBox(-4.9981F, -1.0872F, 2.0F, 3.0F, 1.0F, 4.0F, -0.4F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-3.0019F, 0.0872F, 0.0F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, 0.3927F);
		

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(1.0857F, -2.3915F, -1.5F);
		bone9.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, 0.48F);
		cube_r14.setTextureOffset(108, 96).addBox(-2.9676F, -0.3365F, 0.5F, 3.0F, 1.0F, 2.0F, -0.4F, false);

		leave4 = new ModelRenderer(this);
		leave4.setRotationPoint(-5.0F, 0.0F, 3.0F);
		leaves.addChild(leave4);
		setRotationAngle(leave4, 0.0F, 0.7854F, 0.0F);
		

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(0.0F, 0.0F, 0.0F);
		leave4.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 0.0F, 0.2618F);
		cube_r15.setTextureOffset(36, 86).addBox(-3.0F, -1.0F, -3.0F, 4.0F, 1.0F, 6.0F, -0.4F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-2.0F, 0.0F, 0.0F);
		leave4.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.0F, -0.4363F);
		

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(0.4377F, -0.6026F, -3.5F);
		bone10.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, 0.2618F);
		cube_r16.setTextureOffset(50, 86).addBox(-3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 5.0F, -0.4F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-1.9981F, -0.0872F, 0.0F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, -0.3491F);
		

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(2.9218F, -0.5407F, -4.0F);
		bone11.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.0F, 0.1745F);
		cube_r17.setTextureOffset(96, 91).addBox(-4.9981F, -1.0872F, 2.0F, 3.0F, 1.0F, 4.0F, -0.4F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-3.0019F, 0.0872F, 0.0F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, 0.3927F);
		

		cube_r18 = new ModelRenderer(this);
		cube_r18.setRotationPoint(1.0857F, -2.3915F, -1.5F);
		bone12.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, 0.0F, 0.48F);
		cube_r18.setTextureOffset(118, 125).addBox(-2.9676F, -0.3365F, 0.5F, 3.0F, 1.0F, 2.0F, -0.4F, false);

		leave5 = new ModelRenderer(this);
		leave5.setRotationPoint(0.0F, 0.0F, 5.0F);
		leaves.addChild(leave5);
		setRotationAngle(leave5, 0.0F, 1.5708F, 0.0F);
		

		cube_r19 = new ModelRenderer(this);
		cube_r19.setRotationPoint(0.0F, 0.0F, 0.0F);
		leave5.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0F, 0.0F, 0.2618F);
		cube_r19.setTextureOffset(108, 88).addBox(-3.0F, -1.0F, -3.0F, 4.0F, 1.0F, 6.0F, -0.4F, false);

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(-2.0F, 0.0F, 0.0F);
		leave5.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, -0.4363F);
		

		cube_r20 = new ModelRenderer(this);
		cube_r20.setRotationPoint(0.4377F, -0.6026F, -3.5F);
		bone13.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 0.0F, 0.2618F);
		cube_r20.setTextureOffset(98, 85).addBox(-3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 5.0F, -0.4F, false);

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(-1.9981F, -0.0872F, 0.0F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, 0.0F, 0.0F, -0.3491F);
		

		cube_r21 = new ModelRenderer(this);
		cube_r21.setRotationPoint(2.9218F, -0.5407F, -4.0F);
		bone14.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0F, 0.0F, 0.1745F);
		cube_r21.setTextureOffset(84, 87).addBox(-4.9981F, -1.0872F, 2.0F, 3.0F, 1.0F, 4.0F, -0.4F, false);

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(-3.0019F, 0.0872F, 0.0F);
		bone14.addChild(bone15);
		setRotationAngle(bone15, 0.0F, 0.0F, 0.3927F);
		

		cube_r22 = new ModelRenderer(this);
		cube_r22.setRotationPoint(1.0857F, -2.3915F, -1.5F);
		bone15.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, 0.0F, 0.48F);
		cube_r22.setTextureOffset(108, 125).addBox(-2.9676F, -0.3365F, 0.5F, 3.0F, 1.0F, 2.0F, -0.4F, false);

		leave6 = new ModelRenderer(this);
		leave6.setRotationPoint(5.0F, 0.0F, 3.0F);
		leaves.addChild(leave6);
		setRotationAngle(leave6, 0.0F, 2.3562F, 0.0F);
		

		cube_r23 = new ModelRenderer(this);
		cube_r23.setRotationPoint(0.0F, 0.0F, 0.0F);
		leave6.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0F, 0.0F, 0.2618F);
		cube_r23.setTextureOffset(64, 84).addBox(-3.0F, -1.0F, -3.0F, 4.0F, 1.0F, 6.0F, -0.4F, false);

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(-2.0F, 0.0F, 0.0F);
		leave6.addChild(bone16);
		setRotationAngle(bone16, 0.0F, 0.0F, -0.4363F);
		

		cube_r24 = new ModelRenderer(this);
		cube_r24.setRotationPoint(0.4377F, -0.6026F, -3.5F);
		bone16.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.0F, 0.0F, 0.2618F);
		cube_r24.setTextureOffset(112, 82).addBox(-3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 5.0F, -0.4F, false);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(-1.9981F, -0.0872F, 0.0F);
		bone16.addChild(bone17);
		setRotationAngle(bone17, 0.0F, 0.0F, -0.3491F);
		

		cube_r25 = new ModelRenderer(this);
		cube_r25.setRotationPoint(2.9218F, -0.5407F, -4.0F);
		bone17.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0F, 0.0F, 0.1745F);
		cube_r25.setTextureOffset(79, 82).addBox(-4.9981F, -1.0872F, 2.0F, 3.0F, 1.0F, 4.0F, -0.4F, false);

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(-3.0019F, 0.0872F, 0.0F);
		bone17.addChild(bone18);
		setRotationAngle(bone18, 0.0F, 0.0F, 0.3927F);
		

		cube_r26 = new ModelRenderer(this);
		cube_r26.setRotationPoint(1.0857F, -2.3915F, -1.5F);
		bone18.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.0F, 0.0F, 0.48F);
		cube_r26.setTextureOffset(93, 83).addBox(-2.9676F, -0.3365F, 0.5F, 3.0F, 1.0F, 2.0F, -0.4F, false);

		pult = new ModelRenderer(this);
		pult.setRotationPoint(0.0F, -13.0F, 0.0F);
		total.addChild(pult);
		

		cube_r27 = new ModelRenderer(this);
		cube_r27.setRotationPoint(0.0F, -1.6192F, 9.0169F);
		pult.addChild(cube_r27);
		setRotationAngle(cube_r27, -1.6144F, 0.0F, 0.0F);
		cube_r27.setTextureOffset(120, 108).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, -0.4F, false);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setRotationPoint(0.0F, -3.8077F, 1.2387F);
		pult.addChild(cube_r28);
		setRotationAngle(cube_r28, -1.8326F, 0.0F, 0.0F);
		cube_r28.setTextureOffset(0, 112).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, -0.4F, false);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setRotationPoint(0.0F, 0.0F, 0.0F);
		pult.addChild(cube_r29);
		setRotationAngle(cube_r29, -0.5236F, 0.0F, 0.0F);
		cube_r29.setTextureOffset(90, 111).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 6.0F, 2.0F, -0.4F, false);

		basket = new ModelRenderer(this);
		basket.setRotationPoint(0.0F, 0.5F, 18.0F);
		pult.addChild(basket);
		basket.setTextureOffset(37, 79).addBox(-4.0F, -1.0F, 0.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		basket.setTextureOffset(55, 81).addBox(-4.0F, -4.0F, 6.0F, 6.0F, 3.0F, 1.0F, 0.0F, false);
		basket.setTextureOffset(103, 81).addBox(-4.0F, -4.0F, -1.0F, 6.0F, 3.0F, 1.0F, 0.0F, false);
		basket.setTextureOffset(28, 73).addBox(2.0F, -4.0F, 0.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);
		basket.setTextureOffset(14, 73).addBox(-5.0F, -4.0F, 0.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);

		butter = new ModelRenderer(this);
		butter.setRotationPoint(0.0F, -1.0F, 0.0F);
		basket.addChild(butter);
		butter.setTextureOffset(104, 69).addBox(-4.0F, -6.0F, 0.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		kernel = new ModelRenderer(this);
		kernel.setRotationPoint(0.0F, -1.0F, 0.0F);
		basket.addChild(kernel);
		kernel.setTextureOffset(0, 99).addBox(-2.0F, -5.0F, 2.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(KernelPultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAttackTime() > 0) {
			float percent = 1 - entity.getAttackTime() * 1.0F / entity.getPultAnimTime();
			pult.rotateAngleX = (1F - MathHelper.abs(MathHelper.cos(percent * 3.14159F))) * 1.5F;
			this.kernel.showModel = (percent < 0.5) && entity.getCurrentBullet() == CornTypes.KERNEL;
			this.butter.showModel = (percent < 0.5) && entity.getCurrentBullet() == CornTypes.BUTTER;
		} else {
			pult.rotateAngleX = MathHelper.sin(ageInTicks / 10) / 8;
			this.butter.showModel = (entity instanceof ButterPultEntity);
			this.kernel.showModel = ! this.butter.showModel;
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