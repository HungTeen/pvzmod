package com.hungteen.pvzmod.model.entities.zombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelZomboni extends ModelBase {
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

	public ModelZomboni() {
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
		bone12.cubeList.add(new ModelBox(bone12, 478, 459, -4.0F, 3.0F, 31.0F, 4, 7, 6, 0.0F, false));
		bone12.cubeList.add(new ModelBox(bone12, 481, 436, -4.0F, -4.4853F, 31.0F, 4, 7, 6, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, -10.0F, -2.0F);
		setRotationAngle(bone13, -1.5708F, 0.0F, 0.0F);
		bone14.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 478, 412, -4.0F, -35.7574F, 9.7574F, 4, 7, 6, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 481, 390, -4.0F, -43.2426F, 9.7574F, 4, 7, 6, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, -5.0F, -36.0F);
		setRotationAngle(bone15, -0.7854F, 0.0F, 0.0F);
		tyre_leftfront.addChild(bone15);

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone15.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 481, 372, -4.0F, -21.8492F, 25.8198F, 4, 7, 6, 0.0F, false));
		bone16.cubeList.add(new ModelBox(bone16, 480, 345, -4.0F, -29.3345F, 25.8198F, 4, 7, 6, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, -10.0F, -2.0F);
		setRotationAngle(bone17, -1.5708F, 0.0F, 0.0F);
		bone15.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 478, 324, -4.0F, -30.5772F, -15.0919F, 4, 7, 6, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 480, 297, -4.0F, -38.0624F, -15.0919F, 4, 7, 6, 0.0F, false));

		tyre_leftfront2 = new ModelRenderer(this);
		tyre_leftfront2.setRotationPoint(19.0F, -7.0F, -12.0F);
		lunzi.addChild(tyre_leftfront2);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -3.0F, -34.0F);
		tyre_leftfront2.addChild(bone2);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 481, 270, -4.0F, 3.0F, 31.0F, 4, 7, 6, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 481, 241, -4.0F, -4.4853F, 31.0F, 4, 7, 6, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -10.0F, -2.0F);
		setRotationAngle(bone4, -1.5708F, 0.0F, 0.0F);
		bone2.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 486, 216, -4.0F, -35.7574F, 9.7574F, 4, 7, 6, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 486, 192, -4.0F, -43.2426F, 9.7574F, 4, 7, 6, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, -5.0F, -36.0F);
		setRotationAngle(bone5, -0.7854F, 0.0F, 0.0F);
		tyre_leftfront2.addChild(bone5);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone5.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 488, 145, -4.0F, -21.8492F, 25.8198F, 4, 7, 6, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 484, 169, -4.0F, -29.3345F, 25.8198F, 4, 7, 6, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, -10.0F, -2.0F);
		setRotationAngle(bone7, -1.5708F, 0.0F, 0.0F);
		bone5.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 486, 124, -4.0F, -30.5772F, -15.0919F, 4, 7, 6, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 486, 100, -4.0F, -38.0624F, -15.0919F, 4, 7, 6, 0.0F, false));

		tyre_leftfront3 = new ModelRenderer(this);
		tyre_leftfront3.setRotationPoint(-18.0F, -7.0F, -45.0F);
		lunzi.addChild(tyre_leftfront3);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(37.0F, -3.0F, -34.0F);
		tyre_leftfront3.addChild(bone8);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone8.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 486, 75, -38.0F, 3.0F, 31.0F, 4, 7, 6, 0.0F, false));
		bone9.cubeList.add(new ModelBox(bone9, 484, 56, -38.0F, -4.4853F, 31.0F, 4, 7, 6, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, -10.0F, -2.0F);
		setRotationAngle(bone10, -1.5708F, 0.0F, 0.0F);
		bone8.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 486, 36, -38.0F, -35.7574F, 9.7574F, 4, 7, 6, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 486, 12, -38.0F, -43.2426F, 9.7574F, 4, 7, 6, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(37.0F, -5.0F, -36.0F);
		setRotationAngle(bone11, -0.7854F, 0.0F, 0.0F);
		tyre_leftfront3.addChild(bone11);

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone11.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 456, 11, -38.0F, -21.8492F, 25.8198F, 4, 7, 6, 0.0F, false));
		bone18.cubeList.add(new ModelBox(bone18, 452, 38, -38.0F, -29.3345F, 25.8198F, 4, 7, 6, 0.0F, false));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, -10.0F, -2.0F);
		setRotationAngle(bone19, -1.5708F, 0.0F, 0.0F);
		bone11.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 456, 57, -38.0F, -30.5772F, -15.0919F, 4, 7, 6, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 457, 80, -38.0F, -38.0624F, -15.0919F, 4, 7, 6, 0.0F, false));

		tyre_leftfront4 = new ModelRenderer(this);
		tyre_leftfront4.setRotationPoint(-17.0F, -7.0F, -12.0F);
		lunzi.addChild(tyre_leftfront4);

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(36.0F, -3.0F, -34.0F);
		tyre_leftfront4.addChild(bone20);

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone20.addChild(bone21);
		bone21.cubeList.add(new ModelBox(bone21, 449, 100, -38.0F, 3.0F, 31.0F, 4, 7, 6, 0.0F, false));
		bone21.cubeList.add(new ModelBox(bone21, 454, 124, -38.0F, -4.4853F, 31.0F, 4, 7, 6, 0.0F, false));

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(0.0F, -10.0F, -2.0F);
		setRotationAngle(bone22, -1.5708F, 0.0F, 0.0F);
		bone20.addChild(bone22);
		bone22.cubeList.add(new ModelBox(bone22, 454, 152, -38.0F, -35.7574F, 9.7574F, 4, 7, 6, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 457, 176, -38.0F, -43.2426F, 9.7574F, 4, 7, 6, 0.0F, false));

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(36.0F, -5.0F, -36.0F);
		setRotationAngle(bone23, -0.7854F, 0.0F, 0.0F);
		tyre_leftfront4.addChild(bone23);

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone23.addChild(bone24);
		bone24.cubeList.add(new ModelBox(bone24, 454, 200, -38.0F, -21.8492F, 25.8198F, 4, 7, 6, 0.0F, false));
		bone24.cubeList.add(new ModelBox(bone24, 454, 220, -38.0F, -29.3345F, 25.8198F, 4, 7, 6, 0.0F, false));

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(0.0F, -10.0F, -2.0F);
		setRotationAngle(bone25, -1.5708F, 0.0F, 0.0F);
		bone23.addChild(bone25);
		bone25.cubeList.add(new ModelBox(bone25, 452, 246, -38.0F, -30.5772F, -15.0919F, 4, 7, 6, 0.0F, false));
		bone25.cubeList.add(new ModelBox(bone25, 452, 268, -38.0F, -38.0624F, -15.0919F, 4, 7, 6, 0.0F, false));

		chair = new ModelRenderer(this);
		chair.setRotationPoint(0.0F, -26.0F, 20.0F);
		car.addChild(chair);
		chair.cubeList.add(new ModelBox(chair, 438, 492, -10.0F, -1.0F, -12.0F, 20, 1, 14, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 1.0F);
		setRotationAngle(bone, -0.1745F, 0.0F, 0.0F);
		chair.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 384, 483, -10.0F, -22.9848F, -0.1736F, 20, 22, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 454, 472, 10.0F, -2.9848F, -1.1736F, 1, 3, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 459, 457, -11.0F, -2.9848F, -1.1736F, 1, 3, 2, 0.0F, false));

		mid = new ModelRenderer(this);
		mid.setRotationPoint(0.0F, -10.0F, 2.0F);
		car.addChild(mid);
		mid.cubeList.add(new ModelBox(mid, 4, 491, -15.0F, -1.0F, -7.0F, 30, 3, 15, 0.0F, false));
		mid.cubeList.add(new ModelBox(mid, 89, 488, -15.0F, -16.0F, 8.0F, 30, 18, 1, 0.0F, false));
		mid.cubeList.add(new ModelBox(mid, 171, 491, -15.0F, -16.0F, 9.0F, 29, 8, 8, 0.0F, false));
		mid.cubeList.add(new ModelBox(mid, 299, 497, -8.0F, -8.0F, 9.0F, 16, 4, 4, 0.0F, false));

		bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(0.0F, -10.0F, 17.0F);
		setRotationAngle(bone26, 1.1345F, 0.0F, 0.0F);
		mid.addChild(bone26);
		bone26.cubeList.add(new ModelBox(bone26, 262, 497, -4.0F, -1.0F, 0.0F, 8, 1, 7, 0.0F, false));

		ice = new ModelRenderer(this);
		ice.setRotationPoint(0.0F, 0.0F, 0.0F);
		mid.addChild(ice);
		ice.cubeList.add(new ModelBox(ice, 400, 472, -8.0F, 1.0F, 0.0F, 16, 4, 1, 0.0F, false));
		ice.cubeList.add(new ModelBox(ice, 4, 470, -9.0F, 5.0F, -1.0F, 18, 1, 3, 0.0F, false));
		ice.cubeList.add(new ModelBox(ice, 56, 467, -11.0F, 6.0F, -3.0F, 22, 1, 7, 0.0F, false));
		ice.cubeList.add(new ModelBox(ice, 372, 374, -13.0F, 7.0F, -5.0F, 26, 2, 11, 0.0F, false));

		front = new ModelRenderer(this);
		front.setRotationPoint(0.0F, -10.0F, -3.0F);
		car.addChild(front);
		front.cubeList.add(new ModelBox(front, 6, 363, -15.0F, -35.0F, -50.0F, 30, 38, 48, 0.0F, false));

		level1 = new ModelRenderer(this);
		level1.setRotationPoint(9.0F, -28.0F, -1.0F);
		setRotationAngle(level1, 1.1345F, 0.0F, 0.0F);
		front.addChild(level1);
		level1.cubeList.add(new ModelBox(level1, 176, 452, 1.0F, -2.0F, -2.0F, 2, 2, 16, 0.0F, false));

		level2 = new ModelRenderer(this);
		level2.setRotationPoint(-13.0F, -28.0F, -1.0F);
		setRotationAngle(level2, 1.1345F, 0.0F, 0.0F);
		front.addChild(level2);
		level2.cubeList.add(new ModelBox(level2, 230, 459, 1.0F, -2.0F, -2.0F, 2, 2, 16, 0.0F, false));

		gai = new ModelRenderer(this);
		gai.setRotationPoint(0.0F, -36.0F, -52.0F);
		front.addChild(gai);
		gai.cubeList.add(new ModelBox(gai, 4, 291, -17.0F, -7.0F, 0.0F, 34, 21, 45, 0.0F, false));

		ice2 = new ModelRenderer(this);
		ice2.setRotationPoint(0.0F, 2.0F, -25.0F);
		front.addChild(ice2);
		ice2.cubeList.add(new ModelBox(ice2, 432, 359, -8.0F, 1.0F, 0.0F, 16, 1, 1, 0.0F, false));
		ice2.cubeList.add(new ModelBox(ice2, 338, 359, -9.0F, 2.0F, -1.0F, 18, 1, 3, 0.0F, false));
		ice2.cubeList.add(new ModelBox(ice2, 263, 348, -11.0F, 3.0F, -3.0F, 22, 2, 7, 0.0F, false));

		zombie = new ModelRenderer(this);
		zombie.setRotationPoint(0.0F, -32.0F, 12.0F);
		total.addChild(zombie);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -25.0F, 0.0F);
		zombie.addChild(head);
		head.cubeList.add(new ModelBox(head, 291, 449, -7.0F, -13.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 369, 440, -8.0F, -16.0F, -8.0F, 16, 3, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 294, 435, -7.0F, -17.0F, 0.0F, 14, 1, 8, 0.0F, false));

		bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(8.0F, -14.0F, 0.0F);
		setRotationAngle(bone27, 0.0F, -0.3491F, 1.3963F);
		head.addChild(bone27);
		bone27.cubeList.add(new ModelBox(bone27, 179, 443, 0.0F, 0.0F, -2.0F, 8, 1, 4, 0.0F, false));

		bone28 = new ModelRenderer(this);
		bone28.setRotationPoint(-8.0F, -13.0F, 0.0F);
		setRotationAngle(bone28, 0.0F, 0.3491F, -1.3963F);
		head.addChild(bone28);
		bone28.cubeList.add(new ModelBox(bone28, 219, 446, -7.0F, -1.0F, -2.0F, 8, 1, 4, 0.0F, true));

		bone29 = new ModelRenderer(this);
		bone29.setRotationPoint(0.0F, -17.0F, 8.0F);
		setRotationAngle(bone29, 0.3491F, 0.0F, 0.0F);
		head.addChild(bone29);
		bone29.cubeList.add(new ModelBox(bone29, 264, 444, -1.0F, -2.0F, 0.0F, 2, 2, 2, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-7.0F, -21.0F, 0.0F);
		setRotationAngle(right_hand, -1.2217F, 0.0F, 0.0F);
		zombie.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 176, 403, -7.0F, -3.0F, -3.0F, 6, 21, 6, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(8.0F, -21.0F, 0.0F);
		setRotationAngle(left_hand, -1.2217F, 0.0F, 0.0F);
		zombie.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 217, 406, 0.0F, -3.0F, -3.0F, 6, 21, 6, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -1.0F, 0.0F);
		zombie.addChild(body);
		body.cubeList.add(new ModelBox(body, 252, 400, -8.0F, -23.0F, -4.0F, 16, 24, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 321, 409, -2.0F, 1.0F, -4.0F, 4, 6, 8, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-6.0F, 0.0F, 0.0F);
		zombie.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 358, 398, -2.0F, 0.0F, -13.0F, 6, 6, 17, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 414, 418, -3.0F, 18.0F, -16.0F, 8, 4, 10, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 168, 377, -2.0F, 6.0F, -13.0F, 6, 12, 6, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 0.0F, 0.0F);
		zombie.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 212, 366, -2.0F, 0.0F, -13.0F, 6, 6, 17, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 274, 375, -3.0F, 18.0F, -16.0F, 8, 4, 10, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 326, 373, -2.0F, 6.0F, -13.0F, 6, 12, 6, 0.0F, false));
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
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		this.ice2.rotateAngleX=MathHelper.sin(ageInTicks/20)/2;
		this.tyre_leftfront.rotateAngleX=ageInTicks%360;
		this.tyre_leftfront2.rotateAngleX=ageInTicks%360;
		this.tyre_leftfront3.rotateAngleX=ageInTicks%360;
		this.tyre_leftfront4.rotateAngleX=ageInTicks%360;
	}
}