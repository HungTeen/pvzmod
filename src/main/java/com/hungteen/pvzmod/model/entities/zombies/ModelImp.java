package com.hungteen.pvzmod.model.entities.zombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelImp extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;
	private final ModelRenderer head;

	public ModelImp() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(2.0F, -12.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 111, 111, -1.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-3.0F, -12.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 94, 111, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -12.0F, 0.0F);
		total.addChild(up);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.cubeList.add(new ModelBox(body, 64, 107, -5.0F, -16.0F, -2.0F, 10, 16, 4, 0.0F, false));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-7.0F, -14.0F, 0.0F);
		up.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 111, 85, -2.0F, -2.0F, -2.0F, 4, 16, 4, 0.0F, false));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(7.0F, -14.0F, 0.0F);
		up.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 93, 85, -2.0F, -2.0F, -2.0F, 4, 16, 4, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -16.0F, 0.0F);
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 59, 89, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
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
        this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}