package com.hungteen.pvzmod.model.entities.zombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelYetiZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;

	public ModelYetiZombie() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 2.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-7.0F, -36.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 5, 152, -5.0F, 0.0F, -6.0F, 12, 36, 12, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 156, 164, -5.0F, 34.0F, -12.0F, 12, 2, 6, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(6.0F, -36.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 8, 206, -6.0F, 0.0F, -6.0F, 12, 36, 12, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 155, 178, -6.0F, 34.0F, -12.0F, 12, 2, 6, 0.0F, false));

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -36.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.cubeList.add(new ModelBox(body, 174, 202, -12.0F, -36.0F, -8.0F, 24, 36, 16, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(18.0F, -30.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 204, 144, -6.0F, -6.0F, -6.0F, 12, 40, 12, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-19.0F, -30.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 203, 86, -5.0F, -6.0F, -6.0F, 12, 40, 12, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -36.0F, 0.0F);
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 68, 205, -12.0F, -24.0F, -12.0F, 24, 24, 24, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 156, 195, -4.0F, -1.0F, -13.0F, 8, 3, 1, 0.0F, false));
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