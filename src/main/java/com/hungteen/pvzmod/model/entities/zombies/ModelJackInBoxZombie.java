package com.hungteen.pvzmod.model.entities.zombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelJackInBoxZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_arm;
	private final ModelRenderer bone;
	private final ModelRenderer right_arm;
	private final ModelRenderer head;

	public ModelJackInBoxZombie() {
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

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.cubeList.add(new ModelBox(body, 146, 219, -8.0F, -24.0F, -5.0F, 16, 24, 10, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 94, 231, 0.0F, -14.0F, -21.0F, 12, 10, 12, 0.0F, false));

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
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 90, 183, -7.0F, -14.0F, -7.0F, 14, 14, 14, 0.0F, false));
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
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_arm.rotateAngleX = -3.14159f/2f+MathHelper.cos(limbSwing*4f)* limbSwingAmount;
	}
}