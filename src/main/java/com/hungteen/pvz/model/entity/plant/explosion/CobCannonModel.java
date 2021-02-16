package com.hungteen.pvz.model.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.explosion.CobCannonEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CobCannonModel extends EntityModel<CobCannonEntity> {
	private final ModelRenderer total;
	private final ModelRenderer wheels;
	private final ModelRenderer wheel1;
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
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer wheel2;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;
	private final ModelRenderer cube_r27;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer cube_r30;
	private final ModelRenderer wheel3;
	private final ModelRenderer cube_r31;
	private final ModelRenderer cube_r32;
	private final ModelRenderer cube_r33;
	private final ModelRenderer cube_r34;
	private final ModelRenderer cube_r35;
	private final ModelRenderer cube_r36;
	private final ModelRenderer cube_r37;
	private final ModelRenderer cube_r38;
	private final ModelRenderer cube_r39;
	private final ModelRenderer cube_r40;
	private final ModelRenderer cube_r41;
	private final ModelRenderer cube_r42;
	private final ModelRenderer cube_r43;
	private final ModelRenderer cube_r44;
	private final ModelRenderer cube_r45;
	private final ModelRenderer wheel4;
	private final ModelRenderer cube_r46;
	private final ModelRenderer cube_r47;
	private final ModelRenderer cube_r48;
	private final ModelRenderer cube_r49;
	private final ModelRenderer cube_r50;
	private final ModelRenderer cube_r51;
	private final ModelRenderer cube_r52;
	private final ModelRenderer cube_r53;
	private final ModelRenderer cube_r54;
	private final ModelRenderer cube_r55;
	private final ModelRenderer cube_r56;
	private final ModelRenderer cube_r57;
	private final ModelRenderer cube_r58;
	private final ModelRenderer cube_r59;
	private final ModelRenderer cube_r60;
	private final ModelRenderer base;
	private final ModelRenderer body;
	private final ModelRenderer corn;
	private final ModelRenderer outer;
	private final ModelRenderer layer1;
	private final ModelRenderer layer2;
	private final ModelRenderer layer3;
	private final ModelRenderer layer4;
	private final ModelRenderer leaves;
	private final ModelRenderer leaf1;
	private final ModelRenderer l1;
	private final ModelRenderer cube_r61;
	private final ModelRenderer bone;
	private final ModelRenderer leaf2;
	private final ModelRenderer l2;
	private final ModelRenderer cube_r62;
	private final ModelRenderer bone2;
	private final ModelRenderer leaf3;
	private final ModelRenderer l3;
	private final ModelRenderer cube_r63;
	private final ModelRenderer bone3;
	private final ModelRenderer leaf4;
	private final ModelRenderer l4;
	private final ModelRenderer cube_r64;
	private final ModelRenderer bone4;
	private final ModelRenderer tail;
	private final ModelRenderer cube_r65;
	private final ModelRenderer cube_r66;
	private final ModelRenderer cube_r67;
	private final ModelRenderer cube_r68;
	private final ModelRenderer face;

	public CobCannonModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		wheels = new ModelRenderer(this);
		wheels.setRotationPoint(0.0F, -4.0F, 0.0F);
		total.addChild(wheels);
		

		wheel1 = new ModelRenderer(this);
		wheel1.setRotationPoint(9.0F, 0.0F, -10.0F);
		wheels.addChild(wheel1);
		wheel1.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		wheel1.setTextureOffset(61, 77).addBox(-0.25F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.5F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r1);
		setRotationAngle(cube_r1, 2.7489F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r2);
		setRotationAngle(cube_r2, 2.3562F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r3);
		setRotationAngle(cube_r3, 1.9635F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r4);
		setRotationAngle(cube_r4, 1.5708F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r5);
		setRotationAngle(cube_r5, 1.1781F, 0.0F, 0.0F);
		cube_r5.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.7854F, 0.0F, 0.0F);
		cube_r6.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.3927F, 0.0F, 0.0F);
		cube_r7.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r8);
		setRotationAngle(cube_r8, 3.1416F, 0.0F, 0.0F);
		cube_r8.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r9);
		setRotationAngle(cube_r9, -2.7489F, 0.0F, 0.0F);
		cube_r9.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r10);
		setRotationAngle(cube_r10, -2.3562F, 0.0F, 0.0F);
		cube_r10.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r11);
		setRotationAngle(cube_r11, -1.9635F, 0.0F, 0.0F);
		cube_r11.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r12);
		setRotationAngle(cube_r12, -1.5708F, 0.0F, 0.0F);
		cube_r12.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r13);
		setRotationAngle(cube_r13, -1.1781F, 0.0F, 0.0F);
		cube_r13.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r14);
		setRotationAngle(cube_r14, -0.7854F, 0.0F, 0.0F);
		cube_r14.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel1.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.3927F, 0.0F, 0.0F);
		cube_r15.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		wheel2 = new ModelRenderer(this);
		wheel2.setRotationPoint(-9.0F, 0.0F, -10.0F);
		wheels.addChild(wheel2);
		wheel2.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		wheel2.setTextureOffset(45, 102).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.5F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r16);
		setRotationAngle(cube_r16, 2.7489F, 0.0F, 0.0F);
		cube_r16.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r17);
		setRotationAngle(cube_r17, 2.3562F, 0.0F, 0.0F);
		cube_r17.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r18);
		setRotationAngle(cube_r18, 1.9635F, 0.0F, 0.0F);
		cube_r18.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r19);
		setRotationAngle(cube_r19, 1.5708F, 0.0F, 0.0F);
		cube_r19.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r20);
		setRotationAngle(cube_r20, 1.1781F, 0.0F, 0.0F);
		cube_r20.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.7854F, 0.0F, 0.0F);
		cube_r21.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.3927F, 0.0F, 0.0F);
		cube_r22.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r23);
		setRotationAngle(cube_r23, 3.1416F, 0.0F, 0.0F);
		cube_r23.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r24);
		setRotationAngle(cube_r24, -2.7489F, 0.0F, 0.0F);
		cube_r24.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r25);
		setRotationAngle(cube_r25, -2.3562F, 0.0F, 0.0F);
		cube_r25.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r26);
		setRotationAngle(cube_r26, -1.9635F, 0.0F, 0.0F);
		cube_r26.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r27 = new ModelRenderer(this);
		cube_r27.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r27);
		setRotationAngle(cube_r27, -1.5708F, 0.0F, 0.0F);
		cube_r27.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r28);
		setRotationAngle(cube_r28, -1.1781F, 0.0F, 0.0F);
		cube_r28.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r29);
		setRotationAngle(cube_r29, -0.7854F, 0.0F, 0.0F);
		cube_r29.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r30 = new ModelRenderer(this);
		cube_r30.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel2.addChild(cube_r30);
		setRotationAngle(cube_r30, -0.3927F, 0.0F, 0.0F);
		cube_r30.setTextureOffset(0, 7).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		wheel3 = new ModelRenderer(this);
		wheel3.setRotationPoint(9.0F, 0.0F, 11.0F);
		wheels.addChild(wheel3);
		wheel3.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		wheel3.setTextureOffset(58, 85).addBox(-0.25F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.5F, false);

		cube_r31 = new ModelRenderer(this);
		cube_r31.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r31);
		setRotationAngle(cube_r31, 2.7489F, 0.0F, 0.0F);
		cube_r31.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r32 = new ModelRenderer(this);
		cube_r32.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r32);
		setRotationAngle(cube_r32, 2.3562F, 0.0F, 0.0F);
		cube_r32.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r33 = new ModelRenderer(this);
		cube_r33.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r33);
		setRotationAngle(cube_r33, 1.9635F, 0.0F, 0.0F);
		cube_r33.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r34 = new ModelRenderer(this);
		cube_r34.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r34);
		setRotationAngle(cube_r34, 1.5708F, 0.0F, 0.0F);
		cube_r34.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r35 = new ModelRenderer(this);
		cube_r35.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r35);
		setRotationAngle(cube_r35, 1.1781F, 0.0F, 0.0F);
		cube_r35.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r36 = new ModelRenderer(this);
		cube_r36.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r36);
		setRotationAngle(cube_r36, 0.7854F, 0.0F, 0.0F);
		cube_r36.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r37 = new ModelRenderer(this);
		cube_r37.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r37);
		setRotationAngle(cube_r37, 0.3927F, 0.0F, 0.0F);
		cube_r37.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r38 = new ModelRenderer(this);
		cube_r38.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r38);
		setRotationAngle(cube_r38, 3.1416F, 0.0F, 0.0F);
		cube_r38.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r39 = new ModelRenderer(this);
		cube_r39.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r39);
		setRotationAngle(cube_r39, -2.7489F, 0.0F, 0.0F);
		cube_r39.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r40 = new ModelRenderer(this);
		cube_r40.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r40);
		setRotationAngle(cube_r40, -2.3562F, 0.0F, 0.0F);
		cube_r40.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r41 = new ModelRenderer(this);
		cube_r41.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r41);
		setRotationAngle(cube_r41, -1.9635F, 0.0F, 0.0F);
		cube_r41.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r42 = new ModelRenderer(this);
		cube_r42.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r42);
		setRotationAngle(cube_r42, -1.5708F, 0.0F, 0.0F);
		cube_r42.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r43 = new ModelRenderer(this);
		cube_r43.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r43);
		setRotationAngle(cube_r43, -1.1781F, 0.0F, 0.0F);
		cube_r43.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r44 = new ModelRenderer(this);
		cube_r44.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r44);
		setRotationAngle(cube_r44, -0.7854F, 0.0F, 0.0F);
		cube_r44.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r45 = new ModelRenderer(this);
		cube_r45.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel3.addChild(cube_r45);
		setRotationAngle(cube_r45, -0.3927F, 0.0F, 0.0F);
		cube_r45.setTextureOffset(0, 14).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		wheel4 = new ModelRenderer(this);
		wheel4.setRotationPoint(-9.0F, 0.0F, 11.0F);
		wheels.addChild(wheel4);
		wheel4.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		wheel4.setTextureOffset(58, 93).addBox(-1.75F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.5F, false);

		cube_r46 = new ModelRenderer(this);
		cube_r46.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r46);
		setRotationAngle(cube_r46, 2.7489F, 0.0F, 0.0F);
		cube_r46.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r47 = new ModelRenderer(this);
		cube_r47.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r47);
		setRotationAngle(cube_r47, 2.3562F, 0.0F, 0.0F);
		cube_r47.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r48 = new ModelRenderer(this);
		cube_r48.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r48);
		setRotationAngle(cube_r48, 1.9635F, 0.0F, 0.0F);
		cube_r48.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r49 = new ModelRenderer(this);
		cube_r49.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r49);
		setRotationAngle(cube_r49, 1.5708F, 0.0F, 0.0F);
		cube_r49.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r50 = new ModelRenderer(this);
		cube_r50.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r50);
		setRotationAngle(cube_r50, 1.1781F, 0.0F, 0.0F);
		cube_r50.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r51 = new ModelRenderer(this);
		cube_r51.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r51);
		setRotationAngle(cube_r51, 0.7854F, 0.0F, 0.0F);
		cube_r51.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r52 = new ModelRenderer(this);
		cube_r52.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r52);
		setRotationAngle(cube_r52, 0.3927F, 0.0F, 0.0F);
		cube_r52.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r53 = new ModelRenderer(this);
		cube_r53.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r53);
		setRotationAngle(cube_r53, 3.1416F, 0.0F, 0.0F);
		cube_r53.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r54 = new ModelRenderer(this);
		cube_r54.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r54);
		setRotationAngle(cube_r54, -2.7489F, 0.0F, 0.0F);
		cube_r54.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r55 = new ModelRenderer(this);
		cube_r55.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r55);
		setRotationAngle(cube_r55, -2.3562F, 0.0F, 0.0F);
		cube_r55.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r56 = new ModelRenderer(this);
		cube_r56.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r56);
		setRotationAngle(cube_r56, -1.9635F, 0.0F, 0.0F);
		cube_r56.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r57 = new ModelRenderer(this);
		cube_r57.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r57);
		setRotationAngle(cube_r57, -1.5708F, 0.0F, 0.0F);
		cube_r57.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r58 = new ModelRenderer(this);
		cube_r58.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r58);
		setRotationAngle(cube_r58, -1.1781F, 0.0F, 0.0F);
		cube_r58.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r59 = new ModelRenderer(this);
		cube_r59.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r59);
		setRotationAngle(cube_r59, -0.7854F, 0.0F, 0.0F);
		cube_r59.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r60 = new ModelRenderer(this);
		cube_r60.setRotationPoint(0.0F, 0.0F, 0.0F);
		wheel4.addChild(cube_r60);
		setRotationAngle(cube_r60, -0.3927F, 0.0F, 0.0F);
		cube_r60.setTextureOffset(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, -7.0F, 0.0F);
		total.addChild(base);
		base.setTextureOffset(0, 0).addBox(-8.0F, 1.0F, -16.0F, 3.0F, 4.0F, 32.0F, 0.0F, false);
		base.setTextureOffset(40, 4).addBox(5.0F, 1.0F, -16.0F, 3.0F, 4.0F, 32.0F, 0.0F, false);
		base.setTextureOffset(79, 0).addBox(-5.0F, 3.0F, -16.0F, 10.0F, 2.0F, 32.0F, 0.0F, false);
		base.setTextureOffset(72, 76).addBox(-9.0F, 2.0F, -6.5F, 2.0F, 2.0F, 14.0F, -0.75F, false);
		base.setTextureOffset(93, 62).addBox(7.0F, 2.0F, -6.5F, 2.0F, 2.0F, 14.0F, -0.75F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -9.0F, 10.0F);
		total.addChild(body);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		

		corn = new ModelRenderer(this);
		corn.setRotationPoint(0.0F, 3.0F, -2.0F);
		body.addChild(corn);
		corn.setTextureOffset(0, 37).addBox(-4.5F, -28.0F, -2.5F, 9.0F, 29.0F, 9.0F, 0.0F, false);
		corn.setTextureOffset(41, 19).addBox(-3.5F, -28.75F, -1.5F, 7.0F, 1.0F, 7.0F, -0.25F, false);

		outer = new ModelRenderer(this);
		outer.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(outer);
		outer.setTextureOffset(99, 35).addBox(-5.0F, 4.25F, -5.0F, 10.0F, 1.0F, 10.0F, -0.25F, false);
		outer.setTextureOffset(106, 48).addBox(-4.0F, 4.5F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);

		layer1 = new ModelRenderer(this);
		layer1.setRotationPoint(0.0F, 0.0F, 0.0F);
		outer.addChild(layer1);
		layer1.setTextureOffset(10, 23).addBox(-5.25F, -22.0F, -5.0F, 1.0F, 2.0F, 3.0F, -0.25F, false);
		layer1.setTextureOffset(38, 41).addBox(-5.25F, -19.0F, -5.0F, 1.0F, 24.0F, 10.0F, -0.25F, false);
		layer1.setTextureOffset(20, 22).addBox(-5.25F, -20.5F, 1.0F, 1.0F, 2.0F, 4.0F, -0.25F, false);
		layer1.setTextureOffset(10, 16).addBox(-5.25F, -20.5F, -5.0F, 1.0F, 2.0F, 4.0F, -0.25F, false);
		layer1.setTextureOffset(22, 16).addBox(-5.25F, -22.0F, 2.0F, 1.0F, 2.0F, 3.0F, -0.25F, false);
		layer1.setTextureOffset(11, 10).addBox(-5.25F, -23.5F, -5.0F, 1.0F, 2.0F, 2.0F, -0.25F, false);
		layer1.setTextureOffset(21, 10).addBox(-5.25F, -23.5F, 3.0F, 1.0F, 2.0F, 2.0F, -0.25F, false);

		layer2 = new ModelRenderer(this);
		layer2.setRotationPoint(9.5F, 0.0F, 0.0F);
		outer.addChild(layer2);
		layer2.setTextureOffset(10, 0).addBox(-5.25F, -22.0F, -5.0F, 1.0F, 2.0F, 3.0F, -0.25F, false);
		layer2.setTextureOffset(60, 41).addBox(-5.25F, -19.0F, -5.0F, 1.0F, 24.0F, 10.0F, -0.25F, false);
		layer2.setTextureOffset(20, 1).addBox(-5.25F, -20.5F, 1.0F, 1.0F, 2.0F, 4.0F, -0.25F, false);
		layer2.setTextureOffset(41, 0).addBox(-5.25F, -20.5F, -5.0F, 1.0F, 2.0F, 4.0F, -0.25F, false);
		layer2.setTextureOffset(54, 0).addBox(-5.25F, -22.0F, 2.0F, 1.0F, 2.0F, 3.0F, -0.25F, false);
		layer2.setTextureOffset(40, 8).addBox(-5.25F, -23.5F, -5.0F, 1.0F, 2.0F, 2.0F, -0.25F, false);
		layer2.setTextureOffset(40, 14).addBox(-5.25F, -23.5F, 3.0F, 1.0F, 2.0F, 2.0F, -0.25F, false);

		layer3 = new ModelRenderer(this);
		layer3.setRotationPoint(0.0F, 0.0F, 0.0F);
		outer.addChild(layer3);
		setRotationAngle(layer3, 0.0F, 1.5708F, 0.0F);
		layer3.setTextureOffset(51, 8).addBox(-5.25F, -22.0F, -5.0F, 1.0F, 2.0F, 3.0F, -0.25F, false);
		layer3.setTextureOffset(83, 40).addBox(-5.25F, -19.0F, -5.0F, 1.0F, 24.0F, 10.0F, -0.25F, false);
		layer3.setTextureOffset(80, 0).addBox(-5.25F, -20.5F, 1.0F, 1.0F, 2.0F, 4.0F, -0.25F, false);
		layer3.setTextureOffset(80, 8).addBox(-5.25F, -20.5F, -5.0F, 1.0F, 2.0F, 4.0F, -0.25F, false);
		layer3.setTextureOffset(81, 16).addBox(-5.25F, -22.0F, 2.0F, 1.0F, 2.0F, 3.0F, -0.25F, false);
		layer3.setTextureOffset(82, 24).addBox(-5.25F, -23.5F, -5.0F, 1.0F, 2.0F, 2.0F, -0.25F, false);
		layer3.setTextureOffset(92, 24).addBox(-5.25F, -23.5F, 3.0F, 1.0F, 2.0F, 2.0F, -0.25F, false);

		layer4 = new ModelRenderer(this);
		layer4.setRotationPoint(0.0F, 0.0F, 0.0F);
		outer.addChild(layer4);
		setRotationAngle(layer4, 0.0F, -1.5708F, 0.0F);
		layer4.setTextureOffset(92, 16).addBox(-5.25F, -22.0F, -5.0F, 1.0F, 2.0F, 3.0F, -0.25F, false);
		layer4.setTextureOffset(0, 76).addBox(-5.25F, -19.0F, -5.0F, 1.0F, 24.0F, 10.0F, -0.25F, false);
		layer4.setTextureOffset(93, 0).addBox(-5.25F, -20.5F, 1.0F, 1.0F, 2.0F, 4.0F, -0.25F, false);
		layer4.setTextureOffset(92, 8).addBox(-5.25F, -20.5F, -5.0F, 1.0F, 2.0F, 4.0F, -0.25F, false);
		layer4.setTextureOffset(14, 77).addBox(-5.25F, -22.0F, 2.0F, 1.0F, 2.0F, 3.0F, -0.25F, false);
		layer4.setTextureOffset(0, 78).addBox(-5.25F, -23.5F, -5.0F, 1.0F, 2.0F, 2.0F, -0.25F, false);
		layer4.setTextureOffset(0, 38).addBox(-5.25F, -23.5F, 3.0F, 1.0F, 2.0F, 2.0F, -0.25F, false);

		leaves = new ModelRenderer(this);
		leaves.setRotationPoint(0.0F, 0.0F, 0.0F);
		outer.addChild(leaves);
		

		leaf1 = new ModelRenderer(this);
		leaf1.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaves.addChild(leaf1);
		

		l1 = new ModelRenderer(this);
		l1.setRotationPoint(-4.25F, -22.5F, 4.25F);
		leaf1.addChild(l1);
		setRotationAngle(l1, 0.0F, -0.7854F, 0.0F);
		

		cube_r61 = new ModelRenderer(this);
		cube_r61.setRotationPoint(0.0F, 0.0F, 0.0F);
		l1.addChild(cube_r61);
		setRotationAngle(cube_r61, 0.5236F, 0.0F, 0.0F);
		cube_r61.setTextureOffset(25, 77).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 2.0F, -0.45F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -0.5F, 1.5F);
		l1.addChild(bone);
		bone.setTextureOffset(23, 82).addBox(-1.0F, -1.2013F, -0.8827F, 2.0F, 1.0F, 2.0F, -0.45F, false);

		leaf2 = new ModelRenderer(this);
		leaf2.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaves.addChild(leaf2);
		setRotationAngle(leaf2, 0.0F, 1.5708F, 0.0F);
		

		l2 = new ModelRenderer(this);
		l2.setRotationPoint(-4.25F, -22.5F, 4.25F);
		leaf2.addChild(l2);
		setRotationAngle(l2, 0.0F, -0.7854F, 0.0F);
		

		cube_r62 = new ModelRenderer(this);
		cube_r62.setRotationPoint(0.0F, 0.0F, 0.0F);
		l2.addChild(cube_r62);
		setRotationAngle(cube_r62, 0.5236F, 0.0F, 0.0F);
		cube_r62.setTextureOffset(23, 86).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 2.0F, -0.45F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -0.5F, 1.5F);
		l2.addChild(bone2);
		bone2.setTextureOffset(24, 92).addBox(-1.0F, -1.2013F, -0.8827F, 2.0F, 1.0F, 2.0F, -0.45F, false);

		leaf3 = new ModelRenderer(this);
		leaf3.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaves.addChild(leaf3);
		setRotationAngle(leaf3, 0.0F, -1.5708F, 0.0F);
		

		l3 = new ModelRenderer(this);
		l3.setRotationPoint(-4.25F, -22.5F, 4.25F);
		leaf3.addChild(l3);
		setRotationAngle(l3, 0.0F, -0.7854F, 0.0F);
		

		cube_r63 = new ModelRenderer(this);
		cube_r63.setRotationPoint(0.0F, 0.0F, 0.0F);
		l3.addChild(cube_r63);
		setRotationAngle(cube_r63, 0.5236F, 0.0F, 0.0F);
		cube_r63.setTextureOffset(24, 98).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 2.0F, -0.45F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -0.5F, 1.5F);
		l3.addChild(bone3);
		bone3.setTextureOffset(24, 104).addBox(-1.0F, -1.2013F, -0.8827F, 2.0F, 1.0F, 2.0F, -0.45F, false);

		leaf4 = new ModelRenderer(this);
		leaf4.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaves.addChild(leaf4);
		setRotationAngle(leaf4, 0.0F, 3.1416F, 0.0F);
		

		l4 = new ModelRenderer(this);
		l4.setRotationPoint(-4.25F, -22.5F, 4.25F);
		leaf4.addChild(l4);
		setRotationAngle(l4, 0.0F, -0.7854F, 0.0F);
		

		cube_r64 = new ModelRenderer(this);
		cube_r64.setRotationPoint(0.0F, 0.0F, 0.0F);
		l4.addChild(cube_r64);
		setRotationAngle(cube_r64, 0.5236F, 0.0F, 0.0F);
		cube_r64.setTextureOffset(37, 77).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 2.0F, -0.45F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -0.5F, 1.5F);
		l4.addChild(bone4);
		bone4.setTextureOffset(35, 82).addBox(-1.0F, -1.2013F, -0.8827F, 2.0F, 1.0F, 2.0F, -0.45F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 6.0F, 0.0F);
		body.addChild(tail);
		tail.setTextureOffset(49, 76).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, -0.75F, false);
		tail.setTextureOffset(45, 88).addBox(-1.0F, 1.2474F, -2.0557F, 2.0F, 2.0F, 2.0F, -0.75F, false);

		cube_r65 = new ModelRenderer(this);
		cube_r65.setRotationPoint(0.0F, 4.1305F, -0.6718F);
		tail.addChild(cube_r65);
		setRotationAngle(cube_r65, -0.6109F, 0.0F, 0.0F);
		cube_r65.setTextureOffset(45, 94).addBox(-1.0F, -2.8192F, -1.5736F, 2.0F, 2.0F, 2.0F, -0.75F, false);

		cube_r66 = new ModelRenderer(this);
		cube_r66.setRotationPoint(0.0F, 4.0681F, -1.2443F);
		tail.addChild(cube_r66);
		setRotationAngle(cube_r66, -1.0472F, 0.0F, 0.0F);
		cube_r66.setTextureOffset(35, 102).addBox(-1.0F, -2.5F, -1.866F, 2.0F, 2.0F, 2.0F, -0.75F, false);

		cube_r67 = new ModelRenderer(this);
		cube_r67.setRotationPoint(0.0F, 2.5516F, -1.6933F);
		tail.addChild(cube_r67);
		setRotationAngle(cube_r67, -1.6581F, 0.0F, 0.0F);
		cube_r67.setTextureOffset(36, 94).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.75F, false);

		cube_r68 = new ModelRenderer(this);
		cube_r68.setRotationPoint(0.0F, 1.0382F, -2.3037F);
		tail.addChild(cube_r68);
		setRotationAngle(cube_r68, 0.7418F, 0.0F, 0.0F);
		cube_r68.setTextureOffset(34, 88).addBox(-1.0F, 1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.75F, false);

		face = new ModelRenderer(this);
		face.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(face);
		face.setTextureOffset(77, 79).addBox(-5.5F, -7.0F, 1.0F, 1.0F, 4.0F, 2.0F, -0.45F, false);
		face.setTextureOffset(58, 101).addBox(4.5F, -7.0F, 1.0F, 1.0F, 4.0F, 2.0F, -0.45F, false);
	}

	@Override
	public void setRotationAngles(CobCannonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.isPlayerRiding()) {
			this.wheel1.rotateAngleX = ageInTicks / 2;
			this.wheel2.rotateAngleX = ageInTicks / 2;
			this.wheel3.rotateAngleX = ageInTicks / 2;
			this.wheel4.rotateAngleX = ageInTicks / 2;
		} else {
			this.wheel1.rotateAngleX = 0;
			this.wheel2.rotateAngleX = 0;
			this.wheel3.rotateAngleX = 0;
			this.wheel4.rotateAngleX = 0;
		}
		if(entity.getAttackTime() > 0) {
			int time = entity.getAnimCD() - entity.getAttackTime() + 1;
			this.body.rotateAngleX = 1.5708F - AnimationUtil.getUpDown(time, entity.getAnimCD(), 90);
			this.corn.showModel = (time < entity.getAnimCD() / 2);
		} else {
			this.corn.showModel = (entity.getCornNum() > 0);
			this.body.rotateAngleX = 1.5708F;
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