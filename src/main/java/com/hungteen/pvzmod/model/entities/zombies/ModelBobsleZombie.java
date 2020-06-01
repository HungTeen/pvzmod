package com.hungteen.pvzmod.model.entities.zombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelBobsleZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public ModelBobsleZombie() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		total.addChild(head);
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
		right_hand.setRotationPoint(-9.0F, -46.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 77, 224, -3.0F, -2.0F, -2.0F, 4, 23, 4, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(10.0F, -46.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 54, 227, -2.0F, -2.0F, -2.0F, 4, 23, 4, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 5, 224, -8.0F, -17.0F, -3.0F, 16, 23, 6, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -25.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 184, 229, -2.0F, 0.0F, -2.0F, 4, 21, 4, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 149, 238, -3.0F, 21.0F, -6.0F, 6, 4, 9, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -25.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 238, 228, -2.0F, 0.0F, -2.0F, 4, 21, 4, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 203, 240, -3.0F, 21.0F, -6.0F, 6, 4, 9, 0.0F, false));
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
        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}