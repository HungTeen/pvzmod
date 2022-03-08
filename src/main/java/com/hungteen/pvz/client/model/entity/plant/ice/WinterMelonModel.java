package com.hungteen.pvz.client.model.entity.plant.ice;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.ice.WinterMelonEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class WinterMelonModel extends PVZPlantModel<WinterMelonEntity> {
	private final ModelRenderer total;
	private final ModelRenderer leaves;
	private final ModelRenderer leave1;
	private final ModelRenderer leave2;
	private final ModelRenderer leave3;
	private final ModelRenderer leave4;
	private final ModelRenderer body;
	private final ModelRenderer pult;
	private final ModelRenderer basket;
	private final ModelRenderer melon;
	private final ModelRenderer cube_r1;
	private final ModelRenderer ice;
	private final ModelRenderer ice1;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r2;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer bone5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer bone6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer bone7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer ice2;
	private final ModelRenderer bone13;
	private final ModelRenderer cube_r9;
	private final ModelRenderer bone14;
	private final ModelRenderer cube_r10;
	private final ModelRenderer bone15;
	private final ModelRenderer cube_r11;
	private final ModelRenderer bone16;
	private final ModelRenderer cube_r12;
	private final ModelRenderer bone17;
	private final ModelRenderer cube_r13;
	private final ModelRenderer bone18;
	private final ModelRenderer cube_r14;
	private final ModelRenderer bone19;
	private final ModelRenderer cube_r15;
	private final ModelRenderer ice3;
	private final ModelRenderer bone8;
	private final ModelRenderer cube_r16;
	private final ModelRenderer bone9;
	private final ModelRenderer cube_r17;
	private final ModelRenderer bone10;
	private final ModelRenderer cube_r18;
	private final ModelRenderer bone11;
	private final ModelRenderer cube_r19;
	private final ModelRenderer bone12;
	private final ModelRenderer cube_r20;
	private final ModelRenderer ice4;
	private final ModelRenderer bone20;
	private final ModelRenderer cube_r21;
	private final ModelRenderer bone21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer bone22;
	private final ModelRenderer cube_r23;
	private final ModelRenderer bone23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer bone24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer face;

	public WinterMelonModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 23.75F, 0.0F);
		

		leaves = new ModelRenderer(this);
		leaves.setPos(0.0F, 0.25F, 0.0F);
		total.addChild(leaves);
		

		leave1 = new ModelRenderer(this);
		leave1.setPos(-9.0F, 0.0F, -9.0F);
		leaves.addChild(leave1);
		leave1.texOffs(48, 97).addBox(0.0F, -1.0F, 2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		leave1.texOffs(60, 98).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, -0.1F, false);

		leave2 = new ModelRenderer(this);
		leave2.setPos(6.0F, 0.0F, -5.0F);
		leaves.addChild(leave2);
		leave2.texOffs(68, 97).addBox(0.0F, -1.0F, -2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		leave2.texOffs(78, 97).addBox(2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, -0.1F, false);

		leave3 = new ModelRenderer(this);
		leave3.setPos(-2.0F, 0.0F, 2.0F);
		leaves.addChild(leave3);
		leave3.texOffs(0, 85).addBox(-7.0F, -1.0F, 2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		leave3.texOffs(0, 82).addBox(-8.0F, -1.0F, 4.0F, 2.0F, 1.0F, 2.0F, -0.1F, false);

		leave4 = new ModelRenderer(this);
		leave4.setPos(2.0F, 0.0F, 2.0F);
		leaves.addChild(leave4);
		leave4.texOffs(8, 80).addBox(4.0F, -1.0F, 2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		leave4.texOffs(0, 79).addBox(6.0F, -1.0F, 4.0F, 2.0F, 1.0F, 2.0F, -0.1F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 1.0F, 0.0F);
		total.addChild(body);
		body.texOffs(0, 106).addBox(-8.0F, -11.0F, -6.0F, 16.0F, 10.0F, 12.0F, -0.2F, false);
		body.texOffs(100, 112).addBox(-6.5F, -9.5F, -6.25F, 13.0F, 7.0F, 1.0F, -0.2F, false);
		body.texOffs(56, 112).addBox(-8.25F, -9.5F, -4.5F, 1.0F, 7.0F, 9.0F, -0.2F, false);
		body.texOffs(76, 112).addBox(7.25F, -9.5F, -4.5F, 1.0F, 7.0F, 9.0F, -0.2F, false);
		body.texOffs(100, 120).addBox(-6.5F, -9.5F, 5.25F, 13.0F, 7.0F, 1.0F, -0.2F, false);
		body.texOffs(84, 102).addBox(-6.5F, -11.25F, -4.5F, 13.0F, 1.0F, 9.0F, -0.2F, false);
		body.texOffs(49, 101).addBox(-6.5F, -1.75F, -4.5F, 13.0F, 1.0F, 9.0F, -0.2F, false);

		pult = new ModelRenderer(this);
		pult.setPos(0.0F, -6.5F, 6.0F);
		total.addChild(pult);
		pult.texOffs(102, 80).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 2.0F, 11.0F, -0.2F, false);
		pult.texOffs(75, 89).addBox(-1.0F, -1.0F, 8.5F, 2.0F, 2.0F, 5.0F, -0.4F, false);

		basket = new ModelRenderer(this);
		basket.setPos(0.0F, 0.75F, 1.75F);
		pult.addChild(basket);
		basket.texOffs(96, 93).addBox(-4.0F, 0.0F, 12.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		basket.texOffs(0, 103).addBox(-4.0F, -2.0F, 11.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);
		basket.texOffs(0, 100).addBox(-4.0F, -2.0F, 20.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);
		basket.texOffs(34, 79).addBox(-5.0F, -2.0F, 11.0F, 1.0F, 2.0F, 10.0F, 0.0F, false);
		basket.texOffs(51, 83).addBox(4.0F, -2.0F, 11.0F, 1.0F, 2.0F, 10.0F, 0.0F, false);

		melon = new ModelRenderer(this);
		melon.setPos(-0.5F, 0.0F, 12.5F);
		basket.addChild(melon);
		setRotationAngle(melon, 0.3054F, 0.0F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 0.0F, 0.0F);
		melon.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.0873F, 0.0F, 0.0F);
		cube_r1.texOffs(10, 82).addBox(-3.0F, -6.0541F, -0.2441F, 7.0F, 6.0F, 10.0F, -0.3F, false);

		ice = new ModelRenderer(this);
		ice.setPos(0.0F, 0.0F, 15.5F);
		basket.addChild(ice);
		

		ice1 = new ModelRenderer(this);
		ice1.setPos(0.0F, 0.0F, 0.0F);
		ice.addChild(ice1);
		

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 5.0F);
		ice1.addChild(bone);
		bone.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.5F, 1.0F, 0.0F);
		bone.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.7854F, 0.0F, 0.0F);
		cube_r2.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(-4.5F, 0.0F, 5.0F);
		ice1.addChild(bone2);
		bone2.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.5F, 1.0F, 0.0F);
		bone2.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.7854F, 0.0F, 0.0F);
		cube_r3.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 5.0F);
		ice1.addChild(bone3);
		bone3.texOffs(0, 76).addBox(4.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(5.0F, 1.0F, 0.0F);
		bone3.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.7854F, 0.0F, 0.0F);
		cube_r4.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(-3.0F, 0.0F, 5.0F);
		ice1.addChild(bone4);
		bone4.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(0.5F, 1.0F, 0.0F);
		bone4.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.7854F, 0.0F, 0.0F);
		cube_r5.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-1.5F, 0.0F, 5.0F);
		ice1.addChild(bone5);
		bone5.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(0.5F, 1.0F, 0.0F);
		bone5.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.7854F, 0.0F, 0.0F);
		cube_r6.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(1.5F, 0.0F, 5.0F);
		ice1.addChild(bone6);
		bone6.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(0.5F, 1.0F, 0.0F);
		bone6.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.7854F, 0.0F, 0.0F);
		cube_r7.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(3.0F, 0.0F, 5.0F);
		ice1.addChild(bone7);
		bone7.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(0.5F, 1.0F, 0.0F);
		bone7.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.7854F, 0.0F, 0.0F);
		cube_r8.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		ice2 = new ModelRenderer(this);
		ice2.setPos(0.0F, 0.0F, -9.0F);
		ice.addChild(ice2);
		

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, 0.0F, 5.0F);
		ice2.addChild(bone13);
		bone13.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(0.5F, 1.0F, 0.0F);
		bone13.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.7854F, 0.0F, 0.0F);
		cube_r9.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(-4.5F, 0.0F, 5.0F);
		ice2.addChild(bone14);
		bone14.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(0.5F, 1.0F, 0.0F);
		bone14.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.7854F, 0.0F, 0.0F);
		cube_r10.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(0.0F, 0.0F, 5.0F);
		ice2.addChild(bone15);
		bone15.texOffs(0, 76).addBox(4.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(5.0F, 1.0F, 0.0F);
		bone15.addChild(cube_r11);
		setRotationAngle(cube_r11, -0.7854F, 0.0F, 0.0F);
		cube_r11.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone16 = new ModelRenderer(this);
		bone16.setPos(-3.0F, 0.0F, 5.0F);
		ice2.addChild(bone16);
		bone16.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(0.5F, 1.0F, 0.0F);
		bone16.addChild(cube_r12);
		setRotationAngle(cube_r12, -0.7854F, 0.0F, 0.0F);
		cube_r12.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(-1.5F, 0.0F, 5.0F);
		ice2.addChild(bone17);
		bone17.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(0.5F, 1.0F, 0.0F);
		bone17.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.7854F, 0.0F, 0.0F);
		cube_r13.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(1.5F, 0.0F, 5.0F);
		ice2.addChild(bone18);
		bone18.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(0.5F, 1.0F, 0.0F);
		bone18.addChild(cube_r14);
		setRotationAngle(cube_r14, -0.7854F, 0.0F, 0.0F);
		cube_r14.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone19 = new ModelRenderer(this);
		bone19.setPos(3.0F, 0.0F, 5.0F);
		ice2.addChild(bone19);
		bone19.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(0.5F, 1.0F, 0.0F);
		bone19.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.7854F, 0.0F, 0.0F);
		cube_r15.texOffs(0, 74).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		ice3 = new ModelRenderer(this);
		ice3.setPos(0.0F, 0.0F, 0.0F);
		ice.addChild(ice3);
		

		bone8 = new ModelRenderer(this);
		bone8.setPos(-4.5F, 0.0F, 3.5F);
		ice3.addChild(bone8);
		bone8.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(0.0F, 1.0F, 0.0F);
		bone8.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, 0.7854F);
		cube_r16.texOffs(0, 74).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(-4.5F, 0.0F, 2.0F);
		ice3.addChild(bone9);
		bone9.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(0.0F, 1.0F, 0.0F);
		bone9.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.0F, 0.7854F);
		cube_r17.texOffs(0, 74).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(-4.5F, 0.0F, 0.5F);
		ice3.addChild(bone10);
		bone10.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(0.0F, 1.0F, 0.0F);
		bone10.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, 0.0F, 0.7854F);
		cube_r18.texOffs(0, 74).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(-4.5F, 0.0F, -1.0F);
		ice3.addChild(bone11);
		bone11.texOffs(0, 76).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(0.0F, 1.0F, 0.0F);
		bone11.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0F, 0.0F, 0.7854F);
		cube_r19.texOffs(0, 74).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, 0.0F, 0.0F);
		bone11.addChild(bone12);
		bone12.texOffs(0, 76).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(0.0F, 1.0F, -1.5F);
		bone12.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 0.0F, 0.7854F);
		cube_r20.texOffs(0, 74).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		ice4 = new ModelRenderer(this);
		ice4.setPos(0.0F, 0.0F, 0.0F);
		ice.addChild(ice4);
		

		bone20 = new ModelRenderer(this);
		bone20.setPos(-4.5F, 0.0F, 3.5F);
		ice4.addChild(bone20);
		bone20.texOffs(0, 76).addBox(8.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(9.0F, 1.0F, 0.0F);
		bone20.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0F, 0.0F, 0.7854F);
		cube_r21.texOffs(0, 74).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone21 = new ModelRenderer(this);
		bone21.setPos(-4.5F, 0.0F, 2.0F);
		ice4.addChild(bone21);
		bone21.texOffs(0, 76).addBox(8.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(9.0F, 1.0F, 0.0F);
		bone21.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, 0.0F, 0.7854F);
		cube_r22.texOffs(0, 74).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone22 = new ModelRenderer(this);
		bone22.setPos(-4.5F, 0.0F, 0.5F);
		ice4.addChild(bone22);
		bone22.texOffs(0, 76).addBox(8.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(9.0F, 1.0F, 0.0F);
		bone22.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0F, 0.0F, 0.7854F);
		cube_r23.texOffs(0, 74).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone23 = new ModelRenderer(this);
		bone23.setPos(-4.5F, 0.0F, -1.0F);
		ice4.addChild(bone23);
		bone23.texOffs(0, 76).addBox(8.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setPos(9.0F, 1.0F, 0.0F);
		bone23.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.0F, 0.0F, 0.7854F);
		cube_r24.texOffs(0, 74).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		bone24 = new ModelRenderer(this);
		bone24.setPos(0.0F, 0.0F, 0.0F);
		bone23.addChild(bone24);
		bone24.texOffs(0, 76).addBox(8.5F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setPos(9.0F, 1.0F, -1.5F);
		bone24.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0F, 0.0F, 0.7854F);
		cube_r25.texOffs(0, 74).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, -0.05F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, 0.75F, -2.25F);
		total.addChild(face);
		face.texOffs(0, 114).addBox(-4.0F, -7.5F, -4.3F, 2.0F, 3.0F, 1.0F, -0.45F, false);
		face.texOffs(0, 110).addBox(2.0F, -7.5F, -4.3F, 2.0F, 3.0F, 1.0F, -0.45F, false);
	}

	@Override
	public void setupAnim(WinterMelonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAttackTime() > 0) {
			float percent = 1 - entity.getAttackTime() * 1.0F / entity.getPultAnimTime();
			pult.xRot = (1F - MathHelper.abs(MathHelper.cos(percent * 3.14159F))) * 1.5F;
			this.melon.visible = (percent < 0.5);
		} else {
			pult.xRot = MathHelper.sin(ageInTicks / 10) / 8;
			this.melon.visible = true;
		}
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<WinterMelonEntity> getPlantModel() {
		return this;
	}
}