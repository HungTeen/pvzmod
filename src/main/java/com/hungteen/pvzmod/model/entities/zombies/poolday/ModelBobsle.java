package com.hungteen.pvzmod.model.entities.zombies.poolday;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelBobsle extends ModelBase {
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

	public ModelBobsle() {
		textureWidth = 512;
		textureHeight = 512;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 2.0F, -8.0F);

		z1 = new ModelRenderer(this);
		z1.setRotationPoint(0.0F, 4.0F, 29.0F);
		total.addChild(z1);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -23.0F, 0.0F);
		setRotationAngle(head, 0.0F, -0.3491F, 0.0F);
		z1.addChild(head);
		head.cubeList.add(new ModelBox(head, 3, 192, -6.0F, -12.0F, -6.0F, 12, 12, 12, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 109, 234, -7.0F, -6.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 59, 199, -7.0F, -10.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 100, 201, 6.0F, -6.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 142, 200, 6.0F, -10.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 187, 214, -6.0F, -6.0F, 6.0F, 12, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 222, 217, -6.0F, -10.0F, 6.0F, 12, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 181, 192, -6.0F, -10.0F, -7.0F, 12, 5, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 222, 198, -5.0F, -11.0F, -7.0F, 10, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 134, 187, -5.0F, -5.0F, -7.0F, 10, 1, 1, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-9.0F, -21.0F, 0.0F);
		z1.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 77, 224, -3.0F, -2.0F, -2.0F, 4, 23, 4, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(10.0F, -21.0F, 0.0F);
		z1.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 54, 227, -2.0F, -2.0F, -2.0F, 4, 23, 4, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -6.0F, 0.0F);
		z1.addChild(body);
		body.cubeList.add(new ModelBox(body, 5, 224, -8.0F, -17.0F, -3.0F, 16, 23, 6, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		setRotationAngle(right_leg, -1.309F, -0.7854F, 0.0F);
		z1.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 184, 229, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 149, 238, -3.0F, 17.0F, -6.0F, 6, 4, 9, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 0.0F, 0.0F);
		setRotationAngle(left_leg, -1.309F, 0.7854F, 0.0F);
		z1.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 238, 228, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 203, 240, -3.0F, 17.0F, -6.0F, 6, 4, 9, 0.0F, false));

		z2 = new ModelRenderer(this);
		z2.setRotationPoint(0.0F, 4.0F, 49.0F);
		total.addChild(z2);

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, -23.0F, 0.0F);
		setRotationAngle(head2, 0.0F, 0.6109F, 0.0F);
		z2.addChild(head2);
		head2.cubeList.add(new ModelBox(head2, 3, 192, -6.0F, -12.0F, -6.0F, 12, 12, 12, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 109, 234, -7.0F, -6.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 59, 199, -7.0F, -10.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 100, 201, 6.0F, -6.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 142, 200, 6.0F, -10.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 187, 214, -6.0F, -6.0F, 6.0F, 12, 1, 1, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 222, 217, -6.0F, -10.0F, 6.0F, 12, 1, 1, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 181, 192, -6.0F, -10.0F, -7.0F, 12, 5, 1, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 222, 198, -5.0F, -11.0F, -7.0F, 10, 1, 1, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 134, 187, -5.0F, -5.0F, -7.0F, 10, 1, 1, 0.0F, false));

		right_hand2 = new ModelRenderer(this);
		right_hand2.setRotationPoint(-9.0F, -21.0F, 0.0F);
		z2.addChild(right_hand2);
		right_hand2.cubeList.add(new ModelBox(right_hand2, 77, 224, -3.0F, -2.0F, -2.0F, 4, 23, 4, 0.0F, false));

		left_hand2 = new ModelRenderer(this);
		left_hand2.setRotationPoint(10.0F, -21.0F, 0.0F);
		z2.addChild(left_hand2);
		left_hand2.cubeList.add(new ModelBox(left_hand2, 54, 227, -2.0F, -2.0F, -2.0F, 4, 23, 4, 0.0F, false));

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, -6.0F, 0.0F);
		z2.addChild(body2);
		body2.cubeList.add(new ModelBox(body2, 5, 224, -8.0F, -17.0F, -3.0F, 16, 23, 6, 0.0F, false));

		right_leg2 = new ModelRenderer(this);
		right_leg2.setRotationPoint(-4.0F, 0.0F, 0.0F);
		setRotationAngle(right_leg2, -1.309F, -0.7854F, 0.0F);
		z2.addChild(right_leg2);
		right_leg2.cubeList.add(new ModelBox(right_leg2, 184, 229, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));
		right_leg2.cubeList.add(new ModelBox(right_leg2, 149, 238, -3.0F, 17.0F, -6.0F, 6, 4, 9, 0.0F, false));

		left_leg2 = new ModelRenderer(this);
		left_leg2.setRotationPoint(4.0F, 0.0F, 0.0F);
		setRotationAngle(left_leg2, -1.309F, 0.7854F, 0.0F);
		z2.addChild(left_leg2);
		left_leg2.cubeList.add(new ModelBox(left_leg2, 238, 228, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));
		left_leg2.cubeList.add(new ModelBox(left_leg2, 203, 240, -3.0F, 17.0F, -6.0F, 6, 4, 9, 0.0F, false));

		mid = new ModelRenderer(this);
		mid.setRotationPoint(0.0F, 8.0F, 29.0F);
		total.addChild(mid);
		mid.cubeList.add(new ModelBox(mid, 284, 421, -14.0F, 4.0F, -17.0F, 28, 1, 84, 0.0F, false));
		mid.cubeList.add(new ModelBox(mid, 459, 402, -8.0F, -4.0F, -3.0F, 16, 8, 6, 0.0F, false));
		mid.cubeList.add(new ModelBox(mid, 402, 399, -8.0F, -4.0F, 17.0F, 16, 8, 6, 0.0F, false));
		mid.cubeList.add(new ModelBox(mid, 463, 372, -8.0F, -4.0F, 37.0F, 16, 8, 6, 0.0F, false));
		mid.cubeList.add(new ModelBox(mid, 402, 368, -8.0F, -4.0F, 57.0F, 16, 8, 6, 0.0F, false));
		mid.cubeList.add(new ModelBox(mid, 440, 350, -14.0F, -2.0F, 67.0F, 28, 7, 1, 0.0F, false));

		left = new ModelRenderer(this);
		left.setRotationPoint(13.0F, 4.0F, 24.0F);
		mid.addChild(left);
		left.cubeList.add(new ModelBox(left, 9, 399, 1.0F, -24.0F, -41.0F, 1, 25, 84, 0.0F, false));

		right = new ModelRenderer(this);
		right.setRotationPoint(-13.0F, 4.0F, 25.0F);
		mid.addChild(right);
		right.cubeList.add(new ModelBox(right, 336, 7, -2.0F, -24.0F, -42.0F, 1, 25, 84, 0.0F, false));

		z3 = new ModelRenderer(this);
		z3.setRotationPoint(0.0F, 4.0F, 69.0F);
		total.addChild(z3);

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(0.0F, -23.0F, 0.0F);
		setRotationAngle(head3, -0.3491F, 0.0F, 0.0F);
		z3.addChild(head3);
		head3.cubeList.add(new ModelBox(head3, 3, 192, -6.0F, -12.0F, -6.0F, 12, 12, 12, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 109, 234, -7.0F, -6.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 59, 199, -7.0F, -10.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 100, 201, 6.0F, -6.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 142, 200, 6.0F, -10.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 187, 214, -6.0F, -6.0F, 6.0F, 12, 1, 1, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 222, 217, -6.0F, -10.0F, 6.0F, 12, 1, 1, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 181, 192, -6.0F, -10.0F, -7.0F, 12, 5, 1, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 222, 198, -5.0F, -11.0F, -7.0F, 10, 1, 1, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 134, 187, -5.0F, -5.0F, -7.0F, 10, 1, 1, 0.0F, false));

		right_hand3 = new ModelRenderer(this);
		right_hand3.setRotationPoint(-9.0F, -21.0F, 0.0F);
		setRotationAngle(right_hand3, -1.309F, 0.1745F, 0.0F);
		z3.addChild(right_hand3);
		right_hand3.cubeList.add(new ModelBox(right_hand3, 77, 224, -3.0F, -2.0F, -2.0F, 4, 23, 4, 0.0F, false));

		left_hand3 = new ModelRenderer(this);
		left_hand3.setRotationPoint(10.0F, -21.0F, 0.0F);
		z3.addChild(left_hand3);
		left_hand3.cubeList.add(new ModelBox(left_hand3, 54, 227, -2.0F, -2.0F, -2.0F, 4, 23, 4, 0.0F, false));

		body3 = new ModelRenderer(this);
		body3.setRotationPoint(0.0F, -6.0F, 0.0F);
		z3.addChild(body3);
		body3.cubeList.add(new ModelBox(body3, 5, 224, -8.0F, -17.0F, -3.0F, 16, 23, 6, 0.0F, false));

		right_leg3 = new ModelRenderer(this);
		right_leg3.setRotationPoint(-4.0F, 0.0F, 0.0F);
		setRotationAngle(right_leg3, -1.309F, -0.7854F, 0.0F);
		z3.addChild(right_leg3);
		right_leg3.cubeList.add(new ModelBox(right_leg3, 184, 229, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));
		right_leg3.cubeList.add(new ModelBox(right_leg3, 149, 238, -3.0F, 17.0F, -6.0F, 6, 4, 9, 0.0F, false));

		left_leg3 = new ModelRenderer(this);
		left_leg3.setRotationPoint(4.0F, 0.0F, 0.0F);
		setRotationAngle(left_leg3, -1.309F, 0.7854F, 0.0F);
		z3.addChild(left_leg3);
		left_leg3.cubeList.add(new ModelBox(left_leg3, 238, 228, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));
		left_leg3.cubeList.add(new ModelBox(left_leg3, 203, 240, -3.0F, 17.0F, -6.0F, 6, 4, 9, 0.0F, false));

		z4 = new ModelRenderer(this);
		z4.setRotationPoint(0.0F, 4.0F, 89.0F);
		total.addChild(z4);

		head4 = new ModelRenderer(this);
		head4.setRotationPoint(0.0F, -23.0F, 0.0F);
		z4.addChild(head4);
		head4.cubeList.add(new ModelBox(head4, 3, 192, -6.0F, -12.0F, -6.0F, 12, 12, 12, 0.0F, false));
		head4.cubeList.add(new ModelBox(head4, 109, 234, -7.0F, -6.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head4.cubeList.add(new ModelBox(head4, 59, 199, -7.0F, -10.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head4.cubeList.add(new ModelBox(head4, 100, 201, 6.0F, -6.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head4.cubeList.add(new ModelBox(head4, 142, 200, 6.0F, -10.0F, -7.0F, 1, 1, 14, 0.0F, false));
		head4.cubeList.add(new ModelBox(head4, 187, 214, -6.0F, -6.0F, 6.0F, 12, 1, 1, 0.0F, false));
		head4.cubeList.add(new ModelBox(head4, 222, 217, -6.0F, -10.0F, 6.0F, 12, 1, 1, 0.0F, false));
		head4.cubeList.add(new ModelBox(head4, 181, 192, -6.0F, -10.0F, -7.0F, 12, 5, 1, 0.0F, false));
		head4.cubeList.add(new ModelBox(head4, 222, 198, -5.0F, -11.0F, -7.0F, 10, 1, 1, 0.0F, false));
		head4.cubeList.add(new ModelBox(head4, 134, 187, -5.0F, -5.0F, -7.0F, 10, 1, 1, 0.0F, false));

		right_hand4 = new ModelRenderer(this);
		right_hand4.setRotationPoint(-9.0F, -21.0F, 0.0F);
		z4.addChild(right_hand4);
		right_hand4.cubeList.add(new ModelBox(right_hand4, 77, 224, -3.0F, -2.0F, -2.0F, 4, 23, 4, 0.0F, false));

		left_hand4 = new ModelRenderer(this);
		left_hand4.setRotationPoint(10.0F, -21.0F, 0.0F);
		setRotationAngle(left_hand4, -2.0071F, 0.0F, 0.0F);
		z4.addChild(left_hand4);
		left_hand4.cubeList.add(new ModelBox(left_hand4, 54, 227, -2.0F, -2.0F, -2.0F, 4, 23, 4, 0.0F, false));

		body4 = new ModelRenderer(this);
		body4.setRotationPoint(0.0F, -6.0F, 0.0F);
		z4.addChild(body4);
		body4.cubeList.add(new ModelBox(body4, 5, 224, -8.0F, -17.0F, -3.0F, 16, 23, 6, 0.0F, false));

		right_leg4 = new ModelRenderer(this);
		right_leg4.setRotationPoint(-4.0F, 0.0F, 0.0F);
		setRotationAngle(right_leg4, -1.309F, -0.7854F, 0.0F);
		z4.addChild(right_leg4);
		right_leg4.cubeList.add(new ModelBox(right_leg4, 184, 229, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));
		right_leg4.cubeList.add(new ModelBox(right_leg4, 149, 238, -3.0F, 17.0F, -6.0F, 6, 4, 9, 0.0F, false));

		left_leg4 = new ModelRenderer(this);
		left_leg4.setRotationPoint(4.0F, 0.0F, 0.0F);
		setRotationAngle(left_leg4, -1.309F, 0.7854F, 0.0F);
		z4.addChild(left_leg4);
		left_leg4.cubeList.add(new ModelBox(left_leg4, 238, 228, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));
		left_leg4.cubeList.add(new ModelBox(left_leg4, 203, 240, -3.0F, 17.0F, -6.0F, 6, 4, 9, 0.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 13.0F, 4.0F);
		total.addChild(Head);
		Head.cubeList.add(new ModelBox(Head, 188, 449, -15.0F, -25.0F, -12.0F, 30, 25, 20, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 129, 436, -13.0F, -15.0F, -17.0F, 26, 15, 5, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 13, 378, -10.0F, -7.0F, -22.0F, 20, 7, 5, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 11, 340, -15.0F, -27.0F, -2.0F, 30, 2, 21, 0.0F, false));

		wheal = new ModelRenderer(this);
		wheal.setRotationPoint(13.0F, 16.0F, 6.0F);
		total.addChild(wheal);
		wheal.cubeList.add(new ModelBox(wheal, 0, 0, -1.0F, -1.0F, -6.0F, 2, 2, 16, 0.0F, false));
		wheal.cubeList.add(new ModelBox(wheal, 0, 0, -2.0F, -3.0F, -8.0F, 4, 2, 4, 0.0F, false));
		wheal.cubeList.add(new ModelBox(wheal, 0, 0, -2.0F, -3.0F, 8.0F, 4, 2, 4, 0.0F, false));
		wheal.cubeList.add(new ModelBox(wheal, 0, 0, -2.0F, 1.0F, 9.0F, 4, 2, 4, 0.0F, false));
		wheal.cubeList.add(new ModelBox(wheal, 0, 0, -2.0F, 1.0F, 0.0F, 4, 2, 4, 0.0F, false));
		wheal.cubeList.add(new ModelBox(wheal, 0, 0, -2.0F, 1.0F, -9.0F, 4, 2, 4, 0.0F, false));
		wheal.cubeList.add(new ModelBox(wheal, 0, 0, -1.0F, 3.0F, -11.0F, 2, 3, 26, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 3.0F, -11.0F);
		setRotationAngle(bone, -0.6981F, 0.0F, 0.0F);
		wheal.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.0F, 0.0F, -3.0F, 2, 2, 5, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 3.0F, 14.0F);
		setRotationAngle(bone2, 0.6981F, 0.0F, 0.0F);
		wheal.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -1.0F, 0.6428F, -1.234F, 2, 2, 5, 0.0F, false));

		wheal2 = new ModelRenderer(this);
		wheal2.setRotationPoint(13.0F, 16.0F, 6.0F);
		total.addChild(wheal2);
		wheal2.cubeList.add(new ModelBox(wheal2, 0, 0, -27.0F, -1.0F, -6.0F, 2, 2, 16, 0.0F, false));
		wheal2.cubeList.add(new ModelBox(wheal2, 0, 0, -28.0F, -3.0F, -8.0F, 4, 2, 4, 0.0F, false));
		wheal2.cubeList.add(new ModelBox(wheal2, 0, 0, -28.0F, -3.0F, 8.0F, 4, 2, 4, 0.0F, false));
		wheal2.cubeList.add(new ModelBox(wheal2, 0, 0, -28.0F, 1.0F, 9.0F, 4, 2, 4, 0.0F, false));
		wheal2.cubeList.add(new ModelBox(wheal2, 0, 0, -28.0F, 1.0F, 0.0F, 4, 2, 4, 0.0F, false));
		wheal2.cubeList.add(new ModelBox(wheal2, 0, 0, -28.0F, 1.0F, -9.0F, 4, 2, 4, 0.0F, false));
		wheal2.cubeList.add(new ModelBox(wheal2, 0, 0, -27.0F, 3.0F, -11.0F, 2, 3, 26, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 3.0F, -11.0F);
		setRotationAngle(bone3, -0.6981F, 0.0F, 0.0F);
		wheal2.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -27.0F, 0.0F, -3.0F, 2, 2, 5, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 3.0F, 14.0F);
		setRotationAngle(bone4, 0.6981F, 0.0F, 0.0F);
		wheal2.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -27.0F, 0.6428F, -1.234F, 2, 2, 5, 0.0F, false));

		wheal3 = new ModelRenderer(this);
		wheal3.setRotationPoint(13.0F, 16.0F, 6.0F);
		total.addChild(wheal3);
		wheal3.cubeList.add(new ModelBox(wheal3, 0, 0, -1.0F, -1.0F, 70.0F, 2, 2, 16, 0.0F, false));
		wheal3.cubeList.add(new ModelBox(wheal3, 0, 0, -2.0F, -3.0F, 68.0F, 4, 2, 4, 0.0F, false));
		wheal3.cubeList.add(new ModelBox(wheal3, 0, 0, -2.0F, -3.0F, 84.0F, 4, 2, 4, 0.0F, false));
		wheal3.cubeList.add(new ModelBox(wheal3, 0, 0, -2.0F, 1.0F, 85.0F, 4, 2, 4, 0.0F, false));
		wheal3.cubeList.add(new ModelBox(wheal3, 0, 0, -2.0F, 1.0F, 76.0F, 4, 2, 4, 0.0F, false));
		wheal3.cubeList.add(new ModelBox(wheal3, 0, 0, -2.0F, 1.0F, 67.0F, 4, 2, 4, 0.0F, false));
		wheal3.cubeList.add(new ModelBox(wheal3, 0, 0, -1.0F, 3.0F, 65.0F, 2, 3, 26, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 3.0F, -11.0F);
		setRotationAngle(bone5, -0.6981F, 0.0F, 0.0F);
		wheal3.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 0, 0, -1.0F, -48.8519F, 55.2194F, 2, 2, 5, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 3.0F, 14.0F);
		setRotationAngle(bone6, 0.6981F, 0.0F, 0.0F);
		wheal3.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, -1.0F, 49.4946F, 56.9854F, 2, 2, 5, 0.0F, false));

		wheal4 = new ModelRenderer(this);
		wheal4.setRotationPoint(13.0F, 16.0F, 81.0F);
		total.addChild(wheal4);
		wheal4.cubeList.add(new ModelBox(wheal4, 0, 0, -27.0F, -1.0F, -6.0F, 2, 2, 16, 0.0F, false));
		wheal4.cubeList.add(new ModelBox(wheal4, 0, 0, -28.0F, -3.0F, -8.0F, 4, 2, 4, 0.0F, false));
		wheal4.cubeList.add(new ModelBox(wheal4, 0, 0, -28.0F, -3.0F, 8.0F, 4, 2, 4, 0.0F, false));
		wheal4.cubeList.add(new ModelBox(wheal4, 0, 0, -28.0F, 1.0F, 9.0F, 4, 2, 4, 0.0F, false));
		wheal4.cubeList.add(new ModelBox(wheal4, 0, 0, -28.0F, 1.0F, 0.0F, 4, 2, 4, 0.0F, false));
		wheal4.cubeList.add(new ModelBox(wheal4, 0, 0, -28.0F, 1.0F, -9.0F, 4, 2, 4, 0.0F, false));
		wheal4.cubeList.add(new ModelBox(wheal4, 0, 0, -27.0F, 3.0F, -11.0F, 2, 3, 26, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 3.0F, -11.0F);
		setRotationAngle(bone7, -0.6981F, 0.0F, 0.0F);
		wheal4.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 0, 0, -27.0F, 0.0F, -3.0F, 2, 2, 5, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 3.0F, 14.0F);
		setRotationAngle(bone8, 0.6981F, 0.0F, 0.0F);
		wheal4.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 0, 0, -27.0F, 0.6428F, -1.234F, 2, 2, 5, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.0F, 96.0F);
		setRotationAngle(tail, 0.4363F, 0.0F, 0.0F);
		total.addChild(tail);
		tail.cubeList.add(new ModelBox(tail, 261, 382, 14.0F, -11.0F, 0.0F, 1, 11, 18, 0.0F, false));
		tail.cubeList.add(new ModelBox(tail, 184, 378, -15.0F, -11.0F, 0.0F, 1, 11, 18, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		total.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}