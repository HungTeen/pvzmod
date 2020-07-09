package com.hungteen.pvzmod.model.entities.zombies.roof;

import com.hungteen.pvzmod.entities.plants.common.EntityCabbagePult;
import com.hungteen.pvzmod.entities.zombies.roof.EntityCataPultZombie;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports

public class ModelCataPultZombie extends ModelBase {
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
	private final ModelRenderer front;
	private final ModelRenderer bone27;
	private final ModelRenderer bone28;
	private final ModelRenderer pult;
	private final ModelRenderer pult2;
	private final ModelRenderer pult3;
	private final ModelRenderer level;
	private final ModelRenderer bone;
	private final ModelRenderer zombie;
	private final ModelRenderer head;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public ModelCataPultZombie() {
		textureWidth = 512;
		textureHeight = 512;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		car = new ModelRenderer(this);
		car.setRotationPoint(0.0F, 0.0F, 32.0F);
		total.addChild(car);

		lunzi = new ModelRenderer(this);
		lunzi.setRotationPoint(0.0F, 0.0F, 0.0F);
		car.addChild(lunzi);

		tyre_leftfront = new ModelRenderer(this);
		tyre_leftfront.setRotationPoint(22.0F, -7.0F, -45.0F);
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
		bone14.addChild(bone13);
		setRotationAngle(bone13, -1.5708F, 0.0F, 0.0F);
		bone13.cubeList.add(new ModelBox(bone13, 478, 412, -4.0F, -35.7574F, 9.7574F, 4, 7, 6, 0.0F, false));
		bone13.cubeList.add(new ModelBox(bone13, 481, 390, -4.0F, -43.2426F, 9.7574F, 4, 7, 6, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, -5.0F, -36.0F);
		tyre_leftfront.addChild(bone15);
		setRotationAngle(bone15, -0.7854F, 0.0F, 0.0F);

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone15.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 481, 372, -4.0F, -21.8492F, 25.8198F, 4, 7, 6, 0.0F, false));
		bone16.cubeList.add(new ModelBox(bone16, 480, 345, -4.0F, -29.3345F, 25.8198F, 4, 7, 6, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone15.addChild(bone17);
		setRotationAngle(bone17, -1.5708F, 0.0F, 0.0F);
		bone17.cubeList.add(new ModelBox(bone17, 478, 324, -4.0F, -30.5772F, -15.0919F, 4, 7, 6, 0.0F, false));
		bone17.cubeList.add(new ModelBox(bone17, 480, 297, -4.0F, -38.0624F, -15.0919F, 4, 7, 6, 0.0F, false));

		tyre_leftfront2 = new ModelRenderer(this);
		tyre_leftfront2.setRotationPoint(22.0F, -7.0F, -19.0F);
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
		bone2.addChild(bone4);
		setRotationAngle(bone4, -1.5708F, 0.0F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 486, 216, -4.0F, -35.7574F, 9.7574F, 4, 7, 6, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 486, 192, -4.0F, -43.2426F, 9.7574F, 4, 7, 6, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, -5.0F, -36.0F);
		tyre_leftfront2.addChild(bone5);
		setRotationAngle(bone5, -0.7854F, 0.0F, 0.0F);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone5.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 488, 145, -4.0F, -21.8492F, 25.8198F, 4, 7, 6, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 484, 169, -4.0F, -29.3345F, 25.8198F, 4, 7, 6, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone5.addChild(bone7);
		setRotationAngle(bone7, -1.5708F, 0.0F, 0.0F);
		bone7.cubeList.add(new ModelBox(bone7, 486, 124, -4.0F, -30.5772F, -15.0919F, 4, 7, 6, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 486, 100, -4.0F, -38.0624F, -15.0919F, 4, 7, 6, 0.0F, false));

		tyre_leftfront3 = new ModelRenderer(this);
		tyre_leftfront3.setRotationPoint(-21.0F, -7.0F, -45.0F);
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
		bone8.addChild(bone10);
		setRotationAngle(bone10, -1.5708F, 0.0F, 0.0F);
		bone10.cubeList.add(new ModelBox(bone10, 486, 36, -38.0F, -35.7574F, 9.7574F, 4, 7, 6, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 486, 12, -38.0F, -43.2426F, 9.7574F, 4, 7, 6, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(37.0F, -5.0F, -36.0F);
		tyre_leftfront3.addChild(bone11);
		setRotationAngle(bone11, -0.7854F, 0.0F, 0.0F);

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone11.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 456, 11, -38.0F, -21.8492F, 25.8198F, 4, 7, 6, 0.0F, false));
		bone18.cubeList.add(new ModelBox(bone18, 452, 38, -38.0F, -29.3345F, 25.8198F, 4, 7, 6, 0.0F, false));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone11.addChild(bone19);
		setRotationAngle(bone19, -1.5708F, 0.0F, 0.0F);
		bone19.cubeList.add(new ModelBox(bone19, 456, 57, -38.0F, -30.5772F, -15.0919F, 4, 7, 6, 0.0F, false));
		bone19.cubeList.add(new ModelBox(bone19, 457, 80, -38.0F, -38.0624F, -15.0919F, 4, 7, 6, 0.0F, false));

		tyre_leftfront4 = new ModelRenderer(this);
		tyre_leftfront4.setRotationPoint(-20.0F, -7.0F, -19.0F);
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
		bone20.addChild(bone22);
		setRotationAngle(bone22, -1.5708F, 0.0F, 0.0F);
		bone22.cubeList.add(new ModelBox(bone22, 454, 152, -38.0F, -35.7574F, 9.7574F, 4, 7, 6, 0.0F, false));
		bone22.cubeList.add(new ModelBox(bone22, 457, 176, -38.0F, -43.2426F, 9.7574F, 4, 7, 6, 0.0F, false));

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(36.0F, -5.0F, -36.0F);
		tyre_leftfront4.addChild(bone23);
		setRotationAngle(bone23, -0.7854F, 0.0F, 0.0F);

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone23.addChild(bone24);
		bone24.cubeList.add(new ModelBox(bone24, 454, 200, -38.0F, -21.8492F, 25.8198F, 4, 7, 6, 0.0F, false));
		bone24.cubeList.add(new ModelBox(bone24, 454, 220, -38.0F, -29.3345F, 25.8198F, 4, 7, 6, 0.0F, false));

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone23.addChild(bone25);
		setRotationAngle(bone25, -1.5708F, 0.0F, 0.0F);
		bone25.cubeList.add(new ModelBox(bone25, 452, 246, -38.0F, -30.5772F, -15.0919F, 4, 7, 6, 0.0F, false));
		bone25.cubeList.add(new ModelBox(bone25, 452, 268, -38.0F, -38.0624F, -15.0919F, 4, 7, 6, 0.0F, false));

		front = new ModelRenderer(this);
		front.setRotationPoint(0.0F, -10.0F, -3.0F);
		car.addChild(front);
		front.cubeList.add(new ModelBox(front, 5, 458, -18.0F, -4.0F, -50.0F, 36, 7, 42, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 174, 481, -18.0F, -16.0F, -21.0F, 20, 12, 12, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 12, 421, 17.0F, -11.0F, -24.0F, 1, 7, 14, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 53, 421, 2.0F, -11.0F, -24.0F, 1, 7, 14, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 105, 424, 3.0F, -14.0F, -18.0F, 8, 9, 8, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 120, 392, 11.0F, -13.0F, -17.0F, 6, 8, 7, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 125, 369, 3.0F, -13.0F, -24.0F, 7, 8, 6, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 148, 345, 11.0F, -12.0F, -24.0F, 6, 7, 6, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 205, 344, 2.0F, -11.0F, -25.0F, 16, 7, 1, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 265, 342, 2.0F, -11.0F, -10.0F, 16, 7, 1, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 328, 338, -18.0F, -10.0F, -52.0F, 36, 16, 2, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 330, 313, -18.0F, -16.0F, -50.0F, 36, 12, 4, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 377, 460, 2.0F, -6.0F, -39.0F, 16, 2, 8, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 434, 488, 7.0F, -24.0F, -38.0F, 6, 1, 6, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 396, 482, 9.0F, -23.0F, -45.0F, 2, 19, 2, 0.0F, false));
		front.cubeList.add(new ModelBox(front, 66, 334, -10.0F, -36.0F, -37.0F, 4, 32, 4, 0.0F, false));

		bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(6.0F, -6.0F, -35.0F);
		front.addChild(bone27);
		setRotationAngle(bone27, 0.0F, 0.0F, -0.1745F);
		bone27.cubeList.add(new ModelBox(bone27, 424, 357, -4.0F, -24.0F, -4.0F, 8, 24, 8, 0.0F, false));

		bone28 = new ModelRenderer(this);
		bone28.setRotationPoint(14.0F, -6.0F, -35.0F);
		front.addChild(bone28);
		setRotationAngle(bone28, 0.0F, 0.0F, 0.1745F);
		bone28.cubeList.add(new ModelBox(bone28, 426, 302, -4.0F, -24.0F, -4.0F, 8, 24, 8, 0.0F, false));

		pult = new ModelRenderer(this);
		pult.setRotationPoint(10.0F, -22.0F, -44.0F);
		front.addChild(pult);
		setRotationAngle(pult, 0.3491F, 0.0F, 0.0F);
		pult.cubeList.add(new ModelBox(pult, 330, 254, -1.0F, -2.0F, -5.0F, 2, 2, 41, 0.0F, false));

		pult2 = new ModelRenderer(this);
		pult2.setRotationPoint(0.0F, -1.0F, 36.0F);
		pult.addChild(pult2);
		setRotationAngle(pult2, -1.0472F, 0.0F, 0.0F);
		pult2.cubeList.add(new ModelBox(pult2, 278, 305, -1.0F, -1.0F, -1.0F, 2, 2, 13, 0.0F, false));

		pult3 = new ModelRenderer(this);
		pult3.setRotationPoint(0.0F, 0.0F, 12.0F);
		pult2.addChild(pult3);
		setRotationAngle(pult3, -1.0472F, 0.0F, 0.0F);
		pult3.cubeList.add(new ModelBox(pult3, 241, 318, -1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F, false));
		pult3.cubeList.add(new ModelBox(pult3, 198, 318, -4.0F, -3.0F, -1.0F, 8, 2, 2, 0.0F, false));
		pult3.cubeList.add(new ModelBox(pult3, 165, 326, -4.0F, -13.0F, -1.0F, 8, 2, 2, 0.0F, false));
		pult3.cubeList.add(new ModelBox(pult3, 137, 329, -6.0F, -11.0F, -1.0F, 2, 8, 2, 0.0F, false));
		pult3.cubeList.add(new ModelBox(pult3, 105, 344, 4.0F, -11.0F, -1.0F, 2, 8, 2, 0.0F, false));
		pult3.cubeList.add(new ModelBox(pult3, 92, 373, -4.0F, -11.0F, 1.0F, 8, 8, 1, 0.0F, false));
		pult3.cubeList.add(new ModelBox(pult3, 77, 394, -4.0F, -11.0F, -6.0F, 8, 8, 7, 0.0F, false));
		pult3.cubeList.add(new ModelBox(pult3, 33, 384, -1.0F, -5.0F, 3.0F, 2, 10, 11, 0.0F, false));

		level = new ModelRenderer(this);
		level.setRotationPoint(-10.0F, -32.0F, -35.0F);
		front.addChild(level);
		setRotationAngle(level, -1.5708F, 0.0F, 0.0F);
		level.cubeList.add(new ModelBox(level, 38, 346, -5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F, false));
		level.cubeList.add(new ModelBox(level, 40, 362, -7.0F, -1.0F, -1.0F, 2, 2, 5, 0.0F, false));
		level.cubeList.add(new ModelBox(level, 13, 326, -12.0F, -1.0F, 4.0F, 7, 2, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(10.0F, -4.0F, -27.0F);
		front.addChild(bone);
		setRotationAngle(bone, -0.5236F, 0.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 12, 365, -2.0F, -5.0F, -2.0F, 4, 7, 4, 0.0F, false));

		zombie = new ModelRenderer(this);
		zombie.setRotationPoint(-8.0F, -33.0F, 14.0F);
		total.addChild(zombie);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -25.0F, 0.0F);
		zombie.addChild(head);
		head.cubeList.add(new ModelBox(head, 261, 474, -7.0F, -6.0F, -7.0F, 14, 14, 14, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-7.0F, -14.0F, 0.0F);
		zombie.addChild(right_hand);
		setRotationAngle(right_hand, -1.0472F, 0.0F, 0.0F);
		right_hand.cubeList.add(new ModelBox(right_hand, 157, 445, -7.0F, -3.0F, -3.0F, 6, 24, 6, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(8.0F, -14.0F, 0.0F);
		zombie.addChild(left_hand);
		setRotationAngle(left_hand, -0.6981F, 0.0F, 0.0F);
		left_hand.cubeList.add(new ModelBox(left_hand, 198, 442, 0.0F, -3.0F, -3.0F, 6, 24, 6, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -1.0F, 0.0F);
		zombie.addChild(body);
		body.cubeList.add(new ModelBox(body, 242, 429, -8.0F, -16.0F, -5.0F, 16, 18, 11, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 333, 486, -2.0F, 2.0F, -4.0F, 4, 6, 10, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-6.0F, 0.0F, 0.0F);
		zombie.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 322, 438, -2.0F, 1.0F, -13.0F, 6, 6, 19, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 434, 448, -3.0F, 18.0F, -15.0F, 8, 4, 9, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 174, 413, -2.0F, 7.0F, -13.0F, 6, 11, 6, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 0.0F, 0.0F);
		zombie.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 208, 393, -2.0F, 1.0F, -13.0F, 6, 6, 19, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 301, 413, -3.0F, 18.0F, -15.0F, 8, 4, 9, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 357, 404, -2.0F, 7.0F, -13.0F, 6, 11, 6, 0.0F, false));
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
		EntityCataPultZombie entity = (EntityCataPultZombie) entityIn;
		int time = entity.getAttackTime();
		if (entity.getCanAttackNow()) {
			pult.rotateAngleX = 0.3491f + 1.2f * MathHelper.sin((float) (time * Math.PI / 20f));
		} else {

			pult.rotateAngleX = 0.3491f + 0.12f * MathHelper.sin((float) (time * Math.PI / 20f));
		}
	}
}