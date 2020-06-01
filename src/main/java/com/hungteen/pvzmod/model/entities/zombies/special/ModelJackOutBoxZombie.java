package com.hungteen.pvzmod.model.entities.zombies.special;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelJackOutBoxZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_arm;
	private final ModelRenderer bone;
	private final ModelRenderer right_arm;
	private final ModelRenderer head;
	private final ModelRenderer bone2;
	private final ModelRenderer jack;
	private final ModelRenderer jack_head;
	private final ModelRenderer h1;
	private final ModelRenderer h3;
	private final ModelRenderer h2;

	public ModelJackOutBoxZombie() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 230, 224, -3.0F, 0.0F, -3.0F, 6, 24, 6, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 208, 248, -3.0F, 23.0F, -6.0F, 6, 1, 3, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 202, 210, -3.0F, 0.0F, -3.0F, 6, 24, 6, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 235, 215, -3.0F, 23.0F, -6.0F, 6, 1, 3, 0.0F, false));

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		up.cubeList.add(new ModelBox(up, 224, 136, -3.0F, -14.0F, -21.0F, 1, 10, 12, 0.0F, false));
		up.cubeList.add(new ModelBox(up, 193, 137, 8.0F, -14.0F, -21.0F, 1, 10, 12, 0.0F, false));
		up.cubeList.add(new ModelBox(up, 159, 148, -2.0F, -14.0F, -21.0F, 10, 10, 1, 0.0F, false));
		up.cubeList.add(new ModelBox(up, 128, 148, -2.0F, -14.0F, -10.0F, 10, 10, 1, 0.0F, false));
		up.cubeList.add(new ModelBox(up, 70, 157, -3.0F, -4.0F, -21.0F, 12, 1, 12, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.cubeList.add(new ModelBox(body, 146, 219, -8.0F, -24.0F, -5.0F, 16, 24, 10, 0.0F, false));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(12.0F, -20.0F, 0.0F);
		setRotationAngle(left_arm, -0.5236F, 0.0F, 0.0F);
		up.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 165, 181, -4.0F, -4.0F, -3.0F, 6, 26, 6, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(2.0F, 21.0F, 0.0F);
		setRotationAngle(bone, -1.0472F, 0.0F, 0.0F);
		left_arm.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 232, 191, 0.0F, 0.0F, -1.0F, 2, 1, 7, 0.0F, false));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-12.0F, -20.0F, 0.0F);
		setRotationAngle(right_arm, -1.5708F, 0.0F, 0.0F);
		up.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 197, 170, -2.0F, -4.0F, -4.0F, 6, 26, 6, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 234, 172, 0.0F, 22.0F, 1.0F, 2, 1, 6, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(head, -0.1745F, 0.0F, 0.0F);
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 90, 183, -7.0F, -14.0F, -7.0F, 14, 14, 14, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(3.0F, -14.0F, -20.0F);
		setRotationAngle(bone2, 2.0071F, 0.0F, 0.0F);
		up.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 204, 120, -5.0F, -1.0F, 0.0F, 10, 1, 11, 0.0F, false));

		jack = new ModelRenderer(this);
		jack.setRotationPoint(3.0F, -4.0F, -15.0F);
		setRotationAngle(jack, 0.0873F, 0.0F, 0.0F);
		up.addChild(jack);
		jack.cubeList.add(new ModelBox(jack, 184, 114, -1.0F, -18.0F, -1.0F, 2, 18, 2, 0.0F, false));

		jack_head = new ModelRenderer(this);
		jack_head.setRotationPoint(0.0F, -18.0F, 0.0F);
		setRotationAngle(jack_head, 0.2618F, 0.0F, 0.0F);
		jack.addChild(jack_head);
		jack_head.cubeList.add(new ModelBox(jack_head, 156, 130, -2.0F, -4.0F, -2.0F, 4, 4, 4, 0.0F, false));

		h1 = new ModelRenderer(this);
		h1.setRotationPoint(0.0F, -3.0F, -2.0F);
		setRotationAngle(h1, -0.4363F, 0.0F, 0.0F);
		jack_head.addChild(h1);
		h1.cubeList.add(new ModelBox(h1, 136, 135, -1.0F, -1.0F, -3.0F, 1, 2, 4, 0.0F, false));

		h3 = new ModelRenderer(this);
		h3.setRotationPoint(0.0F, -3.0F, 2.0F);
		setRotationAngle(h3, 0.4363F, 0.0F, 0.0F);
		jack_head.addChild(h3);
		h3.cubeList.add(new ModelBox(h3, 114, 136, -1.0F, -1.0F, -1.0F, 1, 2, 4, 0.0F, false));

		h2 = new ModelRenderer(this);
		h2.setRotationPoint(0.0F, -4.0F, 0.0F);
		setRotationAngle(h2, 0.0F, 0.0F, -0.2618F);
		jack_head.addChild(h2);
		h2.cubeList.add(new ModelBox(h2, 96, 138, -1.0F, -4.0F, -1.0F, 1, 4, 2, 0.0F, false));
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