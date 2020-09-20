package com.hungteen.pvz.model.entity.misc;

import com.hungteen.pvz.entity.zombie.poolday.BobsleTeamEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BobsleTeamModel extends EntityModel<BobsleTeamEntity> {
	private final ModelRenderer total;
	private final ModelRenderer z1;
	private final ModelRenderer head;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer z2;
	private final ModelRenderer head2;
	private final ModelRenderer right_hand2;
	private final ModelRenderer left_hand2;
	private final ModelRenderer body2;
	private final ModelRenderer right_leg2;
	private final ModelRenderer left_leg2;
	private final ModelRenderer mid;
	private final ModelRenderer left;
	private final ModelRenderer right;
	private final ModelRenderer z3;
	private final ModelRenderer head3;
	private final ModelRenderer right_hand3;
	private final ModelRenderer left_hand3;
	private final ModelRenderer body3;
	private final ModelRenderer right_leg3;
	private final ModelRenderer left_leg3;
	private final ModelRenderer z4;
	private final ModelRenderer head4;
	private final ModelRenderer right_hand4;
	private final ModelRenderer left_hand4;
	private final ModelRenderer body4;
	private final ModelRenderer right_leg4;
	private final ModelRenderer left_leg4;
	private final ModelRenderer Head;
	private final ModelRenderer wheal;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer wheal2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer wheal3;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer wheal4;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer tail;

	public BobsleTeamModel() {
		textureWidth = 512;
		textureHeight = 512;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 2.0F, -8.0F);
		

		z1 = new ModelRenderer(this);
		z1.setRotationPoint(0.0F, 4.0F, 29.0F);
		total.addChild(z1);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -23.0F, 0.0F);
		z1.addChild(head);
		setRotationAngle(head, 0.0F, -0.3491F, 0.0F);
		head.setTextureOffset(3, 192).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head.setTextureOffset(109, 234).addBox(-7.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(59, 199).addBox(-7.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(100, 201).addBox(6.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(142, 200).addBox(6.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(187, 214).addBox(-6.0F, -6.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(222, 217).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(181, 192).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(222, 198).addBox(-5.0F, -11.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(134, 187).addBox(-5.0F, -5.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-9.0F, -21.0F, 0.0F);
		z1.addChild(right_hand);
		right_hand.setTextureOffset(77, 224).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(10.0F, -21.0F, 0.0F);
		z1.addChild(left_hand);
		left_hand.setTextureOffset(54, 227).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -6.0F, 0.0F);
		z1.addChild(body);
		body.setTextureOffset(5, 224).addBox(-8.0F, -17.0F, -3.0F, 16.0F, 23.0F, 6.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		z1.addChild(right_leg);
		setRotationAngle(right_leg, -1.309F, -0.7854F, 0.0F);
		right_leg.setTextureOffset(184, 229).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		right_leg.setTextureOffset(149, 238).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 0.0F, 0.0F);
		z1.addChild(left_leg);
		setRotationAngle(left_leg, -1.309F, 0.7854F, 0.0F);
		left_leg.setTextureOffset(238, 228).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		left_leg.setTextureOffset(203, 240).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		z2 = new ModelRenderer(this);
		z2.setRotationPoint(0.0F, 4.0F, 49.0F);
		total.addChild(z2);
		

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, -23.0F, 0.0F);
		z2.addChild(head2);
		setRotationAngle(head2, 0.0F, 0.6109F, 0.0F);
		head2.setTextureOffset(3, 192).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head2.setTextureOffset(109, 234).addBox(-7.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head2.setTextureOffset(59, 199).addBox(-7.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head2.setTextureOffset(100, 201).addBox(6.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head2.setTextureOffset(142, 200).addBox(6.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head2.setTextureOffset(187, 214).addBox(-6.0F, -6.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head2.setTextureOffset(222, 217).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head2.setTextureOffset(181, 192).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		head2.setTextureOffset(222, 198).addBox(-5.0F, -11.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		head2.setTextureOffset(134, 187).addBox(-5.0F, -5.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand2 = new ModelRenderer(this);
		right_hand2.setRotationPoint(-9.0F, -21.0F, 0.0F);
		z2.addChild(right_hand2);
		right_hand2.setTextureOffset(77, 224).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		left_hand2 = new ModelRenderer(this);
		left_hand2.setRotationPoint(10.0F, -21.0F, 0.0F);
		z2.addChild(left_hand2);
		left_hand2.setTextureOffset(54, 227).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, -6.0F, 0.0F);
		z2.addChild(body2);
		body2.setTextureOffset(5, 224).addBox(-8.0F, -17.0F, -3.0F, 16.0F, 23.0F, 6.0F, 0.0F, false);

		right_leg2 = new ModelRenderer(this);
		right_leg2.setRotationPoint(-4.0F, 0.0F, 0.0F);
		z2.addChild(right_leg2);
		setRotationAngle(right_leg2, -1.309F, -0.7854F, 0.0F);
		right_leg2.setTextureOffset(184, 229).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		right_leg2.setTextureOffset(149, 238).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		left_leg2 = new ModelRenderer(this);
		left_leg2.setRotationPoint(4.0F, 0.0F, 0.0F);
		z2.addChild(left_leg2);
		setRotationAngle(left_leg2, -1.309F, 0.7854F, 0.0F);
		left_leg2.setTextureOffset(238, 228).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		left_leg2.setTextureOffset(203, 240).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		mid = new ModelRenderer(this);
		mid.setRotationPoint(0.0F, 8.0F, 29.0F);
		total.addChild(mid);
		mid.setTextureOffset(284, 421).addBox(-14.0F, 4.0F, -17.0F, 28.0F, 1.0F, 84.0F, 0.0F, false);
		mid.setTextureOffset(459, 402).addBox(-8.0F, -4.0F, -3.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.setTextureOffset(402, 399).addBox(-8.0F, -4.0F, 17.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.setTextureOffset(463, 372).addBox(-8.0F, -4.0F, 37.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.setTextureOffset(402, 368).addBox(-8.0F, -4.0F, 57.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.setTextureOffset(440, 350).addBox(-14.0F, -2.0F, 67.0F, 28.0F, 7.0F, 1.0F, 0.0F, false);

		left = new ModelRenderer(this);
		left.setRotationPoint(13.0F, 4.0F, 24.0F);
		mid.addChild(left);
		left.setTextureOffset(9, 399).addBox(1.0F, -24.0F, -41.0F, 1.0F, 25.0F, 84.0F, 0.0F, false);

		right = new ModelRenderer(this);
		right.setRotationPoint(-13.0F, 4.0F, 25.0F);
		mid.addChild(right);
		right.setTextureOffset(336, 7).addBox(-2.0F, -24.0F, -42.0F, 1.0F, 25.0F, 84.0F, 0.0F, false);

		z3 = new ModelRenderer(this);
		z3.setRotationPoint(0.0F, 4.0F, 69.0F);
		total.addChild(z3);
		

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(0.0F, -23.0F, 0.0F);
		z3.addChild(head3);
		setRotationAngle(head3, -0.3491F, 0.0F, 0.0F);
		head3.setTextureOffset(3, 192).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head3.setTextureOffset(109, 234).addBox(-7.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head3.setTextureOffset(59, 199).addBox(-7.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head3.setTextureOffset(100, 201).addBox(6.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head3.setTextureOffset(142, 200).addBox(6.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head3.setTextureOffset(187, 214).addBox(-6.0F, -6.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head3.setTextureOffset(222, 217).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head3.setTextureOffset(181, 192).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		head3.setTextureOffset(222, 198).addBox(-5.0F, -11.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		head3.setTextureOffset(134, 187).addBox(-5.0F, -5.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand3 = new ModelRenderer(this);
		right_hand3.setRotationPoint(-9.0F, -21.0F, 0.0F);
		z3.addChild(right_hand3);
		setRotationAngle(right_hand3, -1.309F, 0.1745F, 0.0F);
		right_hand3.setTextureOffset(77, 224).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		left_hand3 = new ModelRenderer(this);
		left_hand3.setRotationPoint(10.0F, -21.0F, 0.0F);
		z3.addChild(left_hand3);
		left_hand3.setTextureOffset(54, 227).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		body3 = new ModelRenderer(this);
		body3.setRotationPoint(0.0F, -6.0F, 0.0F);
		z3.addChild(body3);
		body3.setTextureOffset(5, 224).addBox(-8.0F, -17.0F, -3.0F, 16.0F, 23.0F, 6.0F, 0.0F, false);

		right_leg3 = new ModelRenderer(this);
		right_leg3.setRotationPoint(-4.0F, 0.0F, 0.0F);
		z3.addChild(right_leg3);
		setRotationAngle(right_leg3, -1.309F, -0.7854F, 0.0F);
		right_leg3.setTextureOffset(184, 229).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		right_leg3.setTextureOffset(149, 238).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		left_leg3 = new ModelRenderer(this);
		left_leg3.setRotationPoint(4.0F, 0.0F, 0.0F);
		z3.addChild(left_leg3);
		setRotationAngle(left_leg3, -1.309F, 0.7854F, 0.0F);
		left_leg3.setTextureOffset(238, 228).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		left_leg3.setTextureOffset(203, 240).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		z4 = new ModelRenderer(this);
		z4.setRotationPoint(0.0F, 4.0F, 89.0F);
		total.addChild(z4);
		

		head4 = new ModelRenderer(this);
		head4.setRotationPoint(0.0F, -23.0F, 0.0F);
		z4.addChild(head4);
		head4.setTextureOffset(3, 192).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head4.setTextureOffset(109, 234).addBox(-7.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head4.setTextureOffset(59, 199).addBox(-7.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head4.setTextureOffset(100, 201).addBox(6.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head4.setTextureOffset(142, 200).addBox(6.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head4.setTextureOffset(187, 214).addBox(-6.0F, -6.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head4.setTextureOffset(222, 217).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head4.setTextureOffset(181, 192).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		head4.setTextureOffset(222, 198).addBox(-5.0F, -11.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		head4.setTextureOffset(134, 187).addBox(-5.0F, -5.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand4 = new ModelRenderer(this);
		right_hand4.setRotationPoint(-9.0F, -21.0F, 0.0F);
		z4.addChild(right_hand4);
		right_hand4.setTextureOffset(77, 224).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		left_hand4 = new ModelRenderer(this);
		left_hand4.setRotationPoint(10.0F, -21.0F, 0.0F);
		z4.addChild(left_hand4);
		setRotationAngle(left_hand4, -2.0071F, 0.0F, 0.0F);
		left_hand4.setTextureOffset(54, 227).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		body4 = new ModelRenderer(this);
		body4.setRotationPoint(0.0F, -6.0F, 0.0F);
		z4.addChild(body4);
		body4.setTextureOffset(5, 224).addBox(-8.0F, -17.0F, -3.0F, 16.0F, 23.0F, 6.0F, 0.0F, false);

		right_leg4 = new ModelRenderer(this);
		right_leg4.setRotationPoint(-4.0F, 0.0F, 0.0F);
		z4.addChild(right_leg4);
		setRotationAngle(right_leg4, -1.309F, -0.7854F, 0.0F);
		right_leg4.setTextureOffset(184, 229).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		right_leg4.setTextureOffset(149, 238).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		left_leg4 = new ModelRenderer(this);
		left_leg4.setRotationPoint(4.0F, 0.0F, 0.0F);
		z4.addChild(left_leg4);
		setRotationAngle(left_leg4, -1.309F, 0.7854F, 0.0F);
		left_leg4.setTextureOffset(238, 228).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		left_leg4.setTextureOffset(203, 240).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 13.0F, 4.0F);
		total.addChild(Head);
		Head.setTextureOffset(188, 449).addBox(-15.0F, -25.0F, -12.0F, 30.0F, 25.0F, 20.0F, 0.0F, false);
		Head.setTextureOffset(129, 436).addBox(-13.0F, -15.0F, -17.0F, 26.0F, 15.0F, 5.0F, 0.0F, false);
		Head.setTextureOffset(13, 378).addBox(-10.0F, -7.0F, -22.0F, 20.0F, 7.0F, 5.0F, 0.0F, false);
		Head.setTextureOffset(11, 340).addBox(-15.0F, -27.0F, -2.0F, 30.0F, 2.0F, 21.0F, 0.0F, false);

		wheal = new ModelRenderer(this);
		wheal.setRotationPoint(13.0F, 16.0F, 6.0F);
		total.addChild(wheal);
		wheal.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.setTextureOffset(0, 0).addBox(-2.0F, 1.0F, 9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.setTextureOffset(0, 0).addBox(-2.0F, 1.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.setTextureOffset(0, 0).addBox(-2.0F, 1.0F, -9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.setTextureOffset(0, 0).addBox(-1.0F, 3.0F, -11.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 3.0F, -11.0F);
		wheal.addChild(bone);
		setRotationAngle(bone, -0.6981F, 0.0F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 3.0F, 14.0F);
		wheal.addChild(bone2);
		setRotationAngle(bone2, 0.6981F, 0.0F, 0.0F);
		bone2.setTextureOffset(0, 0).addBox(-1.0F, 0.6428F, -1.234F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		wheal2 = new ModelRenderer(this);
		wheal2.setRotationPoint(13.0F, 16.0F, 6.0F);
		total.addChild(wheal2);
		wheal2.setTextureOffset(0, 0).addBox(-27.0F, -1.0F, -6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal2.setTextureOffset(0, 0).addBox(-28.0F, -3.0F, -8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.setTextureOffset(0, 0).addBox(-28.0F, -3.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.setTextureOffset(0, 0).addBox(-28.0F, 1.0F, 9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.setTextureOffset(0, 0).addBox(-28.0F, 1.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.setTextureOffset(0, 0).addBox(-28.0F, 1.0F, -9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.setTextureOffset(0, 0).addBox(-27.0F, 3.0F, -11.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 3.0F, -11.0F);
		wheal2.addChild(bone3);
		setRotationAngle(bone3, -0.6981F, 0.0F, 0.0F);
		bone3.setTextureOffset(0, 0).addBox(-27.0F, 0.0F, -3.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 3.0F, 14.0F);
		wheal2.addChild(bone4);
		setRotationAngle(bone4, 0.6981F, 0.0F, 0.0F);
		bone4.setTextureOffset(0, 0).addBox(-27.0F, 0.6428F, -1.234F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		wheal3 = new ModelRenderer(this);
		wheal3.setRotationPoint(13.0F, 16.0F, 6.0F);
		total.addChild(wheal3);
		wheal3.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 70.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal3.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, 68.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, 84.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.setTextureOffset(0, 0).addBox(-2.0F, 1.0F, 85.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.setTextureOffset(0, 0).addBox(-2.0F, 1.0F, 76.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.setTextureOffset(0, 0).addBox(-2.0F, 1.0F, 67.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.setTextureOffset(0, 0).addBox(-1.0F, 3.0F, 65.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 3.0F, -11.0F);
		wheal3.addChild(bone5);
		setRotationAngle(bone5, -0.6981F, 0.0F, 0.0F);
		bone5.setTextureOffset(0, 0).addBox(-1.0F, -48.8519F, 55.2194F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 3.0F, 14.0F);
		wheal3.addChild(bone6);
		setRotationAngle(bone6, 0.6981F, 0.0F, 0.0F);
		bone6.setTextureOffset(0, 0).addBox(-1.0F, 49.4946F, 56.9854F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		wheal4 = new ModelRenderer(this);
		wheal4.setRotationPoint(13.0F, 16.0F, 81.0F);
		total.addChild(wheal4);
		wheal4.setTextureOffset(0, 0).addBox(-27.0F, -1.0F, -6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal4.setTextureOffset(0, 0).addBox(-28.0F, -3.0F, -8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.setTextureOffset(0, 0).addBox(-28.0F, -3.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.setTextureOffset(0, 0).addBox(-28.0F, 1.0F, 9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.setTextureOffset(0, 0).addBox(-28.0F, 1.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.setTextureOffset(0, 0).addBox(-28.0F, 1.0F, -9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.setTextureOffset(0, 0).addBox(-27.0F, 3.0F, -11.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 3.0F, -11.0F);
		wheal4.addChild(bone7);
		setRotationAngle(bone7, -0.6981F, 0.0F, 0.0F);
		bone7.setTextureOffset(0, 0).addBox(-27.0F, 0.0F, -3.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 3.0F, 14.0F);
		wheal4.addChild(bone8);
		setRotationAngle(bone8, 0.6981F, 0.0F, 0.0F);
		bone8.setTextureOffset(0, 0).addBox(-27.0F, 0.6428F, -1.234F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.0F, 96.0F);
		total.addChild(tail);
		setRotationAngle(tail, 0.4363F, 0.0F, 0.0F);
		tail.setTextureOffset(261, 382).addBox(14.0F, -11.0F, 0.0F, 1.0F, 11.0F, 18.0F, 0.0F, false);
		tail.setTextureOffset(184, 378).addBox(-15.0F, -11.0F, 0.0F, 1.0F, 11.0F, 18.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(BobsleTeamEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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