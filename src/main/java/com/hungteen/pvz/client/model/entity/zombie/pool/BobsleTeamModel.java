package com.hungteen.pvz.client.model.entity.zombie.pool;

import com.hungteen.pvz.api.interfaces.IBodyEntity;
import com.hungteen.pvz.api.paz.IZombieModel;
import com.hungteen.pvz.client.model.entity.PVZEntityModel;
import com.hungteen.pvz.common.entity.zombie.pool.BobsleTeamEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BobsleTeamModel extends PVZEntityModel<BobsleTeamEntity> implements IZombieModel<BobsleTeamEntity>{
	
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
		texWidth = 512;
		texHeight = 512;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 2.0F, -8.0F);
		

		z1 = new ModelRenderer(this);
		z1.setPos(0.0F, 4.0F, 29.0F);
		total.addChild(z1);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, -23.0F, 0.0F);
		z1.addChild(head);
		setRotationAngle(head, 0.0F, -0.3491F, 0.0F);
		head.texOffs(3, 192).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head.texOffs(109, 234).addBox(-7.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.texOffs(59, 199).addBox(-7.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.texOffs(100, 201).addBox(6.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.texOffs(142, 200).addBox(6.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.texOffs(187, 214).addBox(-6.0F, -6.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(222, 217).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(181, 192).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		head.texOffs(222, 198).addBox(-5.0F, -11.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(134, 187).addBox(-5.0F, -5.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-9.0F, -21.0F, 0.0F);
		z1.addChild(right_hand);
		right_hand.texOffs(77, 224).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(10.0F, -21.0F, 0.0F);
		z1.addChild(left_hand);
		left_hand.texOffs(54, 227).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -6.0F, 0.0F);
		z1.addChild(body);
		body.texOffs(5, 224).addBox(-8.0F, -17.0F, -3.0F, 16.0F, 23.0F, 6.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, 0.0F, 0.0F);
		z1.addChild(right_leg);
		setRotationAngle(right_leg, -1.309F, -0.7854F, 0.0F);
		right_leg.texOffs(184, 229).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		right_leg.texOffs(149, 238).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, 0.0F, 0.0F);
		z1.addChild(left_leg);
		setRotationAngle(left_leg, -1.309F, 0.7854F, 0.0F);
		left_leg.texOffs(238, 228).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		left_leg.texOffs(203, 240).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		z2 = new ModelRenderer(this);
		z2.setPos(0.0F, 4.0F, 49.0F);
		total.addChild(z2);
		

		head2 = new ModelRenderer(this);
		head2.setPos(0.0F, -23.0F, 0.0F);
		z2.addChild(head2);
		setRotationAngle(head2, 0.0F, 0.6109F, 0.0F);
		head2.texOffs(3, 192).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head2.texOffs(109, 234).addBox(-7.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head2.texOffs(59, 199).addBox(-7.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head2.texOffs(100, 201).addBox(6.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head2.texOffs(142, 200).addBox(6.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head2.texOffs(187, 214).addBox(-6.0F, -6.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head2.texOffs(222, 217).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head2.texOffs(181, 192).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		head2.texOffs(222, 198).addBox(-5.0F, -11.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		head2.texOffs(134, 187).addBox(-5.0F, -5.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand2 = new ModelRenderer(this);
		right_hand2.setPos(-9.0F, -21.0F, 0.0F);
		z2.addChild(right_hand2);
		right_hand2.texOffs(77, 224).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		left_hand2 = new ModelRenderer(this);
		left_hand2.setPos(10.0F, -21.0F, 0.0F);
		z2.addChild(left_hand2);
		left_hand2.texOffs(54, 227).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setPos(0.0F, -6.0F, 0.0F);
		z2.addChild(body2);
		body2.texOffs(5, 224).addBox(-8.0F, -17.0F, -3.0F, 16.0F, 23.0F, 6.0F, 0.0F, false);

		right_leg2 = new ModelRenderer(this);
		right_leg2.setPos(-4.0F, 0.0F, 0.0F);
		z2.addChild(right_leg2);
		setRotationAngle(right_leg2, -1.309F, -0.7854F, 0.0F);
		right_leg2.texOffs(184, 229).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		right_leg2.texOffs(149, 238).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		left_leg2 = new ModelRenderer(this);
		left_leg2.setPos(4.0F, 0.0F, 0.0F);
		z2.addChild(left_leg2);
		setRotationAngle(left_leg2, -1.309F, 0.7854F, 0.0F);
		left_leg2.texOffs(238, 228).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		left_leg2.texOffs(203, 240).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		mid = new ModelRenderer(this);
		mid.setPos(0.0F, 8.0F, 29.0F);
		total.addChild(mid);
		mid.texOffs(284, 421).addBox(-14.0F, 4.0F, -17.0F, 28.0F, 1.0F, 84.0F, 0.0F, false);
		mid.texOffs(459, 402).addBox(-8.0F, -4.0F, -3.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.texOffs(402, 399).addBox(-8.0F, -4.0F, 17.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.texOffs(463, 372).addBox(-8.0F, -4.0F, 37.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.texOffs(402, 368).addBox(-8.0F, -4.0F, 57.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.texOffs(440, 350).addBox(-14.0F, -2.0F, 67.0F, 28.0F, 7.0F, 1.0F, 0.0F, false);

		left = new ModelRenderer(this);
		left.setPos(13.0F, 4.0F, 24.0F);
		mid.addChild(left);
		left.texOffs(9, 399).addBox(1.0F, -24.0F, -41.0F, 1.0F, 25.0F, 84.0F, 0.0F, false);

		right = new ModelRenderer(this);
		right.setPos(-13.0F, 4.0F, 25.0F);
		mid.addChild(right);
		right.texOffs(336, 7).addBox(-2.0F, -24.0F, -42.0F, 1.0F, 25.0F, 84.0F, 0.0F, false);

		z3 = new ModelRenderer(this);
		z3.setPos(0.0F, 4.0F, 69.0F);
		total.addChild(z3);
		

		head3 = new ModelRenderer(this);
		head3.setPos(0.0F, -23.0F, 0.0F);
		z3.addChild(head3);
		setRotationAngle(head3, -0.3491F, 0.0F, 0.0F);
		head3.texOffs(3, 192).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head3.texOffs(109, 234).addBox(-7.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head3.texOffs(59, 199).addBox(-7.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head3.texOffs(100, 201).addBox(6.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head3.texOffs(142, 200).addBox(6.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head3.texOffs(187, 214).addBox(-6.0F, -6.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head3.texOffs(222, 217).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head3.texOffs(181, 192).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		head3.texOffs(222, 198).addBox(-5.0F, -11.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		head3.texOffs(134, 187).addBox(-5.0F, -5.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand3 = new ModelRenderer(this);
		right_hand3.setPos(-9.0F, -21.0F, 0.0F);
		z3.addChild(right_hand3);
		setRotationAngle(right_hand3, -1.309F, 0.1745F, 0.0F);
		right_hand3.texOffs(77, 224).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		left_hand3 = new ModelRenderer(this);
		left_hand3.setPos(10.0F, -21.0F, 0.0F);
		z3.addChild(left_hand3);
		left_hand3.texOffs(54, 227).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		body3 = new ModelRenderer(this);
		body3.setPos(0.0F, -6.0F, 0.0F);
		z3.addChild(body3);
		body3.texOffs(5, 224).addBox(-8.0F, -17.0F, -3.0F, 16.0F, 23.0F, 6.0F, 0.0F, false);

		right_leg3 = new ModelRenderer(this);
		right_leg3.setPos(-4.0F, 0.0F, 0.0F);
		z3.addChild(right_leg3);
		setRotationAngle(right_leg3, -1.309F, -0.7854F, 0.0F);
		right_leg3.texOffs(184, 229).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		right_leg3.texOffs(149, 238).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		left_leg3 = new ModelRenderer(this);
		left_leg3.setPos(4.0F, 0.0F, 0.0F);
		z3.addChild(left_leg3);
		setRotationAngle(left_leg3, -1.309F, 0.7854F, 0.0F);
		left_leg3.texOffs(238, 228).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		left_leg3.texOffs(203, 240).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		z4 = new ModelRenderer(this);
		z4.setPos(0.0F, 4.0F, 89.0F);
		total.addChild(z4);
		

		head4 = new ModelRenderer(this);
		head4.setPos(0.0F, -23.0F, 0.0F);
		z4.addChild(head4);
		head4.texOffs(3, 192).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head4.texOffs(109, 234).addBox(-7.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head4.texOffs(59, 199).addBox(-7.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head4.texOffs(100, 201).addBox(6.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head4.texOffs(142, 200).addBox(6.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head4.texOffs(187, 214).addBox(-6.0F, -6.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head4.texOffs(222, 217).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head4.texOffs(181, 192).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		head4.texOffs(222, 198).addBox(-5.0F, -11.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		head4.texOffs(134, 187).addBox(-5.0F, -5.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand4 = new ModelRenderer(this);
		right_hand4.setPos(-9.0F, -21.0F, 0.0F);
		z4.addChild(right_hand4);
		right_hand4.texOffs(77, 224).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		left_hand4 = new ModelRenderer(this);
		left_hand4.setPos(10.0F, -21.0F, 0.0F);
		z4.addChild(left_hand4);
		setRotationAngle(left_hand4, -2.0071F, 0.0F, 0.0F);
		left_hand4.texOffs(54, 227).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		body4 = new ModelRenderer(this);
		body4.setPos(0.0F, -6.0F, 0.0F);
		z4.addChild(body4);
		body4.texOffs(5, 224).addBox(-8.0F, -17.0F, -3.0F, 16.0F, 23.0F, 6.0F, 0.0F, false);

		right_leg4 = new ModelRenderer(this);
		right_leg4.setPos(-4.0F, 0.0F, 0.0F);
		z4.addChild(right_leg4);
		setRotationAngle(right_leg4, -1.309F, -0.7854F, 0.0F);
		right_leg4.texOffs(184, 229).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		right_leg4.texOffs(149, 238).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		left_leg4 = new ModelRenderer(this);
		left_leg4.setPos(4.0F, 0.0F, 0.0F);
		z4.addChild(left_leg4);
		setRotationAngle(left_leg4, -1.309F, 0.7854F, 0.0F);
		left_leg4.texOffs(238, 228).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
		left_leg4.texOffs(203, 240).addBox(-3.0F, 17.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, 13.0F, 4.0F);
		total.addChild(Head);
		Head.texOffs(188, 449).addBox(-15.0F, -25.0F, -12.0F, 30.0F, 25.0F, 20.0F, 0.0F, false);
		Head.texOffs(129, 436).addBox(-13.0F, -15.0F, -17.0F, 26.0F, 15.0F, 5.0F, 0.0F, false);
		Head.texOffs(13, 378).addBox(-10.0F, -7.0F, -22.0F, 20.0F, 7.0F, 5.0F, 0.0F, false);
		Head.texOffs(11, 340).addBox(-15.0F, -27.0F, -2.0F, 30.0F, 2.0F, 21.0F, 0.0F, false);

		wheal = new ModelRenderer(this);
		wheal.setPos(13.0F, 16.0F, 6.0F);
		total.addChild(wheal);
		wheal.texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-2.0F, -3.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-2.0F, 1.0F, 9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-2.0F, 1.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-2.0F, 1.0F, -9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-1.0F, 3.0F, -11.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 3.0F, -11.0F);
		wheal.addChild(bone);
		setRotationAngle(bone, -0.6981F, 0.0F, 0.0F);
		bone.texOffs(0, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 3.0F, 14.0F);
		wheal.addChild(bone2);
		setRotationAngle(bone2, 0.6981F, 0.0F, 0.0F);
		bone2.texOffs(0, 0).addBox(-1.0F, 0.6428F, -1.234F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		wheal2 = new ModelRenderer(this);
		wheal2.setPos(13.0F, 16.0F, 6.0F);
		total.addChild(wheal2);
		wheal2.texOffs(0, 0).addBox(-27.0F, -1.0F, -6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-28.0F, -3.0F, -8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-28.0F, -3.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-28.0F, 1.0F, 9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-28.0F, 1.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-28.0F, 1.0F, -9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-27.0F, 3.0F, -11.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 3.0F, -11.0F);
		wheal2.addChild(bone3);
		setRotationAngle(bone3, -0.6981F, 0.0F, 0.0F);
		bone3.texOffs(0, 0).addBox(-27.0F, 0.0F, -3.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, 3.0F, 14.0F);
		wheal2.addChild(bone4);
		setRotationAngle(bone4, 0.6981F, 0.0F, 0.0F);
		bone4.texOffs(0, 0).addBox(-27.0F, 0.6428F, -1.234F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		wheal3 = new ModelRenderer(this);
		wheal3.setPos(13.0F, 16.0F, 6.0F);
		total.addChild(wheal3);
		wheal3.texOffs(0, 0).addBox(-1.0F, -1.0F, 70.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-2.0F, -3.0F, 68.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-2.0F, -3.0F, 84.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-2.0F, 1.0F, 85.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-2.0F, 1.0F, 76.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-2.0F, 1.0F, 67.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-1.0F, 3.0F, 65.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 3.0F, -11.0F);
		wheal3.addChild(bone5);
		setRotationAngle(bone5, -0.6981F, 0.0F, 0.0F);
		bone5.texOffs(0, 0).addBox(-1.0F, -48.8519F, 55.2194F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 3.0F, 14.0F);
		wheal3.addChild(bone6);
		setRotationAngle(bone6, 0.6981F, 0.0F, 0.0F);
		bone6.texOffs(0, 0).addBox(-1.0F, 49.4946F, 56.9854F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		wheal4 = new ModelRenderer(this);
		wheal4.setPos(13.0F, 16.0F, 81.0F);
		total.addChild(wheal4);
		wheal4.texOffs(0, 0).addBox(-27.0F, -1.0F, -6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-28.0F, -3.0F, -8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-28.0F, -3.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-28.0F, 1.0F, 9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-28.0F, 1.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-28.0F, 1.0F, -9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-27.0F, 3.0F, -11.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, 3.0F, -11.0F);
		wheal4.addChild(bone7);
		setRotationAngle(bone7, -0.6981F, 0.0F, 0.0F);
		bone7.texOffs(0, 0).addBox(-27.0F, 0.0F, -3.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, 3.0F, 14.0F);
		wheal4.addChild(bone8);
		setRotationAngle(bone8, 0.6981F, 0.0F, 0.0F);
		bone8.texOffs(0, 0).addBox(-27.0F, 0.6428F, -1.234F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, 0.0F, 96.0F);
		total.addChild(tail);
		setRotationAngle(tail, 0.4363F, 0.0F, 0.0F);
		tail.texOffs(261, 382).addBox(14.0F, -11.0F, 0.0F, 1.0F, 11.0F, 18.0F, 0.0F, false);
		tail.texOffs(184, 378).addBox(-15.0F, -11.0F, 0.0F, 1.0F, 11.0F, 18.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(BobsleTeamEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void tickPartAnim(IBodyEntity entity, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
		
	}

	@Override
	public void renderBody(IBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight,
			int packedOverlay) {
		this.z1.visible = false;
		this.z2.visible = false;
		this.z3.visible = false;
		this.z4.visible = false;
		total.render(stack, buffer, packedLight, packedOverlay);
	}

	@Override
	public EntityModel<BobsleTeamEntity> getZombieModel() {
		return this;
	}
}