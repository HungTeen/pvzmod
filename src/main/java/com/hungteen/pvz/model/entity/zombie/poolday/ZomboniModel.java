package com.hungteen.pvz.model.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.ZomboniEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ZomboniModel extends EntityModel<ZomboniEntity> {
	private final ModelRenderer total;
	private final ModelRenderer car;
	private final ModelRenderer lunzi;
	private final ModelRenderer tyre_leftfront;
	private final ModelRenderer bone14;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer tyre_leftfront2;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer tyre_leftfront3;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer tyre_leftfront4;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone23;
	private final ModelRenderer bone24;
	private final ModelRenderer bone25;
	private final ModelRenderer chair;
	private final ModelRenderer bone;
	private final ModelRenderer mid;
	private final ModelRenderer bone26;
	private final ModelRenderer ice;
	private final ModelRenderer front;
	private final ModelRenderer level1;
	private final ModelRenderer level2;
	private final ModelRenderer gai;
	private final ModelRenderer ice2;
	private final ModelRenderer zombie;
	private final ModelRenderer head;
	private final ModelRenderer bone27;
	private final ModelRenderer bone28;
	private final ModelRenderer bone29;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public ZomboniModel() {
		textureWidth = 512;
		textureHeight = 512;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 23.0F, 0.0F);
		

		car = new ModelRenderer(this);
		car.setRotationPoint(0.0F, 1.0F, -3.0F);
		total.addChild(car);
		

		lunzi = new ModelRenderer(this);
		lunzi.setRotationPoint(0.0F, 0.0F, 0.0F);
		car.addChild(lunzi);
		

		tyre_leftfront = new ModelRenderer(this);
		tyre_leftfront.setRotationPoint(19.0F, -7.0F, -45.0F);
		lunzi.addChild(tyre_leftfront);
		

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, -3.0F, -34.0F);
		tyre_leftfront.addChild(bone14);
		

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone14.addChild(bone12);
		bone12.setTextureOffset(478, 459).addBox(-4.0F, 3.0F, 31.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone12.setTextureOffset(481, 436).addBox(-4.0F, -4.4853F, 31.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone14.addChild(bone13);
		setRotationAngle(bone13, -1.5708F, 0.0F, 0.0F);
		bone13.setTextureOffset(478, 412).addBox(-4.0F, -35.7574F, 9.7574F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone13.setTextureOffset(481, 390).addBox(-4.0F, -43.2426F, 9.7574F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, -5.0F, -36.0F);
		tyre_leftfront.addChild(bone15);
		setRotationAngle(bone15, -0.7854F, 0.0F, 0.0F);
		

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone15.addChild(bone16);
		bone16.setTextureOffset(481, 372).addBox(-4.0F, -21.8492F, 25.8198F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone16.setTextureOffset(480, 345).addBox(-4.0F, -29.3345F, 25.8198F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone15.addChild(bone17);
		setRotationAngle(bone17, -1.5708F, 0.0F, 0.0F);
		bone17.setTextureOffset(478, 324).addBox(-4.0F, -30.5772F, -15.0919F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone17.setTextureOffset(480, 297).addBox(-4.0F, -38.0624F, -15.0919F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		tyre_leftfront2 = new ModelRenderer(this);
		tyre_leftfront2.setRotationPoint(19.0F, -7.0F, -12.0F);
		lunzi.addChild(tyre_leftfront2);
		

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -3.0F, -34.0F);
		tyre_leftfront2.addChild(bone2);
		

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		bone3.setTextureOffset(481, 270).addBox(-4.0F, 3.0F, 31.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone3.setTextureOffset(481, 241).addBox(-4.0F, -4.4853F, 31.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone2.addChild(bone4);
		setRotationAngle(bone4, -1.5708F, 0.0F, 0.0F);
		bone4.setTextureOffset(486, 216).addBox(-4.0F, -35.7574F, 9.7574F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone4.setTextureOffset(486, 192).addBox(-4.0F, -43.2426F, 9.7574F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, -5.0F, -36.0F);
		tyre_leftfront2.addChild(bone5);
		setRotationAngle(bone5, -0.7854F, 0.0F, 0.0F);
		

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone5.addChild(bone6);
		bone6.setTextureOffset(488, 145).addBox(-4.0F, -21.8492F, 25.8198F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone6.setTextureOffset(484, 169).addBox(-4.0F, -29.3345F, 25.8198F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone5.addChild(bone7);
		setRotationAngle(bone7, -1.5708F, 0.0F, 0.0F);
		bone7.setTextureOffset(486, 124).addBox(-4.0F, -30.5772F, -15.0919F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone7.setTextureOffset(486, 100).addBox(-4.0F, -38.0624F, -15.0919F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		tyre_leftfront3 = new ModelRenderer(this);
		tyre_leftfront3.setRotationPoint(-18.0F, -7.0F, -45.0F);
		lunzi.addChild(tyre_leftfront3);
		

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(37.0F, -3.0F, -34.0F);
		tyre_leftfront3.addChild(bone8);
		

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone8.addChild(bone9);
		bone9.setTextureOffset(486, 75).addBox(-38.0F, 3.0F, 31.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone9.setTextureOffset(484, 56).addBox(-38.0F, -4.4853F, 31.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone8.addChild(bone10);
		setRotationAngle(bone10, -1.5708F, 0.0F, 0.0F);
		bone10.setTextureOffset(486, 36).addBox(-38.0F, -35.7574F, 9.7574F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone10.setTextureOffset(486, 12).addBox(-38.0F, -43.2426F, 9.7574F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(37.0F, -5.0F, -36.0F);
		tyre_leftfront3.addChild(bone11);
		setRotationAngle(bone11, -0.7854F, 0.0F, 0.0F);
		

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone11.addChild(bone18);
		bone18.setTextureOffset(456, 11).addBox(-38.0F, -21.8492F, 25.8198F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone18.setTextureOffset(452, 38).addBox(-38.0F, -29.3345F, 25.8198F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone11.addChild(bone19);
		setRotationAngle(bone19, -1.5708F, 0.0F, 0.0F);
		bone19.setTextureOffset(456, 57).addBox(-38.0F, -30.5772F, -15.0919F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone19.setTextureOffset(457, 80).addBox(-38.0F, -38.0624F, -15.0919F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		tyre_leftfront4 = new ModelRenderer(this);
		tyre_leftfront4.setRotationPoint(-17.0F, -7.0F, -12.0F);
		lunzi.addChild(tyre_leftfront4);
		

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(36.0F, -3.0F, -34.0F);
		tyre_leftfront4.addChild(bone20);
		

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone20.addChild(bone21);
		bone21.setTextureOffset(449, 100).addBox(-38.0F, 3.0F, 31.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone21.setTextureOffset(454, 124).addBox(-38.0F, -4.4853F, 31.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone20.addChild(bone22);
		setRotationAngle(bone22, -1.5708F, 0.0F, 0.0F);
		bone22.setTextureOffset(454, 152).addBox(-38.0F, -35.7574F, 9.7574F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone22.setTextureOffset(457, 176).addBox(-38.0F, -43.2426F, 9.7574F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(36.0F, -5.0F, -36.0F);
		tyre_leftfront4.addChild(bone23);
		setRotationAngle(bone23, -0.7854F, 0.0F, 0.0F);
		

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone23.addChild(bone24);
		bone24.setTextureOffset(454, 200).addBox(-38.0F, -21.8492F, 25.8198F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone24.setTextureOffset(454, 220).addBox(-38.0F, -29.3345F, 25.8198F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone23.addChild(bone25);
		setRotationAngle(bone25, -1.5708F, 0.0F, 0.0F);
		bone25.setTextureOffset(452, 246).addBox(-38.0F, -30.5772F, -15.0919F, 4.0F, 7.0F, 6.0F, 0.0F, false);
		bone25.setTextureOffset(452, 268).addBox(-38.0F, -38.0624F, -15.0919F, 4.0F, 7.0F, 6.0F, 0.0F, false);

		chair = new ModelRenderer(this);
		chair.setRotationPoint(0.0F, -26.0F, 20.0F);
		car.addChild(chair);
		chair.setTextureOffset(438, 492).addBox(-10.0F, -1.0F, -12.0F, 20.0F, 1.0F, 14.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 1.0F);
		chair.addChild(bone);
		setRotationAngle(bone, -0.1745F, 0.0F, 0.0F);
		bone.setTextureOffset(384, 483).addBox(-10.0F, -22.9848F, -0.1736F, 20.0F, 22.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(454, 472).addBox(10.0F, -2.9848F, -1.1736F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		bone.setTextureOffset(459, 457).addBox(-11.0F, -2.9848F, -1.1736F, 1.0F, 3.0F, 2.0F, 0.0F, false);

		mid = new ModelRenderer(this);
		mid.setRotationPoint(0.0F, -10.0F, 2.0F);
		car.addChild(mid);
		mid.setTextureOffset(4, 491).addBox(-15.0F, -1.0F, -7.0F, 30.0F, 3.0F, 15.0F, 0.0F, false);
		mid.setTextureOffset(89, 488).addBox(-15.0F, -16.0F, 8.0F, 30.0F, 18.0F, 1.0F, 0.0F, false);
		mid.setTextureOffset(171, 491).addBox(-15.0F, -16.0F, 9.0F, 29.0F, 8.0F, 8.0F, 0.0F, false);
		mid.setTextureOffset(299, 497).addBox(-8.0F, -8.0F, 9.0F, 16.0F, 4.0F, 4.0F, 0.0F, false);

		bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(0.0F, -10.0F, 17.0F);
		mid.addChild(bone26);
		setRotationAngle(bone26, 1.1345F, 0.0F, 0.0F);
		bone26.setTextureOffset(262, 497).addBox(-4.0F, -1.0F, 0.0F, 8.0F, 1.0F, 7.0F, 0.0F, false);

		ice = new ModelRenderer(this);
		ice.setRotationPoint(0.0F, 0.0F, 0.0F);
		mid.addChild(ice);
		ice.setTextureOffset(400, 472).addBox(-8.0F, 1.0F, 0.0F, 16.0F, 4.0F, 1.0F, 0.0F, false);
		ice.setTextureOffset(4, 470).addBox(-9.0F, 5.0F, -1.0F, 18.0F, 1.0F, 3.0F, 0.0F, false);
		ice.setTextureOffset(56, 467).addBox(-11.0F, 6.0F, -3.0F, 22.0F, 1.0F, 7.0F, 0.0F, false);
		ice.setTextureOffset(372, 374).addBox(-13.0F, 7.0F, -5.0F, 26.0F, 2.0F, 11.0F, 0.0F, false);

		front = new ModelRenderer(this);
		front.setRotationPoint(0.0F, -10.0F, -3.0F);
		car.addChild(front);
		front.setTextureOffset(6, 363).addBox(-15.0F, -35.0F, -50.0F, 30.0F, 38.0F, 48.0F, 0.0F, false);

		level1 = new ModelRenderer(this);
		level1.setRotationPoint(9.0F, -28.0F, -1.0F);
		front.addChild(level1);
		setRotationAngle(level1, 1.1345F, 0.0F, 0.0F);
		level1.setTextureOffset(176, 452).addBox(1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);

		level2 = new ModelRenderer(this);
		level2.setRotationPoint(-13.0F, -28.0F, -1.0F);
		front.addChild(level2);
		setRotationAngle(level2, 1.1345F, 0.0F, 0.0F);
		level2.setTextureOffset(230, 459).addBox(1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);

		gai = new ModelRenderer(this);
		gai.setRotationPoint(0.0F, -36.0F, -52.0F);
		front.addChild(gai);
		gai.setTextureOffset(4, 291).addBox(-17.0F, -7.0F, 0.0F, 34.0F, 21.0F, 45.0F, 0.0F, false);

		ice2 = new ModelRenderer(this);
		ice2.setRotationPoint(0.0F, 2.0F, -25.0F);
		front.addChild(ice2);
		ice2.setTextureOffset(432, 359).addBox(-8.0F, 1.0F, 0.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
		ice2.setTextureOffset(338, 359).addBox(-9.0F, 2.0F, -1.0F, 18.0F, 1.0F, 3.0F, 0.0F, false);
		ice2.setTextureOffset(263, 348).addBox(-11.0F, 3.0F, -3.0F, 22.0F, 2.0F, 7.0F, 0.0F, false);

		zombie = new ModelRenderer(this);
		zombie.setRotationPoint(0.0F, -32.0F, 12.0F);
		total.addChild(zombie);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -25.0F, 0.0F);
		zombie.addChild(head);
		head.setTextureOffset(291, 449).addBox(-7.0F, -13.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(369, 440).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		head.setTextureOffset(294, 435).addBox(-7.0F, -17.0F, 0.0F, 14.0F, 1.0F, 8.0F, 0.0F, false);

		bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(8.0F, -14.0F, 0.0F);
		head.addChild(bone27);
		setRotationAngle(bone27, 0.0F, -0.3491F, 1.3963F);
		bone27.setTextureOffset(179, 443).addBox(0.0F, 0.0F, -2.0F, 8.0F, 1.0F, 4.0F, 0.0F, false);

		bone28 = new ModelRenderer(this);
		bone28.setRotationPoint(-8.0F, -13.0F, 0.0F);
		head.addChild(bone28);
		setRotationAngle(bone28, 0.0F, 0.3491F, -1.3963F);
		bone28.setTextureOffset(219, 446).addBox(-7.0F, -1.0F, -2.0F, 8.0F, 1.0F, 4.0F, 0.0F, true);

		bone29 = new ModelRenderer(this);
		bone29.setRotationPoint(0.0F, -17.0F, 8.0F);
		head.addChild(bone29);
		setRotationAngle(bone29, 0.3491F, 0.0F, 0.0F);
		bone29.setTextureOffset(264, 444).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-7.0F, -21.0F, 0.0F);
		zombie.addChild(right_hand);
		setRotationAngle(right_hand, -1.2217F, 0.0F, 0.0F);
		right_hand.setTextureOffset(176, 403).addBox(-7.0F, -3.0F, -3.0F, 6.0F, 21.0F, 6.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(8.0F, -21.0F, 0.0F);
		zombie.addChild(left_hand);
		setRotationAngle(left_hand, -1.2217F, 0.0F, 0.0F);
		left_hand.setTextureOffset(217, 406).addBox(0.0F, -3.0F, -3.0F, 6.0F, 21.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -1.0F, 0.0F);
		zombie.addChild(body);
		body.setTextureOffset(252, 400).addBox(-8.0F, -23.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(321, 409).addBox(-2.0F, 1.0F, -4.0F, 4.0F, 6.0F, 8.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-6.0F, 0.0F, 0.0F);
		zombie.addChild(right_leg);
		right_leg.setTextureOffset(358, 398).addBox(-2.0F, 0.0F, -13.0F, 6.0F, 6.0F, 17.0F, 0.0F, false);
		right_leg.setTextureOffset(414, 418).addBox(-3.0F, 18.0F, -16.0F, 8.0F, 4.0F, 10.0F, 0.0F, false);
		right_leg.setTextureOffset(168, 377).addBox(-2.0F, 6.0F, -13.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 0.0F, 0.0F);
		zombie.addChild(left_leg);
		left_leg.setTextureOffset(212, 366).addBox(-2.0F, 0.0F, -13.0F, 6.0F, 6.0F, 17.0F, 0.0F, false);
		left_leg.setTextureOffset(274, 375).addBox(-3.0F, 18.0F, -16.0F, 8.0F, 4.0F, 10.0F, 0.0F, false);
		left_leg.setTextureOffset(326, 373).addBox(-2.0F, 6.0F, -13.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ZomboniEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.ice2.rotateAngleX=MathHelper.sin(ageInTicks/20)/2;
		this.tyre_leftfront.rotateAngleX=ageInTicks%360;
		this.tyre_leftfront2.rotateAngleX=ageInTicks%360;
		this.tyre_leftfront3.rotateAngleX=ageInTicks%360;
		this.tyre_leftfront4.rotateAngleX=ageInTicks%360;
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