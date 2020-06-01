package com.hungteen.pvzmod.model.entities.zombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelPogoZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer zombie;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer bone3;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer pogo;

	public ModelPogoZombie() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 2.0F);
		setRotationAngle(total, 0.0873F, 0.0F, 0.0F);
		

		zombie = new ModelRenderer(this);
		zombie.setRotationPoint(0.0F, -6.0F, 5.0F);
		total.addChild(zombie);
		

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -24.0F, 0.0F);
		zombie.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.cubeList.add(new ModelBox(body, 206, 222, -8.0F, -17.0F, -4.0F, 16, 24, 8, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -0.4363F, 0.0873F, 0.0F);
		left_hand.cubeList.add(new ModelBox(left_hand, 230, 188, -4.0F, -4.0F, -3.0F, 6, 24, 6, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -0.4363F, -0.0873F, 0.0F);
		right_hand.cubeList.add(new ModelBox(right_hand, 229, 152, -2.0F, -4.0F, -3.0F, 6, 24, 6, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 146, 226, -7.0F, -14.0F, -7.0F, 14, 14, 14, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -3.091F, -8.0834F);
		head.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 231, 138, -8.0F, -5.2921F, -0.2749F, 1, 1, 10, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 202, 138, 7.0F, -5.2921F, -0.2749F, 1, 1, 10, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 182, 144, -7.0F, -5.2921F, -0.2749F, 2, 1, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 162, 140, 5.0F, -5.2921F, -0.2749F, 2, 1, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 142, 147, -1.0F, -5.2921F, -0.2749F, 2, 1, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 128, 136, -5.0F, -6.2921F, -0.2749F, 4, 3, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 146, 130, 1.0F, -6.2921F, -0.2749F, 4, 3, 1, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		zombie.addChild(left_leg);
		setRotationAngle(left_leg, -0.4363F, -0.2618F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 184, 188, -3.0F, 0.0F, -3.0F, 6, 24, 6, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		zombie.addChild(right_leg);
		setRotationAngle(right_leg, -0.4363F, 0.2618F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 193, 153, -3.0F, 0.0F, -3.0F, 6, 24, 6, 0.0F, false));

		pogo = new ModelRenderer(this);
		pogo.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(pogo);
		pogo.cubeList.add(new ModelBox(pogo, 150, 211, -1.0F, -10.0F, -6.0F, 2, 10, 2, 0.0F, false));
		pogo.cubeList.add(new ModelBox(pogo, 162, 192, -2.0F, -22.0F, -7.0F, 4, 12, 4, 0.0F, false));
		pogo.cubeList.add(new ModelBox(pogo, 158, 158, -3.0F, -38.0F, -8.0F, 6, 16, 6, 0.0F, false));
		pogo.cubeList.add(new ModelBox(pogo, 130, 163, 3.0F, -34.0F, -6.0F, 9, 2, 2, 0.0F, false));
		pogo.cubeList.add(new ModelBox(pogo, 132, 177, -8.0F, -8.0F, -6.0F, 7, 2, 2, 0.0F, false));
		pogo.cubeList.add(new ModelBox(pogo, 128, 193, 1.0F, -8.0F, -6.0F, 7, 2, 2, 0.0F, false));
		pogo.cubeList.add(new ModelBox(pogo, 126, 213, -12.0F, -34.0F, -6.0F, 9, 2, 2, 0.0F, false));
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