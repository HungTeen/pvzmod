package com.hungteen.pvz.model.entity.plant.spear;

import com.hungteen.pvz.entity.plant.spear.CactusEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CactusModel extends EntityModel<CactusEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer branch;
	private final ModelRenderer mouse;
	private final ModelRenderer hat;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer small1;
	private final ModelRenderer small2;
	private final ModelRenderer small3;
	private final ModelRenderer spikes3;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer right_hand;
	private final ModelRenderer spikes1;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer left_hand;
	private final ModelRenderer spikes2;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer body2;
	private final ModelRenderer spikes4;
	private final ModelRenderer bone23;
	private final ModelRenderer bone24;
	private final ModelRenderer bone25;
	private final ModelRenderer bone26;
	private final ModelRenderer bone27;
	private final ModelRenderer bone28;
	private final ModelRenderer body3;
	private final ModelRenderer spikes5;
	private final ModelRenderer bone22;
	private final ModelRenderer bone29;
	private final ModelRenderer bone30;
	private final ModelRenderer bone31;
	private final ModelRenderer bone32;
	private final ModelRenderer bone33;
	private final ModelRenderer body4;
	private final ModelRenderer spikes6;
	private final ModelRenderer bone34;
	private final ModelRenderer bone35;
	private final ModelRenderer bone36;
	private final ModelRenderer bone37;
	private final ModelRenderer bone38;
	private final ModelRenderer bone39;
	private final ModelRenderer body5;
	private final ModelRenderer spikes7;
	private final ModelRenderer bone40;
	private final ModelRenderer bone41;
	private final ModelRenderer bone42;
	private final ModelRenderer bone43;
	private final ModelRenderer bone44;
	private final ModelRenderer bone45;

	public CactusModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(47, 81).addBox(-4.0F, -28.0F, -5.5F, 4.0F, 4.0F, 1.0F, -0.4F, false);
		body.texOffs(16, 73).addBox(0.0F, -28.0F, -5.5F, 4.0F, 4.0F, 1.0F, -0.4F, false);

		branch = new ModelRenderer(this);
		branch.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(branch);
		branch.texOffs(0, 97).addBox(-4.0F, -31.0F, -5.0F, 8.0F, 30.0F, 1.0F, 0.0F, false);
		branch.texOffs(18, 88).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 32.0F, 8.0F, 0.0F, false);
		branch.texOffs(50, 97).addBox(-4.0F, -31.0F, 4.0F, 8.0F, 30.0F, 1.0F, 0.0F, false);
		branch.texOffs(68, 90).addBox(-5.0F, -31.0F, -4.0F, 1.0F, 30.0F, 8.0F, 0.0F, false);
		branch.texOffs(86, 90).addBox(4.0F, -31.0F, -4.0F, 1.0F, 30.0F, 8.0F, 0.0F, false);

		mouse = new ModelRenderer(this);
		mouse.setPos(0.0F, 0.5F, 3.0F);
		body.addChild(mouse);
		mouse.texOffs(0, 90).addBox(-1.5F, -22.5F, -11.5F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		mouse.texOffs(0, 82).addBox(-2.5F, -22.5F, -12.5F, 5.0F, 3.0F, 5.0F, -0.5F, false);
		mouse.texOffs(20, 78).addBox(-1.5F, -23.5F, -12.5F, 3.0F, 5.0F, 5.0F, -0.5F, false);
		mouse.texOffs(42, 89).addBox(-2.5F, -23.5F, -13.5F, 5.0F, 5.0F, 2.0F, -0.1F, false);
		mouse.texOffs(54, 83).addBox(-3.5F, -23.5F, -14.002F, 7.0F, 5.0F, 3.0F, -0.5F, false);
		mouse.texOffs(0, 72).addBox(-2.5F, -24.5F, -14.001F, 5.0F, 7.0F, 3.0F, -0.5F, false);
		mouse.texOffs(36, 83).addBox(-2.0F, -23.0F, -14.0F, 4.0F, 4.0F, 1.0F, -0.3F, false);

		hat = new ModelRenderer(this);
		hat.setPos(-0.25F, -32.75F, -0.5F);
		body.addChild(hat);
		hat.texOffs(116, 27).addBox(-1.25F, -2.0F, -1.25F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		hat.texOffs(98, 32).addBox(-1.25F, -5.0F, -1.25F, 3.0F, 4.0F, 3.0F, -0.5F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.75F, -4.0F, 0.75F);
		hat.addChild(cube_r1);
		setRotationAngle(cube_r1, -1.0472F, 0.0F, 0.0F);
		cube_r1.texOffs(116, 23).addBox(-1.5F, -1.5F, -0.5F, 2.0F, 2.0F, 1.0F, -0.1F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.75F, -2.0F, 1.75F);
		hat.addChild(cube_r2);
		setRotationAngle(cube_r2, -1.0472F, 0.0F, 0.0F);
		cube_r2.texOffs(104, 26).addBox(-2.0F, -1.5F, -0.5F, 3.0F, 2.0F, 1.0F, -0.1F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.75F, -4.0F, -0.25F);
		hat.addChild(cube_r3);
		setRotationAngle(cube_r3, 1.0908F, 0.0F, 0.0F);
		cube_r3.texOffs(102, 29).addBox(-1.5F, -1.5F, -0.5F, 2.0F, 2.0F, 1.0F, -0.1F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.75F, -2.0F, -1.25F);
		hat.addChild(cube_r4);
		setRotationAngle(cube_r4, 1.1345F, 0.0F, 0.0F);
		cube_r4.texOffs(93, 31).addBox(-2.0F, -1.5F, -0.5F, 3.0F, 2.0F, 1.0F, -0.1F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(-1.25F, -4.0F, -0.25F);
		hat.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.5672F);
		cube_r5.texOffs(120, 24).addBox(-0.7F, -1.0F, -0.5F, 2.0F, 1.0F, 2.0F, -0.1F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(-2.25F, -2.0F, -0.25F);
		hat.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.5672F);
		cube_r6.texOffs(109, 26).addBox(-0.7F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, -0.1F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(0.75F, -4.0F, 0.75F);
		hat.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -0.5672F);
		cube_r7.texOffs(108, 30).addBox(-0.5F, -0.5F, -1.5F, 2.0F, 1.0F, 2.0F, -0.1F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(1.75F, -2.0F, -0.25F);
		hat.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, -0.5672F);
		cube_r8.texOffs(88, 34).addBox(-0.5F, -0.5F, -1.0F, 2.0F, 1.0F, 3.0F, -0.1F, false);

		small1 = new ModelRenderer(this);
		small1.setPos(0.0F, -4.5F, 0.0F);
		hat.addChild(small1);
		setRotationAngle(small1, 0.2618F, 0.0F, 0.0F);
		small1.texOffs(32, 59).addBox(-0.25F, -2.0F, -0.75F, 1.0F, 3.0F, 1.0F, -0.3F, false);
		small1.texOffs(18, 67).addBox(-0.25F, -2.0F, -0.75F, 1.0F, 1.0F, 1.0F, -0.1F, false);

		small2 = new ModelRenderer(this);
		small2.setPos(0.0F, -4.5F, 0.25F);
		hat.addChild(small2);
		setRotationAngle(small2, -0.3927F, -0.4363F, 0.0F);
		small2.texOffs(37, 59).addBox(-0.75F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		small2.texOffs(42, 57).addBox(-0.75F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, -0.3F, false);

		small3 = new ModelRenderer(this);
		small3.setPos(0.5F, -4.5F, 0.5F);
		hat.addChild(small3);
		setRotationAngle(small3, -0.3927F, 0.3927F, 0.0F);
		small3.texOffs(49, 67).addBox(-0.25F, -2.0F, -0.25F, 1.0F, 1.0F, 1.0F, -0.1F, false);
		small3.texOffs(121, 20).addBox(-0.25F, -2.0F, -0.25F, 1.0F, 3.0F, 1.0F, -0.3F, false);

		spikes3 = new ModelRenderer(this);
		spikes3.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(spikes3);
		

		bone15 = new ModelRenderer(this);
		bone15.setPos(3.0F, -29.0F, -3.0F);
		spikes3.addChild(bone15);
		setRotationAngle(bone15, 0.5236F, 0.0F, 0.7854F);
		bone15.texOffs(31, 78).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone15.texOffs(35, 80).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone16 = new ModelRenderer(this);
		bone16.setPos(2.0F, -3.0F, -3.0F);
		spikes3.addChild(bone16);
		setRotationAngle(bone16, 0.7854F, 0.0F, 1.0472F);
		bone16.texOffs(0, 67).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone16.texOffs(4, 69).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(4.0F, -4.0F, 0.0F);
		spikes3.addChild(bone17);
		setRotationAngle(bone17, -0.2182F, 0.0F, 1.309F);
		bone17.texOffs(8, 67).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone17.texOffs(12, 69).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(-2.0F, -6.0F, -4.0F);
		spikes3.addChild(bone18);
		setRotationAngle(bone18, 2.1817F, -0.5236F, 1.309F);
		bone18.texOffs(26, 74).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone18.texOffs(16, 70).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone19 = new ModelRenderer(this);
		bone19.setPos(0.0F, -23.0F, 4.0F);
		spikes3.addChild(bone19);
		setRotationAngle(bone19, -1.309F, 0.0F, 0.2618F);
		bone19.texOffs(70, 91).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone19.texOffs(65, 92).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone20 = new ModelRenderer(this);
		bone20.setPos(0.0F, -10.0F, 4.0F);
		spikes3.addChild(bone20);
		setRotationAngle(bone20, -1.309F, 0.0F, -2.3126F);
		bone20.texOffs(57, 91).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone20.texOffs(39, 80).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone21 = new ModelRenderer(this);
		bone21.setPos(-4.0F, -5.0F, 0.0F);
		spikes3.addChild(bone21);
		setRotationAngle(bone21, 3.0107F, -0.5236F, 1.309F);
		bone21.texOffs(78, 93).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone21.texOffs(82, 95).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-5.0F, -17.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.texOffs(97, 65).addBox(-9.0F, -2.0F, -3.5F, 9.0F, 4.0F, 6.0F, 0.0F, false);
		right_hand.texOffs(94, 75).addBox(-10.0F, -3.0F, -3.5F, 11.0F, 6.0F, 6.0F, -0.5F, false);
		right_hand.texOffs(104, 108).addBox(-11.0F, -12.0F, -3.5F, 6.0F, 14.0F, 6.0F, -0.6F, false);
		right_hand.texOffs(104, 87).addBox(-11.0F, -13.0F, -3.5F, 6.0F, 15.0F, 6.0F, -1.0F, false);
		right_hand.texOffs(69, 61).addBox(-12.0F, -12.0F, -3.5F, 8.0F, 14.0F, 6.0F, -1.0F, false);

		spikes1 = new ModelRenderer(this);
		spikes1.setPos(0.0F, 0.0F, 0.0F);
		right_hand.addChild(spikes1);
		

		bone = new ModelRenderer(this);
		bone.setPos(-9.0F, -11.0F, 0.0F);
		spikes1.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.2182F);
		bone.texOffs(105, 109).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone.texOffs(105, 89).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(-10.0F, -7.0F, 1.0F);
		spikes1.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -1.1781F);
		bone2.texOffs(98, 92).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone2.texOffs(98, 88).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(-7.0F, -8.0F, -1.0F);
		spikes1.addChild(bone3);
		setRotationAngle(bone3, 0.7854F, 0.0F, 0.5236F);
		bone3.texOffs(87, 93).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone3.texOffs(89, 89).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(-10.0F, -3.0F, -1.0F);
		spikes1.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, -2.0944F);
		bone4.texOffs(83, 89).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone4.texOffs(78, 90).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-8.0F, -3.0F, -1.0F);
		spikes1.addChild(bone5);
		setRotationAngle(bone5, 0.2182F, 0.0F, -2.3998F);
		bone5.texOffs(90, 85).addBox(-4.8301F, -5.5F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone5.texOffs(90, 82).addBox(-4.8301F, -4.5F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(-7.0F, -3.0F, -1.0F);
		spikes1.addChild(bone6);
		setRotationAngle(bone6, -0.4363F, 0.0F, -2.6616F);
		bone6.texOffs(86, 82).addBox(-4.8301F, -6.5F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone6.texOffs(82, 86).addBox(-4.8301F, -4.5F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(-7.0F, -6.0F, 1.0F);
		spikes1.addChild(bone7);
		setRotationAngle(bone7, -0.7854F, 0.0F, 0.2618F);
		bone7.texOffs(82, 81).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone7.texOffs(78, 87).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(5.0F, -17.0F, -1.0F);
		total.addChild(left_hand);
		setRotationAngle(left_hand, 0.0F, 3.1416F, 0.0F);
		left_hand.texOffs(39, 70).addBox(-9.0F, -2.0F, -3.5F, 9.0F, 4.0F, 6.0F, 0.0F, false);
		left_hand.texOffs(94, 53).addBox(-10.0F, -3.0F, -3.5F, 11.0F, 6.0F, 6.0F, -0.5F, false);
		left_hand.texOffs(104, 33).addBox(-11.0F, -12.0F, -3.5F, 6.0F, 14.0F, 6.0F, -0.6F, false);
		left_hand.texOffs(75, 38).addBox(-11.0F, -13.0F, -3.5F, 6.0F, 15.0F, 6.0F, -1.0F, false);
		left_hand.texOffs(47, 46).addBox(-12.0F, -12.0F, -3.5F, 8.0F, 14.0F, 6.0F, -1.0F, false);

		spikes2 = new ModelRenderer(this);
		spikes2.setPos(0.0F, 0.0F, 0.0F);
		left_hand.addChild(spikes2);
		

		bone8 = new ModelRenderer(this);
		bone8.setPos(-8.0F, -10.0F, 1.0F);
		spikes2.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, 0.2618F);
		bone8.texOffs(39, 70).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone8.texOffs(34, 75).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(-10.0F, -8.0F, 0.0F);
		spikes2.addChild(bone9);
		setRotationAngle(bone9, -0.3927F, 0.0F, -1.0472F);
		bone9.texOffs(34, 70).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone9.texOffs(30, 71).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(-7.0F, -8.0F, -2.0F);
		spikes2.addChild(bone10);
		setRotationAngle(bone10, 0.7854F, 0.0F, 0.5236F);
		bone10.texOffs(43, 65).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone10.texOffs(38, 67).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(-5.0F, 0.0F, 1.0F);
		spikes2.addChild(bone11);
		setRotationAngle(bone11, -2.618F, 0.0F, -0.5236F);
		bone11.texOffs(26, 69).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone11.texOffs(21, 70).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(-8.0F, 0.0F, 1.0F);
		spikes2.addChild(bone12);
		setRotationAngle(bone12, -2.618F, 0.0F, 0.0F);
		bone12.texOffs(64, 69).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone12.texOffs(32, 68).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(-10.0F, 0.0F, -1.0F);
		spikes2.addChild(bone13);
		setRotationAngle(bone13, -2.0944F, -1.309F, 0.0F);
		bone13.texOffs(38, 62).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone13.texOffs(33, 64).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(-3.0F, 1.0F, -2.0F);
		spikes2.addChild(bone14);
		setRotationAngle(bone14, -1.309F, 2.8798F, 0.0F);
		bone14.texOffs(29, 64).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone14.texOffs(23, 66).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		body2 = new ModelRenderer(this);
		body2.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body2);
		body2.texOffs(2, 106).addBox(-5.0F, -1.0F, -4.0F, 10.0F, 10.0F, 8.0F, 0.0F, false);
		body2.texOffs(26, 110).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);
		body2.texOffs(24, 107).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);

		spikes4 = new ModelRenderer(this);
		spikes4.setPos(0.0F, 11.0F, 0.0F);
		body2.addChild(spikes4);
		setRotationAngle(spikes4, 0.0F, 0.3927F, 0.0F);
		

		bone23 = new ModelRenderer(this);
		bone23.setPos(2.0F, -3.0F, -3.0F);
		spikes4.addChild(bone23);
		setRotationAngle(bone23, 0.7854F, 0.0F, 1.0472F);
		bone23.texOffs(0, 67).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone23.texOffs(4, 69).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone24 = new ModelRenderer(this);
		bone24.setPos(4.0F, -4.0F, 0.0F);
		spikes4.addChild(bone24);
		setRotationAngle(bone24, -0.2182F, 0.0F, 1.309F);
		bone24.texOffs(8, 67).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone24.texOffs(12, 69).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone25 = new ModelRenderer(this);
		bone25.setPos(-2.0F, -6.0F, -4.0F);
		spikes4.addChild(bone25);
		setRotationAngle(bone25, 2.1817F, -0.5236F, 1.309F);
		bone25.texOffs(26, 74).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone25.texOffs(16, 70).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone26 = new ModelRenderer(this);
		bone26.setPos(0.0F, -23.0F, 4.0F);
		spikes4.addChild(bone26);
		setRotationAngle(bone26, -1.309F, 0.0F, 0.2618F);
		bone26.texOffs(70, 91).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone26.texOffs(65, 92).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone27 = new ModelRenderer(this);
		bone27.setPos(0.0F, -10.0F, 4.0F);
		spikes4.addChild(bone27);
		setRotationAngle(bone27, -1.309F, 0.0F, -2.3126F);
		bone27.texOffs(57, 91).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone27.texOffs(39, 80).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone28 = new ModelRenderer(this);
		bone28.setPos(-4.0F, -5.0F, 0.0F);
		spikes4.addChild(bone28);
		setRotationAngle(bone28, 3.0107F, -0.5236F, 1.309F);
		bone28.texOffs(78, 93).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone28.texOffs(82, 95).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		body3 = new ModelRenderer(this);
		body3.setPos(0.0F, 10.0F, 0.0F);
		total.addChild(body3);
		body3.texOffs(2, 106).addBox(-5.0F, -1.0F, -4.0F, 10.0F, 10.0F, 8.0F, 0.0F, false);
		body3.texOffs(26, 110).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);
		body3.texOffs(24, 107).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);

		spikes5 = new ModelRenderer(this);
		spikes5.setPos(0.0F, 11.0F, 0.0F);
		body3.addChild(spikes5);
		setRotationAngle(spikes5, 0.0F, -0.5672F, 0.0F);
		

		bone22 = new ModelRenderer(this);
		bone22.setPos(2.0F, -3.0F, -3.0F);
		spikes5.addChild(bone22);
		setRotationAngle(bone22, 0.7854F, 0.0F, 1.0472F);
		bone22.texOffs(0, 67).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone22.texOffs(4, 69).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone29 = new ModelRenderer(this);
		bone29.setPos(4.0F, -4.0F, 0.0F);
		spikes5.addChild(bone29);
		setRotationAngle(bone29, -0.2182F, 0.0F, 1.309F);
		bone29.texOffs(8, 67).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone29.texOffs(12, 69).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone30 = new ModelRenderer(this);
		bone30.setPos(-2.0F, -6.0F, -4.0F);
		spikes5.addChild(bone30);
		setRotationAngle(bone30, 2.1817F, -0.5236F, 1.309F);
		bone30.texOffs(26, 74).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone30.texOffs(16, 70).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone31 = new ModelRenderer(this);
		bone31.setPos(0.0F, -23.0F, 4.0F);
		spikes5.addChild(bone31);
		setRotationAngle(bone31, -1.309F, 0.0F, 0.2618F);
		bone31.texOffs(70, 91).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone31.texOffs(65, 92).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone32 = new ModelRenderer(this);
		bone32.setPos(0.0F, -10.0F, 4.0F);
		spikes5.addChild(bone32);
		setRotationAngle(bone32, -1.309F, 0.0F, -2.3126F);
		bone32.texOffs(57, 91).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone32.texOffs(39, 80).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone33 = new ModelRenderer(this);
		bone33.setPos(-4.0F, -5.0F, 0.0F);
		spikes5.addChild(bone33);
		setRotationAngle(bone33, 3.0107F, -0.5236F, 1.309F);
		bone33.texOffs(78, 93).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone33.texOffs(82, 95).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		body4 = new ModelRenderer(this);
		body4.setPos(0.0F, 20.0F, 0.0F);
		total.addChild(body4);
		body4.texOffs(2, 106).addBox(-5.0F, -1.0F, -4.0F, 10.0F, 10.0F, 8.0F, 0.0F, false);
		body4.texOffs(26, 110).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);
		body4.texOffs(24, 107).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);

		spikes6 = new ModelRenderer(this);
		spikes6.setPos(0.0F, 11.0F, 0.0F);
		body4.addChild(spikes6);
		setRotationAngle(spikes6, 0.0F, -0.9163F, 0.0F);
		

		bone34 = new ModelRenderer(this);
		bone34.setPos(2.0F, -3.0F, -3.0F);
		spikes6.addChild(bone34);
		setRotationAngle(bone34, 0.7854F, 0.0F, 1.0472F);
		bone34.texOffs(0, 67).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone34.texOffs(4, 69).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone35 = new ModelRenderer(this);
		bone35.setPos(4.0F, -4.0F, 0.0F);
		spikes6.addChild(bone35);
		setRotationAngle(bone35, -0.2182F, 0.0F, 1.309F);
		bone35.texOffs(8, 67).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone35.texOffs(12, 69).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone36 = new ModelRenderer(this);
		bone36.setPos(-2.0F, -6.0F, -4.0F);
		spikes6.addChild(bone36);
		setRotationAngle(bone36, 2.1817F, -0.5236F, 1.309F);
		bone36.texOffs(26, 74).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone36.texOffs(16, 70).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone37 = new ModelRenderer(this);
		bone37.setPos(0.0F, -23.0F, 4.0F);
		spikes6.addChild(bone37);
		setRotationAngle(bone37, -1.309F, 0.0F, 0.2618F);
		bone37.texOffs(70, 91).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone37.texOffs(65, 92).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone38 = new ModelRenderer(this);
		bone38.setPos(0.0F, -10.0F, 4.0F);
		spikes6.addChild(bone38);
		setRotationAngle(bone38, -1.309F, 0.0F, -2.3126F);
		bone38.texOffs(57, 91).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone38.texOffs(39, 80).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone39 = new ModelRenderer(this);
		bone39.setPos(-4.0F, -5.0F, 0.0F);
		spikes6.addChild(bone39);
		setRotationAngle(bone39, 3.0107F, -0.5236F, 1.309F);
		bone39.texOffs(78, 93).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone39.texOffs(82, 95).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		body5 = new ModelRenderer(this);
		body5.setPos(0.0F, 30.0F, 0.0F);
		total.addChild(body5);
		body5.texOffs(2, 106).addBox(-5.0F, -1.0F, -4.0F, 10.0F, 10.0F, 8.0F, 0.0F, false);
		body5.texOffs(26, 110).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);
		body5.texOffs(24, 107).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);

		spikes7 = new ModelRenderer(this);
		spikes7.setPos(0.0F, 11.0F, 0.0F);
		body5.addChild(spikes7);
		setRotationAngle(spikes7, 0.0F, -0.5672F, 0.0F);
		

		bone40 = new ModelRenderer(this);
		bone40.setPos(2.0F, -3.0F, -3.0F);
		spikes7.addChild(bone40);
		setRotationAngle(bone40, 0.7854F, 0.0F, 1.0472F);
		bone40.texOffs(0, 67).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone40.texOffs(4, 69).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone41 = new ModelRenderer(this);
		bone41.setPos(4.0F, -4.0F, 0.0F);
		spikes7.addChild(bone41);
		setRotationAngle(bone41, -0.2182F, 0.0F, 1.309F);
		bone41.texOffs(8, 67).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone41.texOffs(12, 69).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone42 = new ModelRenderer(this);
		bone42.setPos(-2.0F, -6.0F, -4.0F);
		spikes7.addChild(bone42);
		setRotationAngle(bone42, 2.1817F, -0.5236F, 1.309F);
		bone42.texOffs(26, 74).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone42.texOffs(16, 70).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone43 = new ModelRenderer(this);
		bone43.setPos(0.0F, -23.0F, 4.0F);
		spikes7.addChild(bone43);
		setRotationAngle(bone43, -1.309F, 0.0F, 0.2618F);
		bone43.texOffs(70, 91).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone43.texOffs(65, 92).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone44 = new ModelRenderer(this);
		bone44.setPos(0.0F, -10.0F, 4.0F);
		spikes7.addChild(bone44);
		setRotationAngle(bone44, -1.309F, 0.0F, -2.3126F);
		bone44.texOffs(57, 91).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone44.texOffs(39, 80).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);

		bone45 = new ModelRenderer(this);
		bone45.setPos(-4.0F, -5.0F, 0.0F);
		spikes7.addChild(bone45);
		setRotationAngle(bone45, 3.0107F, -0.5236F, 1.309F);
		bone45.texOffs(78, 93).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone45.texOffs(82, 95).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.1F, false);
	}

	@Override
	public void setupAnim(CactusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		float height = entity.getCactusHeight();
		this.body2.visible = true;
		this.body3.visible = height > CactusEntity.SEGMENT_HEIGHT;
		this.body4.visible = height > 2 * CactusEntity.SEGMENT_HEIGHT;
		this.body5.visible = height > 3 * CactusEntity.SEGMENT_HEIGHT;
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